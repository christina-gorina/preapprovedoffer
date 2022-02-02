package com.christinagorina.preapprovedoffer.sheduler;

import com.christinagorina.preapprovedoffer.service.QuartzJobService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuartzJob implements Job {

    private final QuartzJobService quartzJobService;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        quartzJobService.executeService();
    }

}
