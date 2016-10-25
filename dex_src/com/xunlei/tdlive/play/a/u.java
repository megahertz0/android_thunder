package com.xunlei.tdlive.play.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.play.view.ConnectMicView.a;

// compiled from: NormalScreenLayoutPresenter.java
class u implements OnClickListener {
    final /* synthetic */ b a;

    u(b bVar) {
        this.a = bVar;
    }

    public void onClick(View view) {
        if (this.a.a.a.getConnectMicView().getState() == a.c) {
            this.a.a.l.a(1, this.a.a.c);
        }
    }
}
