package com.xunlei.common.accelerator.js;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebView;
import com.xunlei.common.accelerator.XLAccelUtil;
import com.xunlei.common.accelerator.XLAccelerator;
import com.xunlei.common.accelerator.js.KNJsInterface.JumpLisenter;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class KNJsProxy {
    private static final String JS_INTERFACE_NAME = "share";
    private static KNJsProxy mKnJsProxy;
    private XLAccelerator mAccelerator;
    private final Handler mHandler;
    private WebView mWebView;
    public XLOnAccelListenerImpl xLOnAccelListenerImpl;

    private KNJsProxy() {
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                        KNJsProxy.this.xLOnAccelListenerImpl.setCallBackName((String) message.obj);
                        KNJsProxy.this.mAccelerator.queryStatus();
                    case SimpleLog.LOG_LEVEL_TRACE:
                        KNJsProxy.this.xLOnAccelListenerImpl.setCallBackName((String) message.obj);
                        KNJsProxy.this.mAccelerator.startAccel();
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        KNJsProxy.this.xLOnAccelListenerImpl.setCallBackName((String) message.obj);
                        KNJsProxy.this.mAccelerator.stopAccel();
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        KNJsProxy.this.xLOnAccelListenerImpl.setCallBackName((String) message.obj);
                        KNJsProxy.this.mAccelerator.recoverQuery();
                    default:
                        break;
                }
            }
        };
        this.mAccelerator = XLAccelUtil.getAccelerator();
    }

    public static KNJsProxy getKNJsProxy() {
        if (mKnJsProxy == null) {
            synchronized (KNJsProxy.class) {
                if (mKnJsProxy == null) {
                    mKnJsProxy = new KNJsProxy();
                }
            }
        }
        return mKnJsProxy;
    }

    public boolean initJsInterface(WebView webView, JumpLisenter jumpLisenter) {
        if (webView == null || jumpLisenter == null) {
            return false;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mWebView = webView;
        webView.addJavascriptInterface(new KNJsInterfaceImpl(jumpLisenter, this.mHandler), JS_INTERFACE_NAME);
        this.xLOnAccelListenerImpl = new XLOnAccelListenerImpl(webView);
        this.mAccelerator.attachListener(this.xLOnAccelListenerImpl);
        return true;
    }

    public boolean uninitJsInterface() {
        if (this.mWebView == null || this.xLOnAccelListenerImpl == null) {
            return false;
        }
        if (VERSION.SDK_INT >= 11) {
            this.mWebView.removeJavascriptInterface(JS_INTERFACE_NAME);
        } else {
            this.mWebView.addJavascriptInterface(null, BuildConfig.VERSION_NAME);
        }
        this.mWebView.loadUrl(BuildConfig.VERSION_NAME);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mAccelerator.detachListener(this.xLOnAccelListenerImpl);
        this.mWebView = null;
        return true;
    }

    public String getBandInfo() {
        String bandInfo = this.mAccelerator.getBandInfo();
        return TextUtils.isEmpty(bandInfo) ? BuildConfig.VERSION_NAME : bandInfo;
    }

    public String getTryInfo() {
        String tryInfo = this.mAccelerator.getTryInfo();
        return TextUtils.isEmpty(tryInfo) ? BuildConfig.VERSION_NAME : tryInfo;
    }

    public String getUserInfo() {
        String userInfo = this.mAccelerator.getUserInfo();
        return TextUtils.isEmpty(userInfo) ? BuildConfig.VERSION_NAME : userInfo;
    }

    public int getRemainTime() {
        return this.mAccelerator.getRemainTime();
    }

    public void updateUserInfo() {
        this.mAccelerator.updateUserInfo();
    }
}
