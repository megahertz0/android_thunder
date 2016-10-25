package com.xunlei.downloadprovider.download.taskDetail;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: DownloadCenterDetailFragment.java
final class n implements AnimationListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    n(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onAnimationStart(Animation animation) {
        DownloadCenterDetailFragment.a(this.a, DownloadCenterDetailFragment.b);
        DownloadCenterDetailFragment.o(this.a).setClickable(false);
        DownloadCenterDetailFragment.b(this.a).setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    public final void onAnimationEnd(Animation animation) {
        DownloadCenterDetailFragment.o(this.a).setAnimation(null);
        this.a.getView().setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        DownloadCenterDetailFragment.a(this.a, DownloadCenterDetailFragment.b);
        DownloadCenterDetailFragment.o(this.a).setClickable(true);
        if (DownloadCenterDetailFragment.a(this.a) != null) {
            DownloadCenterDetailFragment.a(this.a).f();
            DownloadCenterDetailFragment.a(this.a).h();
        }
        DownloadCenterDetailFragment.p(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
