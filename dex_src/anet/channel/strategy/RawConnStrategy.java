package anet.channel.strategy;

import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.b;
import anet.channel.entity.d;
import anet.channel.entity.e;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.Comparator;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
class RawConnStrategy implements Serializable, Comparable<RawConnStrategy> {
    static Comparator<RawConnStrategy> a;
    private int b;
    private long c;
    public final ConnType connType;
    public final int cto;
    public final int heartbeat;
    public final boolean isAuth;
    public transient boolean isToRemove;
    public final int port;
    public final int retry;
    public final int rto;

    // compiled from: Taobao
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[EventType.values().length];
            try {
                a[EventType.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[EventType.CONNECT_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[EventType.AUTH_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[EventType.AUTH_SUCC.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[EventType.HORSE_RIDE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    // compiled from: Taobao
    static class a {
        a() {
        }

        static RawConnStrategy a(anet.channel.strategy.k.a aVar) {
            ConnType valueOf = ConnType.valueOf(aVar);
            return valueOf == null ? null : new RawConnStrategy(aVar.a, valueOf, aVar.c, aVar.d, aVar.e, aVar.f, aVar.h);
        }

        static RawConnStrategy a() {
            return new RawConnStrategy(443, ConnType.H2_ACCS_0RTT, 0, 0, 1, 45000, true);
        }

        static RawConnStrategy a(int i, ConnType connType) {
            return new RawConnStrategy(i, connType, 0, 0, 1, 45000, false);
        }
    }

    protected RawConnStrategy(int i, ConnType connType, int i2, int i3, int i4, int i5, boolean z) {
        this.b = 2;
        this.c = 2147483647L;
        this.port = i;
        this.connType = connType;
        this.cto = i2;
        this.rto = i3;
        this.retry = i4;
        this.heartbeat = i5;
        this.isAuth = z;
    }

    public void notifyEvent(EventType eventType, d dVar) {
        switch (AnonymousClass_1.a[eventType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.b = 1;
                if (dVar instanceof b) {
                    this.c = ((b) dVar).a;
                }
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.b = 3;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.b = 0;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (dVar instanceof e) {
                    e eVar = (e) dVar;
                    if (eVar.a) {
                        this.b = 0;
                        this.c = eVar.b;
                        return;
                    }
                    this.b = 3;
                }
            default:
                break;
        }
    }

    public boolean isAvailable() {
        return this.b != 3;
    }

    public void resetConnStatus() {
        if (this.b == 3) {
            this.b = 2;
        }
    }

    public int compareTo(RawConnStrategy rawConnStrategy) {
        return a.compare(this, rawConnStrategy);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('{').append(this.port).append(' ').append(this.connType).append(' ').append(a()).append('}');
        return stringBuilder.toString();
    }

    private char a() {
        switch (this.b) {
            case SpdyAgent.ACCS_TEST_SERVER:
                return 'A';
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return 'C';
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return 'N';
            default:
                return 'U';
        }
    }

    static {
        a = new e();
    }
}
