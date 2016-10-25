package com.xunlei.downloadprovider.xl7;

import android.os.Handler;
import com.tencent.connect.common.Constants;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.b.c.e;
import java.util.HashMap;

// compiled from: ReportMyPhoneToQRHelper.java
public final class a extends com.xunlei.downloadprovider.b.a {
    Handler b;
    private Object c;

    public a(Handler handler, Object obj) {
        super(handler, obj);
        this.b = handler;
        this.c = obj;
    }

    public final int a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("Cookie", new StringBuilder("phone_peerid=").append(b.d()).append("; imei=").append(b.f()).append("; client=android; version_code=").append(b.x()).append("; version_name=").append(b.w()).append("; model=").append(b.q()).toString());
        hashMap.put("Referer", "http://device.client.xunlei.com");
        e aVar = new com.xunlei.downloadprovider.b.c.a(new StringBuilder("http://device.client.xunlei.com/device/associate?").append(str).toString(), Constants.HTTP_GET, null, null, hashMap, new b(this, (byte) 0));
        aVar.setBpOnDataLoaderCompleteListener(new b(this));
        setBpFuture(aVar);
        return runBox(this);
    }
}
