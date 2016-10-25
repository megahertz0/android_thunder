package anetwork.channel.entity;

import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.k;

// compiled from: Taobao
final class i implements Runnable {
    final /* synthetic */ DefaultFinishEvent a;
    final /* synthetic */ k b;
    final /* synthetic */ f c;

    i(f fVar, DefaultFinishEvent defaultFinishEvent, k kVar) {
        this.c = fVar;
        this.a = defaultFinishEvent;
        this.b = kVar;
    }

    public final void run() {
        if (ALog.isPrintLog(1)) {
            ALog.d("ANet.Repeater", new StringBuilder("[onFinish]on Finish waitTime:").append(System.currentTimeMillis() - this.c.a).toString(), this.c.b, new Object[0]);
        }
        this.c.a = System.currentTimeMillis();
        if (this.a != null) {
            this.a.a = null;
        }
        try {
            this.b.a(this.a);
            if (this.c.c != null) {
                this.c.c.e();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (ALog.isPrintLog(1)) {
            ALog.d("ANet.Repeater", new StringBuilder("[onFinish]on Finish process time:").append(System.currentTimeMillis() - this.c.a).toString(), this.c.b, new Object[0]);
        }
    }
}
