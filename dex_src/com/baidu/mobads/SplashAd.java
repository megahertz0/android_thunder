package com.baidu.mobads;

import android.content.Context;
import android.view.ViewGroup;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.production.h.a;

public class SplashAd {
    private a a;
    private volatile String b;
    private SplashAdListener c;
    private IOAdEventListener d;

    public SplashAd(Context context, ViewGroup viewGroup, SplashAdListener splashAdListener, String str) {
        this(context, viewGroup, splashAdListener, str, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SplashAd(android.content.Context r7, android.view.ViewGroup r8, com.baidu.mobads.SplashAdListener r9, java.lang.String r10, boolean r11) {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.SplashAd.<init>(android.content.Context, android.view.ViewGroup, com.baidu.mobads.SplashAdListener, java.lang.String, boolean):void");
        /*
        this = this;
        r6.<init>();
        r0 = "init";
        r6.b = r0;
        r0 = new com.baidu.mobads.ag;
        r0.<init>(r6);
        r6.c = r0;
        r0 = new com.baidu.mobads.ah;
        r0.<init>(r6);
        r6.d = r0;
        if (r9 == 0) goto L_0x001a;
    L_0x0018:
        r6.c = r9;	 Catch:{ Exception -> 0x0048 }
    L_0x001a:
        r0 = android.text.TextUtils.isEmpty(r10);	 Catch:{ Exception -> 0x0048 }
        if (r0 == 0) goto L_0x0029;
    L_0x0020:
        r0 = r6.c;	 Catch:{ Exception -> 0x0048 }
        r1 = "\u8bf7\u60a8\u8f93\u5165\u6b63\u786e\u7684\u5e7f\u544a\u4f4dID";
        r0.onAdFailed(r1);	 Catch:{ Exception -> 0x0048 }
    L_0x0028:
        return;
    L_0x0029:
        r3 = new com.baidu.mobads.ao;	 Catch:{ Exception -> 0x0048 }
        r3.<init>(r7);	 Catch:{ Exception -> 0x0048 }
        r0 = new com.baidu.mobads.aj;	 Catch:{ Exception -> 0x0048 }
        r1 = r6;
        r2 = r7;
        r4 = r10;
        r5 = r11;
        r0.<init>(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0048 }
        r3.a(r0);	 Catch:{ Exception -> 0x0048 }
        r0 = new android.view.ViewGroup$LayoutParams;	 Catch:{ Exception -> 0x0048 }
        r1 = -1;
        r2 = -1;
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x0048 }
        r3.setLayoutParams(r0);	 Catch:{ Exception -> 0x0048 }
        r8.addView(r3);	 Catch:{ Exception -> 0x0048 }
        goto L_0x0028;
    L_0x0048:
        r0 = move-exception;
        r1 = com.baidu.mobads.j.m.a();
        r1 = r1.f();
        r1.d(r0);
        r1 = com.baidu.mobads.c.a.a();
        r2 = new java.lang.StringBuilder;
        r3 = "splash ad create failed: ";
        r2.<init>(r3);
        r0 = r0.toString();
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.a(r0);
        goto L_0x0028;
        */
    }

    public static void setAppSid(Context context, String str) {
        m.a().m().setAppId(str);
    }

    @Deprecated
    public static void setAppSec(Context context, String str) {
    }

    public void destroy() {
        if (this.a != null) {
            this.a.l();
        }
    }
}
