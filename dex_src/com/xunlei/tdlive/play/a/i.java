package com.xunlei.tdlive.play.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.user.f;

// compiled from: BaseNormalScreenLayoutPresenter.java
class i implements OnClickListener {
    final /* synthetic */ c a;

    i(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        f.a().a(this.a.i, "attention_room", new j(this, view));
    }
}
