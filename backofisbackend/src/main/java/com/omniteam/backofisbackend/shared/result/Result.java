package com.omniteam.backofisbackend.shared.result;

import com.omniteam.backofisbackend.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Result {
    private boolean success;
    @Getter
    private Integer id;
    private String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success,String message) {
        this(success);
        this.message = message;
    }

    public Result(Boolean success,Integer id) {
        this.id=id;
        this.success=success;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }




}
