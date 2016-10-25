package org.apache.thrift.meta_data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class b implements Serializable {
    private static Map<Class<? extends org.apache.thrift.b>, Map<?, b>> d;
    public final String a;
    public final byte b;
    public final c c;

    static {
        d = new HashMap();
    }

    public b(String str, byte b, c cVar) {
        this.a = str;
        this.b = b;
        this.c = cVar;
    }

    public static void a(Class<? extends org.apache.thrift.b> cls, Map<?, b> map) {
        d.put(cls, map);
    }
}
