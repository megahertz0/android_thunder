package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveRsyncAccountRequest extends XLLiveRequest {
    private int mType;

    public static class RsyncAccountResp extends XLLiveRespBase {
    }

    public XLLiveRsyncAccountRequest(String str, String str2, int i) {
        super(str, str2);
        this.mType = i;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=user&a=rsyncaccount&type=").append(this.mType).toString();
    }

    protected Class<?> onGetObjectClass() {
        return RsyncAccountResp.class;
    }
}
