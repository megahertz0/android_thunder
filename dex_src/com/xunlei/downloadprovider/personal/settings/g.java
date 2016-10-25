package com.xunlei.downloadprovider.personal.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.commonview.dialog.q;

// compiled from: AboutBoxActivity.java
final class g implements OnClickListener {
    final /* synthetic */ q a;
    final /* synthetic */ AboutBoxActivity b;

    g(AboutBoxActivity aboutBoxActivity, q qVar) {
        this.b = aboutBoxActivity;
        this.a = qVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.dismiss();
    }
}
