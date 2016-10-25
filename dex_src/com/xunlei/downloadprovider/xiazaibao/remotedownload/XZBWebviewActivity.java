package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.WebpageProgressBar;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSniffer;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDeviceManager;
import com.xunlei.xiazaibao.sdk.XZBDeviceManager.USERINFO;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import org.android.spdy.SpdyAgent;

@SuppressLint({"SetJavaScriptEnabled"})
public class XZBWebviewActivity extends BaseActivity {
    private static final String e;
    WebView a;
    ErrorView b;
    String c;
    Handler d;
    private TextView f;
    private ImageView g;
    private ImageView h;
    private String i;
    private boolean j;
    private boolean k;
    private WebpageProgressBar l;
    private WebViewClient m;
    private WebChromeClient n;

    public final class DownloadTaskListJsInterface {
        DownloadTaskListJsInterface() {
        }

        @JavascriptInterface
        public final boolean isLogged() {
            LoginHelper.a();
            return LoginHelper.c();
        }

        @JavascriptInterface
        public final void goToLoginPageAndCallback(String str) {
        }

        @JavascriptInterface
        public final void gotoLoginShoulei() {
            XZBWebviewActivity.this.d.post(new bp(this));
        }

        @JavascriptInterface
        public final boolean isShoulei() {
            return true;
        }

        @JavascriptInterface
        public final int getBusinessType() {
            return R.styleable.AppCompatTheme_panelBackground;
        }

        @JavascriptInterface
        public final String getSessionId() {
            return XZBDeviceManager.getInstance().getUserInfo().sessionId;
        }

        @JavascriptInterface
        public final String getUserId() {
            return XZBDeviceManager.getInstance().getUserInfo().userId;
        }

        @JavascriptInterface
        public final int getLevel() {
            return 0;
        }

        @JavascriptInterface
        public final String getPeerId() {
            return XZBShouleiUtil.getInstance().getPeerId();
        }

        @JavascriptInterface
        public final String getUserName() {
            return XZBDeviceManager.getInstance().getUserInfo().userName;
        }

        @JavascriptInterface
        public final String getJumpKey() {
            return XZBDeviceManager.getInstance().getUserInfo().jumpkey;
        }

        @JavascriptInterface
        public final String getClientVersion() {
            return XZBShouleiUtil.getInstance().getClientVersion();
        }

        @JavascriptInterface
        public final String getClientType() {
            return a.d;
        }

        @JavascriptInterface
        public final void addRemoteDeviceByQrcodeScan(String str) {
        }

        @JavascriptInterface
        public final void addRemoteDeviceByActiveCode(String str) {
        }

        @JavascriptInterface
        public final void addRemoteDeviceByPCThunder(String str) {
        }

        @JavascriptInterface
        public final void goBack() {
            XZBWebviewActivity.this.d.post(new br(this));
        }

        @JavascriptInterface
        public final void goBackFromRemoteDownload() {
            XZBWebviewActivity.this.finish();
        }

        @JavascriptInterface
        public final void showToast(String str) {
            Toast.makeText(XZBWebviewActivity.this, str, 0).show();
        }

        @JavascriptInterface
        public final void copyToClipboard(String str) {
            copyToClipboard(null, str, XZBWebviewActivity.this);
        }

        @SuppressLint({"NewApi"})
        public final void copyToClipboard(String str, String str2, Context context) {
            if (VERSION.SDK_INT >= 11) {
                ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(str, str2));
            } else {
                ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(str2);
            }
        }
    }

    public XZBWebviewActivity() {
        this.c = "http://xzb.xunlei.com/wap/view/1.0/index_middle.html?referfrom=v_an_shoulei_download";
        this.d = new bj(this, Looper.getMainLooper());
        this.m = new bn(this);
        this.n = new bo(this);
    }

    static /* synthetic */ void g(XZBWebviewActivity xZBWebviewActivity) {
        if (xZBWebviewActivity.d.hasMessages(1)) {
            xZBWebviewActivity.d.removeMessages(1);
        }
        xZBWebviewActivity.d.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    static /* synthetic */ void h(XZBWebviewActivity xZBWebviewActivity) {
        if (xZBWebviewActivity.d.hasMessages(1)) {
            xZBWebviewActivity.d.removeMessages(1);
        }
        xZBWebviewActivity.d.sendEmptyMessage(1);
    }

    static {
        e = XZBWebviewActivity.class.getSimpleName();
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, XZBWebviewActivity.class);
        intent.putExtra("function", XZBDevice.DOWNLOAD_LIST_FAILED);
        intent.putExtra("ref", str);
        context.startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        if (this.k && !this.j) {
            LoginHelper.a();
            if (LoginHelper.c()) {
                String decode = Uri.decode(this.c);
                this.a.loadUrl(String.format("http://jump.xunlei.com/jump/?jump_key=%s&u1=%s", new Object[]{LoginHelper.a().n, Uri.encode(decode)}));
                this.k = false;
            }
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    private void a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str = "v_an_shoulei_download";
        }
        String toString = new StringBuilder("http://xzb.xunlei.com/wap/view/1.0/index_middle.html?referfrom=").append(str).toString();
        this.c = toString;
        USERINFO userInfo = XZBDeviceManager.getInstance().getUserInfo();
        CharSequence charSequence = a.d;
        if (!(userInfo == null || userInfo.jumpkey == null)) {
            charSequence = userInfo.jumpkey;
        }
        if (TextUtils.isEmpty(charSequence)) {
            str2 = toString;
        } else {
            str2 = String.format("http://jump.xunlei.com/jump/?jump_key=%s&u1=%s", new Object[]{charSequence, Uri.encode(toString)});
        }
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.addJavascriptInterface(new DownloadTaskListJsInterface(), ThunderSniffer.JSINTERFACE_NAMESPACE);
        this.a.loadUrl(str2);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        setContentView(2130969190);
        this.g = (ImageView) findViewById(2131756160);
        this.h = (ImageView) findViewById(2131757221);
        this.a = (WebView) findViewById(2131757657);
        this.a.setWebViewClient(this.m);
        this.a.setWebChromeClient(this.n);
        this.l = (WebpageProgressBar) findViewById(2131755563);
        this.f = (TextView) findViewById(R.id.title);
        this.f.setText("\u8fc5\u96f7\u4e0b\u8f7d\u5b9d\u5b98\u7f51");
        this.b = (ErrorView) findViewById(2131756546);
        this.b.setVisibility(XZBDevice.Wait);
        this.b.setActionButtonListener(new bk(this));
        String toString;
        switch (intent.getIntExtra("function", 0)) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                XZBDevice deviceById = XZBDeviceManager.getInstance().getDeviceById(intent.getStringExtra("deviceid"));
                if (deviceById != null) {
                    toString = new StringBuilder("http://wap.yuancheng.xunlei.com/download.html?pid=").append(deviceById.getPid()).append("&vendor=xzb&referfrom=xzbsdk_shoulei_android").toString();
                    this.a.getSettings().setJavaScriptEnabled(true);
                    this.a.addJavascriptInterface(new DownloadTaskListJsInterface(), ThunderSniffer.JSINTERFACE_NAMESPACE);
                    this.a.loadUrl(toString);
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.a.getSettings().setJavaScriptEnabled(true);
                this.a.addJavascriptInterface(new DownloadTaskListJsInterface(), ThunderSniffer.JSINTERFACE_NAMESPACE);
                this.a.loadUrl("http://wap.yuancheng.xunlei.com/xzb.html?referfrom=xzbsdk_shoulei_android");
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                toString = intent.getStringExtra("ref");
                this.i = toString;
                a(toString);
                break;
            default:
                a("v_an_shoulei_download");
                break;
        }
        this.g.setOnClickListener(new bl(this));
        this.h.setOnClickListener(new bm(this));
        this.h.setVisibility(XZBDevice.Wait);
    }

    protected void onDestroy() {
        this.a.destroy();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public static void a() {
    }

    public void onBackPressed() {
        if (this.a.canGoBack() && this.b.getVisibility() == 8) {
            this.a.goBack();
        } else {
            super.onBackPressed();
        }
    }

    static /* synthetic */ void a(XZBWebviewActivity xZBWebviewActivity) {
        if (xZBWebviewActivity.l != null && xZBWebviewActivity.l.getVisibility() == 0) {
            xZBWebviewActivity.l.b();
            xZBWebviewActivity.l.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
    }

    static /* synthetic */ void b(XZBWebviewActivity xZBWebviewActivity) {
        if (xZBWebviewActivity.l != null && xZBWebviewActivity.l.getVisibility() != 0) {
            xZBWebviewActivity.l.setVisibility(0);
            xZBWebviewActivity.l.a();
        }
    }

    static /* synthetic */ void i(XZBWebviewActivity xZBWebviewActivity) {
        if (xZBWebviewActivity.a.canGoBack()) {
            xZBWebviewActivity.h.setVisibility(0);
        } else {
            xZBWebviewActivity.h.setVisibility(XZBDevice.Wait);
        }
    }
}
