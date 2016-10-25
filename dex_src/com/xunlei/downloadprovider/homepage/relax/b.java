package com.xunlei.downloadprovider.homepage.relax;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.relax.d.a$a;
import com.xunlei.downloadprovider.model.protocol.b.d;
import com.xunlei.downloadprovider.model.protocol.c.c;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: RelaxListFragment.java
final class b implements a$a {
    final /* synthetic */ RelaxListFragment a;

    b(RelaxListFragment relaxListFragment) {
        this.a = relaxListFragment;
    }

    public final void a(d dVar) {
        com.xunlei.downloadprovidershare.d.b().a(RelaxListFragment.a(this.a), RelaxListFragment.a(dVar), this.a);
    }

    public final boolean b(d dVar) {
        String str = BuildConfig.VERSION_NAME;
        if (dVar.g == 0) {
            str = "joy_picture";
        }
        StatReporter.reportRelaxGood(dVar.a, dVar.g);
        return RelaxListFragment.a(this.a, dVar.a, str);
    }

    public final void c(d dVar) {
        String str = BuildConfig.VERSION_NAME;
        if (dVar.g == 0) {
            str = BrothersApplication.a().getString(R.string.relax_tab_photo);
        }
        StatReporter.reportRelaxComment(dVar.a, dVar.g);
        BrowserUtil.a();
        BrothersApplication a = BrothersApplication.a();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        if (dVar != null) {
            stringBuffer.append(dVar.c + "&").append(c.a());
        }
        BrowserUtil.a(a, stringBuilder.append(stringBuffer.toString()).append("&tab=comment").toString(), str);
    }

    public final void d(d dVar) {
        if (dVar != null) {
            RelaxListFragment.a(this.a, dVar);
        }
    }
}
