package com.xunlei.downloadprovider.frame.user;

import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UserFeedBackUmActivity.java
final class bu implements e<ListView> {
    final /* synthetic */ UserFeedBackUmActivity a;

    bu(UserFeedBackUmActivity userFeedBackUmActivity) {
        this.a = userFeedBackUmActivity;
    }

    public final void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        UserFeedBackUmActivity.a(this.a, MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public final void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
    }
}
