package com.omniteam.backofisbackend.shared.result;

import com.omniteam.backofisbackend.entity.BaseEntity;
import com.omniteam.backofisbackend.entity.JobRequest;

public class SuccessResult extends Result {

    public SuccessResult(){
        super(true);
    }

    public SuccessResult(String message){
        super(true,message);
    }


    public SuccessResult(Integer id) {
        super(true,id);
    }
}
