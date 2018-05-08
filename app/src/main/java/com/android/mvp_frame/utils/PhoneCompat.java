package com.android.mvp_frame.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class PhoneCompat {

//    public static int px2dp(Context context, float pxValue) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics());
//    }
//
//    public static int px2sp(Context context, float pxValue) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pxValue, context.getResources().getDisplayMetrics());
//    }
//
//    public static int dp2px(Context context, float dpValue) {
//        float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//    }
//
//    public static int sp2px(Context context, float spValue) {
//        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (spValue * fontScale + 0.5f);
//    }
//
//    public static int getPhoneWidth(Activity context) {
//        WindowManager manager = context.getWindowManager();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        manager.getDefaultDisplay().getMetrics(outMetrics);
//        return outMetrics.widthPixels;
//    }
//
//    public static int getPhoneHeight(Activity context) {
//        WindowManager manager = context.getWindowManager();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        manager.getDefaultDisplay().getMetrics(outMetrics);
//        return outMetrics.heightPixels;
//    }
//
    /**
     * 隐藏键盘
     */
    public static void hideKeyboard(Activity activity) {
        if (activity == null) return;
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(
                        activity.findViewById(android.R.id.content).getWindowToken(), 0);

    }
//
//    /**
//     * 隐藏键盘
//     */
//    public static void hideKeyboard(View view) {
//        ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
//                .hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//
//    /**
//     * 显示键盘
//     */
//    public static void showKeyboard(View view) {
//        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
//    }
//
//    /**
//     * 判定是否需要隐藏
//     */
//    public static boolean isHideInput(View v, MotionEvent ev) {
//        if (v != null && (v instanceof EditText)) {
//            int[] l = {0, 0};
//            v.getLocationInWindow(l);
//            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
//                    + v.getWidth();
//            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top
//                    && ev.getY() < bottom);
//        }
//        return false;
//    }
//
//    /**
//     * 设置状态栏颜色
//     */
//    public static void setStatusBarColor(@Nonnull Activity activity, @ColorInt int color) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = activity.getWindow();
//            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            //设置状态栏颜色
//            window.setStatusBarColor(color);
//        }
//    }
//
//    /**
//     * 设置状态栏透明
//     */
//    public static void setTranslucentStatus(@Nonnull Activity activity) {
//        Window window = activity.getWindow();
//
//        // 5.0以上系统状态栏透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//    }
//
//    /**
//     * 获取状态栏高度
//     */
//    public static int getStatusBarHeight(@Nonnull Context context) {
//        int statusBarHeight = 0;
//        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
//        }
//        return statusBarHeight;
//    }
//
//    /**
//     * 获取虚拟按键高度
//     */
//    public static int getVirtualBarHeight(@Nonnull Context context) {
//        int vh = 0;
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        DisplayMetrics dm = new DisplayMetrics();
//        try {
//            Class c = Class.forName("android.view.Display");
//            @SuppressWarnings("unchecked")
//            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
//            method.invoke(display, dm);
//            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return vh;
//    }
//
//    /**
//     * 设置Android状态栏的字体颜色，状态栏为亮色的时候字体和图标是黑色，状态栏为暗色的时候字体和图标为白色
//     *
//     * @param dark 状态栏字体是否为深色
//     */
//    public static void setStatusBarFontIconDark(@Nonnull Activity activity, boolean dark) {
//        Window window = activity.getWindow();
//        // 小米MIUI
//        try {
//            Class clazz = window.getClass();
//            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
//            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
//            int darkModeFlag = field.getInt(layoutParams);
//            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
//            if (dark) {    //状态栏亮色且黑色字体
//                extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
//            } else {       //清除黑色字体
//                extraFlagField.invoke(window, 0, darkModeFlag);
//            }
//        } catch (Exception ignored) {
//        }
//
//        // 魅族FlymeUI
//        try {
//            WindowManager.LayoutParams lp = window.getAttributes();
//            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
//            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
//            darkFlag.setAccessible(true);
//            meizuFlags.setAccessible(true);
//            int bit = darkFlag.getInt(null);
//            int value = meizuFlags.getInt(lp);
//            if (dark) {
//                value |= bit;
//            } else {
//                value &= ~bit;
//            }
//            meizuFlags.setInt(lp, value);
//            window.setAttributes(lp);
//        } catch (Exception ignored) {
//        }
//
//        // android6.0+系统
//        // 这个设置和在xml的style文件中用这个<item name="android:windowLightStatusBar">true</item>属性是一样的
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (dark) {
//                window.getDecorView().setSystemUiVisibility(
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            }
//        }
//    }
//
//    /**
//     * 获取手机唯一标示
//     */
//    public static String getIMEI(Context activity) {
//        //IMEI
//        TelephonyManager telephonyMgr = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
//        String deviceId = "";
//        try {
//            deviceId = telephonyMgr.getDeviceId();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return deviceId;
//    }
//
//    private static long lastClickTime;
//
//    public static boolean isFastDoubleClick(long maxtime) {
//        long time = System.currentTimeMillis();
//        long timeD = time - lastClickTime;
//        if (0 < timeD && timeD < maxtime) {
//            Log.i("test", "太快了");
//            return true;
//
//        }
//        lastClickTime = time;
//        return false;
//    }
}
