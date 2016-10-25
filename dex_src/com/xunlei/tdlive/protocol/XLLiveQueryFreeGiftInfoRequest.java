package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveQueryFreeGiftInfoRequest.FreeGiftInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveQueryFreeGiftInfoRequest extends XLLiveRequest {

    public static final class FreeGiftInfo {
        public int giftnum;
        public int remaintime;
    }

    public static final class FreeGiftInfoResp extends XLLiveRespBase {
        public FreeGiftInfo data;
    }

    public XLLiveQueryFreeGiftInfoRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=gift&a=getdailyfreegift";
    }

    protected Class<?> onGetObjectClass() {
        return FreeGiftInfoResp.class;
    }
}
