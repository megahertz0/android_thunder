package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.OpenConfig;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xunlei.downloadprovider.web.core.JsInterface;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class SocialApiIml extends BaseApi {
    private Activity a;

    // compiled from: ProGuard
    class AnonymousClass_1 implements com.tencent.open.e.a {
        final /* synthetic */ Bundle a;
        final /* synthetic */ Activity b;
        final /* synthetic */ IUiListener c;

        AnonymousClass_1(Bundle bundle, Activity activity, IUiListener iUiListener) {
            this.a = bundle;
            this.b = activity;
            this.c = iUiListener;
        }

        public void a(String str) {
            this.a.remove(SocialConstants.PARAM_IMG_DATA);
            if (!TextUtils.isEmpty(str)) {
                this.a.putString(SocialConstants.PARAM_IMG_DATA, str);
            }
            SocialApiIml.this.a(this.b, this.a, this.c);
        }

        public void b(String str) {
            this.a.remove(SocialConstants.PARAM_IMG_DATA);
            this.c.onError(new UiError(-5, Constants.MSG_IMAGE_ERROR, Constants.MSG_IMAGE_ERROR));
            SocialApiIml.this.b();
        }
    }

    // compiled from: ProGuard
    protected class a implements IUiListener {
        b a;

        public a(b bVar) {
            this.a = bVar;
        }

        public void onComplete(Object obj) {
            boolean z = false;
            if (obj != null) {
                try {
                    z = ((JSONObject) obj).getBoolean("check_result");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            SocialApiIml.this.b();
            if (z) {
                SocialApiIml.this.a(SocialApiIml.this.a, this.a.a, SocialApiIml.this, this.a.c, this.a.e);
                return;
            }
            e.a(this.a.c.getString(SocialConstants.PARAM_IMG_DATA));
            SocialApiIml.this.a(SocialApiIml.this.a, SocialApiIml.this, this.a.c, this.a.d, this.a.e);
        }

        public void onError(UiError uiError) {
            SocialApiIml.this.b();
            e.a(this.a.c.getString(SocialConstants.PARAM_IMG_DATA));
            SocialApiIml.this.a(SocialApiIml.this.a, SocialApiIml.this, this.a.c, this.a.d, this.a.e);
        }

        public void onCancel() {
            SocialApiIml.this.b();
            e.a(this.a.c.getString(SocialConstants.PARAM_IMG_DATA));
        }
    }

    // compiled from: ProGuard
    private static class b {
        Intent a;
        String b;
        Bundle c;
        String d;
        IUiListener e;
    }

    // compiled from: ProGuard
    private class c implements IUiListener {
        private IUiListener b;
        private String c;
        private String d;
        private Bundle e;
        private Activity f;

        c(Activity activity, IUiListener iUiListener, String str, String str2, Bundle bundle) {
            this.b = iUiListener;
            this.c = str;
            this.d = str2;
            this.e = bundle;
        }

        public void onComplete(Object obj) {
            CharSequence charSequence;
            try {
                String string = ((JSONObject) obj).getString(SocialConstants.PARAM_ENCRY_EOKEN);
            } catch (Throwable e) {
                e.printStackTrace();
                f.b("openSDK_LOG.SocialApiIml", "OpenApi, EncrytokenListener() onComplete error", e);
                charSequence = null;
            }
            this.e.putString("encrytoken", charSequence);
            SocialApiIml.this.a(SocialApiIml.this, this.c, this.e, this.d, this.b);
            if (TextUtils.isEmpty(charSequence)) {
                f.b("openSDK_LOG.SocialApiIml", "The token get from qq or qzone is empty. Write temp token to localstorage.");
                SocialApiIml.this.writeEncryToken(this.f);
            }
        }

        public void onError(UiError uiError) {
            f.b("openSDK_LOG.SocialApiIml", new StringBuilder("OpenApi, EncryptTokenListener() onError").append(uiError.errorMessage).toString());
            this.b.onError(uiError);
        }

        public void onCancel() {
            this.b.onCancel();
        }
    }

    public SocialApiIml(QQToken qQToken) {
        super(qQToken);
    }

    public SocialApiIml(QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        a(activity, SocialConstants.ACTION_GIFT, bundle, iUiListener);
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        a(activity, SocialConstants.ACTION_ASK, bundle, iUiListener);
    }

    private void a(Activity activity, String str, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            f.c("openSDK_LOG.SocialApiIml", "--askgift--friend chooser not found");
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_ASK_GIFT);
        }
        bundle.putAll(composeActivityParams());
        if (SocialConstants.ACTION_ASK.equals(str)) {
            bundle.putString(JsInterface.FUNPLAY_AD_TRPE, SocialConstants.TYPE_REQUEST);
        } else if (SocialConstants.ACTION_GIFT.equals(str)) {
            bundle.putString(JsInterface.FUNPLAY_AD_TRPE, SocialConstants.TYPE_FREEGIFT);
        }
        a(activity, agentIntentWithTarget, str, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_GIFT), iUiListener, false);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            f.c("openSDK_LOG.SocialApiIml", "--invite--friend chooser not found");
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_INVITE);
        }
        bundle.putAll(composeActivityParams());
        Activity activity2 = activity;
        a(activity2, agentIntentWithTarget, SocialConstants.ACTION_INVITE, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_INVITE), iUiListener, false);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_STORY);
        bundle.putAll(composeActivityParams());
        Activity activity2 = activity;
        a(activity2, agentIntentWithTarget, SocialConstants.ACTION_STORY, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_SEND_STORY), iUiListener, false);
    }

    private void a(Activity activity, Intent intent, String str, Bundle bundle, String str2, IUiListener iUiListener, boolean z) {
        Object obj = null;
        f.c("openSDK_LOG.SocialApiIml", new StringBuilder("-->handleIntent action = ").append(str).append(", activityIntent = null ? ").append(intent == null).toString());
        if (intent != null) {
            a(activity, intent, str, bundle, iUiListener);
            return;
        }
        OpenConfig instance = OpenConfig.getInstance(Global.getContext(), this.mToken.getAppId());
        if (z || instance.getBoolean("C_LoginH5")) {
            int i = 1;
        }
        if (obj != null) {
            a(activity, str, bundle, str2, iUiListener);
        } else {
            handleDownloadLastestQQ(activity, bundle, iUiListener);
        }
    }

    private void a(Activity activity, Intent intent, String str, Bundle bundle, IUiListener iUiListener) {
        f.c("openSDK_LOG.SocialApiIml", new StringBuilder("-->handleIntentWithAgent action = ").append(str).toString());
        intent.putExtra(Constants.KEY_ACTION, str);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_API, iUiListener);
        startAssitActivity(activity, intent, Constants.REQUEST_SOCIAL_API);
    }

    private void a(Activity activity, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        f.c("openSDK_LOG.SocialApiIml", new StringBuilder("-->handleIntentWithH5 action = ").append(str).toString());
        Intent targetActivityIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
        Object cVar = new c(activity, iUiListener, str, str2, bundle);
        Intent targetActivityIntent2 = getTargetActivityIntent("com.tencent.open.agent.EncryTokenActivity");
        if (targetActivityIntent2 == null || targetActivityIntent == null || targetActivityIntent.getComponent() == null || targetActivityIntent2.getComponent() == null || !targetActivityIntent.getComponent().getPackageName().equals(targetActivityIntent2.getComponent().getPackageName())) {
            f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--token activity not found");
            String encrypt = Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(SocialConstants.PARAM_ENCRY_EOKEN, encrypt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            cVar.onComplete(jSONObject);
            return;
        }
        targetActivityIntent2.putExtra(SocialConstants.PARAM_CONSUMER_KEY, this.mToken.getAppId());
        targetActivityIntent2.putExtra(SocialConstants.PARAM_OPEN_ID, this.mToken.getOpenId());
        targetActivityIntent2.putExtra(Constants.PARAM_ACCESS_TOKEN, this.mToken.getAccessToken());
        targetActivityIntent2.putExtra(Constants.KEY_ACTION, SocialConstants.ACTION_CHECK_TOKEN);
        if (hasActivityForIntent(targetActivityIntent2)) {
            f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--found token activity");
            UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_H5, cVar);
            startAssitActivity(activity, targetActivityIntent2, Constants.REQUEST_SOCIAL_H5);
        }
    }

    private void a(Context context, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        f.a("openSDK_LOG.SocialApiIml", "OpenUi, showDialog --start");
        CookieSyncManager.createInstance(context);
        bundle.putString(SocialConstants.PARAM_CONSUMER_KEY, this.mToken.getAppId());
        if (this.mToken.isSessionValid()) {
            bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.mToken.getAccessToken());
        }
        String openId = this.mToken.getOpenId();
        if (openId != null) {
            bundle.putString(SocialConstants.PARAM_OPEN_ID, openId);
        }
        try {
            bundle.putString(Constants.PARAM_PLATFORM_ID, Global.getContext().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        } catch (Exception e) {
            e.printStackTrace();
            bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append(HttpUtils.encodeUrl(bundle));
        String toString = stringBuilder.toString();
        f.b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog TDialog");
        if (SocialConstants.ACTION_CHALLENGE.equals(str) || SocialConstants.ACTION_BRAG.equals(str)) {
            f.b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog PKDialog");
            new PKDialog(this.a, str, toString, iUiListener, this.mToken).show();
            return;
        }
        new TDialog(this.a, str, toString, iUiListener, this.mToken).show();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void writeEncryToken(Context context) {
        String str = "tencent&sdk&qazxc***14969%%";
        String accessToken = this.mToken.getAccessToken();
        String appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        String str2 = "qzone3.4";
        if (accessToken == null || accessToken.length() <= 0 || appId == null || appId.length() <= 0 || openId == null || openId.length() <= 0) {
            str = null;
        } else {
            str = Util.encrypt(str + accessToken + appId + openId + str2);
        }
        com.tencent.open.c.b bVar = new com.tencent.open.c.b(context);
        WebSettings settings = bVar.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        accessToken = new StringBuilder("<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"").append(this.mToken.getOpenId()).append("_").append(this.mToken.getAppId()).append("\"]=\"").append(str).append("\";</script></head><body></body></html>").toString();
        str = ServerSetting.getInstance().getEnvUrl(context, ServerSetting.DEFAULT_LOCAL_STORAGE_URI);
        bVar.loadDataWithBaseURL(str, accessToken, "text/html", "utf-8", str);
    }

    protected Intent getTargetActivityIntent(String str) {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, str);
        Intent intent2 = new Intent();
        intent2.setClassName(Constants.PACKAGE_QQ, str);
        Intent intent3 = new Intent();
        intent3.setClassName(Constants.PACKAGE_QQ_PAD, str);
        if (Util.isTablet(Global.getContext()) && SystemUtils.isActivityExist(Global.getContext(), intent3)) {
            return intent3;
        }
        if (SystemUtils.isActivityExist(Global.getContext(), intent2) && SystemUtils.compareQQVersion(Global.getContext(), "4.7") >= 0) {
            return intent2;
        }
        if (!SystemUtils.isActivityExist(Global.getContext(), intent) || SystemUtils.compareVersion(SystemUtils.getAppVersionName(Global.getContext(), Constants.PACKAGE_QZONE), "4.2") < 0) {
            return null;
        }
        return SystemUtils.isAppSignatureValid(Global.getContext(), intent.getComponent().getPackageName(), Constants.SIGNATRUE_QZONE) ? intent : null;
    }

    private void b() {
        if (!this.a.isFinishing() && this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    protected boolean a() {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, SocialConstants.ACTIVITY_CHECK_FUNCTION);
        return SystemUtils.isActivityExist(Global.getContext(), intent);
    }

    protected void a(Activity activity, String str, IUiListener iUiListener) {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, "com.tencent.open.agent.AgentActivity");
        intent.putExtra(Constants.KEY_ACTION, "action_check");
        Bundle bundle = new Bundle();
        bundle.putString(com.alipay.sdk.cons.c.n, str);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_API, iUiListener);
        startAssitActivity(activity, intent, Constants.REQUEST_SOCIAL_API);
    }

    private b a(Bundle bundle, String str, String str2, IUiListener iUiListener) {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, "com.tencent.open.agent.AgentActivity");
        b bVar = new b();
        bVar.a = intent;
        bVar.c = bundle;
        bVar.d = str2;
        bVar.e = iUiListener;
        bVar.b = str;
        return bVar;
    }

    public void reactive(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (agentIntentWithTarget == null) {
            agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_REACTIVE);
        }
        bundle.putAll(composeActivityParams());
        String envUrl = ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_REACTIVE);
        if (agentIntentWithTarget == null && a()) {
            this.mProgressDialog = new ProgressDialog(activity);
            this.mProgressDialog.setMessage("\u8bf7\u7a0d\u5019...");
            this.mProgressDialog.show();
            bundle.putString(JsInterface.FUNPLAY_AD_TRPE, SocialConstants.TYPE_REACTIVE);
            a(activity, SocialConstants.ACTION_REACTIVE, new a(a(bundle, SocialConstants.ACTION_REACTIVE, envUrl, iUiListener)));
            return;
        }
        bundle.putString(SocialConstants.PARAM_SEND_IMG, bundle.getString(SocialConstants.PARAM_IMG_URL));
        bundle.putString(JsInterface.FUNPLAY_AD_TRPE, SocialConstants.TYPE_REACTIVE);
        bundle.remove(SocialConstants.PARAM_IMG_URL);
        a(activity, agentIntentWithTarget, SocialConstants.ACTION_REACTIVE, bundle, envUrl, iUiListener, false);
    }

    public void brag(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_BRAG);
        bundle.putAll(composeActivityParams());
        Activity activity2 = activity;
        a(activity2, agentIntentWithTarget, SocialConstants.ACTION_BRAG, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_BRAG), iUiListener, false);
    }

    public void challenge(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_CHALLENGE);
        bundle.putAll(composeActivityParams());
        Activity activity2 = activity;
        a(activity2, agentIntentWithTarget, SocialConstants.ACTION_CHALLENGE, bundle, ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_BRAG), iUiListener, false);
    }

    public void grade(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        bundle.putAll(composeActivityParams());
        bundle.putString(GameAppOperation.QQFAV_DATALINE_VERSION, Util.getAppVersion(activity));
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_GRADE);
        String str = "http://qzs.qq.com/open/mobile/rate/sdk_rate.html?";
        if (agentIntentWithTarget == null && a()) {
            this.mProgressDialog = new ProgressDialog(activity);
            this.mProgressDialog.setMessage("\u8bf7\u7a0d\u5019...");
            this.mProgressDialog.show();
            a(activity, SocialConstants.ACTION_GRADE, new a(a(bundle, SocialConstants.ACTION_GRADE, str, iUiListener)));
            return;
        }
        a(activity, agentIntentWithTarget, SocialConstants.ACTION_GRADE, bundle, str, iUiListener, true);
    }

    public void voice(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        bundle.putAll(composeActivityParams());
        bundle.putString(GameAppOperation.QQFAV_DATALINE_VERSION, Util.getAppVersion(activity));
        if (!e.a()) {
            f.c("openSDK_LOG.SocialApiIml", "voice no SDCard");
            iUiListener.onError(new UiError(-12, Constants.MSG_NO_SDCARD, Constants.MSG_NO_SDCARD));
        } else if (!bundle.containsKey(SocialConstants.PARAM_IMG_DATA) || ((Bitmap) bundle.getParcelable(SocialConstants.PARAM_IMG_DATA)) == null) {
            a(activity, bundle, iUiListener);
        } else {
            this.mProgressDialog = new ProgressDialog(activity);
            this.mProgressDialog.setMessage("\u8bf7\u7a0d\u5019...");
            this.mProgressDialog.show();
            new e(new AnonymousClass_1(bundle, activity, iUiListener)).execute(new Bitmap[]{r0});
        }
    }

    private void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        Intent agentIntentWithTarget = getAgentIntentWithTarget(SocialConstants.ACTIVITY_VOICE);
        String envUrl = ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_URL_VOICE);
        if (agentIntentWithTarget == null && a()) {
            if (this.mProgressDialog == null || !this.mProgressDialog.isShowing()) {
                this.mProgressDialog = new ProgressDialog(activity);
                this.mProgressDialog.setTitle("\u8bf7\u7a0d\u5019");
                this.mProgressDialog.show();
            }
            a(activity, SocialConstants.ACTION_VOICE, new a(a(bundle, SocialConstants.ACTION_VOICE, envUrl, iUiListener)));
            return;
        }
        a(activity, agentIntentWithTarget, SocialConstants.ACTION_VOICE, bundle, envUrl, iUiListener, true);
    }
}
