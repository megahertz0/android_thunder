package com.xunlei.XLStat.d;

import android.content.Context;
import android.content.SharedPreferences;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.c;
import com.xunlei.XLStat.g;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class a {
    public int a;
    private c b;
    private long c;
    private long d;
    private String e;

    public a(String str, Context context, c cVar) {
        this.a = c(context);
        this.c = System.currentTimeMillis();
        this.b = cVar;
    }

    public void a(Context context) {
        this.d = System.currentTimeMillis();
    }

    public void b(Context context) {
        g gVar = new g();
        gVar.d = context.getClass().getName();
        gVar.f = System.currentTimeMillis();
        gVar.e = (short) ((int) ((gVar.f - this.d) / 1000));
        gVar.b = this.a;
        gVar.c = this.c;
        XLStatLog.i("wang.log.time", "time:  session:  ", new StringBuilder(" start: ").append(this.d).append("   end: ").append(gVar.f).append("  duration:  ").append(gVar.e).toString());
        gVar.g = this.e;
        gVar.h = 0;
        this.b.a(gVar, (int) MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    public static int c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HubbleSession", 0);
        int i = sharedPreferences.getInt("hubbleSessionId", 0) + 1;
        if (i < 0) {
            i = -i;
        }
        sharedPreferences.edit().putInt("hubbleSessionId", i).commit();
        return i;
    }

    public void a(String str) {
        this.e = str;
    }
}
