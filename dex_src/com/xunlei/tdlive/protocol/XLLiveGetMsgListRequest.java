package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetMsgListRequest extends XLLiveRequest {
    int mCount;
    int mPage;

    public static class GetMsgListResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public String after_open;
            public String create_time;
            public String id;
            public String img;
            public String msg_type;
            public String text;
            public String title;
            public String url;
        }
    }

    public XLLiveGetMsgListRequest(String str, String str2) {
        super(str, str2);
        this.mPage = -1;
        this.mCount = -1;
    }

    public XLLiveGetMsgListRequest(String str, String str2, int i, int i2) {
        super(str, str2);
        this.mPage = -1;
        this.mCount = -1;
        this.mPage = i;
        this.mCount = i2;
    }

    protected String onGetURL() {
        return (this.mPage < 0 || this.mCount <= 0) ? "http://biz.live.xunlei.com/caller?c=site&a=getusermailboxlist" : new StringBuilder("http://biz.live.xunlei.com/caller?c=site&a=getusermailboxlist&start=").append(this.mPage * this.mCount).append("&count=").append(this.mCount).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetMsgListResp.class;
    }
}
