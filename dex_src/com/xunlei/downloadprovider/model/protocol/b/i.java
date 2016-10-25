package com.xunlei.downloadprovider.model.protocol.b;

import com.xunlei.downloadprovider.b.c.a.a;
import com.xunlei.tdlive.im.ChatMessage;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RequestFunBox.java
final class i implements a {
    final /* synthetic */ long a;
    final /* synthetic */ String b;
    final /* synthetic */ h.a c;
    final /* synthetic */ h d;

    i(h hVar, long j, String str, h.a aVar) {
        this.d = hVar;
        this.a = j;
        this.b = str;
        this.c = aVar;
    }

    public final void a(int i, Object obj, Map<String, List<String>> map) {
        j jVar;
        new StringBuilder().append(getClass().getSimpleName()).append("---getResponseFromServer---onComplete---").append(i).append("---").append(Thread.currentThread().getId());
        if (i == 0) {
            this.d.setStatus(MqttConnectOptions.MQTT_VERSION_3_1);
            jVar = (j) obj;
            jVar.c = this.a;
            jVar.a = this.b;
            this.d.mListener.sendMessage(this.d.mListener.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, 0, -1, jVar));
            if (this.c != null) {
                this.c.a((j) obj);
            }
        } else {
            this.d.setStatus(MqttConnectOptions.MQTT_VERSION_3_1_1);
            jVar = new j();
            jVar.c = this.a;
            jVar.a = this.b;
            this.d.mListener.sendMessage(this.d.mListener.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, 0, -1, jVar));
        }
        h.b;
        new StringBuilder("relaxlog end bpbox, call back, requestBlock:").append(jVar.c).append(" requestCate:").append(jVar.a).append(" gotBlock:").append(jVar.d).append(" gotCate:").append(jVar.b);
    }
}
