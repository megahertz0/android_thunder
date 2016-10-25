package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// compiled from: Caretaker.java
public final class cy {
    boolean a;
    private final String b;
    private SharedPreferences c;
    private Map<String, ArrayList<g>> d;

    public cy(Context context) {
        this.b = "umeng_event_snapshot";
        this.a = false;
        this.d = new HashMap();
        this.c = context.getSharedPreferences("umeng_event_snapshot", 0);
    }

    public final void a(String str, g gVar) {
        if (this.a) {
            c(str);
        }
        if (this.d.containsKey(str)) {
            ((ArrayList) this.d.get(str)).add(gVar);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(gVar);
            this.d.put(str, arrayList);
        }
        if (this.a) {
            b(str);
        }
    }

    public final g a(String str) {
        g gVar;
        if (this.a) {
            c(str);
        }
        if (this.d.containsKey(str)) {
            ArrayList arrayList = (ArrayList) this.d.get(str);
            if (arrayList.size() > 0) {
                gVar = (g) arrayList.remove(arrayList.size() - 1);
                if (this.a) {
                    b(str);
                }
                return gVar;
            }
        }
        gVar = null;
        if (this.a) {
            b(str);
        }
        return gVar;
    }

    private void b(String str) {
        String str2 = null;
        if (this.d.containsKey(str)) {
            Serializable serializable = (ArrayList) this.d.get(str);
            while (serializable.size() > 4) {
                serializable.remove(0);
            }
            str2 = dg.a(serializable);
        }
        this.c.edit().putString(str, str2).commit();
    }

    private boolean c(String str) {
        if (this.d.containsKey(str)) {
            return true;
        }
        String string = this.c.getString(str, null);
        if (string != null) {
            ArrayList arrayList = (ArrayList) dg.a(string);
            if (arrayList != null) {
                this.d.put(str, arrayList);
                return true;
            }
        }
        return false;
    }
}
