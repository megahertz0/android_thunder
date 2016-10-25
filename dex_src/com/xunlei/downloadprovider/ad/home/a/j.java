package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import com.qq.e.ads.nativ.NativeMediaAD;
import com.qq.e.ads.nativ.NativeMediaAD.NativeMediaADListener;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.common.d.a;
import com.xunlei.downloadprovider.ad.common.e.e;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xllib.a.b;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LoadGDTVodExecutor.java
public final class j extends a implements NativeMediaADListener, a {
    private final int e;
    private d f;

    public j(Context context, com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        super(context, aVar);
        this.e = 3;
    }

    public final void onADLoaded(List<NativeMediaADData> list) {
        if (this.f != null && !this.f.e) {
            this.f.d = true;
        } else if (this.f != null && this.f.e) {
            return;
        }
        if (com.xunlei.xllib.b.d.a(list)) {
            e();
            return;
        }
        NativeMediaADData nativeMediaADData = (NativeMediaADData) list.get(0);
        if (b.h(this.c) && nativeMediaADData.isVideoAD()) {
            nativeMediaADData.preLoadVideo();
        }
        c.a(this.c).c.a(this.d, new e("1136", nativeMediaADData));
        c.a(this.c).a();
    }

    public final void onNoAD(int i) {
        e();
        if (this.f != null && !this.f.e) {
            this.f.d = true;
        }
    }

    public final void onADStatusChanged(NativeMediaADData nativeMediaADData) {
        c.a(this.c).c.a(this.d, new e("1136", nativeMediaADData));
        c.a(this.c).a();
    }

    public final void onADError(NativeMediaADData nativeMediaADData, int i) {
        com.xunlei.downloadprovider.ad.common.a a = c.a(this.c).c.a(this.d);
        if (a != null && a.q() != null) {
            ((e.a) a.q()).a(nativeMediaADData, i);
        }
    }

    public final void onADVideoLoaded(NativeMediaADData nativeMediaADData) {
        com.xunlei.downloadprovider.ad.common.a a = c.a(this.c).c.a(this.d);
        if (a != null && a.q() != null) {
            ((e.a) a.q()).a(nativeMediaADData);
        }
    }

    public final void a() {
        e();
    }

    private void e() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public final void b() {
        super.b();
        new NativeMediaAD(BrothersApplication.a(), "1104872693", "4000116448931057", this).loadAD(MqttConnectOptions.MQTT_VERSION_3_1);
        this.f = new d(this.b);
        this.f.a(this);
        this.f.a();
    }
}
