package com.baidu.mobads.b;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import com.baidu.mobads.j.m;

public class a extends BroadcastReceiver {
    protected final IXAdLogger a;
    private com.baidu.mobads.command.a b;

    public a(com.baidu.mobads.command.a aVar) {
        this.a = m.a().f();
        this.b = aVar;
    }

    @TargetApi(3)
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String replace = intent.getDataString().replace("package:", com.umeng.a.d);
            if (replace.equals(this.b.i)) {
                IXAdPackageUtils l = m.a().l();
                if (this.b.v && this.b.w != null && !this.b.w.equals(com.umeng.a.d)) {
                    IXAdConstants p = m.a().p();
                    if (l.sendAPOInfo(context, this.b.w, replace, 381, p.getActTypeDownload())) {
                        m.a().m().browserOutside(context, this.b.w);
                    }
                    context.unregisterReceiver(this);
                } else if (this.b.l) {
                    try {
                        Thread.sleep(600);
                        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(replace);
                        launchIntentForPackage.addFlags(268435456);
                        context.startActivity(launchIntentForPackage);
                        context.unregisterReceiver(this);
                    } catch (Throwable e) {
                        this.a.d("InstallReceiver", e);
                    }
                }
                com.baidu.mobads.c.a.a().b(context, this.b);
            }
        }
    }
}
