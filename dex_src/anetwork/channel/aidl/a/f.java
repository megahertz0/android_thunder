package anetwork.channel.aidl.a;

import android.os.Handler;
import android.os.RemoteException;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.DefaultProgressEvent;
import anetwork.channel.aidl.ParcelableHeader;
import anetwork.channel.aidl.j;
import anetwork.channel.aidl.k.a;
import anetwork.channel.c;
import anetwork.channel.c.b;
import anetwork.channel.c.d;
import anetwork.channel.e;
import com.taobao.accs.common.Constants;

// compiled from: Taobao
public final class f extends a {
    private e a;
    private Handler b;
    private Object c;
    private byte d;

    public f(e eVar) {
        this.d = (byte) 0;
        this.a = eVar;
        if (c.a.class.isAssignableFrom(eVar.getClass())) {
            this.d = (byte) (this.d | 1);
        }
        if (c.c.class.isAssignableFrom(eVar.getClass())) {
            this.d = (byte) (this.d | 2);
        }
        if (d.class.isAssignableFrom(eVar.getClass())) {
            this.d = (byte) (this.d | 4);
        }
        if (b.class.isAssignableFrom(eVar.getClass())) {
            this.d = (byte) (this.d | 8);
        }
        this.b = null;
        this.c = null;
    }

    private void a(byte b, Object obj) {
        if (this.b == null) {
            b(b, obj);
        } else {
            this.b.post(new g(this, b, obj));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(byte r7, java.lang.Object r8) {
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.aidl.a.f.b(byte, java.lang.Object):void");
        /*
        this = this;
        r5 = 0;
        r1 = 1;
        r4 = 0;
        r0 = 4;
        if (r7 != r0) goto L_0x0035;
    L_0x0006:
        r8 = (anetwork.channel.aidl.ParcelableHeader) r8;	 Catch:{ Exception -> 0x0062 }
        r0 = r6.a;	 Catch:{ Exception -> 0x0062 }
        r0 = (anetwork.channel.c.d) r0;	 Catch:{ Exception -> 0x0062 }
        r1 = r8.a;	 Catch:{ Exception -> 0x0062 }
        r2 = r8.b;	 Catch:{ Exception -> 0x0062 }
        r0.a(r1, r2);	 Catch:{ Exception -> 0x0062 }
        r0 = 1;
        r0 = anet.channel.util.ALog.isPrintLog(r0);	 Catch:{ Exception -> 0x0062 }
        if (r0 == 0) goto L_0x0034;
    L_0x001a:
        r0 = "ANet.ParcelableNetworkListenerWrapper";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0062 }
        r2 = "[onResponseCode]";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0062 }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0062 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0062 }
        r2 = 0;
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0062 }
        anet.channel.util.ALog.d(r0, r1, r2, r3);	 Catch:{ Exception -> 0x0062 }
    L_0x0034:
        return;
    L_0x0035:
        r0 = 2;
        if (r7 != r0) goto L_0x006f;
    L_0x0038:
        r8 = (anetwork.channel.aidl.DefaultProgressEvent) r8;	 Catch:{ Exception -> 0x0062 }
        if (r8 == 0) goto L_0x0040;
    L_0x003c:
        r0 = r6.c;	 Catch:{ Exception -> 0x0062 }
        r8.e = r0;	 Catch:{ Exception -> 0x0062 }
    L_0x0040:
        r0 = 1;
        r0 = anet.channel.util.ALog.isPrintLog(r0);	 Catch:{ Exception -> 0x0062 }
        if (r0 == 0) goto L_0x0034;
    L_0x0047:
        r0 = "ANet.ParcelableNetworkListenerWrapper";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0062 }
        r2 = "[onDataReceived]";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0062 }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0062 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0062 }
        r2 = 0;
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0062 }
        anet.channel.util.ALog.d(r0, r1, r2, r3);	 Catch:{ Exception -> 0x0062 }
        goto L_0x0034;
    L_0x0062:
        r0 = move-exception;
        r0 = "ANet.ParcelableNetworkListenerWrapper";
        r1 = "dispatchCallback error";
        r2 = new java.lang.Object[r4];
        anet.channel.util.ALog.e(r0, r1, r5, r2);
        goto L_0x0034;
    L_0x006f:
        if (r7 != r1) goto L_0x00a2;
    L_0x0071:
        r8 = (anetwork.channel.aidl.DefaultFinishEvent) r8;	 Catch:{ Exception -> 0x0062 }
        if (r8 == 0) goto L_0x0079;
    L_0x0075:
        r0 = r6.c;	 Catch:{ Exception -> 0x0062 }
        r8.a = r0;	 Catch:{ Exception -> 0x0062 }
    L_0x0079:
        r0 = r6.a;	 Catch:{ Exception -> 0x0062 }
        r0 = (anetwork.channel.c.a) r0;	 Catch:{ Exception -> 0x0062 }
        r0.a(r8);	 Catch:{ Exception -> 0x0062 }
        r0 = 1;
        r0 = anet.channel.util.ALog.isPrintLog(r0);	 Catch:{ Exception -> 0x0062 }
        if (r0 == 0) goto L_0x0034;
    L_0x0087:
        r0 = "ANet.ParcelableNetworkListenerWrapper";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0062 }
        r2 = "[onFinished]";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0062 }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0062 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0062 }
        r2 = 0;
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0062 }
        anet.channel.util.ALog.d(r0, r1, r2, r3);	 Catch:{ Exception -> 0x0062 }
        goto L_0x0034;
    L_0x00a2:
        r0 = 8;
        if (r7 != r0) goto L_0x0034;
    L_0x00a6:
        r8 = (anetwork.channel.aidl.j) r8;	 Catch:{ Exception -> 0x0062 }
        r0 = r6.a;	 Catch:{ Exception -> 0x0062 }
        r0 = (anetwork.channel.c.b) r0;	 Catch:{ Exception -> 0x0062 }
        r0.a(r8);	 Catch:{ Exception -> 0x0062 }
        r0 = 1;
        r0 = anet.channel.util.ALog.isPrintLog(r0);	 Catch:{ Exception -> 0x0062 }
        if (r0 == 0) goto L_0x0034;
    L_0x00b6:
        r0 = "ANet.ParcelableNetworkListenerWrapper";
        r1 = "[onInputStreamReceived]";
        r2 = 0;
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0062 }
        anet.channel.util.ALog.d(r0, r1, r2, r3);	 Catch:{ Exception -> 0x0062 }
        goto L_0x0034;
        */
    }

    public final void a(DefaultProgressEvent defaultProgressEvent) throws RemoteException {
        if ((this.d & 2) != 0) {
            a((byte) Constants.PROTOCOL_VERSION, (Object) defaultProgressEvent);
        }
    }

    public final void a(DefaultFinishEvent defaultFinishEvent) throws RemoteException {
        if ((this.d & 1) != 0) {
            a((byte) 1, (Object) defaultFinishEvent);
        }
        this.a = null;
        this.c = null;
        this.b = null;
    }

    public final boolean a(int i, ParcelableHeader parcelableHeader) throws RemoteException {
        if ((this.d & 4) != 0) {
            a((byte) 4, (Object) parcelableHeader);
        }
        return false;
    }

    public final void a(j jVar) throws RemoteException {
        if ((this.d & 8) != 0) {
            a((byte) 8, (Object) jVar);
        }
    }

    public final byte a() throws RemoteException {
        return this.d;
    }
}
