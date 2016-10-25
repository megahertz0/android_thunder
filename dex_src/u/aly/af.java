package u.aly;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

// compiled from: TUnion.java
public abstract class af<T extends af<?, ?>, F extends ad> implements y<T, F> {
    private static final Map<Class<? extends cc>, cd> a;
    protected Object b;
    protected F c;

    // compiled from: TUnion.java
    private static class a extends ce<af> {
        private a() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            af afVar = (af) yVar;
            if (afVar.b() == null || afVar.c() == null) {
                throw new bv("Cannot write a TUnion with no set value!");
            }
            afVar.a();
            buVar.a();
            buVar.a(afVar.a(afVar.c));
            afVar.c(buVar);
            buVar.c();
            buVar.b();
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            af afVar = (af) yVar;
            afVar.c = null;
            afVar.b = null;
            buVar.d();
            br f = buVar.f();
            afVar.b = afVar.a(buVar, f);
            if (afVar.b != null) {
                afVar.c = afVar.a(f.c);
            }
            buVar.f();
            buVar.e();
        }
    }

    // compiled from: TUnion.java
    private static class b implements cd {
        private b() {
        }

        public final /* synthetic */ cc a() {
            return new a();
        }
    }

    // compiled from: TUnion.java
    private static class c extends cf<af> {
        private c() {
        }

        public final /* synthetic */ void a(bu buVar, y yVar) throws ac {
            af afVar = (af) yVar;
            if (afVar.b() == null || afVar.c() == null) {
                throw new bv("Cannot write a TUnion with no set value!");
            }
            buVar.a(afVar.c.a());
            afVar.d(buVar);
        }

        public final /* synthetic */ void b(bu buVar, y yVar) throws ac {
            af afVar = (af) yVar;
            afVar.c = null;
            afVar.b = null;
            short l = buVar.l();
            afVar.b = afVar.a(buVar, l);
            if (afVar.b != null) {
                afVar.c = afVar.a(l);
            }
        }
    }

    // compiled from: TUnion.java
    private static class d implements cd {
        private d() {
        }

        public final /* synthetic */ cc a() {
            return new c();
        }
    }

    protected abstract Object a(bu buVar, br brVar) throws ac;

    protected abstract Object a(bu buVar, short s) throws ac;

    protected abstract F a(short s);

    protected abstract br a(F f);

    protected abstract ca a();

    protected abstract void c(bu buVar) throws ac;

    protected abstract void d(bu buVar) throws ac;

    protected af() {
        this.c = null;
        this.b = null;
    }

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put(ce.class, new b());
        a.put(cf.class, new d());
    }

    public final F b() {
        return this.c;
    }

    public final Object c() {
        return this.b;
    }

    public final boolean d() {
        return this.c != null;
    }

    public final void a(bu buVar) throws ac {
        ((cd) a.get(buVar.s())).a().b(buVar, this);
    }

    public final void b(bu buVar) throws ac {
        ((cd) a.get(buVar.s())).a().a(buVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" ");
        if (this.c != null) {
            Object obj = this.b;
            stringBuilder.append(a(this.c).a);
            stringBuilder.append(":");
            if (obj instanceof ByteBuffer) {
                aa.a((ByteBuffer) obj, stringBuilder);
            } else {
                stringBuilder.append(obj.toString());
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }
}
