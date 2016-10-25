package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetVerifyCodeRequest extends XLLiveRequest {
    private String mMobile;

    public static class GetVerifyCodeResp extends XLLiveRespBase {
    }

    public XLLiveGetVerifyCodeRequest(String str, String str2, String str3) {
        super(str, str2);
        this.mMobile = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=user&a=sendsms&mobile=").append(this.mMobile).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetVerifyCodeResp.class;
    }
}
