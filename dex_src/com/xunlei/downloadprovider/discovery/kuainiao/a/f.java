package com.xunlei.downloadprovider.discovery.kuainiao.a;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.discovery.kuainiao.g;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: KuaiNiaoAccelerator.java
final class f implements OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ b b;

    f(b bVar, Activity activity) {
        this.b = bVar;
        this.a = activity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Context context = this.a;
        PayFrom payFrom = PayFrom.BIRD_TIP;
        LoginHelper.a();
        if (LoginHelper.c()) {
            com.xunlei.downloadprovider.discovery.kuainiao.f.a(context, payFrom);
        } else {
            LoginHelper.a().a(context, new g(context, payFrom), 1);
        }
        StatReporter.reportKuaiNiaoDialog("member");
    }
}
