package com.xunlei.downloadprovider.download.tasklist.list.d;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.xllib.R;

// compiled from: KuainiaoTrialRemindViewHolder.java
final class h implements OnClickListener {
    final /* synthetic */ b a;

    h(b bVar) {
        this.a = bVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.y != null) {
            this.a.y.dismiss();
            this.a.y = null;
        }
        LoginHelper.a().a(this.a.k, new i(this.a), R.styleable.Toolbar_collapseContentDescription);
    }
}
