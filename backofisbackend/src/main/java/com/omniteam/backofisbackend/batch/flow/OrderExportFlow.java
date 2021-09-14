package com.omniteam.backofisbackend.batch.flow;

import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.*;

import java.util.Collection;

public class OrderExportFlow implements Step {

    @Override
    public String getName() {
        return "OrderExportFlow";
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
