package com.android.mvp_frame.model.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 服务器返回状态码
 */
@IntDef({
        ResultCode.SUCCESS, ResultCode.SERVER_HINT, ResultCode.FORBIDDEN, ResultCode.BAD_REQUEST,
        ResultCode.UNAUTHORIZED, ResultCode.TIMEOUT, ResultCode.NOT_FOUND, ResultCode.INTERNAL_SERVER_ERROR
})
@Retention(RetentionPolicy.SOURCE)
public @interface ResultCode {

    //<editor-fold desc="系统异常">
    /**
     * 请求成功
     */
    int SUCCESS = 200;

    /**
     * 提示性状态
     */
    int SERVER_HINT = 300;

    /**
     * 会话非法或过期
     */
    int FORBIDDEN = 400;

    /**
     * 缺少参数
     */
    int BAD_REQUEST = 401;

    /**
     * API未授权
     */
    int UNAUTHORIZED = 402;

    /**
     * 接口调用超时
     */
    int TIMEOUT = 403;

    /**
     * 资源未找到
     */
    int NOT_FOUND = 404;
    /**
     * 服务器端未知错误
     */
    int INTERNAL_SERVER_ERROR = 500;
    //</editor-fold>

}
