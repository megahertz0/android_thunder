package com.xunlei.downloadprovider.filemanager;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.filemanager.ui.h;

// compiled from: PathChooserActivity.java
final class m implements OnClickListener {
    final /* synthetic */ PathChooserActivity a;

    m(PathChooserActivity pathChooserActivity) {
        this.a = pathChooserActivity;
    }

    public final void onClick(View view) {
        this.a.f = new h(this.a, this.a.e.getCurrentPath());
        this.a.f.setOnDismissListener(new n(this));
        this.a.f.setOnShowListener(new o(this));
        this.a.f.show();
    }
}
