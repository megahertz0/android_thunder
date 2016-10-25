package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveGetGiftListRequest.GetGiftListResp.GiftInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.ArrayList;

public class XLLiveGetGiftListRequest extends XLLiveRequest {

    public static class GetGiftListResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public ArrayList<ArrayList<GiftInfo>> giftInfo;
            public String gift_ver;
            public String path;

            public Data() {
                this.giftInfo = new ArrayList();
            }
        }

        public static class GiftInfo {
            public String content;
            public int costnum;
            public int giftid;
            public int giftnum;
            public int level;
            public String name;
            public int remaintime;
        }

        public GetGiftListResp() {
            this.data = new Data();
        }
    }

    public XLLiveGetGiftListRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=gift&a=getgiftinfo";
    }

    protected Class<?> onGetObjectClass() {
        return GetGiftListResp.class;
    }
}
