package com.xiaomi.xmpush.thrift;

import com.umeng.message.proguard.j;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xllib.R;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.e;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.g;
import org.apache.thrift.protocol.k;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class i implements Serializable, Cloneable, b<i, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> m;
    private static final k n;
    private static final c o;
    private static final c p;
    private static final c q;
    private static final c r;
    private static final c s;
    private static final c t;
    private static final c u;
    private static final c v;
    private static final c w;
    private static final c x;
    private static final c y;
    private static final c z;
    private BitSet A;
    public String a;
    public long b;
    public String c;
    public String d;
    public String e;
    public int f;
    public String g;
    public int h;
    public int i;
    public Map<String, String> j;
    public Map<String, String> k;
    public boolean l;

    public enum a {
        ID((short) 1, AgooConstants.MESSAGE_ID),
        MESSAGE_TS((short) 2, "messageTs"),
        TOPIC((short) 3, "topic"),
        TITLE((short) 4, SetKey.TITLE),
        DESCRIPTION((short) 5, "description"),
        NOTIFY_TYPE((short) 6, "notifyType"),
        URL((short) 7, SHubBatchQueryKeys.url),
        PASS_THROUGH((short) 8, "passThrough"),
        NOTIFY_ID((short) 9, "notifyId"),
        EXTRA((short) 10, "extra"),
        INTERNAL((short) 11, "internal"),
        IGNORE_REG_INFO((short) 12, "ignoreRegInfo");
        private static final Map<String, com.xiaomi.xmpush.thrift.i.a> m;
        private final short n;
        private final String o;

        static {
            String str = AgooConstants.MESSAGE_ID;
            a = new com.xiaomi.xmpush.thrift.i.a("ID", 0, (short) 1, AgooConstants.MESSAGE_ID);
            String str2 = "messageTs";
            b = new com.xiaomi.xmpush.thrift.i.a("MESSAGE_TS", 1, (short) 2, "messageTs");
            str2 = "topic";
            c = new com.xiaomi.xmpush.thrift.i.a("TOPIC", 2, (short) 3, "topic");
            str2 = SetKey.TITLE;
            d = new com.xiaomi.xmpush.thrift.i.a("TITLE", 3, (short) 4, SetKey.TITLE);
            str2 = "description";
            e = new com.xiaomi.xmpush.thrift.i.a("DESCRIPTION", 4, (short) 5, "description");
            str = "notifyType";
            f = new com.xiaomi.xmpush.thrift.i.a("NOTIFY_TYPE", 5, (short) 6, "notifyType");
            String str3 = SHubBatchQueryKeys.url;
            g = new com.xiaomi.xmpush.thrift.i.a("URL", 6, (short) 7, SHubBatchQueryKeys.url);
            str3 = "passThrough";
            h = new com.xiaomi.xmpush.thrift.i.a("PASS_THROUGH", 7, (short) 8, "passThrough");
            str3 = "notifyId";
            i = new com.xiaomi.xmpush.thrift.i.a("NOTIFY_ID", 8, (short) 9, "notifyId");
            str3 = "extra";
            j = new com.xiaomi.xmpush.thrift.i.a("EXTRA", 9, (short) 10, "extra");
            str3 = "internal";
            k = new com.xiaomi.xmpush.thrift.i.a("INTERNAL", 10, (short) 11, "internal");
            str3 = "ignoreRegInfo";
            l = new com.xiaomi.xmpush.thrift.i.a("IGNORE_REG_INFO", 11, (short) 12, "ignoreRegInfo");
            p = new com.xiaomi.xmpush.thrift.i.a[]{a, b, c, d, e, f, g, h, i, j, k, l};
            m = new HashMap();
            Iterator it = EnumSet.allOf(com.xiaomi.xmpush.thrift.i.a.class).iterator();
            while (it.hasNext()) {
                com.xiaomi.xmpush.thrift.i.a aVar = (com.xiaomi.xmpush.thrift.i.a) it.next();
                m.put(aVar.a(), aVar);
            }
        }

        private a(short s, String str) {
            this.n = s;
            this.o = str;
        }

        public final String a() {
            return this.o;
        }
    }

    static {
        n = new k("PushMetaInfo");
        o = new c(AgooConstants.MESSAGE_ID, (byte) 11, (short) 1);
        p = new c("messageTs", (byte) 10, (short) 2);
        q = new c("topic", (byte) 11, (short) 3);
        r = new c(SetKey.TITLE, (byte) 11, (short) 4);
        s = new c("description", (byte) 11, (short) 5);
        t = new c("notifyType", (byte) 8, (short) 6);
        u = new c(SHubBatchQueryKeys.url, (byte) 11, (short) 7);
        v = new c("passThrough", (byte) 8, (short) 8);
        w = new c("notifyId", (byte) 8, (short) 9);
        x = new c("extra", (byte) 13, (short) 10);
        y = new c("internal", (byte) 13, (short) 11);
        z = new c("ignoreRegInfo", (byte) 2, (short) 12);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b(AgooConstants.MESSAGE_ID, (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("messageTs", (byte) 1, new org.apache.thrift.meta_data.c((byte) 10)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("topic", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b(SetKey.TITLE, (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b("description", (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b("notifyType", (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b(SHubBatchQueryKeys.url, (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b("passThrough", (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.i, new org.apache.thrift.meta_data.b("notifyId", (byte) 2, new org.apache.thrift.meta_data.c((byte) 8)));
        enumMap.put(a.j, new org.apache.thrift.meta_data.b("extra", (byte) 2, new e((byte) 13, new org.apache.thrift.meta_data.c((byte) 11), new org.apache.thrift.meta_data.c((byte) 11))));
        enumMap.put(a.k, new org.apache.thrift.meta_data.b("internal", (byte) 2, new e((byte) 13, new org.apache.thrift.meta_data.c((byte) 11), new org.apache.thrift.meta_data.c((byte) 11))));
        enumMap.put(a.l, new org.apache.thrift.meta_data.b("ignoreRegInfo", (byte) 2, new org.apache.thrift.meta_data.c((byte) 2)));
        m = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(i.class, m);
    }

    public i() {
        this.A = new BitSet(5);
        this.l = false;
    }

    public i(i iVar) {
        Map hashMap;
        this.A = new BitSet(5);
        this.A.clear();
        this.A.or(iVar.A);
        if (iVar.c()) {
            this.a = iVar.a;
        }
        this.b = iVar.b;
        if (iVar.g()) {
            this.c = iVar.c;
        }
        if (iVar.i()) {
            this.d = iVar.d;
        }
        if (iVar.k()) {
            this.e = iVar.e;
        }
        this.f = iVar.f;
        if (iVar.n()) {
            this.g = iVar.g;
        }
        this.h = iVar.h;
        this.i = iVar.i;
        if (iVar.t()) {
            hashMap = new HashMap();
            for (Entry entry : iVar.j.entrySet()) {
                hashMap.put((String) entry.getKey(), (String) entry.getValue());
            }
            this.j = hashMap;
        }
        if (iVar.u()) {
            hashMap = new HashMap();
            for (Entry entry2 : iVar.k.entrySet()) {
                hashMap.put((String) entry2.getKey(), (String) entry2.getValue());
            }
            this.k = hashMap;
        }
        this.l = iVar.l;
    }

    public i a() {
        return new i(this);
    }

    public i a(int i) {
        this.f = i;
        b(true);
        return this;
    }

    public i a(String str) {
        this.a = str;
        return this;
    }

    public i a(Map<String, String> map) {
        this.j = map;
        return this;
    }

    public void a(String str, String str2) {
        if (this.j == null) {
            this.j = new HashMap();
        }
        this.j.put(str, str2);
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                org.apache.thrift.protocol.e k;
                int i2;
                switch (i.c) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (i.b == (byte) 11) {
                            this.a = fVar.w();
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (i.b == 10) {
                            this.b = fVar.u();
                            a(true);
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (i.b == (byte) 11) {
                            this.c = fVar.w();
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        if (i.b == (byte) 11) {
                            this.d = fVar.w();
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_ERROR:
                        if (i.b == (byte) 11) {
                            this.e = fVar.w();
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        if (i.b == (byte) 8) {
                            this.f = fVar.t();
                            b(true);
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_OFF:
                        if (i.b == (byte) 11) {
                            this.g = fVar.w();
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_ADASH:
                        if (i.b == (byte) 8) {
                            this.h = fVar.t();
                            c(true);
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                        if (i.b == (byte) 8) {
                            this.i = fVar.t();
                            d(true);
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_OPEN:
                        if (i.b == (byte) 13) {
                            k = fVar.k();
                            this.j = new HashMap(k.c * 2);
                            for (i2 = 0; i2 < k.c; i2++) {
                                this.j.put(fVar.w(), fVar.w());
                            }
                            fVar.l();
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                        if (i.b == (byte) 13) {
                            k = fVar.k();
                            this.k = new HashMap(k.c * 2);
                            for (i2 = 0; i2 < k.c; i2++) {
                                this.k.put(fVar.w(), fVar.w());
                            }
                            fVar.l();
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_titleMargins:
                        if (i.b == 2) {
                            this.l = fVar.q();
                            e(true);
                        } else {
                            org.apache.thrift.protocol.i.a(fVar, i.b);
                        }
                        break;
                    default:
                        org.apache.thrift.protocol.i.a(fVar, i.b);
                        break;
                }
                fVar.j();
            } else {
                fVar.h();
                if (e()) {
                    x();
                    return;
                }
                throw new g(new StringBuilder("Required field 'messageTs' was not found in serialized data! Struct: ").append(toString()).toString());
            }
        }
    }

    public void a(boolean z) {
        this.A.set(0, z);
    }

    public boolean a(i iVar) {
        if (iVar == null) {
            return false;
        }
        boolean c = c();
        boolean c2 = iVar.c();
        if (((c || c2) && (!c || !c2 || !this.a.equals(iVar.a))) || this.b != iVar.b) {
            return false;
        }
        c = g();
        c2 = iVar.g();
        if ((c || c2) && (!c || !c2 || !this.c.equals(iVar.c))) {
            return false;
        }
        c = i();
        c2 = iVar.i();
        if ((c || c2) && (!c || !c2 || !this.d.equals(iVar.d))) {
            return false;
        }
        c = k();
        c2 = iVar.k();
        if ((c || c2) && (!c || !c2 || !this.e.equals(iVar.e))) {
            return false;
        }
        c = m();
        c2 = iVar.m();
        if ((c || c2) && (!c || !c2 || this.f != iVar.f)) {
            return false;
        }
        c = n();
        c2 = iVar.n();
        if ((c || c2) && (!c || !c2 || !this.g.equals(iVar.g))) {
            return false;
        }
        c = p();
        c2 = iVar.p();
        if ((c || c2) && (!c || !c2 || this.h != iVar.h)) {
            return false;
        }
        c = r();
        c2 = iVar.r();
        if ((c || c2) && (!c || !c2 || this.i != iVar.i)) {
            return false;
        }
        c = t();
        c2 = iVar.t();
        if ((c || c2) && (!c || !c2 || !this.j.equals(iVar.j))) {
            return false;
        }
        c = u();
        c2 = iVar.u();
        if ((c || c2) && (!c || !c2 || !this.k.equals(iVar.k))) {
            return false;
        }
        c = w();
        c2 = iVar.w();
        return !(c || c2) || (c && c2 && this.l == iVar.l);
    }

    public int b(i iVar) {
        if (!getClass().equals(iVar.getClass())) {
            return getClass().getName().compareTo(iVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(c()).compareTo(Boolean.valueOf(iVar.c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (c()) {
            compareTo = org.apache.thrift.c.a(this.a, iVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(iVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.b, iVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(iVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.c, iVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(iVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.d, iVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(iVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.c.a(this.e, iVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m()).compareTo(Boolean.valueOf(iVar.m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m()) {
            compareTo = org.apache.thrift.c.a(this.f, iVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(iVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.c.a(this.g, iVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(p()).compareTo(Boolean.valueOf(iVar.p()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (p()) {
            compareTo = org.apache.thrift.c.a(this.h, iVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(r()).compareTo(Boolean.valueOf(iVar.r()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (r()) {
            compareTo = org.apache.thrift.c.a(this.i, iVar.i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(t()).compareTo(Boolean.valueOf(iVar.t()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (t()) {
            compareTo = org.apache.thrift.c.a(this.j, iVar.j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(u()).compareTo(Boolean.valueOf(iVar.u()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (u()) {
            compareTo = org.apache.thrift.c.a(this.k, iVar.k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(w()).compareTo(Boolean.valueOf(iVar.w()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (w()) {
            compareTo = org.apache.thrift.c.a(this.l, iVar.l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public i b(int i) {
        this.h = i;
        c(true);
        return this;
    }

    public i b(String str) {
        this.c = str;
        return this;
    }

    public String b() {
        return this.a;
    }

    public void b(f fVar) {
        x();
        fVar.a(n);
        if (this.a != null) {
            fVar.a(o);
            fVar.a(this.a);
            fVar.b();
        }
        fVar.a(p);
        fVar.a(this.b);
        fVar.b();
        if (this.c != null && g()) {
            fVar.a(q);
            fVar.a(this.c);
            fVar.b();
        }
        if (this.d != null && i()) {
            fVar.a(r);
            fVar.a(this.d);
            fVar.b();
        }
        if (this.e != null && k()) {
            fVar.a(s);
            fVar.a(this.e);
            fVar.b();
        }
        if (m()) {
            fVar.a(t);
            fVar.a(this.f);
            fVar.b();
        }
        if (this.g != null && n()) {
            fVar.a(u);
            fVar.a(this.g);
            fVar.b();
        }
        if (p()) {
            fVar.a(v);
            fVar.a(this.h);
            fVar.b();
        }
        if (r()) {
            fVar.a(w);
            fVar.a(this.i);
            fVar.b();
        }
        if (this.j != null && t()) {
            fVar.a(x);
            fVar.a(new org.apache.thrift.protocol.e((byte) 11, (byte) 11, this.j.size()));
            for (Entry entry : this.j.entrySet()) {
                fVar.a((String) entry.getKey());
                fVar.a((String) entry.getValue());
            }
            fVar.d();
            fVar.b();
        }
        if (this.k != null && u()) {
            fVar.a(y);
            fVar.a(new org.apache.thrift.protocol.e((byte) 11, (byte) 11, this.k.size()));
            for (Entry entry2 : this.k.entrySet()) {
                fVar.a((String) entry2.getKey());
                fVar.a((String) entry2.getValue());
            }
            fVar.d();
            fVar.b();
        }
        if (w()) {
            fVar.a(z);
            fVar.a(this.l);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public void b(boolean z) {
        this.A.set(1, z);
    }

    public i c(int i) {
        this.i = i;
        d(true);
        return this;
    }

    public i c(String str) {
        this.d = str;
        return this;
    }

    public void c(boolean z) {
        this.A.set(SimpleLog.LOG_LEVEL_DEBUG, z);
    }

    public boolean c() {
        return this.a != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((i) obj);
    }

    public long d() {
        return this.b;
    }

    public i d(String str) {
        this.e = str;
        return this;
    }

    public void d(boolean z) {
        this.A.set(MqttConnectOptions.MQTT_VERSION_3_1, z);
    }

    public void e(boolean z) {
        this.A.set(MqttConnectOptions.MQTT_VERSION_3_1_1, z);
    }

    public boolean e() {
        return this.A.get(0);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof i)) ? a((i) obj) : false;
    }

    public String f() {
        return this.c;
    }

    public boolean g() {
        return this.c != null;
    }

    public String h() {
        return this.d;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.d != null;
    }

    public String j() {
        return this.e;
    }

    public boolean k() {
        return this.e != null;
    }

    public int l() {
        return this.f;
    }

    public boolean m() {
        return this.A.get(1);
    }

    public boolean n() {
        return this.g != null;
    }

    public int o() {
        return this.h;
    }

    public boolean p() {
        return this.A.get(SimpleLog.LOG_LEVEL_DEBUG);
    }

    public int q() {
        return this.i;
    }

    public boolean r() {
        return this.A.get(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public Map<String, String> s() {
        return this.j;
    }

    public boolean t() {
        return this.j != null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMetaInfo(");
        stringBuilder.append("id:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.b);
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("title:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("description:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (m()) {
            stringBuilder.append(", ");
            stringBuilder.append("notifyType:");
            stringBuilder.append(this.f);
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("url:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (p()) {
            stringBuilder.append(", ");
            stringBuilder.append("passThrough:");
            stringBuilder.append(this.h);
        }
        if (r()) {
            stringBuilder.append(", ");
            stringBuilder.append("notifyId:");
            stringBuilder.append(this.i);
        }
        if (t()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        }
        if (u()) {
            stringBuilder.append(", ");
            stringBuilder.append("internal:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        if (w()) {
            stringBuilder.append(", ");
            stringBuilder.append("ignoreRegInfo:");
            stringBuilder.append(this.l);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public boolean u() {
        return this.k != null;
    }

    public boolean v() {
        return this.l;
    }

    public boolean w() {
        return this.A.get(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    public void x() {
        if (this.a == null) {
            throw new g(new StringBuilder("Required field 'id' was not present! Struct: ").append(toString()).toString());
        }
    }
}
