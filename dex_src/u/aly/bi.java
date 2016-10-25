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

// compiled from: Page.java
public class bi implements Serializable, Cloneable, y<bi, e> {
    public static final Map<e, ah> d;
    private static final ca e;
    private static final br f;
    private static final br g;
    private static final Map<Class<? extends cc>, cd> h;
    public String a;
    public long b;
    byte c;

    // compiled from: Page.java
    private static class a extends ce<bi> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bi biVar = (bi) yVar;
            biVar.b();
            e;
            buVar.a();
            if (biVar.a != null) {
                buVar.a(f);
                buVar.a(biVar.a);
            }
            buVar.a(g);
            buVar.a(biVar.b);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bi biVar = (bi) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 11) {
                                biVar.a = buVar.p();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == 10) {
                                biVar.b = buVar.n();
                                biVar.a();
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
                    if (w.a(biVar.c, 0)) {
                        biVar.b();
                        return;
                    }
                    throw new bv(new StringBuilder("Required field 'duration' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    // compiled from: Page.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Page.java
    private static class c extends cf<bi> {
        private c() {
        }

        public final /* bridge */ /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bi biVar = (bi) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(biVar.a);
            cbVar.a(biVar.b);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bi biVar = (bi) yVar;
            cb cbVar = (cb) buVar;
            biVar.a = cbVar.p();
            biVar.b = cbVar.n();
            biVar.a();
        }
    }

    // compiled from: Page.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Page.java
    public enum e implements ad {
        PAGE_NAME((short) 1, "page_name"),
        DURATION((short) 2, "duration");
        private static final Map<String, u.aly.bi.e> c;
        private final short d;
        private final String e;

        static {
            String str = "page_name";
            a = new u.aly.bi.e("PAGE_NAME", 0, (short) 1, "page_name");
            str = "duration";
            b = new u.aly.bi.e("DURATION", 1, (short) 2, "duration");
            f = new u.aly.bi.e[]{a, b};
            c = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bi.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bi.e eVar = (u.aly.bi.e) it.next();
                c.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bi.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                default:
                    return null;
            }
        }

        public static u.aly.bi.e b(int i) {
            u.aly.bi.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bi.e a(String str) {
            return (u.aly.bi.e) c.get(str);
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
        e = new ca("Page");
        f = new br("page_name", (byte) 11, (short) 1);
        g = new br("duration", (byte) 10, (short) 2);
        Map hashMap = new HashMap();
        h = hashMap;
        hashMap.put(ce.class, new b());
        h.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("page_name", (byte) 1, new ai((byte) 11)));
        hashMap.put(e.b, new ah("duration", (byte) 1, new ai((byte) 10)));
        d = Collections.unmodifiableMap(hashMap);
        ah.a(bi.class, d);
    }

    public bi() {
        this.c = (byte) 0;
    }

    public final void a() {
        this.c = (byte) (this.c | 1);
    }

    public final void a(bu buVar) throws ac {
        ((cd) h.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) h.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Page(");
        stringBuilder.append("page_name:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("duration:");
        stringBuilder.append(this.b);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void b() throws ac {
        if (this.a == null) {
            throw new bv(new StringBuilder("Required field 'page_name' was not present! Struct: ").append(toString()).toString());
        }
    }
}
