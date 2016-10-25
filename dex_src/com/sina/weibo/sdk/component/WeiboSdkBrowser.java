package com.sina.weibo.sdk.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import anet.channel.util.HttpConstant;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.ShareRequestParam.UploadPicResult;
import com.sina.weibo.sdk.component.view.LoadingBar;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.common.Constants;
import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class WeiboSdkBrowser extends Activity implements BrowserRequestCallBack {
    public static final String BROWSER_CLOSE_SCHEME = "sinaweibo://browser/close";
    public static final String BROWSER_WIDGET_SCHEME = "sinaweibo://browser/datatransfer";
    private static final String CANCEL_EN = "Close";
    private static final String CANCEL_ZH_CN = "\u5173\u95ed";
    private static final String CANCEL_ZH_TW = "\u5173\u95ed";
    private static final String CHANNEL_DATA_ERROR_EN = "channel_data_error";
    private static final String CHANNEL_DATA_ERROR_ZH_CN = "\u91cd\u65b0\u52a0\u8f7d";
    private static final String CHANNEL_DATA_ERROR_ZH_TW = "\u91cd\u65b0\u8f09\u5165";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_EN = "A network error occurs, please tap the button to reload";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_ZH_CN = "\u7f51\u7edc\u51fa\u9519\u5566\uff0c\u8bf7\u70b9\u51fb\u6309\u94ae\u91cd\u65b0\u52a0\u8f7d";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_ZH_TW = "\u7db2\u8def\u51fa\u932f\u5566\uff0c\u8acb\u9ede\u64ca\u6309\u9215\u91cd\u65b0\u8f09\u5165";
    private static final String LOADINFO_EN = "Loading....";
    private static final String LOADINFO_ZH_CN = "\u52a0\u8f7d\u4e2d....";
    private static final String LOADINFO_ZH_TW = "\u8f09\u5165\u4e2d....";
    private static final String TAG;
    private static final String WEIBOBROWSER_NO_TITLE_EN = "No Title";
    private static final String WEIBOBROWSER_NO_TITLE_ZH_CN = "\u65e0\u6807\u9898";
    private static final String WEIBOBROWSER_NO_TITLE_ZH_TW = "\u7121\u6a19\u984c";
    private boolean isErrorPage;
    private Boolean isFromGame;
    private boolean isLoading;
    private String mHtmlTitle;
    private TextView mLeftBtn;
    private Button mLoadErrorRetryBtn;
    private LinearLayout mLoadErrorView;
    private LoadingBar mLoadingBar;
    private BrowserRequestParamBase mRequestParam;
    private String mSpecifyTitle;
    private TextView mTitleText;
    private String mUrl;
    private WebView mWebView;
    private WeiboWebViewClient mWeiboWebViewClient;

    class AnonymousClass_1 implements RequestListener {
        private final /* synthetic */ ShareRequestParam val$req;

        AnonymousClass_1(ShareRequestParam shareRequestParam) {
            this.val$req = shareRequestParam;
        }

        public void onWeiboException(WeiboException weiboException) {
            LogUtil.d(TAG, new StringBuilder("post onWeiboException ").append(weiboException.getMessage()).toString());
            this.val$req.sendSdkErrorResponse(WeiboSdkBrowser.this, weiboException.getMessage());
            WeiboSdkBrowser.this.finish();
        }

        public void onComplete(String str) {
            LogUtil.d(TAG, new StringBuilder("post onComplete : ").append(str).toString());
            UploadPicResult parse = UploadPicResult.parse(str);
            if (parse == null || parse.getCode() != 1 || TextUtils.isEmpty(parse.getPicId())) {
                this.val$req.sendSdkErrorResponse(WeiboSdkBrowser.this, "upload pic faild");
                WeiboSdkBrowser.this.finish();
                return;
            }
            WeiboSdkBrowser.this.openUrl(this.val$req.buildUrl(parse.getPicId()));
        }
    }

    private class WeiboChromeClient extends WebChromeClient {
        private WeiboChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            WeiboSdkBrowser.this.mLoadingBar.drawProgress(i);
            if (i == 100) {
                WeiboSdkBrowser.this.isLoading = false;
                WeiboSdkBrowser.this.refreshAllViews();
            } else if (!WeiboSdkBrowser.this.isLoading) {
                WeiboSdkBrowser.this.isLoading = true;
                WeiboSdkBrowser.this.refreshAllViews();
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            if (!WeiboSdkBrowser.this.isWeiboCustomScheme(WeiboSdkBrowser.this.mUrl) && !WeiboSdkBrowser.this.isFromGame.booleanValue()) {
                WeiboSdkBrowser.this.mHtmlTitle = str;
                WeiboSdkBrowser.this.updateTitleName();
            }
        }
    }

    public WeiboSdkBrowser() {
        this.isFromGame = Boolean.valueOf(false);
    }

    static {
        TAG = WeiboSdkBrowser.class.getName();
    }

    public static void startAuth(Context context, String str, AuthInfo authInfo, WeiboAuthListener weiboAuthListener) {
        AuthRequestParam authRequestParam = new AuthRequestParam(context);
        authRequestParam.setLauncher(BrowserLauncher.AUTH);
        authRequestParam.setUrl(str);
        authRequestParam.setAuthInfo(authInfo);
        authRequestParam.setAuthListener(weiboAuthListener);
        Intent intent = new Intent(context, WeiboSdkBrowser.class);
        intent.putExtras(authRequestParam.createRequestParamBundle());
        context.startActivity(intent);
    }

    public static void startShared(Context context, String str, AuthInfo authInfo, WeiboAuthListener weiboAuthListener) {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (initDataFromIntent(getIntent())) {
            setContentView();
            initWebView();
            if (isWeiboShareRequestParam(this.mRequestParam)) {
                startShare();
                return;
            } else {
                openUrl(this.mUrl);
                return;
            }
        }
        finish();
    }

    private boolean initDataFromIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        this.mRequestParam = createBrowserRequestParam(extras);
        if (this.mRequestParam != null) {
            this.mUrl = this.mRequestParam.getUrl();
            this.mSpecifyTitle = this.mRequestParam.getSpecifyTitle();
        } else {
            String string = extras.getString("key_url");
            String string2 = extras.getString("key_specify_title");
            if (!TextUtils.isEmpty(string) && string.startsWith(HttpConstant.HTTP)) {
                this.mUrl = string;
                this.mSpecifyTitle = string2;
            }
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            return false;
        }
        LogUtil.d(TAG, new StringBuilder("LOAD URL : ").append(this.mUrl).toString());
        return true;
    }

    private void openUrl(String str) {
        this.mWebView.loadUrl(str);
    }

    private void startShare() {
        LogUtil.d(TAG, "Enter startShare()............");
        ShareRequestParam shareRequestParam = (ShareRequestParam) this.mRequestParam;
        if (shareRequestParam.hasImage()) {
            LogUtil.d(TAG, "loadUrl hasImage............");
            new AsyncWeiboRunner(this).requestAsync(ShareRequestParam.UPLOAD_PIC_URL, shareRequestParam.buildUploadPicParam(new WeiboParameters(shareRequestParam.getAppKey())), Constants.HTTP_POST, new AnonymousClass_1(shareRequestParam));
            return;
        }
        openUrl(this.mUrl);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        if (isWeiboShareRequestParam(this.mRequestParam)) {
            this.mWebView.getSettings().setUserAgentString(Utility.generateUA(this));
        }
        this.mWebView.getSettings().setSavePassword(false);
        this.mWebView.setWebViewClient(this.mWeiboWebViewClient);
        this.mWebView.setWebChromeClient(new WeiboChromeClient());
        this.mWebView.requestFocus();
        this.mWebView.setScrollBarStyle(0);
        if (VERSION.SDK_INT >= 11) {
            this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        } else {
            removeJavascriptInterface(this.mWebView);
        }
    }

    private void setTopNavTitle() {
        this.mTitleText.setText(this.mSpecifyTitle);
        this.mLeftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (WeiboSdkBrowser.this.mRequestParam != null) {
                    WeiboSdkBrowser.this.mRequestParam.execRequest(WeiboSdkBrowser.this, XZBDevice.DOWNLOAD_LIST_FAILED);
                }
                WeiboSdkBrowser.this.finish();
            }
        });
    }

    private void updateTitleName() {
        CharSequence charSequence = a.d;
        if (!TextUtils.isEmpty(this.mHtmlTitle)) {
            charSequence = this.mHtmlTitle;
        } else if (!TextUtils.isEmpty(this.mSpecifyTitle)) {
            charSequence = this.mSpecifyTitle;
        }
        this.mTitleText.setText(charSequence);
    }

    private void setContentView() {
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(-1);
        View linearLayout = new LinearLayout(this);
        linearLayout.setId(1);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        View initTitleBar = initTitleBar();
        View textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, XZBDevice.DOWNLOAD_LIST_RECYCLE)));
        textView.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "weibosdk_common_shadow_top.9.png"));
        this.mLoadingBar = new LoadingBar(this);
        this.mLoadingBar.setBackgroundColor(0);
        this.mLoadingBar.drawProgress(0);
        this.mLoadingBar.setLayoutParams(new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, XZBDevice.DOWNLOAD_LIST_FAILED)));
        linearLayout.addView(initTitleBar);
        linearLayout.addView(textView);
        linearLayout.addView(this.mLoadingBar);
        this.mWebView = new WebView(this);
        this.mWebView.setBackgroundColor(-1);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(XZBDevice.DOWNLOAD_LIST_FAILED, 1);
        this.mWebView.setLayoutParams(layoutParams);
        this.mLoadErrorView = new LinearLayout(this);
        this.mLoadErrorView.setVisibility(XZBDevice.Wait);
        this.mLoadErrorView.setOrientation(1);
        this.mLoadErrorView.setGravity(R.styleable.Toolbar_maxButtonHeight);
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(XZBDevice.DOWNLOAD_LIST_FAILED, 1);
        this.mLoadErrorView.setLayoutParams(layoutParams);
        initTitleBar = new ImageView(this);
        initTitleBar.setImageDrawable(ResourceManager.getDrawable(this, "weibosdk_empty_failed.png"));
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        int dp2px = ResourceManager.dp2px(this, XZBDevice.Wait);
        layoutParams2.bottomMargin = dp2px;
        layoutParams2.rightMargin = dp2px;
        layoutParams2.topMargin = dp2px;
        layoutParams2.leftMargin = dp2px;
        initTitleBar.setLayoutParams(layoutParams2);
        this.mLoadErrorView.addView(initTitleBar);
        initTitleBar = new TextView(this);
        initTitleBar.setGravity(1);
        initTitleBar.setTextColor(-4342339);
        initTitleBar.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 14.0f);
        initTitleBar.setText(ResourceManager.getString(this, EMPTY_PROMPT_BAD_NETWORK_UI_EN, EMPTY_PROMPT_BAD_NETWORK_UI_ZH_CN, EMPTY_PROMPT_BAD_NETWORK_UI_ZH_TW));
        initTitleBar.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.mLoadErrorView.addView(initTitleBar);
        this.mLoadErrorRetryBtn = new Button(this);
        this.mLoadErrorRetryBtn.setGravity(R.styleable.Toolbar_maxButtonHeight);
        this.mLoadErrorRetryBtn.setTextColor(-8882056);
        this.mLoadErrorRetryBtn.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 16.0f);
        this.mLoadErrorRetryBtn.setText(ResourceManager.getString(this, CHANNEL_DATA_ERROR_EN, CHANNEL_DATA_ERROR_ZH_CN, CHANNEL_DATA_ERROR_ZH_TW));
        this.mLoadErrorRetryBtn.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "weibosdk_common_button_alpha.9.png", "weibosdk_common_button_alpha_highlighted.9.png"));
        layoutParams = new LinearLayout.LayoutParams(ResourceManager.dp2px(this, 142), ResourceManager.dp2px(this, R.styleable.AppCompatTheme_dropdownListPreferredItemHeight));
        layoutParams.topMargin = ResourceManager.dp2px(this, XZBDevice.Stop);
        this.mLoadErrorRetryBtn.setLayoutParams(layoutParams);
        this.mLoadErrorRetryBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WeiboSdkBrowser.this.openUrl(WeiboSdkBrowser.this.mUrl);
                WeiboSdkBrowser.this.isErrorPage = false;
            }
        });
        this.mLoadErrorView.addView(this.mLoadErrorRetryBtn);
        relativeLayout.addView(linearLayout);
        relativeLayout.addView(this.mWebView);
        relativeLayout.addView(this.mLoadErrorView);
        setContentView(relativeLayout);
        setTopNavTitle();
    }

    private View initTitleBar() {
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LayoutParams(-1, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_actionDropDownStyle)));
        relativeLayout.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "weibosdk_navigationbar_background.9.png"));
        this.mLeftBtn = new TextView(this);
        this.mLeftBtn.setClickable(true);
        this.mLeftBtn.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 17.0f);
        this.mLeftBtn.setTextColor(ResourceManager.createColorStateList(-32256, 1728020992));
        this.mLeftBtn.setText(ResourceManager.getString(this, CANCEL_EN, CANCEL_ZH_TW, CANCEL_ZH_TW));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        layoutParams.addRule(XZBDevice.Delete);
        layoutParams.leftMargin = ResourceManager.dp2px(this, XZBDevice.Stop);
        layoutParams.rightMargin = ResourceManager.dp2px(this, XZBDevice.Stop);
        this.mLeftBtn.setLayoutParams(layoutParams);
        relativeLayout.addView(this.mLeftBtn);
        this.mTitleText = new TextView(this);
        this.mTitleText.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 18.0f);
        this.mTitleText.setTextColor(-11382190);
        this.mTitleText.setEllipsize(TruncateAt.END);
        this.mTitleText.setSingleLine(true);
        this.mTitleText.setGravity(R.styleable.Toolbar_maxButtonHeight);
        this.mTitleText.setMaxWidth(ResourceManager.dp2px(this, 160));
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(XZBDevice.Upload);
        this.mTitleText.setLayoutParams(layoutParams);
        relativeLayout.addView(this.mTitleText);
        return relativeLayout;
    }

    protected void refreshAllViews() {
        if (this.isLoading) {
            setViewLoading();
        } else {
            setViewNormal();
        }
    }

    private void setViewNormal() {
        updateTitleName();
        this.mLoadingBar.setVisibility(XZBDevice.Wait);
    }

    private void setViewLoading() {
        this.mTitleText.setText(ResourceManager.getString(this, LOADINFO_EN, LOADINFO_ZH_CN, LOADINFO_ZH_TW));
        this.mLoadingBar.setVisibility(0);
    }

    private void handleReceivedError(WebView webView, int i, String str, String str2) {
        if (!str2.startsWith("sinaweibo")) {
            this.isErrorPage = true;
            promptError();
        }
    }

    private void promptError() {
        this.mLoadErrorView.setVisibility(0);
        this.mWebView.setVisibility(XZBDevice.Wait);
    }

    private void hiddenErrorPrompt() {
        this.mLoadErrorView.setVisibility(XZBDevice.Wait);
        this.mWebView.setVisibility(0);
    }

    private boolean isWeiboCustomScheme(String str) {
        return !TextUtils.isEmpty(str) && "sinaweibo".equalsIgnoreCase(Uri.parse(str).getAuthority());
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        NetworkHelper.clearCookies(this);
        super.onDestroy();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        if (this.mRequestParam != null) {
            this.mRequestParam.execRequest(this, XZBDevice.DOWNLOAD_LIST_FAILED);
        }
        finish();
        return true;
    }

    private BrowserRequestParamBase createBrowserRequestParam(Bundle bundle) {
        this.isFromGame = Boolean.valueOf(false);
        BrowserLauncher browserLauncher = (BrowserLauncher) bundle.getSerializable(BrowserRequestParamBase.EXTRA_KEY_LAUNCHER);
        BrowserRequestParamBase authRequestParam;
        if (browserLauncher == BrowserLauncher.AUTH) {
            authRequestParam = new AuthRequestParam(this);
            authRequestParam.setupRequestParam(bundle);
            installAuthWeiboWebViewClient(authRequestParam);
            return authRequestParam;
        } else if (browserLauncher == BrowserLauncher.SHARE) {
            authRequestParam = new ShareRequestParam(this);
            authRequestParam.setupRequestParam(bundle);
            installShareWeiboWebViewClient(authRequestParam);
            return authRequestParam;
        } else if (browserLauncher == BrowserLauncher.WIDGET) {
            authRequestParam = new WidgetRequestParam(this);
            authRequestParam.setupRequestParam(bundle);
            installWidgetWeiboWebViewClient(authRequestParam);
            return authRequestParam;
        } else if (browserLauncher != BrowserLauncher.GAME) {
            return null;
        } else {
            this.isFromGame = Boolean.valueOf(true);
            authRequestParam = new GameRequestParam(this);
            authRequestParam.setupRequestParam(bundle);
            installWeiboWebGameClient(authRequestParam);
            return authRequestParam;
        }
    }

    private boolean isWeiboShareRequestParam(BrowserRequestParamBase browserRequestParamBase) {
        return browserRequestParamBase != null && browserRequestParamBase.getLauncher() == BrowserLauncher.SHARE;
    }

    private void installAuthWeiboWebViewClient(AuthRequestParam authRequestParam) {
        this.mWeiboWebViewClient = new AuthWeiboWebViewClient(this, authRequestParam);
        this.mWeiboWebViewClient.setBrowserRequestCallBack(this);
    }

    private void installShareWeiboWebViewClient(ShareRequestParam shareRequestParam) {
        WeiboWebViewClient shareWeiboWebViewClient = new ShareWeiboWebViewClient(this, shareRequestParam);
        shareWeiboWebViewClient.setBrowserRequestCallBack(this);
        this.mWeiboWebViewClient = shareWeiboWebViewClient;
    }

    private void installWidgetWeiboWebViewClient(WidgetRequestParam widgetRequestParam) {
        WeiboWebViewClient widgetWeiboWebViewClient = new WidgetWeiboWebViewClient(this, widgetRequestParam);
        widgetWeiboWebViewClient.setBrowserRequestCallBack(this);
        this.mWeiboWebViewClient = widgetWeiboWebViewClient;
    }

    private void installWeiboWebGameClient(GameRequestParam gameRequestParam) {
        WeiboWebViewClient weiboGameClient = new WeiboGameClient(this, gameRequestParam);
        weiboGameClient.setBrowserRequestCallBack(this);
        this.mWeiboWebViewClient = weiboGameClient;
    }

    public void onPageStartedCallBack(WebView webView, String str, Bitmap bitmap) {
        LogUtil.d(TAG, new StringBuilder("onPageStarted URL: ").append(str).toString());
        this.mUrl = str;
        if (!isWeiboCustomScheme(str)) {
            this.mHtmlTitle = a.d;
        }
    }

    public boolean shouldOverrideUrlLoadingCallBack(WebView webView, String str) {
        LogUtil.i(TAG, new StringBuilder("shouldOverrideUrlLoading URL: ").append(str).toString());
        return false;
    }

    public void onPageFinishedCallBack(WebView webView, String str) {
        LogUtil.d(TAG, new StringBuilder("onPageFinished URL: ").append(str).toString());
        if (this.isErrorPage) {
            promptError();
            return;
        }
        this.isErrorPage = false;
        hiddenErrorPrompt();
    }

    public void onReceivedErrorCallBack(WebView webView, int i, String str, String str2) {
        LogUtil.d(TAG, new StringBuilder("onReceivedError: errorCode = ").append(i).append(", description = ").append(str).append(", failingUrl = ").append(str2).toString());
        handleReceivedError(webView, i, str, str2);
    }

    public void onReceivedSslErrorCallBack(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        LogUtil.d(TAG, "onReceivedSslErrorCallBack.........");
    }

    public static void closeBrowser(Activity activity, String str, String str2) {
        WeiboCallbackManager instance = WeiboCallbackManager.getInstance(activity.getApplicationContext());
        if (!TextUtils.isEmpty(str)) {
            instance.removeWeiboAuthListener(str);
            activity.finish();
        }
        if (!TextUtils.isEmpty(str2)) {
            instance.removeWidgetRequestCallback(str2);
            activity.finish();
        }
    }

    public void removeJavascriptInterface(WebView webView) {
        if (VERSION.SDK_INT < 11) {
            try {
                webView.getClass().getDeclaredMethod("removeJavascriptInterface", new Class[0]).invoke("searchBoxJavaBridge_", new Object[0]);
            } catch (Exception e) {
                LogUtil.e(TAG, e.toString());
            }
        }
    }
}
