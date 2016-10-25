package com.xunlei.downloadprovider.model.protocol.g;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.downloadprovider.b.b.a.a;
import com.xunlei.downloadprovider.b.c;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: XunleiScanCodeBox.java
public final class g implements a {
    final /* synthetic */ e a;

    public g(e eVar) {
        this.a = eVar;
    }

    public final void a(int i, Object obj) {
        new StringBuilder("ACTION_GET_XUNLEI_SCAN_CODE_RESULT:").append(this.a.mListener);
        this.a.setStatus(MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.a.mListener != null) {
            c cVar = new c();
            cVar.b = obj;
            cVar.a = this.a.getRunnerId();
            cVar.c = this.a.mUserData;
            this.a.mListener.obtainMessage(XiaomiOAuthConstants.SCOPE_MI_CLOUD_GALLERY, i, -1, cVar).sendToTarget();
            this.a.mListener = null;
        }
    }
}
