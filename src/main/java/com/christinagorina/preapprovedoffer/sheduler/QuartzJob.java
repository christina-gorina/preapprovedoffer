package com.christinagorina.preapprovedoffer.sheduler;

import com.christinagorina.preapprovedoffer.service.QuartzJobService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuartzJob implements Job {

    private final QuartzJobService quartzJobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        quartzJobService.executeService();
    }

}
