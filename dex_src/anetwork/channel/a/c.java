package anetwork.channel.a;

import anet.channel.util.HttpConstant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: Taobao
public final class c {
    private static final String b;
    public a a;

    public c() {
        this.a = null;
        this.a = d.a();
    }

    public static Map<String, List<String>> a() {
        Map<String, List<String>> hashMap = new HashMap();
        hashMap.put(null, Arrays.asList(new String[]{"HTTP/1.1 200 OK"}));
        hashMap.put(HttpConstant.CACHE_CONTROL, Arrays.asList(new String[]{"no-store"}));
        return hashMap;
    }

    static {
        b = HttpConstant.CACHE_CONTROL.toLowerCase();
    }

    public static Map<String, List<String>> a(Map<String, List<String>> map) {
        if (map != null) {
            map.remove(b);
            map.put(HttpConstant.CACHE_CONTROL, Arrays.asList(new String[]{"no-store"}));
        }
        return map;
    }
}
