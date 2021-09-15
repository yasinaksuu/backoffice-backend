package com.omniteam.backofisbackend.batch;

import com.omniteam.backofisbackend.batch.step.OrderUnexpectedStateStep;
import com.omniteam.backofisbackend.batch.step.OrderWriteProccessFailedStep;
import com.omniteam.backofisbackend.batch.step.OrderWriteSuccessfullNotifyStep;
import com.omniteam.backofisbackend.batch.step.OrderWriteToFileStep;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OrderOperationsBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    private Step writeFileStep() {
        return OrderWriteToFileStep.builder().build();
    }

    @Bean
    private Step writeFailStep() {
        return OrderWriteProccessFailedStep.builder().build();
    }

    @Bean
    private Step writeUnexpectedStep() {
        return OrderUnexpectedStateStep.builder().build();
    }

    @Bean
    private Step writeDoneStep() {
        return OrderWriteSuccessfullNotifyStep.builder().build();
    }


    @Qualifier("orderExporterJob")
    @Bean
    public Job OrderExporterJob() {
        return this.jobBuilderFactory.get("order-export")
                .incrementer(new RunIdIncrementer())
//                .preventRestart()
                .start(writeFileStep())
                .on("COMPLETED").to(writeDoneStep())
                .from(writeFileStep()).on("FAILED").to(writeFailStep())
                .from(writeFileStep()).on("*").to(writeUnexpectedStep())
                .end()
                .build();

    }


}
