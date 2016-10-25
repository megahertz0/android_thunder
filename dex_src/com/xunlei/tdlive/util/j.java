package com.xunlei.tdlive.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.tdlive.user.f;

// compiled from: RequestHelper.java
final class j implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;

    j(Context context, boolean z, String str, String str2) {
        this.a = context;
        this.b = z;
        this.c = str;
        this.d = str2;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (i == 1) {
            f.a().a(this.a, "complain", new k(this));
        }
    }
}
