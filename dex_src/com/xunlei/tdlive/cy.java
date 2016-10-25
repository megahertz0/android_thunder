package com.xunlei.tdlive;

import android.view.View;
import com.xunlei.tdlive.play.view.ac;
import com.xunlei.tdlive.play.view.b;
import com.xunlei.tdlive.util.y.a;

// compiled from: LivePublishEndingActivity.java
class cy implements a {
    final /* synthetic */ View a;
    final /* synthetic */ LivePublishEndingActivity b;

    cy(LivePublishEndingActivity livePublishEndingActivity, View view) {
        this.b = livePublishEndingActivity;
        this.a = view;
    }

    public void a(int i, String str) {
        ac acVar = new ac(this.b);
        b.a aVar = new b.a();
        aVar.a(this.a);
        aVar.a(str);
        acVar.a(aVar);
        acVar.b();
    }
}
