package com.xunlei.downloadprovider.homepage.recommend;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: XLOfficialAccountRecruitHeaderView.java
final class s implements OnClickListener {
    final /* synthetic */ r a;

    s(r rVar) {
        this.a = rVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.removeHeaderView(this.a.c);
        dialogInterface.dismiss();
    }
}
