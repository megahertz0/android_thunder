package com.xunlei.tdlive.protocol;

import com.xunlei.analytics.b.c;
import com.xunlei.tdlive.protocol.XLLiveGetRoomStatusRequest.RoomStatus;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetRoomStatusRequest extends XLLiveRequest {
    private String mRoomId;

    public static final class RoomStatus {
        public String play_status;
        public String status;

        public final boolean isLive() {
            return c.f.equals(this.status) || c.c.equals(this.status);
        }
    }

    public static final class RoomStatusResp extends XLLiveRespBase {
        public RoomStatus data;
    }

    public XLLiveGetRoomStatusRequest(String str, String str2, String str3) {
        super(str, str2);
        this.mRoomId = str3;
    }

    protected String onGetURL() {
        return new StringBuilder("http://biz.live.xunlei.com/caller?c=room&a=getroomstatus&roomid=").append(this.mRoomId).toString();
    }

    protected Class<?> onGetObjectClass() {
        return RoomStatusResp.class;
    }
}
