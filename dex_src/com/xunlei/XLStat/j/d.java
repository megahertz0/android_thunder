package com.xunlei.XLStat.j;

import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.e;
import com.xunlei.XLStat.f;
import com.xunlei.XLStat.f.b;
import com.xunlei.XLStat.g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class d {
    public static byte[] a(ArrayList<e> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            byte[] bArr = new byte[4];
            Arrays.fill(bArr, (byte) 0);
            return bArr;
        }
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((e) it.next()).a().length + i;
        }
        Object obj = new Object[(i + 4)];
        System.arraycopy(b.a(arrayList.size()), 0, obj, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Iterator it2 = arrayList.iterator();
        i = 4;
        while (it2.hasNext()) {
            e eVar = (e) it2.next();
            System.arraycopy(eVar.a(), 0, obj, i, eVar.a().length);
            i = eVar.a().length + i;
        }
        return obj;
    }

    public static byte[] b(ArrayList<g> arrayList) {
        int i = ((g) arrayList.get(0)).b;
        long j = ((g) arrayList.get(0)).c;
        Object obj = new Object[4];
        System.arraycopy(b.a((long) i), 0, obj, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Object obj2 = new Object[8];
        System.arraycopy(b.a(j), 0, obj2, 0, SpdyProtocol.PUBKEY_SEQ_ADASH);
        Iterator it = arrayList.iterator();
        i = 0;
        while (it.hasNext()) {
            i = ((g) it.next()).a().length + i;
        }
        Object obj3 = new Object[((((i + 4) + 4) + 4) + 8)];
        System.arraycopy(b.a(((i + 4) + 4) + 8), 0, obj3, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj, 0, obj3, MqttConnectOptions.MQTT_VERSION_3_1_1, MqttConnectOptions.MQTT_VERSION_3_1_1);
        System.arraycopy(obj2, 0, obj3, SpdyProtocol.PUBKEY_SEQ_ADASH, SpdyProtocol.PUBKEY_SEQ_ADASH);
        System.arraycopy(b.a(arrayList.size()), 0, obj3, SpdyProtocol.CUSTOM, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Iterator it2 = arrayList.iterator();
        i = 20;
        while (it2.hasNext()) {
            g gVar = (g) it2.next();
            System.arraycopy(gVar.a(), 0, obj3, i, gVar.a().length);
            i = gVar.a().length + i;
        }
        return obj3;
    }

    public static byte[] c(ArrayList<b> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            byte[] bArr = new byte[4];
            Arrays.fill(bArr, (byte) 0);
            return bArr;
        }
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((b) it.next()).a().length + i;
        }
        Object obj = new Object[(i + 4)];
        System.arraycopy(b.a(arrayList.size()), 0, obj, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Iterator it2 = arrayList.iterator();
        i = 4;
        while (it2.hasNext()) {
            b bVar = (b) it2.next();
            System.arraycopy(bVar.a(), 0, obj, i, bVar.a().length);
            i = bVar.a().length + i;
        }
        XLStatLog.d("Type2Bytes", "XLStatContext2Bytes", new StringBuilder("contexts size: ").append(obj.length).append("  contextsbytes to HEX bytes: ").append(e.a(obj)).toString());
        return obj;
    }

    public static byte[] d(ArrayList<f> arrayList) {
        XLStatLog.d("Type2Bytes", "XLStatHeartbeat2Bytes", new StringBuilder("heartbeats: ").append(arrayList).toString());
        if (arrayList == null || arrayList.size() == 0) {
            byte[] bArr = new byte[4];
            Arrays.fill(bArr, (byte) 0);
            return bArr;
        }
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((f) it.next()).a().length + i;
        }
        XLStatLog.d("Type2Bytes", "XLStatHeartbeat2Bytes", new StringBuilder("heartbeatslen: ").append(i).toString());
        Object obj = new Object[(i + 4)];
        System.arraycopy(b.a(arrayList.size()), 0, obj, 0, MqttConnectOptions.MQTT_VERSION_3_1_1);
        Iterator it2 = arrayList.iterator();
        i = 4;
        while (it2.hasNext()) {
            f fVar = (f) it2.next();
            System.arraycopy(fVar.a(), 0, obj, i, fVar.a().length);
            i = fVar.a().length + i;
        }
        XLStatLog.d("Type2Bytes", "XLStatHeartbeat2Bytes", new StringBuilder("heartbeats size: ").append(obj.length).append(", heartbeatsbytes to HEX bytes: ").append(e.a(obj)).toString());
        return obj;
    }
}
