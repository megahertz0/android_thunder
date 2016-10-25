package com.xunlei.downloadprovidershare.b;

import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: WeChatReportHelper.java
public class d {
    private static d a;
    private a b;
    private com.android.volley.r.a c;
    private b<JSONObject> d;

    // compiled from: WeChatReportHelper.java
    public static interface a {
        void a(String str);
    }

    private d() {
        this.c = new e(this);
        this.d = new f(this);
    }

    public static d a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public final void a(com.xunlei.downloadprovidershare.data.a aVar, a aVar2) {
        this.b = aVar2;
        com.xunlei.downloadprovider.j.a.a().d().a(new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/remotestorage/wechart_set", a(aVar), this.d, this.c));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject a(com.xunlei.downloadprovidershare.data.a r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovidershare.b.d.a(com.xunlei.downloadprovidershare.data.a):org.json.JSONObject");
        /*
        r2 = 0;
        r0 = r8.d;
        if (r0 == 0) goto L_0x000f;
    L_0x0006:
        r1 = "";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0011;
    L_0x000f:
        r0 = r8.a;
    L_0x0011:
        r6 = r8.f;
        r1 = com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.a(r0);
        r7 = r1.ordinal();
        r1 = "UTF-8";
        r0 = java.net.URLEncoder.encode(r0, r1);	 Catch:{ Exception -> 0x00c2 }
    L_0x0022:
        r4 = r8.b;
        r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r1 >= 0) goto L_0x00f9;
    L_0x0028:
        r1 = new org.json.JSONObject;
        r1.<init>();
        if (r0 == 0) goto L_0x0038;
    L_0x002f:
        r4 = "";
        r4 = r4.equals(r0);	 Catch:{ JSONException -> 0x00d0 }
        if (r4 == 0) goto L_0x00c8;
    L_0x0038:
        r0 = "title";
        r4 = "";
        r1.put(r0, r4);	 Catch:{ JSONException -> 0x00d0 }
    L_0x0041:
        r0 = "type";
        r4 = java.lang.String.valueOf(r7);	 Catch:{ JSONException -> 0x00d0 }
        r1.put(r0, r4);	 Catch:{ JSONException -> 0x00d0 }
        r0 = "fileSize";
        r2 = java.lang.String.valueOf(r2);	 Catch:{ JSONException -> 0x00d0 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
        r0 = "url";
        r1.put(r0, r6);	 Catch:{ JSONException -> 0x00d0 }
        r0 = r8.g;	 Catch:{ JSONException -> 0x00d0 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ JSONException -> 0x00d0 }
        if (r0 == 0) goto L_0x00d5;
    L_0x0063:
        r0 = "fileCid";
        r2 = "";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
    L_0x006c:
        r0 = r8.h;	 Catch:{ JSONException -> 0x00d0 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ JSONException -> 0x00d0 }
        if (r0 == 0) goto L_0x00de;
    L_0x0074:
        r0 = "fileGcid";
        r2 = "";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
    L_0x007d:
        r0 = r8.i;	 Catch:{ JSONException -> 0x00d0 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ JSONException -> 0x00d0 }
        if (r0 == 0) goto L_0x00e7;
    L_0x0085:
        r0 = "extUrl";
        r2 = "";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
    L_0x008e:
        r0 = r8.j;	 Catch:{ JSONException -> 0x00d0 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ JSONException -> 0x00d0 }
        if (r0 == 0) goto L_0x00f0;
    L_0x0096:
        r0 = "refUrl";
        r2 = "";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
    L_0x009f:
        r0 = "appVersion";
        r2 = com.xunlei.downloadprovidershare.d.a();	 Catch:{ JSONException -> 0x00d0 }
        r2 = com.xunlei.downloadprovider.a.b.f(r2);	 Catch:{ JSONException -> 0x00d0 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
        r0 = "appType";
        r2 = "android";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
        r0 = "share_from";
        r2 = r8.k;	 Catch:{ JSONException -> 0x00d0 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
        r1.toString();	 Catch:{ JSONException -> 0x00d0 }
    L_0x00c1:
        return r1;
    L_0x00c2:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0022;
    L_0x00c8:
        r4 = "title";
        r1.put(r4, r0);	 Catch:{ JSONException -> 0x00d0 }
        goto L_0x0041;
    L_0x00d0:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00c1;
    L_0x00d5:
        r0 = "fileCid";
        r2 = r8.g;	 Catch:{ JSONException -> 0x00d0 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
        goto L_0x006c;
    L_0x00de:
        r0 = "fileGcid";
        r2 = r8.h;	 Catch:{ JSONException -> 0x00d0 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
        goto L_0x007d;
    L_0x00e7:
        r0 = "extUrl";
        r2 = r8.i;	 Catch:{ JSONException -> 0x00d0 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
        goto L_0x008e;
    L_0x00f0:
        r0 = "refUrl";
        r2 = r8.j;	 Catch:{ JSONException -> 0x00d0 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x00d0 }
        goto L_0x009f;
    L_0x00f9:
        r2 = r4;
        goto L_0x0028;
        */
    }
}
