package com.xunlei.downloadprovider.web.base.model;

import com.xunlei.downloadprovider.c.a.b;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.c.a.n;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ShortMovieDetailDataLoader.java
final class r implements a<Long> {
    final /* synthetic */ String a;
    final /* synthetic */ c b;
    final /* synthetic */ d c;

    r(d dVar, String str, c cVar) {
        this.c = dVar;
        this.a = str;
        this.b = cVar;
    }

    public final /* synthetic */ void a(Object obj) {
        Long l = (Long) obj;
        c cVar = new c();
        cVar.e = this.c.g.a;
        cVar.c = System.currentTimeMillis();
        cVar.a = l.longValue();
        cVar.n = 0;
        cVar.m = false;
        cVar.a(this.a);
        cVar.d = "\u624b\u673a";
        cVar.i = LoginHelper.a().j;
        String str = LoginHelper.a().i;
        if (str == null || str.trim().length() == 0) {
            str = "\u8fc5\u96f7\u7528\u6237";
        }
        cVar.j = str;
        cVar.k = LoginHelper.a().l;
        cVar.l = true;
        if (this.b != null) {
            n nVar = new n();
            nVar.a(this.b.b);
            nVar.a = this.b.a;
            nVar.d = this.b.j;
            nVar.c = this.b.i;
            nVar.e = this.b.k;
            nVar.f = this.b.d;
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(nVar);
            cVar.q = arrayList;
            cVar.p = 1;
            cVar.s = this.b.s;
        }
        this.c.e.a(cVar);
    }

    public final void a(b bVar) {
        this.c.e.a(MqttConnectOptions.MQTT_VERSION_3_1_1, bVar.a);
    }
}
