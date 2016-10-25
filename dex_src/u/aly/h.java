package u.aly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: UEKV.java
public final class h extends ax implements dc {
    public h(String str, Map<String, Object> map, long j, int i) {
        this.a = str;
        a(System.currentTimeMillis());
        if (map.size() > 0) {
            Iterator it = map.entrySet().iterator();
            Map hashMap = new HashMap();
            int i2 = 0;
            while (i2 < 10 && it.hasNext()) {
                Entry entry = (Entry) it.next();
                bj bjVar = new bj();
                Object value = entry.getValue();
                if (value instanceof String) {
                    bjVar.a((String) value);
                } else if (value instanceof Long) {
                    bjVar.a(((Long) value).longValue());
                } else if (value instanceof Integer) {
                    bjVar.a(((Integer) value).longValue());
                } else if (value instanceof Float) {
                    bjVar.a(((Float) value).longValue());
                } else if (value instanceof Double) {
                    bjVar.a(((Double) value).longValue());
                }
                if (bjVar.d()) {
                    hashMap.put(entry.getKey(), bjVar);
                    i2++;
                }
            }
            this.b = hashMap;
        }
        if (i <= 0) {
            i = 1;
        }
        a(i);
        if (j > 0) {
            this.c = j;
            b();
        }
    }

    public static g a(String str, String str2, Map<String, Object> map) {
        g gVar = new g();
        gVar.b = str;
        gVar.c = str2;
        gVar.d = map;
        return gVar;
    }

    public static String a(String str, String str2) {
        return str + str2;
    }

    public final void a(bp bpVar, String str) {
        if (bpVar.b() > 0) {
            for (be beVar : bpVar.f) {
                if (str.equals(beVar.a)) {
                    break;
                }
            }
        }
        be beVar2 = null;
        if (beVar2 == null) {
            beVar2 = new be();
            beVar2.a = str;
            bpVar.a(beVar2);
        }
        if (beVar2.c == null) {
            beVar2.c = new ArrayList();
        }
        beVar2.c.add(this);
    }
}
