package com.xunlei.downloadprovider.web;

import android.widget.Toast;
import com.umeng.a;

// compiled from: DetailPageBrowserActivity.java
final class k implements Runnable {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public final void run() {
        if (this.a.b.getIntent() != null && this.a.b.getIntent().getExtras() != null) {
            CharSequence string = this.a.b.getIntent().getExtras().getString("ToastMessage");
            if (!this.a.b.m && string != null && !a.d.equals(string.trim())) {
                Toast.makeText(this.a.b, string, 0).show();
                this.a.b.m = true;
            }
        }
    }
}
