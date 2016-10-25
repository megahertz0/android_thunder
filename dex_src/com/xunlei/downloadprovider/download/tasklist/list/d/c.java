package com.xunlei.downloadprovider.download.tasklist.list.d;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.a.a;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: KuainiaoTrialRemindViewHolder.java
final class c implements OnClickListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (this.a.m.equals("speedUp") || this.a.m.equals("inTrial") || this.a.m.equals("afterTrial")) {
            a.a(this.a.k, PayFrom.DOWNLOAD_TASK_RENEWTIP, BuildConfig.VERSION_NAME);
        }
    }
}
