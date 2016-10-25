package com.xunlei.common.member.c;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.a.b;
import com.xunlei.common.member.c.p.a;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.http.client.HttpResponseException;

// compiled from: UserKeyLoginTask.java
final class f$1 extends b {
    private /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.member.c.f$1.a(java.lang.String):void");
        /*
        this = this;
        r1 = 1;
        r0 = 0;
        r2 = "UserKeyLoginTask";
        com.xunlei.common.base.XLLog.v(r2, r7);
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00fd }
        r2.<init>(r7);	 Catch:{ JSONException -> 0x00fd }
        r3 = "UserKeyLoginTask";
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00fd }
        r5 = "result json objet = ";
        r4.<init>(r5);	 Catch:{ JSONException -> 0x00fd }
        r5 = r2.toString();	 Catch:{ JSONException -> 0x00fd }
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x00fd }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x00fd }
        com.xunlei.common.base.XLLog.v(r3, r4);	 Catch:{ JSONException -> 0x00fd }
        r3 = "errorCode";
        r3 = r2.getInt(r3);	 Catch:{ JSONException -> 0x00fd }
        if (r3 != 0) goto L_0x00c4;
    L_0x0030:
        r0 = com.xunlei.common.member.a.m.a();	 Catch:{ JSONException -> 0x00fd }
        r0.v();	 Catch:{ JSONException -> 0x00fd }
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.h();	 Catch:{ JSONException -> 0x00fd }
        r0.clearUserData();	 Catch:{ JSONException -> 0x00fd }
        r0 = "UserKeyLoginTask";
        r1 = "start to obtain xl user info.";
        com.xunlei.common.base.XLLog.v(r0, r1);	 Catch:{ JSONException -> 0x00fd }
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.h();	 Catch:{ JSONException -> 0x00fd }
        r0.a(r2);	 Catch:{ JSONException -> 0x00fd }
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.h();	 Catch:{ JSONException -> 0x00fd }
        r0.dump();	 Catch:{ JSONException -> 0x00fd }
        r0 = "newLoginKey";
        r0 = r2.optString(r0);	 Catch:{ JSONException -> 0x00fd }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ JSONException -> 0x00fd }
        if (r1 != 0) goto L_0x008e;
    L_0x0068:
        r1 = new com.xunlei.common.member.a.d;	 Catch:{ JSONException -> 0x00fd }
        r2 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r2 = r2.h();	 Catch:{ JSONException -> 0x00fd }
        r3 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.UserID;	 Catch:{ JSONException -> 0x00fd }
        r2 = r2.getIntValue(r3);	 Catch:{ JSONException -> 0x00fd }
        r3 = "";
        r4 = "";
        r1.<init>(r2, r3, r4, r0);	 Catch:{ JSONException -> 0x00fd }
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.g();	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.h();	 Catch:{ JSONException -> 0x00fd }
        r2 = com.xunlei.common.member.a.d.a.b;	 Catch:{ JSONException -> 0x00fd }
        com.xunlei.common.member.a.d.a(r1, r0, r2);	 Catch:{ JSONException -> 0x00fd }
    L_0x008e:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.h();	 Catch:{ JSONException -> 0x00fd }
        r1 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r1 = r1.g();	 Catch:{ JSONException -> 0x00fd }
        r1 = r1.h();	 Catch:{ JSONException -> 0x00fd }
        r0.a(r1);	 Catch:{ JSONException -> 0x00fd }
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r1 = 0;
        com.xunlei.common.member.c.f.a(r0, r1);	 Catch:{ JSONException -> 0x00fd }
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.f();	 Catch:{ JSONException -> 0x00fd }
        r1 = com.xunlei.common.member.c.p.a.d;	 Catch:{ JSONException -> 0x00fd }
        if (r0 == r1) goto L_0x00bc;
    L_0x00b1:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.g();	 Catch:{ JSONException -> 0x00fd }
        r1 = 1;
        r2 = 0;
        r0.a(r1, r2);	 Catch:{ JSONException -> 0x00fd }
    L_0x00bc:
        r0 = r6.a;
        r1 = com.xunlei.common.member.c.p.a.c;
        r0.d(r1);
    L_0x00c3:
        return;
    L_0x00c4:
        r2 = com.xunlei.common.member.c.f.e(r3);	 Catch:{ JSONException -> 0x00fd }
        if (r2 != 0) goto L_0x00d0;
    L_0x00ca:
        r2 = com.xunlei.common.member.c.f.f(r3);	 Catch:{ JSONException -> 0x00fd }
        if (r2 == 0) goto L_0x010a;
    L_0x00d0:
        r0 = com.xunlei.common.member.c.f.e(r3);	 Catch:{ JSONException -> 0x00fd }
        if (r0 == 0) goto L_0x00e5;
    L_0x00d6:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.g();	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.h();	 Catch:{ JSONException -> 0x00fd }
        r2 = com.xunlei.common.member.a.d.a.c;	 Catch:{ JSONException -> 0x00fd }
        com.xunlei.common.member.a.d.a(r0, r2);	 Catch:{ JSONException -> 0x00fd }
    L_0x00e5:
        r0 = com.xunlei.common.member.c.f.f(r3);	 Catch:{ JSONException -> 0x00fd }
        if (r0 == 0) goto L_0x0112;
    L_0x00eb:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.h();	 Catch:{ JSONException -> 0x00fd }
        r0.clearUserData();	 Catch:{ JSONException -> 0x00fd }
        r0 = r1;
    L_0x00f5:
        if (r0 == 0) goto L_0x0114;
    L_0x00f7:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        com.xunlei.common.member.c.f.a(r0, r3);	 Catch:{ JSONException -> 0x00fd }
        goto L_0x00bc;
    L_0x00fd:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r6.a;
        r1 = 16777214; // 0xfffffe float:2.3509884E-38 double:8.289045E-317;
        com.xunlei.common.member.c.f.a(r0, r1);
        goto L_0x00bc;
    L_0x010a:
        r2 = 8;
        if (r3 == r2) goto L_0x00f5;
    L_0x010e:
        r2 = 16;
        if (r3 == r2) goto L_0x00f5;
    L_0x0112:
        r0 = r1;
        goto L_0x00f5;
    L_0x0114:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.g();	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.h();	 Catch:{ JSONException -> 0x00fd }
        r0 = com.xunlei.common.member.a.d.a(r0);	 Catch:{ JSONException -> 0x00fd }
        if (r0 == 0) goto L_0x0173;
    L_0x0124:
        r1 = r0.b;	 Catch:{ JSONException -> 0x00fd }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ JSONException -> 0x00fd }
        if (r1 != 0) goto L_0x0173;
    L_0x012c:
        r1 = "UserKeyLoginTask";
        r2 = "retry with uid and psw.";
        com.xunlei.common.base.XLLog.v(r1, r2);	 Catch:{ JSONException -> 0x00fd }
        r1 = new com.xunlei.common.member.c.g;	 Catch:{ JSONException -> 0x00fd }
        r2 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r2 = r2.g();	 Catch:{ JSONException -> 0x00fd }
        r1.<init>(r2);	 Catch:{ JSONException -> 0x00fd }
        r1.a();	 Catch:{ JSONException -> 0x00fd }
        r2 = new com.xunlei.common.member.c.f$a;	 Catch:{ JSONException -> 0x00fd }
        r3 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r2.<init>(r3);	 Catch:{ JSONException -> 0x00fd }
        r1.a(r2);	 Catch:{ JSONException -> 0x00fd }
        r2 = "xl-uid-login";
        r1.b(r2);	 Catch:{ JSONException -> 0x00fd }
        r2 = 0;
        r1.b(r2);	 Catch:{ JSONException -> 0x00fd }
        r2 = r0.a;	 Catch:{ JSONException -> 0x00fd }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ JSONException -> 0x00fd }
        r3 = 1;
        r1.a(r2, r3);	 Catch:{ JSONException -> 0x00fd }
        r2 = r0.b;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.c;	 Catch:{ JSONException -> 0x00fd }
        r1.a(r2, r0);	 Catch:{ JSONException -> 0x00fd }
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        r0 = r0.g();	 Catch:{ JSONException -> 0x00fd }
        r0.a(r1);	 Catch:{ JSONException -> 0x00fd }
        goto L_0x00c3;
    L_0x0173:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00fd }
        com.xunlei.common.member.c.f.a(r0, r3);	 Catch:{ JSONException -> 0x00fd }
        goto L_0x00bc;
        */
    }

    public final void a(Throwable th) {
        int statusCode;
        XLLog.e("UserKeyLoginTask", new StringBuilder("error = ").append(th.getMessage()).toString());
        Object obj = 16781312;
        if (th instanceof UnknownHostException) {
            obj = 16781311;
        }
        if (th instanceof SocketException) {
            obj = 16781310;
        }
        if (th instanceof SocketTimeoutException) {
            obj = 16781309;
        }
        if (th instanceof HttpResponseException) {
            statusCode = ((HttpResponseException) th).getStatusCode();
        }
        f.a(this.a, statusCode);
        this.a.d(a.c);
    }
}
