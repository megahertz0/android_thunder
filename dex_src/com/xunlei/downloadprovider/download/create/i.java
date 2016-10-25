package com.xunlei.downloadprovider.download.create;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.personal.settings.HelpActivity;

// compiled from: CreateUrlTask.java
final class i implements OnClickListener {
    final /* synthetic */ CreateUrlTask a;

    i(CreateUrlTask createUrlTask) {
        this.a = createUrlTask;
    }

    public final void onClick(View view) {
        HelpActivity.a(this.a, "file:///android_asset/help/code.html");
    }
}
