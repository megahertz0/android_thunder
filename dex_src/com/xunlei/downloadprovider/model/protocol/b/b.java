package com.xunlei.downloadprovider.model.protocol.b;

import android.os.Handler;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.b.c.a;
import org.android.spdy.SpdyRequest;

// compiled from: DownloadJsonData.java
final class b implements b {
    b() {
    }

    public final void a(Handler handler, String str, long j, int i, Object obj) {
        h hVar = new h(handler, obj);
        c cVar = new c(this);
        new StringBuilder().append(hVar.getClass().getSimpleName()).append("---getResponseFromServer---").append(Thread.currentThread().getId());
        StringBuilder stringBuilder = new StringBuilder("http://m.sjzhushou.com/cgi-bin/funtime?");
        stringBuilder.append("category=").append(str);
        stringBuilder.append("&block=").append("0".equals(Long.valueOf(j)) ? c.f : Long.valueOf(j));
        stringBuilder.append("&time=").append(String.valueOf(i));
        stringBuilder.append("&product_id=").append(f.a());
        stringBuilder.append("&version=").append(f.b());
        stringBuilder.append("&peer_id=").append(f.c());
        stringBuilder.append("&userid=").append(f.e());
        stringBuilder.append("&imei=").append(f.d());
        stringBuilder.append("&versioncode=").append(f.f());
        new StringBuilder("relaxlog start bpbox, url = ").append(stringBuilder.toString());
        new StringBuilder().append(hVar.getClass().getSimpleName()).append("---getResponseFromServer---url =---").append(stringBuilder.toString()).append("---").append(Thread.currentThread().getId());
        a aVar = new a(stringBuilder.toString(), SpdyRequest.GET_METHOD, null, null, null, new e(), 10000, 10000, 1);
        aVar.setGzip(true);
        aVar.setBpOnDataLoaderCompleteListener(new i(hVar, j, str, cVar));
        hVar.setBpFuture(aVar);
        h.runBox(hVar);
    }
}
