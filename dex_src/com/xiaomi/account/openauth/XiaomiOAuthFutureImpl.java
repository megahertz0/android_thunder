package com.xiaomi.account.openauth;

import android.accounts.OperationCanceledException;
import android.os.Looper;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class XiaomiOAuthFutureImpl<V> extends FutureTask<V> implements XiaomiOAuthFuture<V> {
    private static final long DEFAULT_TIMEOUT_MINUTE = 10;

    private void ensureNotOnMainThread() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == Looper.getMainLooper()) {
            throw new IllegalStateException("calling this from your main thread can lead to deadlock");
        }
    }

    XiaomiOAuthFutureImpl() {
        super(new Callable<V>() {
            public V call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        });
    }

    public V getResult() throws IOException, OperationCanceledException, XMAuthericationException {
        return internalGetResult(Long.valueOf(DEFAULT_TIMEOUT_MINUTE), TimeUnit.MINUTES);
    }

    public V getResult(long j, TimeUnit timeUnit) throws IOException, OperationCanceledException, XMAuthericationException {
        return internalGetResult(Long.valueOf(j), timeUnit);
    }

    public void setException(Throwable th) {
        super.setException(th);
    }

    public void set(V v) {
        super.set(v);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private V internalGetResult(java.lang.Long r4, java.util.concurrent.TimeUnit r5) throws android.accounts.OperationCanceledException, java.io.IOException, com.xiaomi.account.openauth.XMAuthericationException {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.openauth.XiaomiOAuthFutureImpl.internalGetResult(java.lang.Long, java.util.concurrent.TimeUnit):V");
        /*
        this = this;
        r2 = 1;
        r0 = r3.isDone();
        if (r0 != 0) goto L_0x000a;
    L_0x0007:
        r3.ensureNotOnMainThread();
    L_0x000a:
        if (r4 != 0) goto L_0x0014;
    L_0x000c:
        r0 = r3.get();	 Catch:{ CancellationException -> 0x0020, TimeoutException -> 0x002c, InterruptedException -> 0x0036, ExecutionException -> 0x003b }
        r3.cancel(r2);
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = r4.longValue();	 Catch:{ CancellationException -> 0x0020, TimeoutException -> 0x002c, InterruptedException -> 0x0036, ExecutionException -> 0x003b }
        r0 = r3.get(r0, r5);	 Catch:{ CancellationException -> 0x0020, TimeoutException -> 0x002c, InterruptedException -> 0x0036, ExecutionException -> 0x003b }
        r3.cancel(r2);
        goto L_0x0013;
    L_0x0020:
        r0 = move-exception;
        r0 = new android.accounts.OperationCanceledException;	 Catch:{ all -> 0x0027 }
        r0.<init>();	 Catch:{ all -> 0x0027 }
        throw r0;	 Catch:{ all -> 0x0027 }
    L_0x0027:
        r0 = move-exception;
        r3.cancel(r2);
        throw r0;
    L_0x002c:
        r0 = move-exception;
        r3.cancel(r2);
    L_0x0030:
        r0 = new android.accounts.OperationCanceledException;
        r0.<init>();
        throw r0;
    L_0x0036:
        r0 = move-exception;
        r3.cancel(r2);
        goto L_0x0030;
    L_0x003b:
        r0 = move-exception;
        r0 = r0.getCause();	 Catch:{ all -> 0x0027 }
        r1 = r0 instanceof java.io.IOException;	 Catch:{ all -> 0x0027 }
        if (r1 == 0) goto L_0x0047;
    L_0x0044:
        r0 = (java.io.IOException) r0;	 Catch:{ all -> 0x0027 }
        throw r0;	 Catch:{ all -> 0x0027 }
    L_0x0047:
        r1 = r0 instanceof java.lang.RuntimeException;	 Catch:{ all -> 0x0027 }
        if (r1 == 0) goto L_0x004e;
    L_0x004b:
        r0 = (java.lang.RuntimeException) r0;	 Catch:{ all -> 0x0027 }
        throw r0;	 Catch:{ all -> 0x0027 }
    L_0x004e:
        r1 = r0 instanceof java.lang.Error;	 Catch:{ all -> 0x0027 }
        if (r1 == 0) goto L_0x0055;
    L_0x0052:
        r0 = (java.lang.Error) r0;	 Catch:{ all -> 0x0027 }
        throw r0;	 Catch:{ all -> 0x0027 }
    L_0x0055:
        r1 = r0 instanceof com.xiaomi.account.openauth.XMAuthericationException;	 Catch:{ all -> 0x0027 }
        if (r1 == 0) goto L_0x005c;
    L_0x0059:
        r0 = (com.xiaomi.account.openauth.XMAuthericationException) r0;	 Catch:{ all -> 0x0027 }
        throw r0;	 Catch:{ all -> 0x0027 }
    L_0x005c:
        r1 = r0 instanceof android.accounts.OperationCanceledException;	 Catch:{ all -> 0x0027 }
        if (r1 == 0) goto L_0x0063;
    L_0x0060:
        r0 = (android.accounts.OperationCanceledException) r0;	 Catch:{ all -> 0x0027 }
        throw r0;	 Catch:{ all -> 0x0027 }
    L_0x0063:
        r1 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0027 }
        r1.<init>(r0);	 Catch:{ all -> 0x0027 }
        throw r1;	 Catch:{ all -> 0x0027 }
        */
    }
}
