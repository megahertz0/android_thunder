package com.xunlei.common.lixian;

import android.os.Handler;
import android.os.Message;

final class i extends Handler {
    private /* synthetic */ XLLixianUtil a;

    i(XLLixianUtil xLLixianUtil) {
    }

    public final void handleMessage(Message message) {
        if (message.what > 100000 && message.what < 200000) {
            XLLixianMessage xLLixianMessage = (XLLixianMessage) message.obj;
            XLLixianRequestBase xLLixianRequestBase = xLLixianMessage.mSender;
            if (xLLixianRequestBase != null) {
                xLLixianRequestBase.handleMessage(xLLixianMessage);
            }
        } else if (message.what != 10000 && message.what != 10001 && message.what != 10002) {
            int i = message.what;
        }
    }
}
