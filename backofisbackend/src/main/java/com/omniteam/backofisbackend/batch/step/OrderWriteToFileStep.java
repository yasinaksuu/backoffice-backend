package com.omniteam.backofisbackend.batch.step;

import lombok.Builder;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

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
        return 1;
    }

    @Override
    public void execute(StepExecution stepExecution) throws JobInterruptedException {
        // Gerçekleştirilecek işlem
        System.out.println("Writing proccess.");
        stepExecution.setExitStatus(ExitStatus.COMPLETED);

    }



}