package com.xunlei.tdlive.protocol.test;

import com.xunlei.tdlive.protocol.XLLiveGetBannerRequest;
import com.xunlei.tdlive.protocol.XLLiveGetContributRankRequest;
import com.xunlei.tdlive.protocol.XLLiveGetGiftListRequest;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetProductPayInfoListRequest;
import com.xunlei.tdlive.protocol.XLLiveGetRoomInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetTickerRequest;
import com.xunlei.tdlive.protocol.XLLiveGetTopRankRequest;
import com.xunlei.tdlive.protocol.XLLiveReplayRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest;

public class NoSessionFilter implements IRequestFilter {
    private static final Class<?>[] REQUEST_NO_NEED_SESSION;

    static {
        REQUEST_NO_NEED_SESSION = new Class[]{XLLiveGetTickerRequest.class, XLLiveGetLiveListRequest.class, XLLiveGetRoomInfoRequest.class, XLLiveReplayRequest.class, XLLiveGetGiftListRequest.class, XLLiveGetProductPayInfoListRequest.class, XLLiveGetBannerRequest.class, XLLiveGetOtherUserInfoRequest.class, XLLiveGetTopRankRequest.class, XLLiveGetContributRankRequest.class};
    }

    public boolean filter(Class<? extends XLLiveRequest> cls) {
        return false;
    }
}
