package com.xunlei.tdlive;

import android.widget.TextView;
import com.xunlei.tdlive.base.BaseActivity.OnCheckUpdateStateChangeListener;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SettingActivity.java
class ej implements OnCheckUpdateStateChangeListener {
    final /* synthetic */ TextView a;
    final /* synthetic */ SettingActivity b;

    ej(SettingActivity settingActivity, TextView textView) {
        this.b = settingActivity;
        this.a = textView;
    }

    public void a(int i, String str) {
        if (i == 0) {
            this.b.f = SimpleLog.LOG_LEVEL_DEBUG;
            this.a.setText(R.string.discover_update_version);
        } else if (i == -1) {
            this.b.f = SimpleLog.LOG_LEVEL_DEBUG;
            this.a.setText(str);
        } else {
            this.b.f = MqttConnectOptions.MQTT_VERSION_3_1;
            this.a.setText(null);
            this.b.showToast(str, 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
        }
    }

    public void a() {
        this.b.f = 1;
        this.a.setText(R.string.check_update_running);
    }
}
