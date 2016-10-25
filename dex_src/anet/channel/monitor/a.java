package anet.channel.monitor;

import anet.channel.c.c;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.util.ALog;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.CopyOnWriteArrayList;

// compiled from: Taobao
public class a {
    static int a;
    static long b;
    static long c;
    static long d;
    static long e;
    static long f;
    static double g;
    static double h;
    static double i;
    static double j;
    private static volatile boolean k;
    private int l;
    private int m;
    private CopyOnWriteArrayList<INetworkQualityChangeListener> n;
    private d o;

    // compiled from: Taobao
    static class a {
        static a a;

        a() {
        }

        static {
            a = new a();
        }
    }

    static {
        k = false;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0.0d;
        h = 0.0d;
        i = 0.0d;
        j = 40.0d;
    }

    public static a a() {
        return a.a;
    }

    private a() {
        this.l = 5;
        this.m = 0;
        this.n = new CopyOnWriteArrayList();
        this.o = new d();
        NetworkStatusHelper.a(new b(this));
    }

    public void a(INetworkQualityChangeListener iNetworkQualityChangeListener) {
        this.n.add(iNetworkQualityChangeListener);
    }

    public void b(INetworkQualityChangeListener iNetworkQualityChangeListener) {
        this.n.remove(iNetworkQualityChangeListener);
    }

    public int b() {
        return NetworkStatusHelper.a() == NetworkStatus.G2 ? 1 : this.l;
    }

    public synchronized void c() {
        try {
            ALog.i("awcn.BandWidthSampler", "[startNetworkMeter]", null, "NetworkStatus", NetworkStatusHelper.a());
            if (NetworkStatusHelper.a() == NetworkStatus.G2) {
                k = false;
            } else {
                k = true;
            }
        } catch (Throwable e) {
            ALog.w("awcn.BandWidthSampler", "startNetworkMeter fail.", null, e, new Object[0]);
        }
    }

    public void d() {
        k = false;
    }

    public void a(long j, long j2, long j3) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.BandWidthSampler", "onDataReceived", null, "mRequestStartTime", Long.valueOf(j), "mRequestFinishedTime", Long.valueOf(j2), "mRequestDataSize", Long.valueOf(j3));
        }
        if (k && j3 > 3000 && j < j2) {
            a++;
            e += j3;
            if (a == 1) {
                d = j2 - j;
            }
            if (a >= 2 && a <= 3) {
                if (j >= c) {
                    d += j2 - j;
                } else if (j < c && j2 >= c) {
                    long j4 = d + (j2 - j);
                    d = j4;
                    d = j4 - (c - j);
                }
            }
            b = j;
            c = j2;
            if (a == 3) {
                i = (double) ((long) this.o.a((double) e, (double) d));
                f++;
                this.m++;
                if (f > 30) {
                    this.o.a();
                    f = 3;
                }
                double d = ((i * 0.68d) + (h * 0.27d)) + (g * 0.05d);
                g = h;
                h = i;
                if (i < 0.65d * g || i > 2.0d * g) {
                    i = d;
                }
                int i = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                if (i < j) {
                    i = 1;
                }
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.BandWidthSampler", "NetworkSpeed", null, "mKalmanDataSize", Long.valueOf(e), "mKalmanTimeUsed", Long.valueOf(d), "speed", Double.valueOf(i), "tmpspeed", Integer.valueOf(i), "mSpeedKalmanCount", Long.valueOf(f));
                }
                if (i != this.l) {
                    ALog.i("awcn.BandWidthSampler", "NetworkSpeed change!", null, "From", Integer.valueOf(this.l), "To", Integer.valueOf(i), "mSinceLastNotification", Integer.valueOf(this.m));
                    if (this.m > 5 || f == 2) {
                        this.m = 0;
                        this.l = i;
                        ALog.i("awcn.BandWidthSampler", "NetworkSpeed change!", null, "Send Network quality change notification.");
                        c.a(new c(this));
                    }
                }
                d = 0;
                e = 0;
                a = 0;
            }
        }
    }
}
