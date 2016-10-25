package com.xunlei.tdlive.play.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.analytics.b.c;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.play.view.ConnectMicView.a;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: UserConnectMicPresenter.java
class bb implements JsonCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ av b;

    bb(av avVar, String str) {
        this.b = avVar;
        this.a = str;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0) {
            if (jsonWrapper.getObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA) != null) {
                jsonWrapper.getObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getString("voiceall_pull", BuildConfig.VERSION_NAME);
            }
            if (c.f.equals(this.a)) {
                this.b.a(a.c);
            } else {
                this.b.a(a.a);
            }
        }
    }
}
