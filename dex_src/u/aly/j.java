package u.aly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: UGKV.java
public final class j extends ax implements dc {
    public j(String str, Map<String, Object> map) {
        this.a = str;
        a(System.currentTimeMillis());
        if (map.size() > 0) {
            Iterator it = map.entrySet().iterator();
            Map hashMap = new HashMap();
            int i = 0;
            while (i < 10 && it.hasNext()) {
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
                    i++;
                }
            }
            this.b = hashMap;
        }
        a(this.d > 0 ? this.d : 1);
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
        if (beVar2.d == null) {
            beVar2.d = new ArrayList();
        }
        beVar2.d.add(this);
    }
}
