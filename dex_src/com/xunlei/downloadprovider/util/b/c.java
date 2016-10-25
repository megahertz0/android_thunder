package com.xunlei.downloadprovider.util.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebView;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.downloadprovider.a.b;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: CrackUtil.java
public final class c {
    private static c b;
    public final Map<Object, a> a;
    private final int c;
    private final Handler d;

    static {
        b = null;
    }

    private c() {
        this.c = 30000;
        this.a = new HashMap();
        this.d = new d(this, Looper.getMainLooper());
    }

    public static c a() {
        if (b == null) {
            b = new c();
        }
        return b;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public final void a(Object obj, Context context, String str, String str2, String str3, String str4, String str5, int i, boolean z, String str6, f fVar) {
        String encode;
        String encode2;
        a aVar;
        WebView webView;
        Message obtainMessage;
        if ((obj == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) && fVar != null) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    fVar.a(XLPayErrorCode.XLP_GATE_PARAM_ERROR, null);
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    fVar.a(XLPayErrorCode.XLP_GATE_PARAM_ERROR, str, str2, str3, str4, null);
                    break;
            }
        }
        if (this.a.containsKey(obj)) {
            a aVar2 = (a) this.a.remove(obj);
            if (aVar2 != null) {
                aVar2.mCancel = true;
            }
        }
        StringBuilder stringBuilder = new StringBuilder("http://m.sjzhushou.com/v2/search/getPlayList.html?");
        try {
            encode = URLEncoder.encode(str, CharsetConvert.UTF_8);
            try {
                encode2 = URLEncoder.encode(str2, CharsetConvert.UTF_8);
            } catch (UnsupportedEncodingException e) {
                UnsupportedEncodingException unsupportedEncodingException = e;
                encode2 = encode;
                UnsupportedEncodingException unsupportedEncodingException2 = unsupportedEncodingException;
                unsupportedEncodingException2.printStackTrace();
                encode = encode2;
                encode2 = str2;
                stringBuilder.append("tag=").append(str3);
                stringBuilder.append("&title=").append(encode);
                stringBuilder.append("&refUrl=").append(encode2);
                stringBuilder.append("&id=").append(str4);
                switch (i) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        stringBuilder.append("&action=download");
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        stringBuilder.append("&action=play");
                        break;
                }
                if (!TextUtils.isEmpty(str5)) {
                    stringBuilder.append("&which=").append(str5);
                }
                stringBuilder.append("&copyright=").append(z);
                if (!TextUtils.isEmpty(str6)) {
                    stringBuilder.append("&sharpness=").append(str6);
                }
                stringBuilder.append("&versionCode=").append(String.valueOf(b.x()));
                aVar = new a(this.d);
                aVar.mID = str4;
                aVar.mKeyData = obj;
                aVar.mOperateType = i;
                aVar.mRefURL = str2;
                aVar.mTAG = str3;
                aVar.mTitle = str;
                aVar.setOnCrackCallback(fVar);
                this.a.put(obj, aVar);
                webView = new WebView(context);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.addJavascriptInterface(aVar, "share");
                webView.setWebViewClient(new e(this, aVar, obj, fVar, i, str, str2, str3, str4));
                obtainMessage = this.d.obtainMessage();
                obtainMessage.what = 9901;
                obtainMessage.obj = obj;
                obtainMessage.setData(new Bundle());
                this.d.sendMessageDelayed(obtainMessage, 30000);
                webView.loadUrl(stringBuilder.toString());
            } catch (IllegalArgumentException e2) {
                IllegalArgumentException illegalArgumentException = e2;
                encode2 = encode;
                IllegalArgumentException illegalArgumentException2 = illegalArgumentException;
                illegalArgumentException2.printStackTrace();
                encode = encode2;
                encode2 = str2;
                stringBuilder.append("tag=").append(str3);
                stringBuilder.append("&title=").append(encode);
                stringBuilder.append("&refUrl=").append(encode2);
                stringBuilder.append("&id=").append(str4);
                switch (i) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        stringBuilder.append("&action=download");
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        stringBuilder.append("&action=play");
                        break;
                }
                if (TextUtils.isEmpty(str5)) {
                    stringBuilder.append("&which=").append(str5);
                }
                stringBuilder.append("&copyright=").append(z);
                if (TextUtils.isEmpty(str6)) {
                    stringBuilder.append("&sharpness=").append(str6);
                }
                stringBuilder.append("&versionCode=").append(String.valueOf(b.x()));
                aVar = new a(this.d);
                aVar.mID = str4;
                aVar.mKeyData = obj;
                aVar.mOperateType = i;
                aVar.mRefURL = str2;
                aVar.mTAG = str3;
                aVar.mTitle = str;
                aVar.setOnCrackCallback(fVar);
                this.a.put(obj, aVar);
                webView = new WebView(context);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.addJavascriptInterface(aVar, "share");
                webView.setWebViewClient(new e(this, aVar, obj, fVar, i, str, str2, str3, str4));
                obtainMessage = this.d.obtainMessage();
                obtainMessage.what = 9901;
                obtainMessage.obj = obj;
                obtainMessage.setData(new Bundle());
                this.d.sendMessageDelayed(obtainMessage, 30000);
                webView.loadUrl(stringBuilder.toString());
            }
        } catch (UnsupportedEncodingException e3) {
            unsupportedEncodingException2 = e3;
            encode2 = str;
            unsupportedEncodingException2.printStackTrace();
            encode = encode2;
            encode2 = str2;
            stringBuilder.append("tag=").append(str3);
            stringBuilder.append("&title=").append(encode);
            stringBuilder.append("&refUrl=").append(encode2);
            stringBuilder.append("&id=").append(str4);
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    stringBuilder.append("&action=download");
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    stringBuilder.append("&action=play");
                    break;
            }
            if (TextUtils.isEmpty(str5)) {
                stringBuilder.append("&which=").append(str5);
            }
            stringBuilder.append("&copyright=").append(z);
            if (TextUtils.isEmpty(str6)) {
                stringBuilder.append("&sharpness=").append(str6);
            }
            stringBuilder.append("&versionCode=").append(String.valueOf(b.x()));
            aVar = new a(this.d);
            aVar.mID = str4;
            aVar.mKeyData = obj;
            aVar.mOperateType = i;
            aVar.mRefURL = str2;
            aVar.mTAG = str3;
            aVar.mTitle = str;
            aVar.setOnCrackCallback(fVar);
            this.a.put(obj, aVar);
            webView = new WebView(context);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(aVar, "share");
            webView.setWebViewClient(new e(this, aVar, obj, fVar, i, str, str2, str3, str4));
            obtainMessage = this.d.obtainMessage();
            obtainMessage.what = 9901;
            obtainMessage.obj = obj;
            obtainMessage.setData(new Bundle());
            this.d.sendMessageDelayed(obtainMessage, 30000);
            webView.loadUrl(stringBuilder.toString());
        } catch (IllegalArgumentException e22) {
            illegalArgumentException2 = e22;
            encode2 = str;
            illegalArgumentException2.printStackTrace();
            encode = encode2;
            encode2 = str2;
            stringBuilder.append("tag=").append(str3);
            stringBuilder.append("&title=").append(encode);
            stringBuilder.append("&refUrl=").append(encode2);
            stringBuilder.append("&id=").append(str4);
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    stringBuilder.append("&action=download");
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    stringBuilder.append("&action=play");
                    break;
            }
            if (TextUtils.isEmpty(str5)) {
                stringBuilder.append("&which=").append(str5);
            }
            stringBuilder.append("&copyright=").append(z);
            if (TextUtils.isEmpty(str6)) {
                stringBuilder.append("&sharpness=").append(str6);
            }
            stringBuilder.append("&versionCode=").append(String.valueOf(b.x()));
            aVar = new a(this.d);
            aVar.mID = str4;
            aVar.mKeyData = obj;
            aVar.mOperateType = i;
            aVar.mRefURL = str2;
            aVar.mTAG = str3;
            aVar.mTitle = str;
            aVar.setOnCrackCallback(fVar);
            this.a.put(obj, aVar);
            webView = new WebView(context);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(aVar, "share");
            webView.setWebViewClient(new e(this, aVar, obj, fVar, i, str, str2, str3, str4));
            obtainMessage = this.d.obtainMessage();
            obtainMessage.what = 9901;
            obtainMessage.obj = obj;
            obtainMessage.setData(new Bundle());
            this.d.sendMessageDelayed(obtainMessage, 30000);
            webView.loadUrl(stringBuilder.toString());
        }
        stringBuilder.append("tag=").append(str3);
        stringBuilder.append("&title=").append(encode);
        stringBuilder.append("&refUrl=").append(encode2);
        stringBuilder.append("&id=").append(str4);
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                stringBuilder.append("&action=download");
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                stringBuilder.append("&action=play");
                break;
        }
        if (TextUtils.isEmpty(str5)) {
            stringBuilder.append("&which=").append(str5);
        }
        stringBuilder.append("&copyright=").append(z);
        if (TextUtils.isEmpty(str6)) {
            stringBuilder.append("&sharpness=").append(str6);
        }
        stringBuilder.append("&versionCode=").append(String.valueOf(b.x()));
        aVar = new a(this.d);
        aVar.mID = str4;
        aVar.mKeyData = obj;
        aVar.mOperateType = i;
        aVar.mRefURL = str2;
        aVar.mTAG = str3;
        aVar.mTitle = str;
        aVar.setOnCrackCallback(fVar);
        this.a.put(obj, aVar);
        webView = new WebView(context);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(aVar, "share");
        webView.setWebViewClient(new e(this, aVar, obj, fVar, i, str, str2, str3, str4));
        obtainMessage = this.d.obtainMessage();
        obtainMessage.what = 9901;
        obtainMessage.obj = obj;
        obtainMessage.setData(new Bundle());
        this.d.sendMessageDelayed(obtainMessage, 30000);
        webView.loadUrl(stringBuilder.toString());
    }
}
