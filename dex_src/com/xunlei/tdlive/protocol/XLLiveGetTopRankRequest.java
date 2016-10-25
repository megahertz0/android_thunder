package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveGetTopRankRequest.GetTopRankResp.User;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.List;

public class XLLiveGetTopRankRequest extends XLLiveRequest {
    private String mType;

    public static class GetTopRankResp extends XLLiveRespBase {
        public List<Data> data;

        public static class Data {
            public int total_point;
            public User user_info;
        }

        public static class User {
            public String avatar;
            public String nickname;
            public String sign;
            public String userid;
        }
    }

    public XLLiveGetTopRankRequest(String str, String str2, String str3) {
        super(str, str2);
        this.mType = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=pointlist&a=getpointlist&type=").append(this.mType).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetTopRankResp.class;
    }
}
