package com.mediav.ads.sdk.adcore;

import android.os.Message;
import com.alipay.sdk.authjs.a;
import com.mediav.ads.sdk.adcore.HttpRequester.Listener;
import com.umeng.socialize.common.SocializeConstants;
import java.util.HashMap;

// compiled from: HttpRequester.java
class ResultRunable implements Runnable {
    private static final String UNKOWN_ERROR = "unkown error";
    private Message msg;

    public ResultRunable(Message message) {
        this.msg = null;
        this.msg = message;
    }

    public void run() {
        try {
            HashMap hashMap = (HashMap) this.msg.obj;
            Listener listener = (Listener) hashMap.get(a.c);
            if (this.msg.what == 0) {
                listener.onGetDataSucceed((byte[]) hashMap.get(SocializeConstants.JSON_DATA));
            } else if (this.msg.what == 1) {
                listener.onGetDataFailed((String) hashMap.get("error"));
            } else if (this.msg.what == 2) {
                listener.onGetDataFailed((String) hashMap.get("error"));
            } else {
                listener.onGetDataFailed(UNKOWN_ERROR);
            }
        } catch (Exception e) {
            ((Listener) ((HashMap) this.msg.obj).get(a.c)).onGetDataFailed(new StringBuilder("HttpRequester get data error:").append(e.getMessage()).toString());
        }
    }
}
