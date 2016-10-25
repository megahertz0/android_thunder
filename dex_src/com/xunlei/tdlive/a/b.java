package com.xunlei.tdlive.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;

// compiled from: FollowListAdapter.java
class b implements JsonCallBack {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0 && jsonWrapper != null) {
            this.a.a(jsonWrapper.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]"));
        }
    }
}
