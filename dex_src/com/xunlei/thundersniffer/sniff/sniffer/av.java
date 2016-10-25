package com.xunlei.thundersniffer.sniff.sniffer;

final class av implements SniffingTask$TaskStateChangeListener {
    final /* synthetic */ au a;

    av(au auVar) {
        this.a = auVar;
    }

    public final void OnTaskStateChange(SniffingTask sniffingTask, int i, int i2) {
        if (sniffingTask.c() == SniffingTask.k) {
            au auVar = this.a;
            synchronized (auVar) {
                auVar.b--;
            }
            synchronized (auVar.a) {
                if (auVar.g != null) {
                    auVar.g.remove(sniffingTask);
                }
            }
            Object obj = null;
            synchronized (this.a.a) {
                au auVar2 = this.a;
                auVar2.h--;
                if (this.a.h <= 0) {
                    obj = 1;
                }
            }
            this.a.b();
            if (this.a.c != null) {
                this.a.c.a(sniffingTask);
            }
            if (obj != null && this.a.c != null) {
                this.a.c.a();
            }
        } else if (sniffingTask.c() == SniffingTask.h) {
            aw.a().a(sniffingTask);
        }
    }
}
