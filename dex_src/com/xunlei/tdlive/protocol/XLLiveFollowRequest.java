package com.xunlei.tdlive.protocol;

public class XLLiveFollowRequest extends XLLiveRequest {
    private String mFollowUserId;
    private boolean mIsFollow;

    public XLLiveFollowRequest(String str, String str2, String str3, boolean z) {
        super(str, str2);
        this.mFollowUserId = str3;
        this.mIsFollow = z;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=player&a=follow&playerid=").append(this.mFollowUserId).append("&type=").append(this.mIsFollow ? 1 : 0).toString();
    }

    protected Class<?> onGetObjectClass() {
        return FollowResp.class;
    }
}
