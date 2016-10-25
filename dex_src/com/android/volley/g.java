package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

// compiled from: ExecutorDelivery.java
public final class g implements s {
    private final Executor a;

    // compiled from: ExecutorDelivery.java
    private class a implements Runnable {
        private final Request b;
        private final r c;
        private final Runnable d;

        public a(Request request, r rVar, Runnable runnable) {
            this.b = request;
            this.c = rVar;
            this.d = runnable;
        }

        public final void run() {
            if (this.b.isCanceled()) {
                this.b.finish("canceled-at-delivery");
                return;
            }
            Object obj;
            if (this.c.c == null) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                this.b.deliverResponse(g.this);
            } else {
                this.b.deliverError(this.c.c);
            }
            if (this.c.d) {
                this.b.addMarker("intermediate-response");
            } else {
                this.b.finish("done");
            }
            if (this.d != null) {
                this.d.run();
            }
        }
    }

    public g(Handler handler) {
        this.a = new h(this, handler);
    }

    public g(Executor executor) {
        this.a = executor;
    }

    public final void a(Request<?> request, r<?> rVar) {
        a(request, rVar, null);
    }

    public final void a(Request<?> request, r<?> rVar, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        this.a.execute(new a(request, rVar, runnable));
    }

    public final void a(Request<?> request, w wVar) {
        request.addMarker("post-error");
        this.a.execute(new a(request, r.a(wVar), null));
    }
}
