package com.xunlei.downloadprovider.web.browser;

import android.view.View.OnClickListener;

// compiled from: BrowserActivity.java
final class k implements OnClickListener {
    final /* synthetic */ BrowserActivity a;

    k(BrowserActivity browserActivity) {
        this.a = browserActivity;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.web.browser.k.onClick(android.view.View):void");
        /*
        this = this;
        r0 = 1;
        r1 = 0;
        r2 = r7.a;
        r2 = r2.h;
        if (r2 == 0) goto L_0x00b7;
    L_0x000a:
        r2 = r7.a;	 Catch:{ Exception -> 0x010b }
        r2 = r2.h;	 Catch:{ Exception -> 0x010b }
        r2 = r2.g();	 Catch:{ Exception -> 0x010b }
        r3 = r7.a;	 Catch:{ Exception -> 0x010b }
        r3 = r3.l;	 Catch:{ Exception -> 0x010b }
        r3 = r3.a;	 Catch:{ Exception -> 0x010b }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x010b }
        if (r3 != 0) goto L_0x00b8;
    L_0x0022:
        r3 = com.xunlei.downloadprovider.model.i.a();	 Catch:{ Exception -> 0x010b }
        r3 = r3.a(r2);	 Catch:{ Exception -> 0x010b }
        if (r3 == 0) goto L_0x00b8;
    L_0x002c:
        r4 = r3.c;	 Catch:{ Exception -> 0x010b }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x010b }
        if (r4 != 0) goto L_0x00b8;
    L_0x0034:
        r3 = r3.c;	 Catch:{ Exception -> 0x010b }
        r3 = r3.endsWith(r2);	 Catch:{ Exception -> 0x010b }
        if (r3 == 0) goto L_0x00b8;
    L_0x003c:
        if (r0 == 0) goto L_0x00ba;
    L_0x003e:
        r0 = r7.a;	 Catch:{ Exception -> 0x010b }
        r0 = r0.l;	 Catch:{ Exception -> 0x010b }
        r1 = r0.a;	 Catch:{ Exception -> 0x010b }
        r0 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x010b }
        if (r0 != 0) goto L_0x00b3;
    L_0x004c:
        r0 = com.xunlei.downloadprovider.model.i.a();	 Catch:{ Exception -> 0x010b }
        r0 = r0.a(r2);	 Catch:{ Exception -> 0x010b }
        if (r0 == 0) goto L_0x00b3;
    L_0x0056:
        r3 = r0.c;	 Catch:{ Exception -> 0x010b }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x010b }
        if (r3 != 0) goto L_0x00b3;
    L_0x005e:
        r0 = r0.c;	 Catch:{ Exception -> 0x010b }
        r0 = r0.endsWith(r2);	 Catch:{ Exception -> 0x010b }
        if (r0 == 0) goto L_0x00b3;
    L_0x0066:
        r0 = com.xunlei.downloadprovider.model.i.a();	 Catch:{ Exception -> 0x010b }
        r0.b(r2);	 Catch:{ Exception -> 0x010b }
        r3 = new android.widget.Toast;	 Catch:{ Exception -> 0x010b }
        r0 = r1.b;	 Catch:{ Exception -> 0x010b }
        r0 = r0.getActivity();	 Catch:{ Exception -> 0x010b }
        r3.<init>(r0);	 Catch:{ Exception -> 0x010b }
        r0 = r1.b;	 Catch:{ Exception -> 0x010b }
        r0 = r0.getActivity();	 Catch:{ Exception -> 0x010b }
        r0 = android.view.LayoutInflater.from(r0);	 Catch:{ Exception -> 0x010b }
        r4 = 2130968744; // 0x7f0400a8 float:1.754615E38 double:1.052838449E-314;
        r5 = 0;
        r4 = r0.inflate(r4, r5);	 Catch:{ Exception -> 0x010b }
        r0 = 2131755473; // 0x7f1001d1 float:1.9141826E38 double:1.0532271445E-314;
        r0 = r4.findViewById(r0);	 Catch:{ Exception -> 0x010b }
        r0 = (android.widget.TextView) r0;	 Catch:{ Exception -> 0x010b }
        r5 = r1.b;	 Catch:{ Exception -> 0x010b }
        r6 = 2131232768; // 0x7f080800 float:1.8081655E38 double:1.052968894E-314;
        r5 = r5.getString(r6);	 Catch:{ Exception -> 0x010b }
        r0.setText(r5);	 Catch:{ Exception -> 0x010b }
        r0 = 17;
        r5 = 0;
        r6 = 0;
        r3.setGravity(r0, r5, r6);	 Catch:{ Exception -> 0x010b }
        r3.setView(r4);	 Catch:{ Exception -> 0x010b }
        r0 = 0;
        r3.setDuration(r0);	 Catch:{ Exception -> 0x010b }
        r3.show();	 Catch:{ Exception -> 0x010b }
        r1.b(r2);	 Catch:{ Exception -> 0x010b }
    L_0x00b3:
        r0 = 1;
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a(r0);	 Catch:{ Exception -> 0x010b }
    L_0x00b7:
        return;
    L_0x00b8:
        r0 = r1;
        goto L_0x003c;
    L_0x00ba:
        r0 = 0;
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a(r0);	 Catch:{ Exception -> 0x010b }
        r0 = r7.a;	 Catch:{ Exception -> 0x010b }
        r0 = r0.h;	 Catch:{ Exception -> 0x010b }
        r1 = r0.h();	 Catch:{ Exception -> 0x010b }
        r0 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x010b }
        if (r0 != 0) goto L_0x00b7;
    L_0x00ce:
        if (r1 != 0) goto L_0x00d3;
    L_0x00d0:
        r1 = "";
    L_0x00d3:
        r0 = r7.a;	 Catch:{ Exception -> 0x010b }
        r0 = r0.l;	 Catch:{ Exception -> 0x010b }
        r3 = r0.a;	 Catch:{ Exception -> 0x010b }
        r0 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x010b }
        if (r0 != 0) goto L_0x0107;
    L_0x00e1:
        r0 = com.xunlei.downloadprovider.model.i.a();	 Catch:{ Exception -> 0x010b }
        r0 = r0.a(r2);	 Catch:{ Exception -> 0x010b }
        if (r0 == 0) goto L_0x0110;
    L_0x00eb:
        r4 = r0.c;	 Catch:{ Exception -> 0x010b }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x010b }
        if (r4 != 0) goto L_0x0110;
    L_0x00f3:
        r0 = r0.c;	 Catch:{ Exception -> 0x010b }
        r0 = r0.endsWith(r2);	 Catch:{ Exception -> 0x010b }
        if (r0 == 0) goto L_0x0110;
    L_0x00fb:
        r0 = r3.b;	 Catch:{ Exception -> 0x010b }
        r2 = 2131232767; // 0x7f0807ff float:1.8081653E38 double:1.0529688935E-314;
        r0 = r0.getString(r2);	 Catch:{ Exception -> 0x010b }
        r3.a(r0);	 Catch:{ Exception -> 0x010b }
    L_0x0107:
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a(r1, r1);	 Catch:{ Exception -> 0x010b }
        goto L_0x00b7;
    L_0x010b:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b7;
    L_0x0110:
        r0 = "://";
        r4 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x010b }
        if (r4 == 0) goto L_0x0159;
    L_0x0119:
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x010b }
        if (r4 != 0) goto L_0x0159;
    L_0x011f:
        r4 = r2.contains(r0);	 Catch:{ Exception -> 0x010b }
        if (r4 == 0) goto L_0x0159;
    L_0x0125:
        r4 = r2.indexOf(r0);	 Catch:{ Exception -> 0x010b }
        r0 = r0.length();	 Catch:{ Exception -> 0x010b }
        r0 = r0 + r4;
        r0 = r2.substring(r0);	 Catch:{ Exception -> 0x010b }
    L_0x0132:
        r4 = new com.xunlei.downloadprovider.model.b;	 Catch:{ Exception -> 0x010b }
        r4.<init>();	 Catch:{ Exception -> 0x010b }
        r4.b = r0;	 Catch:{ Exception -> 0x010b }
        r4.c = r2;	 Catch:{ Exception -> 0x010b }
        r0 = 0;
        r4.d = r0;	 Catch:{ Exception -> 0x010b }
        r0 = com.xunlei.downloadprovider.member.login.a.a.a();	 Catch:{ Exception -> 0x010b }
        r0 = r0.a(r4);	 Catch:{ Exception -> 0x010b }
        if (r0 == 0) goto L_0x0107;
    L_0x0148:
        r0 = 1;
        r3.a(r0);	 Catch:{ Exception -> 0x010b }
        r0 = r3.b;	 Catch:{ Exception -> 0x010b }
        r2 = 2131232766; // 0x7f0807fe float:1.808165E38 double:1.052968893E-314;
        r0 = r0.getString(r2);	 Catch:{ Exception -> 0x010b }
        r3.a(r0);	 Catch:{ Exception -> 0x010b }
        goto L_0x0107;
    L_0x0159:
        r0 = r1;
        goto L_0x0132;
        */
    }
}
