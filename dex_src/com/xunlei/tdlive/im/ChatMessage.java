package com.xunlei.tdlive.im;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.im.IMMessage.Builder;
import com.xunlei.tdlive.protocol.LevelInfo;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;

public class ChatMessage extends BaseMessage {
    public static final int FLAG_COMMON = 0;
    public static final int FLAG_GIFT_NOTIFY = 2;
    public static final int FLAG_INROOM_NOTIFY = 3;
    public static final int FLAG_LIKE_NOTIFY = 1;
    public static final int FLAG_SYS_NOTIFY = 1000;
    public String chat_id;
    public String chat_time;
    public String color1;
    public String color2;
    public String content;
    public int flag;
    public String roomid;
    public User user;

    public static class User {
        public LevelInfo level;
        public String nickname;
        public String userid;
    }

    public ChatMessage() {
        this.flag = 0;
        this.chat_id = BuildConfig.VERSION_NAME;
        this.roomid = BuildConfig.VERSION_NAME;
        this.content = BuildConfig.VERSION_NAME;
        this.chat_time = BuildConfig.VERSION_NAME;
        this.user = new User();
    }

    public static IMMessage build(String str, String str2, String str3) {
        return build(str, str2, FLAG_COMMON, str3);
    }

    public static IMMessage build(String str, String str2, int i, String str3) {
        return new Builder(SocializeProtocolConstants.PROTOCOL_KEY_DEFAULT, "sendchat").putParam(AgooConstants.MESSAGE_FLAG, i).putParam("userid", str).putParam("roomid", str2).putParam("content", str3).build();
    }
}
