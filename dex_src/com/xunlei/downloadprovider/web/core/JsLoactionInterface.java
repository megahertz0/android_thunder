package com.xunlei.downloadprovider.web.core;

import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import com.xunlei.downloadprovider.g.a;

public class JsLoactionInterface extends JsInterface {
    public static final int MSG_JS_LOCATION_FINISH_ACTIVITY = 1067;
    public static final int MSG_JS_LOCATION_GET_LOCATION = 1065;
    public static final int MSG_JS_LOCATION_GO_BACK = 1066;
    public static final int MSG_JS_LOCATION_GO_TO_GPS_SETTING = 1064;

    public JsLoactionInterface(Handler handler, String str, String str2, String str3, String str4, String str5) {
        super(handler, str, str2, str3, str4, str5);
    }

    @JavascriptInterface
    public int isGpsOpen() {
        return a.a() ? 1 : 0;
    }

    @JavascriptInterface
    public void gotoGpsSetting() {
        new StringBuilder().append(getClass()).append("---goToGpsSetting()---").append(Thread.currentThread().getId());
        this.mHandler.obtainMessage(MSG_JS_LOCATION_GO_TO_GPS_SETTING).sendToTarget();
    }

    @JavascriptInterface
    public void getLocation(String str) {
        new StringBuilder().append(getClass()).append("---getLocation(String json)---").append(Thread.currentThread().getId());
        if (str != null && !str.equals(com.umeng.a.d)) {
            new StringBuilder().append(getClass()).append("---getLocation(String json)---json!=null && !json.equals---").append(Thread.currentThread().getId());
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_LOCATION_GET_LOCATION);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void goBack() {
        new StringBuilder().append(getClass()).append("---goBack()---").append(Thread.currentThread().getId());
        this.mHandler.obtainMessage(MSG_JS_LOCATION_GO_BACK).sendToTarget();
    }

    @JavascriptInterface
    public void finishActivity() {
        new StringBuilder().append(getClass()).append("---finishActivity()---").append(Thread.currentThread().getId());
        this.mHandler.obtainMessage(MSG_JS_LOCATION_FINISH_ACTIVITY).sendToTarget();
    }

    @JavascriptInterface
    public String getCpuName() {
        return a.b();
    }

    @JavascriptInterface
    public String getRamTotalMemory() {
        return a.c();
    }
}
