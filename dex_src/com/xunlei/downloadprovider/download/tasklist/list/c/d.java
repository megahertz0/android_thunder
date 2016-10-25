package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.b;
import com.xunlei.downloadprovider.download.tasklist.list.b.c;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.web.BrowserUtil;

// compiled from: DownloadFreeTrialBanner.java
public final class d extends c {
    public int b;
    public a c;
    OnClickListener d;
    Handler e;
    Context f;
    b g;
    com.xunlei.downloadprovider.download.a.a h;

    public d(Context context, ViewStub viewStub) {
        super(viewStub);
        this.e = new Handler();
        this.f = context;
    }

    public final void onInflate(ViewStub viewStub, View view) {
        super.onInflate(viewStub, view);
        this.g = new b(view);
        b bVar = this.g;
        g.a();
        bVar.a(g.i());
        bVar = this.g;
        g.a();
        bVar.b(g.m());
        this.g.d.setOnClickListener(new e(this));
        this.g.b.setOnClickListener(new f(this));
    }

    public final void b(int i) {
        this.b = i;
        b bVar;
        if (i == 1) {
            bVar = this.g;
            g.a();
            bVar.b(g.n());
        } else if (i == 0) {
            bVar = this.g;
            g.a();
            bVar.b(g.m());
        } else if (i == 2) {
            bVar = this.g;
            g.a();
            bVar.b(g.o());
        }
    }

    public final void a(String str) {
        if (this.g != null) {
            this.g.a(str);
        }
    }

    static /* synthetic */ void a(d dVar) {
        String str;
        if (n.f(dVar.c)) {
            str = "using";
        } else {
            str = "finish";
        }
        com.xunlei.downloadprovider.download.report.a.e(str);
    }

    static /* synthetic */ void f(d dVar) {
        BrowserUtil.a();
        Context context = dVar.f;
        Bundle bundle = new Bundle();
        bundle.putString("from_key", "fromDownloadCenter");
        BrowserUtil.a(context, "http://xlzh.xlkf.xunlei.com/?companyID=8950&configID=21&enterurl=m.help.xunlei.com&policyId=14&live800_domain=m.help.xunlei.com&live800_robot_ud_Android=Android", "\u5e2e\u52a9\u53cd\u9988", 2071, bundle);
    }

    static /* synthetic */ void g(d dVar) {
        String str;
        if (dVar.c.mIsEnteredHighSpeedTrial) {
            if (n.a(dVar.c)) {
                str = "finish";
            } else {
                str = "in";
            }
        } else if (g.a().b()) {
            str = "finish";
        } else {
            str = "before";
        }
        com.xunlei.downloadprovider.download.report.a.d(str);
    }
}
