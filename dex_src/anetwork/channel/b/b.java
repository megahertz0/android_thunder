package anetwork.channel.b;

// compiled from: Taobao
public final class b {
    private static volatile boolean a;
    private static volatile boolean b;
    private static volatile boolean c;
    private static volatile boolean d;
    private static volatile a e;

    static {
        a = true;
        b = true;
        c = true;
        d = true;
    }

    public static void a() {
        a dVar = new d();
        e = dVar;
        dVar.a();
    }

    public static boolean b() {
        return a;
    }

    public static boolean c() {
        return b;
    }

    public static boolean d() {
        return d;
    }
}
