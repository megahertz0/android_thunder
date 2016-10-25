package u.aly;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

// compiled from: IdTracker.java
public final class ck {
    public static ck b;
    bb a;
    private final String c;
    private File d;
    private long e;
    private long f;
    private Set<a> g;
    private a h;

    // compiled from: IdTracker.java
    public static class a {
        Set<String> a;
        private Context b;

        public a(Context context) {
            this.a = new HashSet();
            this.b = context;
        }

        public final void a() {
            if (!this.a.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String str : this.a) {
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                this.b.getSharedPreferences("umeng_general_config", 0).edit().putString("invld_id", stringBuilder.toString()).commit();
            }
        }

        public final void b() {
            int i = 0;
            Object string = this.b.getSharedPreferences("umeng_general_config", 0).getString("invld_id", null);
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(",");
                if (split != null) {
                    int length = split.length;
                    while (i < length) {
                        CharSequence charSequence = split[i];
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.a.add(charSequence);
                        }
                        i++;
                    }
                }
            }
        }
    }

    private ck(Context context) {
        this.c = "umeng_it.cache";
        this.a = null;
        this.g = new HashSet();
        this.h = null;
        this.d = new File(context.getFilesDir(), "umeng_it.cache");
        this.f = 86400000;
        this.h = new a(context);
        this.h.b();
    }

    public static synchronized ck a(Context context) {
        ck ckVar;
        synchronized (ck.class) {
            try {
                if (b == null) {
                    ckVar = new ck(context);
                    b = ckVar;
                    ckVar.a(new cl(context));
                    b.a(new cn(context));
                    b.a(new p(context));
                    b.a(new cq(context));
                    b.a(new cp(context));
                    b.a(new bw(context));
                    b.a(new co());
                    ck ckVar2 = b;
                    bb e = ckVar2.e();
                    if (e != null) {
                        List<a> arrayList = new ArrayList(ckVar2.g.size());
                        synchronized (ckVar2) {
                            ckVar2.a = e;
                            for (a aVar : ckVar2.g) {
                                aVar.a(ckVar2.a);
                                if (!aVar.a()) {
                                    arrayList.add(aVar);
                                }
                            }
                            for (a aVar2 : arrayList) {
                                ckVar2.g.remove(aVar2);
                            }
                        }
                        ckVar2.c();
                    }
                }
                ckVar = b;
            } catch (Throwable th) {
            }
        }
        return ckVar;
    }

    private boolean a(a aVar) {
        boolean z;
        if (this.h.a.contains(aVar.a)) {
            z = false;
        } else {
            Object obj = 1;
        }
        return z ? this.g.add(aVar) : false;
    }

    public final void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.e >= this.f) {
            int i = 0;
            for (a aVar : this.g) {
                if (aVar.a()) {
                    String str;
                    int i2;
                    int i3;
                    ba baVar = aVar.c;
                    if (baVar == null) {
                        str = null;
                    } else {
                        str = baVar.a;
                    }
                    if (baVar == null) {
                        i2 = 0;
                    } else {
                        i2 = baVar.c;
                    }
                    String b = aVar.b();
                    if (b == null) {
                        b = null;
                    } else {
                        b = b.trim();
                        if (b.length() == 0) {
                            b = null;
                        } else if ("0".equals(b)) {
                            b = null;
                        } else if ("unknown".equals(b.toLowerCase(Locale.US))) {
                            b = null;
                        }
                    }
                    if (b == null || b.equals(str)) {
                        i2 = 0;
                    } else {
                        if (baVar == null) {
                            baVar = new ba();
                        }
                        baVar.a = b;
                        baVar.b = System.currentTimeMillis();
                        baVar.a();
                        baVar.c = i2 + 1;
                        baVar.b();
                        az azVar = new az();
                        azVar.a = aVar.a;
                        azVar.c = b;
                        azVar.b = str;
                        azVar.d = baVar.b;
                        azVar.b();
                        if (aVar.b == null) {
                            aVar.b = new ArrayList(2);
                        }
                        aVar.b.add(azVar);
                        if (aVar.b.size() > 10) {
                            aVar.b.remove(0);
                        }
                        aVar.c = baVar;
                        i2 = 1;
                    }
                    if (i2 != 0) {
                        if (!aVar.a()) {
                            a aVar2 = this.h;
                            aVar2.a.add(aVar.a);
                        }
                        i3 = 1;
                    } else {
                        i3 = i;
                    }
                    i = i3;
                }
            }
            if (i != 0) {
                c();
                this.h.a();
                d();
            }
            this.e = currentTimeMillis;
        }
    }

    private void c() {
        bb bbVar = new bb();
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (a aVar : this.g) {
            if (aVar.a()) {
                if (aVar.c != null) {
                    hashMap.put(aVar.a, aVar.c);
                }
                if (aVar.b != null && !aVar.b.isEmpty()) {
                    arrayList.addAll(aVar.b);
                }
            }
        }
        bbVar.b = arrayList;
        bbVar.a = hashMap;
        synchronized (this) {
            this.a = bbVar;
        }
    }

    public final void b() {
        boolean z = false;
        for (a aVar : this.g) {
            if (aVar.a()) {
                boolean z2;
                if (aVar.b == null || aVar.b.isEmpty()) {
                    z2 = z;
                } else {
                    aVar.b = null;
                    int i = 1;
                }
                z = z2;
            }
        }
        if (z) {
            this.a.a(false);
            d();
        }
    }

    private void d() {
        if (this.a != null) {
            y yVar = this.a;
            if (yVar != null) {
                try {
                    byte[] a;
                    synchronized (this) {
                        a = new ae().a(yVar);
                    }
                    if (a != null) {
                        u.a(this.d, a);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private bb e() {
        bb bbVar = null;
        if (!this.d.exists()) {
            return null;
        }
        try {
            InputStream fileInputStream = new FileInputStream(this.d);
            try {
                byte[] b = u.b(fileInputStream);
                y bbVar2 = new bb();
                new ab().a(bbVar2, b);
                u.c(fileInputStream);
                y yVar = bbVar2;
                return yVar;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            try {
                Exception e3;
                e3.printStackTrace();
                u.c(fileInputStream);
                return bbVar;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th2 = th3;
            u.c(fileInputStream);
            throw th2;
        }
    }
}
