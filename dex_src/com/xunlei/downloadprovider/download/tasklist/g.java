package com.xunlei.downloadprovider.download.tasklist;

import android.support.v7.widget.RecyclerView.c;
import android.view.View;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.a;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TaskListPageFragment.java
final class g extends c {
    final /* synthetic */ TaskListPageFragment a;

    g(TaskListPageFragment taskListPageFragment) {
        this.a = taskListPageFragment;
    }

    public final void a() {
        super.a();
        if (this.a.isVisible() && this.a.s != null) {
            if (this.a.d == null) {
                this.a.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.a.s.setVisibility(0);
                this.a.s.setErrorType(0);
                this.a.s.a(null, null);
            } else if (this.a.d.getItemCount() == 0 && this.a.f.size() == 0) {
                this.a.s.setVisibility(0);
                this.a.s.setErrorType(0);
                this.a.s.a(null, null);
                this.a.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else if (!this.a.d.e) {
                a a;
                if (!this.a.d.f()) {
                    a = a.a();
                    if (a.k[this.a.b]) {
                        this.a.d.h();
                    }
                    a = a.a();
                    a.k[this.a.b] = false;
                } else if (this.a.d.f()) {
                    a = a.a();
                    a.k[this.a.b] = true;
                    int g = this.a.d.g();
                    a a2 = a.a();
                    int i = a2.l[this.a.b];
                    if (!(i == 0 || i == g || g == 0)) {
                        com.xunlei.downloadprovider.download.tasklist.list.a b = this.a.d;
                        View view = b.h.getView();
                        if (view != null) {
                            view.post(new com.xunlei.downloadprovider.download.tasklist.list.c(b));
                        }
                    }
                    a2 = a.a();
                    a2.l[this.a.b] = g;
                }
                this.a.c.setVisibility(0);
                this.a.s.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
            }
        }
    }
}
