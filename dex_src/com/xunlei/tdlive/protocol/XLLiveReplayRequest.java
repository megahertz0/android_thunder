package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveReplayRequest.ReplayResp.PlayerInfo;
import com.xunlei.tdlive.protocol.XLLiveReplayRequest.ReplayResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.ArrayList;

public class XLLiveReplayRequest extends XLLiveRequest {
    private String mRoomId;

    public static class ReplayResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public long current_user;
            public PlayerInfo playinfo;
            public UserInfo userinfo;
            public ArrayList<UserInfo> userlist;

            public Data() {
                this.playinfo = new PlayerInfo();
                this.userinfo = new UserInfo();
            }
        }

        public static class PlayerInfo {
            public long total_point;
        }

        public static class UserInfo {
            public String avatar;
            public LevelInfo level;
            public String nickname;
            public String sign;
            public String userid;
        }

        public ReplayResp() {
            this.data = new Data();
        }
    }

    public XLLiveReplayRequest(String str, String str2, String str3) {
        super(str, str2);
        this.mRoomId = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=getrecord&roomid=").append(this.mRoomId).append("&nopush=1").toString();
    }

    protected Class<?> onGetObjectClass() {
        return ReplayResp.class;
    }
}
