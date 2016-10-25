package com.xunlei.downloadprovider.ad.splash.b;

import android.view.ViewGroup;
import com.qq.e.ads.cfg.MultiProcessFlag;
import com.qq.e.ads.nativ.NativeAD;
import com.xunlei.downloadprovider.ad.common.d;
import com.xunlei.downloadprovider.ad.splash.c.a;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SplashGDTAd.java
public final class p extends a {
    public p(int i, BaseActivity baseActivity, String str, ViewGroup viewGroup, l lVar, d dVar) {
        super(i, baseActivity, str, viewGroup, lVar, dVar);
    }

    public final void b() {
        super.b();
        MultiProcessFlag.setMultiProcess(true);
        new NativeAD(BrothersApplication.a(), "1104872693", this.b, new q(this)).loadAD(MqttConnectOptions.MQTT_VERSION_3_1);
        c();
        a.a("adv_launch_tx_request");
    }

    protected final void d(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
    }

    protected final void e(com.xunlei.downloadprovider.ad.splash.a.a aVar) {
    }

    public final void a() {
        a.a("adv_launch_tx_fail", new StringBuilder("timeout_").append(this.g.a).toString());
    }
}
