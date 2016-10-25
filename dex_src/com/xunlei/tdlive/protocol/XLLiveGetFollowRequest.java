package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetFollowRequest extends XLLiveRequest {
    private String mPlayerid;
    private String mRoomId;

    public static class GetFollowResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public int is_deniedchat;
            public int is_follow;
        }

        public GetFollowResp() {
            this.data = new Data();
        }
    }

    public XLLiveGetFollowRequest(String str, String str2, String str3, String str4) {
        super(str, str2);
        this.mPlayerid = str3;
        this.mRoomId = str4;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=player&a=checkfollow&playerid=").append(this.mPlayerid).append("&roomid=").append(this.mRoomId).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetFollowResp.class;
    }
}
