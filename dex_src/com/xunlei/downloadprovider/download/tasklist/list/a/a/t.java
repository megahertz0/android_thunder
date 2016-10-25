package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ThunderSaleADHandler.java
public final class t extends a {
    String c;

    // compiled from: ThunderSaleADHandler.java
    private class a extends a {
        public a(m$a com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a) {
            super(com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a);
        }

        public final void a() {
            b bVar;
            com.xunlei.downloadprovider.download.tasklist.list.a.b.a a = com.xunlei.downloadprovider.download.tasklist.list.a.b.a.a();
            switch (this.b.b()) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    bVar = a.g;
                    break;
                case SimpleLog.LOG_LEVEL_TRACE:
                    bVar = a.h;
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    bVar = a.i;
                    break;
                default:
                    bVar = null;
                    break;
            }
            if (this.b != null && bVar != null && t.this.equals(h.a(this.b))) {
                com.xunlei.downloadprovider.download.tasklist.list.a.b.a.a().b(this.b.b()).put(t.this, bVar);
                this.b.a(bVar);
            } else if (bVar == null && t.this.a != null) {
                t.this.a.a();
            }
        }
    }

    public t(m$a com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a) {
        super(com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a);
        this.c = h.a(com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a);
    }

    public final void a() {
        u.a().a(new a(this.b));
    }
}
