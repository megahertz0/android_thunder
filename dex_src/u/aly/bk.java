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

// compiled from: Resolution.java
public class bk implements Serializable, Cloneable, y<bk, e> {
    public static final Map<e, ah> d;
    private static final ca e;
    private static final br f;
    private static final br g;
    private static final Map<Class<? extends cc>, cd> h;
    public int a;
    public int b;
    byte c;

    // compiled from: Resolution.java
    private static class a extends ce<bk> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bk bkVar = (bk) yVar;
            bk.c();
            e;
            buVar.a();
            buVar.a(f);
            buVar.a(bkVar.a);
            buVar.a(g);
            buVar.a(bkVar.b);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bk bkVar = (bk) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 8) {
                                bkVar.a = buVar.m();
                                bkVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 8) {
                                bkVar.b = buVar.m();
                                bkVar.b();
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
                    if (!w.a(bkVar.c, 0)) {
                        throw new bv(new StringBuilder("Required field 'height' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (w.a(bkVar.c, 1)) {
                        bk.c();
                        return;
                    } else {
                        throw new bv(new StringBuilder("Required field 'width' was not found in serialized data! Struct: ").append(toString()).toString());
                    }
                }
            }
        }
    }

    // compiled from: Resolution.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Resolution.java
    private static class c extends cf<bk> {
        private c() {
        }

        public final /* bridge */ /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bk bkVar = (bk) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(bkVar.a);
            cbVar.a(bkVar.b);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bk bkVar = (bk) yVar;
            cb cbVar = (cb) buVar;
            bkVar.a = cbVar.m();
            bkVar.a();
            bkVar.b = cbVar.m();
            bkVar.b();
        }
    }

    // compiled from: Resolution.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Resolution.java
    public enum e implements ad {
        HEIGHT((short) 1, "height"),
        WIDTH((short) 2, "width");
        private static final Map<String, u.aly.bk.e> c;
        private final short d;
        private final String e;

        static {
            String str = "height";
            a = new u.aly.bk.e("HEIGHT", 0, (short) 1, "height");
            str = "width";
            b = new u.aly.bk.e("WIDTH", 1, (short) 2, "width");
            f = new u.aly.bk.e[]{a, b};
            c = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bk.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bk.e eVar = (u.aly.bk.e) it.next();
                c.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bk.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                default:
                    return null;
            }
        }

        public static u.aly.bk.e b(int i) {
            u.aly.bk.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bk.e a(String str) {
            return (u.aly.bk.e) c.get(str);
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
        e = new ca("Resolution");
        f = new br("height", (byte) 8, (short) 1);
        g = new br("width", (byte) 8, (short) 2);
        Map hashMap = new HashMap();
        h = hashMap;
        hashMap.put(ce.class, new b());
        h.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("height", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.b, new ah("width", (byte) 1, new ai((byte) 8)));
        d = Collections.unmodifiableMap(hashMap);
        ah.a(bk.class, d);
    }

    public bk() {
        this.c = (byte) 0;
    }

    public bk(int i, int i2) {
        this();
        this.a = i;
        a();
        this.b = i2;
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
        StringBuilder stringBuilder = new StringBuilder("Resolution(");
        stringBuilder.append("height:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("width:");
        stringBuilder.append(this.b);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public static void c() throws ac {
    }
}
