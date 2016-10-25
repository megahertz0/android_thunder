package com.xunlei.common.stat.base;

// compiled from: XLLocker.java
public final class b {
    private Object a;

    public b() {
        this.a = null;
        this.a = new Object();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.stat.base.b.a(long):void");
        /*
        this = this;
        r2 = 0;
        r1 = r4.a;
        monitor-enter(r1);
        r0 = (r2 > r2 ? 1 : (r2 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0012;
    L_0x0009:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x0018 }
        r2 = 0;
        r0.wait(r2);	 Catch:{ InterruptedException -> 0x0018 }
    L_0x0010:
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        return;
    L_0x0012:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x0018 }
        r0.wait();	 Catch:{ InterruptedException -> 0x0018 }
        goto L_0x0010;
    L_0x0018:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x001d }
        goto L_0x0010;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        throw r0;
        */
    }

    public final void a() {
        synchronized (this.a) {
            this.a.notify();
        }
    }
}
