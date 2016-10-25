package com.xunlei.tdlive.play.view;

import android.view.View;
import android.view.View.OnClickListener;
import cn.nodemedia.LivePublisher;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: PublishButtonBar.java
class ab implements OnClickListener {
    final /* synthetic */ PublishButtonBar a;

    ab(PublishButtonBar publishButtonBar) {
        this.a = publishButtonBar;
    }

    public void onClick(View view) {
        switch (LivePublisher.getFlashState()) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                LivePublisher.setFlashEnable(true);
                this.a.b();
            case SimpleLog.LOG_LEVEL_TRACE:
                LivePublisher.setFlashEnable(false);
                this.a.b();
            default:
                break;
        }
    }
}
