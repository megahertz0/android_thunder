package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.tdlive.protocol.XLLiveSetPublishStateRequest.SetPublishStateResp.Player;

public class XLLiveSetPublishStateRequest extends XLLiveRequest {
    public static final int STATE_PAUSE = 3;
    public static final int STATE_RESUME = 1;
    private String mRoomId;
    private int mState;

    public static class SetPublishStateResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public String image;
            public Player playerinfo;
            public String roomid;
            public String start_time;
            public int status;
            public String stream_push;
            public String title;
            public long total_point;
            public String userid;

            public Data() {
                this.playerinfo = new Player();
            }
        }

        public static class Player {
            public long funs_num;
            public long like_num;
            public long total_gold_coin;
            public long total_play_num;
            public String userid;
        }

        public SetPublishStateResp() {
            this.data = new Data();
        }
    }

    public XLLiveSetPublishStateRequest(String str, String str2, String str3, int i) {
        super(str, str2);
        this.mState = i;
        this.mRoomId = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=setstatus&roomid=").append(this.mRoomId).append("&status=").append(this.mState).toString();
    }

    protected Class<?> onGetObjectClass() {
        return SetPublishStateResp.class;
    }
}
