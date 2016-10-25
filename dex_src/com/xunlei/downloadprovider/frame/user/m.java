package com.xunlei.downloadprovider.frame.user;

import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.c;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: HistoryCommentItemFragment.java
final class m implements a<List<c>> {
    final /* synthetic */ HistoryCommentItemFragment a;

    m(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final /* synthetic */ void a(Object obj) {
        List<c> list = (List) obj;
        HistoryCommentItemFragment.a(this.a, SimpleLog.LOG_LEVEL_DEBUG);
        HistoryCommentItemFragment.g(this.a).a(HistoryCommentItemFragment.m(this.a));
        HistoryCommentItemFragment.s(this.a).setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (list == null || list.isEmpty()) {
            HistoryCommentItemFragment.v(this.a);
            return;
        }
        HistoryCommentItemFragment.a(this.a, list);
        Object arrayList = new ArrayList();
        for (c cVar : list) {
            arrayList.add(new ai(cVar, 0));
            HistoryCommentItemFragment.f(this.a).add(cVar);
        }
        ah g = HistoryCommentItemFragment.g(this.a);
        if (!arrayList.isEmpty()) {
            int size = g.a.size();
            g.a.addAll(arrayList);
            g.notifyItemRangeInserted(size, arrayList.size());
        }
    }

    public final void a(b bVar) {
        HistoryCommentItemFragment.a(this.a, 1);
        HistoryCommentItemFragment.g(this.a).a(HistoryCommentItemFragment.m(this.a));
    }
}
