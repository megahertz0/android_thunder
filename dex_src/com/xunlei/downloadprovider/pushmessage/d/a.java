package com.xunlei.downloadprovider.pushmessage.d;

import android.content.Context;
import com.xunlei.c.a.b;
import java.util.List;

// compiled from: PushDataHelper.java
public class a {
    private static a c;
    public final b a;
    public final Context b;

    private a(Context context) {
        this.a = new b();
        this.b = context;
    }

    public static a a(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(context);
                }
            }
        }
        return c;
    }

    public final void a(String str, String str2, String str3, String str4, String str5, List<String> list, String str6) {
        if (b.a(this.b)) {
            this.a.a("http://api-shoulei-ssl.xunlei.com/push_services/peer/register/", str, str2, str3, str4, str5, list, str6);
        }
    }

    public final void b(String str, String str2, String str3, String str4, String str5, List<String> list, String str6) {
        if (b.a(this.b)) {
            this.a.a("http://api-shoulei-ssl.xunlei.com/push_services/peer/update/", str, str2, str3, str4, str5, list, str6);
        }
    }
}
