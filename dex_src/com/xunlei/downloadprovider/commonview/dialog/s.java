package com.xunlei.downloadprovider.commonview.dialog;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.d.a;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: XLProgressDialog.java
final class s extends Handler {
    final /* synthetic */ r a;

    s(r rVar) {
        this.a = rVar;
    }

    public final void handleMessage(Message message) {
        if (message.what == r.i) {
            new StringBuilder("mCurrent = ").append(this.a.k).append("; mPermillage = ").append(this.a.l);
            this.a.n.setText((this.a.l / 10) + "%");
            this.a.o.setText(a.a(this.a.k) + MqttTopic.TOPIC_LEVEL_SEPARATOR + a.a(this.a.j));
            this.a.m.setProgress(this.a.l);
        }
    }
}
