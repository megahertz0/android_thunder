package com.xunlei.downloadprovider.c.a;

import android.net.Uri;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// compiled from: SendCommentRequest.java
public class k extends a {
    private static final String h;
    public String a;
    public int b;
    public String c;
    public long d;
    public String e;
    public String f;
    public long g;

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
        h = k.class.getSimpleName();
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
            hashMap.put("type_id", this.b);
            hashMap.put("source_id", this.c);
            hashMap.put("comment", this.e);
            hashMap.put("trigger_id", new StringBuilder("comment").append((this.e + (System.currentTimeMillis() / 10000) + LoginHelper.a().j).hashCode()).toString());
            if (!TextUtils.isEmpty(this.f)) {
                hashMap.put("device", this.f);
            }
            if (this.d > 0) {
                hashMap.put(SHubBatchQueryKeys.cid, this.d);
            }
            return hashMap;
        }
        throw new IllegalStateException("You are not login !!");
    }

    public final JSONObject f() {
        Map e = e();
        String a = a(e);
        e.put("comment", Uri.encode(this.e));
        if (this.d > 0) {
            e.put("ref_uid", this.g);
            e.put(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME2, LoginHelper.a().i);
        }
        e.put("sig", a);
        return new JSONObject(e);
    }
}
