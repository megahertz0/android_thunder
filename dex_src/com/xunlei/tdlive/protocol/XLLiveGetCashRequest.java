package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetCashRequest extends XLLiveRequest {
    private String mMoney;

    public static class GetCashResp extends XLLiveRespBase {
    }

    public XLLiveGetCashRequest(String str, String str2, int i) {
        super(str, str2);
        this.mMoney = String.valueOf(i);
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=user&a=exchange&money=").append(this.mMoney).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetCashResp.class;
    }
}
