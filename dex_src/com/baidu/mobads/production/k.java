package com.baidu.mobads.production;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;

class k extends Handler {
    final /* synthetic */ IXAdInstanceInfo a;
    final /* synthetic */ a b;

    k(a aVar, Looper looper, IXAdInstanceInfo iXAdInstanceInfo) {
        this.b = aVar;
        this.a = iXAdInstanceInfo;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (message.getData().getBoolean("caching_result")) {
            this.a.setLocalCreativeURL(message.getData().getString("local_creative_url"));
        } else {
            this.a.setLocalCreativeURL(null);
        }
    }
}
