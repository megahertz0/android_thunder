package com.xunlei.downloadprovider.personal.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.downloadprovider.xiazaibao.setting.DownloadDevieSettingActivity;
import org.android.spdy.SpdyProtocol;

// compiled from: SettingsIndexFragment.java
final class u implements OnClickListener {
    final /* synthetic */ SettingsIndexFragment a;

    u(SettingsIndexFragment settingsIndexFragment) {
        this.a = settingsIndexFragment;
    }

    public final void onClick(View view) {
        XZBReporter.c();
        DownloadDevieSettingActivity.a(this.a.getActivity());
        if (SettingsIndexFragment.f(this.a).getVisibility() == 0 && SettingsIndexFragment.g(this.a).getVisibility() == 0) {
            e.a();
            e.g();
        }
        SettingsIndexFragment.f(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SettingsIndexFragment.g(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
