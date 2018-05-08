package com.android.mvp_frame.data.exception;


import com.android.mvp_frame.model.annotation.ResultCode;

public class BusinessException extends Exception {

    private int errorCode;

    public BusinessException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @ResultCode
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(@ResultCode int errorCode) {
        this.errorCode = errorCode;
    }
}
