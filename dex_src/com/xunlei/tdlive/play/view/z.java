package com.xunlei.tdlive.play.view;

import android.view.View;
import android.view.View.OnClickListener;
import cn.nodemedia.LivePublisher;

// compiled from: PublishButtonBar.java
class z implements OnClickListener {
    final /* synthetic */ PublishButtonBar a;

    z(PublishButtonBar publishButtonBar) {
        this.a = publishButtonBar;
    }

    public void onClick(View view) {
        LivePublisher.switchCamera();
        this.a.b();
    }
}
