package com.xunlei.downloadprovider.personal.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: SettingsIndexFragment.java
final class y implements OnClickListener {
    final /* synthetic */ SettingsIndexFragment a;

    y(SettingsIndexFragment settingsIndexFragment) {
        this.a = settingsIndexFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (SettingsIndexFragment.i(this.a) != null) {
            SettingsIndexFragment.i(this.a).dismiss();
        }
    }
}
