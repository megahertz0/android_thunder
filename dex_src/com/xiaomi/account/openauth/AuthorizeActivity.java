package com.xiaomi.account.openauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.common.Constants;
import com.umeng.a;
import com.xiaomi.account.IXiaomiAuthResponse;
import com.xiaomi.account.XiaomiOAuthResponse;
import com.xunlei.tdlive.R;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class AuthorizeActivity extends Activity {
    private static final String AUTHORIZE_PATH;
    private static final String KEY_KEEP_COOKIES = "extra_keep_cookies ";
    private static final String KEY_MY_BUNDLE = "extra_my_bundle";
    private static final String KEY_MY_INTENT = "extra_my_intent";
    private static final String KEY_RESPONSE = "extra_response";
    private static final String KEY_RESULT_CODE = "extra_result_code";
    private static final String LOCALE_KEY_IN_URL = "_locale";
    private static final int REQUEST_CODE = 1001;
    public static int RESULT_CANCEL = 0;
    public static int RESULT_FAIL = 0;
    public static int RESULT_SUCCESS = 0;
    private static final String UTF8 = "UTF-8";
    private boolean mKeepCookies;
    private XiaomiOAuthResponse mResponse;
    private WebSettings mSettings;
    private String mUrl;
    private WebView mWebView;

    class AuthorizeWebViewClient extends WebViewClient {
        AuthorizeWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String str2 = new String(str);
            int indexOf = str2.indexOf(R.styleable.AppCompatTheme_editTextBackground);
            String substring;
            if (indexOf > 0) {
                substring = str2.substring(indexOf + 1);
                if (substring.startsWith("code=") || substring.contains("&code=")) {
                    AuthorizeActivity.this.setResultAndFinish(RESULT_SUCCESS, str2);
                    return true;
                } else if (substring.startsWith("error=") || substring.contains("&error=")) {
                    AuthorizeActivity.this.setResultAndFinish(RESULT_FAIL, str2);
                    return true;
                }
            }
            indexOf = str2.indexOf(R.styleable.AppCompatTheme_actionModeSelectAllDrawable);
            if (indexOf > 0) {
                substring = str2.substring(indexOf + 1);
                if (substring.startsWith("access_token=") || substring.contains("&access_token=")) {
                    AuthorizeActivity.this.setResultAndFinish(RESULT_SUCCESS, str2.replace("#", "?"));
                    return true;
                } else if (substring.startsWith("error=") || substring.contains("&error=")) {
                    AuthorizeActivity.this.setResultAndFinish(RESULT_FAIL, str2.replace("#", "?"));
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    public AuthorizeActivity() {
        this.mKeepCookies = false;
    }

    static {
        RESULT_SUCCESS = -1;
        RESULT_FAIL = 1;
        RESULT_CANCEL = 0;
        AUTHORIZE_PATH = AuthorizeHelper.OAUTH2_HOST + "/oauth2/authorize";
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        Bundle bundleExtra = intent.getBundleExtra(KEY_MY_BUNDLE);
        if (bundleExtra != null) {
            setResultAndFinish(intent.getIntExtra(KEY_RESULT_CODE, -1), bundleExtra);
            return;
        }
        this.mResponse = (XiaomiOAuthResponse) intent.getParcelableExtra(KEY_RESPONSE);
        Intent intent2 = (Intent) intent.getParcelableExtra(KEY_MY_INTENT);
        if (intent2 != null) {
            startActivityForResult(intent2, REQUEST_CODE);
            return;
        }
        this.mKeepCookies = intent.getBooleanExtra(KEY_KEEP_COOKIES, false);
        setContentView(createView());
        this.mSettings = this.mWebView.getSettings();
        this.mSettings.setJavaScriptEnabled(true);
        this.mSettings.setSavePassword(false);
        this.mSettings.setSaveFormData(false);
        this.mUrl = AUTHORIZE_PATH + "?" + parseBundle(addLocaleIfNeeded(intent.getBundleExtra("url_param")));
        removeCookiesIfNeeded();
        this.mWebView.loadUrl(this.mUrl);
        this.mWebView.setWebViewClient(new AuthorizeWebViewClient());
    }

    private View createView() {
        View linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        this.mWebView = new WebView(this);
        linearLayout.addView(this.mWebView, new LayoutParams(-1, -1));
        return linearLayout;
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            setResultAndFinish(RESULT_CANCEL, null);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001) {
            setResultAndFinish(i2, intent != null ? intent.getExtras() : null);
        }
    }

    void setResultAndFinish(int i, String str) {
        setResultAndFinish(i, parseUrl(str));
    }

    void setResultAndFinish(int i, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        setResult(i, intent);
        if (this.mResponse != null) {
            if (i == 0) {
                this.mResponse.onCancel();
            } else {
                this.mResponse.onResult(bundle);
            }
        }
        removeCookiesIfNeeded();
        finish();
    }

    private void removeCookiesIfNeeded() {
        if (!this.mKeepCookies) {
            CookieSyncManager.createInstance(this);
            CookieManager.getInstance().removeAllCookie();
        }
    }

    private Bundle parseUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            try {
                for (NameValuePair nameValuePair : URLEncodedUtils.parse(new URI(str), UTF8)) {
                    if (!TextUtils.isEmpty(nameValuePair.getName()) && !TextUtils.isEmpty(nameValuePair.getValue())) {
                        bundle.putString(nameValuePair.getName(), nameValuePair.getValue());
                    }
                }
            } catch (URISyntaxException e) {
                e.getMessage();
            }
        }
        return bundle;
    }

    private String parseBundle(Bundle bundle) {
        if (bundle == null) {
            return a.d;
        }
        List arrayList = new ArrayList();
        for (String str : bundle.keySet()) {
            Object string = bundle.getString(str);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(string)) {
                arrayList.add(new BasicNameValuePair(str, string));
            }
        }
        return URLEncodedUtils.format(arrayList, UTF8);
    }

    private Bundle addLocaleIfNeeded(Bundle bundle) {
        if (!(bundle == null || bundle.containsKey(LOCALE_KEY_IN_URL))) {
            Object localeString = getLocaleString(Locale.getDefault());
            if (!TextUtils.isEmpty(localeString)) {
                bundle.putString(LOCALE_KEY_IN_URL, localeString);
            }
        }
        return bundle;
    }

    private static String getLocaleString(Locale locale) {
        if (locale == null) {
            return null;
        }
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(locale.getCountry())) {
            return language;
        }
        return String.format("%s_%s", new Object[]{language, locale.getCountry()});
    }

    public static Intent asMiddleActivity(Activity activity, Intent intent, IXiaomiAuthResponse iXiaomiAuthResponse) {
        Intent intent2 = new Intent(activity, AuthorizeActivity.class);
        intent2.putExtra(KEY_MY_INTENT, intent);
        intent2.putExtra(KEY_RESPONSE, new XiaomiOAuthResponse(iXiaomiAuthResponse));
        return intent2;
    }

    public static Intent asMiddleActivity(Activity activity, int i, Bundle bundle) {
        Intent intent = new Intent(activity, AuthorizeActivity.class);
        intent.putExtra(KEY_MY_BUNDLE, bundle);
        intent.putExtra(KEY_RESULT_CODE, i);
        return intent;
    }

    public static Intent getIntent(Activity activity, String str, String str2, String str3, String str4, String str5, Boolean bool, boolean z, IXiaomiAuthResponse iXiaomiAuthResponse) {
        Intent intent = new Intent(activity, AuthorizeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAM_CLIENT_ID, String.valueOf(str));
        bundle.putString(WBConstants.AUTH_PARAMS_REDIRECT_URL, str2);
        bundle.putString(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, str3);
        bundle.putString(Constants.PARAM_SCOPE, str4);
        bundle.putString("state", str5);
        if (bool != null) {
            bundle.putString("skip_confirm", String.valueOf(bool));
        }
        intent.putExtra("url_param", bundle);
        intent.putExtra(KEY_KEEP_COOKIES, z);
        intent.putExtra(KEY_RESPONSE, new XiaomiOAuthResponse(iXiaomiAuthResponse));
        return intent;
    }
}
