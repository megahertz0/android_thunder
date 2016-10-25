package com.xiaomi.smack;

import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.packet.h;
import com.xiaomi.smack.util.g;

public class k {

    public static class b extends d {
        private a c;

        public static class a {
            public static final com.xiaomi.smack.k.b.a a;
            public static final com.xiaomi.smack.k.b.a b;
            private String c;

            static {
                a = new com.xiaomi.smack.k.b.a("result");
                b = new com.xiaomi.smack.k.b.a("error");
            }

            private a(String str) {
                this.c = str;
            }

            public static com.xiaomi.smack.k.b.a a(String str) {
                if (str == null) {
                    return null;
                }
                String toLowerCase = str.toLowerCase();
                return b.toString().equals(toLowerCase) ? b : a.toString().equals(toLowerCase) ? a : null;
            }

            public String toString() {
                return this.c;
            }
        }

        public String a() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<bind ");
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
                stringBuilder.append(" chid=\"").append(g.a(l())).append("\" ");
            }
            if (this.c == null) {
                stringBuilder.append("type=\"result\">");
            } else {
                stringBuilder.append("type=\"").append(b()).append("\">");
            }
            if (q() != null) {
                for (com.xiaomi.smack.packet.a aVar : q()) {
                    stringBuilder.append(aVar.d());
                }
            }
            h p = p();
            if (p != null) {
                stringBuilder.append(p.d());
            }
            stringBuilder.append("</bind>");
            return stringBuilder.toString();
        }

        public void a(a aVar) {
            if (aVar == null) {
                this.c = a.a;
            } else {
                this.c = aVar;
            }
        }

        public a b() {
            return this.c;
        }
    }

    public void a(com.xiaomi.push.service.x.b bVar, String str, a aVar) {
        d aVar2 = new a(bVar, str, aVar);
        aVar.a(aVar2);
        com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("SMACK: bind id=").append(aVar2.k()).toString());
    }
}
