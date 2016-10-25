package com.xunlei.downloadprovider.loading;

import android.content.Intent;
import com.xunlei.tdlive.R;

// compiled from: LoadingActivity.java
final class g implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ LoadingActivity b;

    g(LoadingActivity loadingActivity, Intent intent) {
        this.b = loadingActivity;
        this.a = intent;
    }

    public final void run() {
        this.b.startActivity(this.a);
        this.b.finish();
        this.b.overridePendingTransition(R.anim.translate_between_interface_right_in, R.anim.translate_between_interface_left_out);
    }
}
