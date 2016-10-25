package anetwork.channel.a;

import anet.channel.util.HttpConstant;
import anetwork.channel.entity.j;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// compiled from: Taobao
public final class d {
    private static volatile a a;
    private static Set<String> b;

    static {
        a = null;
        Set hashSet = new HashSet();
        b = hashSet;
        hashSet.add("jpg");
        b.add("gif");
        b.add("png");
        b.add("webp");
    }

    public static a a() {
        return a;
    }

    public static boolean a(j jVar) {
        if (a == null) {
            return false;
        }
        Map d = jVar.d();
        String str = (String) d.get("f_refer");
        if (str != null && str.equals("wv_h5")) {
            try {
                str = anet.channel.util.d.a(new URL(jVar.b));
                if (str != null && b.contains(str)) {
                    return true;
                }
                str = (String) d.get(HttpConstant.ACCEPT);
                if (str != null && str.contains("image/")) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
