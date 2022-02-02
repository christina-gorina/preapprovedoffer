package com.christinagorina.preapprovedoffer.configuration;

import com.christinagorina.preapprovedoffer.model.message.CheckMessage;
import com.christinagorina.preapprovedoffer.model.message.DecisionMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

@Configuration
@EnableIntegration
public class MessagingConfig {

    @Bean
    public QueueChannel checkChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel decisionChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow checkFlow() {

        return IntegrationFlows.from("checkChannel")
                .split()
                .<CheckMessage, String>route(message -> message.type,
                        mapping -> mapping
                                .subFlowMapping("phone", subflow -> subflow
                                        .handle("blackListServiceImpl", "findPhone")
                                )
                                .subFlowMapping("passport", subflow -> subflow
                                        .handle("blackListServiceImpl", "findPassport")
                                )
                                .subFlowMapping("addresses", subflow -> subflow
                                        .handle("blackListServiceImpl", "findAddresses")
                                )
                )
                .<DecisionMessage, String>transform(d ->
                    "Check "
                    .concat(d.getType())
                    .concat(" for offer with id ")
                    .concat(d.getOfferId().toString())
                    .concat(" is ")
                    .concat(d.getDecision().toString())
                )
                .aggregate()
                .channel("decisionChannel")
                .get();
    }
}
