package com.xunlei.tdlive.protocol;

public class GetPayPageBannerInfoRequest extends XLLiveRequest {
    public GetPayPageBannerInfoRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=site&a=getbanner&type=pay";
    }

    protected Class<?> onGetObjectClass() {
        return RespData.class;
    }
}
