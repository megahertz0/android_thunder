package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

final class e implements Runnable {
    final /* synthetic */ Context a;

    e(Context context) {
        this.a = context;
    }

    public final void run() {
        try {
            List<PackageInfo> installedPackages = this.a.getPackageManager().getInstalledPackages(MqttConnectOptions.MQTT_VERSION_3_1_1);
            if (installedPackages != null) {
                for (PackageInfo packageInfo : installedPackages) {
                    MiPushClient.access$100(this.a, packageInfo);
                }
            }
        } catch (Throwable th) {
        }
    }
}
