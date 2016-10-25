package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.xiazaibao.BuildConfig;

public class XLLiveGetOnlineConfigRequest extends XLLiveRequest {

    public static class GetOnlineConfigResp extends XLLiveRespBase {
    }

    public XLLiveGetOnlineConfigRequest() {
        super(BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME);
    }

    protected String onGetURL() {
        return "http://list.live.xunlei.com/get?type=appconf";
    }

    protected Class<?> onGetObjectClass() {
        return GetOnlineConfigResp.class;
    }
}
