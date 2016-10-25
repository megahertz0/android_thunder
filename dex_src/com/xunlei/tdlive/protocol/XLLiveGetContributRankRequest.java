package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveGetContributRankRequest.GetContributRankResp.User;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetContributRankRequest extends XLLiveRequest {
    private String mRoomUserId;
    private String mType;

    public static class GetContributRankResp extends XLLiveRespBase {
        public Data data;

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

        public GetContributRankResp() {
            this.data = new Data();
        }
    }

    public XLLiveGetContributRankRequest(String str, String str2, String str3, String str4) {
        super(str, str2);
        this.mType = str4;
        this.mRoomUserId = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=user&a=cointop&playeruid=").append(this.mRoomUserId).append("&type=").append(this.mType).toString();
    }

    protected Class<?> onGetObjectClass() {
        return GetContributRankResp.class;
    }
}
