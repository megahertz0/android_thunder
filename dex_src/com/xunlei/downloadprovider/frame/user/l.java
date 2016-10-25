package com.xunlei.downloadprovider.frame.user;

import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.c;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: HistoryCommentItemFragment.java
final class l implements a<List<c>> {
    final /* synthetic */ HistoryCommentItemFragment a;

    l(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final /* synthetic */ void a(Object obj) {
        List<c> list = (List) obj;
        HistoryCommentItemFragment.r(this.a).b();
        HistoryCommentItemFragment.s(this.a).setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        HistoryCommentItemFragment.t(this.a);
        HistoryCommentItemFragment.f(this.a).clear();
        HistoryCommentItemFragment.u(this.a);
        if (list == null || list.isEmpty()) {
            HistoryCommentItemFragment.s(this.a).setErrorType(0);
            HistoryCommentItemFragment.s(this.a).setVisibility(0);
            return;
        }
        HistoryCommentItemFragment.a(this.a, list);
        List arrayList = new ArrayList();
        for (c cVar : list) {
            ai aiVar = new ai(cVar, 0);
            HistoryCommentItemFragment.f(this.a).add(cVar);
            arrayList.add(aiVar);
        }
        if (arrayList.size() < 20) {
            HistoryCommentItemFragment.v(this.a);
        }
        HistoryCommentItemFragment.g(this.a).a(arrayList);
    }

    public final void a(b bVar) {
        HistoryCommentItemFragment.r(this.a).b();
        HistoryCommentItemFragment.w(this.a);
    }
}
