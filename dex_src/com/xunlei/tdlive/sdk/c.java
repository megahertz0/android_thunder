package com.xunlei.tdlive.sdk;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Iterator;

// compiled from: XLLiveSDK.java
class c implements JsonCallBack {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0 && jsonWrapper != null) {
            JsonWrapper object = jsonWrapper.getObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "{}");
            JsonWrapper object2 = object.getObject("rate_limit", "{}");
            JsonWrapper object3 = object.getObject("play_buffer_time", "{}");
            JsonWrapper array = object.getArray("skincare_uids", "[]");
            JsonWrapper array2 = object.getArray("share_titles", "[]");
            JsonWrapper object4 = object.getObject("app_color", "{}");
            JsonWrapper object5 = object.getObject("sl_config", "{}");
            JsonWrapper object6 = object.getObject("in_room", "{}");
            JsonWrapper object7 = object.getObject("gift_pop", "{}");
            e.r = object6.getInt("l_min", e.r);
            e.s = object6.getInt("l_vip_min", e.s);
            e.m = object2.getInt("count", e.m);
            e.k = object2.getInt("medium", e.k);
            e.l = object2.getInt("low", e.l);
            e.g = array.toString();
            e.h = array2.toString();
            e.i = object.getString("share_content", BuildConfig.VERSION_NAME);
            e.c = object3.getInt("buffer_time", e.c);
            e.b = object3.getInt("max_buffer_time", e.b);
            e.j = object.getBoolean("user_verify", e.j);
            e.p = object5.getString("live_download_url", "http://down.sandai.net/xllive/xllive_android.apk");
            e.q = object5.getString("playend_download_url", "http://down.sandai.net/xllive/xllive_android.apk");
            e.t = object5.getInt("limit_speed", e.t);
            e.v = object7.getString("new_user", e.v);
            e.w = object7.getString("new_gift", e.w);
            e.x = object7.getString("after_60s_plus", e.x);
            Iterator keys = object4.keys();
            while (keys != null && keys.hasNext()) {
                String str2 = (String) keys.next();
                array = object4.getObject(str2, "{}");
                e.o.put(str2, array.getString("former", BuildConfig.VERSION_NAME) + "=" + array.getString("latter", BuildConfig.VERSION_NAME));
            }
            e.u = object.getString("report_url", "http://biz.live.xunlei.com/caller?c=site&a=report&from=app");
        }
    }
}
