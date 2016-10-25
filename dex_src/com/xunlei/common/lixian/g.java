package com.xunlei.common.lixian;

import android.os.Handler;
import android.os.Message;

final class g extends Handler {
    private /* synthetic */ XLLixianRequestBase a;

    g(XLLixianRequestBase xLLixianRequestBase) {
        this.a = xLLixianRequestBase;
    }

    public final void handleMessage(Message message) {
        this.a.handleMessage((XLLixianMessage) message.obj);
    }
}
