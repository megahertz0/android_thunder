package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.xiazaibao.setting.DownloadDevieSettingActivity;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import org.android.spdy.SpdyProtocol;

// compiled from: RemoteDownloadTaskViewHolder.java
final class ad implements OnClickListener {
    final /* synthetic */ ac a;

    ad(ac acVar) {
        this.a = acVar;
    }

    public final void onClick(View view) {
        DownloadDevieSettingActivity.a(ac.a(this.a));
        ac.b(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        XZBShouleiUtil.getInstance().putClickSettingFromXZBDownloadListIntoPrefs(ac.a(this.a));
    }
}
