package anet.channel;

import anet.channel.entity.EventCb;
import anet.channel.entity.EventType;
import anet.channel.entity.b;
import anet.channel.entity.d;
import anet.channel.entity.e;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
final class c implements EventCb {
    final /* synthetic */ long a;

    c(long j) {
        this.a = j;
    }

    public final void onEvent(Session session, EventType eventType, d dVar) {
        if (session != null && eventType != null) {
            e eVar = new e(EventType.HORSE_RIDE);
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.HorseRide", "horse ride evnet callback now !!!! ", session.mSeq, "ip", session.getIp(), "port", Integer.valueOf(session.getPort()), "conntype", session.getConnType(), "EventType", eventType, "Event", dVar);
            }
            switch (AnonymousClass_1.a[eventType.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    eVar.a = true;
                    if (dVar instanceof b) {
                        eVar.b = ((b) dVar).a;
                    } else {
                        eVar.b = System.currentTimeMillis() - this.a;
                    }
                    b.c(session);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    b.b(eVar, dVar);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    b.b(eVar, dVar);
                    break;
                default:
                    return;
            }
            StrategyCenter.getInstance().notifyConnEvent(n.e(session.getHost()), session.getConnStrategy(), EventType.HORSE_RIDE, eVar);
        }
    }
}
