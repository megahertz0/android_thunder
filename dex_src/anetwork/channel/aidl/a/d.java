package anetwork.channel.aidl.a;

import android.os.RemoteException;
import anet.channel.util.ALog;
import anetwork.channel.aidl.j.a;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// compiled from: Taobao
public final class d extends a {
    private static final anet.channel.a.a e;
    public int a;
    public int b;
    final ReentrantLock c;
    final Condition d;
    private final AtomicBoolean f;
    private LinkedList<anet.channel.a.a> g;
    private int h;
    private int i;
    private int j;

    public d() {
        this.f = new AtomicBoolean(false);
        this.g = new LinkedList();
        this.b = 10000;
        this.j = 0;
        this.c = new ReentrantLock();
        this.d = this.c.newCondition();
    }

    static {
        e = anet.channel.a.a.a(0);
    }

    public final void a(anet.channel.a.a aVar) {
        if (!this.f.get()) {
            this.c.lock();
            this.g.add(aVar);
            this.j += aVar.c();
            this.d.signal();
            this.c.unlock();
        }
    }

    public final void e() {
        a(e);
        if (ALog.isPrintLog(1)) {
            ALog.d("anet.ParcelableInputStreamImpl", "set EOS flag to stream", null, new Object[0]);
        }
        if (this.a != 0 && this.a != this.j) {
            ALog.e("anet.ParcelableInputStreamImpl", "data length no match!", null, "ContentLength", Integer.valueOf(this.a), "Received", Integer.valueOf(this.j));
        }
    }

    private void f() {
        this.c.lock();
        ((anet.channel.a.a) this.g.set(this.h, e)).d();
        this.c.unlock();
    }

    public final int a() throws RemoteException {
        if (this.f.get()) {
            throw new RuntimeException("Stream is closed");
        }
        this.c.lock();
        if (this.h == this.g.size()) {
            this.c.unlock();
            return 0;
        }
        ListIterator listIterator = this.g.listIterator(this.h);
        int i = 0;
        while (listIterator.hasNext()) {
            i = ((anet.channel.a.a) listIterator.next()).c() + i;
        }
        int i2 = i - this.i;
        this.c.unlock();
        return i2;
    }

    public final void b() throws RemoteException {
        if (this.f.compareAndSet(false, true)) {
            this.c.lock();
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                anet.channel.a.a aVar = (anet.channel.a.a) it.next();
                if (aVar != e) {
                    aVar.d();
                }
            }
            this.g.clear();
            this.g = null;
            this.h = -1;
            this.i = -1;
            this.a = 0;
            this.c.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int c() throws android.os.RemoteException {
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.aidl.a.d.c():int");
        /*
        this = this;
        r0 = r4.f;
        r0 = r0.get();
        if (r0 == 0) goto L_0x0011;
    L_0x0008:
        r0 = new java.lang.RuntimeException;
        r1 = "Stream is closed";
        r0.<init>(r1);
        throw r0;
    L_0x0011:
        r0 = r4.c;
        r0.lock();
    L_0x0016:
        r0 = r4.h;	 Catch:{ InterruptedException -> 0x0039 }
        r1 = r4.g;	 Catch:{ InterruptedException -> 0x0039 }
        r1 = r1.size();	 Catch:{ InterruptedException -> 0x0039 }
        if (r0 != r1) goto L_0x004d;
    L_0x0020:
        r0 = r4.d;	 Catch:{ InterruptedException -> 0x0039 }
        r1 = r4.b;	 Catch:{ InterruptedException -> 0x0039 }
        r2 = (long) r1;	 Catch:{ InterruptedException -> 0x0039 }
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x0039 }
        r0 = r0.await(r2, r1);	 Catch:{ InterruptedException -> 0x0039 }
        if (r0 != 0) goto L_0x004d;
    L_0x002d:
        r4.b();	 Catch:{ InterruptedException -> 0x0039 }
        r0 = new java.lang.RuntimeException;	 Catch:{ InterruptedException -> 0x0039 }
        r1 = "await timeout.";
        r0.<init>(r1);	 Catch:{ InterruptedException -> 0x0039 }
        throw r0;	 Catch:{ InterruptedException -> 0x0039 }
    L_0x0039:
        r0 = move-exception;
        r4.b();	 Catch:{ all -> 0x0046 }
        r0 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0046 }
        r1 = "await interrupt";
        r0.<init>(r1);	 Catch:{ all -> 0x0046 }
        throw r0;	 Catch:{ all -> 0x0046 }
    L_0x0046:
        r0 = move-exception;
        r1 = r4.c;
        r1.unlock();
        throw r0;
    L_0x004d:
        r0 = r4.g;	 Catch:{ InterruptedException -> 0x0039 }
        r1 = r4.h;	 Catch:{ InterruptedException -> 0x0039 }
        r0 = r0.get(r1);	 Catch:{ InterruptedException -> 0x0039 }
        r0 = (anet.channel.a.a) r0;	 Catch:{ InterruptedException -> 0x0039 }
        r1 = e;	 Catch:{ InterruptedException -> 0x0039 }
        if (r0 != r1) goto L_0x0062;
    L_0x005b:
        r0 = -1;
    L_0x005c:
        r1 = r4.c;
        r1.unlock();
        return r0;
    L_0x0062:
        r1 = r4.i;	 Catch:{ InterruptedException -> 0x0039 }
        r2 = r0.c();	 Catch:{ InterruptedException -> 0x0039 }
        r2 = r2 + -1;
        if (r1 >= r2) goto L_0x007b;
    L_0x006c:
        r1 = r4.i;	 Catch:{ InterruptedException -> 0x0039 }
        r1 = r1 + 1;
        r4.i = r1;	 Catch:{ InterruptedException -> 0x0039 }
        r0 = r0.a();	 Catch:{ InterruptedException -> 0x0039 }
        r1 = r4.i;	 Catch:{ InterruptedException -> 0x0039 }
        r0 = r0[r1];	 Catch:{ InterruptedException -> 0x0039 }
        goto L_0x005c;
    L_0x007b:
        r4.f();	 Catch:{ InterruptedException -> 0x0039 }
        r0 = r4.h;	 Catch:{ InterruptedException -> 0x0039 }
        r0 = r0 + 1;
        r4.h = r0;	 Catch:{ InterruptedException -> 0x0039 }
        r0 = 0;
        r4.i = r0;	 Catch:{ InterruptedException -> 0x0039 }
        goto L_0x0016;
        */
    }

    public final int a(byte[] bArr) throws RemoteException {
        int i = 0;
        if (this.f.get()) {
            throw new RuntimeException("Stream is closed");
        }
        this.c.lock();
        while (i < bArr.length) {
            try {
                if (this.h != this.g.size() || this.d.await((long) this.b, TimeUnit.MILLISECONDS)) {
                    anet.channel.a.a aVar = (anet.channel.a.a) this.g.get(this.h);
                    if (aVar == e) {
                        break;
                    }
                    int c = aVar.c() - this.i;
                    int length = bArr.length - i;
                    if (c <= length) {
                        System.arraycopy(aVar.a(), this.i, bArr, i, c);
                        i += c;
                        f();
                        this.h++;
                        this.i = 0;
                    } else {
                        System.arraycopy(aVar.a(), this.i, bArr, i, length);
                        this.i += length;
                        i += length;
                    }
                } else {
                    b();
                    throw new RuntimeException("await timeout.");
                }
            } catch (InterruptedException e) {
                b();
                throw new RuntimeException("await interrupt");
            } catch (Throwable th) {
            }
        }
        this.c.unlock();
        return i > 0 ? i : -1;
    }

    public final long a(int i) throws RemoteException {
        int c;
        int i2 = 0;
        this.c.lock();
        while (i2 < i) {
            if (this.h == this.g.size()) {
                break;
            }
            anet.channel.a.a aVar = (anet.channel.a.a) this.g.get(this.h);
            if (aVar == e) {
                break;
            }
            c = aVar.c();
            if (c - this.i < i - i2) {
                c = (c - this.i) + i2;
                f();
                this.h++;
                this.i = 0;
                break;
            }
            this.i += i - i;
            i2 = i;
        }
        c = i2;
        this.c.unlock();
        return (long) c;
    }

    public final int d() throws RemoteException {
        return this.a;
    }
}
