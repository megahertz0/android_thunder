package anet.channel;

import anet.channel.entity.EventCb;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.util.ALog;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
class f implements EventCb {
    final /* synthetic */ IConnCb a;
    final /* synthetic */ long b;
    final /* synthetic */ SessionRequest c;

    f(SessionRequest sessionRequest, IConnCb iConnCb, long j) {
        this.c = sessionRequest;
        this.a = iConnCb;
        this.b = j;
    }

    public void onEvent(Session session, EventType eventType, d dVar) {
        if (session != null && eventType != null) {
            int i = dVar == null ? 0 : dVar.d;
            String str = dVar == null ? a.d : dVar.e;
            String str2;
            String str3;
            switch (AnonymousClass_1.a[eventType.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    str2 = "awcn.SessionRequest";
                    if (session != null) {
                        str = session.mSeq;
                    } else {
                        str = null;
                    }
                    ALog.d(str2, null, str, "Session", session, "EventType", eventType, "Event", dVar);
                    this.c.a(session.getHost(), true, 0, null);
                    this.a.onSuccess(session, this.b);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    str3 = "awcn.SessionRequest";
                    if (session != null) {
                        str2 = session.mSeq;
                    } else {
                        str2 = null;
                    }
                    ALog.d(str3, null, str2, "Session", session, "EventType", eventType, "Event", dVar);
                    this.c.a(session.getHost(), false, i, str);
                    if (a.a.c(this.c, session)) {
                        this.a.onDisConnect(session, this.b, eventType);
                    } else {
                        this.a.onFailed(session, this.b, eventType, i);
                    }
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    str3 = "awcn.SessionRequest";
                    if (session != null) {
                        str2 = session.mSeq;
                    } else {
                        str2 = null;
                    }
                    ALog.d(str3, null, str2, "Session", session, "EventType", eventType, "Event", dVar);
                    this.c.a(session.getHost(), false, i, str);
                    this.a.onFailed(session, this.b, eventType, i);
                default:
                    break;
            }
        }
    }
}
