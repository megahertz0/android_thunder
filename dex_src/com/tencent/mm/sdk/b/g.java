package com.tencent.mm.sdk.b;

import android.os.Debug;
import android.os.Handler;
import android.os.Process;

public final class g implements Runnable {
    private static final String bf;
    private static final String bg;
    final Runnable aQ;
    final String aR;
    final Object aS;
    final Thread aT;
    String aU;
    long aV;
    final a aW;
    long aX;
    long aY;
    long aZ;
    long ba;
    long bb;
    long bc;
    long bd;
    float be;
    final Handler handler;
    int priority;
    boolean started;

    public static interface a {
        void c(Runnable runnable, g gVar);
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("taskName = %s");
        stringBuilder.append("|token = %s");
        stringBuilder.append("|handler = %s");
        stringBuilder.append("|threadName = %s");
        stringBuilder.append("|threadId = %d");
        stringBuilder.append("|priority = %d");
        stringBuilder.append("|addTime = %d");
        stringBuilder.append("|delayTime = %d");
        stringBuilder.append("|usedTime = %d");
        stringBuilder.append("|cpuTime = %d");
        stringBuilder.append("|started = %b");
        bf = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("taskName = %s");
        stringBuilder.append(" | addTime = %s");
        stringBuilder.append(" | endTime = %s");
        stringBuilder.append(" | usedTime = %d");
        stringBuilder.append(" | cpuTime = %d");
        stringBuilder.append(" | threadCpuTime = %d");
        stringBuilder.append(" | totalCpuTime = %d");
        stringBuilder.append(" | threadCpuRate = %.1f");
        bg = stringBuilder.toString();
    }

    g(Thread thread, Handler handler, Runnable runnable, Object obj, a aVar) {
        this.started = false;
        this.be = -1.0f;
        this.aT = thread;
        if (thread != null) {
            this.aU = thread.getName();
            this.aV = thread.getId();
            this.priority = thread.getPriority();
        }
        this.handler = handler;
        this.aQ = runnable;
        String name = runnable.getClass().getName();
        String toString = runnable.toString();
        if (!h.h(toString)) {
            int indexOf = toString.indexOf(124);
            if (indexOf > 0) {
                name = name + "_" + toString.substring(indexOf + 1);
            }
        }
        this.aR = name;
        this.aS = obj;
        this.aW = aVar;
        this.aX = System.currentTimeMillis();
    }

    public final void run() {
        new StringBuilder("/proc/self/task/").append(Process.myTid()).append("/stat");
        this.ba = System.currentTimeMillis();
        this.bb = Debug.threadCpuTimeNanos();
        this.bc = -1;
        this.bd = -1;
        this.started = true;
        this.aQ.run();
        this.bc = -1 - this.bc;
        this.bd = -1 - this.bd;
        this.aZ = System.currentTimeMillis();
        this.ba = this.aZ - this.ba;
        this.bb = (Debug.threadCpuTimeNanos() - this.bb) / 1000000;
        if (this.bd != 0) {
            this.be = ((float) (100 * this.bc)) / ((float) this.bd);
        }
        if (this.aW != null) {
            this.aW.c(this.aQ, this);
        }
    }
}
