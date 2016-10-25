package anet.channel.session;

import anet.channel.RequestCb;
import anet.channel.a.a;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.statist.RequestStatistic;
import java.util.List;
import java.util.Map;

// compiled from: Taobao
class h implements RequestCb {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void onResponseCode(int i, Map<String, List<String>> map) {
        if (i <= 0) {
            this.a.c.handleCallbacks(EventType.DISCONNECTED, new d(EventType.DISCONNECTED, 0, "Http connect fail"));
        }
        this.a.b.onResponseCode(i, map);
    }

    public void onDataReceive(a aVar, boolean z) {
        this.a.b.onDataReceive(aVar, z);
    }

    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        this.a.b.onFinish(i, str, requestStatistic);
    }
}
