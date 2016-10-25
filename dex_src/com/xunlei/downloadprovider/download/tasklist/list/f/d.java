package com.xunlei.downloadprovider.download.tasklist.list.f;

import android.text.TextUtils;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.xiazaibao.BuildConfig;
import org.json.JSONObject;

// compiled from: RedEnvelopeOnlineConfigure.java
public final class d {
    private static d b;
    private j a;

    static {
        b = new d();
    }

    public static d a() {
        return b;
    }

    private d() {
    }

    public final JSONObject b() {
        if (this.a == null) {
            this.a = new j(BrothersApplication.a(), "vip_renew");
        }
        try {
            Object b = this.a.b("redEnvelopeJson", BuildConfig.VERSION_NAME);
            if (!TextUtils.isEmpty(b)) {
                return new JSONObject(b);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
