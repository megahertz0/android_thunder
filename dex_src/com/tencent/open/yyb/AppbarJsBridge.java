package com.tencent.open.yyb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.alipay.sdk.packet.d;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.utils.Util;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.WebBrowserActivity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: ProGuard
public class AppbarJsBridge {
    public static final int AUTHORIZE_FAIL = -5;
    public static final String BUTTON_CLICK_CALLBACK_FUNCTION_NAME = "clickCallback";
    public static final String CALLBACK_LOGIN = "loginCallback";
    public static final String CALLBACK_SHARE = "shareCallback";
    public static final int Code_Java_Exception = -3;
    public static final int Code_None = -2;
    public static final int JSBRIDGE_VERSION = 1;
    public static final String JS_BRIDGE_SCHEME = "jsb://";
    public static final String READY_CALLBACK_FUNCTION_NAME = "readyCallback";
    public static final int Result_Fail = -1;
    public static final int Result_OK = 0;
    public static final int SHARE_QQ = 1;
    public static final int SHARE_QZ = 2;
    public static final int SHARE_TIMELINE = 4;
    public static final int SHARE_WX = 3;
    private WebView a;
    private Activity b;

    public AppbarJsBridge(Activity activity, WebView webView) {
        this.b = activity;
        this.a = webView;
    }

    public void closeWebView(Uri uri, int i, String str, String str2) {
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->closeWebView : url = ").append(uri).toString());
        this.b.finish();
    }

    public void pageControl(Uri uri, int i, String str, String str2) {
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->pageControl : url = ").append(uri).toString());
        int parseIntValue = Util.parseIntValue(uri.getQueryParameter(JsInterface.FUNPLAY_AD_TRPE));
        if (this.a != null) {
            if (parseIntValue == 1) {
                this.a.goBack();
            } else if (parseIntValue == 2) {
                this.a.goForward();
            } else {
                this.a.reload();
            }
        }
        response(str2, i, str, a.d);
    }

    public void share(Uri uri, int i, String str, String str2) {
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->share : url = ").append(uri).toString());
        String queryParameter = uri.getQueryParameter(WebBrowserActivity.EXTRA_TITLE);
        String queryParameter2 = uri.getQueryParameter(SocialConstants.PARAM_SUMMARY);
        String queryParameter3 = uri.getQueryParameter("iconUrl");
        if (TextUtils.isEmpty(queryParameter3)) {
            queryParameter3 = "http://qzs.qq.com/open/mobile/jsbridge/demo.htm";
        }
        String queryParameter4 = uri.getQueryParameter("jumpUrl");
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->share : title = ").append(queryParameter).toString());
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->share : summary = ").append(queryParameter2).toString());
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->share : iconUrl = ").append(queryParameter3).toString());
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->share : jumpUrl = ").append(queryParameter4).toString());
        ShareModel shareModel = new ShareModel();
        shareModel.a = queryParameter;
        shareModel.b = queryParameter2;
        shareModel.c = queryParameter3;
        shareModel.d = queryParameter4;
        ((AppbarActivity) this.b).setShareModel(shareModel);
        switch (Util.parseIntValue(uri.getQueryParameter(JsInterface.FUNPLAY_AD_TRPE), Result_OK)) {
            case SHARE_QQ:
                ((AppbarActivity) this.b).shareToQQ();
            case SHARE_QZ:
                ((AppbarActivity) this.b).shareToQzone();
            case SHARE_WX:
                ((AppbarActivity) this.b).shareToWX();
            case SHARE_TIMELINE:
                ((AppbarActivity) this.b).shareToTimeline();
            default:
                ((AppbarActivity) this.b).showFloatingDialog();
        }
    }

    public void getAppInfo(Uri uri, int i, String str, String str2) {
        Object queryParameter = uri.getQueryParameter("packagenames");
        f.b("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->getAppInfo : packageNames = ").append(queryParameter).toString());
        if (!TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(str2)) {
            String[] split = queryParameter.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            if (split != null && split.length != 0) {
                JSONObject jSONObject = new JSONObject();
                int length = split.length;
                for (int i2 = 0; i2 < length; i2++) {
                    PackageInfo packageInfo;
                    String trim = split[i2].trim();
                    try {
                        packageInfo = this.b.getPackageManager().getPackageInfo(trim, Result_OK);
                    } catch (Throwable e) {
                        f.b("openSDK_LOG.AppbarJsBridge", "-->getAppInfo : NameNotFoundException e1", e);
                        packageInfo = null;
                    }
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        if (packageInfo != null) {
                            jSONObject2.put("install", SHARE_QQ);
                            jSONObject2.put(SocialConstants.PARAM_APPNAME, packageInfo.applicationInfo.name);
                            jSONObject2.put("verCode", packageInfo.versionCode);
                            jSONObject2.put("verName", packageInfo.versionName);
                        } else {
                            jSONObject2.put("install", Result_OK);
                        }
                        jSONObject.put(trim, jSONObject2);
                    } catch (Exception e2) {
                        responseFail(str2, i, str, Code_Java_Exception);
                    }
                }
                f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->getAppInfo : result = ").append(jSONObject.toString()).toString());
                response(str2, i, str, jSONObject.toString());
            }
        }
    }

    public void clickCallback() {
        response(BUTTON_CLICK_CALLBACK_FUNCTION_NAME, Result_OK, null, a.d);
    }

    public void openNewWindow(Uri uri, int i, String str, String str2) {
        String queryParameter = uri.getQueryParameter(SocialConstants.PARAM_URL);
        try {
            Intent intent = new Intent(this.b, AppbarActivity.class);
            intent.putExtra(SocialConstants.PARAM_URL, queryParameter);
            this.b.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWebView(Uri uri, int i, String str, String str2) {
        boolean z = true;
        try {
            Object queryParameter = uri.getQueryParameter(WebBrowserActivity.EXTRA_TITLE);
            int parseIntValue = Util.parseIntValue(uri.getQueryParameter("buttonVisible"), Result_OK);
            if (!TextUtils.isEmpty(queryParameter)) {
                ((AppbarActivity) this.b).setAppbarTitle(queryParameter);
            }
            AppbarActivity appbarActivity = (AppbarActivity) this.b;
            if (parseIntValue != 1) {
                z = false;
            }
            appbarActivity.setShareVisibility(z);
            f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->setWebView : url = ").append(uri).append(" -- buttonVisiable = ").append(parseIntValue).toString());
            response(str2, i, str, a.d);
        } catch (Exception e) {
            responseFail(str2, i, str, Code_Java_Exception);
        }
    }

    public void openLoginActivity(Uri uri, int i, String str, String str2) {
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->openLoginActivity : url = ").append(uri).toString());
        ((AppbarActivity) this.b).login();
    }

    public int getVersion() {
        return SHARE_QQ;
    }

    public void invoke(String str) {
        int i;
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->invoke : url = ").append(str).toString());
        Uri parse = Uri.parse(str);
        String host = parse.getHost();
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->invoke : hostAsMethodName = ").append(host).toString());
        if (!TextUtils.isEmpty(host)) {
            List pathSegments = parse.getPathSegments();
            String str2 = null;
            if (pathSegments == null || pathSegments.size() <= 0) {
                i = 0;
            } else {
                i = Util.parseIntValue((String) pathSegments.get(Result_OK));
                if (pathSegments.size() > 1) {
                    str2 = (String) pathSegments.get(SHARE_QQ);
                }
            }
            f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->invoke : seqid = ").append(i).append(" callbackName = ").append(str2).toString());
            if (host.equals("callBatch")) {
                try {
                    JSONArray jSONArray = new JSONArray(parse.getQueryParameter(SocializeConstants.OP_KEY));
                    int length = jSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        String string = jSONObject.getString(d.q);
                        int i3 = jSONObject.getInt("seqid");
                        String optString = jSONObject.optString(com.alipay.sdk.authjs.a.c);
                        JSONObject jSONObject2 = jSONObject.getJSONObject("args");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(JS_BRIDGE_SCHEME).append(string).append("/").append(i3).append("/").append(!TextUtils.isEmpty(optString) ? optString : a.d).append("?");
                        if (jSONObject2 != null) {
                            Iterator keys = jSONObject2.keys();
                            while (keys.hasNext()) {
                                String str3 = (String) keys.next();
                                stringBuilder.append(str3).append("=").append(Uri.encode(Uri.decode(jSONObject2.getString(str3)))).append(com.alipay.sdk.sys.a.b);
                            }
                        }
                        a(Uri.parse(stringBuilder.toString()), string, i3, optString);
                    }
                    return;
                } catch (Exception e) {
                    if (!TextUtils.isEmpty(str2)) {
                        responseFail(str2, i, host, AUTHORIZE_FAIL);
                    }
                }
            }
            a(parse, host, i, str2);
        }
    }

    private void a(Uri uri, String str, int i, String str2) {
        f.a("openSDK_LOG.AppbarJsBridge", new StringBuilder("-->callAMethod : uri = ").append(uri).toString());
        if (a(str)) {
            try {
                AppbarJsBridge.class.getDeclaredMethod(str, new Class[]{Uri.class, Integer.TYPE, String.class, String.class}).invoke(this, new Object[]{uri, Integer.valueOf(i), str, str2});
            } catch (Throwable e) {
                f.a("openSDK_LOG.AppbarJsBridge", "-->callAMethod : Exception = ", e);
                e.printStackTrace();
                if (!TextUtils.isEmpty(str2)) {
                    responseFail(str2, i, str, Code_Java_Exception);
                }
            }
        } else if (!TextUtils.isEmpty(str2)) {
            responseFail(str2, i, str, AUTHORIZE_FAIL);
        }
    }

    private boolean a(String str) {
        return true;
    }

    public void ready() {
        response(READY_CALLBACK_FUNCTION_NAME, SHARE_QQ, null, "true");
    }

    public void responseShare(int i) {
        Map hashMap = new HashMap();
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, String.valueOf(i));
        response(CALLBACK_SHARE, Result_OK, null, MessageService.MSG_DB_READY_REPORT, hashMap);
    }

    public void responseShareFail(int i) {
        Map hashMap = new HashMap();
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, String.valueOf(i));
        response(CALLBACK_SHARE, Result_OK, null, MessageService.MSG_DB_NOTIFY_REACHED, hashMap);
    }

    public void response(String str, int i, String str2, String str3) {
        response(str, i, str2, str3, null);
    }

    public void response(String str, int i, String str2, String str3, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", Result_OK);
                jSONObject.put(SocializeConstants.JSON_DATA, str3);
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put(d.q, str2);
                }
                jSONObject.put("seqid", i);
                if (map != null) {
                    for (String str4 : map.keySet()) {
                        jSONObject.put(str4, map.get(str4));
                    }
                }
                callback(str, jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void responseFail(String str, int i, String str2, int i2) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", Result_Fail);
                jSONObject.put(Constants.KEY_HTTP_CODE, i2);
                jSONObject.put(d.q, str2);
                jSONObject.put("seqid", i);
                callback(str, jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void callback(String str, String str2) {
        if (this.a != null) {
            StringBuffer stringBuffer = new StringBuffer(BaseJsInterface.JS_PREFIX);
            stringBuffer.append("if(!!").append(str).append("){");
            stringBuffer.append(str);
            stringBuffer.append(SocializeConstants.OP_OPEN_PAREN);
            stringBuffer.append(str2);
            stringBuffer.append(")}");
            this.a.loadUrl(stringBuffer.toString());
        }
    }
}
