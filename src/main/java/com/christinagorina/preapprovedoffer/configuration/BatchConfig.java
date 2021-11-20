package com.christinagorina.preapprovedoffer.configuration;

import com.christinagorina.preapprovedoffer.mapper.CheckResultRowMapper;
import com.christinagorina.preapprovedoffer.model.CheckResult;
import com.christinagorina.preapprovedoffer.service.ReportService;
import com.christinagorina.preapprovedoffer.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private static final int CHUNK_SIZE = 5;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final String OUTPUTFILEAUTO = "src\\main\\resources\\output\\outputAuto_";
    private final String OUTPUTFILEMANUAL = "src\\main\\resources\\output\\outputManual_";

    @Bean
    public JobRegistryBeanPostProcessor postProcessor(JobRegistry jobRegistry) {
        var processor = new JobRegistryBeanPostProcessor();
        processor.setJobRegistry(jobRegistry);
        return processor;
    }

    @StepScope
    @Bean
    public JdbcCursorItemReader<CheckResult> checkResultReader() {

        return new JdbcCursorItemReaderBuilder<CheckResult>()
            .sql("select ch.id as id, " +
                    "ch.offer_id as offerId, " +
                    "ch.phone_approve as phoneApprove, " +
                    "ch.passport_approve as passportApprove, " +
                    "ch.address_approve as addressApprove, " +
                    "ch.report as report, " +
                    "ch.result as result " +
                    "from check_result as ch " +
                    "where report = false")
            .name("checkResultJdbcCursorItemReader")
            .dataSource(dataSource)
            .rowMapper(new CheckResultRowMapper())
            .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<CheckResult, CheckResult> checkResultProcessor(ReportService reportService) {
        return reportService::reportProcessing;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<CheckResult> itemWriterHand() {
        return createWriter(OUTPUTFILEMANUAL + Util.fileNameDate() + ".txt", "hand");
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<CheckResult> itemWriterAuto() {
        return createWriter(OUTPUTFILEAUTO + Util.fileNameDate() + ".txt","auto");
    }

    public FlatFileItemWriter<CheckResult> createWriter(String output, String writerName) {
        return new FlatFileItemWriterBuilder<CheckResult>()
            .name(writerName)
            .resource(new FileSystemResource(output))
            .lineAggregator(new PassThroughLineAggregator<>())
            .build();
    }

    @Bean
    public ClassifierCompositeItemWriter<CheckResult> classifierCompositeItemWriter(
            FlatFileItemWriter<CheckResult> itemWriterHand,
            FlatFileItemWriter<CheckResult> itemWriterAuto
    ) {
        ClassifierCompositeItemWriter<CheckResult> compositeItemWriter = new ClassifierCompositeItemWriter<>();
        compositeItemWriter.setClassifier(new BatchClassifier(itemWriterHand, itemWriterAuto));
        return compositeItemWriter;
    }

    @Bean
    public Step checkResultStep(
        JdbcCursorItemReader<CheckResult> checkResultReader,
        FlatFileItemWriter<CheckResult> itemWriterHand,
        FlatFileItemWriter<CheckResult> itemWriterAuto,
        ClassifierCompositeItemWriter<CheckResult> classifierCompositeItemWriter,
        ItemProcessor<CheckResult, CheckResult> checkResultProcessor
    ) {
        return stepBuilderFactory.get("bookStep")
            .<CheckResult, CheckResult>chunk(CHUNK_SIZE)
            .reader(checkResultReader)
            .processor(checkResultProcessor)
            .writer(classifierCompositeItemWriter)
            .stream(itemWriterHand)
            .stream(itemWriterAuto)
            .build();
    }

    @Bean
    public Job checkResultJob(Step checkResultStep) {
        return jobBuilderFactory.get("checkResultJob")
            .incrementer(new RunIdIncrementer())
            .flow(checkResultStep)
            .end()
            .build();
    }

}
