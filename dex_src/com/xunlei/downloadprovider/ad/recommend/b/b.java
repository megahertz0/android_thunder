package com.xunlei.downloadprovider.ad.recommend.b;

import android.text.TextUtils;
import android.view.View;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.recommend.a.a;
import com.xunlei.downloadprovider.ad.recommend.a.k;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovidercommon.a.d;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RecommendAdPresenter.java
public class b implements a {
    private static final String a;
    private final k b;
    private com.xunlei.downloadprovider.ad.recommend.a.b c;

    static {
        a = b.class.getSimpleName();
    }

    public b(k kVar) {
        this.b = kVar;
    }

    public final void a() {
        boolean z;
        this.c.f();
        this.c.a(false);
        k kVar = this.b;
        int b = this.c.b();
        c cVar = new c(this);
        Boolean bool = (Boolean) kVar.b.get(Integer.valueOf(b));
        if (bool == null) {
            z = false;
        } else {
            z = bool.booleanValue();
        }
        if (z) {
            List list = (List) kVar.a.get(Integer.valueOf(b));
            if (list == null || list.isEmpty()) {
                cVar.a(com.xunlei.downloadprovider.ad.recommend.a.b.a.a.e, com.xunlei.downloadprovider.ad.recommend.a.b.a.a.f);
                return;
            } else {
                cVar.a(list);
                return;
            }
        }
        Map hashMap = new HashMap(1);
        hashMap.put(Integer.valueOf(b), cVar);
        kVar.registerObserver(hashMap);
    }

    public final void b() {
        this.c.f();
        this.c.a(false);
        this.b.a(this.c.b(), new d(this), "change", SpdyProtocol.PUBKEY_SEQ_OPEN);
    }

    private void b(com.xunlei.downloadprovider.ad.common.a aVar, View view, int i) {
        String h;
        aVar.onClick(view);
        if (aVar.e()) {
            if ((aVar.o() == AD_TYPE.SOURCE_SSP_FLAG || aVar.o() == AD_TYPE.SOURCE_SSP_DEFAULT_FLAG || aVar.o() == AD_TYPE.SOURCE_INMOBI_NATIVE_FLAG) && !TextUtils.isEmpty(aVar.h()) && aVar.e()) {
                h = aVar.h();
                g gVar = new g(3, h, null);
                String a = com.xunlei.downloadprovider.ad.common.b.a(aVar);
                switch (this.c.b()) {
                    case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                        gVar.d = com.xunlei.downloadprovider.service.a.j + a;
                        break;
                    case SimpleLog.LOG_LEVEL_TRACE:
                        gVar.d = com.xunlei.downloadprovider.service.a.k + a;
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        gVar.d = com.xunlei.downloadprovider.service.a.i + a;
                        break;
                }
                StatReporter.reportOverallDownload("guanggao");
                com.xunlei.downloadprovider.service.downloads.task.b bVar = new com.xunlei.downloadprovider.service.downloads.task.b();
                bVar.c = aVar.n();
                bVar.e = aVar.m();
                bVar.d = true;
                ((ThunderTask) this.c.c()).createLocalTaskWithAdditionInfo(h, aVar.m(), 0, null, null, null, 0, gVar, null, false, bVar);
            }
        }
        int b = this.c.b();
        String valueOf = String.valueOf(i + 1);
        h = aVar.o().getSourceName();
        String a2 = com.xunlei.downloadprovider.ad.common.b.a(aVar);
        String j = aVar.j();
        new StringBuilder("reportRecommendAdClick attr: adv_downloadin_click tabId: ").append(b).append(" adPosition: ").append(valueOf).append(" adType: ").append(h).append(" adId: ").append(a2).append(" material: ").append(j);
        d.a(com.xunlei.downloadprovidercommon.a.a.a("android_advertise", "adv_downloadin_click").b("tabid", com.xunlei.downloadprovider.ad.recommend.c.a.a(b)).b("adv_position", valueOf).b("ad_type", h).b("advid", a2).b("material", j));
    }

    private void a(List<com.xunlei.downloadprovider.ad.common.a> list) {
        if (list != null) {
            int size;
            StringBuilder stringBuilder = new StringBuilder("\u6b64\u5e7f\u544a\u7531");
            Set<String> hashSet = new HashSet();
            for (com.xunlei.downloadprovider.ad.common.a aVar : list) {
                hashSet.add(aVar.o().getSourceCompanyName());
            }
            int size2 = hashSet.size();
            if (size2 == 3) {
                hashSet.remove("\u8fc5\u96f7");
                size = hashSet.size();
            } else {
                size = size2;
            }
            for (String str : hashSet) {
                stringBuilder.append(str);
                if (size > 1 && stringBuilder.indexOf("&") == -1) {
                    stringBuilder.append("&");
                }
            }
            stringBuilder.append("\u63d0\u4f9b");
            this.c.a(stringBuilder.toString());
        }
    }

    public final void a(com.xunlei.downloadprovider.ad.common.a aVar, View view, int i) {
        Object obj;
        if (aVar.o() == AD_TYPE.SOURCE_BAIDU_FLAG && com.xunlei.xllib.a.b.g(BrothersApplication.a()) && aVar.e()) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.c.a(new e(this, aVar, view, i), new f(this));
            this.c.d();
            return;
        }
        b(aVar, view, i);
    }

    static /* synthetic */ void a(b bVar, List list) {
        boolean a = bVar.c.a(list);
        bVar.a(list);
        bVar.c.g();
        bVar.c.a(true);
        if (a) {
            bVar.c.a(0);
        }
    }

    static /* synthetic */ void a(b bVar) {
        bVar.c.a();
        bVar.c.a((int) SpdyProtocol.PUBKEY_SEQ_ADASH);
        bVar.c.g();
        bVar.c.a(true);
    }
}
