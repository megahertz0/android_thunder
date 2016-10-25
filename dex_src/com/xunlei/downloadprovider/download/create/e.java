package com.xunlei.downloadprovider.download.create;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: CreateUrlTask.java
final class e implements OnClickListener {
    final /* synthetic */ CreateUrlTask a;

    e(CreateUrlTask createUrlTask) {
        this.a = createUrlTask;
    }

    public final void onClick(View view) {
        this.a.f.requestFocus();
        int length = this.a.f.getText().toString().length();
        if (length != 0 && this.a.g) {
            this.a.g = false;
            this.a.f.setSelection(0, length);
        }
    }
}
