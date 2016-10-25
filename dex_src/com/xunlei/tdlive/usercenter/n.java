package com.xunlei.tdlive.usercenter;

import com.xunlei.tdlive.protocol.XLLiveFollowRequest.FollowResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: UserCenterActivity.java
class n implements ObjectCallBack {
    final /* synthetic */ UserCenterActivity a;

    n(UserCenterActivity userCenterActivity) {
        this.a = userCenterActivity;
    }

    public void onResponse(int i, String str, Object obj) {
        boolean isFollow;
        Object obj2 = null;
        if (i == -1004) {
            obj2 = 1;
        }
        if (i == 0 && (obj instanceof FollowResp)) {
            isFollow = ((FollowResp) obj).isFollow();
        }
        if (isFollow) {
            UserCenterActivity.a(this.a);
            UserCenterActivity.b(this.a);
        }
        UserCenterActivity.a(this.a, isFollow);
    }
}
