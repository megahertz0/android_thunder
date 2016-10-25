package u.aly;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.ReportPolicy;
import java.io.File;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

// compiled from: ImprintHandler.java
public final class cm {
    static final byte[] a;
    private static cm f;
    di b;
    a c;
    bc d;
    Context e;

    // compiled from: ImprintHandler.java
    public static class a {
        int a;
        int b;
        int c;
        String d;
        int e;
        int f;
        private int g;
        private int h;
        private int i;
        private String j;

        a() {
            this.g = -1;
            this.a = -1;
            this.b = -1;
            this.h = -1;
            this.i = -1;
            this.j = null;
            this.c = -1;
            this.d = null;
            this.e = -1;
            this.f = -1;
        }

        public final void a(bc bcVar) {
            if (bcVar != null) {
                this.g = a(bcVar, "defcon");
                this.a = a(bcVar, "latent");
                this.b = a(bcVar, "codex");
                this.h = a(bcVar, "report_policy");
                this.i = a(bcVar, "report_interval");
                this.j = b(bcVar, "client_test");
                this.c = a(bcVar, "test_report_interval");
                this.d = b(bcVar, "umid");
                this.e = a(bcVar, "integrated_test");
                this.f = a(bcVar, "latent_hours");
            }
        }

        public final int a() {
            return (this.g != -1 && this.g <= 3 && this.g >= 0) ? this.g : 0;
        }

        public final int[] b() {
            if (this.h == -1 || !ReportPolicy.a(this.h)) {
                return new int[]{-1, -1};
            }
            if (this.i == -1 || this.i < 90 || this.i > 86400) {
                this.i = 90;
            }
            return new int[]{this.h, this.i * 1000};
        }

        public final String c() {
            return (this.j == null || !m.a(this.j)) ? null : this.j;
        }

        public final int a(int i) {
            return (this.c == -1 || this.c < 90 || this.c > 86400) ? i : this.c * 1000;
        }

        private static int a(bc bcVar, String str) {
            if (bcVar == null || !bcVar.a()) {
                return -1;
            }
            bd bdVar = (bd) bcVar.a.get(str);
            if (bdVar == null || TextUtils.isEmpty(bdVar.a)) {
                return -1;
            }
            try {
                return Integer.parseInt(bdVar.a.trim());
            } catch (Exception e) {
                return -1;
            }
        }

        private static String b(bc bcVar, String str) {
            if (bcVar == null || !bcVar.a()) {
                return null;
            }
            bd bdVar = (bd) bcVar.a.get(str);
            return (bdVar == null || TextUtils.isEmpty(bdVar.a)) ? null : bdVar.a;
        }
    }

    static {
        a = "pbl0".getBytes();
    }

    private cm(Context context) {
        this.c = new a();
        this.d = null;
        this.e = context;
    }

    public static synchronized cm a(Context context) {
        InputStream openFileInput;
        Exception e;
        Throwable th;
        byte[] b;
        bc bcVar;
        cm cmVar;
        InputStream inputStream = null;
        synchronized (cm.class) {
            if (f == null) {
                cm cmVar2 = new cm(context);
                f = cmVar2;
                if (new File(cmVar2.e.getFilesDir(), ".imprint").exists()) {
                    try {
                        openFileInput = cmVar2.e.openFileInput(".imprint");
                    } catch (Exception e2) {
                        e = e2;
                        openFileInput = null;
                        try {
                            e.printStackTrace();
                            u.c(openFileInput);
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream = openFileInput;
                            u.c(inputStream);
                            throw th;
                        }
                        if (b != null) {
                            bcVar = new bc();
                            new ab().a(bcVar, b);
                            cmVar2.d = bcVar;
                            cmVar2.c.a(bcVar);
                        }
                        cmVar = f;
                        return cmVar;
                    } catch (Throwable th3) {
                        th = th3;
                        u.c(inputStream);
                        throw th;
                    }
                    try {
                        b = u.b(openFileInput);
                        u.c(openFileInput);
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        u.c(openFileInput);
                        if (b != null) {
                            bcVar = new bc();
                            new ab().a(bcVar, b);
                            cmVar2.d = bcVar;
                            cmVar2.c.a(bcVar);
                        }
                        cmVar = f;
                        return cmVar;
                    }
                    if (b != null) {
                        try {
                            bcVar = new bc();
                            new ab().a(bcVar, b);
                            cmVar2.d = bcVar;
                            cmVar2.c.a(bcVar);
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            }
            cmVar = f;
        }
        return cmVar;
    }

    public static String a(bc bcVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : new TreeMap(bcVar.a).entrySet()) {
            stringBuilder.append((String) entry.getKey());
            if (((bd) entry.getValue()).a()) {
                stringBuilder.append(((bd) entry.getValue()).a);
            }
            stringBuilder.append(((bd) entry.getValue()).b);
            stringBuilder.append(((bd) entry.getValue()).c);
        }
        stringBuilder.append(bcVar.b);
        return u.a(stringBuilder.toString()).toLowerCase(Locale.US);
    }

    static bc a(bc bcVar, bc bcVar2) {
        if (bcVar2 != null) {
            Map map = bcVar.a;
            for (Entry entry : bcVar2.a.entrySet()) {
                if (((bd) entry.getValue()).a()) {
                    map.put(entry.getKey(), entry.getValue());
                } else {
                    map.remove(entry.getKey());
                }
            }
            bcVar.b = bcVar2.b;
            bcVar.b();
            bcVar.c = a(bcVar);
        }
        return bcVar;
    }

    public final synchronized bc a() {
        return this.d;
    }
}
