package com.alipay.sdk.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.alipay.sdk.authjs.c;
import com.alipay.sdk.util.k;
import com.umeng.socialize.common.SocializeConstants;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public class AuthActivity extends Activity {
    static final String a = "params";
    static final String b = "redirectUri";
    private WebView c;
    private String d;
    private com.alipay.sdk.widget.a e;
    private Handler f;
    private boolean g;
    private boolean h;
    private Runnable i;

    private class a extends WebChromeClient {
        private a() {
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (TextUtils.isEmpty(message)) {
                return super.onConsoleMessage(consoleMessage);
            }
            Object obj = null;
            if (message.startsWith("h5container.message: ")) {
                obj = message.replaceFirst("h5container.message: ", com.umeng.a.d);
            }
            if (TextUtils.isEmpty(obj)) {
                return super.onConsoleMessage(consoleMessage);
            }
            AuthActivity.b(AuthActivity.this, obj);
            return super.onConsoleMessage(consoleMessage);
        }
    }

    private class b extends WebViewClient {
        private b() {
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            AuthActivity.this.h = true;
            super.onReceivedError(webView, i, str, str2);
        }

        public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (AuthActivity.this.g) {
                sslErrorHandler.proceed();
                AuthActivity.this.g = false;
                return;
            }
            AuthActivity.this.runOnUiThread(new e(this, sslErrorHandler));
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean shouldOverrideUrlLoading(android.webkit.WebView r5, java.lang.String r6) {
            throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.auth.AuthActivity.b.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
            /*
            this = this;
            r0 = 1;
            r1 = r6.toLowerCase();
            r2 = "alipays://platformapi/startApp?";
            r2 = r2.toLowerCase();
            r1 = r1.startsWith(r2);
            if (r1 != 0) goto L_0x0023;
        L_0x0012:
            r1 = r6.toLowerCase();
            r2 = "intent://platformapi/startapp?";
            r2 = r2.toLowerCase();
            r1 = r1.startsWith(r2);
            if (r1 == 0) goto L_0x005c;
        L_0x0023:
            r1 = com.alipay.sdk.auth.AuthActivity.this;	 Catch:{ Throwable -> 0x005a }
            r2 = "com.eg.android.AlipayGphone";
            r1 = com.alipay.sdk.util.k.a(r1, r2);	 Catch:{ Throwable -> 0x005a }
            if (r1 == 0) goto L_0x0034;
        L_0x002e:
            r1 = r1.a();	 Catch:{ Throwable -> 0x005a }
            if (r1 == 0) goto L_0x0035;
        L_0x0034:
            return r0;
        L_0x0035:
            r1 = "intent://platformapi/startapp";
            r1 = r6.startsWith(r1);	 Catch:{ Throwable -> 0x005a }
            if (r1 == 0) goto L_0x0048;
        L_0x003e:
            r1 = "intent://platformapi/startapp?";
            r2 = "alipays://platformapi/startApp?";
            r6 = r6.replaceFirst(r1, r2);	 Catch:{ Throwable -> 0x005a }
        L_0x0048:
            r1 = new android.content.Intent;	 Catch:{ Throwable -> 0x005a }
            r2 = "android.intent.action.VIEW";
            r3 = android.net.Uri.parse(r6);	 Catch:{ Throwable -> 0x005a }
            r1.<init>(r2, r3);	 Catch:{ Throwable -> 0x005a }
            r2 = com.alipay.sdk.auth.AuthActivity.this;	 Catch:{ Throwable -> 0x005a }
            r2.startActivity(r1);	 Catch:{ Throwable -> 0x005a }
            goto L_0x0034;
        L_0x005a:
            r1 = move-exception;
            goto L_0x0034;
        L_0x005c:
            r1 = com.alipay.sdk.auth.AuthActivity.this;
            r1 = com.alipay.sdk.auth.AuthActivity.a(r1, r6);
            if (r1 == 0) goto L_0x0068;
        L_0x0064:
            r5.stopLoading();
            goto L_0x0034;
        L_0x0068:
            r0 = super.shouldOverrideUrlLoading(r5, r6);
            goto L_0x0034;
            */
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AuthActivity.d(AuthActivity.this);
            AuthActivity.this.f.postDelayed(AuthActivity.this.i, StatisticConfig.MIN_UPLOAD_INTERVAL);
            super.onPageStarted(webView, str, bitmap);
        }

        public final void onPageFinished(WebView webView, String str) {
            AuthActivity.g(AuthActivity.this);
            AuthActivity.this.f.removeCallbacks(AuthActivity.this.i);
        }
    }

    public AuthActivity() {
        this.i = new d(this);
    }

    static /* synthetic */ void a(AuthActivity authActivity, com.alipay.sdk.authjs.a aVar) {
        if (authActivity.c != null && aVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.alipay.sdk.authjs.a.e, aVar.i);
                jSONObject.put(com.alipay.sdk.authjs.a.g, aVar.k);
                jSONObject.put(SocializeConstants.OP_KEY, aVar.m);
                jSONObject.put(com.alipay.sdk.authjs.a.h, aVar.l);
                authActivity.runOnUiThread(new c(authActivity, String.format("AlipayJSBridge._invokeJS(%s)", new Object[]{jSONObject.toString()})));
            } catch (JSONException e) {
            }
        }
    }

    static /* synthetic */ boolean a(AuthActivity authActivity, String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, authActivity.d)) {
                str = str + "?resultCode=150";
            }
            h.a((Activity) authActivity, str);
        }
        authActivity.finish();
        return true;
    }

    static /* synthetic */ void b(AuthActivity authActivity, String str) {
        Object obj;
        c cVar = new c(authActivity.getApplicationContext(), new b(authActivity));
        try {
            JSONObject jSONObject = new JSONObject(str);
            Object string = jSONObject.getString(com.alipay.sdk.authjs.a.e);
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(SocializeConstants.OP_KEY);
                jSONObject2 = jSONObject2 instanceof JSONObject ? jSONObject2 : null;
                String string2 = jSONObject.getString(com.alipay.sdk.authjs.a.g);
                String string3 = jSONObject.getString(com.alipay.sdk.authjs.a.d);
                com.alipay.sdk.authjs.a aVar = new com.alipay.sdk.authjs.a(com.alipay.sdk.authjs.a.b);
                aVar.j = string3;
                aVar.k = string2;
                aVar.m = jSONObject2;
                aVar.i = string;
                cVar.a(aVar);
            }
        } catch (Exception e) {
            obj = string;
            if (!TextUtils.isEmpty(r0)) {
                try {
                    cVar.a(r0, com.alipay.sdk.authjs.a.a.d);
                } catch (JSONException e2) {
                }
            }
        }
    }

    static /* synthetic */ void d(AuthActivity authActivity) {
        try {
            if (authActivity.e == null) {
                authActivity.e = new com.alipay.sdk.widget.a(authActivity, com.alipay.sdk.widget.a.a);
            }
            authActivity.e.a();
        } catch (Exception e) {
            authActivity.e = null;
        }
    }

    static /* synthetic */ void g(AuthActivity authActivity) {
        if (authActivity.e != null) {
            authActivity.e.b();
        }
        authActivity.e = null;
    }

    protected void onCreate(Bundle bundle) {
        Method method;
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                finish();
                return;
            }
            try {
                this.d = extras.getString(b);
                String string = extras.getString(a);
                if (k.b(string)) {
                    super.requestWindowFeature(1);
                    this.f = new Handler(getMainLooper());
                    View linearLayout = new LinearLayout(this);
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                    linearLayout.setOrientation(1);
                    setContentView(linearLayout, layoutParams);
                    this.c = new WebView(this);
                    layoutParams.weight = 1.0f;
                    this.c.setVisibility(0);
                    linearLayout.addView(this.c, layoutParams);
                    WebSettings settings = this.c.getSettings();
                    settings.setUserAgentString(settings.getUserAgentString() + k.d(getApplicationContext()));
                    settings.setRenderPriority(RenderPriority.HIGH);
                    settings.setSupportMultipleWindows(true);
                    settings.setJavaScriptEnabled(true);
                    settings.setSavePassword(false);
                    settings.setJavaScriptCanOpenWindowsAutomatically(true);
                    settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
                    settings.setAllowFileAccess(false);
                    settings.setTextSize(TextSize.NORMAL);
                    this.c.setVerticalScrollbarOverlay(true);
                    this.c.setWebViewClient(new b());
                    this.c.setWebChromeClient(new a());
                    this.c.setDownloadListener(new a(this));
                    this.c.loadUrl(string);
                    if (VERSION.SDK_INT >= 7) {
                        try {
                            method = this.c.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                            if (method != null) {
                                method.invoke(this.c.getSettings(), new Object[]{Boolean.valueOf(true)});
                            }
                        } catch (Exception e) {
                        }
                    }
                    try {
                        this.c.removeJavascriptInterface("searchBoxJavaBridge_");
                        this.c.removeJavascriptInterface("accessibility");
                        this.c.removeJavascriptInterface("accessibilityTraversal");
                    } catch (Throwable th) {
                        try {
                            method = this.c.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                            if (method != null) {
                                method.invoke(this.c, new Object[]{"searchBoxJavaBridge_"});
                                method.invoke(this.c, new Object[]{"accessibility"});
                                method.invoke(this.c, new Object[]{"accessibilityTraversal"});
                            }
                        } catch (Throwable th2) {
                        }
                    }
                    if (VERSION.SDK_INT >= 19) {
                        this.c.getSettings().setCacheMode(1);
                        return;
                    }
                    return;
                }
                finish();
            } catch (Exception e2) {
                finish();
            }
        } catch (Exception e3) {
            finish();
        }
    }

    public void onBackPressed() {
        if (!this.c.canGoBack()) {
            h.a((Activity) this, this.d + "?resultCode=150");
            finish();
        } else if (this.h) {
            h.a((Activity) this, this.d + "?resultCode=150");
            finish();
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        if (!"SDKLite://h5quit".equalsIgnoreCase(str)) {
            if (TextUtils.equals(str, this.d)) {
                str = str + "?resultCode=150";
            }
            h.a((Activity) this, str);
        }
        finish();
        return true;
    }

    private void b(String str) {
        Object obj;
        c cVar = new c(getApplicationContext(), new b(this));
        try {
            JSONObject jSONObject = new JSONObject(str);
            Object string = jSONObject.getString(com.alipay.sdk.authjs.a.e);
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(SocializeConstants.OP_KEY);
                jSONObject2 = jSONObject2 instanceof JSONObject ? jSONObject2 : null;
                String string2 = jSONObject.getString(com.alipay.sdk.authjs.a.g);
                String string3 = jSONObject.getString(com.alipay.sdk.authjs.a.d);
                com.alipay.sdk.authjs.a aVar = new com.alipay.sdk.authjs.a(com.alipay.sdk.authjs.a.b);
                aVar.j = string3;
                aVar.k = string2;
                aVar.m = jSONObject2;
                aVar.i = string;
                cVar.a(aVar);
            }
        } catch (Exception e) {
            obj = string;
            if (!TextUtils.isEmpty(r0)) {
                try {
                    cVar.a(r0, com.alipay.sdk.authjs.a.a.d);
                } catch (JSONException e2) {
                }
            }
        }
    }

    private void a(com.alipay.sdk.authjs.a aVar) {
        if (this.c != null && aVar != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.alipay.sdk.authjs.a.e, aVar.i);
                jSONObject.put(com.alipay.sdk.authjs.a.g, aVar.k);
                jSONObject.put(SocializeConstants.OP_KEY, aVar.m);
                jSONObject.put(com.alipay.sdk.authjs.a.h, aVar.l);
                runOnUiThread(new c(this, String.format("AlipayJSBridge._invokeJS(%s)", new Object[]{jSONObject.toString()})));
            } catch (JSONException e) {
            }
        }
    }

    private void a() {
        try {
            if (this.e == null) {
                this.e = new com.alipay.sdk.widget.a(this, com.alipay.sdk.widget.a.a);
            }
            this.e.a();
        } catch (Exception e) {
            this.e = null;
        }
    }

    private void b() {
        if (this.e != null) {
            this.e.b();
        }
        this.e = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.c != null) {
            this.c.removeAllViews();
            try {
                this.c.destroy();
            } catch (Throwable th) {
            }
            this.c = null;
        }
    }
}
