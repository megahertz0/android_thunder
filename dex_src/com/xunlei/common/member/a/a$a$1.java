package com.xunlei.common.member.a;

import android.os.Bundle;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.a.a.a;
import org.android.agoo.common.AgooConstants;

// compiled from: AsyncHttpProxy.java
final class a$a$1 implements BaseHttpClientListener {
    private /* synthetic */ String a;
    private /* synthetic */ a b;

    a$a$1(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onSuccess(int r6, org.apache.http.Header[] r7, byte[] r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.member.a.a$a$1.onSuccess(int, org.apache.http.Header[], byte[]):void");
        /*
        this = this;
        r4 = 1;
        r0 = r5.b;
        r0 = com.xunlei.common.member.a.a.a.a(r0, r8);
        r1 = r5.b;
        r1 = r1.b;
        if (r1 != r4) goto L_0x0105;
    L_0x000d:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00d5 }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x00d5 }
        r2 = "errorCode";
        r2 = r1.getInt(r2);	 Catch:{ JSONException -> 0x00d5 }
        r3 = 8;
        if (r2 != r3) goto L_0x0051;
    L_0x001d:
        r2 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.c;	 Catch:{ JSONException -> 0x00d5 }
        r3 = 3;
        if (r2 >= r3) goto L_0x0051;
    L_0x0024:
        r0 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r0.c;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1 + 1;
        r0.c = r1;	 Catch:{ JSONException -> 0x00d5 }
        r0 = new android.os.Bundle;	 Catch:{ JSONException -> 0x00d5 }
        r0.<init>();	 Catch:{ JSONException -> 0x00d5 }
        r1 = "type";
        r2 = "onRetry";
        r0.putString(r1, r2);	 Catch:{ JSONException -> 0x00d5 }
        r1 = "count";
        r2 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.c;	 Catch:{ JSONException -> 0x00d5 }
        r0.putInt(r1, r2);	 Catch:{ JSONException -> 0x00d5 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1.h;	 Catch:{ JSONException -> 0x00d5 }
        r1.a(r0);	 Catch:{ JSONException -> 0x00d5 }
        r0 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r0.a();	 Catch:{ JSONException -> 0x00d5 }
    L_0x0050:
        return;
    L_0x0051:
        r2 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r3 = 0;
        r2.c = r3;	 Catch:{ JSONException -> 0x00d5 }
        r2 = "errorIsRetry";
        r2 = r1.has(r2);	 Catch:{ JSONException -> 0x00d5 }
        if (r2 != r4) goto L_0x00ef;
    L_0x005f:
        r2 = "errorIsRetry";
        r1 = r1.getInt(r2);	 Catch:{ JSONException -> 0x00d5 }
        if (r1 == 0) goto L_0x00ef;
    L_0x0068:
        r1 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1.d;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.h;	 Catch:{ JSONException -> 0x00d5 }
        r3 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r3 = r3.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.a(r3);	 Catch:{ JSONException -> 0x00d5 }
        if (r1 >= r2) goto L_0x00ef;
    L_0x007a:
        r0 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1.g;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1 + 1;
        r2 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.h;	 Catch:{ JSONException -> 0x00d5 }
        r3 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r3 = r3.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.a(r3);	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1 % r2;
        r0.g = r1;	 Catch:{ JSONException -> 0x00d5 }
        r0 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r0.d;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1 + 1;
        r0.d = r1;	 Catch:{ JSONException -> 0x00d5 }
        r0 = new android.os.Bundle;	 Catch:{ JSONException -> 0x00d5 }
        r0.<init>();	 Catch:{ JSONException -> 0x00d5 }
        r1 = "type";
        r2 = "onRetry";
        r0.putString(r1, r2);	 Catch:{ JSONException -> 0x00d5 }
        r1 = "count";
        r2 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.d;	 Catch:{ JSONException -> 0x00d5 }
        r0.putInt(r1, r2);	 Catch:{ JSONException -> 0x00d5 }
        r1 = "address";
        r2 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.h;	 Catch:{ JSONException -> 0x00d5 }
        r3 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r3 = r3.b;	 Catch:{ JSONException -> 0x00d5 }
        r4 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r4 = r4.g;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r2.a(r3, r4);	 Catch:{ JSONException -> 0x00d5 }
        r0.putString(r1, r2);	 Catch:{ JSONException -> 0x00d5 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1.h;	 Catch:{ JSONException -> 0x00d5 }
        r1.a(r0);	 Catch:{ JSONException -> 0x00d5 }
        r0 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r0.a();	 Catch:{ JSONException -> 0x00d5 }
        goto L_0x0050;
    L_0x00d5:
        r0 = move-exception;
        r1 = r5.b;
        r1 = r1.f;
        if (r1 == 0) goto L_0x00ea;
    L_0x00dc:
        r1 = r5.b;
        r2 = r5.a;
        com.xunlei.common.member.a.a.a.a(r1, r2);
        r1 = r5.b;
        r1 = r1.f;
        r1.a(r0);
    L_0x00ea:
        r0.printStackTrace();
        goto L_0x0050;
    L_0x00ef:
        r1 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1.f;	 Catch:{ JSONException -> 0x00d5 }
        if (r1 == 0) goto L_0x0050;
    L_0x00f5:
        r1 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r2 = r5.a;	 Catch:{ JSONException -> 0x00d5 }
        com.xunlei.common.member.a.a.a.a(r1, r2);	 Catch:{ JSONException -> 0x00d5 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x00d5 }
        r1 = r1.f;	 Catch:{ JSONException -> 0x00d5 }
        r1.a(r0);	 Catch:{ JSONException -> 0x00d5 }
        goto L_0x0050;
    L_0x0105:
        r1 = r5.b;
        r1 = r1.b;
        r2 = 7;
        if (r1 != r2) goto L_0x0175;
    L_0x010c:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0159 }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x0159 }
        r2 = "errorCode";
        r1 = r1.getInt(r2);	 Catch:{ JSONException -> 0x0159 }
        if (r1 == 0) goto L_0x015f;
    L_0x011a:
        r0 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r0 = r0.d;	 Catch:{ JSONException -> 0x0159 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r1 = r1.h;	 Catch:{ JSONException -> 0x0159 }
        r2 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r2 = r2.b;	 Catch:{ JSONException -> 0x0159 }
        r1 = r1.a(r2);	 Catch:{ JSONException -> 0x0159 }
        if (r0 >= r1) goto L_0x0050;
    L_0x012c:
        r0 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r0 = r0.h;	 Catch:{ JSONException -> 0x0159 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r1 = r1.h;	 Catch:{ JSONException -> 0x0159 }
        r1 = com.xunlei.common.member.a.a.b(r1);	 Catch:{ JSONException -> 0x0159 }
        r1 = r1 + 1;
        r2 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r2 = r2.h;	 Catch:{ JSONException -> 0x0159 }
        r3 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r3 = r3.b;	 Catch:{ JSONException -> 0x0159 }
        r2 = r2.a(r3);	 Catch:{ JSONException -> 0x0159 }
        r1 = r1 % r2;
        com.xunlei.common.member.a.a.a(r0, r1);	 Catch:{ JSONException -> 0x0159 }
        r0 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r1 = r0.d;	 Catch:{ JSONException -> 0x0159 }
        r1 = r1 + 1;
        r0.d = r1;	 Catch:{ JSONException -> 0x0159 }
        r0 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r0.a();	 Catch:{ JSONException -> 0x0159 }
        goto L_0x0050;
    L_0x0159:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0050;
    L_0x015f:
        r1 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r1 = r1.f;	 Catch:{ JSONException -> 0x0159 }
        if (r1 == 0) goto L_0x0050;
    L_0x0165:
        r1 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r2 = r5.a;	 Catch:{ JSONException -> 0x0159 }
        com.xunlei.common.member.a.a.a.a(r1, r2);	 Catch:{ JSONException -> 0x0159 }
        r1 = r5.b;	 Catch:{ JSONException -> 0x0159 }
        r1 = r1.f;	 Catch:{ JSONException -> 0x0159 }
        r1.a(r0);	 Catch:{ JSONException -> 0x0159 }
        goto L_0x0050;
    L_0x0175:
        r1 = r5.b;
        r1 = r1.f;
        if (r1 == 0) goto L_0x0050;
    L_0x017b:
        r1 = r5.b;
        r2 = r5.a;
        com.xunlei.common.member.a.a.a.a(r1, r2);
        r1 = r5.b;
        r1 = r1.f;
        r1.a(r0);
        goto L_0x0050;
        */
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        a aVar;
        if (this.b.b == 7) {
            if (this.b.d < this.b.h.a(this.b.b)) {
                this.b.g = (this.b.g + 1) % this.b.h.a(this.b.b);
                aVar = this.b;
                aVar.d++;
                this.b.a();
                XLLog.d(a.a(this.b.h), th.getMessage() + "portal\u91cd\u8bd5    \u6b21\u6570\uff1a" + this.b.d + "#request=" + hashCode());
            }
        } else if (this.b.d < this.b.h.a(this.b.b)) {
            this.b.g = (this.b.g + 1) % this.b.h.a(this.b.b);
            aVar = this.b;
            aVar.d++;
            Bundle bundle = new Bundle();
            bundle.putString(AgooConstants.MESSAGE_TYPE, "onRetry");
            bundle.putInt("count", this.b.d);
            bundle.putString("address", this.b.h.a(this.b.b, this.b.g));
            this.b.h.a(bundle);
            this.b.a();
            XLLog.d(a.a(this.b.h), th.getMessage() + "\u8bf7\u6c42\u91cd\u8bd5    \u6b21\u6570\uff1a" + this.b.d);
        } else if (this.b.f != null) {
            a.a(this.b, this.a);
            this.b.f.a(th);
        }
    }
}
