package com.xunlei.downloadprovider.discovery.kuainiao.a;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import com.umeng.a;
import com.xunlei.common.accelerator.XLAccelUtil;
import com.xunlei.common.accelerator.XLOnAccelListener;
import com.xunlei.downloadprovider.discovery.kuainiao.KuaiNiaoActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// compiled from: KuaiNiaoAccelerator.java
public final class b {
    String a;
    public Context b;
    Stack<WeakReference<Activity>> c;
    public int d;
    boolean e;
    public XLOnAccelListener f;
    private List<a> g;

    public b(Context context, Stack<WeakReference<Activity>> stack) {
        this.a = a.d;
        this.g = new ArrayList();
        this.e = true;
        this.f = new c(this);
        this.b = context;
        this.c = stack;
        XLAccelUtil.getAccelerator().attachListener(this.f);
        a();
    }

    public final void a() {
        XLAccelUtil.getAccelerator().init(this.b, com.xunlei.downloadprovider.a.b.d(), com.xunlei.downloadprovider.a.b.w());
    }

    public static boolean b() {
        XLAccelUtil.getAccelerator().queryStatus();
        return false;
    }

    static /* synthetic */ void a(b bVar, String str) {
        Builder builder = new Builder(bVar.b);
        builder.setSmallIcon(2130837711);
        builder.setTicker(bVar.b.getResources().getString(2131231637));
        builder.setAutoCancel(true);
        builder.setNumber(0);
        if (com.xunlei.downloadprovider.businessutil.b.a().h()) {
            builder.setDefaults(1);
        } else {
            builder.setDefaults(0);
        }
        PendingIntent activity = PendingIntent.getActivity(bVar.b, 0, KuaiNiaoActivity.a(bVar.b, true), 268435456);
        RemoteViews remoteViews = new RemoteViews(bVar.b.getPackageName(), 2130968885);
        remoteViews.setTextViewText(2131756683, bVar.b.getResources().getString(2131231638, new Object[]{str}));
        builder.setContent(remoteViews);
        builder.setContentIntent(activity);
        com.xunlei.downloadprovider.a.a.b.a(bVar.b).a(50001, builder.build());
    }
}
