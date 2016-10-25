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

// compiled from: Traffic.java
public class bo implements Serializable, Cloneable, y<bo, e> {
    public static final Map<e, ah> d;
    private static final ca e;
    private static final br f;
    private static final br g;
    private static final Map<Class<? extends cc>, cd> h;
    public int a;
    public int b;
    byte c;

    // compiled from: Traffic.java
    private static class a extends ce<bo> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bo boVar = (bo) yVar;
            bo.c();
            e;
            buVar.a();
            buVar.a(f);
            buVar.a(boVar.a);
            buVar.a(g);
            buVar.a(boVar.b);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bo boVar = (bo) yVar;
            buVar.d();
            while (true) {
                br f = buVar.f();
                if (f.b != null) {
                    switch (f.c) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (f.b == (byte) 8) {
                                boVar.a = buVar.m();
                                boVar.a();
                            } else {
                                by.a(buVar, f.b);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (f.b == (byte) 8) {
                                boVar.b = buVar.m();
                                boVar.b();
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
                    if (!w.a(boVar.c, 0)) {
                        throw new bv(new StringBuilder("Required field 'upload_traffic' was not found in serialized data! Struct: ").append(toString()).toString());
                    } else if (w.a(boVar.c, 1)) {
                        bo.c();
                        return;
                    } else {
                        throw new bv(new StringBuilder("Required field 'download_traffic' was not found in serialized data! Struct: ").append(toString()).toString());
                    }
                }
            }
        }
    }

    // compiled from: Traffic.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: Traffic.java
    private static class c extends cf<bo> {
        private c() {
        }

        public final /* bridge */ /* synthetic */ void a(bu buVar, y yVar) throws ac {
            bo boVar = (bo) yVar;
            cb cbVar = (cb) buVar;
            cbVar.a(boVar.a);
            cbVar.a(boVar.b);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            bo boVar = (bo) yVar;
            cb cbVar = (cb) buVar;
            boVar.a = cbVar.m();
            boVar.a();
            boVar.b = cbVar.m();
            boVar.b();
        }
    }

    // compiled from: Traffic.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    // compiled from: Traffic.java
    public enum e implements ad {
        UPLOAD_TRAFFIC((short) 1, "upload_traffic"),
        DOWNLOAD_TRAFFIC((short) 2, "download_traffic");
        private static final Map<String, u.aly.bo.e> c;
        private final short d;
        private final String e;

        static {
            String str = "upload_traffic";
            a = new u.aly.bo.e("UPLOAD_TRAFFIC", 0, (short) 1, "upload_traffic");
            str = "download_traffic";
            b = new u.aly.bo.e("DOWNLOAD_TRAFFIC", 1, (short) 2, "download_traffic");
            f = new u.aly.bo.e[]{a, b};
            c = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bo.e.class).iterator();
            while (it.hasNext()) {
                u.aly.bo.e eVar = (u.aly.bo.e) it.next();
                c.put(eVar.b(), eVar);
            }
        }

        public static u.aly.bo.e a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                default:
                    return null;
            }
        }

        public static u.aly.bo.e b(int i) {
            u.aly.bo.e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bo.e a(String str) {
            return (u.aly.bo.e) c.get(str);
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
        e = new ca("Traffic");
        f = new br("upload_traffic", (byte) 8, (short) 1);
        g = new br("download_traffic", (byte) 8, (short) 2);
        Map hashMap = new HashMap();
        h = hashMap;
        hashMap.put(ce.class, new b());
        h.put(cf.class, new d());
        hashMap = new EnumMap(e.class);
        hashMap.put(e.a, new ah("upload_traffic", (byte) 1, new ai((byte) 8)));
        hashMap.put(e.b, new ah("download_traffic", (byte) 1, new ai((byte) 8)));
        d = Collections.unmodifiableMap(hashMap);
        ah.a(bo.class, d);
    }

    public bo() {
        this.c = (byte) 0;
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
        StringBuilder stringBuilder = new StringBuilder("Traffic(");
        stringBuilder.append("upload_traffic:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("download_traffic:");
        stringBuilder.append(this.b);
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }

    public static void c() throws ac {
    }
}
