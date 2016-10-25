package com.xunlei.downloadprovider.download.tasklist.list.d;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

// compiled from: KuainiaoTrialRemindViewHolder.java
final class g implements OnClickListener {
    final /* synthetic */ b a;

    g(b bVar) {
        this.a = bVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.y != null) {
            this.a.y.dismiss();
            this.a.y = null;
        }
        this.a.a(this.a.k.getString(R.string.download_list_kuainiao_speed_up_tip, new Object[]{Integer.valueOf(this.a.o)}), true, this.a.k.getString(R.string.download_list_kuainiao_open), b.b);
        this.a.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        b.a(this.a, false);
    }
}
