package com.xunlei.downloadprovider.web.sniff;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SnifferResultsFragment.java
final class g implements OnItemClickListener {
    final /* synthetic */ SnifferResultsFragment a;

    g(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        r rVar = (r) SnifferResultsFragment.u(this.a).getAdapter();
        if (rVar != null && rVar.b() != null && rVar.b().size() - 1 >= i) {
            SnifferResultsFragment.a(this.a, MqttConnectOptions.MQTT_VERSION_3_1);
            SnifferResultsFragment.a(this.a, new SnifferResultsFragment$e((byte) 0));
            ((SnifferResultsFragment$e) SnifferResultsFragment.m(this.a)).b = (SniffingResourceGroup) rVar.b().get(i);
            SnifferResultsFragment.m(this.a).a();
            if (SnifferResultsFragment.b(this.a) != null) {
                SnifferResultsFragment.b(this.a).a(i, (SniffingResourceGroup) rVar.b().get(i));
            }
        }
    }
}
