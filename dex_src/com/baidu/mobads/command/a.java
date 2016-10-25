package com.baidu.mobads.command;

import android.content.Context;
import android.os.Process;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONObject;

public class a implements Serializable {
    private boolean A;
    public String a;
    public String b;
    public String c;
    public long d;
    public int e;
    public int f;
    public DownloadStatus g;
    public Object h;
    public String i;
    public String j;
    public String k;
    public boolean l;
    public String m;
    public String n;
    public String o;
    public String p;
    public int q;
    public boolean r;
    public long s;
    protected long t;
    protected long u;
    public boolean v;
    public String w;
    protected final IXAdLogger x;
    private long y;
    private long z;

    public a(String str, String str2) {
        this.d = -1;
        this.e = 0;
        this.g = DownloadStatus.NONE;
        this.h = null;
        this.q = 0;
        this.r = false;
        this.v = false;
        this.w = null;
        this.x = m.a().f();
        this.i = str;
        this.a = str2;
    }

    public void a(String str, String str2, String str3, boolean z) {
        this.m = str;
        this.n = str2;
        this.j = str3;
        this.l = z;
    }

    public void a(String str, String str2) {
        this.b = str;
        this.c = str2;
    }

    public void b(String str, String str2) {
        this.o = str;
        this.p = str2;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(IXAdCommonUtils.PKGS_PREF_DOWNLOAD_STATUS, this.g.getCode());
            jSONObject.put("filename", this.b);
            jSONObject.put("folder", this.c);
            jSONObject.put(WebBrowserActivity.EXTRA_TITLE, this.a);
            jSONObject.put("contentLength", this.d);
            jSONObject.put(IXAdRequestInfo.PACKAGE, this.i);
            jSONObject.put("qk", this.m);
            jSONObject.put("autoOpen", this.l);
            jSONObject.put("adid", this.n);
            jSONObject.put("placeId", this.o);
            jSONObject.put("prod", this.p);
            jSONObject.put("dlTunnel", XZBDevice.DOWNLOAD_LIST_ALL);
            if (this.k == null || this.k.length() <= 0) {
                jSONObject.put(SocialConstants.PARAM_URL, this.j);
            } else {
                jSONObject.put("turl", this.k);
            }
            jSONObject.put("mnCfm", this.r);
            jSONObject.put("dlCnt", this.q);
            jSONObject.put("cts", this.s);
            if (this.q == 1) {
                this.t = System.currentTimeMillis();
                this.u = (long) Process.myPid();
            }
            jSONObject.put(MsgConstant.KEY_TS, this.t);
            jSONObject.put("clickProcId", this.u);
        } catch (Throwable e) {
            this.x.d(e);
        }
        return jSONObject;
    }

    public static String b() {
        return m.a().n().getCurrentProcessName(m.a().d());
    }

    public static a a(Context context, String str) {
        a aVar = null;
        if (str == null || com.umeng.a.d.equals(str)) {
            return null;
        }
        try {
            String string = context.getSharedPreferences(IXAdCommonUtils.PKGS_PREF_DOWNLOAD, 0).getString(str + "#$#" + b(), null);
            if (string == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(string);
            String string2 = jSONObject.getString(WebBrowserActivity.EXTRA_TITLE);
            String optString = jSONObject.optString(SocialConstants.PARAM_URL, jSONObject.getString("turl"));
            a aVar2 = new a(str, string2);
            try {
                aVar2.a(jSONObject.optString("qk"), jSONObject.optString("adid"), optString, jSONObject.optBoolean("autoOpen"));
                aVar2.a(jSONObject.getString("filename"), jSONObject.getString("folder"));
                aVar2.b(jSONObject.optString("placeId"), jSONObject.optString("prod"));
                int i = jSONObject.getInt(IXAdCommonUtils.PKGS_PREF_DOWNLOAD_STATUS);
                DownloadStatus[] values = DownloadStatus.values();
                DownloadStatus downloadStatus = DownloadStatus.NONE;
                for (int i2 = 0; i2 < values.length; i2++) {
                    if (values[i2].getCode() == i) {
                        downloadStatus = values[i2];
                    }
                }
                aVar2.g = downloadStatus;
                aVar2.r = jSONObject.optBoolean("mnCfm");
                aVar2.q = jSONObject.getInt("dlCnt");
                aVar2.s = jSONObject.optLong("cts");
                aVar2.t = jSONObject.optLong(MsgConstant.KEY_TS);
                aVar2.u = (long) jSONObject.optInt("clickProcId");
                return aVar2;
            } catch (Throwable e) {
                Throwable th = e;
                aVar = aVar2;
                Throwable th2 = th;
                m.a().f().d(th2);
                com.baidu.mobads.c.a.a().a(new StringBuilder("get stored download info failed: ").append(th2.toString()).toString());
                return aVar;
            }
        } catch (Exception e2) {
            th2 = e2;
            m.a().f().d(th2);
            com.baidu.mobads.c.a.a().a(new StringBuilder("get stored download info failed: ").append(th2.toString()).toString());
            return aVar;
        }
    }

    public static List<String> a(Context context, long j) {
        List<String> arrayList = new ArrayList();
        try {
            for (Entry entry : context.getSharedPreferences(IXAdCommonUtils.PKGS_PREF_DOWNLOAD, 0).getAll().entrySet()) {
                try {
                    String str = (String) entry.getKey();
                    if (str.contains(new StringBuilder("#$#").append(b()).toString())) {
                        JSONObject jSONObject = new JSONObject((String) entry.getValue());
                        if (jSONObject.getLong("cts") >= j) {
                            int i = jSONObject.getInt(IXAdCommonUtils.PKGS_PREF_DOWNLOAD_STATUS);
                            if (i == 0 || i == 1 || i == 4) {
                                arrayList.add(str.substring(0, str.indexOf("#$#")));
                            }
                        }
                    }
                } catch (Exception e) {
                    m.a().f().d("XAdDownloaderManager", e.getMessage());
                }
            }
        } catch (Throwable e2) {
            m.a().f().d(e2);
        }
        return arrayList;
    }

    public long c() {
        return this.y;
    }

    public void a(long j) {
        this.y = j;
    }

    public String d() {
        return this.i;
    }

    public long e() {
        return this.z;
    }

    public boolean f() {
        return this.A;
    }

    public void a(boolean z) {
        this.A = z;
    }

    public void b(long j) {
        this.z = j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(9)
    public void a(android.content.Context r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.command.a.a(android.content.Context):void");
        /*
        this = this;
        r0 = r3.i;
        if (r0 == 0) goto L_0x004d;
    L_0x0004:
        r0 = "";
        r1 = r3.i;
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x004d;
    L_0x000f:
        r0 = "__sdk_remote_dl_2";
        r1 = 0;
        r0 = r4.getSharedPreferences(r0, r1);	 Catch:{ Exception -> 0x0052 }
        r0 = r0.edit();	 Catch:{ Exception -> 0x0052 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0052 }
        r1.<init>();	 Catch:{ Exception -> 0x0052 }
        r2 = r3.i;	 Catch:{ Exception -> 0x0052 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0052 }
        r2 = "#$#";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0052 }
        r2 = b();	 Catch:{ Exception -> 0x0052 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0052 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0052 }
        r2 = r3.a();	 Catch:{ Exception -> 0x0052 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0052 }
        r0.putString(r1, r2);	 Catch:{ Exception -> 0x0052 }
        r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x0052 }
        r2 = 9;
        if (r1 < r2) goto L_0x004e;
    L_0x004a:
        r0.apply();	 Catch:{ Exception -> 0x0052 }
    L_0x004d:
        return;
    L_0x004e:
        r0.commit();	 Catch:{ Exception -> 0x0052 }
        goto L_0x004d;
    L_0x0052:
        r0 = move-exception;
        r1 = com.baidu.mobads.j.m.a();
        r1 = r1.f();
        r2 = "XAdAPKDownloadExtraInfo";
        r1.d(r2, r0);
        goto L_0x004d;
        */
    }

    public String g() {
        return this.n;
    }

    public String h() {
        return this.m;
    }

    public String i() {
        return this.p;
    }
}
