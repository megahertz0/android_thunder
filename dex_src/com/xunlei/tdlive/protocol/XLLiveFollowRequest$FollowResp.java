package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public final class XLLiveFollowRequest$FollowResp extends XLLiveRespBase {
    public XLLiveFollowRequest$FollowInfo data;

    public final boolean isFollow() {
        if (this.data == null) {
            return false;
        }
        return this.data.is_follow == 1 || this.result == -1004;
    }
}
