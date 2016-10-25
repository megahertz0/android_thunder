package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.alipay.sdk.data.c;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.k;
import com.alipay.sdk.widget.a;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.open.SocialConstants;
import java.util.List;

public class AuthTask {
    static final Object a;
    private static final int b = 73;
    private Activity c;
    private a d;

    static {
        a = e.class;
    }

    public AuthTask(Activity activity) {
        this.c = activity;
        b a = b.a();
        Context context = this.c;
        c.a();
        a.a(context);
        com.alipay.sdk.app.statistic.a.a(activity);
        this.d = new a(activity, a.c);
    }

    private e.a a() {
        return new a(this);
    }

    private void b() {
        if (this.d != null) {
            this.d.a();
        }
    }

    private void c() {
        if (this.d != null) {
            this.d.b();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String auth(java.lang.String r6, boolean r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AuthTask.auth(java.lang.String, boolean):java.lang.String");
        /*
        this = this;
        monitor-enter(r5);
        if (r7 == 0) goto L_0x0006;
    L_0x0003:
        r5.b();	 Catch:{ all -> 0x0089 }
    L_0x0006:
        r0 = com.alipay.sdk.sys.b.a();	 Catch:{ all -> 0x0089 }
        r1 = r5.c;	 Catch:{ all -> 0x0089 }
        com.alipay.sdk.data.c.a();	 Catch:{ all -> 0x0089 }
        r0.a(r1);	 Catch:{ all -> 0x0089 }
        r1 = com.alipay.sdk.app.h.a();	 Catch:{ all -> 0x0089 }
        r2 = r5.c;	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r0 = new com.alipay.sdk.sys.a;	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r3 = r5.c;	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r3 = r0.a(r6);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r0 = a(r2);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        if (r0 == 0) goto L_0x005d;
    L_0x0029:
        r0 = new com.alipay.sdk.util.e;	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r4 = new com.alipay.sdk.app.a;	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r0.<init>(r2, r4);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r0 = r0.a(r3);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        r4 = "failed";
        r4 = android.text.TextUtils.equals(r0, r4);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        if (r4 != 0) goto L_0x005d;
    L_0x0040:
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        if (r2 == 0) goto L_0x004a;
    L_0x0046:
        r0 = com.alipay.sdk.app.h.a();	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
    L_0x004a:
        r1 = com.alipay.sdk.data.a.b();	 Catch:{ all -> 0x0089 }
        r2 = r5.c;	 Catch:{ all -> 0x0089 }
        r1.a(r2);	 Catch:{ all -> 0x0089 }
        r5.c();	 Catch:{ all -> 0x0089 }
        r1 = r5.c;	 Catch:{ all -> 0x0089 }
        com.alipay.sdk.app.statistic.a.a(r1, r6);	 Catch:{ all -> 0x0089 }
    L_0x005b:
        monitor-exit(r5);
        return r0;
    L_0x005d:
        r0 = r5.b(r2, r3);	 Catch:{ Exception -> 0x0062, all -> 0x0076 }
        goto L_0x004a;
    L_0x0062:
        r0 = move-exception;
        r0 = com.alipay.sdk.data.a.b();	 Catch:{ all -> 0x0089 }
        r2 = r5.c;	 Catch:{ all -> 0x0089 }
        r0.a(r2);	 Catch:{ all -> 0x0089 }
        r5.c();	 Catch:{ all -> 0x0089 }
        r0 = r5.c;	 Catch:{ all -> 0x0089 }
        com.alipay.sdk.app.statistic.a.a(r0, r6);	 Catch:{ all -> 0x0089 }
        r0 = r1;
        goto L_0x005b;
    L_0x0076:
        r0 = move-exception;
        r1 = com.alipay.sdk.data.a.b();	 Catch:{ all -> 0x0089 }
        r2 = r5.c;	 Catch:{ all -> 0x0089 }
        r1.a(r2);	 Catch:{ all -> 0x0089 }
        r5.c();	 Catch:{ all -> 0x0089 }
        r1 = r5.c;	 Catch:{ all -> 0x0089 }
        com.alipay.sdk.app.statistic.a.a(r1, r6);	 Catch:{ all -> 0x0089 }
        throw r0;	 Catch:{ all -> 0x0089 }
    L_0x0089:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
        */
    }

    private String a(Activity activity, String str) {
        String a = new com.alipay.sdk.sys.a(this.c).a(str);
        if (!a((Context) activity)) {
            return b(activity, a);
        }
        String a2 = new e(activity, new a(this)).a(a);
        if (TextUtils.equals(a2, e.b)) {
            return b(activity, a);
        }
        return TextUtils.isEmpty(a2) ? h.a() : a2;
    }

    private String b(Activity activity, String str) {
        i iVar;
        b();
        try {
            List a = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.packet.impl.a().a(activity, str).a().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
            c();
            for (int i = 0; i < a.size(); i++) {
                if (((com.alipay.sdk.protocol.b) a.get(i)).a == com.alipay.sdk.protocol.a.b) {
                    String a2 = a((com.alipay.sdk.protocol.b) a.get(i));
                    c();
                    return a2;
                }
            }
            c();
            iVar = null;
        } catch (Throwable e) {
            i a3 = i.a(i.d.h);
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, e);
            c();
            iVar = a3;
        } catch (Throwable e2) {
            com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, com.alipay.sdk.app.statistic.c.r, e2);
            c();
            iVar = null;
        }
        if (iVar == null) {
            iVar = i.a(i.b.h);
        }
        return h.a(iVar.h, iVar.i, com.umeng.a.d);
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArr = bVar.b;
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_URL, strArr[0]);
        Intent intent = new Intent(this.c, H5AuthActivity.class);
        intent.putExtras(bundle);
        this.c.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        return TextUtils.isEmpty(str) ? h.a() : str;
    }

    private static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(k.b, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            return packageInfo != null && packageInfo.versionCode >= 73;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
