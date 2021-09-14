package com.omniteam.backofisbackend.batch.step;

import lombok.Builder;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;

@Builder
public class OrderWriteSuccessfullNotifyStep implements Step {
    @Override
    public String getName() {
        return "OrderExportFileReady";
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
        // Başarılı olduğu durumda kullanıcıya bildirim
        System.out.println("Export file has done");
    }
}
