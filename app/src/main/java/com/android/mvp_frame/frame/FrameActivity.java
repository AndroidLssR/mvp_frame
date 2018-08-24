package com.android.mvp_frame.frame;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mvp_frame.R;
import com.android.mvp_frame.data.exception.AccessTokenException;
import com.android.mvp_frame.data.exception.BadRequestException;
import com.android.mvp_frame.data.exception.BusinessException;
import com.android.mvp_frame.data.exception.ResultFailException;
import com.android.mvp_frame.data.exception.ServiceHintException;
import com.android.mvp_frame.model.event.NetworkThrowableEvent;
import com.android.mvp_frame.ui.LayoutInflaterConvert;
import com.android.mvp_frame.ui.lifecycle.EventBusLifecycle;
import com.android.mvp_frame.utils.PresenterCompat;
import com.android.mvp_frame.utils.SafetyHandler;
import com.google.gson.JsonSyntaxException;
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import dagger.internal.Beta;
import retrofit2.HttpException;


@Beta
public abstract class FrameActivity extends AppCompatActivity
        implements HasFragmentInjector, HasSupportFragmentInjector, SafetyHandler.Delegate, BaseView {

    private final LifecycleProvider<Lifecycle.Event> provider
            = AndroidLifecycle.createLifecycleProvider(this);

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    private Unbinder unbinder;

    // Primary toolbar
    private Toolbar mActionBarToolbar;
    // The toolbar title
    private TextView mToolbarTitle;

    protected View mViewStatusBarPlace;
    private FrameLayout mFrameLayoutContent;

    private final SafetyHandler mSafetyHandler = SafetyHandler.create(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            AndroidInjection.inject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int result[] = new int[3];

        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new LayoutInflaterConvert());

        super.onCreate(savedInstanceState);

        PresenterCompat.inject(this);
        getLifecycle().addObserver(new EventBusLifecycle(this));
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);

        // 存在presenter onCreate调用，保证View已经初始化
        List<BasePresenter> presenters = PresenterCompat.getPresenters(this);
        for (BasePresenter presenter : presenters) {
            getLifecycle().addObserver(presenter);
        }
        getActionBarToolbar();
    }

    protected void setActionBarToolbarIcon(int i) {
        if (this.mActionBarToolbar != null) {
            this.mActionBarToolbar.setNavigationIcon(i);
        }
    }

    protected void setTitle(String title) {
        if (mActionBarToolbar != null && mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mActionBarToolbar != null && mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        }
    }

    @OnClick(R.id.toolbar_navigation_text)
    void backPressed(){
        onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        dismissProgressBar();
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }


    protected void showHomeAsUp(boolean showHomeAsUp) {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                mActionBarToolbar.setNavigationIcon(R.drawable.icon_up);
                setSupportActionBar(mActionBarToolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);

                mToolbarTitle = mActionBarToolbar.findViewById(R.id.toolbar_title);
            }
        }
        return mActionBarToolbar;
    }

    protected SafetyHandler getSafetyHandler() {
        return this.mSafetyHandler;
    }

    @Override
    public void onReceivedHandlerMessage(Message message) {

    }

    @Override
    public LifecycleProvider<Lifecycle.Event> getLifecycleProvider() {
        return provider;
    }

    @Override
    public void showProgressBar(CharSequence message) {
        showProgressBar(message, true);
    }

    @Override
    public void showProgressBar(CharSequence message, boolean cancelable) {
//        if (mDefaultProgressDialog == null) {
//            mDefaultProgressDialog = new DefaultProgressDialog(this, message, cancelable);
//            mDefaultProgressDialog.show();
//        } else {
//            mDefaultProgressDialog.show();
//        }
    }

    @Override
    public void dismissProgressBar() {
//        if (mDefaultProgressDialog != null && mDefaultProgressDialog.isShowing()) {
//            mDefaultProgressDialog.dismiss();
//        }

    }

    @Override
    public void showToast(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkThrowableEvent(NetworkThrowableEvent event) {
        Throwable exception = event.getThrowable();
        exception.printStackTrace();

        String message = analysisExceptionMessage(exception);

        if (event.isToast() && !TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public String analysisExceptionMessage(Throwable exception) {
        String message = null;
        if (exception instanceof BusinessException) {
            disposeBusinessException((BusinessException) exception);
            return exception.getMessage();
        } else {
            if (exception instanceof JsonSyntaxException) {  // 数据格式化错误
                message = "数据异常";
            } else if (exception instanceof HttpException) {    // http异常
                // http 错误不提示
            } else if (exception instanceof UnknownHostException || exception instanceof ConnectException
                    || exception instanceof SocketTimeoutException || exception instanceof NoRouteToHostException) { // 未连接网络、DNS错误和超时
                message = "网络不给力，请检查网络设置";
            } else if (exception instanceof ResultFailException || exception instanceof ServiceHintException) {  // 显示接口返回的错误提示
                message = exception.getMessage();
            } else if (exception instanceof BadRequestException) {
                message = "错误请求";
            } else if (exception instanceof AccessTokenException) {
                message = "异地登录了";
                // TODO: 2018/4/11
//                startActivity(LoginActivity.navigateToLogin(this, true));
//                EventBus.getDefault().post(new LoginOutEvent());
                return null;
            } else {
                message = "未知异常";
            }
            return message;
        }
    }

    /**
     * Todo: 处理业务异常
     */
    //<editor-fold desc="业务异常提示">
    public void disposeBusinessException(BusinessException exception) {
        switch (exception.getErrorCode()) {
//            case FORBIDDEN:
//                break;
        }
    }


    //</editor-fold>
}
