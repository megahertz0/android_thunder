package u.aly;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// compiled from: FieldMetaData.java
public final class ah implements Serializable {
    private static Map<Class<? extends y>, Map<? extends ad, ah>> d;
    public final String a;
    public final byte b;
    public final ai c;

    static {
        d = new HashMap();
    }

    public ah(String str, byte b, ai aiVar) {
        this.a = str;
        this.b = b;
        this.c = aiVar;
    }

    public static void a(Class<? extends y> cls, Map<? extends ad, ah> map) {
        d.put(cls, map);
    }
}
