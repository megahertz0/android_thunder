package com.alipay.apmobilesecuritysdk.c;

import android.content.Context;
import android.os.Build;
import com.alipay.b.a.a.e.d;
import com.umeng.message.MsgConstant;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class a {
    public static synchronized void a(Context context, String str, String str2, String str3) {
        synchronized (a.class) {
            com.alipay.b.a.a.e.a aVar = new com.alipay.b.a.a.e.a(Build.MODEL, context.getApplicationContext().getApplicationInfo().packageName, "security-sdk-token", "3.2.0-20160621", str, str2, str3);
            d.a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + MsgConstant.CACHE_LOG_FILE_EXT, aVar.toString());
        }
    }

    public static synchronized void a(String str) {
        synchronized (a.class) {
            d.a(str);
        }
    }

    public static synchronized void a(Throwable th) {
        synchronized (a.class) {
            d.a(th);
        }
    }
}
