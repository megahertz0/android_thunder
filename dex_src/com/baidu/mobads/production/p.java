package com.baidu.mobads.production;

import android.content.Context;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import java.util.Timer;
import java.util.TimerTask;

class p extends TimerTask {
    final /* synthetic */ IXAdPackageUtils a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ Timer d;
    final /* synthetic */ String e;
    final /* synthetic */ o f;

    p(o oVar, IXAdPackageUtils iXAdPackageUtils, Context context, String str, Timer timer, String str2) {
        this.f = oVar;
        this.a = iXAdPackageUtils;
        this.b = context;
        this.c = str;
        this.d = timer;
        this.e = str2;
    }

    public void run() {
        if (o.a(this.f) >= o.b(this.f)) {
            if (o.a(this.f) >= o.c(this.f)) {
                this.d.cancel();
                this.a.sendAPOIsSuccess(this.b, true, 0, this.e, this.c);
            } else if (!this.a.isForeground(this.b, this.c)) {
                this.d.cancel();
                this.a.sendAPOIsSuccess(this.b, false, o.a(this.f), this.e, this.c);
            }
        }
        o.d(this.f);
    }
}
