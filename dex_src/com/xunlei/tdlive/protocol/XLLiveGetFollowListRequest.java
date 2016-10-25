package com.xunlei.tdlive.protocol;

import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.protocol.XLLiveGetFollowListRequest.RoomInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.List;

public class XLLiveGetFollowListRequest extends XLLiveRequest {
    public static String TP_FOLLOW;
    public static String TP_FUNS;
    private int mLimit;
    private int mStart;
    private String mType;
    private String mUid;

    public static final class RoomInfo {
        public String roomid;
        public String stream_pull;
    }

    public static class UserListResp extends XLLiveRespBase {
        public List<UserInfo> data;

        public static final class UserInfo {
            public String avatar;
            public int is_follow;
            public int is_playering;
            public String nickname;
            public RoomInfo room_info;
            public String sign;
            public String status;
            public int userid;

            public final boolean isFollow() {
                return this.is_follow == 1;
            }

            public final void toggleFollow() {
                int i = 1;
                if (this.is_follow == 1) {
                    i = 0;
                }
                this.is_follow = i;
            }

            public final boolean isLiving() {
                return this.is_playering == 1 && this.room_info != null;
            }

            public final String toString() {
                return this.nickname + SocializeConstants.OP_DIVIDER_MINUS + this.userid;
            }
        }
    }

    static {
        TP_FUNS = "fans";
        TP_FOLLOW = "focus";
    }

    public XLLiveGetFollowListRequest(String str, String str2, String str3, String str4, int i, int i2) {
        super(str, str2);
        this.mStart = i;
        this.mLimit = i2;
        this.mUid = str3;
        this.mType = str4;
    }

    protected String onGetURL() {
        return String.format("http://biz.live.xunlei.com/caller?c=player&a=getFollowList&playerid=%s&type=%s&start=%d&limit=%d", new Object[]{this.mUid, this.mType, Integer.valueOf(this.mStart), Integer.valueOf(this.mLimit)});
    }

    protected Class<?> onGetObjectClass() {
        return UserListResp.class;
    }
}
