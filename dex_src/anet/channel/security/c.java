package anet.channel.security;

// compiled from: Taobao
public class c {
    private static volatile ISecurity a;

    static {
        a = null;
    }

    public static void a(ISecurity iSecurity) {
        a = iSecurity;
    }

    public static ISecurity a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }
}
