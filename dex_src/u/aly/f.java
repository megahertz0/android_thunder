package u.aly;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: ViewPageTracker.java
public final class f {
    public final ArrayList<d> a;
    private final Map<String, Long> b;

    public f() {
        this.b = new HashMap();
        this.a = new ArrayList();
    }

    public final void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.b) {
                this.b.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public final void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Long l;
            synchronized (this.b) {
                l = (Long) this.b.remove(str);
            }
            if (l == null) {
                v.c("please call 'onPageStart(%s)' before onPageEnd", str);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - l.longValue();
            synchronized (this.a) {
                this.a.add(new d(str, currentTimeMillis));
            }
        }
    }

    public final void a() {
        String str = null;
        long j = 0;
        synchronized (this.b) {
            for (Entry entry : this.b.entrySet()) {
                String str2;
                long j2;
                if (((Long) entry.getValue()).longValue() > j) {
                    long longValue = ((Long) entry.getValue()).longValue();
                    str2 = (String) entry.getKey();
                    j2 = longValue;
                } else {
                    j2 = j;
                    str2 = str;
                }
                str = str2;
                j = j2;
            }
        }
        if (str != null) {
            b(str);
        }
    }

    public static List<bi> a(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString("activities", BuildConfig.VERSION_NAME);
        if (BuildConfig.VERSION_NAME.equals(string)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            String[] split = string.split(";");
            for (Object obj : split) {
                if (!TextUtils.isEmpty(obj)) {
                    arrayList.add(new k(obj));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList.size() > 0 ? arrayList : null;
    }
}
