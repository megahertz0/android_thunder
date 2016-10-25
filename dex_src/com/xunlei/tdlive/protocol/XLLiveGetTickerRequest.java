package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.XLLiveGetTickerRequest.GetTickerResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetTickerRequest extends XLLiveRequest {
    private String mGuestId;
    private String mSessionId;

    public static class GetTickerResp extends XLLiveRespBase {
        public Data data;

        public static class Data {
            public String host;
            public int port;
            public String roomid;
            public String ticker;
            public String userId;
            public UserInfo user_info;
            public String video_host;
        }

        public static class UserInfo {
            public String avatar;
            public int is_sign;
            public int mail_num;
            public String nickname;
            public String sign;
            public int status;

            public boolean isSigner() {
                return this.is_sign == 1;
            }

            public String toString() {
                return new StringBuilder("nickname = ").append(this.nickname).append(", avatar = ").append(this.avatar).append(", sign = ").append(this.sign).append(", status=").append(this.status).toString();
            }
        }

        public GetTickerResp() {
            this.data = new Data();
        }
    }

    public XLLiveGetTickerRequest(String str, String str2) {
        super(str, str2);
        this.mGuestId = str;
        this.mSessionId = str2;
    }

    protected String onGetURL() {
        return (this.mSessionId == null || this.mSessionId.length() <= 0) ? new StringBuilder("http://biz.live.xunlei.com/caller?c=user&a=ticker&id=").append(this.mGuestId).toString() : "http://biz.live.xunlei.com/caller?c=user&a=ticker";
    }

    protected Class<?> onGetObjectClass() {
        return GetTickerResp.class;
    }
}
