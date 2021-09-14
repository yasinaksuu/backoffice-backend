package com.omniteam.backofisbackend.batch;

import com.omniteam.backofisbackend.batch.step.OrderWriteProccessFailedStep;
import com.omniteam.backofisbackend.batch.step.OrderWriteSuccessfullNotifyStep;
import com.omniteam.backofisbackend.batch.step.OrderWriteToFileStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OrderOperationsBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job OrderExporterJob() {
        return this.jobBuilderFactory.get("order-export")
                .start(OrderWriteToFileStep.builder().build())
                .on("COMPLETED").to(OrderWriteSuccessfullNotifyStep.builder().build())
                .on("FAILED").to(OrderWriteProccessFailedStep.builder().build())
                .end().build();

    }


}
