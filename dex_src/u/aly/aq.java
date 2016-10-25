package u.aly;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ActiveUser.java
public class aq implements Serializable, Cloneable, y<aq, e> {
    public static final Map<e, ah> c;
    private static final ca d;
    private static final br e;
    private static final br f;
    private static final Map<Class<? extends cc>, cd> g;
    public String a;
    public String b;

    // compiled from: ActiveUser.java
    private static class a extends ce<aq> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            aq aqVar = (aq) yVar;
            aqVar.a();
            d;
            buVar.a();
            if (aqVar.a != null) {
                buVar.a(e);
                buVar.a(aqVar.a);
            }
            if (aqVar.b != null) {
                buVar.a(f);
                buVar.a(aqVar.b);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            aq aqVar = (aq) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 11) {
                                aqVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 11) {
                                aqVar.b = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        default:
                            by.a(buVar, f.b);
                            break;
                    }
                } else {
                    buVar.e();
                    aqVar.a();
                    return;
                }
            }
        }
    }

    // compiled from: ActiveUser.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: ActiveUser.java
    private static class c extends cf<aq> {
        private c() {
        }

        public final /* bridge */ /* synthetic */ void a(bu buVar, y yVar) throws ac {
            aq aqVar = (aq) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(aqVar.a);
            cbVar.a(aqVar.b);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            aq aqVar = (aq) yVar;
            cb cbVar = (cb) buVar;
            aqVar.a = cbVar.p();
            aqVar.b = cbVar.p();
        }
    }

    // compiled from: ActiveUser.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: ActiveUser.java
    public enum e implements ad {
        PROVIDER((short) 1, "provider"),
        PUID((short) 2, "puid");
        private static final Map<String, u.aly.aq.e> c;
        private final short d;
        private final String e;

        static {
            String str = "provider";
            a = new u.aly.aq.e("PROVIDER", 0, (short) 1, "provider");
            str = "puid";
            b = new u.aly.aq.e("PUID", 1, (short) 2, "puid");
            f = new u.aly.aq.e[]{a, b};
            c = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.aq.e.class).iterator();
            while (it.hasNext()) {
                u.aly.aq.e eVar = (u.aly.aq.e) it.next();
                c.put(eVar.b(), eVar);
            }
        }

        public static u.aly.aq.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                default:
                    return null;
            }
        }

        public static u.aly.aq.e b(int i) {
            u.aly.aq.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.aq.e a(String str) {
            return (u.aly.aq.e) c.get(str);
        }

        private e(short s, String str) {
            this.d = s;
            this.e = str;
        }

        public final short a() {
            return this.d;
        }

        public final String b() {
            return this.e;
        }
    }

    static {
        d = new ca("ActiveUser");
        e = new br("provider", (byte) 11, (short) 1);
        f = new br("puid", (byte) 11, (short) 2);
        Map hashMap = new HashMap();
        g = hashMap;
        hashMap.put(ce.class, new b());
        g.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("provider", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("puid", (byte) 1, new ai((byte) 11)));
        c = Collections.unmodifiableMap(hashMap);
        ah.a(aq.class, c);
    }

    public aq(String str, String str2) {
        this();
        this.a = str;
        this.b = str2;
    }

    public final void a(bu buVar) throws ac {
        ((cd) g.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) g.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ActiveUser(");
        stringBuilder.append("provider:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("puid:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void a() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'provider' was not present! Struct: ").append(toString()).toString());
        } else if (this.b == null) {
            throw new bv(new StringBuilder("Required field 'puid' was not present! Struct: ").append(toString()).toString());
        }
    }
}
