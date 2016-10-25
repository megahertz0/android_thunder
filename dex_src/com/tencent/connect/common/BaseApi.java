package com.tencent.connect.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.SocialConstants;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public abstract class BaseApi {
    protected static final String ACTION_CHECK_TOKEN = "action_check_token";
    protected static final String ACTIVITY_AGENT = "com.tencent.open.agent.AgentActivity";
    protected static final String ACTIVITY_ENCRY_TOKEN = "com.tencent.open.agent.EncryTokenActivity";
    protected static final String DEFAULT_PF = "openmobile_android";
    private static final String KEY_REQUEST_CODE = "key_request_code";
    private static final int MSG_COMPLETE = 0;
    protected static final String PARAM_ENCRY_EOKEN = "encry_token";
    protected static final String PLATFORM = "desktop_m_qq";
    protected static final String PREFERENCE_PF = "pfStore";
    private static final String TAG = "openSDK_LOG.BaseApi";
    protected static final String VERSION = "android";
    public static String businessId;
    public static String installChannel;
    public static boolean isOEM;
    public static String registerChannel;
    public ProgressDialog mProgressDialog;
    protected QQAuth mQQAuth;
    public QQToken mToken;

    // compiled from: ProGuard
    public class TempRequestListener implements IRequestListener {
        private final Handler mHandler;
        private final IUiListener mListener;

        // compiled from: ProGuard
        class AnonymousClass_1 extends Handler {
            final /* synthetic */ BaseApi val$this$0;

            AnonymousClass_1(Looper looper, BaseApi baseApi) {
                this.val$this$0 = baseApi;
                super(looper);
            }

            public void handleMessage(Message message) {
                if (message.what == 0) {
                    com.tencent.connect.common.BaseApi.TempRequestListener.this.mListener.onComplete(message.obj);
                } else {
                    com.tencent.connect.common.BaseApi.TempRequestListener.this.mListener.onError(new UiError(message.what, (String) message.obj, null));
                }
            }
        }

        public TempRequestListener(IUiListener iUiListener) {
            this.mListener = iUiListener;
            this.mHandler = new AnonymousClass_1(Global.getContext().getMainLooper(), BaseApi.this);
        }

        public void onComplete(JSONObject jSONObject) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = jSONObject;
            obtainMessage.what = 0;
            this.mHandler.sendMessage(obtainMessage);
        }

        public void onIOException(IOException iOException) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = iOException.getMessage();
            obtainMessage.what = -2;
            this.mHandler.sendMessage(obtainMessage);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = malformedURLException.getMessage();
            obtainMessage.what = -3;
            this.mHandler.sendMessage(obtainMessage);
        }

        public void onJSONException(JSONException jSONException) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = jSONException.getMessage();
            obtainMessage.what = -4;
            this.mHandler.sendMessage(obtainMessage);
        }

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = connectTimeoutException.getMessage();
            obtainMessage.what = -7;
            this.mHandler.sendMessage(obtainMessage);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = socketTimeoutException.getMessage();
            obtainMessage.what = -8;
            this.mHandler.sendMessage(obtainMessage);
        }

        public void onNetworkUnavailableException(NetworkUnavailableException networkUnavailableException) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = networkUnavailableException.getMessage();
            obtainMessage.what = -10;
            this.mHandler.sendMessage(obtainMessage);
        }

        public void onHttpStatusException(HttpStatusException httpStatusException) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = httpStatusException.getMessage();
            obtainMessage.what = -9;
            this.mHandler.sendMessage(obtainMessage);
        }

        public void onUnknowException(Exception exception) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.obj = exception.getMessage();
            obtainMessage.what = -6;
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    static {
        registerChannel = null;
        installChannel = null;
        businessId = null;
        isOEM = false;
    }

    public BaseApi(QQAuth qQAuth, QQToken qQToken) {
        this.mQQAuth = qQAuth;
        this.mToken = qQToken;
    }

    public BaseApi(QQToken qQToken) {
        this(null, qQToken);
    }

    public Bundle composeCGIParams() {
        Bundle bundle = new Bundle();
        bundle.putString("format", "json");
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", VERSION.SDK);
        bundle.putString(Constants.KEY_ELECTION_SDKV, Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        if (this.mToken != null && this.mToken.isSessionValid()) {
            bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.mToken.getAccessToken());
            bundle.putString(SocialConstants.PARAM_CONSUMER_KEY, this.mToken.getAppId());
            bundle.putString(SocialConstants.PARAM_OPEN_ID, this.mToken.getOpenId());
            bundle.putString("appid_for_getting_config", this.mToken.getAppId());
        }
        SharedPreferences sharedPreferences = Global.getContext().getSharedPreferences(PREFERENCE_PF, MSG_COMPLETE);
        if (isOEM) {
            bundle.putString(Constants.PARAM_PLATFORM_ID, new StringBuilder("desktop_m_qq-").append(installChannel).append("-android-").append(registerChannel).append(SocializeConstants.OP_DIVIDER_MINUS).append(businessId).toString());
        } else {
            bundle.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, DEFAULT_PF));
        }
        return bundle;
    }

    public String getCommonDownloadQQUrl(String str) {
        Bundle composeCGIParams = composeCGIParams();
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            composeCGIParams.putString("need_version", str);
        }
        stringBuilder.append(ServerSetting.NEED_QQ_VERSION_TIPS_URL);
        stringBuilder.append(HttpUtils.encodeUrl(composeCGIParams));
        return stringBuilder.toString();
    }

    public Bundle composeActivityParams() {
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        if (this.mToken.isSessionValid()) {
            bundle.putString(Constants.PARAM_KEY_STR, this.mToken.getAccessToken());
            bundle.putString(Constants.PARAM_KEY_TYPE, "0x80");
        }
        String openId = this.mToken.getOpenId();
        if (openId != null) {
            bundle.putString(SocialConstants.PARAM_HOPEN_ID, openId);
        }
        bundle.putString(Constants.PARAM_PLATFORM, "androidqz");
        SharedPreferences sharedPreferences = Global.getContext().getSharedPreferences(PREFERENCE_PF, MSG_COMPLETE);
        if (isOEM) {
            bundle.putString(Constants.PARAM_PLATFORM_ID, new StringBuilder("desktop_m_qq-").append(installChannel).append("-android-").append(registerChannel).append(SocializeConstants.OP_DIVIDER_MINUS).append(businessId).toString());
        } else {
            bundle.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, DEFAULT_PF));
            bundle.putString(Constants.PARAM_PLATFORM_ID, DEFAULT_PF);
        }
        bundle.putString(Constants.KEY_ELECTION_SDKV, Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        return bundle;
    }

    private Intent getAssitIntent(Activity activity, Intent intent) {
        Intent intent2 = new Intent(activity.getApplicationContext(), AssistActivity.class);
        intent2.putExtra(SystemUtils.IS_LOGIN, true);
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        return intent2;
    }

    public void startAssistActivity(Activity activity, int i, Intent intent, boolean z) {
        Intent intent2 = new Intent(activity.getApplicationContext(), AssistActivity.class);
        if (z) {
            intent2.putExtra(SystemUtils.IS_QQ_MOBILE_SHARE, true);
        }
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        activity.startActivityForResult(intent2, i);
    }

    protected void startAssistActivity(Activity activity, Bundle bundle, int i, Intent intent) {
        Intent intent2 = new Intent(activity.getApplicationContext(), AssistActivity.class);
        intent2.putExtra(SystemUtils.H5_SHARE_DATA, bundle);
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        activity.startActivityForResult(intent2, i);
    }

    public void startAssitActivity(Activity activity, Intent intent, int i) {
        intent.putExtra(KEY_REQUEST_CODE, i);
        activity.startActivityForResult(getAssitIntent(activity, intent), i);
    }

    public void startAssitActivity(Fragment fragment, Intent intent, int i) {
        intent.putExtra(KEY_REQUEST_CODE, i);
        fragment.startActivityForResult(getAssitIntent(fragment.getActivity(), intent), i);
    }

    public boolean hasActivityForIntent(Intent intent) {
        return intent != null ? SystemUtils.isActivityExist(Global.getContext(), intent) : false;
    }

    public Intent getTargetActivityIntent(String str) {
        Intent intent = new Intent();
        if (Util.isTablet(Global.getContext())) {
            intent.setClassName(Constants.PACKAGE_QQ_PAD, str);
            if (SystemUtils.isActivityExist(Global.getContext(), intent)) {
                return intent;
            }
        }
        intent.setClassName(Constants.PACKAGE_QQ, str);
        return !SystemUtils.isActivityExist(Global.getContext(), intent) ? null : intent;
    }

    public void handleDownloadLastestQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        f.c(TAG, "--handleDownloadLastestQQ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ServerSetting.DOWNLOAD_QQ_URL);
        stringBuilder.append(HttpUtils.encodeUrl(bundle));
        Context context = activity;
        new TDialog(context, a.d, stringBuilder.toString(), null, this.mToken).show();
    }

    protected void showProgressDialog(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            str = "\u8bf7\u7a0d\u5019";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "\u6b63\u5728\u52a0\u8f7d...";
        }
        this.mProgressDialog = ProgressDialog.show(context, str, str2);
        this.mProgressDialog.setCancelable(true);
    }

    public Intent getAgentIntentWithTarget(String str) {
        Intent intent = new Intent();
        Intent targetActivityIntent = getTargetActivityIntent(str);
        if (targetActivityIntent == null || targetActivityIntent.getComponent() == null) {
            return null;
        }
        intent.setClassName(targetActivityIntent.getComponent().getPackageName(), ACTIVITY_AGENT);
        return intent;
    }

    public void releaseResource() {
    }
}
