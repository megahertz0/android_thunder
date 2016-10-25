package anet.channel.c;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
class b extends ThreadPoolExecutor {

    // compiled from: Taobao
    class a<V> extends FutureTask<V> implements Comparable<a<V>> {
        private Object b;

        public /* synthetic */ int compareTo(Object obj) {
            return a((a) obj);
        }

        public a(Callable<V> callable) {
            super(callable);
            this.b = callable;
        }

        public a(Runnable runnable, V v) {
            super(runnable, v);
            this.b = runnable;
        }

        public int a(a<V> aVar) {
            if (this == aVar) {
                return 0;
            }
            if (aVar == null) {
                return -1;
            }
            return (this.b == null || aVar.b == null || !this.b.getClass().equals(aVar.b.getClass()) || !(this.b instanceof Comparable)) ? 0 : ((Comparable) this.b).compareTo(aVar.b);
        }
    }

    public b(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory);
    }

    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new a(runnable, t);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new a(callable);
    }
}
