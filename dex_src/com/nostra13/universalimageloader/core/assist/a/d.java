package com.nostra13.universalimageloader.core.assist.a;

import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// compiled from: LinkedBlockingDeque.java
public class d<E> extends AbstractQueue<E> implements a<E>, Serializable {
    transient c<E> a;
    transient c<E> b;
    final ReentrantLock c;
    private transient int d;
    private final int e;
    private final Condition f;
    private final Condition g;

    // compiled from: LinkedBlockingDeque.java
    private abstract class a implements Iterator<E> {
        c<E> a;
        E b;
        private c<E> d;

        abstract c<E> a();

        abstract c<E> a(c<E> cVar);

        a() {
            ReentrantLock reentrantLock = d.this;
            reentrantLock.lock();
            this.a = a();
            this.b = this.a == null ? null : this.a.a;
            reentrantLock.unlock();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void b() {
            throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.assist.a.d.a.b():void");
            /*
            this = this;
            r0 = 0;
            r1 = com.nostra13.universalimageloader.core.assist.a.d.this;
            r3 = com.nostra13.universalimageloader.core.assist.a.d.this;
            r3.lock();
            r1 = r5.a;	 Catch:{ all -> 0x0030 }
            r2 = r1;
        L_0x000b:
            r1 = r5.a(r2);	 Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x001e;
        L_0x0011:
            r1 = r0;
        L_0x0012:
            r5.a = r1;	 Catch:{ all -> 0x0030 }
            r1 = r5.a;	 Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x002b;
        L_0x0018:
            r5.b = r0;	 Catch:{ all -> 0x0030 }
            r3.unlock();
            return;
        L_0x001e:
            r4 = r1.a;	 Catch:{ all -> 0x0030 }
            if (r4 != 0) goto L_0x0012;
        L_0x0022:
            if (r1 != r2) goto L_0x0029;
        L_0x0024:
            r1 = r5.a();	 Catch:{ all -> 0x0030 }
            goto L_0x0012;
        L_0x0029:
            r2 = r1;
            goto L_0x000b;
        L_0x002b:
            r0 = r5.a;	 Catch:{ all -> 0x0030 }
            r0 = r0.a;	 Catch:{ all -> 0x0030 }
            goto L_0x0018;
        L_0x0030:
            r0 = move-exception;
            r3.unlock();
            throw r0;
            */
        }

        public boolean hasNext() {
            return this.a != null;
        }

        public E next() {
            if (this.a == null) {
                throw new NoSuchElementException();
            }
            this.d = this.a;
            E e = this.b;
            b();
            return e;
        }

        public void remove() {
            c cVar = this.d;
            if (cVar == null) {
                throw new IllegalStateException();
            }
            this.d = null;
            ReentrantLock reentrantLock = d.this;
            reentrantLock.lock();
            if (cVar.a != null) {
                d.this.a(cVar);
            }
            reentrantLock.unlock();
        }
    }

    // compiled from: LinkedBlockingDeque.java
    private class b extends a {
        private b() {
            super();
        }

        final c<E> a() {
            return d.this.a;
        }

        final c<E> a(c<E> cVar) {
            return cVar.c;
        }
    }

    // compiled from: LinkedBlockingDeque.java
    static final class c<E> {
        E a;
        c<E> b;
        c<E> c;

        c(E e) {
            this.a = e;
        }
    }

    public d() {
        this((byte) 0);
    }

    private d(byte b) {
        this.c = new ReentrantLock();
        this.f = this.c.newCondition();
        this.g = this.c.newCondition();
        this.e = Integer.MAX_VALUE;
    }

    private boolean b(c<E> cVar) {
        if (this.d >= this.e) {
            return false;
        }
        c cVar2 = this.b;
        cVar.b = cVar2;
        this.b = cVar;
        if (this.a == null) {
            this.a = cVar;
        } else {
            cVar2.c = cVar;
        }
        this.d++;
        this.f.signal();
        return true;
    }

    private E b() {
        c cVar = this.a;
        if (cVar == null) {
            return null;
        }
        c cVar2 = cVar.c;
        E e = cVar.a;
        cVar.a = null;
        cVar.c = cVar;
        this.a = cVar2;
        if (cVar2 == null) {
            this.b = null;
        } else {
            cVar2.b = null;
        }
        this.d--;
        this.g.signal();
        return e;
    }

    final void a(c<E> cVar) {
        c cVar2 = cVar.b;
        c cVar3 = cVar.c;
        if (cVar2 == null) {
            b();
        } else if (cVar3 == null) {
            cVar2 = this.b;
            if (cVar2 != null) {
                cVar3 = cVar2.b;
                cVar2.a = null;
                cVar2.b = cVar2;
                this.b = cVar3;
                if (cVar3 == null) {
                    this.a = null;
                } else {
                    cVar3.c = null;
                }
                this.d--;
                this.g.signal();
            }
        } else {
            cVar2.c = cVar3;
            cVar3.b = cVar2;
            cVar.a = null;
            this.d--;
            this.g.signal();
        }
    }

    public final boolean a(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        boolean z;
        c cVar = new c(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        if (this.d >= this.e) {
            z = false;
        } else {
            c cVar2 = this.a;
            cVar.c = cVar2;
            this.a = cVar;
            if (this.b == null) {
                this.b = cVar;
            } else {
                cVar2.b = cVar;
            }
            this.d++;
            this.f.signal();
            z = true;
        }
        reentrantLock.unlock();
        return z;
    }

    private boolean b(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        c cVar = new c(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        boolean b = b(cVar);
        reentrantLock.unlock();
        return b;
    }

    private boolean a(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        c cVar = new c(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        while (!b(cVar)) {
            if (toNanos <= 0) {
                reentrantLock.unlock();
                return false;
            }
            toNanos = this.g.awaitNanos(toNanos);
        }
        reentrantLock.unlock();
        return true;
    }

    public final E a() {
        E c = c();
        if (c != null) {
            return c;
        }
        throw new NoSuchElementException();
    }

    private E c() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        E b = b();
        reentrantLock.unlock();
        return b;
    }

    private E d() throws InterruptedException {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (true) {
            E b = b();
            if (b == null) {
                this.f.await();
            } else {
                reentrantLock.unlock();
                return b;
            }
        }
    }

    private E a(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            E b = b();
            if (b != null) {
                reentrantLock.unlock();
                return b;
            } else if (j2 <= 0) {
                reentrantLock.unlock();
                return null;
            } else {
                j2 = this.f.awaitNanos(j2);
            }
        }
    }

    private E e() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        E e = this.a == null ? null : this.a.a;
        reentrantLock.unlock();
        return e;
    }

    private boolean c(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        for (c cVar = this.a; cVar != null; cVar = cVar.c) {
            if (obj.equals(cVar.a)) {
                a(cVar);
                reentrantLock.unlock();
                return true;
            }
        }
        reentrantLock.unlock();
        return false;
    }

    public boolean offer(E e) {
        return b((Object) e);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        return a(e, j, timeUnit);
    }

    public E remove() {
        return a();
    }

    public E poll() {
        return c();
    }

    public E take() throws InterruptedException {
        return d();
    }

    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        return a(j, timeUnit);
    }

    public E peek() {
        return e();
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        int i = this.e - this.d;
        reentrantLock.unlock();
        return i;
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, InMobiClientPositioning.NO_REPEAT);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            int min = Math.min(i, this.d);
            for (int i2 = 0; i2 < min; i2++) {
                collection.add(this.a.a);
                b();
            }
            reentrantLock.unlock();
            return min;
        }
    }

    public boolean remove(Object obj) {
        return c(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        int i = this.d;
        reentrantLock.unlock();
        return i;
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        for (c cVar = this.a; cVar != null; cVar = cVar.c) {
            if (obj.equals(cVar.a)) {
                reentrantLock.unlock();
                return true;
            }
        }
        reentrantLock.unlock();
        return false;
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        Object[] objArr = new Object[this.d];
        int i = 0;
        c cVar = this.a;
        while (cVar != null) {
            int i2 = i + 1;
            objArr[i] = cVar.a;
            cVar = cVar.c;
            i = i2;
        }
        reentrantLock.unlock();
        return objArr;
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        if (tArr.length < this.d) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.d);
        }
        int i = 0;
        c cVar = this.a;
        while (cVar != null) {
            int i2 = i + 1;
            tArr[i] = cVar.a;
            cVar = cVar.c;
            i = i2;
        }
        if (tArr.length > i) {
            tArr[i] = null;
        }
        reentrantLock.unlock();
        return tArr;
    }

    public String toString() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        c cVar = this.a;
        if (cVar == null) {
            String str = "[]";
            reentrantLock.unlock();
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        c cVar2 = cVar;
        while (true) {
            Object obj;
            d dVar = cVar2.a;
            if (dVar == this) {
                obj = "(this Collection)";
            }
            stringBuilder.append(obj);
            cVar = cVar2.c;
            if (cVar == null) {
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
                return str;
            }
            stringBuilder.append(',').append(' ');
            cVar2 = cVar;
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        c cVar = this.a;
        while (cVar != null) {
            cVar.a = null;
            c cVar2 = cVar.c;
            cVar.b = null;
            cVar.c = null;
            cVar = cVar2;
        }
        this.b = null;
        this.a = null;
        this.d = 0;
        this.g.signalAll();
        reentrantLock.unlock();
    }

    public Iterator<E> iterator() {
        return new b();
    }

    public boolean add(E e) {
        if (b((Object) e)) {
            return true;
        }
        throw new IllegalStateException("Deque full");
    }

    public void put(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        c cVar = new c(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (!b(cVar)) {
            this.g.await();
        }
        reentrantLock.unlock();
    }

    public E element() {
        E e = e();
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }
}
