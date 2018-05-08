package com.android.mvp_frame.utils;

import android.accounts.NetworkErrorException;
import android.support.v4.util.ObjectsCompat;

import com.android.mvp_frame.data.exception.AccessTokenException;
import com.android.mvp_frame.data.exception.BadRequestException;
import com.android.mvp_frame.data.exception.BusinessException;
import com.android.mvp_frame.data.exception.ResourceNotFoundException;
import com.android.mvp_frame.data.exception.ResultFailException;
import com.android.mvp_frame.data.exception.ServiceHintException;
import com.android.mvp_frame.data.exception.UnknownException;
import com.android.mvp_frame.model.entity.ApiResult;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.android.mvp_frame.model.annotation.ResultCode.BAD_REQUEST;
import static com.android.mvp_frame.model.annotation.ResultCode.FORBIDDEN;
import static com.android.mvp_frame.model.annotation.ResultCode.INTERNAL_SERVER_ERROR;
import static com.android.mvp_frame.model.annotation.ResultCode.NOT_FOUND;
import static com.android.mvp_frame.model.annotation.ResultCode.SERVER_HINT;
import static com.android.mvp_frame.model.annotation.ResultCode.SUCCESS;


public class ReactivexCompat {

    /**
     * Single网络api转换并包含线程调度
     */
    public static <T> SingleTransformer<ApiResult<T>, T> singleTransform() {
        return upstream -> upstream.compose(singleThreadSchedule())
                .filter(t -> !ObjectsCompat.equals(t, null))
                .switchIfEmpty(observer -> observer.onError(new NetworkErrorException()))
                .flatMap(apiResult -> {
                    if (apiResult.getErrCode() == SUCCESS) {
                        if (apiResult.getData() == null) {
                            apiResult.setData((T) new Object());
                        }
                        return Maybe.just(apiResult.getData());
                    } else {
                        return processResultError(apiResult);
                    }
                })
                .toSingle();
    }

    /**
     * Single网络api转换
     */
    public static <T> SingleTransformer<ApiResult<T>, T> singleTransformNoThreadSchedule() {
        return upstream -> upstream
                .filter(t -> !ObjectsCompat.equals(t, null))
                .switchIfEmpty(observer -> observer.onError(new NetworkErrorException()))
                .flatMap(apiResult -> {
                    if (apiResult.getErrCode() == SUCCESS) {
                        if (apiResult.getData() == null) {
                            apiResult.setData((T) new Object());
                        }
                        return Maybe.just(apiResult.getData());
                    } else {
                        return processResultError(apiResult);
                    }
                })
                .toSingle();
    }

    /**
     * 处理网络异常
     */
    private static Maybe processResultError(ApiResult apiResult) {
        int errorCode = apiResult.getError() != null ? apiResult.getError().getCode() : apiResult.getErrCode();
        String errorMsg = apiResult.getError() != null ? apiResult.getError().getTitle() : apiResult.getErrMsg();

        // 小于0都为有业务异常
        if (errorCode < 0) {
            return Maybe.error(new BusinessException(errorMsg, errorCode));
        }

        switch (errorCode) {
            case INTERNAL_SERVER_ERROR:
                return Maybe.error(new ResultFailException(errorMsg));
            case SERVER_HINT:
                return Maybe.error(new ServiceHintException(errorMsg));
            case FORBIDDEN:
                return Maybe.error(new AccessTokenException());
            case NOT_FOUND:
                return Maybe.error(new ResourceNotFoundException(errorMsg));
            case BAD_REQUEST:
                return Maybe.error(new BadRequestException());
            default:
                return Maybe.error(new UnknownException());
        }
    }

    /**
     * Maybe线程调度
     */
    public static <T> MaybeTransformer<T, T> maybeThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     *
     */
    public static <T> SingleTransformer<T, T> singleThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Observable线程调度
     */
    public static <T> ObservableTransformer<T, T> observableThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * flowable线程调度
     */
    public static <T> FlowableTransformer<T, T> flowableThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * completable线程调度
     */
    public static CompletableTransformer completableThreadSchedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
