package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: BasePayPageFragment.java
final class l implements OnClickListener {
    final /* synthetic */ BasePayPageFragment a;

    l(BasePayPageFragment basePayPageFragment) {
        this.a = basePayPageFragment;
    }

    public final void onClick(View view) {
        BasePayPageFragment basePayPageFragment = this.a;
        basePayPageFragment.a.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        basePayPageFragment.b.setVisibility(0);
        basePayPageFragment.c.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        basePayPageFragment.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.e();
    }
}
