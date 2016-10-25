package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetBannerRequest extends XLLiveRequest {

    public static final class GetBannerResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
        }
    }

    public XLLiveGetBannerRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://list.live.xunlei.com/get?type=appbanner";
    }

    protected Class<?> onGetObjectClass() {
        return GetBannerResp.class;
    }
}
