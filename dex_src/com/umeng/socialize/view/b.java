package com.umeng.socialize.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: OauthDialog.java
class b implements OnClickListener {
    final /* synthetic */ OauthDialog a;

    b(OauthDialog oauthDialog) {
        this.a = oauthDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
