package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.a;
import com.xiaomi.channel.commonutils.string.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class c {
    public static boolean a(Context context, String str, String str2) {
        boolean z = false;
        OutputStream outputStream = null;
        try {
            InputStream open = context.getAssets().open(str);
            try {
                byte[] b = a.b(open);
                File file = new File(str2);
                try {
                    OutputStream fileOutputStream = new FileOutputStream(file);
                } catch (Exception e) {
                    e = e;
                    r3 = r4;
                    r4 = open;
                    e.printStackTrace();
                    a.a(r4);
                    a.a(r3);
                    a.a(outputStream);
                    return z;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    r3 = r4;
                    a.a(open);
                    a.a(r3);
                    a.a(outputStream);
                    throw th2;
                }
            } catch (Exception e2) {
                e = e2;
                r3 = null;
                r4 = open;
                e.printStackTrace();
                a.a(r4);
                a.a(r3);
                a.a(outputStream);
                return z;
            } catch (Throwable th3) {
                th2 = th3;
                r3 = null;
                a.a(open);
                a.a(r3);
                a.a(outputStream);
                throw th2;
            }
            if (file.exists()) {
                r3 = new FileInputStream(file);
                try {
                    Object a = d.a(a.b(r3));
                    String a2 = d.a(b);
                    if (TextUtils.isEmpty(a) || !a.equals(a2)) {
                        r4 = r3;
                    } else {
                        a.a(open);
                        a.a(r3);
                        a.a(null);
                        return false;
                    }
                } catch (Exception e3) {
                    e = e3;
                    r4 = open;
                    e.printStackTrace();
                    a.a(r4);
                    a.a(r3);
                    a.a(outputStream);
                    return z;
                } catch (Throwable th4) {
                    th2 = th4;
                    a.a(open);
                    a.a(r3);
                    a.a(outputStream);
                    throw th2;
                }
            }
            r4 = null;
            try {
                fileOutputStream.write(b);
                fileOutputStream.flush();
                a.a(open);
                a.a(r4);
                a.a(fileOutputStream);
                z = true;
                return true;
            } catch (Exception e4) {
                e = e4;
                outputStream = fileOutputStream;
                r3 = r4;
                r4 = open;
            } catch (Throwable th5) {
                th2 = th5;
                outputStream = fileOutputStream;
                r3 = r4;
                a.a(open);
                a.a(r3);
                a.a(outputStream);
                throw th2;
            }
        } catch (Exception e5) {
            e = e5;
            r3 = null;
            r4 = null;
            try {
                Exception e6;
                InputStream inputStream;
                InputStream inputStream2;
                e6.printStackTrace();
                a.a(inputStream2);
                a.a(inputStream);
                a.a(outputStream);
                return z;
            } catch (Throwable th6) {
                th2 = th6;
                open = inputStream2;
            }
        } catch (Throwable th7) {
            th2 = th7;
            inputStream = null;
            open = null;
            a.a(open);
            a.a(inputStream);
            a.a(outputStream);
            throw th2;
        }
    }
}
