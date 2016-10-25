package com.xunlei.downloadprovider.c.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.HashMap;
import org.android.agoo.common.AgooConstants;

// compiled from: SyncZanRequest.java
public final class m extends l {
    public boolean f;

    protected final HashMap<String, String> e() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_APP_ID, a());
        hashMap.put("v", b());
        hashMap.put("call_id", c());
        if (this.f) {
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
}
