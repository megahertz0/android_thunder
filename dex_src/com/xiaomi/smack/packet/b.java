package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.xiaomi.smack.util.g;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.android.agoo.intent.IntentUtil;

public class b extends d {
    private a c;
    private final Map<String, String> d;

    public static class a {
        public static final com.xiaomi.smack.packet.b.a a;
        public static final com.xiaomi.smack.packet.b.a b;
        public static final com.xiaomi.smack.packet.b.a c;
        public static final com.xiaomi.smack.packet.b.a d;
        public static final com.xiaomi.smack.packet.b.a e;
        private String f;

        static {
            a = new com.xiaomi.smack.packet.b.a("get");
            b = new com.xiaomi.smack.packet.b.a("set");
            c = new com.xiaomi.smack.packet.b.a("result");
            d = new com.xiaomi.smack.packet.b.a("error");
            e = new com.xiaomi.smack.packet.b.a(IntentUtil.AGOO_COMMAND);
        }

        private a(String str) {
            this.f = str;
        }

        public static com.xiaomi.smack.packet.b.a a(String str) {
            if (str == null) {
                return null;
            }
            String toLowerCase = str.toLowerCase();
            return a.toString().equals(toLowerCase) ? a : b.toString().equals(toLowerCase) ? b : d.toString().equals(toLowerCase) ? d : c.toString().equals(toLowerCase) ? c : e.toString().equals(toLowerCase) ? e : null;
        }

        public String toString() {
            return this.f;
        }
    }

    public b() {
        this.c = a.a;
        this.d = new HashMap();
    }

    public b(Bundle bundle) {
        super(bundle);
        this.c = a.a;
        this.d = new HashMap();
        if (bundle.containsKey("ext_iq_type")) {
            this.c = a.a(bundle.getString("ext_iq_type"));
        }
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<iq ");
        if (k() != null) {
            stringBuilder.append(new StringBuilder("id=\"").append(k()).append("\" ").toString());
        }
        if (m() != null) {
            stringBuilder.append("to=\"").append(g.a(m())).append("\" ");
        }
        if (n() != null) {
            stringBuilder.append("from=\"").append(g.a(n())).append("\" ");
        }
        if (l() != null) {
            stringBuilder.append("chid=\"").append(g.a(l())).append("\" ");
        }
        for (Entry entry : this.d.entrySet()) {
            stringBuilder.append(g.a((String) entry.getKey())).append("=\"");
            stringBuilder.append(g.a((String) entry.getValue())).append("\" ");
        }
        if (this.c == null) {
            stringBuilder.append("type=\"get\">");
        } else {
            stringBuilder.append("type=\"").append(b()).append("\">");
        }
        String d = d();
        if (d != null) {
            stringBuilder.append(d);
        }
        stringBuilder.append(s());
        h p = p();
        if (p != null) {
            stringBuilder.append(p.d());
        }
        stringBuilder.append("</iq>");
        return stringBuilder.toString();
    }

    public synchronized String a(String str) {
        return (String) this.d.get(str);
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.c = a.a;
        } else {
            this.c = aVar;
        }
    }

    public synchronized void a(String str, String str2) {
        this.d.put(str, str2);
    }

    public synchronized void a(Map<String, String> map) {
        this.d.putAll(map);
    }

    public a b() {
        return this.c;
    }

    public Bundle c() {
        Bundle c = super.c();
        if (this.c != null) {
            c.putString("ext_iq_type", this.c.toString());
        }
        return c;
    }

    public String d() {
        return null;
    }
}
