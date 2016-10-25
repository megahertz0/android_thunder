package com.baidu.mobads.production;

import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

class b implements IOAdEventListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run(com.baidu.mobads.openad.interfaces.event.IOAdEvent r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.production.b.run(com.baidu.mobads.openad.interfaces.event.IOAdEvent):void");
        /*
        this = this;
        r0 = r4.a;
        r0.g();
        r0 = "URLLoader.Load.Complete";
        r1 = r5.getType();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x009f;
    L_0x0012:
        r0 = r5.getData();
        r1 = "message";
        r0 = r0.get(r1);
        r0 = (java.lang.String) r0;
        r1 = r4.a;	 Catch:{ JSONException -> 0x007d }
        r2 = new com.baidu.mobads.vo.c;	 Catch:{ JSONException -> 0x007d }
        r2.<init>(r0);	 Catch:{ JSONException -> 0x007d }
        r1.setAdResponseInfo(r2);	 Catch:{ JSONException -> 0x007d }
        r0 = r4.a;	 Catch:{ JSONException -> 0x007d }
        r0 = r0.getAdResponseInfo();	 Catch:{ JSONException -> 0x007d }
        r0 = r0.getAdInstanceList();	 Catch:{ JSONException -> 0x007d }
        r0 = r0.size();	 Catch:{ JSONException -> 0x007d }
        if (r0 <= 0) goto L_0x004b;
    L_0x0039:
        r0 = r4.a;	 Catch:{ JSONException -> 0x007d }
        r1 = 1;
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ JSONException -> 0x007d }
        r0.b = r1;	 Catch:{ JSONException -> 0x007d }
        r0 = r4.a;	 Catch:{ JSONException -> 0x007d }
        r1 = "XAdMouldeLoader ad-server requesting success";
        r0.a(r1);	 Catch:{ JSONException -> 0x007d }
    L_0x004a:
        return;
    L_0x004b:
        r0 = com.baidu.mobads.j.m.a();	 Catch:{ JSONException -> 0x007d }
        r0 = r0.q();	 Catch:{ JSONException -> 0x007d }
        r1 = r4.a;	 Catch:{ JSONException -> 0x007d }
        r1 = r1.getAdResponseInfo();	 Catch:{ JSONException -> 0x007d }
        r1 = r1.getErrorCode();	 Catch:{ JSONException -> 0x007d }
        r2 = r4.a;	 Catch:{ JSONException -> 0x007d }
        r2 = r2.getAdResponseInfo();	 Catch:{ JSONException -> 0x007d }
        r2 = r2.getErrorMessage();	 Catch:{ JSONException -> 0x007d }
        r3 = "";
        r0.printErrorMessage(r1, r2, r3);	 Catch:{ JSONException -> 0x007d }
        r0 = r4.a;	 Catch:{ JSONException -> 0x007d }
        r1 = r4.a;	 Catch:{ JSONException -> 0x007d }
        r1 = r1.getAdResponseInfo();	 Catch:{ JSONException -> 0x007d }
        r1 = r1.getErrorMessage();	 Catch:{ JSONException -> 0x007d }
        r0.b(r1);	 Catch:{ JSONException -> 0x007d }
        goto L_0x004a;
    L_0x007d:
        r0 = move-exception;
        r0 = "response json parsing error";
        r1 = com.baidu.mobads.j.m.a();
        r1 = r1.q();
        r2 = "";
        r3 = "";
        r1.printErrorMessage(r2, r0, r3);
        r1 = r4.a;
        r1.b(r0);
        r1 = com.baidu.mobads.c.a.a();
        r1.a(r0);
        goto L_0x004a;
    L_0x009f:
        r0 = "request ad-server error, io_err/timeout";
        r1 = com.baidu.mobads.j.m.a();
        r1 = r1.q();
        r2 = "";
        r3 = "";
        r1.printErrorMessage(r2, r0, r3);
        r1 = r4.a;
        r1.b(r0);
        r1 = com.baidu.mobads.c.a.a();
        r1.a(r0);
        goto L_0x004a;
        */
    }
}
