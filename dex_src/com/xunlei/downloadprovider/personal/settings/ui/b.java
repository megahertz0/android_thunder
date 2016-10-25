package com.xunlei.downloadprovider.personal.settings.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: SettingsItem.java
final class b implements OnClickListener {
    final /* synthetic */ SettingsItem a;

    b(SettingsItem settingsItem) {
        this.a = settingsItem;
    }

    public final void onClick(View view) {
        SettingsItem.a(this.a).a(!SettingsItem.a(this.a).getSwitchState(), true);
    }
}
