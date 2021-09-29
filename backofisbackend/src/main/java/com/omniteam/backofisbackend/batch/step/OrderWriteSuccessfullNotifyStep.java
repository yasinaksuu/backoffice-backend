package com.omniteam.backofisbackend.batch.step;

import com.omniteam.backofisbackend.entity.RequestStatus;
import com.omniteam.backofisbackend.jms.EmailPublisherMQ;
import com.omniteam.backofisbackend.service.implementation.JobRequestServiceImpl;
import lombok.Builder;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class OrderWriteSuccessfullNotifyStep implements Tasklet {

    @Autowired
    private JobRequestServiceImpl jobRequestService;

    @Autowired
    EmailPublisherMQ publisherMQ;

//    public OrderWriteSuccessfullNotifyStep(JobRequestServiceImpl service) {
//        jobRequestService = service;
//    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        Integer jobRequestID = Integer.valueOf(chunkContext.getStepContext().getJobExecutionContext().get("JobRequestID").toString());
        jobRequestService.setStatus(jobRequestID, RequestStatus.DONE);
        String mailTo=chunkContext.getStepContext().getJobParameters().get("user-email").toString();
        publisherMQ.sendAsSystem(mailTo,"Order Report has ready!");
        return RepeatStatus.FINISHED;
    }
}
