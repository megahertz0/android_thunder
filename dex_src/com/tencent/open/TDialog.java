package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.connect.auth.AuthConstants;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.a.f;
import com.tencent.open.b.g;
import com.tencent.open.c.b;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.a;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import org.android.spdy.SpdyAgent;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class TDialog extends b {
    static final LayoutParams a;
    static Toast b;
    private static WeakReference<ProgressDialog> d;
    private WeakReference<Context> c;
    private String e;
    private OnTimeListener f;
    private IUiListener g;
    private FrameLayout h;
    private b i;
    private Handler j;
    private boolean k;
    private QQToken l;

    // compiled from: ProGuard
    private class FbWebViewClient extends WebViewClient {
        private FbWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            f.a("openSDK_LOG.TDialog", new StringBuilder("Redirect URL: ").append(str).toString());
            if (str.startsWith(ServerSetting.getInstance().getEnvUrl((Context) TDialog.this.c.get(), ServerSetting.DEFAULT_REDIRECT_URI))) {
                TDialog.this.f.onComplete(Util.parseUrlToJson(str));
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith(Constants.CANCEL_URI)) {
                TDialog.this.f.onCancel();
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith(Constants.CLOSE_URI)) {
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (!str.startsWith(Constants.DOWNLOAD_URI)) {
                return str.startsWith(AuthConstants.PROGRESS_URI);
            } else {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring(XZBDevice.Success))));
                    intent.addFlags(268435456);
                    if (!(TDialog.this.c == null || TDialog.this.c.get() == null)) {
                        ((Context) TDialog.this.c.get()).startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            TDialog.this.f.onError(new UiError(i, str, str2));
            if (!(TDialog.this.c == null || TDialog.this.c.get() == null)) {
                Toast.makeText((Context) TDialog.this.c.get(), "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\u6216\u7cfb\u7edf\u9519\u8bef", 0).show();
            }
            TDialog.this.dismiss();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            f.a("openSDK_LOG.TDialog", new StringBuilder("Webview loading URL: ").append(str).toString());
            super.onPageStarted(webView, str, bitmap);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            TDialog.this.i.setVisibility(0);
        }
    }

    // compiled from: ProGuard
    private class JsListener extends a.b {
        private JsListener() {
        }

        public void onAddShare(String str) {
            f.b("openSDK_LOG.TDialog", "JsListener onAddShare");
            onComplete(str);
        }

        public void onInvite(String str) {
            onComplete(str);
        }

        public void onCancelAddShare(String str) {
            f.e("openSDK_LOG.TDialog", new StringBuilder("JsListener onCancelAddShare").append(str).toString());
            onCancel("cancel");
        }

        public void onCancelLogin() {
            onCancel(a.d);
        }

        public void onCancelInvite() {
            f.e("openSDK_LOG.TDialog", "JsListener onCancelInvite");
            onCancel(a.d);
        }

        public void onComplete(String str) {
            TDialog.this.j.obtainMessage(1, str).sendToTarget();
            f.e("openSDK_LOG.TDialog", new StringBuilder("JsListener onComplete").append(str).toString());
            TDialog.this.dismiss();
        }

        public void onCancel(String str) {
            f.e("openSDK_LOG.TDialog", new StringBuilder("JsListener onCancel --msg = ").append(str).toString());
            TDialog.this.j.obtainMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE, str).sendToTarget();
            TDialog.this.dismiss();
        }

        public void showMsg(String str) {
            TDialog.this.j.obtainMessage(XZBDevice.DOWNLOAD_LIST_FAILED, str).sendToTarget();
        }

        public void onLoad(String str) {
            TDialog.this.j.obtainMessage(XZBDevice.DOWNLOAD_LIST_ALL, str).sendToTarget();
        }
    }

    // compiled from: ProGuard
    private static class OnTimeListener implements IUiListener {
        private String mAction;
        String mAppid;
        String mUrl;
        private WeakReference<Context> mWeakCtx;
        private IUiListener mWeakL;

        public OnTimeListener(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.mWeakCtx = new WeakReference(context);
            this.mAction = str;
            this.mUrl = str2;
            this.mAppid = str3;
            this.mWeakL = iUiListener;
        }

        private void onComplete(String str) {
            try {
                onComplete(Util.parseJson(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            g.a().a(this.mAction + "_H5", SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt(KEYS.RET, ErrCode.ERR_BAN), this.mUrl, false);
            if (this.mWeakL != null) {
                this.mWeakL.onComplete(jSONObject);
                this.mWeakL = null;
            }
        }

        public void onError(UiError uiError) {
            g.a().a(this.mAction + "_H5", SystemClock.elapsedRealtime(), 0, 0, uiError.errorCode, uiError.errorMessage != null ? uiError.errorMessage + this.mUrl : this.mUrl, false);
            if (this.mWeakL != null) {
                this.mWeakL.onError(uiError);
                this.mWeakL = null;
            }
        }

        public void onCancel() {
            if (this.mWeakL != null) {
                this.mWeakL.onCancel();
                this.mWeakL = null;
            }
        }
    }

    // compiled from: ProGuard
    private class THandler extends Handler {
        private OnTimeListener mL;

        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            this.mL = onTimeListener;
        }

        public void handleMessage(Message message) {
            f.b("openSDK_LOG.TDialog", new StringBuilder("--handleMessage--msg.WHAT = ").append(message.what).toString());
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.mL.onComplete((String) message.obj);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.mL.onCancel();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (TDialog.this.c != null && TDialog.this.c.get() != null) {
                        TDialog.c((Context) TDialog.this.c.get(), (String) message.obj);
                    }
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    if (TDialog.this.c != null && TDialog.this.c.get() != null) {
                        TDialog.d((Context) TDialog.this.c.get(), (String) message.obj);
                    }
                default:
                    break;
            }
        }
    }

    static {
        a = new LayoutParams(-1, -1);
        b = null;
    }

    public TDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.k = false;
        this.l = null;
        this.c = new WeakReference(context);
        this.e = str2;
        this.f = new OnTimeListener(context, str, str2, qQToken.getAppId(), iUiListener);
        this.j = new THandler(this.f, context.getMainLooper());
        this.g = iUiListener;
        this.l = qQToken;
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        a();
        b();
    }

    public void onBackPressed() {
        if (this.f != null) {
            this.f.onCancel();
        }
        super.onBackPressed();
    }

    private void a() {
        new TextView((Context) this.c.get()).setText("test");
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.i = new b((Context) this.c.get());
        this.i.setLayoutParams(layoutParams);
        this.h = new FrameLayout((Context) this.c.get());
        layoutParams.gravity = 17;
        this.h.setLayoutParams(layoutParams);
        this.h.addView(this.i);
        setContentView(this.h);
    }

    protected void onConsoleMessage(String str) {
        f.b("openSDK_LOG.TDialog", "--onConsoleMessage--");
        try {
            this.jsBridge.a(this.i, str);
        } catch (Exception e) {
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void b() {
        this.i.setVerticalScrollBarEnabled(false);
        this.i.setHorizontalScrollBarEnabled(false);
        this.i.setWebViewClient(new FbWebViewClient());
        this.i.setWebChromeClient(this.mChromeClient);
        this.i.clearFormData();
        WebSettings settings = this.i.getSettings();
        if (settings != null) {
            settings.setSavePassword(false);
            settings.setSaveFormData(false);
            settings.setCacheMode(-1);
            settings.setNeedInitialFocus(false);
            settings.setBuiltInZoomControls(true);
            settings.setSupportZoom(true);
            settings.setRenderPriority(RenderPriority.HIGH);
            settings.setJavaScriptEnabled(true);
            if (!(this.c == null || this.c.get() == null)) {
                settings.setDatabaseEnabled(true);
                settings.setDatabasePath(((Context) this.c.get()).getApplicationContext().getDir("databases", 0).getPath());
            }
            settings.setDomStorageEnabled(true);
            this.jsBridge.a(new JsListener(), "sdk_js_if");
            this.i.loadUrl(this.e);
            this.i.setLayoutParams(a);
            this.i.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            this.i.getSettings().setSavePassword(false);
        }
    }

    private static void c(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt(JsInterface.FUNPLAY_AD_TRPE);
            CharSequence string = parseJson.getString(SocialConstants.PARAM_SEND_MSG);
            if (i == 0) {
                if (b == null) {
                    b = Toast.makeText(context, string, 0);
                } else {
                    b.setView(b.getView());
                    b.setText(string);
                    b.setDuration(0);
                }
                b.show();
            } else if (i == 1) {
                if (b == null) {
                    b = Toast.makeText(context, string, 1);
                } else {
                    b.setView(b.getView());
                    b.setText(string);
                    b.setDuration(1);
                }
                b.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void d(Context context, String str) {
        if (context != null && str != null) {
            try {
                JSONObject parseJson = Util.parseJson(str);
                int i = parseJson.getInt(JsInterface.KEY_ACTION);
                CharSequence string = parseJson.getString(SocialConstants.PARAM_SEND_MSG);
                if (i == 1) {
                    if (d == null || d.get() == null) {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage(string);
                        d = new WeakReference(progressDialog);
                        progressDialog.show();
                        return;
                    }
                    ((ProgressDialog) d.get()).setMessage(string);
                    if (!((ProgressDialog) d.get()).isShowing()) {
                        ((ProgressDialog) d.get()).show();
                    }
                } else if (i == 0 && d != null && d.get() != null && ((ProgressDialog) d.get()).isShowing()) {
                    ((ProgressDialog) d.get()).dismiss();
                    d = null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
