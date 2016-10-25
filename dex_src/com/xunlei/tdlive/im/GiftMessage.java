package com.xunlei.tdlive.im;

import com.xunlei.tdlive.protocol.LevelInfo;

public class GiftMessage extends BaseMessage {
    public GiftInfo giftInfo;
    public int giftid;
    public PlayerInfo playerInfo;
    public String roomid;
    public String sendtime;
    public UserInfo userInfo;

    public static class GiftInfo {
        public String content;
        public int continue_num;
        public String name;
        public String path;
    }

    public static class PlayerInfo {
        public int total_point;
        public String userid;
    }

    public static class UserInfo {
        public String avatar;
        public LevelInfo level;
        public String nickname;
        public String userid;

        public UserInfo() {
            this.level = new LevelInfo();
        }
    }

    public GiftMessage() {
        this.userInfo = new UserInfo();
        this.playerInfo = new PlayerInfo();
        this.giftInfo = new GiftInfo();
    }
}
