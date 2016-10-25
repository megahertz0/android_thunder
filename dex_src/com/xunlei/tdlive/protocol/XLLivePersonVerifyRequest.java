package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLivePersonVerifyRequest extends XLLiveRequest {

    public static class PersonVerifyResp extends XLLiveRespBase {
        public Data data;

        public PersonVerifyResp() {
            this.data = new Data();
        }
    }

    public XLLivePersonVerifyRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=auth&a=status";
    }

    protected Class<?> onGetObjectClass() {
        return PersonVerifyResp.class;
    }
}
