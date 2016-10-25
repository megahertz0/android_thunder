package com.xunlei.downloadprovider.model.protocol.report;

import com.umeng.socialize.common.SocializeConstants;

// compiled from: HubbleProxy.java
public final class a {
    public static void a(String str, com.xunlei.downloadprovider.model.protocol.report.b.a aVar) {
        new StringBuilder("reportParam --> (adEvent: ").append(str).append(", detailData: ").append(aVar.a.toString()).append(SocializeConstants.OP_CLOSE_PAREN);
        b.a("android_advertise", str, aVar);
    }
}
