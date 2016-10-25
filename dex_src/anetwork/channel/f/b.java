package anetwork.channel.f;

import com.umeng.a;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

// compiled from: Taobao
public class b {
    private static b f;
    public boolean a;
    public long b;
    public Set<String> c;
    public Set<String> d;
    public long e;

    private b() {
        this.a = false;
        this.b = 0;
        this.e = 0;
        if (this.c == null) {
            this.c = new HashSet();
        } else {
            this.c.clear();
        }
        if (this.d == null) {
            this.d = new HashSet();
        }
    }

    public static b a() {
        if (f == null) {
            synchronized (b.class) {
                if (f == null) {
                    f = new b();
                }
            }
        }
        return f;
    }

    public static String a(String str) {
        try {
            String path = new URL(str).getPath();
            return '/' == path.charAt(path.length() + -1) ? path.substring(0, path.length() - 1) : path;
        } catch (Exception e) {
            return a.d;
        }
    }
}
