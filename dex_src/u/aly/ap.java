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

// compiled from: ActivateMsg.java
public class ap implements Serializable, Cloneable, y<ap, e> {
    public static final Map<e, ah> c;
    private static final ca d;
    private static final br e;
    private static final Map<Class<? extends cc>, cd> f;
    public long a;
    byte b;

    // compiled from: ActivateMsg.java
    private static class a extends ce<ap> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            ap apVar = (ap) yVar;
            ap.b();
            d;
            buVar.a();
            buVar.a(e);
            buVar.a(apVar.a);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            ap apVar = (ap) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 10) {
                                apVar.a = buVar.n();
                                apVar.a();
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
                    if (w.a(apVar.b, 0)) {
                        ap.b();
                        return;
                    }
                    throw new bv(new StringBuilder("Required field 'ts' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    // compiled from: ActivateMsg.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: ActivateMsg.java
    private static class c extends cf<ap> {
        private c() {
        }

        public final /* bridge */ /* synthetic */ void a(bu buVar, y yVar) throws ac {
            ((cb) buVar).a(((ap) yVar).a);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            ap apVar = (ap) yVar;
            apVar.a = ((cb) buVar).n();
            apVar.a();
        }
    }

    // compiled from: ActivateMsg.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: ActivateMsg.java
    public enum e implements ad {
        TS;
        private static final Map<String, u.aly.ap.e> b;
        private final short c;
        private final String d;

        static {
            a = new u.aly.ap.e("TS", "ts");
            e = new u.aly.ap.e[]{a};
            b = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.ap.e.class).iterator();
            while (it.hasNext()) {
                u.aly.ap.e eVar = (u.aly.ap.e) it.next();
                b.put(eVar.b(), eVar);
            }
        }

        public static u.aly.ap.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                default:
                    return null;
            }
        }

        public static u.aly.ap.e b(int i) {
            u.aly.ap.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.ap.e a(String str) {
            return (u.aly.ap.e) b.get(str);
        }

        private e() {
            this.c = (short) 1;
            this.d = r3;
        }

        public final short a() {
            return this.c;
        }

        public final String b() {
            return this.d;
        }
    }

    static {
        d = new ca("ActivateMsg");
        e = new br("ts", (byte) 10, (short) 1);
        Map hashMap = new HashMap();
        f = hashMap;
        hashMap.put(ce.class, new b());
        f.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("ts", (byte) 1, new ai((byte) 10)));
        c = Collections.unmodifiableMap(hashMap);
        ah.a(ap.class, c);
    }

    public ap() {
        this.b = (byte) 0;
    }

    public ap(long j) {
        this();
        this.a = j;
        a();
    }

    public final void a() {
        this.b = (byte) (this.b | 1);
    }

    public final void a(bu buVar) throws ac {
        ((cd) f.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) f.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ActivateMsg(");
        stringBuilder.append("ts:");
        stringBuilder.append(this.a);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public static void b() throws ac {
    }
}
