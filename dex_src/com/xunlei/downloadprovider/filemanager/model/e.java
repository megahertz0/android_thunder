package com.xunlei.downloadprovider.filemanager.model;

import android.os.Handler;
import com.xunlei.downloadprovider.filemanager.ui.g.a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: FileManagerUtil.java
final class e extends Thread {
    final /* synthetic */ a a;
    final /* synthetic */ Handler b;

    e(a aVar, Handler handler) {
        this.a = aVar;
        this.b = handler;
    }

    public final void run() {
        int i = 0;
        if (new File(this.a.b).renameTo(new File(this.a.c))) {
            String str;
            List arrayList = new ArrayList();
            String str2 = this.a.b.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) ? this.a.b : this.a.b + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            if (this.a.c.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                str = this.a.c;
            } else {
                str = this.a.c + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            }
            b.a(this.a.c, arrayList);
            List arrayList2 = new ArrayList();
            List arrayList3 = new ArrayList();
            while (i < arrayList.size()) {
                i iVar = (i) arrayList.get(i);
                String replaceFirst = iVar.g.replaceFirst(str, str2);
                new StringBuilder().append(replaceFirst).append("  ").append(iVar.g);
                arrayList2.add(replaceFirst);
                arrayList3.add(iVar.g);
                i++;
            }
            b.a(arrayList2, arrayList3);
            this.a.a = true;
            this.b.obtainMessage(b.d, this.a).sendToTarget();
            return;
        }
        this.a.a = false;
        this.b.obtainMessage(b.d, this.a).sendToTarget();
    }
}
