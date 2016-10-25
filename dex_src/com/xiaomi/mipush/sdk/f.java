package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

final class f implements Runnable {
    final /* synthetic */ String[] a;
    final /* synthetic */ Context b;

    f(String[] strArr, Context context) {
        this.a = strArr;
        this.b = context;
    }

    public final void run() {
        try {
            for (Object obj : this.a) {
                if (!TextUtils.isEmpty(obj)) {
                    PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo(obj, MqttConnectOptions.MQTT_VERSION_3_1_1);
                    if (packageInfo != null) {
                        MiPushClient.access$100(this.b, packageInfo);
                    }
                }
            }
        } catch (Throwable th) {
            b.a(th);
        }
    }
}
