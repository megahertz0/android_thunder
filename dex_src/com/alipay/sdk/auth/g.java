package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class g implements OnClickListener {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.cancel();
        AuthActivity.a(this.a.b.a, false);
        h.a(this.a.b.a, AuthActivity.c(this.a.b.a) + "?resultCode=150");
        this.a.b.a.finish();
    }
}
