package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.search.bean.c;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SearchHotWebsiteItemInfo.java
public class m extends w {
    c a;
    private int c;
    private int d;
    private int e;
    private int f;

    // compiled from: SearchHotWebsiteItemInfo.java
    private class a {
        TextView a;
        TextView b;
        TextView c;
        View d;

        private a() {
        }
    }

    public m(c cVar, Context context) {
        super(context);
        this.c = 2130838363;
        this.d = 2130838364;
        this.e = 2130838365;
        this.f = 2130838366;
        this.a = cVar;
    }

    public int a() {
        return MqttConnectOptions.MQTT_VERSION_3_1;
    }

    public final void a(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            switch (Integer.valueOf(this.a.a).intValue()) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    aVar.a.setBackgroundDrawable(this.b.getResources().getDrawable(this.c));
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    aVar.a.setBackgroundDrawable(this.b.getResources().getDrawable(this.d));
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    aVar.a.setBackgroundDrawable(this.b.getResources().getDrawable(this.e));
                    break;
                default:
                    aVar.a.setBackgroundDrawable(this.b.getResources().getDrawable(this.f));
                    break;
            }
            aVar.a.setText(this.a.a);
            aVar.b.setText(this.a.b);
            aVar.c.setText(this.a.c);
            aVar.d.setOnClickListener(new n(this));
        }
    }

    public final Object a(View view) {
        a aVar = new a();
        aVar.a = (TextView) view.findViewById(R.id.tv_record_page_list_item_icon);
        aVar.b = (TextView) view.findViewById(R.id.record_page_list_item_name);
        aVar.c = (TextView) view.findViewById(R.id.record_page_list_item_url);
        aVar.d = view.findViewById(R.id.record_page_list_item_layout);
        return aVar;
    }

    public int b() {
        return R.layout.search_hot_website_item_layout;
    }
}
