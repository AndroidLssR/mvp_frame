package com.android.mvp_frame.model.entity;


import com.android.mvp_frame.model.annotation.ResultCode;

/**
 * 通用接口返回数据包装类
 */
public class ApiResult<T> {

    /**
     * 请求状态码
     */
    private int errCode;

    /**
     * 返回值说明
     */
    private String errMsg;

    /**
     * 返回数据
     */
    private T data;

    private Error error;

    public class Error {
        private int code;

        private String title;

        @ResultCode
        public int getCode() {
            return code;
        }

        public void setCode(@ResultCode int code) {
            this.code = code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @ResultCode
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(@ResultCode int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
