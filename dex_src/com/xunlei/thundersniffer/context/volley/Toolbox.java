package com.xunlei.thundersniffer.context.volley;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.p;
import com.android.volley.s;
import com.android.volley.toolbox.a;
import com.android.volley.toolbox.d;
import com.android.volley.toolbox.e;
import com.android.volley.toolbox.g;
import com.android.volley.toolbox.h;
import java.io.File;
import java.util.concurrent.Executor;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class Toolbox {
    private Toolbox() {
    }

    public static p newRequestQueue(Context context, g gVar, int i, int i2, Executor executor) {
        s gVar2;
        p pVar;
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = packageName + MqttTopic.TOPIC_LEVEL_SEPARATOR + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        if (gVar == null) {
            if (VERSION.SDK_INT >= 9) {
                gVar = new h();
            } else {
                gVar = new e(AndroidHttpClient.newInstance(str));
            }
        }
        a aVar = new a(gVar);
        if (executor == null) {
            gVar2 = new com.android.volley.g(new Handler(Looper.getMainLooper()));
        } else {
            gVar2 = new com.android.volley.g(executor);
        }
        if (i < 0) {
            pVar = new p(new d(file), aVar, i2, gVar2);
        } else {
            pVar = new p(new d(file, i), aVar, i2, gVar2);
        }
        pVar.a();
        return pVar;
    }
}
