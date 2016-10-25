package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.xiazaibao.BuildConfig;

public class XLLiveGetSplashImageRequest extends XLLiveRequest {

    public static final class GetSplashImageResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public String begin_date;
            public int display_count;
            public String end_date;
            public String id;
            public String image;
            public String md5;
        }
    }

    public XLLiveGetSplashImageRequest() {
        super(BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME);
    }

    protected String onGetURL() {
        return "http://list.live.xunlei.com/get?type=screen";
    }

    protected Class<?> onGetObjectClass() {
        return GetSplashImageResp.class;
    }
}
