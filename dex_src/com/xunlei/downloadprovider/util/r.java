package com.xunlei.downloadprovider.util;

import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.toolbox.o;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.umeng.socialize.PlatformConfig.TencentWeibo;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.a.i;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSniffer;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: OnlineConfigure.java
public final class r extends com.xunlei.downloadprovider.util.a.c<JSONObject> {
    public static String a;
    public static ExecutorService c;
    private static r p;
    protected ArrayList<e> b;
    boolean d;
    public final a e;
    public final f f;
    public final i g;
    public final j h;
    public final com.xunlei.downloadprovider.discovery.a.a i;
    protected final b j;
    public final d k;
    public final h l;
    public final c m;
    public final g n;
    private long q;
    private String r;

    // compiled from: OnlineConfigure.java
    public static class a {
        public JSONObject a;

        // compiled from: OnlineConfigure.java
        public static class a {
            public int a;
            public boolean b;
            public int c;
            public int d;
            public boolean e;
            public boolean f;
            public boolean g;
            public boolean h;
            public int i;
            public Map<AD_TYPE, Integer> j;

            public a() {
                this.b = true;
                this.c = 1;
                this.d = 0;
                this.e = true;
                this.f = true;
                this.g = true;
                this.h = true;
                this.i = 1;
                this.j = new HashMap();
                this.j.put(AD_TYPE.SOURCE_GDT_FLAG, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle));
                this.j.put(AD_TYPE.SOURCE_SSP_FLAG, Integer.valueOf(0));
            }
        }

        public final a a() {
            JSONObject optJSONObject;
            JSONObject jSONObject = null;
            boolean z = true;
            a aVar = new a();
            String g = b.g();
            if (this.a != null) {
                JSONObject optJSONObject2 = this.a.optJSONObject("ad_switch_513");
                if (optJSONObject2 != null) {
                    String str = a;
                    if (optJSONObject2.has(g)) {
                        optJSONObject = optJSONObject2.optJSONObject(g);
                    } else {
                        optJSONObject = optJSONObject2.optJSONObject("default");
                    }
                } else {
                    optJSONObject = null;
                }
                if (BrothersApplication.a().getResources().getString(R.string.version).equals(this.a.optString(org.android.agoo.common.b.PROPERTY_APP_VERSION, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE))) {
                    if (optJSONObject != null && optJSONObject.has("switch_new")) {
                        jSONObject = optJSONObject.optJSONObject("switch_new");
                    }
                } else if (optJSONObject != null && optJSONObject.has("switch")) {
                    jSONObject = optJSONObject.optJSONObject("switch");
                }
                optJSONObject = this.a.optJSONObject("downloadinfo_ad_chance");
                if (optJSONObject != null) {
                    aVar.j.put(AD_TYPE.SOURCE_GDT_FLAG, Integer.valueOf(optJSONObject.optInt(TencentWeibo.Name, R.styleable.AppCompatTheme_buttonStyle)));
                    aVar.j.put(AD_TYPE.SOURCE_SSP_FLAG, Integer.valueOf(optJSONObject.optInt("shoulei", 0)));
                }
            }
            if (jSONObject != null && c()) {
                boolean z2;
                optJSONObject = jSONObject.optJSONObject("launch_ad_chance_522");
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("is_show");
                    if (optString == null || !optString.equals(MessageService.MSG_DB_NOTIFY_REACHED)) {
                        if (optString == null) {
                            aVar.b = true;
                        } else {
                            aVar.b = false;
                        }
                    } else if (optJSONObject.optJSONObject("config") != null) {
                        aVar.b = true;
                    }
                }
                aVar.c = jSONObject.optInt("downrecommend_ad", 1);
                aVar.d = jSONObject.optInt("searchtab_ad", 0);
                aVar.i = jSONObject.optInt("main_page_ad_type", 0);
                aVar.e = jSONObject.optInt("ad_downloadtab1_ad_enable", 1) == 1;
                if (jSONObject.optInt("ad_downloadtab2_ad_enable", 1) == 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                aVar.f = z2;
                if (jSONObject.optInt("ad_downloadtab3_ad_enable", 1) == 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                aVar.g = z2;
                if (jSONObject.optInt("downloadinfo_ad_enable", 1) != 1) {
                    z = false;
                }
                aVar.h = z;
                aVar.a = this.a.optInt("launch_countdown", XZBDevice.DOWNLOAD_LIST_FAILED);
            }
            return aVar;
        }

        public final int b() {
            return this.a != null ? this.a.optInt("launch_timeout", XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED) : XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        }

        public final boolean c() {
            return (this.a == null || this.a.optInt("ad_type", 0) == 0) ? false : true;
        }

        public final boolean d() {
            return this.a == null || this.a.optInt("ad_downloadtag_close_enable", 1) == 1;
        }
    }

    // compiled from: OnlineConfigure.java
    public static class c {
        public JSONObject a;

        public final boolean a() {
            return this.a != null && this.a.optInt("show_sharebar") == 1;
        }
    }

    // compiled from: OnlineConfigure.java
    public static class f {
        JSONObject a;
        public HashSet<String> b;

        public f() {
            this.b = new HashSet();
        }

        final void a(JSONObject jSONObject) {
            this.a = jSONObject;
            this.b.clear();
            if (jSONObject != null) {
                JSONArray optJSONArray = jSONObject.optJSONArray("h5_pay_channels");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        CharSequence optString = optJSONArray.optString(i, com.umeng.a.d);
                        if (!TextUtils.isEmpty(optString)) {
                            this.b.add(optString);
                        }
                    }
                }
            }
        }
    }

    // compiled from: OnlineConfigure.java
    public static class j {
        public JSONObject a;

        public final int a() {
            Object obj = 1;
            int i = -1;
            if (this.a != null) {
                i = this.a.optInt("xzb_exhibition_task_count", -1);
            }
            if (i < 0 || i > 20) {
                Object obj2 = null;
            } else {
                int i2 = 1;
            }
            if (obj2 != null) {
                e.a();
                e.b(i);
                return i;
            }
            e.a();
            i2 = e.d();
            if (i2 < 0 || i2 > 20) {
                obj = null;
            }
            if (obj == null) {
                return (i < 0 || i > 20) ? 0 : i;
            } else {
                e.a();
                return e.d();
            }
        }
    }

    protected final File a() {
        if (TextUtils.isEmpty(this.r)) {
            this.r = BrothersApplication.a().getCacheDir().getAbsolutePath() + "thunder_config.json";
        }
        return new File(this.r);
    }

    protected final void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                File a = a();
                new StringBuilder("saveConfigureToFile - cache : ").append(a.getAbsolutePath());
                String toString = jSONObject.toString();
                FileOutputStream fileOutputStream = new FileOutputStream(a);
                fileOutputStream.write(toString.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        a = "OnlineConfigure";
        c = Executors.newCachedThreadPool();
    }

    private r() {
        this.b = null;
        this.d = false;
        this.q = 0;
        this.e = new a();
        this.f = new f();
        this.g = new i();
        this.h = new j();
        this.i = new com.xunlei.downloadprovider.discovery.a.a();
        this.j = new b();
        this.k = new d();
        this.l = new h();
        this.m = new c();
        this.n = new g();
    }

    public final ArrayList<e> b() {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        return this.b;
    }

    private void i() {
        ArrayList arrayList = new ArrayList(b());
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((e) it.next()).a();
            }
        }
    }

    public static r c() {
        if (p == null) {
            p = new r();
        }
        return p;
    }

    protected final void d() {
        Object obj = null;
        File a = a();
        if (a.exists()) {
            new StringBuilder("loadConfigureFromLocalCache - cache : ").append(a.getAbsolutePath());
            obj = a(a);
        }
        try {
            if (!TextUtils.isEmpty(obj)) {
                a(new JSONObject(obj), 1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final void e() {
        g();
        SniffConfigure.a().g();
    }

    protected final void g() {
        String toString = new StringBuilder("http://static.m.sjzhushou.com/thunder_config.json?versionCode=").append(b.x()).toString();
        Request oVar = new o(0, toString, null, new t(this), new u(this, toString));
        oVar.setRetryPolicy(new com.android.volley.f(5000, 1, 1.0f));
        oVar.setShouldCache(false);
        com.xunlei.downloadprovider.j.a.b().a(oVar);
    }

    protected final void a(JSONObject jSONObject, int i) {
        new StringBuilder("loadConfigureJson - ").append(jSONObject);
        if (jSONObject != null) {
            a(h() | i);
            SniffConfigure.a();
            SniffConfigure.a(jSONObject.optJSONObject("sniff"));
            this.e.a = jSONObject.optJSONObject("ad");
            this.f.a(jSONObject.optJSONObject("pay"));
            this.h.a = jSONObject.optJSONObject("xzb_exhibition");
            JSONObject optJSONObject = jSONObject.optJSONObject("thunder");
            if (optJSONObject != null) {
                optJSONObject = optJSONObject.optJSONObject("urls");
                if (optJSONObject != null) {
                    z.a(optJSONObject);
                }
            }
            this.k.a = jSONObject.optJSONObject("feed");
            this.l.a = jSONObject.optJSONObject("video_tag");
            this.g.a = jSONObject.optJSONObject("tdlive");
            this.i.k = jSONObject.optJSONObject("discovery_tab");
            c cVar = this.m;
            JSONObject optJSONObject2 = jSONObject.optJSONObject("download_task");
            if (optJSONObject2 != null) {
                cVar.a = optJSONObject2;
            }
            g gVar = this.n;
            optJSONObject2 = jSONObject.optJSONObject(ThunderSniffer.JSINTERFACE_NAMESPACE);
            if (optJSONObject2 != null) {
                gVar.a = optJSONObject2;
            }
            if (gVar.a != null) {
                gVar.c = gVar.a.optString("short_movie_url", null);
                gVar.b = gVar.a.optString("download_task_url", null);
                new StringBuilder("short_movie_url:").append(gVar.c);
                new StringBuilder("download_task_url:").append(gVar.b);
            }
            this.d = true;
            this.q = System.currentTimeMillis();
        }
        i();
    }

    public final void f() {
        if (this.d) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.q > 0 && this.q + 28800000 <= currentTimeMillis) {
                g();
            }
        } else {
            g();
        }
        if ("com.xunlei.downloadprovider".equals(i.a(BrothersApplication.a))) {
            SniffConfigure.a().g();
        }
    }
}
