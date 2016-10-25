package com.xunlei.downloadprovider.frame.advertisement.b;

import android.content.Context;
import android.os.Handler;
import anet.channel.util.ErrorConstant;
import com.android.volley.f;
import com.android.volley.l;
import com.android.volley.w;
import com.xunlei.downloadprovider.member.payment.a.e;
import java.util.List;
import org.json.JSONException;

@Deprecated
// compiled from: ThunderADManager.java
public class d extends e {
    public static final String a;
    private static d c;
    public final int b;
    private Context d;
    private Handler e;

    // compiled from: ThunderADManager.java
    public static abstract class a {
        public boolean c;
        public boolean d;

        public abstract void a(int i, String str);

        public abstract void a(List<com.xunlei.downloadprovider.frame.advertisement.a.a> list);
    }

    static {
        a = d.class.getSimpleName();
    }

    private d(Context context) {
        this.b = 3000;
        this.e = new e(this);
        this.d = context;
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (c == null) {
                c = new d(context.getApplicationContext());
            }
            dVar = c;
        }
        return dVar;
    }

    public static int a(w wVar) {
        if (wVar == null) {
            return ErrorConstant.ERROR_TNET_EXCEPTION;
        }
        l lVar = wVar.a;
        return lVar != null ? lVar.a : ErrorConstant.ERROR_TNET_EXCEPTION;
    }

    public static String b(w wVar) {
        return wVar != null ? wVar.getClass().getSimpleName() : com.umeng.a.d;
    }

    public final void a(String str, String str2, a aVar) {
        h hVar = new h(c.a(str), new f(this, str, aVar, str2), new g(this, str, aVar));
        hVar.setRetryPolicy(new f(2500, 0, 1.0f));
        hVar.setShouldCache(false);
        a(hVar);
    }

    public static List<com.xunlei.downloadprovider.frame.advertisement.a.a> a(String str, String str2) throws JSONException {
        if (str.equals("android_home")) {
            return str2.equals("android_search_hotword_3") ? a.a().a : null;
        } else {
            if (str.equals("android_app")) {
                return a.a().c;
            }
            if (str.equals("android_download")) {
                if (str2 == null) {
                    return null;
                }
                if (str2.equals("android_download_funplay_1")) {
                    return a.a().d;
                }
                if (str2.equals("android_download_funplay_3")) {
                    return a.a().e;
                }
                if (str2.equals("android_download_funplay_5")) {
                    return a.a().f;
                }
                if (str2.equals("android_download_funplay_6")) {
                    return a.a().g;
                }
                if (str2.equals("android_download_funplay_8")) {
                    return a.a().h;
                }
                if (str2.equals("android_download_use_1")) {
                    return a.a().j;
                }
                if (str2.equals("android_download_use_2")) {
                    return a.a().k;
                }
                return str2.equals("android_download_use_3") ? a.a().l : null;
            } else if (str.equals("android_guide")) {
                return a.a().i;
            } else {
                return (str.equals("android_downloadinfo") && str2.equals("android_downloadinfo_sell")) ? a.a().m : null;
            }
        }
    }
}
