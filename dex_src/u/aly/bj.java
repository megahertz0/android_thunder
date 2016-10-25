package u.aly;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: PropertyValue.java
public class bj extends af<bj, a> {
    public static final Map<a, ah> a;
    private static final ca d;
    private static final br e;
    private static final br f;

    // compiled from: PropertyValue.java
    public enum a implements ad {
        STRING_VALUE((short) 1, "string_value"),
        LONG_VALUE((short) 2, "long_value");
        private static final Map<String, u.aly.bj.a> c;
        private final short d;
        private final String e;

        static {
            String str = "string_value";
            a = new u.aly.bj.a("STRING_VALUE", 0, (short) 1, "string_value");
            str = "long_value";
            b = new u.aly.bj.a("LONG_VALUE", 1, (short) 2, "long_value");
            f = new u.aly.bj.a[]{a, b};
            c = new HashMap();
            Iterator it = EnumSet.allOf(u.aly.bj.a.class).iterator();
            while (it.hasNext()) {
                u.aly.bj.a aVar = (u.aly.bj.a) it.next();
                c.put(aVar.b(), aVar);
            }
        }

        public static u.aly.bj.a a(int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return a;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return b;
                default:
                    return null;
            }
        }

        public static u.aly.bj.a b(int i) {
            u.aly.bj.a a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException(new StringBuilder("Field ").append(i).append(" doesn't exist!").toString());
        }

        public static u.aly.bj.a a(String str) {
            return (u.aly.bj.a) c.get(str);
        }

        private a(short s, String str) {
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

    protected final /* synthetic */ br a(ad adVar) {
        a aVar = (a) adVar;
        switch (q.a[aVar.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return e;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return f;
            default:
                throw new IllegalArgumentException(new StringBuilder("Unknown field id ").append(aVar).toString());
        }
    }

    static {
        d = new ca("PropertyValue");
        e = new br("string_value", (byte) 11, (short) 1);
        f = new br("long_value", (byte) 10, (short) 2);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new ah("string_value", (byte) 3, new ai((byte) 11)));
        enumMap.put(a.b, new ah("long_value", (byte) 3, new ai((byte) 10)));
        a = Collections.unmodifiableMap(enumMap);
        ah.a(bj.class, a);
    }

    protected final Object a(bu buVar, br brVar) throws ac {
        a a = a.a(brVar.c);
        if (a == null) {
            return null;
        }
        switch (q.a[a.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                if (brVar.b == e.b) {
                    return buVar.p();
                }
                by.a(buVar, brVar.b);
                return null;
            case SimpleLog.LOG_LEVEL_DEBUG:
                if (brVar.b == f.b) {
                    return Long.valueOf(buVar.n());
                }
                by.a(buVar, brVar.b);
                return null;
            default:
                throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
        }
    }

    protected final void c(bu buVar) throws ac {
        switch (q.a[((a) this.c).ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                buVar.a((String) this.b);
            case SimpleLog.LOG_LEVEL_DEBUG:
                buVar.a(((Long) this.b).longValue());
            default:
                throw new IllegalStateException(new StringBuilder("Cannot write union with unknown field ").append(this.c).toString());
        }
    }

    protected final Object a(bu buVar, short s) throws ac {
        a a = a.a((int) s);
        if (a != null) {
            switch (q.a[a.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    return buVar.p();
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return Long.valueOf(buVar.n());
                default:
                    throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
            }
        }
        throw new bv(new StringBuilder("Couldn't find a field with field id ").append(s).toString());
    }

    protected final void d(bu buVar) throws ac {
        switch (q.a[((a) this.c).ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                buVar.a((String) this.b);
            case SimpleLog.LOG_LEVEL_DEBUG:
                buVar.a(((Long) this.b).longValue());
            default:
                throw new IllegalStateException(new StringBuilder("Cannot write union with unknown field ").append(this.c).toString());
        }
    }

    protected final ca a() {
        return d;
    }

    public final void a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.c = a.a;
        this.b = str;
    }

    public final void a(long j) {
        this.c = a.b;
        this.b = Long.valueOf(j);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof bj)) {
            return false;
        }
        bj bjVar = (bj) obj;
        return bjVar != null && b() == bjVar.b() && c().equals(bjVar.c());
    }

    public int hashCode() {
        return 0;
    }

    protected final /* synthetic */ ad a(short s) {
        return a.b(s);
    }
}
