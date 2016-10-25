package com.xunlei.tdlive;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import anet.channel.util.HttpConstant;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetSearchHotKeywordsRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveRequestSimple;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Semaphore;

@SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
public class WebBrowserActivity extends BaseActivity implements TextWatcher, OnClickListener, OnFocusChangeListener, OnEditorActionListener {
    public static final String EXTRA_SHOW_BTN = "right_btn";
    public static final String EXTRA_SHOW_SEARCH_BAR = "EXTRA_SHOW_SEARCH_BAR";
    public static final String EXTRA_SHOW_TITLE_BAR = "EXTRA_SHOW_TITLE_BAR";
    public static final String EXTRA_TEST_MODE = "EXTRA_TEST_MODE";
    public static final String EXTRA_TITLE = "title";
    private static final String TAG = "WebBrowserActivity";
    public a buyCoins;
    public a close;
    public a getAPPInfo;
    public a getSessionId;
    public a getUserInfo;
    public a getVersionCode;
    public a getVersionName;
    public a hideKeyboard;
    public a hideLoading;
    public a inroom;
    private File mCompressFile;
    private View mEditClearView;
    private EditText mEditView;
    private View mErrorView;
    private int mHotKeyIndex;
    private ArrayList<String> mHotKeys;
    private File mPhotoFile;
    private ProgressBar mProgressBar;
    private ValueCallback<Uri> mValueCallback;
    private c mWebChromeClient;
    private WebView mWebView;
    public a openURL;
    public a recharge;
    public a setMoreAction;
    public a share;
    public a showLoading;
    public a showLogin;
    public a showToast;
    public a showUserCenter;
    public a traceEvent;
    public a uploadFile;

    abstract class a {
        public abstract String a(String str, String str2);

        a() {
        }

        public boolean a() {
            return false;
        }
    }

    class b implements Runnable {
        Semaphore a;
        String b;
        String c;
        String d;
        String e;

        b() {
        }

        public String a(String str, String str2, String str3) {
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.a = new Semaphore(0);
            WebBrowserActivity.this.runOnUiThread(this);
            try {
                this.a.acquire();
            } catch (Exception e) {
            }
            return this.b;
        }

        public void run() {
            try {
                this.b = ((a) WebBrowserActivity.class.getDeclaredField(this.c).get(WebBrowserActivity.this)).a(this.d, this.e);
            } catch (Throwable e) {
                XLog.e(TAG, "callMessage error", e);
            }
            this.a.release();
        }
    }

    class c extends WebChromeClient implements ValueCallback<Uri>, JsonCallBack {
        private String b;
        private String c;
        private String d;

        c() {
        }

        public /* synthetic */ void onReceiveValue(Object obj) {
            a((Uri) obj);
        }

        public void a(ValueCallback<Uri> valueCallback) {
            a(valueCallback, com.umeng.a.d);
        }

        public void a(ValueCallback<Uri> valueCallback, String str) {
            a((ValueCallback) valueCallback, com.umeng.a.d, "*");
        }

        public void a(ValueCallback<Uri> valueCallback, String str, String str2) {
            if (WebBrowserActivity.this.mValueCallback == null) {
                WebBrowserActivity.this.mValueCallback = valueCallback;
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.US);
                WebBrowserActivity.this.mPhotoFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), simpleDateFormat.format(date) + ".jpg");
                WebBrowserActivity.this.mCompressFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), simpleDateFormat.format(date) + "_min.jpg");
                ArrayList arrayList = new ArrayList();
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                for (ResolveInfo resolveInfo : WebBrowserActivity.this.getPackageManager().queryIntentActivities(intent, 0)) {
                    Intent intent2 = new Intent(intent);
                    intent2.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    intent2.setPackage(resolveInfo.activityInfo.packageName);
                    intent2.putExtra("output", Uri.fromFile(WebBrowserActivity.this.mPhotoFile));
                    arrayList.add(intent2);
                }
                Intent intent3 = new Intent("android.intent.action.GET_CONTENT");
                intent3.setType("image/*");
                intent3.addCategory("android.intent.category.OPENABLE");
                intent = Intent.createChooser(intent3, "\u9009\u62e9\u6587\u4ef6");
                intent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
                WebBrowserActivity.this.startActivityForResult(intent, R.styleable.AppCompatTheme_buttonStyleSmall);
                return;
            }
            valueCallback.onReceiveValue(null);
        }

        public void a(String str, String str2, String str3) {
            this.b = str;
            this.c = str2;
            this.d = str3;
            a((ValueCallback) this);
        }

        public void a(Uri uri) {
            if (uri != null) {
                WebBrowserActivity.this.showLoadingDialog("\u6b63\u5728\u4e0a\u4f20...", false);
                new XLLiveRequestSimple(f.a(WebBrowserActivity.this).k(), f.a(WebBrowserActivity.this).l(), this.b, this.c, new File(uri.getPath())).send(this);
            }
        }

        public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
            try {
                WebBrowserActivity.this.hideLoadingDialog();
                WebBrowserActivity.this.callJS(this.d, jsonWrapper);
            } catch (Exception e) {
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            WebBrowserActivity.this.mProgressBar.setProgress(i);
            if (i >= 100) {
                WebBrowserActivity.this.mProgressBar.postDelayed(new fy(this), 300);
            } else {
                WebBrowserActivity.this.mProgressBar.setVisibility(0);
            }
            WebBrowserActivity.this.setProgress(i * 100);
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (ac.a()) {
                WebBrowserActivity.this.setTitle(str);
            }
        }
    }

    public WebBrowserActivity() {
        this.mHotKeyIndex = -1;
        this.mHotKeys = new ArrayList();
        this.getAPPInfo = new ft(this);
        this.showLogin = new fu(this);
        this.setMoreAction = new fw(this);
        this.inroom = new eu(this);
        this.recharge = new ev(this);
        this.openURL = new ew(this);
        this.hideKeyboard = new ex(this);
        this.uploadFile = new ey(this);
        this.traceEvent = new ez(this);
        this.close = new fa(this);
        this.showLoading = new fb(this);
        this.hideLoading = new fc(this);
        this.showToast = new fd(this);
        this.showUserCenter = new ff(this);
        this.getVersionName = new fg(this);
        this.getVersionCode = new fh(this);
        this.getSessionId = new fi(this);
        this.getUserInfo = new fj(this);
        this.share = new fk(this);
        this.buyCoins = new fm(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        setContentView(R.layout.xllive_activity_webbrowser);
        if (!ac.j()) {
            this.mErrorView = XLLiveSDK.getInstance(this).host().newErrorView(this, new et(this));
            this.mErrorView.setVisibility(XZBDevice.Wait);
            ((FrameLayout) findViewById(R.id.errorview)).addView(this.mErrorView, -1, -1);
        }
        this.mEditClearView = findViewById(R.id.search_edit_clear);
        this.mEditClearView.setOnClickListener(this);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress);
        this.mEditView = (EditText) findViewById(R.id.search_edit);
        this.mEditView.addTextChangedListener(this);
        this.mEditView.setOnEditorActionListener(this);
        this.mEditView.setOnFocusChangeListener(this);
        if (getIntent().getBooleanExtra(EXTRA_SHOW_SEARCH_BAR, false)) {
            this.mEditView.setVisibility(0);
            new XLLiveGetSearchHotKeywordsRequest(f.a((Context) this).k(), f.a((Context) this).l()).send(new fe(this));
        } else {
            this.mEditView.setVisibility(XZBDevice.Wait);
        }
        this.mWebView = (WebView) findViewById(R.id.webview);
        WebView webView = this.mWebView;
        WebChromeClient cVar = new c();
        this.mWebChromeClient = cVar;
        webView.setWebChromeClient(cVar);
        this.mWebView.setWebViewClient(new fp(this));
        this.mWebView.setDownloadListener(new fq(this));
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.addJavascriptInterface(this, "xlliveBridge");
        this.mWebView.loadUrl(getIntent().getData().toString());
        if (getIntent().getStringExtra(EXTRA_TITLE) != null) {
            setTitle(getIntent().getStringExtra(EXTRA_TITLE));
        } else {
            setTitle("\u52a0\u8f7d\u4e2d...");
        }
        setLeftVisible(true);
        setLeftTextClickListener(this);
        setLeftText2ClickListener(this);
        setLeftDrawable(getResources().getDrawable(R.drawable.xllive_ic_back));
        setRightVisible(getIntent().getBooleanExtra(EXTRA_SHOW_BTN, true));
        setRightClickListener(this);
        setRightDrawable(getResources().getDrawable(R.drawable.xllive_more));
        setTitleBarVisible(getIntent().getBooleanExtra(EXTRA_SHOW_TITLE_BAR, true));
        if (getIntent().getBooleanExtra(EXTRA_TEST_MODE, false)) {
            findViewById(R.id.address_bar).setVisibility(0);
            findViewById(R.id.go).setOnClickListener(this);
        }
    }

    public void onDestroy() {
        this.mWebView.removeJavascriptInterface("xlliveBridge");
        this.mWebView.loadUrl("about:blank");
        this.mWebView.removeAllViews();
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        callJS("window.onResume", new JsonWrapper("{}"));
    }

    public void onPause() {
        super.onPause();
        callJS("window.onPause", new JsonWrapper("{}"));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.mWebView.canGoBack()) {
                this.mWebView.goBack();
                setLeftVisible(true, true);
                return true;
            }
        } else if (i == 82 && keyEvent.getAction() == 0) {
            onClick(this.mTitleBarRight);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onClick(View view) {
        if (view == this.mTitleBarLeftText) {
            if (this.mWebView.canGoBack()) {
                this.mWebView.goBack();
                setLeftVisible(true, true);
                return;
            }
            finish();
        } else if (view == this.mTitleBarLeftText2) {
            finish();
        } else if (view == this.mTitleBarRight) {
            if (!this.setMoreAction.a()) {
                new com.xunlei.tdlive.base.a(this, "\u66f4\u591a\u9009\u9879", "\u53d6\u6d88", "\u5237\u65b0", "\u5206\u4eab", "\u5173\u95ed").b(new fr(this));
            }
        } else if (view == this.mEditClearView) {
            this.mEditView.getEditableText().clear();
        } else if (view.getId() == R.id.go) {
            String toString = ((EditText) findViewById(R.id.address)).getText().toString();
            if (!toString.contains(HttpConstant.SCHEME_SPLIT)) {
                toString = new StringBuilder("http://").append(toString).toString();
            }
            this.mWebView.loadUrl(toString);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            UMShareAPI.get(this).onActivityResult(i, i2, intent);
        } catch (Exception e) {
        }
        if (i == 100) {
            if (i2 == -1) {
                this.mWebView.loadUrl("javascript:void(function(){try{     onLoginFinish(0);}catch(e){}}())");
            } else {
                this.mWebView.loadUrl("javascript:void(function(){try{     onLoginFinish(-1);}catch(e){}}())");
            }
        } else if (i == 101) {
            if (this.mValueCallback != null) {
                String a;
                if (i2 == -1) {
                    if (intent != null) {
                        Uri data = intent.getData();
                        if (data == null || !data.getScheme().equals("file")) {
                            if (data != null) {
                                a = com.xunlei.tdlive.util.c.a((Context) this, data);
                            } else if (intent.hasExtra(SocializeConstants.JSON_DATA)) {
                                a = intent.getStringExtra(SocializeConstants.JSON_DATA);
                            }
                            if (a != null || a.length() <= 0) {
                                this.mValueCallback.onReceiveValue(null);
                            } else {
                                a = com.xunlei.tdlive.util.c.a(a, this.mCompressFile, AccessibilityNodeInfoCompat.ACTION_DISMISS);
                                if (a.startsWith("file://")) {
                                    this.mValueCallback.onReceiveValue(Uri.parse(a));
                                } else {
                                    this.mValueCallback.onReceiveValue(Uri.parse(new StringBuilder("file://").append(a).toString()));
                                }
                            }
                        } else {
                            a = data.getPath();
                            if (a != null) {
                            }
                            this.mValueCallback.onReceiveValue(null);
                        }
                    } else if (this.mPhotoFile != null && this.mPhotoFile.exists()) {
                        a = this.mPhotoFile.getAbsolutePath();
                        if (a != null) {
                        }
                        this.mValueCallback.onReceiveValue(null);
                    }
                }
                a = null;
                if (a != null) {
                }
                this.mValueCallback.onReceiveValue(null);
            }
            this.mValueCallback = null;
            this.mPhotoFile = null;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.mEditClearView.setVisibility(editable.length() > 0 ? 0 : XZBDevice.Wait);
        JsonWrapper jsonWrapper = new JsonWrapper("{}");
        jsonWrapper.putString("keywords", editable.toString().trim());
        jsonWrapper.putInt("suggest", 1);
        callJS("window.getSearchInfo", jsonWrapper);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != R.id.search_edit && i != 0 && i != 4 && i != 6 && i != 2 && i != 3) {
            return false;
        }
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(textView.getWindowToken(), 0);
        } catch (Exception e) {
        }
        String trim = textView.getText().toString().trim();
        if (trim.length() <= 0 && this.mHotKeys.size() > 0) {
            trim = ((String) this.mHotKeys.get(this.mHotKeyIndex)).trim();
        }
        if (trim.length() <= 0) {
            return false;
        }
        JsonWrapper jsonWrapper = new JsonWrapper("{}");
        jsonWrapper.putString("keywords", trim);
        jsonWrapper.putInt("suggest", 0);
        callJS("window.getSearchInfo", jsonWrapper);
        try {
            q.a("zb_search", JsInterface.PAGE_HOME, null);
        } catch (Exception e2) {
        }
        return true;
    }

    public void onFocusChange(View view, boolean z) {
        if (view == this.mEditView && !z) {
            try {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception e) {
            }
        }
    }

    public void onTimer(int i) {
        try {
            ArrayList arrayList = this.mHotKeys;
            int i2 = this.mHotKeyIndex + 1;
            this.mHotKeyIndex = i2;
            i2 %= this.mHotKeys.size();
            this.mHotKeyIndex = i2;
            String str = (String) arrayList.get(i2);
            if (str.length() > 0) {
                this.mEditView.setHint(str);
            }
        } catch (Exception e) {
        }
    }

    @JavascriptInterface
    public void setTitle(String str) {
        this.mTitleBar.postDelayed(new fs(this, str), 10);
    }

    @JavascriptInterface
    public String getUserId() {
        return f.a((Context) this).k();
    }

    @JavascriptInterface
    public String getSessionId() {
        return f.a((Context) this).l();
    }

    @JavascriptInterface
    public void close(int i, String str) {
        setResult(i, new Intent().putExtra(SocializeConstants.JSON_DATA, str));
        super.finish();
    }

    @JavascriptInterface
    public void showLeftButton(boolean z) {
        setLeftVisible(z);
    }

    @JavascriptInterface
    public void showRightButton(boolean z) {
        setRightVisible(z);
    }

    @JavascriptInterface
    public String callMessage(String str) {
        return callMessage(str, null, null);
    }

    @JavascriptInterface
    public String callMessage(String str, String str2) {
        return callMessage(str, str2, null);
    }

    @JavascriptInterface
    public String callMessage(String str, String str2, String str3) {
        return new b().a(str, str2, str3);
    }

    public void callJS(String str, JsonWrapper jsonWrapper) {
        if (str != null && str.length() > 0) {
            if (jsonWrapper == null) {
                jsonWrapper = new JsonWrapper("{}");
            }
            this.mWebView.loadUrl(new StringBuilder("javascript:void(function(){try{").append(str).append("('").append(jsonWrapper.toString()).append("');}catch(e){}}())").toString());
        }
    }

    public static Intent buildStartIntent(Context context, String str, String str2, boolean z, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Intent intent = new Intent(context, WebBrowserActivity.class);
        intent.setData(Uri.parse(str));
        intent.putExtra(EXTRA_TITLE, str2);
        intent.putExtra(EXTRA_SHOW_BTN, z);
        intent.setFlags(i);
        return intent;
    }

    public static void start(Context context, String str, String str2, boolean z, boolean z2, boolean z3) {
        Intent buildStartIntent = buildStartIntent(context, str, str2, z2, 0);
        if (buildStartIntent != null) {
            buildStartIntent.putExtra(EXTRA_SHOW_SEARCH_BAR, z);
            buildStartIntent.putExtra(EXTRA_TEST_MODE, z3);
            context.startActivity(buildStartIntent);
        }
    }

    public static void start(Context context, String str, String str2, boolean z, boolean z2) {
        Intent buildStartIntent = buildStartIntent(context, str, str2, z, 0);
        if (buildStartIntent != null) {
            buildStartIntent.putExtra(EXTRA_TEST_MODE, z2);
            context.startActivity(buildStartIntent);
        }
    }

    public static void start(Context context, String str, String str2, boolean z, int i) {
        Intent buildStartIntent = buildStartIntent(context, str, str2, z, i);
        if (buildStartIntent != null) {
            context.startActivity(buildStartIntent);
        }
    }

    public static void start(Context context, String str, String str2, boolean z) {
        start(context, str, str2, z, 0);
    }
}
