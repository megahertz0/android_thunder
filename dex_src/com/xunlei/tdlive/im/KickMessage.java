package com.xunlei.tdlive.im;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.im.IMMessage.Builder;

public class KickMessage extends BaseMessage {
    public String time;

    public static IMMessage build(String str) {
        return new Builder(SocializeProtocolConstants.PROTOCOL_KEY_DEFAULT, "kick").putParam("userid", str).build();
    }
}
