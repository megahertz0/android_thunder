package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: PersonalSpaceActivity.java
final class al implements OnClickListener {
    final /* synthetic */ PersonalSpaceActivity a;

    al(PersonalSpaceActivity personalSpaceActivity) {
        this.a = personalSpaceActivity;
    }

    public final void onClick(View view) {
        this.a.onBackPressed();
    }
}
