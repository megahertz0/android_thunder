package com.xunlei.downloadprovider.download.tasklist.list.d;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.member.login.LoginHelper.c;
import org.android.spdy.SpdyProtocol;

// compiled from: KuainiaoTrialRemindViewHolder.java
final class i implements c {
    final /* synthetic */ b a;

    i(b bVar) {
        this.a = bVar;
    }

    public final void a(int i) {
        this.a.a(this.a.k.getString(R.string.download_list_kuainiao_speed_up_tip, new Object[]{Integer.valueOf(this.a.o)}), true, this.a.k.getString(R.string.download_list_kuainiao_open), b.b);
        this.a.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        b.a(this.a, true);
    }
}
