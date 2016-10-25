package com.xunlei.common.member.c;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;

// compiled from: UserWxLoginTask.java
final class t$2 implements BaseHttpClientListener {
    final /* synthetic */ t a;

    t$2(t tVar) {
        this.a = tVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onSuccess(int r8, org.apache.http.Header[] r9, byte[] r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.member.c.t$2.onSuccess(int, org.apache.http.Header[], byte[]):void");
        /*
        this = this;
        r6 = 16777214; // 0xfffffe float:2.3509884E-38 double:8.289045E-317;
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r0 = "UserWxLoginTask";
        r1 = "recieve web session.";
        com.xunlei.common.base.XLLog.e(r0, r1);
        if (r8 != r3) goto L_0x00b9;
    L_0x0010:
        r0 = "";
        r1 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x00d8 }
        r2 = "UTF-8";
        r1.<init>(r10, r2);	 Catch:{ UnsupportedEncodingException -> 0x00d8 }
        r0 = "UserWxLoginTask";
        com.xunlei.common.base.XLLog.v(r0, r1);	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0087 }
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0087 }
        r2 = "code";
        r2 = r0.getInt(r2);	 Catch:{ JSONException -> 0x0087 }
        if (r2 != r3) goto L_0x006d;
    L_0x002f:
        r2 = r7.a;	 Catch:{ JSONException -> 0x0087 }
        r3 = "uid";
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x0087 }
        com.xunlei.common.member.c.t.a(r2, r3);	 Catch:{ JSONException -> 0x0087 }
        r2 = r7.a;	 Catch:{ JSONException -> 0x0087 }
        r3 = "sessionid";
        r3 = r0.getString(r3);	 Catch:{ JSONException -> 0x0087 }
        com.xunlei.common.member.c.t.a(r2, r3);	 Catch:{ JSONException -> 0x0087 }
        r2 = r7.a;	 Catch:{ JSONException -> 0x0087 }
        r3 = "first_login";
        r0 = r0.optInt(r3);	 Catch:{ JSONException -> 0x0087 }
        com.xunlei.common.member.c.t.b(r2, r0);	 Catch:{ JSONException -> 0x0087 }
        r0 = r7.a;	 Catch:{ JSONException -> 0x0087 }
        r2 = 257; // 0x101 float:3.6E-43 double:1.27E-321;
        com.xunlei.common.member.c.t.c(r0, r2);	 Catch:{ JSONException -> 0x0087 }
        r0 = com.xunlei.common.member.a.m.a();	 Catch:{ JSONException -> 0x0087 }
        r0 = r0.n();	 Catch:{ JSONException -> 0x0087 }
        r2 = new com.xunlei.common.member.c.t$2$1;	 Catch:{ JSONException -> 0x0087 }
        r2.<init>();	 Catch:{ JSONException -> 0x0087 }
        r4 = 0;
        r0.postDelayed(r2, r4);	 Catch:{ JSONException -> 0x0087 }
    L_0x006c:
        return;
    L_0x006d:
        r3 = r7.a;	 Catch:{ JSONException -> 0x0087 }
        r4 = "msg";
        r0 = r0.optString(r4);	 Catch:{ JSONException -> 0x0087 }
        com.xunlei.common.member.c.t.b(r3, r0);	 Catch:{ JSONException -> 0x0087 }
        r0 = r7.a;	 Catch:{ JSONException -> 0x0087 }
        com.xunlei.common.member.c.t.d(r0, r2);	 Catch:{ JSONException -> 0x0087 }
        r0 = r7.a;	 Catch:{ JSONException -> 0x0087 }
        r2 = 16781308; // 0x1000ffc float:2.3521355E-38 double:8.291068E-317;
        com.xunlei.common.member.c.t.e(r0, r2);	 Catch:{ JSONException -> 0x0087 }
        goto L_0x006c;
    L_0x0087:
        r0 = move-exception;
        r2 = "UserWxLoginTask";
        r3 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        r4 = "error = ";
        r3.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        r0 = r0.getMessage();	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        r0 = r3.append(r0);	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        r0 = r0.toString();	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        com.xunlei.common.base.XLLog.e(r2, r0);	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        r0 = r7.a;	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        r2 = 16777214; // 0xfffffe float:2.3509884E-38 double:8.289045E-317;
        com.xunlei.common.member.c.t.e(r0, r2);	 Catch:{ UnsupportedEncodingException -> 0x00ab }
        goto L_0x006c;
    L_0x00ab:
        r0 = move-exception;
        r0 = r1;
    L_0x00ad:
        r1 = r7.a;
        com.xunlei.common.member.c.t.e(r1, r6);
        r1 = "UserWxLoginTask";
        com.xunlei.common.base.XLLog.e(r1, r0);
        goto L_0x006c;
    L_0x00b9:
        r0 = r7.a;
        r1 = 16781295; // 0x1000fef float:2.352132E-38 double:8.2910614E-317;
        com.xunlei.common.member.c.t.e(r0, r1);
        r0 = "UserWxLoginTask";
        r1 = new java.lang.StringBuilder;
        r2 = "error = ";
        r1.<init>(r2);
        r1 = r1.append(r8);
        r1 = r1.toString();
        com.xunlei.common.base.XLLog.e(r0, r1);
        goto L_0x006c;
    L_0x00d8:
        r1 = move-exception;
        goto L_0x00ad;
        */
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.e("UserWxLoginTask", new StringBuilder("error = ").append(th.getMessage()).toString());
        t.b(this.a, th.getMessage());
        t.d(this.a, 16781295);
        t.e(this.a, 16781295);
    }
}
