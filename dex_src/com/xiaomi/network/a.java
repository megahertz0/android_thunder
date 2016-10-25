package com.xiaomi.network;

import com.xiaomi.common.logger.thrift.mfs.b;
import com.xiaomi.network.UploadHostStatHelper.HttpRecordCallback;
import java.util.List;
import org.json.JSONException;

class a implements HttpRecordCallback {
    final /* synthetic */ HostManager a;

    a(HostManager hostManager) {
        this.a = hostManager;
    }

    public List<b> a() {
        try {
            return this.a.generateHostStats();
        } catch (JSONException e) {
            return null;
        }
    }

    public double b() {
        Fallback fallbacksByHost = this.a.getFallbacksByHost("f3.mi-stat.gslb.mi-idc.com");
        return fallbacksByHost != null ? fallbacksByHost.g() : 0.1d;
    }
}
