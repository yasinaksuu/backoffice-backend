package com.omniteam.backofisbackend.batch.step;

import lombok.Builder;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;

@Builder
public class OrderWriteProccessFailedStep implements Step {
    @Override
    public String getName() {
        return "OrderWriteFailed";
    }

    @Override
    public boolean isAllowStartIfComplete() {
        return false;
    }

    @Override
    public int getStartLimit() {
        return 0;
    }

    @Override
    public void execute(StepExecution stepExecution) throws JobInterruptedException {

        System.out.println("Order Write Failed");

    }
}
