package com.android.mvp_frame.data.exception;

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 创建错误信息工厂类，解析来自Exception的异常
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * 根据Exception处理或返回异常信息
     *
     * @return {@link String} 异常信息
     */
    public static String create(Exception exception) {
        String message = null;

        if (exception instanceof JsonSyntaxException) {  // 数据格式化错误
            message = "数据异常";
        } else if (exception instanceof HttpException) {    // http异常
            // http 错误不提示
        } else if (exception instanceof UnknownHostException || exception instanceof ConnectException || exception instanceof SocketTimeoutException) { // 未连接网络、DNS错误和超时
            message = "网络不给力，请检查网络设置";
        } else if (exception instanceof ResultFailException) {  // 显示接口返回的错误提示
            message = exception.getMessage();
        } else {
            exception.printStackTrace();
        }

        return message;
    }
}
