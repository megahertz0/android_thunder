package com.umeng.socialize.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.message.ALIAS_TYPE;
import com.umeng.socialize.Config;
import com.umeng.socialize.SocializeException;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.URLBuilder;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.download.proguard.c;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

public class OauthDialog extends Dialog {
    private static final String a = "OauthDialog";
    private final ResContainer b;
    private WebView c;
    private View d;
    private View e;
    private CheckBox f;
    private int g;
    private Bundle h;
    private String i;
    private Context j;
    private Activity k;
    private SHARE_MEDIA l;
    private Set<String> m;
    private a n;
    private Handler o;

    static class a {
        private UMAuthListener a;
        private SHARE_MEDIA b;
        private int c;

        public a(UMAuthListener uMAuthListener, SHARE_MEDIA share_media) {
            this.a = null;
            this.a = uMAuthListener;
            this.b = share_media;
        }

        public void a(Exception exception) {
            if (this.a != null) {
                this.a.onError(this.b, this.c, exception);
            }
        }

        public void a(Bundle bundle) {
            if (this.a != null) {
                this.a.onComplete(this.b, this.c, b(bundle));
            }
        }

        public void a() {
            if (this.a != null) {
                this.a.onCancel(this.b, this.c);
            }
        }

        private Map<String, String> b(Bundle bundle) {
            if (bundle == null || bundle.isEmpty()) {
                return null;
            }
            Set<String> keySet = bundle.keySet();
            Map<String, String> hashMap = new HashMap();
            for (String str : keySet) {
                hashMap.put(str, bundle.getString(str));
            }
            return hashMap;
        }
    }

    private class b extends WebViewClient {
        private b() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.i(a, new StringBuilder("shouldOverrideUrlLoading current : ").append(str).toString());
            if (DeviceConfig.isNetworkAvailable(OauthDialog.this.j)) {
                if (str.contains("?ud_get=")) {
                    str = OauthDialog.this.a(str);
                }
                if (str.contains(OauthDialog.this.i)) {
                    a(str);
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
            Toast.makeText(OauthDialog.this.j, "\u62b1\u6b49,\u60a8\u7684\u7f51\u7edc\u4e0d\u53ef\u7528...", 0).show();
            return true;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            Log.e(a, new StringBuilder("onReceivedError: ").append(str2).append("\nerrCode: ").append(i).append(" description:").append(str).toString());
            if (OauthDialog.this.e.getVisibility() == 0) {
                OauthDialog.this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            super.onReceivedError(webView, i, str, str2);
            SocializeUtils.safeCloseDialog(OauthDialog.this);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (str.contains("?ud_get=")) {
                str = OauthDialog.this.a(str);
            }
            if (!str.contains("access_key") || !str.contains("access_secret")) {
                super.onPageStarted(webView, str, bitmap);
            } else if (str.contains(OauthDialog.this.i)) {
                a(str);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            OauthDialog.this.o.sendEmptyMessage(1);
            super.onPageFinished(webView, str);
            if (OauthDialog.this.g == 0 && str.contains(OauthDialog.this.i)) {
                a(str);
            }
        }

        private void a(String str) {
            Log.d(a, new StringBuilder("OauthDialog ").append(str).toString());
            OauthDialog.this.g = 1;
            OauthDialog.this.h = SocializeUtils.parseUrl(str);
            if (OauthDialog.this.isShowing()) {
                SocializeUtils.safeCloseDialog(OauthDialog.this);
            }
        }
    }

    public OauthDialog(Activity activity, SHARE_MEDIA share_media, UMAuthListener uMAuthListener) {
        String str = null;
        super(activity, ResContainer.get(activity).style("umeng_socialize_popup_dialog"));
        this.g = 0;
        this.i = XiaomiOAuthConstants.EXTRA_ERROR_CODE_2;
        this.o = new a(this);
        this.j = activity.getApplicationContext();
        this.b = ResContainer.get(this.j);
        this.k = activity;
        this.l = share_media;
        this.n = new a(uMAuthListener, share_media);
        setOwnerActivity(activity);
        LayoutInflater layoutInflater = (LayoutInflater) this.k.getSystemService("layout_inflater");
        int layout = this.b.layout("umeng_socialize_oauth_dialog");
        int id = this.b.id("umeng_socialize_follow");
        int id2 = this.b.id("umeng_socialize_follow_check");
        this.d = layoutInflater.inflate(layout, null);
        View findViewById = this.d.findViewById(id);
        this.f = (CheckBox) this.d.findViewById(id2);
        int i = (this.m == null || this.m.size() <= 0) ? 0 : 1;
        if (share_media == SHARE_MEDIA.SINA || share_media == SHARE_MEDIA.TENCENT) {
            layout = 1;
        } else {
            layout = 0;
        }
        if (i == 0 || r2 == 0) {
            findViewById.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            findViewById.setVisibility(0);
        }
        i = this.b.id("progress_bar_parent");
        layout = this.b.id("umeng_socialize_title_bar_leftBt");
        id2 = this.b.id("umeng_socialize_title_bar_rightBt");
        int id3 = this.b.id("umeng_socialize_title_bar_middleTv");
        int id4 = this.b.id("umeng_socialize_titlebar");
        this.e = this.d.findViewById(i);
        this.e.setVisibility(0);
        ((Button) this.d.findViewById(layout)).setOnClickListener(new b(this));
        this.d.findViewById(id2).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        TextView textView = (TextView) this.d.findViewById(id3);
        if (share_media.toString().equals("SINA")) {
            str = "\u5fae\u535a";
        } else if (share_media.toString().equals(ALIAS_TYPE.RENREN)) {
            str = "\u4eba\u4eba\u7f51";
        } else if (share_media.toString().equals("DOUBAN")) {
            str = "\u8c46\u74e3";
        } else if (share_media.toString().equals("TENCENT")) {
            str = "\u817e\u8baf\u5fae\u535a";
        }
        textView.setText(new StringBuilder("\u6388\u6743").append(str).toString());
        a();
        View cVar = new c(this, this.j, findViewById, this.d.findViewById(id4), SocializeUtils.dip2Px(this.j, 200.0f));
        cVar.addView(this.d, -1, -1);
        setContentView(cVar);
        LayoutParams attributes = getWindow().getAttributes();
        if (SocializeUtils.isFloatWindowStyle(this.j)) {
            int[] floatWindowSize = SocializeUtils.getFloatWindowSize(this.j);
            attributes.width = floatWindowSize[0];
            attributes.height = floatWindowSize[1];
            i = this.b.style("umeng_socialize_dialog_anim_fade");
        } else {
            attributes.height = -1;
            attributes.width = -1;
            i = this.b.style("umeng_socialize_dialog_animations");
        }
        attributes.gravity = 17;
        getWindow().getAttributes().windowAnimations = i;
    }

    public void setWaitUrl(String str) {
        this.i = str;
    }

    private String a(SHARE_MEDIA share_media) {
        URLBuilder uRLBuilder = new URLBuilder(this.j);
        uRLBuilder.setHost(SocializeClient.BASE_URL).setPath("share/auth/").setAppkey(SocializeUtils.getAppkey(this.j)).setEntityKey(Config.EntityKey).withMedia(share_media).withOpId(AgooConstants.ACK_REMOVE_PACKAGE).withSessionId(Config.SessionId).withUID(Config.UID);
        return uRLBuilder.toEncript();
    }

    private boolean a() {
        this.c = (WebView) this.d.findViewById(this.b.id("webView"));
        this.c.setWebViewClient(b());
        this.c.setWebChromeClient(new f(this));
        this.c.requestFocusFromTouch();
        this.c.setVerticalScrollBarEnabled(false);
        this.c.setHorizontalScrollBarEnabled(false);
        this.c.setScrollBarStyle(0);
        this.c.getSettings().setCacheMode(SimpleLog.LOG_LEVEL_DEBUG);
        WebSettings settings = this.c.getSettings();
        settings.setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 8) {
            settings.setPluginState(PluginState.ON);
        }
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        settings.setUseWideViewPort(true);
        if (VERSION.SDK_INT >= 8) {
            settings.setLoadWithOverviewMode(true);
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setGeolocationEnabled(true);
            settings.setAppCacheEnabled(true);
        }
        if (VERSION.SDK_INT >= 11) {
            try {
                Method declaredMethod = WebSettings.class.getDeclaredMethod("setDisplayZoomControls", new Class[]{Boolean.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(settings, new Object[]{Boolean.valueOf(false)});
            } catch (Exception e) {
            }
        }
        try {
            if (this.l == SHARE_MEDIA.RENREN) {
                CookieSyncManager.createInstance(this.j);
                CookieManager.getInstance().removeAllCookie();
            }
        } catch (Exception e2) {
        }
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    private WebViewClient b() {
        Object obj = 1;
        Object obj2 = null;
        try {
            if (WebViewClient.class.getMethod("onReceivedSslError", new Class[]{WebView.class, SslErrorHandler.class, SslError.class}) == null) {
                obj = null;
            }
            obj2 = obj;
        } catch (NoSuchMethodException e) {
        } catch (IllegalArgumentException e2) {
        }
        if (obj2 != null) {
            Log.i(a, "has method onReceivedSslError : ");
            return new g(this);
        }
        Log.i(a, "has no method onReceivedSslError : ");
        return new b();
    }

    private String a(String str) {
        try {
            String[] split = str.split("ud_get=");
            split[1] = AesHelper.decryptNoPadding(split[1], CharsetConvert.UTF_8).trim();
            return split[0] + split[1];
        } catch (Exception e) {
            Log.e(a, "### AuthWebViewClient\u89e3\u5bc6\u5931\u8d25");
            e.printStackTrace();
            return str;
        }
    }

    public void show() {
        super.show();
        this.h = null;
        this.c.loadUrl(a(this.l));
    }

    public void dismiss() {
        if (this.h == null) {
            this.n.a();
        } else if (TextUtils.isEmpty(this.h.getString(c.f))) {
            this.n.a(new SocializeException("unfetch usid..."));
        } else {
            Log.d(a, "### dismiss ");
            this.n.a(this.h);
        }
        super.dismiss();
    }
}
