package com.baidu.mobads.j;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobads.interfaces.utils.IXAdActivityUtils;

public class b implements IXAdActivityUtils {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean isFullScreen(android.app.Activity r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.j.b.isFullScreen(android.app.Activity):java.lang.Boolean");
        /*
        this = this;
        r1 = 0;
        if (r4 == 0) goto L_0x001b;
    L_0x0003:
        r0 = r4.getWindow();	 Catch:{ Exception -> 0x0021 }
        r0 = r0.getAttributes();	 Catch:{ Exception -> 0x0021 }
        r0 = r0.flags;	 Catch:{ Exception -> 0x0021 }
        r0 = r0 & 1024;
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        if (r0 != r2) goto L_0x0019;
    L_0x0013:
        r0 = 1;
    L_0x0014:
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ Exception -> 0x0021 }
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r1;
        goto L_0x0014;
    L_0x001b:
        r0 = 0;
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ Exception -> 0x0021 }
        goto L_0x0018;
    L_0x0021:
        r0 = move-exception;
        r0 = java.lang.Boolean.valueOf(r1);
        goto L_0x0018;
        */
    }

    public void showAlertDialog(Activity activity, String str, String str2, String str3, String str4, boolean z, OnClickListener onClickListener, OnClickListener onClickListener2) {
        if (activity != null) {
            try {
                new Builder(activity).setCancelable(z).setTitle(str).setMessage(str2).setPositiveButton(str3, onClickListener).setNegativeButton(str4, onClickListener2).create().show();
            } catch (Throwable e) {
                m.a().f().d(e);
            }
        }
    }
}
