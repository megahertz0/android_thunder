package com.xunlei.thundersniffer.sniff.sniffer.internal;

import com.android.volley.l;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.f;
import com.android.volley.w;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.thundersniffer.context.ClientInfo;
import com.xunlei.thundersniffer.context.ThunderSnifferContext;
import com.xunlei.thundersniffer.context.volley.Request.JsonObjectProtocolRequest;
import com.xunlei.xiazaibao.BuildConfig;
import org.json.JSONObject;

public class SnifferJsonObjectRequest extends JsonObjectProtocolRequest {
    public SnifferJsonObjectRequest(String str, b<JSONObject> bVar, a aVar) {
        super(str, bVar, aVar);
        setClientCookies();
        setShouldCache(false);
    }

    public SnifferJsonObjectRequest(int i, String str, b<JSONObject> bVar, a aVar) {
        super(i, str, (b) bVar, aVar);
        setClientCookies();
        setShouldCache(false);
    }

    public SnifferJsonObjectRequest(int i, String str, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(i, str, jSONObject, bVar, aVar);
        setClientCookies();
        setShouldCache(false);
    }

    public SnifferJsonObjectRequest(String str, JSONObject jSONObject, b<JSONObject> bVar, a aVar) {
        super(str, jSONObject, (b) bVar, aVar);
        setClientCookies();
        setShouldCache(false);
    }

    protected void setClientCookies() {
        ClientInfo clientInfo = ThunderSnifferContext.getClientInfo();
        if (clientInfo != null && getCookie() != null) {
            getCookie().put("version", clientInfo.version != null ? clientInfo.version : BuildConfig.VERSION_NAME);
            getCookie().put("versionCode", Integer.toString(clientInfo.versionCode));
            getCookie().put("productId", clientInfo.productId != null ? clientInfo.productId : BuildConfig.VERSION_NAME);
            getCookie().put("channelId", clientInfo.channelId != null ? clientInfo.channelId : BuildConfig.VERSION_NAME);
        }
    }

    protected r<JSONObject> parseNetworkResponse(l lVar) {
        try {
            return r.a(new JSONObject(new String(lVar.b, CharsetConvert.UTF_8)), f.a(lVar));
        } catch (Throwable e) {
            e.printStackTrace();
            return r.a(new w(new StringBuilder("Bad response body: ").append(e.getMessage()).toString(), e));
        }
    }
}
