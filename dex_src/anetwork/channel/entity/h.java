package anetwork.channel.entity;

import android.os.RemoteException;
import anet.channel.a.a;
import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultProgressEvent;
import anetwork.channel.aidl.a.d;
import anetwork.channel.aidl.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Taobao
final class h implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ a b;
    final /* synthetic */ int c;
    final /* synthetic */ k d;
    final /* synthetic */ f e;

    h(f fVar, int i, a aVar, int i2, k kVar) {
        this.e = fVar;
        this.a = i;
        this.b = aVar;
        this.c = i2;
        this.d = kVar;
    }

    public final void run() {
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            ALog.d("ANet.Repeater", new StringBuilder("[onDataReceiveSize] index:").append(this.a).toString(), this.e.b, new Object[0]);
        }
        if (this.e.d) {
            try {
                if (this.e.c == null) {
                    this.e.c = new d();
                    this.e.c.a = this.c;
                    this.e.c.b = this.e.e.i;
                    this.e.c.a(this.b);
                    this.d.a(this.e.c);
                    return;
                }
                this.e.c.a(this.b);
                return;
            } catch (Exception e) {
                if (this.e.c != null) {
                    try {
                        this.e.c.b();
                    } catch (RemoteException e2) {
                    }
                }
            }
        }
        DefaultProgressEvent defaultProgressEvent = new DefaultProgressEvent();
        defaultProgressEvent.b = this.b.c();
        defaultProgressEvent.c = this.c;
        defaultProgressEvent.d = com.umeng.a.d;
        defaultProgressEvent.a = this.a;
        defaultProgressEvent.f = this.b.a();
        try {
            this.d.a(defaultProgressEvent);
        } catch (RemoteException e3) {
            e3.printStackTrace();
        }
    }
}
