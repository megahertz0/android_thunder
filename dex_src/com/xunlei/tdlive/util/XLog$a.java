package com.xunlei.tdlive.util;

import android.os.Environment;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

private class XLog$a {
    private String a;
    private boolean b;
    private String c;
    private long d;
    private String e;
    private String f;
    private String g;
    private XLog$b h;
    private long i;
    private File j;
    private int k;

    public XLog$a() {
        this.i = 20971520;
        this.h = XLog$b.a;
        this.e = BuildConfig.VERSION_NAME;
        this.g = XLog.a();
        this.f = XLog.b();
        this.j = null;
        this.k = 0;
        this.a = BuildConfig.VERSION_NAME;
        this.b = false;
        this.d = 0;
        this.c = BuildConfig.VERSION_NAME;
    }

    public long a() {
        return this.d;
    }

    public String b() {
        return this.c == null ? BuildConfig.VERSION_NAME : this.c;
    }

    public boolean c() {
        return this.b;
    }

    public String d() {
        return this.a == null ? BuildConfig.VERSION_NAME : this.a;
    }

    public String e() {
        return this.e == null ? BuildConfig.VERSION_NAME : this.e;
    }

    public XLog$b f() {
        return this.h;
    }

    public File g() {
        if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + this.f);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (this.j == null) {
                this.j = new File(file.getPath() + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()) + this.g);
            }
            while (this.j != null && this.j.length() >= this.i) {
                this.k++;
                this.j = new File(file.getPath() + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "." + this.k + "." + this.g);
            }
        }
        return this.j;
    }

    public boolean a(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                return a(new FileInputStream(file), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.io.InputStream r9, boolean r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.util.XLog$a.a(java.io.InputStream, boolean):boolean");
        /*
        this = this;
        r7 = 13;
        r6 = 10;
        r5 = -1;
        if (r9 == 0) goto L_0x0068;
    L_0x0007:
        r2 = "";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0067 }
        r1.<init>();	 Catch:{ Exception -> 0x0067 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0067 }
        r0.<init>();	 Catch:{ Exception -> 0x0067 }
    L_0x0014:
        r3 = r9.read();	 Catch:{ Exception -> 0x0067 }
        if (r3 == r5) goto L_0x011e;
    L_0x001a:
        r4 = r1.length();	 Catch:{ Exception -> 0x0067 }
        if (r4 != 0) goto L_0x002f;
    L_0x0020:
        r4 = 35;
        if (r3 != r4) goto L_0x002f;
    L_0x0024:
        r3 = r9.read();	 Catch:{ Exception -> 0x0067 }
        if (r3 == r5) goto L_0x0014;
    L_0x002a:
        if (r3 == r7) goto L_0x0014;
    L_0x002c:
        if (r3 != r6) goto L_0x0024;
    L_0x002e:
        goto L_0x0014;
    L_0x002f:
        r4 = 32;
        if (r3 == r4) goto L_0x0014;
    L_0x0033:
        r4 = 9;
        if (r3 == r4) goto L_0x0014;
    L_0x0037:
        r4 = 61;
        if (r3 != r4) goto L_0x0040;
    L_0x003b:
        r2 = r1.toString();	 Catch:{ Exception -> 0x0067 }
        goto L_0x0014;
    L_0x0040:
        if (r3 == r6) goto L_0x0044;
    L_0x0042:
        if (r3 != r7) goto L_0x010c;
    L_0x0044:
        r3 = r1.length();	 Catch:{ Exception -> 0x0067 }
        if (r3 == 0) goto L_0x0014;
    L_0x004a:
        r1 = "ANR_DIR";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x006a;
    L_0x0053:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r8.c = r0;	 Catch:{ Exception -> 0x0067 }
    L_0x0059:
        r2 = "";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0067 }
        r1.<init>();	 Catch:{ Exception -> 0x0067 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0067 }
        r0.<init>();	 Catch:{ Exception -> 0x0067 }
        goto L_0x0014;
    L_0x0067:
        r0 = move-exception;
    L_0x0068:
        r0 = 0;
    L_0x0069:
        return r0;
    L_0x006a:
        r1 = "ANR_INTERVAL";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x007e;
    L_0x0073:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x0067 }
        r8.d = r0;	 Catch:{ Exception -> 0x0067 }
        goto L_0x0059;
    L_0x007e:
        r1 = "CRASH_DIR";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x008e;
    L_0x0087:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r8.a = r0;	 Catch:{ Exception -> 0x0067 }
        goto L_0x0059;
    L_0x008e:
        r1 = "CRASH_DUMP";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x00a2;
    L_0x0097:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r0 = java.lang.Boolean.parseBoolean(r0);	 Catch:{ Exception -> 0x0067 }
        r8.b = r0;	 Catch:{ Exception -> 0x0067 }
        goto L_0x0059;
    L_0x00a2:
        r1 = "LOG_FILTER";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x00b2;
    L_0x00ab:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r8.e = r0;	 Catch:{ Exception -> 0x0067 }
        goto L_0x0059;
    L_0x00b2:
        r1 = "LOG_FILE";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x00c2;
    L_0x00bb:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r8.g = r0;	 Catch:{ Exception -> 0x0067 }
        goto L_0x0059;
    L_0x00c2:
        r1 = "LOG_DIR";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x00d2;
    L_0x00cb:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r8.f = r0;	 Catch:{ Exception -> 0x0067 }
        goto L_0x0059;
    L_0x00d2:
        r1 = "LOG_FILE_SIZE";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x00f4;
    L_0x00db:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x0067 }
        r8.i = r0;	 Catch:{ Exception -> 0x0067 }
        r0 = r8.i;	 Catch:{ Exception -> 0x0067 }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x0059;
    L_0x00ed:
        r0 = 20971520; // 0x1400000 float:3.526483E-38 double:1.03613076E-316;
        r8.i = r0;	 Catch:{ Exception -> 0x0067 }
        goto L_0x0059;
    L_0x00f4:
        r1 = "LOG_LEVEL";
        r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0067 }
        if (r1 == 0) goto L_0x0059;
    L_0x00fd:
        r1 = r8.h;	 Catch:{ Exception -> 0x0067 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0067 }
        r0 = r0.toLowerCase();	 Catch:{ Exception -> 0x0067 }
        r1.a(r0);	 Catch:{ Exception -> 0x0067 }
        goto L_0x0059;
    L_0x010c:
        r4 = r2.length();	 Catch:{ Exception -> 0x0067 }
        if (r4 != 0) goto L_0x0118;
    L_0x0112:
        r3 = (char) r3;	 Catch:{ Exception -> 0x0067 }
        r1.append(r3);	 Catch:{ Exception -> 0x0067 }
        goto L_0x0014;
    L_0x0118:
        r3 = (char) r3;	 Catch:{ Exception -> 0x0067 }
        r0.append(r3);	 Catch:{ Exception -> 0x0067 }
        goto L_0x0014;
    L_0x011e:
        r9.close();	 Catch:{ Exception -> 0x0067 }
        r0 = 1;
        goto L_0x0069;
        */
    }
}
