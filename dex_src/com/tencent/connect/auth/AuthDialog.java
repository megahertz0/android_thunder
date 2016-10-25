package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.connect.auth.AuthMap.Auth;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.b.g;
import com.tencent.open.c.c;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.open.web.security.JniInterface;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.open.web.security.b;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class AuthDialog extends Dialog {
    private String a;
    private OnTimeListener b;
    private IUiListener c;
    private Handler d;
    private FrameLayout e;
    private LinearLayout f;
    private FrameLayout g;
    private ProgressBar h;
    private String i;
    private c j;
    private Context k;
    private b l;
    private boolean m;
    private int n;
    private String o;
    private String p;
    private long q;
    private long r;
    private HashMap<String, Runnable> s;

    // compiled from: ProGuard
    private class LoginWebViewClient extends WebViewClient {
        private LoginWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            f.a("openSDK_LOG.AuthDialog", new StringBuilder("-->Redirect URL: ").append(str).toString());
            if (str.startsWith(AuthConstants.REDIRECT_BROWSER_URI)) {
                JSONObject parseUrlToJson = Util.parseUrlToJson(str);
                AuthDialog.this.m = AuthDialog.this.e();
                if (!AuthDialog.this.m) {
                    if (parseUrlToJson.optString("fail_cb", null) != null) {
                        AuthDialog.this.callJs(parseUrlToJson.optString("fail_cb"), a.d);
                    } else if (parseUrlToJson.optInt("fall_to_wv") == 1) {
                        AuthDialog.a(AuthDialog.this, AuthDialog.this.indexOf("?") >= 0 ? com.alipay.sdk.sys.a.b : "?");
                        AuthDialog.a(AuthDialog.this, (Object) "browser_error=1");
                        AuthDialog.this.j.loadUrl(AuthDialog.this);
                    } else {
                        String optString = parseUrlToJson.optString("redir", null);
                        if (optString != null) {
                            AuthDialog.this.j.loadUrl(optString);
                        }
                    }
                }
                return true;
            } else if (str.startsWith(ServerSetting.DEFAULT_REDIRECT_URI)) {
                AuthDialog.this.b.onComplete(Util.parseUrlToJson(str));
                AuthDialog.this.dismiss();
                return true;
            } else if (str.startsWith(Constants.CANCEL_URI)) {
                AuthDialog.this.b.onCancel();
                AuthDialog.this.dismiss();
                return true;
            } else if (str.startsWith(Constants.CLOSE_URI)) {
                AuthDialog.this.dismiss();
                return true;
            } else if (str.startsWith(Constants.DOWNLOAD_URI)) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring(XZBDevice.Success))));
                    intent.addFlags(268435456);
                    AuthDialog.this.k.startActivity(intent);
                } catch (Throwable e) {
                    f.b("openSDK_LOG.AuthDialog", "-->start download activity exception, e: ", e);
                }
                return true;
            } else if (str.startsWith(AuthConstants.PROGRESS_URI)) {
                try {
                    pathSegments = Uri.parse(str).getPathSegments();
                    if (pathSegments.isEmpty()) {
                        return true;
                    }
                    int intValue = Integer.valueOf((String) pathSegments.get(0)).intValue();
                    if (intValue == 0) {
                        AuthDialog.this.g.setVisibility(XZBDevice.Wait);
                        AuthDialog.this.j.setVisibility(0);
                    } else if (intValue == 1) {
                        AuthDialog.this.g.setVisibility(0);
                    }
                    return true;
                } catch (Exception e2) {
                    return true;
                }
            } else if (str.startsWith(AuthConstants.ON_LOGIN_URI)) {
                try {
                    pathSegments = Uri.parse(str).getPathSegments();
                    if (!pathSegments.isEmpty()) {
                        AuthDialog.this.p = (String) pathSegments.get(0);
                    }
                } catch (Exception e3) {
                }
                return true;
            } else if (AuthDialog.this.l.a(AuthDialog.this.j, str)) {
                return true;
            } else {
                f.c("openSDK_LOG.AuthDialog", "-->Redirect URL: return false");
                return false;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            f.c("openSDK_LOG.AuthDialog", new StringBuilder("-->onReceivedError, errorCode: ").append(i).append(" | description: ").append(str).toString());
            if (!Util.checkNetWork(AuthDialog.this.k)) {
                AuthDialog.this.b.onError(new UiError(9001, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01", str2));
                AuthDialog.this.dismiss();
            } else if (AuthDialog.this.o.startsWith(ServerSetting.DOWNLOAD_QQ_URL)) {
                AuthDialog.this.b.onError(new UiError(i, str, str2));
                AuthDialog.this.dismiss();
            } else {
                long elapsedRealtime = SystemClock.elapsedRealtime() - AuthDialog.this.q;
                if (AuthDialog.this.n > 0 || elapsedRealtime >= AuthDialog.this.r) {
                    AuthDialog.this.j.loadUrl(AuthDialog.this.a());
                    return;
                }
                AuthDialog.m(AuthDialog.this);
                AuthDialog.this.d.postDelayed(new Runnable() {
                    public void run() {
                        LoginWebViewClient.this.j.loadUrl(LoginWebViewClient.this.o);
                    }
                }, 500);
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            f.a("openSDK_LOG.AuthDialog", new StringBuilder("-->onPageStarted, url: ").append(str).toString());
            super.onPageStarted(webView, str, bitmap);
            AuthDialog.this.g.setVisibility(0);
            AuthDialog.this.q = SystemClock.elapsedRealtime();
            if (!TextUtils.isEmpty(AuthDialog.this.o)) {
                AuthDialog.this.d.removeCallbacks((Runnable) AuthDialog.this.s.remove(AuthDialog.this.o));
            }
            AuthDialog.this.o = str;
            Runnable timeOutRunable = new TimeOutRunable(AuthDialog.this.o);
            AuthDialog.this.s.put(str, timeOutRunable);
            AuthDialog.this.d.postDelayed(timeOutRunable, 120000);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            f.a("openSDK_LOG.AuthDialog", new StringBuilder("-->onPageFinished, url: ").append(str).toString());
            AuthDialog.this.g.setVisibility(XZBDevice.Wait);
            if (AuthDialog.this.j != null) {
                AuthDialog.this.j.setVisibility(0);
            }
            if (!TextUtils.isEmpty(str)) {
                AuthDialog.this.d.removeCallbacks((Runnable) AuthDialog.this.s.remove(str));
            }
        }

        @TargetApi(8)
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
            AuthDialog.this.b.onError(new UiError(sslError.getPrimaryError(), "\u8bf7\u6c42\u4e0d\u5408\u6cd5\uff0c\u8bf7\u68c0\u67e5\u624b\u673a\u5b89\u5168\u8bbe\u7f6e\uff0c\u5982\u7cfb\u7edf\u65f6\u95f4\u3001\u4ee3\u7406\u7b49\u3002", "ssl error"));
            AuthDialog.this.dismiss();
        }
    }

    // compiled from: ProGuard
    private class OnTimeListener implements IUiListener {
        String a;
        String b;
        private String d;
        private IUiListener e;

        public OnTimeListener(String str, String str2, String str3, IUiListener iUiListener) {
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.e = iUiListener;
        }

        private void a(String str) {
            try {
                onComplete(Util.parseJson(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt(KEYS.RET, ErrCode.ERR_BAN), this.a, false);
            if (this.e != null) {
                this.e.onComplete(jSONObject);
                this.e = null;
            }
        }

        public void onError(UiError uiError) {
            String str = uiError.errorMessage != null ? uiError.errorMessage + this.a : this.a;
            g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0, 0, uiError.errorCode, str, false);
            AuthDialog.this.a(str);
            if (this.e != null) {
                this.e.onError(uiError);
                this.e = null;
            }
        }

        public void onCancel() {
            if (this.e != null) {
                this.e.onCancel();
                this.e = null;
            }
        }
    }

    // compiled from: ProGuard
    private class THandler extends Handler {
        private OnTimeListener b;

        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            this.b = onTimeListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.b.a((String) message.obj);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.b.onCancel();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    AuthDialog.b(AuthDialog.this.k, (String) message.obj);
                default:
                    break;
            }
        }
    }

    // compiled from: ProGuard
    class TimeOutRunable implements Runnable {
        String a;

        public TimeOutRunable(String str) {
            this.a = a.d;
            this.a = str;
        }

        public void run() {
            f.a("openSDK_LOG.AuthDialog", new StringBuilder("-->timeoutUrl: ").append(this.a).append(" | mRetryUrl: ").append(AuthDialog.this.o).toString());
            if (this.a.equals(AuthDialog.this.o)) {
                AuthDialog.this.onError(new UiError(9002, "\u8bf7\u6c42\u9875\u9762\u8d85\u65f6\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01", AuthDialog.this.o));
                AuthDialog.this.dismiss();
            }
        }
    }

    static /* synthetic */ String a(AuthDialog authDialog, Object obj) {
        String str = authDialog.a + obj;
        authDialog.a = str;
        return str;
    }

    static /* synthetic */ int m(AuthDialog authDialog) {
        int i = authDialog.n;
        authDialog.n = i + 1;
        return i;
    }

    static {
        try {
            Context context = Global.getContext();
            if (context == null) {
                f.c("openSDK_LOG.AuthDialog", new StringBuilder("-->load lib fail, because context is null:").append(AuthAgent.SECURE_LIB_NAME).toString());
            } else if (new File(context.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME).exists()) {
                System.load(context.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME);
                f.c("openSDK_LOG.AuthDialog", new StringBuilder("-->load lib success:").append(AuthAgent.SECURE_LIB_NAME).toString());
            } else {
                f.c("openSDK_LOG.AuthDialog", new StringBuilder("-->fail, because so is not exists:").append(AuthAgent.SECURE_LIB_NAME).toString());
            }
        } catch (Throwable e) {
            f.b("openSDK_LOG.AuthDialog", new StringBuilder("-->load lib error:").append(AuthAgent.SECURE_LIB_NAME).toString(), e);
        }
    }

    public AuthDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.m = false;
        this.q = 0;
        this.r = 30000;
        this.k = context;
        this.a = str2;
        this.b = new OnTimeListener(str, str2, qQToken.getAppId(), iUiListener);
        this.d = new THandler(this.b, context.getMainLooper());
        this.c = iUiListener;
        this.i = str;
        this.l = new b();
        getWindow().setSoftInputMode(R.styleable.AppCompatTheme_actionModeCutDrawable);
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        b();
        d();
        this.s = new HashMap();
    }

    public void onBackPressed() {
        if (!this.m) {
            this.b.onCancel();
        }
        super.onBackPressed();
    }

    protected void onStop() {
        super.onStop();
    }

    private String a(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (!TextUtils.isEmpty(this.p) && this.p.length() >= 4) {
            stringBuilder.append("_u_").append(this.p.substring(this.p.length() - 4));
        }
        return stringBuilder.toString();
    }

    private String a() {
        String toString = new StringBuilder(ServerSetting.DOWNLOAD_QQ_URL).append(this.a.substring(this.a.indexOf("?") + 1)).toString();
        f.c("openSDK_LOG.AuthDialog", "-->generateDownloadUrl, url: http://qzs.qq.com/open/mobile/login/qzsjump.html?");
        return toString;
    }

    private void b() {
        c();
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.j = new c(this.k);
        if (VERSION.SDK_INT >= 11) {
            this.j.setLayerType(1, null);
        }
        this.j.setLayoutParams(layoutParams);
        this.e = new FrameLayout(this.k);
        layoutParams.gravity = 17;
        this.e.setLayoutParams(layoutParams);
        this.e.addView(this.j);
        this.e.addView(this.g);
        setContentView(this.e);
    }

    private void c() {
        LayoutParams layoutParams;
        this.h = new ProgressBar(this.k);
        this.h.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.f = new LinearLayout(this.k);
        View view = null;
        if (this.i.equals(SystemUtils.ACTION_LOGIN)) {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = 5;
            view = new TextView(this.k);
            if (Locale.getDefault().getLanguage().equals("zh")) {
                view.setText("\u767b\u5f55\u4e2d...");
            } else {
                view.setText("Logging in...");
            }
            view.setTextColor(Color.rgb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
            view.setTextSize(18.0f);
            view.setLayoutParams(layoutParams);
        }
        layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f.setLayoutParams(layoutParams);
        this.f.addView(this.h);
        if (view != null) {
            this.f.addView(view);
        }
        this.g = new FrameLayout(this.k);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.leftMargin = 80;
        layoutParams2.rightMargin = 80;
        layoutParams2.topMargin = 40;
        layoutParams2.bottomMargin = 40;
        layoutParams2.gravity = 17;
        this.g.setLayoutParams(layoutParams2);
        this.g.setBackgroundResource(17301504);
        this.g.addView(this.f);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void d() {
        this.j.setVerticalScrollBarEnabled(false);
        this.j.setHorizontalScrollBarEnabled(false);
        this.j.setWebViewClient(new LoginWebViewClient());
        this.j.setWebChromeClient(new WebChromeClient());
        this.j.clearFormData();
        this.j.clearSslPreferences();
        this.j.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.j.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (!view.hasFocus()) {
                            view.requestFocus();
                        }
                        break;
                }
                return false;
            }
        });
        WebSettings settings = this.j.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.k.getDir("databases", 0).getPath());
        settings.setDomStorageEnabled(true);
        f.a("openSDK_LOG.AuthDialog", new StringBuilder("-->mUrl : ").append(this.a).toString());
        this.o = this.a;
        this.j.loadUrl(this.a);
        this.j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.j.getSettings().setSavePassword(false);
        this.l.a(new SecureJsInterface(), "SecureJsInterface");
        SecureJsInterface.isPWDEdit = false;
        super.setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                try {
                    JniInterface.clearAllPWD();
                } catch (Exception e) {
                }
            }
        });
    }

    private boolean e() {
        AuthMap instance = AuthMap.getInstance();
        String makeKey = instance.makeKey();
        Auth auth = new Auth();
        auth.listener = this.c;
        auth.dialog = this;
        auth.key = makeKey;
        String str = instance.set(auth);
        String substring = this.a.substring(0, this.a.indexOf("?"));
        Bundle parseUrl = Util.parseUrl(this.a);
        parseUrl.putString("token_key", makeKey);
        parseUrl.putString(anet.channel.b.HR_SERIAL, str);
        parseUrl.putString("browser", MessageService.MSG_DB_NOTIFY_REACHED);
        this.a = substring + "?" + HttpUtils.encodeUrl(parseUrl);
        return Util.openBrowser(this.k, this.a);
    }

    private static void b(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt(JsInterface.FUNPLAY_AD_TRPE);
            Toast.makeText(context.getApplicationContext(), parseJson.getString(SocialConstants.PARAM_SEND_MSG), i).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void callJs(String str, String str2) {
        this.j.loadUrl(new StringBuilder(BaseJsInterface.JS_PREFIX).append(str).append(SocializeConstants.OP_OPEN_PAREN).append(str2).append(");void(").append(System.currentTimeMillis()).append(");").toString());
    }

    public void dismiss() {
        this.s.clear();
        this.d.removeCallbacksAndMessages(null);
        if (isShowing()) {
            super.dismiss();
        }
        if (this.j != null) {
            this.j.destroy();
            this.j = null;
        }
    }
}
