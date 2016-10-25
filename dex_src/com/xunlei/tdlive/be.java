package com.xunlei.tdlive;

import android.text.Editable;
import android.text.TextWatcher;
import com.xunlei.tdlive.util.v;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LivePlayerDialog.java
class be implements TextWatcher {
    final /* synthetic */ au a;

    be(au auVar) {
        this.a = auVar;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        int a = v.a(editable.toString(), SimpleLog.LOG_LEVEL_DEBUG, 1);
        if (editable != null && a > 60) {
            CharSequence a2 = v.a(editable.toString(), MqttConnectOptions.KEEP_ALIVE_INTERVAL_DEFAULT, SimpleLog.LOG_LEVEL_DEBUG, 1, BuildConfig.VERSION_NAME);
            editable.clear();
            editable.append(a2);
        }
    }
}
