package com.xunlei.downloadprovider.i.a;

import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.b.c.a.a;
import com.xunlei.downloadprovider.util.aa;
import java.util.List;
import java.util.Map;

// compiled from: ReportGrayUpdateHelper.java
final class b implements a {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        new StringBuilder().append(getClass()).append("---onComplete---errCode---").append(i);
        if (i == 0) {
            aa.a(BrothersApplication.a(), "is_reported", true);
        }
    }
}
