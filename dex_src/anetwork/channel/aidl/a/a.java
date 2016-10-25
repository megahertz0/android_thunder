package anetwork.channel.aidl.a;

import android.os.Build.VERSION;
import android.os.RemoteException;
import anet.channel.util.ErrorConstant;
import anetwork.channel.aidl.h;
import anetwork.channel.c.b;
import anetwork.channel.c.d;
import anetwork.channel.entity.j;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
public final class a extends anetwork.channel.aidl.a.a implements anetwork.channel.c.a, b, d {
    public anetwork.channel.f.a a;
    public h b;
    private d c;
    private int d;
    private String e;
    private Map<String, List<String>> f;
    private CountDownLatch g;
    private CountDownLatch h;
    private j i;

    public a(j jVar) {
        this.g = new CountDownLatch(1);
        this.h = new CountDownLatch(1);
        this.i = jVar;
    }

    public final String c() throws RemoteException {
        a(this.g);
        return this.e;
    }

    public final anetwork.channel.aidl.j a() throws RemoteException {
        a(this.h);
        return this.c;
    }

    public final int b() throws RemoteException {
        a(this.g);
        return this.d;
    }

    public final Map<String, List<String>> d() throws RemoteException {
        a(this.g);
        return this.f;
    }

    public final void e() throws RemoteException {
        if (this.b != null) {
            this.b.a(true);
        }
    }

    public final void a(anetwork.channel.d.a aVar) {
        if (this.c != null) {
            this.c.e();
        }
        this.d = aVar.a();
        this.e = aVar.b() != null ? aVar.b() : ErrorConstant.getErrMsg(this.d);
        this.a = aVar.c();
        this.h.countDown();
        this.g.countDown();
    }

    private void a(CountDownLatch countDownLatch) throws RemoteException {
        try {
            if (!countDownLatch.await((long) this.i.i, TimeUnit.MILLISECONDS)) {
                if (this.b != null) {
                    this.b.a(true);
                }
                throw a("wait time out");
            }
        } catch (InterruptedException e) {
            throw a("thread interrupt");
        }
    }

    private static RemoteException a(String str) {
        return VERSION.SDK_INT >= 15 ? new RemoteException(str) : new RemoteException();
    }

    public final boolean a(int i, Map<String, List<String>> map) {
        this.d = i;
        this.e = ErrorConstant.getErrMsg(this.d);
        this.f = map;
        this.g.countDown();
        return false;
    }

    public final void a(anetwork.channel.aidl.j jVar) {
        this.c = (d) jVar;
        this.h.countDown();
    }
}
