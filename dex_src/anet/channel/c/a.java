package anet.channel.c;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// compiled from: Taobao
class a implements Comparable<a>, Runnable, Future {
    Runnable a;
    int b;
    long c;
    volatile boolean d;
    volatile Future<?> e;

    public /* synthetic */ int compareTo(Object obj) {
        return a((a) obj);
    }

    public a(Runnable runnable, int i) {
        this.a = null;
        this.b = 0;
        this.c = System.currentTimeMillis();
        this.d = false;
        this.e = null;
        this.a = runnable;
        this.b = i;
        this.c = System.currentTimeMillis();
    }

    public int a(a aVar) {
        return this.b != aVar.b ? this.b - aVar.b : (int) (aVar.c - this.c);
    }

    public void run() {
        try {
            if (!this.d) {
                this.e = c.c().submit(this.a);
            }
        } catch (RejectedExecutionException e) {
            this.b++;
            c.a(this, (long) ((this.b + 1) * 500), TimeUnit.MILLISECONDS);
        }
    }

    public boolean cancel(boolean z) {
        this.d = true;
        return this.e != null ? this.e.cancel(z) : true;
    }

    public boolean isCancelled() {
        return this.d;
    }

    public boolean isDone() {
        return false;
    }

    public Object get() throws InterruptedException, ExecutionException {
        throw new RuntimeException("NOT SUPPORT!");
    }

    public Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new RuntimeException("NOT SUPPORT!");
    }
}
