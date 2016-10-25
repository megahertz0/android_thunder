package com.xunlei.downloadprovider.web.core;

import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;

public class JsKNAccelInterface extends JsLoactionInterface {
    public static final int MSG_JS_GOTO_KUAINIAO = 1224;
    public static final int MSG_JS_KUAINIAO_GET_ACCEL_INFO = 1220;
    public static final int MSG_JS_KUAINIAO_GET_ACCEL_STATUS = 1219;
    public static final int MSG_JS_KUAINIAO_GET_TRY_ACCEL_INFO = 1223;
    public static final int MSG_JS_KUAINIAO_START_ACCEL = 1221;
    public static final int MSG_JS_KUAINIAO_STOP_ACCEL = 1222;

    public JsKNAccelInterface(Handler handler, String str, String str2, String str3, String str4, String str5) {
        super(handler, str, str2, str3, str4, str5);
    }

    @JavascriptInterface
    public int getNetType() {
        int b = b.b(BrothersApplication.a());
        if (b == 1) {
            return 1;
        }
        return b == 0 ? XZBDevice.DOWNLOAD_LIST_RECYCLE : XZBDevice.DOWNLOAD_LIST_FAILED;
    }

    @JavascriptInterface
    public void userGetAccelStatus(String str) {
        if (str != null && !str.equals(a.d)) {
            Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_KUAINIAO_GET_ACCEL_STATUS);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }
    }

    @JavascriptInterface
    public void userGetAccelInfo() {
        this.mHandler.obtainMessage(MSG_JS_KUAINIAO_GET_ACCEL_INFO).sendToTarget();
    }

    @JavascriptInterface
    public void userStartAccel() {
        this.mHandler.obtainMessage(MSG_JS_KUAINIAO_START_ACCEL).sendToTarget();
    }

    @JavascriptInterface
    public void userStopAccel() {
        this.mHandler.obtainMessage(MSG_JS_KUAINIAO_STOP_ACCEL).sendToTarget();
    }

    @JavascriptInterface
    public void userGetTryAccelInfo() {
        this.mHandler.obtainMessage(MSG_JS_KUAINIAO_GET_TRY_ACCEL_INFO).sendToTarget();
    }

    @JavascriptInterface
    public int inFirstKAccel() {
        return BrothersApplication.a().getSharedPreferences("kuainiao", 0).getInt("centerdot", 0);
    }

    @JavascriptInterface
    public void gotoKuaiNiao() {
        this.mHandler.obtainMessage(MSG_JS_GOTO_KUAINIAO).sendToTarget();
    }
}
