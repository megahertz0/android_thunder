package com.xunlei.downloadprovider.personal.settings;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.f.c;
import com.xunlei.downloadprovider.util.aa;

// compiled from: SettingsIndexFragment.java
final class s extends Handler {
    final /* synthetic */ SettingsIndexFragment a;

    s(SettingsIndexFragment settingsIndexFragment) {
        this.a = settingsIndexFragment;
    }

    public final void handleMessage(Message message) {
        if (SettingsIndexFragment.a(this.a) != null && !SettingsIndexFragment.b(this.a).isFinishing() && message.what == 10004 && message.arg1 == 0) {
            SettingsIndexFragment.a(this.a, (c) ((com.xunlei.downloadprovider.b.c) message.obj).b);
            SettingsIndexFragment.c(this.a);
            new StringBuilder("manual update info=>").append(SettingsIndexFragment.d(this.a).toString());
            aa.a(BrothersApplication.a().getApplicationContext(), "update_info_manual", SettingsIndexFragment.d(this.a).b());
        }
    }
}
