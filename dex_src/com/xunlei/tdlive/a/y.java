package com.xunlei.tdlive.a;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RecordAdapter.java
class y implements AnimationListener {
    final /* synthetic */ View a;
    final /* synthetic */ x b;

    y(x xVar, View view) {
        this.b = xVar;
        this.a = view;
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }
}
