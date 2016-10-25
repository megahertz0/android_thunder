package com.xunlei.downloadprovider.util.sniff;

import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.toolbox.o;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.util.a.a.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

public class SniffConfigure {
    private static SniffConfigure b;
    private static boolean c;
    private static String d;
    private static SniffWay e;
    private static final String h;
    boolean a;
    private a f;
    private final g g;
    private String i;

    public enum SniffWay {
        SNIFF_WAY_FORBIDDEN(MessageService.MSG_DB_READY_REPORT),
        SNIFF_WAY_NORMAL(MessageService.MSG_DB_NOTIFY_REACHED),
        SNIFF_WAY_LOCAL(MessageService.MSG_DB_NOTIFY_CLICK);
        private String a;

        static {
            String str = MessageService.MSG_DB_READY_REPORT;
            SNIFF_WAY_FORBIDDEN = new com.xunlei.downloadprovider.util.sniff.SniffConfigure.SniffWay("SNIFF_WAY_FORBIDDEN", 0, MessageService.MSG_DB_READY_REPORT);
            str = MessageService.MSG_DB_NOTIFY_REACHED;
            SNIFF_WAY_NORMAL = new com.xunlei.downloadprovider.util.sniff.SniffConfigure.SniffWay("SNIFF_WAY_NORMAL", 1, MessageService.MSG_DB_NOTIFY_REACHED);
            str = MessageService.MSG_DB_NOTIFY_CLICK;
            SNIFF_WAY_LOCAL = new com.xunlei.downloadprovider.util.sniff.SniffConfigure.SniffWay("SNIFF_WAY_LOCAL", 2, MessageService.MSG_DB_NOTIFY_CLICK);
            b = new com.xunlei.downloadprovider.util.sniff.SniffConfigure.SniffWay[]{SNIFF_WAY_FORBIDDEN, SNIFF_WAY_NORMAL, SNIFF_WAY_LOCAL};
        }

        private SniffWay(String str) {
            this.a = str;
        }

        public static com.xunlei.downloadprovider.util.sniff.SniffConfigure.SniffWay fromSniffWay(String str) {
            if (str != null) {
                com.xunlei.downloadprovider.util.sniff.SniffConfigure.SniffWay[] values = values();
                int length = values.length;
                for (int i = 0; i < length; i++) {
                    com.xunlei.downloadprovider.util.sniff.SniffConfigure.SniffWay sniffWay = values[i];
                    if (str.equalsIgnoreCase(sniffWay.a)) {
                        return sniffWay;
                    }
                }
            }
            return null;
        }
    }

    static {
        c = true;
        d = null;
        e = SniffWay.SNIFF_WAY_NORMAL;
        h = SniffConfigure.class.getSimpleName();
    }

    public static SniffConfigure a() {
        if (b == null) {
            b = new SniffConfigure();
        }
        return b;
    }

    public static boolean c() {
        return c;
    }

    public static SniffWay d() {
        return e;
    }

    public static String e() {
        return d;
    }

    private SniffConfigure() {
        this.f = null;
        this.g = new g();
        this.a = false;
    }

    private String i() {
        if (TextUtils.isEmpty(this.i)) {
            this.i = BrothersApplication.a().getCacheDir().getAbsolutePath() + b.x() + "sniff_config_default.json";
        }
        return this.i;
    }

    private void j() {
        InputStream open;
        Object obj = null;
        try {
            File file = new File(i());
            if (file.exists()) {
                new StringBuilder("loadConfigureFromFile - cache : ").append(file.getAbsolutePath());
                FileInputStream fileInputStream = new FileInputStream(file);
            }
            if (r1 == null) {
                try {
                    open = BrothersApplication.a().getAssets().open("sniff_config_default.json");
                } catch (Exception e) {
                    e.printStackTrace();
                    open = r1;
                }
            } else {
                open = r1;
            }
        } catch (Exception e2) {
            try {
                e2.printStackTrace();
                try {
                    open = BrothersApplication.a().getAssets().open("sniff_config_default.json");
                } catch (Exception e22) {
                    e22.printStackTrace();
                    open = null;
                }
            } catch (Throwable th) {
                try {
                    BrothersApplication.a().getAssets().open("sniff_config_default.json");
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (open != null) {
            try {
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                open.close();
                b(new JSONObject(new String(bArr, GameManager.DEFAULT_CHARSET)));
            } catch (Exception e222) {
                e222.printStackTrace();
            }
        }
    }

    private void b(JSONObject jSONObject) {
        new StringBuilder("loadConfigureFromJSON - ").append(jSONObject.toString());
        this.a = true;
        if (this.f == null) {
            this.f = new a();
        }
        a(jSONObject, "sniff_config");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.json.JSONObject r6, java.lang.String r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.util.sniff.SniffConfigure.a(org.json.JSONObject, java.lang.String):void");
        /*
        this = this;
    L_0x0000:
        r1 = r6.optJSONObject(r7);
        if (r1 != 0) goto L_0x000c;
    L_0x0006:
        r0 = "http://m.baidu.com/s?word=%s";
        d = r0;	 Catch:{ JSONException -> 0x006b }
    L_0x000b:
        return;
    L_0x000c:
        r0 = "keyword_suffix";
        r2 = r1.optJSONArray(r0);	 Catch:{ JSONException -> 0x006b }
        if (r2 == 0) goto L_0x0045;
    L_0x0015:
        r3 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x006b }
        r3.<init>();	 Catch:{ JSONException -> 0x006b }
        r0 = 0;
    L_0x001b:
        r4 = r2.length();	 Catch:{ JSONException -> 0x006b }
        if (r0 >= r4) goto L_0x002b;
    L_0x0021:
        r4 = r2.getString(r0);	 Catch:{ JSONException -> 0x006b }
        r3.add(r4);	 Catch:{ JSONException -> 0x006b }
        r0 = r0 + 1;
        goto L_0x001b;
    L_0x002b:
        r0 = r5.f;	 Catch:{ JSONException -> 0x006b }
        r0 = r0.a;	 Catch:{ JSONException -> 0x006b }
        r0 = com.xunlei.xllib.b.d.a(r0);	 Catch:{ JSONException -> 0x006b }
        if (r0 != 0) goto L_0x0041;
    L_0x0035:
        r0 = r5.f;	 Catch:{ JSONException -> 0x006b }
        r0 = r0.a;	 Catch:{ JSONException -> 0x006b }
        r0.clear();	 Catch:{ JSONException -> 0x006b }
        r0 = r5.f;	 Catch:{ JSONException -> 0x006b }
        r2 = 0;
        r0.a = r2;	 Catch:{ JSONException -> 0x006b }
    L_0x0041:
        r0 = r5.f;	 Catch:{ JSONException -> 0x006b }
        r0.a = r3;	 Catch:{ JSONException -> 0x006b }
    L_0x0045:
        r0 = "search_url";
        r0 = r1.optString(r0);
        d = r0;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x005c;
    L_0x0054:
        r0 = d;
        r0 = com.xunlei.thundersniffer.sniff.sniffer.ThunderSnifferUtil.isSupportedSearchPageUrl(r0);
        if (r0 != 0) goto L_0x000b;
    L_0x005c:
        r0 = "default_sniff_config";
        r0 = r7.equals(r0);
        if (r0 == 0) goto L_0x0070;
    L_0x0065:
        r0 = "http://m.baidu.com/s?word=%s";
        d = r0;
        goto L_0x000b;
    L_0x006b:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0045;
    L_0x0070:
        r7 = "default_sniff_config";
        goto L_0x0000;
        */
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (jSONObject.has("sniff_enable")) {
                c = jSONObject.optBoolean("sniff_enable", true);
            }
            if (jSONObject.has("sniff_way")) {
                Object optString = jSONObject.optString("sniff_way");
                if (!TextUtils.isEmpty(optString)) {
                    e = SniffWay.fromSniffWay(optString);
                }
            }
        }
    }

    public final void g() {
        g gVar = this.g;
        new StringBuilder("checkIsCanGetSniffRules() --> ").append(gVar.b());
        if (gVar.b()) {
            gVar.c = System.currentTimeMillis();
            String str = new StringBuilder("http://static.m.sjzhushou.com/pss/static/config_json_3618/athunder_sniffer_rules.json?versionCode=").append(b.x()).toString() + "&rd=" + a.a();
            Request oVar = new o(0, str, null, new h(gVar), new i(gVar, str));
            oVar.setRetryPolicy(new f(5000, 1, 1.0f));
            oVar.setShouldCache(true);
            com.xunlei.downloadprovider.j.a.b().a(oVar);
        }
    }

    public final String a(String str, String str2) {
        g.a aVar = (g.a) this.g.b.get(str);
        return (aVar == null || aVar.d == null) ? str2 : aVar.d;
    }

    public final a b() {
        if (!this.a) {
            f();
        }
        return this.f;
    }

    public final boolean f() {
        if (!this.a) {
            j();
        }
        g gVar = this.g;
        if (!gVar.a) {
            gVar.c();
        }
        return this.a;
    }
}
