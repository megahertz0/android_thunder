package com.xunlei.tdlive.play.a;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ReplayDialogPresenter.java
class ao implements Runnable {
    final /* synthetic */ aa a;
    final /* synthetic */ aa$c b;

    ao(aa$c com_xunlei_tdlive_play_a_aa_c, aa aaVar) {
        this.b = com_xunlei_tdlive_play_a_aa_c;
        this.a = aaVar;
    }

    public void run() {
        this.b.e.clear();
        this.b.f.clear();
        this.b.g.clear();
        this.b.d.clear();
        this.b.j.c();
        this.b.h.cancel();
        this.b.h.send(this.b, this.b.l / 1000, MqttConnectOptions.KEEP_ALIVE_INTERVAL_DEFAULT);
    }
}
