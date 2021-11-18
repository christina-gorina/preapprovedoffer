package com.christinagorina.preapprovedoffer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuartzJobService {

    private final JobLauncher jobLauncher;
    private final Job importLibraryJob;

    public void executeService() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("QuartzJobService...");
        jobLauncher.run(importLibraryJob,
                new JobParametersBuilder()
                .addString("jobDateTime", LocalDateTime.now().toString())
                .toJobParameters());
    }
}
