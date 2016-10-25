package anet.channel.a;

import anet.channel.util.ALog;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

// compiled from: Taobao
public class b {
    public static final int MAX_POOL_SIZE = 524288;
    public static final String TAG = "awcn.ByteArrayPool";
    private final TreeSet<a> a;
    private final a b;
    private final Random c;
    private long d;
    private long e;

    // compiled from: Taobao
    static class a {
        public static b a;

        a() {
        }

        static {
            a = new b();
        }
    }

    public b() {
        this.a = new TreeSet();
        this.b = a.a(0);
        this.c = new Random();
        this.d = 0;
        this.e = 0;
    }

    public synchronized void a(a aVar) {
        if (aVar != null) {
            if (aVar.b < 524288) {
                this.d += (long) aVar.b;
                this.a.add(aVar);
                while (this.d > 524288) {
                    a aVar2;
                    if (this.c.nextBoolean()) {
                        aVar2 = (a) this.a.pollFirst();
                    } else {
                        aVar2 = (a) this.a.pollLast();
                    }
                    this.d -= (long) aVar2.b;
                }
                if (ALog.isPrintLog(1)) {
                    ALog.d(TAG, "ByteArray Pool refund", null, "refund", Integer.valueOf(aVar.b()), "total", Long.valueOf(this.d));
                }
            }
        }
    }

    public synchronized a a(int i) {
        a a;
        if (i >= 524288) {
            a = a.a(i);
        } else {
            this.b.b = i;
            a = (a) this.a.ceiling(this.b);
            if (a == null) {
                a = a.a(i);
            } else {
                Arrays.fill(a.a, (byte) 0);
                a.c = 0;
                this.a.remove(a);
                this.d -= (long) a.b;
                this.e += (long) i;
                if (ALog.isPrintLog(1)) {
                    ALog.d(TAG, "ByteArray Pool retrieve", null, "retrieve", Integer.valueOf(i), "reused", Long.valueOf(this.e));
                }
            }
        }
        return a;
    }

    public a a(byte[] bArr, int i) {
        a a = a(i);
        System.arraycopy(bArr, 0, a.a, 0, i);
        a.c = i;
        return a;
    }
}
