package com.xunlei.downloadprovider.ad.splash.a.a;

import android.view.View;
import com.xunlei.downloadprovider.ad.a.a;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: BaseSSPAdInfo.java
public class e extends a<a> {
    public e(a aVar) {
        super(aVar);
    }

    public final boolean g() {
        return e() == 2;
    }

    public final String h() {
        return ((a) this.b).g;
    }

    public final String i() {
        return ((a) this.b).f;
    }

    public final String j() {
        return ((a) this.b).i;
    }

    public final float k() {
        return ((a) this.b).h;
    }

    public final int e() {
        switch (((a) this.b).c) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return 1;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return SimpleLog.LOG_LEVEL_DEBUG;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return MqttConnectOptions.MQTT_VERSION_3_1_1;
            default:
                return 1;
        }
    }

    public final String l() {
        return ((a) this.b).d;
    }

    public void onClick(BaseActivity baseActivity, View view) {
    }

    public final void a(View view) {
    }

    public String d() {
        return "xunlei";
    }

    public final String p() {
        return String.valueOf(((a) this.b).b);
    }

    public final int c_() {
        return ((a) this.b).a;
    }

    public final String m() {
        return String.valueOf(((a) this.b).b);
    }

    public final String n() {
        return String.valueOf(((a) this.b).k);
    }

    public final int o() {
        return R.styleable.AppCompatTheme_editTextStyle;
    }

    public String toString() {
        return new StringBuilder("BaseSSPAdInfo{title: ").append(h()).append("desc: ").append(i()).append("}").toString();
    }
}
