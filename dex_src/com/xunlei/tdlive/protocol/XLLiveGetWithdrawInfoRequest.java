package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.ProtocolData.WidthDrawDetails;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetWithdrawInfoRequest extends XLLiveRequest {

    public static final class WidthdrawInfo extends XLLiveRespBase {
        public WidthDrawDetails data;
    }

    public XLLiveGetWithdrawInfoRequest(String str, String str2) {
        super(str, str2);
    }

    protected String onGetURL() {
        return "http://biz.live.xunlei.com/caller?c=user&a=exchangeinfo";
    }

    protected Class<?> onGetObjectClass() {
        return WidthdrawInfo.class;
    }
}
