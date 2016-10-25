package com.xunlei.downloadprovider.ad.common.d.c;

import com.xunlei.downloadprovider.ad.common.d.c;
import java.util.Locale;

// compiled from: ReportComponent.java
public abstract class a {
    public abstract String r();

    public abstract String s();

    public final void a(int i) {
        com.xunlei.downloadprovider.ad.common.d.a.a aVar = new com.xunlei.downloadprovider.ad.common.d.a.a();
        aVar.b = s();
        aVar.c = Integer.valueOf(r()).intValue();
        aVar.a = i;
        com.xunlei.downloadprovider.j.a.a().e().a(new c(aVar, new b(this), new c(this)));
    }

    public final String b(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(Locale.CHINA, "%07d", new Object[]{Integer.valueOf(Integer.parseInt(r()))}));
        stringBuilder.append(String.format(Locale.CHINA, "%04d", new Object[]{Integer.valueOf(i)}));
        long j = 0;
        try {
            j = Long.parseLong(stringBuilder.toString()) * -1;
        } catch (NumberFormatException e) {
        }
        return String.valueOf(j);
    }
}
