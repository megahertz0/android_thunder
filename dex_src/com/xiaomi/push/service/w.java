package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.channel.commonutils.misc.b;
import com.xiaomi.xmpush.thrift.c;
import com.xiaomi.xmpush.thrift.d;
import com.xiaomi.xmpush.thrift.e;
import com.xiaomi.xmpush.thrift.g;
import com.xiaomi.xmpush.thrift.p;
import com.xiaomi.xmpush.thrift.q;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class w {

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            b = new int[d.values().length];
            try {
                b[d.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[d.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[d.c.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[d.d.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            a = new int[c.values().length];
            try {
                a[c.a.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[c.b.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public static int a(v vVar, c cVar) {
        int i = 0;
        String a = a(cVar);
        switch (AnonymousClass_1.a[cVar.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                i = 1;
                break;
        }
        return vVar.a.getInt(a, i);
    }

    private static String a(c cVar) {
        return new StringBuilder("oc_version_").append(cVar.a()).toString();
    }

    private static List<Pair<Integer, Object>> a(List<g> list, boolean z) {
        if (b.a(list)) {
            return null;
        }
        List<Pair<Integer, Object>> arrayList = new ArrayList();
        for (g gVar : list) {
            int a = gVar.a();
            d a2 = d.a(gVar.c());
            if (a2 != null) {
                if (z && gVar.c) {
                    arrayList.add(new Pair(Integer.valueOf(a), null));
                } else {
                    Object obj;
                    Pair pair;
                    switch (AnonymousClass_1.b[a2.ordinal()]) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            pair = new Pair(Integer.valueOf(a), Integer.valueOf(gVar.f()));
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            pair = new Pair(Integer.valueOf(a), Long.valueOf(gVar.h()));
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1:
                            pair = new Pair(Integer.valueOf(a), gVar.j());
                            break;
                        case MqttConnectOptions.MQTT_VERSION_3_1_1:
                            pair = new Pair(Integer.valueOf(a), Boolean.valueOf(gVar.l()));
                            break;
                        default:
                            obj = null;
                            break;
                    }
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    public static void a(v vVar, c cVar, int i) {
        vVar.a.edit().putInt(a(cVar), i).commit();
    }

    public static void a(v vVar, p pVar) {
        vVar.b(a(pVar.a(), true));
    }

    public static void a(v vVar, q qVar) {
        for (e eVar : qVar.a()) {
            if (eVar.a() > a(vVar, eVar.d())) {
                a(vVar, eVar.d(), eVar.a());
                vVar.a(a(eVar.b, false));
            }
        }
    }
}
