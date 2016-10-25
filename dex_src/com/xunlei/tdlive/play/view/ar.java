package com.xunlei.tdlive.play.view;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.play.view.ah.a;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: UserInfoWindowHelper.java
class ar implements JsonCallBack {
    final /* synthetic */ ah a;

    ar(ah ahVar) {
        this.a = ahVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0 && jsonWrapper != null) {
            JsonWrapper array = jsonWrapper.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]");
            if (array != null && array.isArray() && array.getLength() > 0) {
                JsonWrapper object = array.getObject(0);
                if (object != null) {
                    ((a) this.a.e()).r = object.getObject("user_info", "{}").getString("avatar", BuildConfig.VERSION_NAME);
                    this.a.a(false);
                }
            }
        }
    }
}
