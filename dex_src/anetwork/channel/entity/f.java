package anetwork.channel.entity;

import android.os.RemoteException;
import anet.channel.a.a;
import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.a.d;
import anetwork.channel.aidl.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;

// compiled from: Taobao
public final class f {
    long a;
    public String b;
    d c;
    boolean d;
    j e;
    private k f;

    public f(k kVar, j jVar) {
        this.c = null;
        this.d = false;
        this.e = null;
        this.f = kVar;
        this.e = jVar;
        if (kVar != null) {
            try {
                if ((kVar.a() & 8) != 0) {
                    this.d = true;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public final void a(int i, Map<String, List<String>> map) {
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            ALog.i("ANet.Repeater", "[onResponseCode]", this.b, new Object[0]);
        }
        if (this.f != null) {
            a(new g(this, this.f, i, map));
        }
    }

    public final void a(int i, int i2, a aVar) {
        if (this.f != null) {
            a(new h(this, i, aVar, i2, this.f));
        }
    }

    public final void a(DefaultFinishEvent defaultFinishEvent) {
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            ALog.i("ANet.Repeater", "[onFinish] ", this.b, new Object[0]);
        }
        if (this.f != null) {
            Runnable iVar = new i(this, defaultFinishEvent, this.f);
            this.a = System.currentTimeMillis();
            a(iVar);
        }
        this.f = null;
    }

    private void a(Runnable runnable) {
        int hashCode = this.b != null ? this.b.hashCode() : hashCode();
        d.a(hashCode, runnable);
        if (ALog.isPrintLog(1)) {
            ALog.d("ANet.Repeater", new StringBuilder("[dispatchCallBack] submitTask, hashcode=").append(hashCode).append(", callback=").append(runnable).toString(), this.b, new Object[0]);
        }
    }
}
