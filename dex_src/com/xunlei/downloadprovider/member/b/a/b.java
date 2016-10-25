package com.xunlei.downloadprovider.member.b.a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.toolbox.t;
import com.xunlei.downloadprovider.a.f;
import com.xunlei.downloadprovider.homepage.a.a.c;
import com.xunlei.downloadprovider.member.b.a;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.android.spdy.SpdyProtocol;
import org.json.JSONObject;

// compiled from: VipRenewal.java
public final class b implements a, d {
    private static String c;
    a.a a;
    String b;

    static {
        c = "VipRenewal";
    }

    public b(a.a aVar) {
        this.b = BuildConfig.VERSION_NAME;
        this.a = aVar;
    }

    public final void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            LoginHelper.a();
            if (LoginHelper.c()) {
                int i;
                if (!TextUtils.isEmpty(str)) {
                    String[] split = str.split(";");
                    int length = split.length;
                    for (i = 0; i < length; i++) {
                        String str2 = split[i];
                        d b = a.b(str2);
                        if (b != null && SystemClock.elapsedRealtime() - b.a > 43200000) {
                            a.c(str2);
                        }
                    }
                }
                c cVar = new c(this.a);
                try {
                    if (!c.a) {
                        c.a = true;
                        cVar.b = str;
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("userid", LoginHelper.a().j);
                        jSONObject.put("expire", c.a());
                        jSONObject.put("vas_type", LoginHelper.a().h);
                        jSONObject.put("level", LoginHelper.a().e);
                        jSONObject.put("style", str);
                        jSONObject.put("version", com.xunlei.downloadprovider.a.b.w());
                        new StringBuilder("reqUrl params:").append(jSONObject.toString());
                        t tVar = new t(new StringBuilder("http://bubble.vip.xunlei.com/service/bubble?request=querybubble&protocol=101&querystr=").append(URLEncoder.encode(jSONObject.toString(), "utf-8")).toString(), cVar, cVar);
                        tVar.setShouldCache(false);
                        com.xunlei.downloadprovider.j.a.a().e().a(tVar);
                    }
                } catch (Exception e) {
                    cVar.a();
                }
                new StringBuilder("update isSheduled:").append(a.a.a);
                if (!a.a.a) {
                    e eVar = a.a;
                    try {
                        Runnable fVar = new f(eVar);
                        i = ((24 - Calendar.getInstance().get(SpdyProtocol.PUBKEY_PSEQ_OPEN)) - 1) + 5;
                        int i2 = Calendar.getInstance().get(R.styleable.Toolbar_titleMargins);
                        long j = (long) (((i * 60) * 60) + ((60 - i2) * 60));
                        new StringBuilder("hours:").append(i).append(" minute:").append(i2).append(" delay:").append(j).append(" date:").append(f.a(System.currentTimeMillis() + (1000 * j)));
                        eVar.b.scheduleAtFixedRate(fVar, j, 86400, TimeUnit.SECONDS);
                        eVar.a = true;
                        return;
                    } catch (Exception e2) {
                        eVar.a = false;
                    }
                }
                return;
            }
            LoginHelper.a();
            if (LoginHelper.p()) {
                LoginHelper.a().a(this);
                this.b = str;
            }
        }
    }

    public final com.xunlei.downloadprovider.member.b.c b(String str) {
        return a.b(str);
    }

    public final void c(String str) {
        d b = a.b(str);
        if (b != null && SystemClock.elapsedRealtime() - b.a > 43200000) {
            a.c(str);
        }
        if (!a.d(str)) {
            a(str);
        } else if (this.a != null) {
            this.a.b();
        }
    }

    public final void a() {
        a.a();
    }

    public final void a(int i, int i2, boolean z, Object obj) {
        new StringBuilder("OnLoginCompleted:isAutoLog:").append(z).append(" event:").append(i);
        LoginHelper.a();
        if (LoginHelper.c()) {
            LoginHelper.a().b(this);
            a(this.b);
            this.b = BuildConfig.VERSION_NAME;
        }
    }
}
