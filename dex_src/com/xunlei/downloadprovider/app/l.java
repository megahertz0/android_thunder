package com.xunlei.downloadprovider.app;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: GuideActivity.java
final class l implements OnClickListener {
    final /* synthetic */ GuideActivity a;

    l(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    public final void onClick(View view) {
        if (this.a.r.isChecked()) {
            this.a.r.setChecked(false);
        } else {
            this.a.r.setChecked(true);
        }
    }
}
