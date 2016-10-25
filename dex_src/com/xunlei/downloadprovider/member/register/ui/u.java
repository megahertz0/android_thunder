package com.xunlei.downloadprovider.member.register.ui;

import com.xunlei.downloadprovider.member.register.ui.MobileSetupActivity.a;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: MobileSetupActivity.java
final class u implements Runnable {
    final /* synthetic */ a a;

    u(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        int i = MqttConnectOptions.KEEP_ALIVE_INTERVAL_DEFAULT;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            if (this.a.a) {
                i = i2;
            } else {
                this.a.b.sendEmptyMessage(i2);
                try {
                    Thread.sleep(1000);
                    i = i2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    i = i2;
                }
            }
        }
    }
}
