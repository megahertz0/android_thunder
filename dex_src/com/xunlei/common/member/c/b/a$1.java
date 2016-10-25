package com.xunlei.common.member.c.b;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;

// compiled from: UserGetRecommendAvatarsTask.java
final class a$1 implements BaseHttpClientListener {
    private /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onSuccess(int r8, org.apache.http.Header[] r9, byte[] r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.member.c.b.a$1.onSuccess(int, org.apache.http.Header[], byte[]):void");
        /*
        this = this;
        r6 = 16777214; // 0xfffffe float:2.3509884E-38 double:8.289045E-317;
        r0 = 0;
        r1 = r7.a;
        r1 = com.xunlei.common.member.c.b.a.a(r1);
        r2 = new java.lang.StringBuilder;
        r3 = "statusCode = ";
        r2.<init>(r3);
        r2 = r2.append(r8);
        r2 = r2.toString();
        com.xunlei.common.base.XLLog.v(r1, r2);
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r8 != r1) goto L_0x00ae;
    L_0x0021:
        r1 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r2 = "UTF-8";
        r1.<init>(r10, r2);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r2 = r7.a;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r2 = com.xunlei.common.member.c.b.a.b(r2);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r3 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r4 = "response from server body = ";
        r3.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r3 = r3.append(r1);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r3 = r3.toString();	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        com.xunlei.common.base.XLLog.v(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r2 = new org.json.JSONObject;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r2.<init>(r1);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r1 = r7.a;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r3 = "result";
        r3 = r2.getInt(r3);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r1 = com.xunlei.common.member.c.b.a.a(r1, r3);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        if (r1 != 0) goto L_0x009e;
    L_0x0056:
        r1 = "data";
        r2 = r2.getJSONArray(r1);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r3 = r2.length();	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        if (r3 <= 0) goto L_0x008b;
    L_0x0063:
        r1 = r7.a;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r4 = new com.xunlei.common.member.XLAvatarItem[r3];	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        com.xunlei.common.member.c.b.a.a(r1, r4);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r1 = r0;
    L_0x006b:
        if (r1 >= r3) goto L_0x0084;
    L_0x006d:
        r0 = r2.get(r1);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r0 = (org.json.JSONObject) r0;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r4 = r7.a;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r4 = com.xunlei.common.member.c.b.a.c(r4);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r5 = new com.xunlei.common.member.XLAvatarItem;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r5.<init>(r0);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r4[r1] = r5;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x006b;
    L_0x0084:
        r0 = r7.a;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r1 = 0;
        com.xunlei.common.member.c.b.a.b(r0, r1);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
    L_0x008a:
        return;
    L_0x008b:
        r0 = r7.a;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        r1 = 16781294; // 0x1000fee float:2.3521316E-38 double:8.291061E-317;
        com.xunlei.common.member.c.b.a.b(r0, r1);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        goto L_0x008a;
    L_0x0094:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r7.a;
        com.xunlei.common.member.c.b.a.b(r0, r6);
        goto L_0x008a;
    L_0x009e:
        r0 = r7.a;	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        com.xunlei.common.member.c.b.a.b(r0, r1);	 Catch:{ UnsupportedEncodingException -> 0x0094, JSONException -> 0x00a4 }
        goto L_0x008a;
    L_0x00a4:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r7.a;
        com.xunlei.common.member.c.b.a.b(r0, r6);
        goto L_0x008a;
    L_0x00ae:
        r0 = r7.a;
        r1 = 16781295; // 0x1000fef float:2.352132E-38 double:8.2910614E-317;
        com.xunlei.common.member.c.b.a.b(r0, r1);
        goto L_0x008a;
        */
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        a.b(this.a, 16781295);
        XLLog.v(a.d(this.a), "UserGetRecommendAvatarsTask onFailure");
    }
}
