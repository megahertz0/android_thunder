package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.xunlei.downloadprovider.frame.user.ah.a;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: HistoryCommentItemViewHolder.java
final class y implements OnLongClickListener {
    final /* synthetic */ v a;

    y(v vVar) {
        this.a = vVar;
    }

    public final boolean onLongClick(View view) {
        if (v.a(this.a) != null) {
            a a = v.a(this.a);
            View view2 = this.a.itemView;
            a.a(MqttConnectOptions.MQTT_VERSION_3_1, v.b(this.a));
        }
        return true;
    }
}
