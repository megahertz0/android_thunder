package com.xunlei.downloadprovider.search.ui.home;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.search.ui.website.q;
import com.xunlei.downloadprovider.search.ui.website.w;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SearchWebsiteFragment.java
final class i extends Handler {
    final /* synthetic */ SearchWebsiteFragment a;

    i(SearchWebsiteFragment searchWebsiteFragment) {
        this.a = searchWebsiteFragment;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                List<w> list = (List) message.obj;
                q a = this.a.c;
                a.a = list;
                if (list != null) {
                    for (w wVar : list) {
                        if (wVar != null && !a.b.contains(Integer.valueOf(wVar.a()))) {
                            a.b.add(Integer.valueOf(wVar.a()));
                        }
                    }
                }
                a.notifyDataSetChanged();
                if (this.a.b.getAdapter() == null) {
                    this.a.b.setAdapter(this.a.c);
                }
                this.a.b.setEmptyView(this.a.a);
            case SimpleLog.LOG_LEVEL_TRACE:
                this.a.a();
            default:
                break;
        }
    }
}
