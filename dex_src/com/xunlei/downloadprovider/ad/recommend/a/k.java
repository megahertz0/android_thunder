package com.xunlei.downloadprovider.ad.recommend.a;

import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.ad.common.c;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping;
import com.xunlei.downloadprovider.app.BrothersApplication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RecommendAdModelProxy.java
public class k extends o {
    private static String c;
    private static k d;
    public final Map<Integer, List<a>> a;
    public final Map<Integer, Boolean> b;
    private i e;

    static {
        c = k.class.getSimpleName();
        d = null;
    }

    private k() {
        this.e = null;
        this.e = i.a();
        this.a = new HashMap();
        this.b = new HashMap();
    }

    public static k a() {
        if (d == null) {
            d = new k();
        }
        return d;
    }

    public static void b() {
        d = null;
        i.b();
    }

    public final void a(int i, c.a aVar, String str, int i2) {
        d dVar = new d(i2);
        dVar.a(new m(this, aVar));
        i iVar = this.e;
        n nVar = new n(this, dVar, aVar);
        List<String> positionIds = RecommendSSPAdMapping.getPositionIds(i);
        if (positionIds.isEmpty()) {
            nVar.a(b.a.a.e, b.a.a.f);
        } else {
            int positionCount = RecommendSSPAdMapping.getPositionCount(i);
            List list = (List) iVar.a.get(Integer.valueOf(i));
            list.clear();
            for (int i3 = 0; i3 < positionCount; i3++) {
                list.add(null);
            }
            list = (List) iVar.b.get(Integer.valueOf(i));
            list.clear();
            list.addAll(RecommendSSPAdMapping.getPositionIds(i));
            i.a jVar = new j(iVar, nVar);
            for (String str2 : positionIds) {
                d a = d.a();
                Map map = (Map) a.b.get(str2);
                if (map == null || map.isEmpty()) {
                    a.a(str2);
                }
                AD_TYPE a2 = b.a((Map) a.b.get(str2));
                switch (AnonymousClass_1.a[a2.ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                    case SimpleLog.LOG_LEVEL_DEBUG:
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        break;
                    default:
                        a2 = AD_TYPE.SOURCE_GDT_FLAG;
                        break;
                }
                new StringBuilder("createExecutor pageIndex: ").append(i).append(" positionId: ").append(str2).append(" adType: ").append(a2.name());
                f fVar = new f();
                com.xunlei.downloadprovider.ad.common.b.a a3 = f.a(BrothersApplication.a(), i, str2, a2, jVar, str);
                if (a3 instanceof p) {
                    fVar = new f();
                    a3.a = f.a(BrothersApplication.a(), i, str2, AD_TYPE.SOURCE_GDT_FLAG, jVar, str);
                }
                a3.b();
            }
        }
        dVar.a();
    }
}
