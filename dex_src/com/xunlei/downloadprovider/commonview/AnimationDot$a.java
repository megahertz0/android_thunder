package com.xunlei.downloadprovider.commonview;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

private class AnimationDot$a extends Handler {
    private WeakReference<AnimationDot> a;

    public AnimationDot$a(AnimationDot animationDot) {
        this.a = new WeakReference(animationDot);
    }

    public final void handleMessage(Message message) {
        int i = 1;
        AnimationDot animationDot = (AnimationDot) this.a.get();
        if (animationDot != null) {
            switch (message.what) {
                case 506428:
                    CharSequence a = AnimationDot.a(animationDot);
                    AnimationDot.b(animationDot);
                    if (AnimationDot.c(animationDot) > 3) {
                        AnimationDot.a(animationDot, 1);
                    } else if (AnimationDot.c(animationDot) <= 0) {
                        AnimationDot.a(animationDot, MqttConnectOptions.MQTT_VERSION_3_1);
                    }
                    while (i <= AnimationDot.c(animationDot)) {
                        a = a + ".";
                        i++;
                    }
                    animationDot.setText(a);
                    sendEmptyMessageDelayed(506428, 250);
                default:
                    break;
            }
        }
    }
}
