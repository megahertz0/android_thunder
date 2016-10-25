package com.tencent.open.yyb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ZoomButtonsController;
import anet.channel.util.HttpConstant;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.utils.SystemUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class AppbarActivity extends Activity implements OnClickListener {
    private static final int FLOATING_DIALOG_HEIGHT = 100;
    public static final String MYAPP_CACHE_PATH = "/tencent/tassistant";
    private static final String TAG = "openSDK_LOG.AppbarActivity";
    private static final String UA_PREFIX = "qqdownloader/";
    private static final String WEBVIEW_PATH = "/webview_cache";
    private static ArrayList<String> specialModel;
    private String appid;
    private AppbarJsBridge jsBridge;
    private final DownloadListener mDownloadListener;
    private MoreFloatingDialog mFloatingDialog;
    protected ProgressDialog mProgressDialog;
    private LinearLayout mRootView;
    private TitleBar mTitleBar;
    private QQToken mToken;
    private com.tencent.open.c.b mWebView;
    private ShareModel model;
    private Tencent tencent;
    private int titlebarTop;
    private String url;

    // compiled from: ProGuard
    class AnonymousClass_2 implements IUiListener {
        final /* synthetic */ QQToken a;

        AnonymousClass_2(QQToken qQToken) {
            this.a = qQToken;
        }

        public void onError(UiError uiError) {
            f.b(TAG, new StringBuilder("-->(AppbarActivity)shareToQQ onError").append(uiError.errorMessage).toString());
            AppbarActivity.this.jsBridge.responseShareFail(1);
        }

        public void onComplete(Object obj) {
            f.b(TAG, "-->(AppbarActivity)shareToQQ onComplete");
            AppbarActivity.this.jsBridge.responseShare(1);
            a.a(this.a.getAppId(), "400", "SDK.APPBAR.HOME.SHARE.QQ");
        }

        public void onCancel() {
            f.b(TAG, "-->(AppbarActivity)shareToQQ onCancel");
            AppbarActivity.this.jsBridge.responseShareFail(1);
        }
    }

    // compiled from: ProGuard
    class AnonymousClass_3 implements IUiListener {
        final /* synthetic */ QQToken a;

        AnonymousClass_3(QQToken qQToken) {
            this.a = qQToken;
        }

        public void onError(UiError uiError) {
            f.b(TAG, new StringBuilder("-->(AppbarActivity)shareToQzone onError").append(uiError.errorMessage).toString());
            AppbarActivity.this.jsBridge.responseShareFail(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }

        public void onComplete(Object obj) {
            f.b(TAG, "-->(AppbarActivity)shareToQzone onComplete");
            AppbarActivity.this.jsBridge.responseShare(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            a.a(this.a.getAppId(), "400", "SDK.APPBAR.HOME.SHARE.QZ");
        }

        public void onCancel() {
            f.b(TAG, "-->(AppbarActivity)shareToQzone onCancel");
            AppbarActivity.this.jsBridge.responseShareFail(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
    }

    // compiled from: ProGuard
    private static interface a {
        void a(byte[] bArr);
    }

    // compiled from: ProGuard
    private static class b extends AsyncTask<String, Void, byte[]> {
        private a a;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((byte[]) obj);
        }

        public b(a aVar) {
            this.a = aVar;
        }

        protected byte[] a(String... strArr) {
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                    httpURLConnection.setConnectTimeout(5000);
                    try {
                        httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                        try {
                            InputStream inputStream = httpURLConnection.getInputStream();
                            try {
                                if (httpURLConnection.getResponseCode() == 200) {
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    byte[] bArr = new byte[1024];
                                    while (true) {
                                        int read = inputStream.read(bArr);
                                        if (read != -1) {
                                            byteArrayOutputStream.write(bArr, 0, read);
                                        } else {
                                            byteArrayOutputStream.close();
                                            inputStream.close();
                                            return byteArrayOutputStream.toByteArray();
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return null;
                        }
                    } catch (ProtocolException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                } catch (IOException e22) {
                    e22.printStackTrace();
                    return null;
                }
            } catch (MalformedURLException e4) {
                e4.printStackTrace();
                return null;
            }
        }

        protected void a(byte[] bArr) {
            super.onPostExecute(bArr);
            this.a.a(bArr);
        }
    }

    // compiled from: ProGuard
    private class c extends WebChromeClient {
        private c() {
        }

        public void onReceivedTitle(WebView webView, String str) {
            AppbarActivity.this.mTitleBar.setTitle(str);
        }
    }

    // compiled from: ProGuard
    private class d extends WebViewClient {
        private d() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            AppbarActivity.this.setSupportZoom(true);
            AppbarActivity.this.jsBridge.ready();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            AppbarActivity.this.setSupportZoom(false);
            if (!str.startsWith(HttpConstant.HTTP)) {
                str.startsWith(com.alipay.sdk.cons.b.a);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            f.a(TAG, new StringBuilder("-->(AppbarDialog)shouldOverrideUrlLoading : url = ").append(str).toString());
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (str.startsWith(HttpConstant.HTTP) || str.startsWith(com.alipay.sdk.cons.b.a)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            if (!str.startsWith(AppbarJsBridge.JS_BRIDGE_SCHEME)) {
                return (str.equals("about:blank;") || str.equals("about:blank")) && VERSION.SDK_INT < 11;
            } else {
                AppbarActivity.this.jsBridge.invoke(str);
                return true;
            }
        }
    }

    public AppbarActivity() {
        this.mDownloadListener = new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                f.a(TAG, new StringBuilder("-->(AppbarActivity)onDownloadStart : url = ").append(str).toString());
                try {
                    AppbarActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Exception e) {
                    f.b(TAG, "-->(AppbarActivity)onDownloadStart : activity aciton_view not found.");
                }
                QQToken access$500 = AppbarActivity.this.getToken();
                if (access$500 != null) {
                    a.a(access$500.getAppId(), "200", "SDK.APPBAR.HOME ACTION");
                }
            }
        };
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.appid = getIntent().getStringExtra(SocialConstants.PARAM_APP_ID);
        this.url = getIntent().getStringExtra(SocialConstants.PARAM_URL);
        f.a(TAG, new StringBuilder("-->(AppbarActivity)onCreate : appid = ").append(this.appid).append(" url = ").append(this.url).toString());
        this.mWebView = new com.tencent.open.c.b(this);
        this.jsBridge = new AppbarJsBridge(this, this.mWebView);
        createViews();
        initViews();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
        MoreFloatingDialog floatingDialg = getFloatingDialg();
        if (floatingDialg != null && floatingDialg.isShowing()) {
            floatingDialg.dismiss();
        }
    }

    public void onBackPressed() {
        MoreFloatingDialog floatingDialg = getFloatingDialg();
        if (floatingDialg == null || !floatingDialg.isShowing()) {
            super.onBackPressed();
        } else {
            floatingDialg.dismiss();
        }
    }

    private void createViews() {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mWebView.setLayoutParams(layoutParams);
        this.mRootView = new LinearLayout(this);
        layoutParams.gravity = 17;
        this.mRootView.setLayoutParams(layoutParams);
        this.mRootView.setOrientation(1);
        this.mTitleBar = new TitleBar(this);
        this.mTitleBar.getBackBtn().setOnClickListener(this);
        this.mTitleBar.getSharBtn().setOnClickListener(this);
        this.mRootView.addView(this.mTitleBar);
        this.mRootView.addView(this.mWebView);
        setContentView(this.mRootView);
    }

    private void initViews() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setUserAgentString(settings.getUserAgentString() + "/qqdownloader/" + this.jsBridge.getVersion() + "/sdk");
        settings.setJavaScriptEnabled(true);
        Class cls = settings.getClass();
        try {
            Method method = cls.getMethod("setPluginsEnabled", new Class[]{Boolean.TYPE});
            if (method != null) {
                method.invoke(settings, new Object[]{Boolean.valueOf(true)});
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            method = cls.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
            if (method != null) {
                method.invoke(settings, new Object[]{Boolean.valueOf(true)});
            }
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
        } catch (IllegalArgumentException e4) {
        } catch (IllegalAccessException e5) {
        } catch (InvocationTargetException e6) {
        }
        settings.setAppCachePath(getWebViewCacheDir());
        settings.setDatabasePath(getWebViewCacheDir());
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        if (supportWebViewFullScreen()) {
            settings.setUseWideViewPort(true);
            if (VERSION.SDK_INT >= 7) {
                try {
                    cls.getMethod("setLoadWithOverviewMode", new Class[]{Boolean.TYPE}).invoke(settings, new Object[]{Boolean.valueOf(true)});
                } catch (Exception e7) {
                }
            }
            if (SystemUtils.isSupportMultiTouch()) {
                if (SystemUtils.getAndroidSDKVersion() < 11) {
                    try {
                        Field declaredField = WebView.class.getDeclaredField("mZoomButtonsController");
                        declaredField.setAccessible(true);
                        ZoomButtonsController zoomButtonsController = new ZoomButtonsController(this.mWebView);
                        zoomButtonsController.getZoomControls().setVisibility(XZBDevice.Wait);
                        declaredField.set(this.mWebView, zoomButtonsController);
                    } catch (Exception e8) {
                    }
                } else {
                    try {
                        this.mWebView.getSettings().getClass().getMethod("setDisplayZoomControls", new Class[]{Boolean.TYPE}).invoke(this.mWebView.getSettings(), new Object[]{Boolean.valueOf(false)});
                    } catch (Exception e9) {
                    }
                }
            }
        }
        this.mWebView.setWebViewClient(new d());
        this.mWebView.setWebChromeClient(new c());
        this.mWebView.setDownloadListener(this.mDownloadListener);
        this.mWebView.loadUrl(this.url);
    }

    static {
        ArrayList arrayList = new ArrayList();
        specialModel = arrayList;
        arrayList.add("MT870");
        specialModel.add("XT910");
        specialModel.add("XT928");
        specialModel.add("MT917");
        specialModel.add("Lenovo A60");
    }

    private boolean supportWebViewFullScreen() {
        String str = Build.MODEL;
        return (str.contains("vivo") || specialModel.contains(str)) ? false : true;
    }

    private Tencent getTencent() {
        if (this.tencent == null) {
            this.tencent = Tencent.createInstance(this.appid, this);
        }
        return this.tencent;
    }

    private QQToken getToken() {
        if (this.mToken == null) {
            this.mToken = getTencent().getQQToken();
        }
        return this.mToken;
    }

    private String getWebViewCacheDir() {
        return getCommonPath(WEBVIEW_PATH);
    }

    private String getCommonPath(String str) {
        String commonRootDir = getCommonRootDir();
        if (!TextUtils.isEmpty(str)) {
            commonRootDir = commonRootDir + str;
        }
        return getPath(commonRootDir, false);
    }

    private MoreFloatingDialog getFloatingDialg() {
        if (this.mFloatingDialog == null) {
            this.mFloatingDialog = new MoreFloatingDialog(this);
            this.mFloatingDialog.setCanceledOnTouchOutside(true);
            this.mFloatingDialog.getQQItem().setOnClickListener(this);
            this.mFloatingDialog.getQzoneItem().setOnClickListener(this);
        }
        return this.mFloatingDialog;
    }

    private String getCommonRootDir() {
        String str;
        if (isSDCardExistAndCanWrite()) {
            str = Environment.getExternalStorageDirectory().getPath() + MYAPP_CACHE_PATH;
        } else {
            File filesDir = getFilesDir();
            if (filesDir == null) {
                return com.umeng.a.d;
            }
            str = filesDir.getAbsolutePath() + MYAPP_CACHE_PATH;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    private boolean isSDCardExistAndCanWrite() {
        try {
            return "mounted".equals(Environment.getExternalStorageState()) && Environment.getExternalStorageDirectory().canWrite();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getPath(String str, boolean z) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
            if (z) {
                try {
                    new File(str + File.separator + ".nomedia").createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file.getAbsolutePath();
    }

    private void setSupportZoom(boolean z) {
        if (this.mWebView != null) {
            this.mWebView.getSettings().setSupportZoom(z);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mWebView != null) {
            this.mWebView.removeAllViews();
            this.mWebView.setVisibility(XZBDevice.Wait);
            this.mWebView.stopLoading();
            this.mWebView.clearHistory();
            this.mWebView.destroy();
        }
    }

    public void showFloatingDialog() {
        MoreFloatingDialog floatingDialg = getFloatingDialg();
        floatingDialg.show();
        Window window = floatingDialg.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 48;
        attributes.y = getTitbarTop() + this.mTitleBar.getHeight();
        Display defaultDisplay = floatingDialg.getWindow().getWindowManager().getDefaultDisplay();
        attributes.height = floatingDialg.dip2px(100.0f);
        attributes.width = ((int) (((double) defaultDisplay.getWidth()) * 0.95d)) / 2;
        attributes.x = attributes.width / 2;
        f.b(TAG, new StringBuilder("-->(AppbarDialog)showFloatingDialog : params.x = ").append(attributes.x).toString());
        window.setAttributes(attributes);
    }

    private int getTitbarTop() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.titlebarTop = displayMetrics.heightPixels - rect.height();
        return this.titlebarTop;
    }

    public void onClick(View view) {
        MoreFloatingDialog floatingDialg = getFloatingDialg();
        if (view == this.mTitleBar.getSharBtn()) {
            this.jsBridge.clickCallback();
        } else if (view == floatingDialg.getQQItem()) {
            shareToQQ();
        } else if (view == floatingDialg.getQzoneItem()) {
            shareToQzone();
        } else if (view == floatingDialg.getWXItem()) {
            shareToWX();
        } else if (view == floatingDialg.getTimelineItem()) {
            shareToTimeline();
        } else if (view == this.mTitleBar.getBackBtn()) {
            finish();
        }
    }

    public void login() {
        f.b(TAG, "-->login : activity~~~");
        getTencent().login((Activity) this, "all", new IUiListener() {
            public void onError(UiError uiError) {
                f.b(TAG, new StringBuilder("-->(AppbarJsBridge)openLoginActivity onError").append(uiError.errorMessage).toString());
                AppbarActivity.this.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, AppbarJsBridge.AUTHORIZE_FAIL);
            }

            public void onComplete(Object obj) {
                f.b(TAG, "-->(AppbarJsBridge)openLoginActivity onComplete");
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.optInt(KEYS.RET, -1) != 0) {
                    AppbarActivity.this.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, AppbarJsBridge.AUTHORIZE_FAIL);
                    return;
                }
                try {
                    String string = jSONObject.getString(SocialConstants.PARAM_OPEN_ID);
                    String string2 = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                    jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                    a.a(AppbarActivity.this, AppbarActivity.this.mWebView.getUrl(), string, string2, AppbarActivity.this.getToken().getAppId());
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("logintype", "SSO");
                        jSONObject2.put(SocialConstants.PARAM_OPEN_ID, string);
                        jSONObject2.put("accesstoken", string2);
                        AppbarActivity.this.jsBridge.response(AppbarJsBridge.CALLBACK_LOGIN, 0, null, jSONObject2.toString());
                        Intent intent = new Intent();
                        intent.putExtra(Constants.LOGIN_INFO, jSONObject.toString());
                        AppbarActivity.this.setResult(-1, intent);
                    } catch (JSONException e) {
                        AppbarActivity.this.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, AppbarJsBridge.AUTHORIZE_FAIL);
                        f.b(TAG, "-->(AppbarJsBridge)openLoginActivity onComplete: put keys callback failed.");
                    }
                } catch (JSONException e2) {
                    AppbarActivity.this.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, AppbarJsBridge.AUTHORIZE_FAIL);
                    f.b(TAG, "-->(AppbarJsBridge)openLoginActivity onComplete: get keys failed.");
                }
            }

            public void onCancel() {
                f.b(TAG, "-->(AppbarJsBridge)openLoginActivity onCancel");
                AppbarActivity.this.jsBridge.responseFail(AppbarJsBridge.CALLBACK_LOGIN, 0, null, Tabs.TAB_CREATE_REACH_MAX_COUNT);
            }
        });
    }

    public void shareToQQ() {
        QQToken token = getToken();
        if (token != null) {
            QQShare qQShare = new QQShare(this, token);
            Bundle bundle = new Bundle();
            bundle.putString(WebBrowserActivity.EXTRA_TITLE, this.model.a);
            bundle.putString(SocialConstants.PARAM_TARGET_URL, this.model.d);
            bundle.putString(SocialConstants.PARAM_SUMMARY, this.model.b);
            bundle.putString(SocialConstants.PARAM_IMAGE_URL, this.model.c);
            f.a(TAG, new StringBuilder("-->(AppbarActivity)shareToQQ : model.mTitle = ").append(this.model.a).toString());
            f.a(TAG, new StringBuilder("-->(AppbarActivity)shareToQQ : model.mTargetUrl = ").append(this.model.d).toString());
            f.a(TAG, new StringBuilder("-->(AppbarActivity)shareToQQ : model.mDescription = ").append(this.model.b).toString());
            f.a(TAG, new StringBuilder("-->(AppbarActivity)shareToQQ : model.mIconUrl = ").append(this.model.c).toString());
            qQShare.shareToQQ(this, bundle, new AnonymousClass_2(token));
            a.a(token.getAppId(), "200", "SDK.APPBAR.HOME.SHARE.QQ");
        }
    }

    public void shareToQzone() {
        QQToken token = getToken();
        if (token != null) {
            QzoneShare qzoneShare = new QzoneShare(this, token);
            Bundle bundle = new Bundle();
            bundle.putInt(GameAppOperation.QQFAV_DATALINE_REQTYPE, 1);
            bundle.putString(WebBrowserActivity.EXTRA_TITLE, this.model.a);
            bundle.putString(SocialConstants.PARAM_SUMMARY, this.model.b);
            bundle.putString(SocialConstants.PARAM_TARGET_URL, this.model.d);
            ArrayList arrayList = new ArrayList();
            f.a(TAG, new StringBuilder("-->shareToQzone : mIconUrl = ").append(this.model.c).toString());
            arrayList.add(this.model.c);
            bundle.putStringArrayList(SocialConstants.PARAM_IMAGE_URL, arrayList);
            qzoneShare.shareToQzone(this, bundle, new AnonymousClass_3(token));
            a.a(token.getAppId(), "200", "SDK.APPBAR.HOME.SHARE.QZ");
        }
    }

    public void shareToWX() {
        shareToWX(false);
    }

    public void shareToTimeline() {
        shareToWX(true);
    }

    private void shareToWX(boolean z) {
        f.a(TAG, "-->shareToWX : wx_appid = wx8e8dc60535c9cd93");
        if (!TextUtils.isEmpty(this.model.c)) {
            showProgressDialog(this, com.umeng.a.d, com.umeng.a.d);
            new b(new a() {
                public void a(byte[] bArr) {
                    AppbarActivity.this.mProgressDialog.dismiss();
                }
            }).execute(new String[]{this.model.c});
        }
    }

    public void setShareVisibility(boolean z) {
        this.mTitleBar.getSharBtn().setVisibility(z ? 0 : XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public void setAppbarTitle(String str) {
        this.mTitleBar.setTitle(str);
    }

    public void setShareModel(ShareModel shareModel) {
        this.model = shareModel;
    }

    private String buildTransaction(String str) {
        return str == null ? String.valueOf(System.currentTimeMillis()) : str + System.currentTimeMillis();
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
}
