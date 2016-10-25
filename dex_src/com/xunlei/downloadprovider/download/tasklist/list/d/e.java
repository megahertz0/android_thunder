package com.xunlei.downloadprovider.download.tasklist.list.d;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.a.a;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

// compiled from: KuainiaoTrialRemindViewHolder.java
final class e implements OnClickListener {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (this.a.m.equals("hasNotTrial")) {
            LoginHelper.a();
            if (LoginHelper.c()) {
                this.a.a(this.a.k.getString(R.string.download_list_kuainiao_speed_up_tip, new Object[]{Integer.valueOf(this.a.o)}), true, this.a.k.getString(R.string.download_list_kuainiao_open), b.b);
                this.a.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                b.a(this.a, true);
                return;
            }
            b bVar = this.a;
            f fVar = new f(this);
            b.g(bVar);
        } else if (this.a.m.equals("speedUp")) {
            a.a(this.a.k, PayFrom.DOWNLOAD_TASK_RENEWTIP, BuildConfig.VERSION_NAME);
        } else if (this.a.m.equals("inTrial")) {
            a.a(this.a.k, PayFrom.DOWNLOAD_TASK_RENEWTIP, BuildConfig.VERSION_NAME);
        } else if (this.a.m.equals("afterTrial")) {
            a.a(this.a.k, PayFrom.DOWNLOAD_TASK_RENEWTIP, BuildConfig.VERSION_NAME);
            b.c(this.a);
        } else {
            this.a.m.equals("mobileNetException");
        }
    }
}
