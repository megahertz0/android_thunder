package anet.channel;

import anet.channel.entity.EventCb;
import anet.channel.entity.EventType;
import anet.channel.util.ALog;

// compiled from: Taobao
class d implements Runnable {
    final /* synthetic */ EventType a;
    final /* synthetic */ anet.channel.entity.d b;
    final /* synthetic */ Session c;

    d(Session session, EventType eventType, anet.channel.entity.d dVar) {
        this.c = session;
        this.a = eventType;
        this.b = dVar;
    }

    public void run() {
        try {
            if (this.c.mEventCallBacks != null && this.a != null) {
                for (EventCb eventCb : this.c.mEventCallBacks.keySet()) {
                    if (eventCb != null && (((Integer) this.c.mEventCallBacks.get(eventCb)).intValue() & this.a.getType()) != 0) {
                        try {
                            eventCb.onEvent(this.c, this.a, this.b);
                        } catch (Exception e) {
                            ALog.e("awcn.Session", e.toString(), this.c.mSeq, new Object[0]);
                        }
                    }
                }
            }
        } catch (Throwable e2) {
            ALog.e("awcn.Session", "handleCallbacks", this.c.mSeq, e2, new Object[0]);
        }
    }
}
