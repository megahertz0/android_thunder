package anetwork.channel.g;

import android.content.Context;
import android.os.RemoteException;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.NetworkResponse;
import anetwork.channel.aidl.ParcelableRequest;
import anetwork.channel.aidl.a.c;
import anetwork.channel.aidl.a.f;
import anetwork.channel.aidl.h;
import anetwork.channel.aidl.k;
import anetwork.channel.aidl.n.a;
import anetwork.channel.entity.j;
import com.taobao.accs.data.Message;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
public abstract class b extends a {
    public int a;

    public b(Context context) {
        this.a = 1;
        anetwork.channel.http.b.a(context);
    }

    public final NetworkResponse a(ParcelableRequest parcelableRequest) throws RemoteException {
        return c(parcelableRequest);
    }

    public final h a(ParcelableRequest parcelableRequest, k kVar) throws RemoteException {
        return a(new j(parcelableRequest), kVar);
    }

    private h a(j jVar, k kVar) throws RemoteException {
        c cVar = new c(jVar, kVar, this.a);
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            ALog.i("ANet.UnifiedNetworkTask", SocialConstants.TYPE_REQUEST, cVar.c, "Url", cVar.a.b);
        }
        if (NetworkStatusHelper.e()) {
            ScheduledThreadPoolExecutor a = anetwork.channel.h.a.a();
            Runnable dVar = new d(cVar);
            j jVar2 = cVar.a;
            cVar.f = a.schedule(dVar, (long) ((jVar2.g + 1) * jVar2.i), TimeUnit.MILLISECONDS);
            cVar.e = new a(cVar);
            anetwork.channel.h.a.a().submit(cVar.e);
        } else {
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                ALog.i("ANet.UnifiedNetworkTask", NetworkUnavailableException.ERROR_INFO, cVar.c, new StringBuilder("NetworkStatus").append(NetworkStatusHelper.a()).toString());
            }
            cVar.b.a(new DefaultFinishEvent((byte) 0));
        }
        return new c(new a(cVar));
    }

    public final anetwork.channel.aidl.a b(ParcelableRequest parcelableRequest) throws RemoteException {
        j jVar = new j(parcelableRequest);
        anetwork.channel.aidl.a aVar = new anetwork.channel.aidl.a.a(jVar);
        aVar.b = a(jVar, new f(aVar));
        return aVar;
    }

    private NetworkResponse c(ParcelableRequest parcelableRequest) {
        NetworkResponse networkResponse = new NetworkResponse();
        try {
            anetwork.channel.aidl.a.a aVar = (anetwork.channel.aidl.a.a) b(parcelableRequest);
            networkResponse.a(aVar.b());
            networkResponse.c = aVar.d();
            anetwork.channel.aidl.j a = aVar.a();
            if (a != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(aVar.a().d());
                anet.channel.a.a a2 = a.a.a((int) Message.FLAG_RET);
                while (true) {
                    int a3 = a.a(a2.a());
                    if (a3 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(a2.a(), 0, a3);
                }
                networkResponse.b = byteArrayOutputStream.toByteArray();
                networkResponse.d = aVar.a;
            }
            return networkResponse;
        } catch (RemoteException e) {
            networkResponse.a((int) ErrorConstant.ERROR_REMOTE_CALL_FAIL);
            return networkResponse;
        } catch (Exception e2) {
            networkResponse.a((int) ErrorConstant.ERROR_REQUEST_FAIL);
            return networkResponse;
        }
    }
}
