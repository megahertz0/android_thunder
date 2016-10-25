package com.xunlei.downloadprovider.c.a;

import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.o;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

// compiled from: SendZanRequest.java
public class l extends a {
    private static final String f;
    public String a;
    public int b;
    public String c;
    public long d;
    public long e;

    public final /* bridge */ /* synthetic */ int a() {
        return super.a();
    }

    public final /* bridge */ /* synthetic */ String a(HashMap hashMap) {
        return super.a(hashMap);
    }

    public final /* bridge */ /* synthetic */ String b() {
        return super.b();
    }

    public final /* bridge */ /* synthetic */ String c() {
        return super.c();
    }

    static {
        f = l.class.getSimpleName();
    }

    protected HashMap<String, String> e() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, super.a());
        hashMap.put("v", super.b());
        hashMap.put("call_id", super.c());
        LoginHelper.a();
        if (LoginHelper.c()) {
            hashMap.put("session_id", new StringBuilder("40:").append(LoginHelper.a().k).toString());
            hashMap.put("userid", LoginHelper.a().j);
        }
        hashMap.put("tid", this.a);
        hashMap.put("type_id", this.b);
        hashMap.put("source_id", this.c);
        hashMap.put(AgooConstants.MESSAGE_TYPE, c.f);
        hashMap.put(SHubBatchQueryKeys.cid, this.d);
        return hashMap;
    }

    private final String f() {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap e = e();
        String a = a(e);
        LoginHelper.a();
        if (LoginHelper.c()) {
            e.put(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME2, LoginHelper.a().i);
        }
        e.put("ref_uid", this.e);
        for (String str : e.keySet()) {
            stringBuilder.append(str).append("=").append((String) e.get(str)).append("&");
        }
        stringBuilder.append("sig=").append(a);
        return stringBuilder.toString();
    }

    public final o a(b<JSONObject> bVar, a aVar) {
        return new h(0, new StringBuilder("https://comment-shoulei-ssl.xunlei.com/comment/api/comment_count?").append(f()).toString(), null, bVar, aVar);
    }
}
