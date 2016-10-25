package com.xunlei.common.yunbo;

import android.os.Handler;
import android.os.Message;

class XLYunboUtil$1 extends Handler {
    final /* synthetic */ XLYunboUtil this$0;

    XLYunboUtil$1(XLYunboUtil xLYunboUtil) {
        this.this$0 = xLYunboUtil;
    }

    public void handleMessage(Message message) {
        if (message.what > 1000000 && message.what < 2000000) {
            XLYunboMassage xLYunboMassage = (XLYunboMassage) message.obj;
            if (xLYunboMassage != null) {
                XLYunboRequestBase currentRequest = xLYunboMassage.getCurrentRequest();
                if (currentRequest != null) {
                    currentRequest.handleMessage(message);
                }
            }
        } else if (message.what != 10000 && message.what != 10001 && message.what != 10002) {
            int i = message.what;
        }
    }
}
