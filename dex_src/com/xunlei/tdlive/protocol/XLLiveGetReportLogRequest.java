package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetReportLogRequest extends XLLiveRequest {
    private String mRoomId;

    public static class GetReportLogResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public String appver;
            public int bitrate;
            public String cmid;
            public int down_speed;
            public int fps;
            public int h;
            public String model;
            public int net;
            public String os;
            public String osver;
            public String roomid;
            public int stream_type;
            public int up_speed;
            public int w;
        }

        public GetReportLogResp() {
            this.data = new Data();
        }
    }

    public XLLiveGetReportLogRequest(String str, String str2, String str3) {
        super(str, str2);
        this.mRoomId = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=getplayernet&roomid=").append(this.mRoomId).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetReportLogResp.class;
    }
}
