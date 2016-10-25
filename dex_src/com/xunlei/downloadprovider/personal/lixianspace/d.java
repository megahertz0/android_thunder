package com.xunlei.downloadprovider.personal.lixianspace;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.R;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LixianSpaceFragment.java
final class d implements AnimationListener {
    final /* synthetic */ LixianSpaceFragment a;

    d(LixianSpaceFragment lixianSpaceFragment) {
        this.a = lixianSpaceFragment;
    }

    public final void onAnimationStart(Animation animation) {
        LixianSpaceFragment.x(this.a).setVisibility(0);
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        Animation loadAnimation = AnimationUtils.loadAnimation(LixianSpaceFragment.y(this.a), R.anim.thunder_tip_top_out);
        loadAnimation.setStartOffset(c.x);
        LixianSpaceFragment.x(this.a).startAnimation(loadAnimation);
        LixianSpaceFragment.x(this.a).setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }
}
