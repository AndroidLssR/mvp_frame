package com.android.mvp_frame.data.interceptor;

import com.android.mvp_frame.data.manager.PrefManager;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * okHttp拦截器，统一添加请求头
 */
@Singleton
public class HeaderInterceptor implements Interceptor {

    private PrefManager prefManager;

    @Inject
    public HeaderInterceptor(PrefManager prefManager) {
        this.prefManager = prefManager;
    }

    /**
     * 授权token
     */
    private static final String HEADER_CLIENT_KEY = "clientKey";

    /**
     * 设备类型
     */
    private static final String HEADER_DEVICE_TYPE = "deviceType";

    /**
     * 版本名称
     */
    private static final String HEADER_VERSION_NO = "versionNo";

    /**
     * 版本号
     */
    private static final String HEADER_VERSION_CODE = "versionCode";

    /**
     * 手机唯一标识
     */
    private static final String HEADER_IDENTIFIER = "identifier";

    /**
     * 来源
     */
    private static final String HEADER_APP_SOURCE = "appSource";

    /**
     * 手机号码
     */
    private static final String HEADER_USER_MOBILE = "userMobile";

    /**
     * 是否启用前端单元测试
     */
    private static final String HEADER_IS_FRONT_TEST = "isFrontTest";

    /**
     * 区分请求来源  U-FS：erpappv1
     */
    private static final String HEADER_U_FS = "U-FS";

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }


//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request original = chain.request();
//        Request request;
//        try {
//            Request.Builder requestBuilder = original.newBuilder()
//                    .addHeader(HEADER_DEVICE_TYPE, String.valueOf(BuildConfig.DEVICE_TYPE))
//                    .addHeader(HEADER_VERSION_NO, BuildConfig.VERSION_NAME)
//                    .addHeader(HEADER_APP_SOURCE, String.valueOf(BuildConfig.APP_SOURCE))
//                    .addHeader(HEADER_VERSION_CODE, String.valueOf(BuildConfig.VERSION_CODE))
//                    .addHeader(HEADER_U_FS, String.valueOf(BuildConfig.U_FS));
//
//            if (!TextUtils.isEmpty(prefManager.getIMEI())) {
//                requestBuilder.addHeader(HEADER_IDENTIFIER, prefManager.getIMEI());
//            }
//
//            if (!TextUtils.isEmpty(prefManager.getClientKey())) {
//                requestBuilder.addHeader(HEADER_CLIENT_KEY, prefManager.getClientKey());
//            }
//
//            if (!TextUtils.isEmpty(prefManager.getLoginMobile())) {
//                requestBuilder.addHeader(HEADER_USER_MOBILE, prefManager.getLoginMobile());
//            }
//
//            if (BuildConfig.DEBUG) {
//                requestBuilder.addHeader(HEADER_IS_FRONT_TEST, "1");
//            }
//
//            request = requestBuilder.method(original.method(), original.body())
//                    .build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            request = original;
//        }
//
//
//        return chain.proceed(request);
//    }
}
