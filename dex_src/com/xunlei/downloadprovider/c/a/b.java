package com.xunlei.downloadprovider.c.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import java.util.HashMap;

// compiled from: CommentHistoryRequest.java
public class b extends a {
    private static final String c;
    public long a;
    public int b;

    public final /* bridge */ /* synthetic */ int a() {
        return super.a();
    }

    public final /* bridge */ /* synthetic */ String b() {
        return super.b();
    }

    public final /* bridge */ /* synthetic */ String c() {
        return super.c();
    }

    static {
        c = b.class.getSimpleName();
    }

    public final long d() {
        return super.d();
    }

    public final void a(long j) {
        super.a(j);
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
        hashMap.put(c.f, super.d());
        hashMap.put("last_id", this.a);
        hashMap.put("len", this.b);
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
