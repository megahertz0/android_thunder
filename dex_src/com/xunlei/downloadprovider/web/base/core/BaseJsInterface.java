package com.xunlei.downloadprovider.web.base.core;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.android.volley.Request;
import com.android.volley.p;
import com.taobao.accs.common.Constants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.j.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseJsInterface {
    private static final String EXTRA_CALLBACK = "Callback";
    private static final String EXTRA_METHOD_NAME = "MethodName";
    private static final String EXTRA_PARAMS = "Params";
    public static final String JS_PREFIX = "javascript:";
    public static final String NAME = "XLJSWebViewBridge";
    protected static final String TAG = "JsInterface";
    private static final int WHAT_SEND_MESSAGE = 1;
    private static p sQueue;
    private Context mContext;
    private Handler mHandler;
    private CustomWebView mWebView;

    protected abstract boolean handleMessage(MethodName methodName, JSONObject jSONObject, String str) throws JSONException;

    static {
        sQueue = a.a().e();
    }

    public BaseJsInterface() {
        this.mHandler = new a(this, Looper.getMainLooper());
    }

    public BaseJsInterface(Context context, CustomWebView customWebView) {
        this.mHandler = new a(this, Looper.getMainLooper());
        setupParams(context, customWebView);
    }

    public void setupParams(Context context, CustomWebView customWebView) {
        this.mWebView = customWebView;
        this.mContext = context;
    }

    @JavascriptInterface
    public void sendMessage(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            new StringBuilder("sendMessage--methodNameStr=").append(str).append("|params=").append(str2).append("|callback=").append(str3);
            Message obtain = Message.obtain();
            obtain.what = 1;
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_METHOD_NAME, str);
            bundle.putString(EXTRA_CALLBACK, str3);
            bundle.putString(EXTRA_PARAMS, str2);
            obtain.setData(bundle);
            this.mHandler.sendMessage(obtain);
        }
    }

    protected Context getContext() {
        return this.mContext;
    }

    protected CustomWebView getWebView() {
        return this.mWebView;
    }

    protected void callbackWithOneParam(String str, String str2, Object obj) {
        Map map = null;
        if (!TextUtils.isEmpty(str2)) {
            map = new HashMap();
            map.put(str2, obj);
        }
        callback(str, map);
    }

    protected void callbackError(String str, int i, String str2) {
        Map hashMap = new HashMap();
        hashMap.put(Constants.KEY_ERROR_CODE, Integer.valueOf(i));
        hashMap.put(Impl.COLUMN_ERROR_MSG, str2);
        Map hashMap2 = new HashMap();
        hashMap2.put("internalError", hashMap);
        callback(str, hashMap2);
    }

    protected void callback(String str, Map<String, Object> map) {
        runOnUiThread(new a(this, str, map));
    }

    protected void callbackDirect(String str, Map<String, Object> map) {
        if (!t.a(str)) {
            String generateCallbackJson = generateCallbackJson(str, map);
            if (!TextUtils.isEmpty(generateCallbackJson)) {
                this.mWebView.a(generateCallbackJson);
            }
        }
    }

    protected void add(Request request) {
        sQueue.a(request);
    }

    private String generateCallbackJson(String str, Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return mergeJsCallbackUrl(str, new String[0]);
        }
        return mergeJsCallbackUrl(str, new JSONObject(map).toString());
    }

    private static String mergeJsCallbackUrl(String str, String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(JS_PREFIX);
        stringBuilder.append(str);
        stringBuilder.append('(');
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                stringBuilder.append("'");
                if (strArr[i] != null) {
                    stringBuilder.append(strArr[i].replace("\\", "\\\\").replace("'", "\\'"));
                }
                stringBuilder.append("'");
                stringBuilder.append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    protected void runOnUiThread(Runnable runnable) {
        this.mWebView.post(runnable);
    }
}
