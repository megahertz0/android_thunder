package com.xunlei.downloadprovider.c.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.HashMap;

// compiled from: DelCommentRequest.java
public class i extends a {
    private static final String e;
    public String a;
    public String b;
    public int c;
    public long d;

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
        e = i.class.getSimpleName();
    }

    protected final HashMap<String, String> e() {
        LoginHelper.a();
        if (LoginHelper.c()) {
            HashMap<String, String> hashMap = new HashMap();
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, super.a());
            hashMap.put("v", super.b());
            hashMap.put("call_id", super.c());
            hashMap.put("session_id", new StringBuilder("40:").append(LoginHelper.a().k).toString());
            hashMap.put("userid", LoginHelper.a().j);
            hashMap.put("tid", this.a);
            hashMap.put("source_id", this.b);
            hashMap.put("type_id", this.c);
            hashMap.put(SHubBatchQueryKeys.cid, this.d);
            return hashMap;
        }
        throw new IllegalStateException("You are not login !!");
    }

    public final String f() {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap e = e();
        String a = a(e);
        for (String str : e.keySet()) {
            stringBuilder.append(str).append("=").append((String) e.get(str)).append("&");
        }
        stringBuilder.append("sig=").append(a);
        return stringBuilder.toString();
    }
}
