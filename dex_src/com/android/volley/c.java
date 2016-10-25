package com.android.volley;

import android.os.Process;
import com.android.volley.b.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.BlockingQueue;

// compiled from: CacheDispatcher.java
public final class c extends Thread {
    private static final boolean b;
    volatile boolean a;
    private final BlockingQueue<Request<?>> c;
    private final BlockingQueue<Request<?>> d;
    private final b e;
    private final s f;

    static {
        b = x.b;
    }

    public c(BlockingQueue<Request<?>> blockingQueue, BlockingQueue<Request<?>> blockingQueue2, b bVar, s sVar) {
        this.a = false;
        this.c = blockingQueue;
        this.d = blockingQueue2;
        this.e = bVar;
        this.f = sVar;
    }

    public final void run() {
        if (b) {
            x.a("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(XZBDevice.Stop);
        this.e.a();
        while (true) {
            try {
                Request request = (Request) this.c.take();
                request.addMarker("cache-queue-take");
                if (request.isCanceled()) {
                    request.finish("cache-discard-canceled");
                } else {
                    a a = this.e.a(request.getCacheKey());
                    if (a == null) {
                        request.addMarker("cache-miss");
                        this.d.put(request);
                    } else {
                        int i;
                        if (a.e < System.currentTimeMillis()) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i != 0) {
                            request.addMarker("cache-hit-expired");
                            request.setCacheEntry(a);
                            this.d.put(request);
                        } else {
                            request.addMarker("cache-hit");
                            r parseNetworkResponse = request.parseNetworkResponse(new l(a.a, a.g));
                            request.addMarker("cache-hit-parsed");
                            if (a.f < System.currentTimeMillis()) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (i == 0) {
                                this.f.a(request, parseNetworkResponse);
                            } else {
                                request.addMarker("cache-hit-refresh-needed");
                                request.setCacheEntry(a);
                                parseNetworkResponse.d = true;
                                this.f.a(request, parseNetworkResponse, new d(this, request));
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.a) {
                }
            }
        }
    }
}
