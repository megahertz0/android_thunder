package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveCreatePublishRoomRequest.CreatePublishRoomResp.Player;
import com.xunlei.tdlive.protocol.XLLiveCreatePublishRoomRequest.CreatePublishRoomResp.Stream;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.tdlive.util.f.f;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;

public class XLLiveCreatePublishRoomRequest extends XLLiveRequest {
    private int mPingAvg;
    private String mTitle;

    public static class CreatePublishRoomResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public String image;
            public Player playerinfo;
            public String roomid;
            public String start_time;
            public int status;
            public Stream stream;
            public String stream_push;
            public String title;
            public long total_point;
            public String userid;

            public Data() {
                this.stream = new Stream();
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

        public static class Stream {
            public int bitrate;
            public int fps;
            public int h;
            public int w;
        }

        public CreatePublishRoomResp() {
            this.data = new Data();
        }
    }

    public XLLiveCreatePublishRoomRequest(String str, String str2, String str3, int i) {
        super(str, str2);
        this.mTitle = str3;
        this.mPingAvg = i;
    }

    protected boolean useHttpPost() {
        return true;
    }

    protected void onAddBodyParams(f fVar) {
        fVar.b(SetKey.TITLE, this.mTitle);
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=create&from=1&ping_avg=").append(this.mPingAvg).toString();
    }

    protected Class<?> onGetObjectClass() {
        return CreatePublishRoomResp.class;
    }
}
