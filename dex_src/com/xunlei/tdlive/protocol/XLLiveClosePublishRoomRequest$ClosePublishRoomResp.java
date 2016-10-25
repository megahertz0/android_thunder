package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveClosePublishRoomRequest$ClosePublishRoomResp extends XLLiveRespBase {
    public Data data;

    public static class Data {
        public int current_income;
        public int current_point;
        public int current_user;
        public String end_time;
        public String start_time;
        public int status;
    }

    public XLLiveClosePublishRoomRequest$ClosePublishRoomResp() {
        this.data = new Data();
    }
}
