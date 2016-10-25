package com.xunlei.downloadprovider.util.sniff;

import android.os.Build.VERSION;
import com.android.volley.r;
import com.android.volley.toolbox.o;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// compiled from: SniffConfigJsonObjectRequest.java
public final class a extends o {
    private static Map<String, String> a;

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put("Peer-Id", b.b());
        a.put("Product-Id", "37");
        a.put("channelId=", com.xunlei.downloadprovider.a.b.g());
        a.put("Version-Code", com.xunlei.downloadprovider.a.b.x());
        a.put("Version-Name", com.xunlei.downloadprovider.a.b.w());
        a.put("Mobile-Type", anet.channel.strategy.dispatch.a.ANDROID);
        a.put("Platform-Version", VERSION.RELEASE);
    }

    public a(String str, r.b<JSONObject> bVar, com.android.volley.r.a aVar) {
        super(0, str, null, bVar, aVar);
    }

    public final Map<String, String> getHeaders() throws com.android.volley.a {
        LoginHelper.a();
        if (LoginHelper.c()) {
            a.put("Session-Id", LoginHelper.a().k);
        } else {
            a.remove("Session-Id");
        }
        return a;
    }
}
