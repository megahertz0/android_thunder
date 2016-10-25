package com.xunlei.tdlive.play.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.play.view.ConnectMicView;

// compiled from: NormalScreenLayoutPresenter.java
class s implements OnClickListener {
    final /* synthetic */ ConnectMicView a;
    final /* synthetic */ q b;

    s(q qVar, ConnectMicView connectMicView) {
        this.b = qVar;
        this.a = connectMicView;
    }

    public void onClick(View view) {
        this.b.a(this.a);
    }
}
