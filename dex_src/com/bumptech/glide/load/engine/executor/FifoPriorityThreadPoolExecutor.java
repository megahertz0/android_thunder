package com.bumptech.glide.load.engine.executor;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class FifoPriorityThreadPoolExecutor extends ThreadPoolExecutor {
    private final AtomicInteger a;
    private final UncaughtThrowableStrategy b;

    public enum UncaughtThrowableStrategy {
        IGNORE;

        static {
            IGNORE = new com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.UncaughtThrowableStrategy("IGNORE", 0);
            LOG = new b("LOG");
            THROW = new c("THROW");
            a = new com.bumptech.glide.load.engine.executor.FifoPriorityThreadPoolExecutor.UncaughtThrowableStrategy[]{IGNORE, LOG, THROW};
        }

        protected void handle(Throwable th) {
        }
    }

    public static class a implements ThreadFactory {
        int a;

        public a() {
            this.a = 0;
        }

        public final Thread newThread(Runnable runnable) {
            Thread aVar = new a(this, runnable, new StringBuilder("fifo-pool-thread-").append(this.a).toString());
            this.a++;
            return aVar;
        }
    }

    static class b<T> extends FutureTask<T> implements Comparable<b<?>> {
        private final int a;
        private final int b;

        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            b bVar = (b) obj;
            int i = this.a - bVar.a;
            return i == 0 ? this.b - bVar.b : i;
        }

        public b(Runnable runnable, T t, int i) {
            super(runnable, t);
            if (runnable instanceof d) {
                this.a = ((d) runnable).a();
                this.b = i;
                return;
            }
            throw new IllegalArgumentException("FifoPriorityThreadPoolExecutor must be given Runnables that implement Prioritized");
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.b == bVar.b && this.a == bVar.a;
        }

        public final int hashCode() {
            return (this.a * 31) + this.b;
        }
    }

    public FifoPriorityThreadPoolExecutor(int i) {
        this(i, UncaughtThrowableStrategy.LOG);
    }

    private FifoPriorityThreadPoolExecutor(int i, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        this(i, i, TimeUnit.MILLISECONDS, new a(), uncaughtThrowableStrategy);
    }

    private FifoPriorityThreadPoolExecutor(int i, int i2, TimeUnit timeUnit, ThreadFactory threadFactory, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        super(i, i2, 0, timeUnit, new PriorityBlockingQueue(), threadFactory);
        this.a = new AtomicInteger();
        this.b = uncaughtThrowableStrategy;
    }

    protected final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new b(runnable, t, this.a.getAndIncrement());
    }

    protected final void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            Future future = (Future) runnable;
            if (future.isDone() && !future.isCancelled()) {
                try {
                    future.get();
                } catch (Throwable e) {
                    this.b.handle(e);
                } catch (Throwable e2) {
                    this.b.handle(e2);
                }
            }
        }
    }
}
