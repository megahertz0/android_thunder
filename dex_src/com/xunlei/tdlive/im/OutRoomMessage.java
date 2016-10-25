package com.xunlei.tdlive.im;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.analytics.c.e;
import com.xunlei.tdlive.im.IMMessage.Builder;

public class OutRoomMessage extends BaseMessage {
    public String roomid;
    public String time;

    public static IMMessage build(String str, String str2, String str3, String str4) {
        return new Builder(SocializeProtocolConstants.PROTOCOL_KEY_DEFAULT, "outroom").putParam("userid", str).putParam("roomid", str2).putParam("platform", str3).putParam(e.a, str4).build();
    }
}
