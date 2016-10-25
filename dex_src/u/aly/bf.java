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

// compiled from: Latent.java
public class bf implements Serializable, Cloneable, y<bf, e> {
    public static final Map<e, ah> d;
    private static final ca e;
    private static final br f;
    private static final br g;
    private static final Map<Class<? extends cc>, cd> h;
    public int a;
    public long b;
    byte c;

    // compiled from: Latent.java
    private static class a extends ce<bf> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bf bfVar = (bf) yVar;
            bf.c();
            e;
            buVar.a();
            buVar.a(f);
            buVar.a(bfVar.a);
            buVar.a(g);
            buVar.a(bfVar.b);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bf bfVar = (bf) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 8) {
                                bfVar.a = buVar.m();
                                bfVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 10) {
                                bfVar.b = buVar.n();
                                bfVar.b();
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
                    if (!w.a(bfVar.c, 0)) {
                        throw new bv(new StringBuilder("Required field 'latency' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (w.a(bfVar.c, 1)) {
                        bf.c();
                        return;
                    } else {
                        throw new bv(new StringBuilder("Required field 'interval' was not found in serialized data! Struct: ").append(toString()).toString());
                    }
                }
            }
        }
    }

    // compiled from: Latent.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Latent.java
    private static class c extends cf<bf> {
        private c() {
        }

        public final /* bridge */ /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bf bfVar = (bf) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(bfVar.a);
            cbVar.a(bfVar.b);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bf bfVar = (bf) yVar;
            cb cbVar = (cb) buVar;
            bfVar.a = cbVar.m();
            bfVar.a();
            bfVar.b = cbVar.n();
            bfVar.b();
        }
    }

    // compiled from: Latent.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Latent.java
    public enum e implements ad {
        LATENCY((short) 1, "latency"),
        INTERVAL((short) 2, "interval");
        private static final Map<String, u.aly.bf.e> c;
        private final short d;
        private final String e;

        static {
            String str = "latency";
            a = new u.aly.bf.e("LATENCY", 0, (short) 1, "latency");
            str = "interval";
            b = new u.aly.bf.e("INTERVAL", 1, (short) 2, "interval");
            f = new u.aly.bf.e[]{a, b};
            c = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bf.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bf.e eVar = (u.aly.bf.e) it.next();
                c.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bf.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                default:
                    return null;
            }
        }

        public static u.aly.bf.e b(int i) {
            u.aly.bf.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bf.e a(String str) {
            return (u.aly.bf.e) c.get(str);
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
        e = new ca("Latent");
        f = new br("latency", (byte) 8, (short) 1);
        g = new br("interval", (byte) 10, (short) 2);
        Map hashMap = new HashMap();
        h = hashMap;
        hashMap.put(ce.class, new b());
        h.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("latency", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.b, new ah("interval", (byte) 1, new ai((byte) 10)));
        d = Collections.unmodifiableMap(hashMap);
        ah.a(bf.class, d);
    }

    public bf() {
        this.c = (byte) 0;
    }

    public bf(int i, long j) {
        this();
        this.a = i;
        a();
        this.b = j;
        b();
    }

    public final void a() {
        this.c = (byte) (this.c | 1);
    }

    public final void b() {
        this.c = (byte) (this.c | 2);
    }

    public final void a(bu buVar) throws ac {
        ((cd) h.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) h.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Latent(");
        stringBuilder.append("latency:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("interval:");
        stringBuilder.append(this.b);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public static void c() throws ac {
    }
}
