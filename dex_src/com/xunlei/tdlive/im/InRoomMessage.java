package com.xunlei.tdlive.im;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.analytics.c.e;
import com.xunlei.tdlive.im.IMMessage.Builder;
import com.xunlei.tdlive.protocol.LevelInfo;

public class InRoomMessage extends BaseMessage {
    public long current_gold_coin;
    public String id;
    public LevelInfo level;
    public String nickname;
    public String online_roomid;
    public int online_stat;
    public String register_time;
    public String roomid;
    public int status;
    public String time;
    public long total_gold_coin;
    public String type;

    public static IMMessage build(String str, String str2, String str3, String str4) {
        return new Builder(SocializeProtocolConstants.PROTOCOL_KEY_DEFAULT, "inroom").putParam("userid", str).putParam("roomid", str2).putParam("platform", str3).putParam(e.a, str4).build();
    }
}
