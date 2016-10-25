package com.xunlei.tdlive.util;

import android.content.Context;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.protocol.XLLiveClosePublishRoomRequest;
import com.xunlei.tdlive.protocol.XLLiveShareNoticeRequest;
import com.xunlei.tdlive.user.f;

// compiled from: RequestHelper.java
public class h {
    public static void a(Context context, String str, String str2) {
        new XLLiveClosePublishRoomRequest(f.a(context).k(), f.a(context).l(), str, str2).send(new i());
    }

    public static void a(Context context, String str, String str2, boolean z, String str3) {
        Context context2 = context;
        new c(context2, null, new StringBuilder("\u4e3e\u62a5").append(str2).append("\u5417\uff1f").toString(), "\u53d6\u6d88", new String[]{"\u786e\u5b9a"}).b(new j(context, z, str, str3));
    }

    public static void a(Context context, String str, String str2, int i) {
        new XLLiveShareNoticeRequest(f.a(context).k(), f.a(context).l(), str, str2, i).send(new m());
    }
}
