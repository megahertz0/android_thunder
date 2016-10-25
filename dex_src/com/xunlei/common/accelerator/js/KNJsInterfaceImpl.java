package com.xunlei.common.accelerator.js;

import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;

public class KNJsInterfaceImpl implements KNJsInterface {
    public static final int MSG_JS_KN_QUERY_STATUS = 0;
    public static final int MSG_JS_KN_RECOVER_QUERY = 3;
    public static final int MSG_JS_KN_START_ACCEL = 1;
    public static final int MSG_JS_KN_STOP_ACCEL = 2;
    private Handler mHandler;
    private KNJsInterface$JumpLisenter mJumpLisenter;

    public KNJsInterfaceImpl(KNJsInterface$JumpLisenter kNJsInterface$JumpLisenter, Handler handler) {
        this.mJumpLisenter = kNJsInterface$JumpLisenter;
        this.mHandler = handler;
    }

    @JavascriptInterface
    public void queryStatus(String str) {
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_KN_QUERY_STATUS);
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void startAccel(String str) {
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_KN_START_ACCEL);
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void stopAccel(String str) {
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_KN_STOP_ACCEL);
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public void recoverQuery(String str) {
        Message obtainMessage = this.mHandler.obtainMessage(MSG_JS_KN_RECOVER_QUERY);
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }

    @JavascriptInterface
    public String getBandInfo() {
        return KNJsUtils.getKnJsUtils().getBandInfo();
    }

    @JavascriptInterface
    public String getTryInfo() {
        return KNJsUtils.getKnJsUtils().getTryInfo();
    }

    @JavascriptInterface
    public String getUserInfo() {
        return KNJsUtils.getKnJsUtils().getUserInfo();
    }

    @JavascriptInterface
    public int getRemainTime() {
        return KNJsUtils.getKnJsUtils().getRemainTime();
    }

    @JavascriptInterface
    public void startLogin() {
        this.mJumpLisenter.jumpToLogin();
    }

    @JavascriptInterface
    public void startPay() {
        this.mJumpLisenter.jumpToPay();
    }
}
