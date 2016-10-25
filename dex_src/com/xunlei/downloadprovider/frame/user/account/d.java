package com.xunlei.downloadprovider.frame.user.account;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.user.account.a.b;

// compiled from: ActionSheetHelper.java
public final class d implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ b b;

    public d(Dialog dialog, b bVar) {
        this.a = dialog;
        this.b = bVar;
    }

    public final void onClick(View view) {
        this.a.dismiss();
        if (this.b != null) {
            this.b.a(1);
        }
    }
}
