package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SearchHistoryTitleInfo.java
public final class j extends w {
    private int a;
    private int c;
    private Handler d;

    // compiled from: SearchHistoryTitleInfo.java
    private class a {
        ImageView a;
        TextView b;
        TextView c;

        private a() {
        }
    }

    public j(Context context, Handler handler) {
        super(context);
        this.a = 2130838984;
        this.c = 2131232415;
        this.d = handler;
    }

    public final int a() {
        return MqttConnectOptions.MQTT_VERSION_3_1_1;
    }

    public final void a(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            aVar.b.setText(this.b.getString(this.c));
            aVar.a.setImageDrawable(this.b.getResources().getDrawable(this.a));
        }
    }

    public final Object a(View view) {
        a aVar = new a();
        aVar.a = (ImageView) view.findViewById(R.id.record_page_list_item_icon);
        aVar.b = (TextView) view.findViewById(R.id.search_item_title);
        aVar.c = (TextView) view.findViewById(R.id.search_scan_all);
        aVar.c.setOnClickListener(new k(this));
        return aVar;
    }

    public final int b() {
        return R.layout.search_website_history_title_layout;
    }
}
