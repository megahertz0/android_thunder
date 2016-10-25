package com.xunlei.downloadprovider.vod;

import com.xunlei.downloadprovider.model.protocol.report.ReportContants.Vod.VodReportPlayState;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.b.a;
import com.xunlei.downloadprovider.vod.protocol.h;

// compiled from: VodPlayerActivity.java
final class ai implements a {
    final /* synthetic */ VodPlayerActivity a;

    ai(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void a(int i, String str, com.xunlei.downloadprovider.vod.protocol.a aVar) {
        VodPlayerActivity.access$500();
        new StringBuilder("onObtainDownloadVodInfo - result: ").append(i).append(" DownloadVodInfo:").append(aVar);
        if (i != 0 || aVar == null) {
            VodPlayerActivity.access$2202(this.a, null);
            str = this.a.getResources().getString(2131233135);
        } else if (VodPlayerActivity.access$700(this.a) != null) {
            h a = VodPlayerActivity.access$700(this.a).a();
            ap access$700 = VodPlayerActivity.access$700(this.a);
            access$700.k.clear();
            access$700.a = 0;
            h hVar = new h();
            hVar.d = a.d;
            hVar.f = a.f;
            hVar.e = a.e;
            hVar.c = a.c;
            hVar.a = a.a;
            hVar.o = 1;
            hVar.x = VodVideoFormat.flv;
            hVar.p = aVar.b;
            hVar.v = 2;
            VodPlayerActivity.access$700(this.a).a(hVar);
            VodPlayerActivity.access$1902(this.a, new a(aVar.b, aVar.c, aVar.d, aVar.e, VodPlayerActivity.access$2000(this.a)));
            VodPlayerActivity.access$2100(this.a);
            VodPlayerActivity.access$2202(this.a, aVar.b);
            VodPlayerActivity.access$2300(this.a);
            VodPlayerActivity.access$1200(this.a).Open(VodPlayerActivity.access$2200(this.a));
        } else {
            return;
        }
        if (str != null) {
            VodPlayerActivity.access$2400(this.a, VodReportPlayState.failed, "vodurl_err");
            VodPlayerActivity.access$2500(this.a, str);
        }
    }
}
