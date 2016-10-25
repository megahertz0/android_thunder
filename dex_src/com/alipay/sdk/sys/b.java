package com.alipay.sdk.sys;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.data.c;
import com.ta.utdid2.device.UTDevice;
import com.umeng.a;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

public final class b {
    private static b b;
    public Context a;

    private b() {
    }

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    private Context d() {
        return this.a;
    }

    public final void a(Context context) {
        this.a = context.getApplicationContext();
    }

    private static c e() {
        return c.a();
    }

    public static boolean b() {
        String[] strArr = new String[]{"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 5) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    String a = a(new String[]{"ls", "-l", strArr[i] + "su"});
                    return (TextUtils.isEmpty(a) || a.indexOf("root") == a.lastIndexOf("root")) ? false : true;
                } else {
                    i++;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    private static String a(String[] strArr) {
        String readLine;
        String str = a.d;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            processBuilder.redirectErrorStream(false);
            Process start = processBuilder.start();
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(start.getOutputStream());
                readLine = new DataInputStream(start.getInputStream()).readLine();
                try {
                    dataOutputStream.writeBytes("exit\n");
                    dataOutputStream.flush();
                    start.waitFor();
                    try {
                        start.destroy();
                    } catch (Exception e) {
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    start.destroy();
                    throw th2;
                }
            } catch (Throwable th3) {
                Throwable th22 = th3;
                start.destroy();
                throw th22;
            }
        } catch (Throwable th4) {
            Throwable th5 = th4;
            start = null;
            th22 = th5;
            try {
                start.destroy();
            } catch (Exception e2) {
            }
            throw th22;
        }
        return readLine;
    }

    public final String c() {
        String str = a.d;
        try {
            return UTDevice.getUtdid(this.a);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.i, th);
            return str;
        }
    }
}
