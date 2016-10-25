package u.aly;

import java.util.Formatter;

// compiled from: MLog.java
public final class v {
    public static boolean a;
    private static String b;

    static {
        a = false;
        b = "MobclickAgent";
    }

    public static void a(String str, Object... objArr) {
        try {
            if (str.contains("%")) {
                c(new Formatter().format(str, objArr).toString(), null);
            } else {
                c((String) objArr[0], null);
            }
        } catch (Throwable th) {
            a(th);
        }
    }

    public static void b(String str, Object... objArr) {
        try {
            if (str.contains("%")) {
                e(new Formatter().format(str, objArr).toString(), null);
            } else {
                e((String) objArr[0], null);
            }
        } catch (Throwable th) {
            a(th);
        }
    }

    public static void c(String str, Object... objArr) {
        try {
            if (str.contains("%")) {
                d(new Formatter().format(str, objArr).toString(), null);
            } else {
                d((String) objArr[0], null);
            }
        } catch (Throwable th) {
            a(th);
        }
    }

    public static void a(Throwable th) {
        e(null, th);
    }

    public static void a(String str, Throwable th) {
        c(str, th);
    }

    public static void b(String str, Throwable th) {
        e(str, th);
    }

    public static void a(String str) {
        c(str, null);
    }

    public static void b(String str) {
        d(str, null);
    }

    public static void c(String str) {
        e(str, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(java.lang.String r6, java.lang.Throwable r7) {
        throw new UnsupportedOperationException("Method not decompiled: u.aly.v.c(java.lang.String, java.lang.Throwable):void");
        /*
        r0 = a;
        if (r0 == 0) goto L_0x0008;
    L_0x0004:
        if (r7 != 0) goto L_0x0009;
    L_0x0006:
        if (r6 == 0) goto L_0x0008;
    L_0x0008:
        return;
    L_0x0009:
        if (r6 == 0) goto L_0x0045;
    L_0x000b:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0049 }
        r0.<init>();	 Catch:{ Throwable -> 0x0049 }
        r1 = r7.toString();	 Catch:{ Throwable -> 0x0049 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
        r1 = ":  [";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
        r0 = r0.append(r6);	 Catch:{ Throwable -> 0x0049 }
        r1 = "]";
        r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
    L_0x0029:
        r1 = r7.getStackTrace();	 Catch:{ Throwable -> 0x0049 }
        r2 = r1.length;	 Catch:{ Throwable -> 0x0049 }
        r0 = 0;
    L_0x002f:
        if (r0 >= r2) goto L_0x0008;
    L_0x0031:
        r3 = r1[r0];	 Catch:{ Throwable -> 0x0049 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0049 }
        r5 = "        at  ";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x0049 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0049 }
        r4.append(r3);	 Catch:{ Throwable -> 0x0049 }
        r0 = r0 + 1;
        goto L_0x002f;
    L_0x0045:
        r7.toString();	 Catch:{ Throwable -> 0x0049 }
        goto L_0x0029;
    L_0x0049:
        r0 = move-exception;
        goto L_0x0008;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void d(java.lang.String r6, java.lang.Throwable r7) {
        throw new UnsupportedOperationException("Method not decompiled: u.aly.v.d(java.lang.String, java.lang.Throwable):void");
        /*
        r0 = a;
        if (r0 == 0) goto L_0x0008;
    L_0x0004:
        if (r7 != 0) goto L_0x0009;
    L_0x0006:
        if (r6 == 0) goto L_0x0008;
    L_0x0008:
        return;
    L_0x0009:
        if (r6 == 0) goto L_0x0045;
    L_0x000b:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0049 }
        r0.<init>();	 Catch:{ Throwable -> 0x0049 }
        r1 = r7.toString();	 Catch:{ Throwable -> 0x0049 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
        r1 = ":  [";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
        r0 = r0.append(r6);	 Catch:{ Throwable -> 0x0049 }
        r1 = "]";
        r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
    L_0x0029:
        r1 = r7.getStackTrace();	 Catch:{ Throwable -> 0x0049 }
        r2 = r1.length;	 Catch:{ Throwable -> 0x0049 }
        r0 = 0;
    L_0x002f:
        if (r0 >= r2) goto L_0x0008;
    L_0x0031:
        r3 = r1[r0];	 Catch:{ Throwable -> 0x0049 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0049 }
        r5 = "        at  ";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x0049 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0049 }
        r4.append(r3);	 Catch:{ Throwable -> 0x0049 }
        r0 = r0 + 1;
        goto L_0x002f;
    L_0x0045:
        r7.toString();	 Catch:{ Throwable -> 0x0049 }
        goto L_0x0029;
    L_0x0049:
        r0 = move-exception;
        goto L_0x0008;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void e(java.lang.String r6, java.lang.Throwable r7) {
        throw new UnsupportedOperationException("Method not decompiled: u.aly.v.e(java.lang.String, java.lang.Throwable):void");
        /*
        r0 = a;
        if (r0 == 0) goto L_0x0008;
    L_0x0004:
        if (r7 != 0) goto L_0x0009;
    L_0x0006:
        if (r6 == 0) goto L_0x0008;
    L_0x0008:
        return;
    L_0x0009:
        if (r6 == 0) goto L_0x0045;
    L_0x000b:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0049 }
        r0.<init>();	 Catch:{ Throwable -> 0x0049 }
        r1 = r7.toString();	 Catch:{ Throwable -> 0x0049 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
        r1 = ":  [";
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
        r0 = r0.append(r6);	 Catch:{ Throwable -> 0x0049 }
        r1 = "]";
        r0.append(r1);	 Catch:{ Throwable -> 0x0049 }
    L_0x0029:
        r1 = r7.getStackTrace();	 Catch:{ Throwable -> 0x0049 }
        r2 = r1.length;	 Catch:{ Throwable -> 0x0049 }
        r0 = 0;
    L_0x002f:
        if (r0 >= r2) goto L_0x0008;
    L_0x0031:
        r3 = r1[r0];	 Catch:{ Throwable -> 0x0049 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0049 }
        r5 = "        at  ";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x0049 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0049 }
        r4.append(r3);	 Catch:{ Throwable -> 0x0049 }
        r0 = r0 + 1;
        goto L_0x002f;
    L_0x0045:
        r7.toString();	 Catch:{ Throwable -> 0x0049 }
        goto L_0x0029;
    L_0x0049:
        r0 = move-exception;
        goto L_0x0008;
        */
    }
}
