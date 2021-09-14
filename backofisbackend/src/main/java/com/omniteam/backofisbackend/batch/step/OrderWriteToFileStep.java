package com.omniteam.backofisbackend.batch.step;

import lombok.Builder;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;

@Builder
public class OrderWriteToFileStep implements Step {

    @Override
    public String getName() {
        return "OrderWrite2File";
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

        // Gerçekleştirilecek işlem
        System.out.println("Step has worked.");


    }
}