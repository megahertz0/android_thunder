package com.tencent.map.b;

import android.location.Location;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.socialize.editorpage.ShareActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static b b;
    public String a;
    private double c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;
    private a i;
    private b j;
    private boolean k;

    public static interface a {
        void a(double d, double d2);
    }

    public class b extends Thread {
        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.b.b.run():void");
            /*
            this = this;
            r6 = 4645040803167600640; // 0x4076800000000000 float:0.0 double:360.0;
            r1 = 0;
            r0 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x0036 }
            r0 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x0036 }
            r0 = r0.getBytes();	 Catch:{ Exception -> 0x0036 }
            r0 = com.tencent.map.b.j.a(r0);	 Catch:{ Exception -> 0x0036 }
            r2 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x0036 }
            r3 = 1;
            r2.k = r3;	 Catch:{ Exception -> 0x0036 }
            r2 = "http://ls.map.soso.com/deflect?c=1";
            r3 = "SOSO MAP LBS SDK";
            r0 = com.tencent.map.b.b.a(r2, r3, r0);	 Catch:{ Exception -> 0x0036 }
            r2 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x0036 }
            r3 = 0;
            r2.k = r3;	 Catch:{ Exception -> 0x0036 }
            r2 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x0036 }
            r2 = com.tencent.map.b.j.b(r2);	 Catch:{ Exception -> 0x0036 }
            r3 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x0036 }
            r0 = r0.b;	 Catch:{ Exception -> 0x0036 }
            com.tencent.map.b.b.a(r3, r2, r0);	 Catch:{ Exception -> 0x0036 }
        L_0x0035:
            return;
        L_0x0036:
            r0 = move-exception;
            r0 = r1;
        L_0x0038:
            r0 = r0 + 1;
            r2 = 3;
            if (r0 > r2) goto L_0x006e;
        L_0x003d:
            r2 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
            sleep(r2);	 Catch:{ Exception -> 0x006c }
            r2 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x006c }
            r2 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x006c }
            r2 = r2.getBytes();	 Catch:{ Exception -> 0x006c }
            r2 = com.tencent.map.b.j.a(r2);	 Catch:{ Exception -> 0x006c }
            r3 = "http://ls.map.soso.com/deflect?c=1";
            r4 = "SOSO MAP LBS SDK";
            r2 = com.tencent.map.b.b.a(r3, r4, r2);	 Catch:{ Exception -> 0x006c }
            r3 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x006c }
            r4 = 0;
            r3.k = r4;	 Catch:{ Exception -> 0x006c }
            r3 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x006c }
            r3 = com.tencent.map.b.j.b(r3);	 Catch:{ Exception -> 0x006c }
            r4 = com.tencent.map.b.b.this;	 Catch:{ Exception -> 0x006c }
            r2 = r2.b;	 Catch:{ Exception -> 0x006c }
            com.tencent.map.b.b.a(r4, r3, r2);	 Catch:{ Exception -> 0x006c }
            goto L_0x0035;
        L_0x006c:
            r2 = move-exception;
            goto L_0x0038;
        L_0x006e:
            r0 = com.tencent.map.b.b.this;
            r0.k = r1;
            r0 = com.tencent.map.b.b.this;
            r0 = r0.i;
            if (r0 == 0) goto L_0x0035;
        L_0x007b:
            r0 = com.tencent.map.b.b.this;
            r0 = r0.i;
            r0.a(r6, r6);
            goto L_0x0035;
            */
        }
    }

    public b() {
        this.c = 0.0d;
        this.d = 0.0d;
        this.e = 0.0d;
        this.f = 0.0d;
        this.g = 0.0d;
        this.h = 0.0d;
        this.j = null;
        this.k = false;
        this.a = com.umeng.a.d;
    }

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    static /* synthetic */ void a(b bVar, byte[] bArr, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(new String(bArr, str));
        } catch (Exception e) {
            if (bVar.i != null) {
                bVar.i.a(360.0d, 360.0d);
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(stringBuffer.toString()).getJSONObject(ShareActivity.KEY_LOCATION);
            double d = jSONObject.getDouble(ParamKey.LATITUDE);
            double d2 = jSONObject.getDouble(ParamKey.LONGITUDE);
            bVar.g = d - bVar.e;
            bVar.h = d2 - bVar.f;
            bVar.c = bVar.e;
            bVar.d = bVar.f;
            if (bVar.i != null) {
                bVar.i.a(d, d2);
            }
        } catch (JSONException e2) {
            if (bVar.i != null) {
                bVar.i.a(360.0d, 360.0d);
            }
        }
    }

    public final void a(double d, double d2, a aVar) {
        this.i = aVar;
        if (!(this.g == 0.0d || this.h == 0.0d)) {
            float[] fArr = new float[10];
            Location.distanceBetween(d, d2, this.c, this.d, fArr);
            if (fArr[0] < 1500.0f) {
                this.i.a(this.g + d, this.h + d2);
                return;
            }
        }
        if (!this.k) {
            this.a = new StringBuilder("{\"source\":101,\"access_token\":\"160e7bd42dec9428721034e0146fc6dd\",\"location\":{\"latitude\":").append(d).append(",\"longitude\":").append(d2).append("}\t}").toString();
            this.e = d;
            this.f = d2;
            this.j = new b();
            this.j.start();
        }
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static n a(String str, String str2, byte[] bArr) throws o, r, Exception {
        Object obj = 1;
        if (l.b() == null) {
            obj = null;
        }
        if (obj == null) {
            throw new o();
        }
        try {
            return q.a(false, str, str2, null, bArr, false, true);
        } catch (Exception e) {
            throw e;
        }
    }
}
