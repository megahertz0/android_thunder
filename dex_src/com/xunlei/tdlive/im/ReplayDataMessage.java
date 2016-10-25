package com.xunlei.tdlive.im;

import com.xunlei.tdlive.im.ReplayDataMessage.ChatAction;
import com.xunlei.tdlive.im.ReplayDataMessage.GiftAction;
import com.xunlei.tdlive.im.ReplayDataMessage.LikeAction;
import com.xunlei.tdlive.protocol.LevelInfo;
import java.util.ArrayList;

public class ReplayDataMessage extends BaseMessage {
    public ArrayList<Action> data;
    public String path;
    public int second;

    public static class Action {
        public ArrayList<ChatAction> chat;
        public ArrayList<GiftAction> gift;
        public ArrayList<LikeAction> like;
        public int second;

        public Action() {
            this.gift = new ArrayList();
            this.like = new ArrayList();
            this.chat = new ArrayList();
        }
    }

    public static class ChatAction {
        public String content;
        public int flag;
        public String nickname;
        public String userid;
    }

    public static class GiftAction {
        public String avatar;
        public String content;
        public int continue_num;
        public int gift_id;
        public LevelInfo level;
        public String nickname;
        public int num;
        public String userid;
    }

    public static class LikeAction {
        public String roomid;
        public String type;
        public String userid;
    }
}
