package com.xunlei.downloadprovider.c;

import org.json.JSONObject;

// compiled from: CommentManager.java
final class b implements com.android.volley.r.b<JSONObject> {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    b(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void onResponse(java.lang.Object r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.c.b.onResponse(java.lang.Object):void");
        /*
        this = this;
        r1 = 0;
        r13 = (org.json.JSONObject) r13;
        com.xunlei.downloadprovider.c.a.a;
        r0 = new java.lang.StringBuilder;
        r2 = "history comment list response=>";
        r0.<init>(r2);
        r2 = r13.toString();
        r0.append(r2);
        r0 = 0;
        if (r13 == 0) goto L_0x01d8;
    L_0x0018:
        r2 = "comments";
        r2 = r13.has(r2);	 Catch:{ Exception -> 0x01e2 }
        if (r2 == 0) goto L_0x01d8;
    L_0x0021:
        r0 = "comments";
        r2 = r13.optJSONArray(r0);	 Catch:{ Exception -> 0x01e2 }
        r3 = r2.length();	 Catch:{ Exception -> 0x01e2 }
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01e2 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x01e2 }
    L_0x0031:
        if (r1 >= r3) goto L_0x01d8;
    L_0x0033:
        r4 = r2.optJSONObject(r1);	 Catch:{ Exception -> 0x01e2 }
        r5 = new com.xunlei.downloadprovider.c.a.c;	 Catch:{ Exception -> 0x01e2 }
        r5.<init>();	 Catch:{ Exception -> 0x01e2 }
        r6 = "tid";
        r6 = r4.getString(r6);	 Catch:{ Exception -> 0x01e2 }
        r5.f = r6;	 Catch:{ Exception -> 0x01e2 }
        r6 = "comment";
        r6 = r4.getJSONObject(r6);	 Catch:{ Exception -> 0x01e2 }
        r7 = "cid";
        r8 = r6.getLong(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.a = r8;	 Catch:{ Exception -> 0x01e2 }
        r7 = "sourceId";
        r7 = r6.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.e = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "comment";
        r7 = r6.getString(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.a(r7);	 Catch:{ Exception -> 0x01e2 }
        r7 = "time";
        r8 = r6.getLong(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.c = r8;	 Catch:{ Exception -> 0x01e2 }
        r7 = "userName";
        r7 = r6.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.j = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "uid";
        r8 = r6.optLong(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.i = r8;	 Catch:{ Exception -> 0x01e2 }
        r7 = "userImg";
        r7 = r6.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.k = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = r5.j;	 Catch:{ Exception -> 0x01e2 }
        if (r7 == 0) goto L_0x009c;
    L_0x0090:
        r7 = r5.j;	 Catch:{ Exception -> 0x01e2 }
        r7 = r7.trim();	 Catch:{ Exception -> 0x01e2 }
        r7 = android.text.TextUtils.isGraphic(r7);	 Catch:{ Exception -> 0x01e2 }
        if (r7 != 0) goto L_0x00a1;
    L_0x009c:
        r7 = "\u8fc5\u96f7\u7528\u6237";
        r5.j = r7;	 Catch:{ Exception -> 0x01e2 }
    L_0x00a1:
        r7 = "gcount";
        r8 = r6.getLong(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.n = r8;	 Catch:{ Exception -> 0x01e2 }
        r7 = "rcount";
        r8 = r6.optLong(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.p = r8;	 Catch:{ Exception -> 0x01e2 }
        r7 = "scount";
        r8 = 0;
        r7 = r6.optInt(r7, r8);	 Catch:{ Exception -> 0x01e2 }
        r5.o = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "isPraise";
        r8 = 0;
        r7 = r6.optBoolean(r7, r8);	 Catch:{ Exception -> 0x01e2 }
        r5.m = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "isPdRiew";
        r8 = 0;
        r7 = r6.optBoolean(r7, r8);	 Catch:{ Exception -> 0x01e2 }
        r5.l = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "replys";
        r7 = r6.optJSONArray(r7);	 Catch:{ Exception -> 0x01e2 }
        if (r7 == 0) goto L_0x013a;
    L_0x00da:
        r8 = r7.length();	 Catch:{ Exception -> 0x01e2 }
        if (r8 <= 0) goto L_0x013a;
    L_0x00e0:
        r8 = new java.util.ArrayList;	 Catch:{ Exception -> 0x01e2 }
        r9 = 1;
        r8.<init>(r9);	 Catch:{ Exception -> 0x01e2 }
        r5.q = r8;	 Catch:{ Exception -> 0x01e2 }
        r9 = 0;
        r7 = r7.optJSONObject(r9);	 Catch:{ Exception -> 0x01e2 }
        if (r7 == 0) goto L_0x013a;
    L_0x00ef:
        r9 = new com.xunlei.downloadprovider.c.a.n;	 Catch:{ Exception -> 0x01e2 }
        r9.<init>();	 Catch:{ Exception -> 0x01e2 }
        r10 = "cid";
        r10 = r7.getLong(r10);	 Catch:{ Exception -> 0x01e2 }
        r9.a = r10;	 Catch:{ Exception -> 0x01e2 }
        r10 = "content";
        r10 = r7.getString(r10);	 Catch:{ Exception -> 0x01e2 }
        r9.a(r10);	 Catch:{ Exception -> 0x01e2 }
        r10 = "uid";
        r10 = r7.getLong(r10);	 Catch:{ Exception -> 0x01e2 }
        r9.c = r10;	 Catch:{ Exception -> 0x01e2 }
        r10 = "user";
        r10 = r7.getString(r10);	 Catch:{ Exception -> 0x01e2 }
        r9.d = r10;	 Catch:{ Exception -> 0x01e2 }
        r10 = "userImg";
        r7 = r7.getString(r10);	 Catch:{ Exception -> 0x01e2 }
        r9.e = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = r9.d;	 Catch:{ Exception -> 0x01e2 }
        if (r7 == 0) goto L_0x0132;
    L_0x0126:
        r7 = r9.d;	 Catch:{ Exception -> 0x01e2 }
        r7 = r7.trim();	 Catch:{ Exception -> 0x01e2 }
        r7 = android.text.TextUtils.isGraphic(r7);	 Catch:{ Exception -> 0x01e2 }
        if (r7 != 0) goto L_0x0137;
    L_0x0132:
        r7 = "\u8fc5\u96f7\u7528\u6237";
        r9.d = r7;	 Catch:{ Exception -> 0x01e2 }
    L_0x0137:
        r8.add(r9);	 Catch:{ Exception -> 0x01e2 }
    L_0x013a:
        r7 = "device";
        r7 = r6.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.d = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "po";
        r7 = r6.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.g = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "ci";
        r6 = r6.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r5.h = r6;	 Catch:{ Exception -> 0x01e2 }
        r6 = "videoinfo";
        r4 = r4.optJSONObject(r6);	 Catch:{ Exception -> 0x01e2 }
        if (r4 == 0) goto L_0x01d1;
    L_0x015e:
        r6 = new com.xunlei.downloadprovider.web.base.model.u;	 Catch:{ Exception -> 0x01e2 }
        r6.<init>();	 Catch:{ Exception -> 0x01e2 }
        r7 = "id";
        r7 = r4.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.a = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "title";
        r7 = r4.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.b = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "gcid";
        r7 = r4.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.g = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "pic_url";
        r7 = r4.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.c = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "duration";
        r8 = r4.optLong(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.o = r8;	 Catch:{ Exception -> 0x01e2 }
        r7 = "play_url";
        r7 = r4.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.e = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "favorite_count";
        r7 = r4.optInt(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.l = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "channel_info";
        r4 = r4.optJSONObject(r7);	 Catch:{ Exception -> 0x01e2 }
        if (r4 == 0) goto L_0x01cf;
    L_0x01ab:
        r7 = "title";
        r7 = r4.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.i = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "icon_url";
        r7 = r4.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.h = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "cover_url";
        r7 = r4.optString(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.j = r7;	 Catch:{ Exception -> 0x01e2 }
        r7 = "type";
        r4 = r4.optInt(r7);	 Catch:{ Exception -> 0x01e2 }
        r6.n = r4;	 Catch:{ Exception -> 0x01e2 }
    L_0x01cf:
        r5.s = r6;	 Catch:{ Exception -> 0x01e2 }
    L_0x01d1:
        r0.add(r5);	 Catch:{ Exception -> 0x01e2 }
        r1 = r1 + 1;
        goto L_0x0031;
    L_0x01d8:
        r1 = r12.a;	 Catch:{ Exception -> 0x01e2 }
        if (r1 == 0) goto L_0x01e1;
    L_0x01dc:
        r1 = r12.a;	 Catch:{ Exception -> 0x01e2 }
        r1.a(r0);	 Catch:{ Exception -> 0x01e2 }
    L_0x01e1:
        return;
    L_0x01e2:
        r0 = move-exception;
        com.xunlei.downloadprovider.c.a.a;
        r0.getMessage();
        r1 = new com.xunlei.downloadprovider.c.a$b;
        r1.<init>();
        r2 = "result";
        r3 = -1001; // 0xfffffffffffffc17 float:NaN double:NaN;
        r2 = r13.optInt(r2, r3);
        r1.a = r2;
        r2 = r0.getMessage();
        r1.b = r2;
        r2 = r12.a;
        if (r2 == 0) goto L_0x0208;
    L_0x0203:
        r2 = r12.a;
        r2.a(r1);
    L_0x0208:
        r0.printStackTrace();
        goto L_0x01e1;
        */
    }
}
