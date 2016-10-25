package com.xunlei.tdlive.play.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.user.f;

// compiled from: UserInfoWindowHelper.java
class al implements OnClickListener {
    final /* synthetic */ ah a;

    al(ah ahVar) {
        this.a = ahVar;
    }

    public void onClick(View view) {
        f.a().a(view.getContext(), "attention_viewer", new am(this));
    }
}
