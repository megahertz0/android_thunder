package com.xunlei.common.member.act;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.alipay.sdk.util.h;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.c.l;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressLint({"SetJavaScriptEnabled"})
public class XLQRCodeLoginAuthActivity extends Activity {
    private String TAG;
    private boolean mNormalFinish;
    private int mTaskId;
    private String mUrl;
    protected WebView mWebView;

    public final class JsCallBack {
        @JavascriptInterface
        public final void callBack(int i, String str) {
            XLQRCodeLoginAuthActivity.this.mNormalFinish = true;
            XLQRCodeLoginAuthActivity.this.notifyToTask(i, str);
            XLQRCodeLoginAuthActivity.this.finish();
            XLLog.v(XLQRCodeLoginAuthActivity.this.TAG, new StringBuilder("call back from js error = ").append(i).append("#errordesc = ").append(str).toString());
        }
    }

    public XLQRCodeLoginAuthActivity() {
        this.TAG = XLQRCodeLoginAuthActivity.class.getSimpleName();
        this.mWebView = null;
        this.mTaskId = 0;
        this.mUrl = a.d;
        this.mNormalFinish = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.mTaskId = intent.getIntExtra("xl_task_id", 0);
        if (this.mTaskId != 0) {
            this.mUrl = intent.getStringExtra("xl_qr_auth_url");
            this.mWebView = new WebView(this);
            setContentView(this.mWebView);
            new WebViewTask(this, null).execute(new Void[0]);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!this.mNormalFinish) {
            notifyToTask(XLErrorCode.QR_LOGIN_CANCLE_ERROR, a.d);
        }
        CookieManager.getInstance().removeAllCookie();
    }

    protected boolean webViewPreLoadUrl(WebView webView, String str) {
        XLLog.v(this.TAG, new StringBuilder("webViewPreLoadUrl url = ").append(str).toString());
        return true;
    }

    private void synCookies(CookieManager cookieManager, String str, String str2) {
        String[] split = str2.split(h.b);
        for (String str3 : split) {
            cookieManager.setCookie(str, str3);
        }
        CookieSyncManager.getInstance().sync();
    }

    private String getCookies() {
        StringBuilder stringBuilder = new StringBuilder();
        XLUserInfo i = m.a().i();
        StringBuilder append = stringBuilder.append("business_type=").append(m.a().d()).append(";clientsessionid=").append(i.getStringValue(USERINFOKEY.SessionID)).append(";userid=").append(i.getStringValue(USERINFOKEY.UserID)).append(";pkgname=").append(m.a().m()).append(";appversion=").append(m.a().e()).append(";sdkversion=").append(m.a().c()).append(";deviceid=");
        m.a();
        append.append(m.s());
        try {
            URL url = new URL(this.mUrl);
            String host = url.getHost();
            stringBuilder.append(";domain=").append(host).append(";path=").append(url.getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        XLLog.v(this.TAG, stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void register(WebView webView) {
        if (webView != null) {
            XLLog.v(this.TAG, "register javascript interface to webview");
            webView.addJavascriptInterface(new JsCallBack(), JsCallBack.class.getSimpleName());
        }
    }

    private void notifyToTask(int i, String str) {
        l lVar = (l) m.a().a(this.mTaskId);
        if (lVar != null) {
            if (!(i == 0 || i == 50331653)) {
                i = XLErrorCode.QR_LOGIN_AUTH_ERROR;
            }
            lVar.b(i);
        }
    }
}
