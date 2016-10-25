package com.xunlei.common.pay;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import com.xunlei.common.httpclient.BaseHttpClient;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.js.export.IXLPayJSHandler;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLPriceParam;
import com.xunlei.common.pay.param.XLWxPayParam;

public class XLPayUtil {
    private static XLPayUtil mInstance;

    static {
        mInstance = null;
    }

    private XLPayUtil() {
    }

    private f getProxy() {
        return f.a();
    }

    public static XLPayUtil getInstance() {
        if (mInstance == null) {
            mInstance = new XLPayUtil();
        }
        return mInstance;
    }

    public final Context getContext() {
        return getProxy().d();
    }

    public final BaseHttpClient getHttpClient() {
        return getProxy().e();
    }

    public final String getVersion() {
        return getProxy().f();
    }

    public final int getBusinessType() {
        return getProxy().g();
    }

    public void attachListener(XLOnPayListener xLOnPayListener) {
        getProxy().a(xLOnPayListener);
    }

    public void detachListener(XLOnPayListener xLOnPayListener) {
        getProxy().b(xLOnPayListener);
    }

    public void init(Context context, int i, String str, String str2, String str3) {
        getProxy().a(context, i, str, str2);
    }

    public void unInit() {
        getProxy().b();
    }

    public void acceptWxPayResult(int i, int i2) {
        getProxy().a(i, i2);
    }

    public int userGetPrice(XLPriceParam xLPriceParam, Object obj) {
        return getProxy().a(xLPriceParam, obj);
    }

    public int userWxPay(XLWxPayParam xLWxPayParam, Object obj) {
        return getProxy().a(xLWxPayParam, obj, null);
    }

    public int userAliPay(XLAlipayParam xLAlipayParam, Object obj) {
        return getProxy().a(xLAlipayParam, obj, null);
    }

    public int userNbPay(XLAlipayParam xLAlipayParam, Object obj) {
        return getProxy().b(xLAlipayParam, obj, null);
    }

    public XLContractor userGetXLContractor(int i) {
        getProxy();
        return f.a(i);
    }

    public boolean userRegisterJSInterface(WebView webView, Activity activity, IXLPayJSHandler iXLPayJSHandler) {
        return getProxy().a(webView, activity, iXLPayJSHandler);
    }

    public boolean userUnRegisterJSInterface(WebView webView) {
        return getProxy().a(webView);
    }
}
