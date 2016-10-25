package com.xunlei.downloadprovider.c.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;

// compiled from: ReportCommentRequest.java
public class j extends a {
    private static final String f;
    public String a;
    public String b;
    public int c;
    public long d;
    public int e;

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
        f = j.class.getSimpleName();
    }

    protected final HashMap<String, String> e() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, super.a());
        hashMap.put("v", super.b());
        hashMap.put("call_id", super.c());
        hashMap.put("tid", this.a);
        hashMap.put("source_id", this.b);
        hashMap.put("type_id", this.c);
        hashMap.put(SHubBatchQueryKeys.cid, this.d);
        hashMap.put(AgooConstants.MESSAGE_TYPE, this.e);
        return hashMap;
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
