package com.xunlei.downloadprovider.c.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;

// compiled from: CommentListRequest.java
public class d extends a {
    private static final String h;
    public String a;
    public int b;
    public long c;
    public String d;
    public int e;
    public String f;
    public String g;

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
        h = d.class.getSimpleName();
    }

    protected final HashMap<String, String> e() {
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
        hashMap.put("last_id", this.c);
        hashMap.put("source_id", this.d);
        hashMap.put(AgooConstants.MESSAGE_TYPE, this.g);
        hashMap.put("page_size", this.e);
        hashMap.put("category", this.f);
        return hashMap;
    }

    public final String f() {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap e = e();
        for (String str : e.keySet()) {
            stringBuilder.append(str).append("=").append((String) e.get(str)).append("&");
        }
        stringBuilder.append("sig=").append(a(e));
        return stringBuilder.toString();
    }
}
