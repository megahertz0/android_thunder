package com.xunlei.downloadprovider.model.protocol.f;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovidershare.R;
import java.net.URLEncoder;
import org.android.spdy.SpdyRequest;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UpdateBox.java
public final class a extends com.xunlei.downloadprovider.b.a {
    private int b;

    public a(Handler handler, int i) {
        super(handler, null);
        this.b = i;
    }

    public final int a() {
        String substring = VERSION.RELEASE.substring(0, MqttConnectOptions.MQTT_VERSION_3_1);
        Context applicationContext = BrothersApplication.a.getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder("http://upgrade.m.xunlei.com/cgi-bin/upgrade");
        try {
            stringBuilder.append("?ver=").append(applicationContext.getString(R.string.version)).append("&ios=").append(substring).append("&prouct_id=").append(applicationContext.getString(R.string.product_id)).append("&channel=").append(b.g()).append("&vercode=").append(b.x()).append("&board=").append(URLEncoder.encode(b.j(), "utf-8")).append("&brand=").append(URLEncoder.encode(b.k(), "utf-8")).append("&device=").append(URLEncoder.encode(b.l(), "utf-8")).append("&display=").append(URLEncoder.encode(b.m(), "utf-8")).append("&fingerprint=").append(URLEncoder.encode(b.n(), "utf-8")).append("&hardware=").append(URLEncoder.encode(b.o(), "utf-8")).append("&manufacturer=").append(URLEncoder.encode(b.p(), "utf-8")).append("&model=").append(URLEncoder.encode(b.q(), "utf-8")).append("&prouduct=").append(URLEncoder.encode(b.r(), "utf-8")).append("&tags=").append(URLEncoder.encode(b.s(), "utf-8")).append("&peerid=").append(URLEncoder.encode(b.d(), "utf-8")).append("&checkType=").append(this.b).append("&_t=").append(System.currentTimeMillis() / 60000);
        } catch (Exception e) {
        }
        new StringBuilder("url = ").append(stringBuilder);
        com.xunlei.downloadprovider.b.c.a aVar = new com.xunlei.downloadprovider.b.c.a(stringBuilder.toString(), SpdyRequest.GET_METHOD, null, new d());
        aVar.setBpOnDataLoaderCompleteListener(new b(this));
        setBpFuture(aVar);
        return runBox(this);
    }
}
