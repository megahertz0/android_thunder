package com.xunlei.downloadprovider.personal.playrecord.widget;

import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.downloadprovider.vod.b.b;
import java.util.Set;

// compiled from: PlayRecordListWidget.java
public final class i extends Thread {
    final /* synthetic */ PlayRecordListWidget a;

    public i(PlayRecordListWidget playRecordListWidget) {
        this.a = playRecordListWidget;
    }

    public final void run() {
        b a = b.a();
        Set t = PlayRecordListWidget.t(this.a);
        if (!(t == null || t.isEmpty())) {
            a.a.a(t);
        }
        PlayRecordListWidget.s(this.a).obtainMessage(XLPayErrorCode.XLP_GATE_PARAM_ERROR, 0, -1, PlayRecordListWidget.u(this.a)).sendToTarget();
    }
}
