package com.android.volley;

import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.BlockingQueue;

// compiled from: NetworkDispatcher.java
public final class j extends Thread {
    volatile boolean a;
    private final BlockingQueue<Request<?>> b;
    private final i c;
    private final b d;
    private final s e;

    public j(BlockingQueue<Request<?>> blockingQueue, i iVar, b bVar, s sVar) {
        this.a = false;
        this.b = blockingQueue;
        this.c = iVar;
        this.d = bVar;
        this.e = sVar;
    }

    public final void run() {
        Process.setThreadPriority(XZBDevice.Stop);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                Request request = (Request) this.b.take();
                try {
                    request.addMarker("network-queue-take");
                    if (request.isCanceled()) {
                        request.finish("network-discard-cancelled");
                    } else {
                        if (VERSION.SDK_INT >= 14) {
                            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
                        }
                        l a = this.c.a(request);
                        request.addMarker("network-http-complete");
                        if (a.d && request.hasHadResponseDelivered()) {
                            request.finish("not-modified");
                        } else {
                            r parseNetworkResponse = request.parseNetworkResponse(a);
                            request.addMarker("network-parse-complete");
                            if (request.shouldCache() && parseNetworkResponse.b != null) {
                                this.d.a(request.getCacheKey(), parseNetworkResponse.b);
                                request.addMarker("network-cache-written");
                            }
                            request.markDelivered();
                            this.e.a(request, parseNetworkResponse);
                        }
                    }
                } catch (w e) {
                    e.b = SystemClock.elapsedRealtime() - elapsedRealtime;
                    this.e.a(request, request.parseNetworkError(e));
                } catch (Throwable e2) {
                    x.d("Unhandled exception %s", e2.toString());
                    w wVar = new w(e2);
                    wVar.b = SystemClock.elapsedRealtime() - elapsedRealtime;
                    this.e.a(request, wVar);
                }
            } catch (InterruptedException e3) {
                if (this.a) {
                }
            }
        }
    }
}
