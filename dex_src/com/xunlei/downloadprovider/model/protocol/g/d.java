package com.xunlei.downloadprovider.model.protocol.g;

import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.downloadprovider.b.a;
import com.xunlei.downloadprovider.b.b.d.b;
import com.xunlei.downloadprovider.b.c;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UrlBox.java
public final class d implements b {
    final /* synthetic */ a a;

    public d(a aVar) {
        this.a = aVar;
    }

    public final void a(int i) {
        this.a.setStatus(MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.a.mListener != null) {
            c cVar = new c();
            cVar.b = null;
            cVar.a = this.a.getRunnerId();
            cVar.c = this.a.mUserData;
            this.a.mListener.obtainMessage(XLYunboMassage.MSG_TASKFINISHED, i, -1, cVar).sendToTarget();
            this.a.mListener = null;
        }
        a.cancel(this.a.getRunnerId());
    }
}
