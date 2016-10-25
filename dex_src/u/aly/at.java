package u.aly;

import com.umeng.message.proguard.j;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ControlPolicy.java
public class at implements Serializable, Cloneable, y<at, e> {
    public static final Map<e, ah> b;
    private static final ca c;
    private static final br d;
    private static final Map<Class<? extends cc>, cd> e;
    public bf a;
    private e[] f;

    // compiled from: ControlPolicy.java
    private static class a extends ce<at> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            at atVar = (at) yVar;
            atVar.b();
            c;
            buVar.a();
            if (atVar.a != null && atVar.a()) {
                buVar.a(d);
                atVar.a.b(buVar);
            }
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            at atVar = (at) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == 12) {
                                atVar.a = new bf();
                                atVar.a.a(buVar);
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
                    atVar.b();
                    return;
                }
            }
        }
    }

    // compiled from: ControlPolicy.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: ControlPolicy.java
    private static class c extends cf<at> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            at atVar = (at) yVar;
            cb cbVar = (cb) buVar;
            BitSet bitSet = new BitSet();
            if (atVar.a()) {
                bitSet.set(0);
            }
            cbVar.a(bitSet, 1);
            if (atVar.a()) {
                atVar.a.b(cbVar);
            }
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            at atVar = (at) yVar;
            cb cbVar = (cb) buVar;
            if (cbVar.b(1).get(0)) {
                atVar.a = new bf();
                atVar.a.a(cbVar);
            }
        }
    }

    // compiled from: ControlPolicy.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: ControlPolicy.java
    public enum e implements ad {
        LATENT;
        private static final Map<String, u.aly.at.e> b;
        private final short c;
        private final String d;

        static {
            a = new u.aly.at.e("LATENT", "latent");
            e = new u.aly.at.e[]{a};
            b = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.at.e.class).iterator();
            while (it.hasNext()) {
                u.aly.at.e eVar = (u.aly.at.e) it.next();
                b.put(eVar.b(), eVar);
            }
        }

        public static u.aly.at.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                default:
                    return null;
            }
        }

        public static u.aly.at.e b(int i) {
            u.aly.at.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.at.e a(String str) {
            return (u.aly.at.e) b.get(str);
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
        c = new ca("ControlPolicy");
        d = new br("latent", (byte) 12, (short) 1);
        Map hashMap = new HashMap();
        e = hashMap;
        hashMap.put(ce.class, new b());
        e.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("latent", (byte) 2, new al(bf.class)));
        b = Collections.unmodifiableMap(hashMap);
        ah.a(at.class, b);
    }

    public at() {
        this.f = new e[]{e.a};
    }

    public final boolean a() {
        return this.a != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) e.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) e.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ControlPolicy(");
        if (a()) {
            stringBuilder.append("latent:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public final void b() throws ac {
        if (this.a != null) {
            bf.c();
        }
    }
}
