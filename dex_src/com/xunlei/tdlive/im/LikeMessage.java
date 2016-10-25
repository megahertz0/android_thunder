package com.xunlei.tdlive.im;

import com.xunlei.tdlive.im.IMMessage.Builder;

public class LikeMessage extends BaseMessage {
    public String drawable;
    public String roomid;
    public String time;
    public long total_point;
    public String type;

    public static IMMessage build(String str, String str2, String str3) {
        return new Builder("like", "like", 0).putParam("userid", str).putParam("roomid", str2).putParam("drawable", str3).build();
    }
}
