package com.omniteam.backofisbackend.batch.step;

import lombok.Builder;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;

@Builder
public class OrderUnexpectedStateStep implements Step {
    @Override
    public String getName() {
        return "unexpected-state-step";
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

        System.out.println("Unexpected state");
        stepExecution.setExitStatus(ExitStatus.COMPLETED);

    }
}
