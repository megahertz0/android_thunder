package com.xunlei.tdlive.im;

import com.xunlei.tdlive.protocol.LevelInfo;
import java.util.ArrayList;

public class RoomUserListMessage extends BaseMessage {
    public String roomid;
    public long total_point;
    public long user_count;
    public ArrayList<User> user_lists;

    public static class User {
        public String avatar;
        public int day_coin;
        public LevelInfo level;
        public String nickname;
        public String sign;
        public String userid;
    }
}
