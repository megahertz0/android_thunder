package com.xunlei.downloadprovider.personal.settings;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SettingsIndexFragment.java
final class t implements OnClickListener {
    final /* synthetic */ SettingsIndexFragment a;

    t(SettingsIndexFragment settingsIndexFragment) {
        this.a = settingsIndexFragment;
    }

    public final void onClick(View view) {
        SettingsIndexFragment.e(this.a).finish();
    }
}
