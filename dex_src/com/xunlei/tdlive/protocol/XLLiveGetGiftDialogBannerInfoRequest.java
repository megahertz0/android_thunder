package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetGiftDialogBannerInfoRequest extends XLLiveRequest {

    public static final class GetBannerResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
        }
    }

    public XLLiveGetGiftDialogBannerInfoRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=gift&a=giftbanner";
    }

    protected Class<?> onGetObjectClass() {
        return GetBannerResp.class;
    }
}
