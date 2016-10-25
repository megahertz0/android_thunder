package com.xunlei.downloadprovider.personal.playrecord;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.R;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: PlayRecordFragment.java
final class b implements AnimationListener {
    final /* synthetic */ PlayRecordFragment a;

    b(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void onAnimationStart(Animation animation) {
        PlayRecordFragment.p(this.a).setVisibility(0);
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        Animation loadAnimation = AnimationUtils.loadAnimation(PlayRecordFragment.q(this.a), R.anim.thunder_tip_top_out);
        loadAnimation.setStartOffset(c.x);
        PlayRecordFragment.p(this.a).startAnimation(loadAnimation);
        PlayRecordFragment.p(this.a).setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }
}
