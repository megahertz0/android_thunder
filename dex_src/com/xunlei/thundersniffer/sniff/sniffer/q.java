package com.xunlei.thundersniffer.sniff.sniffer;

import java.util.TimerTask;

final class q extends TimerTask {
    final /* synthetic */ String a;
    final /* synthetic */ b b;
    final /* synthetic */ long c;
    final /* synthetic */ SniffingDetailPageTask d;

    q(SniffingDetailPageTask sniffingDetailPageTask, String str, b bVar, long j) {
        this.d = sniffingDetailPageTask;
        this.a = str;
        this.b = bVar;
        this.c = j;
    }

    public final void run() {
        new StringBuilder("onTimeOut url = ").append(this.a);
        this.d.x = true;
        SniffingDetailPageTask.b(this.d);
        if (!this.d.w && this.b != null) {
            new StringBuilder("spend_time_3 --> ").append(System.currentTimeMillis() - this.c);
            this.b.a(this.a, null, false);
        }
    }
}
