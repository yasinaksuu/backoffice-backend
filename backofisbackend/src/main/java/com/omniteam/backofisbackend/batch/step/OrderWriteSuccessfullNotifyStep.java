package com.omniteam.backofisbackend.batch.step;

import com.omniteam.backofisbackend.entity.RequestStatus;
import com.omniteam.backofisbackend.service.JobRequestService;
import com.omniteam.backofisbackend.service.implementation.JobRequestServiceImpl;
import lombok.Builder;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

@Builder
public class OrderWriteSuccessfullNotifyStep implements Tasklet {

    private JobRequestServiceImpl jobRequestService;

    public OrderWriteSuccessfullNotifyStep(JobRequestServiceImpl service)
    {
        jobRequestService=service;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        Integer jobRequestID = Integer.valueOf(chunkContext.getStepContext().getJobExecutionContext().get("JobRequestID").toString());
        jobRequestService.setStatus(jobRequestID, RequestStatus.DONE);
        // Başarılı olduğu durumda kullanıcıya bildirim
        System.out.println("Export file has done");
        return RepeatStatus.FINISHED;
    }
}
