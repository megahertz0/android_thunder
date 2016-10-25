package com.xiaomi.smack.util;

import android.content.Context;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.d;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.security.MessageDigest;

public class h {
    private static Context a;

    public static Context a() {
        return a;
    }

    public static void a(Context context) {
        a = context.getApplicationContext();
    }

    public static String b() {
        String c = d.c(a);
        if (c == null) {
            c = d.e(a);
        }
        if (c != null) {
            try {
                return Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(c.getBytes()), XZBDevice.Wait).substring(0, R.styleable.Toolbar_titleMarginBottom);
            } catch (Throwable e) {
                b.a(e);
            }
        }
        return null;
    }

    public static boolean b(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Throwable e) {
            b.a(e);
            return false;
        }
    }
}
