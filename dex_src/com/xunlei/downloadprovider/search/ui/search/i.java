package com.xunlei.downloadprovider.search.ui.search;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.search.bean.a.a;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SearchAssociativeFragment.java
final class i implements OnItemClickListener {
    final /* synthetic */ SearchAssociativeFragment a;

    i(SearchAssociativeFragment searchAssociativeFragment) {
        this.a = searchAssociativeFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str;
        int headerViewsCount = SearchAssociativeFragment.a(this.a).getHeaderViewsCount();
        if (i < headerViewsCount) {
            str = SearchAssociativeFragment.b(this.a) + " " + SearchAssociativeFragment.c(this.a);
        } else {
            a aVar = (a) SearchAssociativeFragment.d(this.a).getItem(i - headerViewsCount);
            str = aVar.a + " " + aVar.f;
        }
        SearchAssociativeFragment.a(this.a, str, SniffStartFrom.search_think_keyin);
        String str2 = "search_think_click";
        g a = g.a("android_search", str2, str2);
        a.a("clickid", "word", MqttConnectOptions.MQTT_VERSION_3_1);
        a.a("word", str, MqttConnectOptions.MQTT_VERSION_3_1);
        f.a(a);
    }
}
