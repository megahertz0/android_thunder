package com.xunlei.downloadprovider.web.sniff;

import android.view.View;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

private class SnifferResultsFragment$k extends SnifferResultsFragment$c {
    final /* synthetic */ SnifferResultsFragment b;

    private SnifferResultsFragment$k(SnifferResultsFragment snifferResultsFragment) {
        this.b = snifferResultsFragment;
        super((byte) 0);
    }

    final void a() {
        new StringBuilder(" performUIChange ").append(getClass().getSimpleName());
        View view = this.b.getView();
        if (view != null) {
            view.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        }
        super.b();
        SnifferResultsFragment.u(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.d(this.b).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        SnifferResultsFragment.u(this.b).setAdapter(null);
        SnifferResultsFragment.d(this.b).setAdapter(null);
        if (SnifferResultsFragment.a(this.b) != null) {
            SnifferResultsFragment.a(this.b).a();
        }
        if (SnifferResultsFragment.I(this.b) != null) {
            SnifferResultsFragment.I(this.b).a();
        }
        if (SnifferResultsFragment.b(this.b) != null) {
            SnifferResultsFragment.b(this.b).a(true);
            SnifferResultsFragment.b(this.b).b(true);
            SnifferResultsFragment.b(this.b).d();
        }
    }
}
