package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveQueryStreamInfoRequest.QueryStreamInfoResp.Stream;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveQueryStreamInfoRequest$QueryStreamInfoResp extends XLLiveRespBase {
    public Data data;

    public static class Data {
        public Stream stream;

        public Data() {
            this.stream = new Stream();
        }
    }

    public static class Stream {
        public int bitrate;
        public int fps;
        public int h;
        public int w;
    }

    public XLLiveQueryStreamInfoRequest$QueryStreamInfoResp() {
        this.data = new Data();
    }
}
