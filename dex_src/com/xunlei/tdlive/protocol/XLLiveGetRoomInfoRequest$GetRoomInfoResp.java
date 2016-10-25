package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest.GetRoomInfoResp.Player;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest.GetRoomInfoResp.RoomInfo;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest.GetRoomInfoResp.RoomUser;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest.GetRoomInfoResp.User;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.List;

public class XLLiveGetRoomInfoRequest$GetRoomInfoResp extends XLLiveRespBase {
    public Data data;
    public String date;

    public static class Data {
        public RoomInfo roominfo;
        public List<RoomUser> roomuser;

        public Data() {
            this.roominfo = new RoomInfo();
        }
    }

    public static class Player {
        public long fans_num;
        public long follow_num;
        public long like_num;
        public long total_gold_coin;
        public long total_play_num;
        public long total_point;
    }

    public static class RoomInfo {
        public long current_point;
        public long current_user;
        public String end_time;
        public String image;
        public long onlinenum;
        public Player playerinfo;
        public String roomid;
        public String start_time;
        public int status;
        public String stream_pull;
        public int stream_server;
        public String title;
        public long total_income;
        public long total_point;
        public String userid;
        public User userinfo;

        public RoomInfo() {
            this.playerinfo = new Player();
            this.userinfo = new User();
        }
    }

    public static class RoomUser {
        public String avatar;
        public long current_gold_coin;
        public int day_coin;
        public LevelInfo level;
        public String nickname;
        public String sign;
        public long total_gold_coin;
        public int type;
        public String userid;
    }

    public static class User {
        public String avatar;
        public long current_gold_coin;
        public LevelInfo level;
        public String nickname;
        public String sign;
        public long total_gold_coin;
        public int type;
    }

    public XLLiveGetRoomInfoRequest$GetRoomInfoResp() {
        this.data = new Data();
    }
}
