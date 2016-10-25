package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveGetPlayerInfoRequest.GetPlayerInfoResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetPlayerInfoRequest extends XLLiveRequest {
    private String mPlayerUid;
    private String mRoomId;

    public static class GetPlayerInfoResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public long current_point;
            public long current_room_coin;
            public String server_time;
            public UserInfo userinfo;
        }

        public static class UserInfo {
            public String avatar;
            public long fans_num;
            public long follow_num;
            public String userid;
        }
    }

    public XLLiveGetPlayerInfoRequest(String str, String str2, String str3, String str4) {
        super(str, str2);
        this.mPlayerUid = str3;
        this.mRoomId = str4;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=player&a=getPlayerInfo&playerid=").append(this.mPlayerUid).append("&roomid=").append(this.mRoomId).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetPlayerInfoResp.class;
    }
}
