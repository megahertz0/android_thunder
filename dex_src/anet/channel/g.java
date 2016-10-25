package anet.channel;

import anet.channel.entity.EventCb;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import com.xunlei.downloadprovider.web.core.JsInterface;

// compiled from: Taobao
class g implements EventCb {
    final /* synthetic */ Session a;
    final /* synthetic */ SessionRequest b;

    g(SessionRequest sessionRequest, Session session) {
        this.b = sessionRequest;
        this.a = session;
    }

    public void onEvent(Session session, EventType eventType, d dVar) {
        ALog.d("awcn.SessionRequest", "Receive session event", null, JsInterface.FUNPLAY_AD_TRPE, eventType);
        StrategyCenter.getInstance().notifyConnEvent(n.e(this.a.getHost()), this.a.getConnStrategy(), eventType, dVar);
    }
}
