package com.xunlei.downloadprovider.personal.playrecord.widget;

import com.xunlei.downloadprovider.vod.b.b;
import com.xunlei.tdlive.im.ChatMessage;

// compiled from: PlayRecordListWidget.java
final class h extends Thread {
    final /* synthetic */ long a;
    final /* synthetic */ int b;
    final /* synthetic */ PlayRecordListWidget c;

    h(PlayRecordListWidget playRecordListWidget, long j) {
        this.c = playRecordListWidget;
        this.a = j;
        this.b = 20;
    }

    public final void run() {
        b a = b.a();
        PlayRecordListWidget.s(this.c).obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, a.a.a(this.a, this.b)).sendToTarget();
    }
}
