package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.tdlive.protocol.XLLiveSendGiftRequest.SendGiftResp.GiftInfo;
import com.xunlei.tdlive.protocol.XLLiveSendGiftRequest.SendGiftResp.PlayerInfo;
import com.xunlei.tdlive.protocol.XLLiveSendGiftRequest.SendGiftResp.UserInfo;

public class XLLiveSendGiftRequest extends XLLiveRequest {
    private int mCount;
    private int mGiftId;
    private String mRoomId;
    private String mRoomUserId;

    public static class SendGiftResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public int current_coin;
            public GiftInfo giftInfo;
            public int giftid;
            public PlayerInfo playerInfo;
            public String roomid;
            public String sendtime;
            public UserInfo userInfo;

            public Data() {
                this.userInfo = new UserInfo();
                this.playerInfo = new PlayerInfo();
                this.giftInfo = new GiftInfo();
            }
        }

        public static class GiftInfo {
            public String content;
            public int continue_num;
            public int giftnum;
            public int level;
            public String name;
            public String path;
            public int remaintime;
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
    }

    public XLLiveSendGiftRequest(String str, String str2, String str3, String str4, int i, int i2) {
        super(str, str2);
        this.mRoomId = str3;
        this.mRoomUserId = str4;
        this.mGiftId = i;
        this.mCount = i2;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=gift&a=sendgift&refrom=android&giftid=").append(this.mGiftId).append("&num=").append(this.mCount).append("&touserid=").append(this.mRoomUserId).append("&roomid=").append(this.mRoomId).toString();
    }

    protected Class<?> onGetObjectClass() {
        return SendGiftResp.class;
    }
}
