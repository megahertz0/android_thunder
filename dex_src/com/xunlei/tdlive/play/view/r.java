package com.xunlei.tdlive.play.view;

import android.animation.ObjectAnimator;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: NormalScreenLayout.java
class r implements Runnable {
    final /* synthetic */ NormalScreenLayout a;

    r(NormalScreenLayout normalScreenLayout) {
        this.a = normalScreenLayout;
    }

    public void run() {
        this.a.mAttendButton.setClickable(true);
        this.a.mAttendButton.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        ObjectAnimator.ofInt(NormalScreenLayout.a(this.a), "width", new int[]{NormalScreenLayout.b(this.a), 0}).setDuration(1000).start();
    }
}
