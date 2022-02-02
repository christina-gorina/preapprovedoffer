package com.christinagorina.preapprovedoffer.configuration;

import com.christinagorina.preapprovedoffer.sheduler.QuartzJob;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder
            .newJob()
            .ofType(QuartzJob.class)
            .withIdentity("project", "project")
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger enableTrigger() {
        return TriggerBuilder
            .newTrigger()
            .forJob(jobDetail())
            .withIdentity("project", "project")
            .withSchedule(CronScheduleBuilder.cronSchedule("0/10 0/1 * 1/1 * ? *"))
            .build();
    }

}
