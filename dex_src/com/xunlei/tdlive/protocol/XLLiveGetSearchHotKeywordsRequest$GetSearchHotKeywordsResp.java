package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.ArrayList;

public class XLLiveGetSearchHotKeywordsRequest$GetSearchHotKeywordsResp extends XLLiveRespBase {
    public Data data;

    public static class Data {
        public ArrayList<String> keys;

        public Data() {
            this.keys = new ArrayList();
        }
    }

    public XLLiveGetSearchHotKeywordsRequest$GetSearchHotKeywordsResp() {
        this.data = new Data();
    }
}
