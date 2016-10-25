package com.baidu.mobads.production;

import android.content.Context;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import java.util.Timer;
import java.util.TimerTask;

class q extends TimerTask {
    final /* synthetic */ IXAdPackageUtils a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ Timer d;
    final /* synthetic */ o e;

    q(o oVar, IXAdPackageUtils iXAdPackageUtils, Context context, String str, Timer timer) {
        this.e = oVar;
        this.a = iXAdPackageUtils;
        this.b = context;
        this.c = str;
        this.d = timer;
    }

    public void run() {
        if (o.e(this.e) >= o.f(this.e)) {
            if (o.e(this.e) >= o.g(this.e)) {
                this.d.cancel();
                this.a.sendDialerIsSuccess(this.b, true, 0, this.c);
            } else if (!this.a.isForeground(this.b, this.c) && this.a.isForeground(this.b, this.b.getPackageName())) {
                this.d.cancel();
                this.a.sendDialerIsSuccess(this.b, false, o.e(this.e), this.c);
            }
        }
        o.h(this.e);
    }
}
