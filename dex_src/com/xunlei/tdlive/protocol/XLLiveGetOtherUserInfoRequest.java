package com.xunlei.tdlive.protocol;

import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest.GetOtherUserInfoResp.PlayInfo;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest.GetOtherUserInfoResp.RoomInfo;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest.GetOtherUserInfoResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetOtherUserInfoRequest extends XLLiveRequest {
    private String mOtherUserId;
    private String mRoomId;

    public static class GetOtherUserInfoResp extends XLLiveRespBase {
        public Data data;

        public static final class Data {
            public int is_deniedchat;
            public int is_follow;
            public PlayInfo player_info;
            public RoomInfo room_info;
            public long used_coin;
            public UserInfo user_info;

            public final boolean isFollow() {
                return this.is_follow == 1;
            }
        }

        public static final class PlayInfo {
            public String current_room;
            public int total_record_num;
        }

        public static final class RoomInfo {
            public int current_user;
            public String image;
            public int online_user_num;
            public String roomid;
            public int status;
            public String stream_pull;
            public String stream_push;
            public String title;
            public int userid;

            public final boolean isValid() {
                return (TextUtils.isEmpty(this.roomid) || TextUtils.isEmpty(this.stream_pull)) ? false : true;
            }

            public final boolean isLive() {
                return isValid() && (this.status == 1 || this.status == 3);
            }
        }

        public static final class UserInfo {
            public String avatar;
            public long current_coin;
            public int day_coin;
            public int fans_num;
            public int follow_num;
            public LevelInfo level;
            public String nickname;
            public int sex;
            public String sign;
            public int total_play_num;
            public long used_coin;
            public String userid;
            public String uuid;

            public final int getSend() {
                return (int) this.used_coin;
            }
        }

        public GetOtherUserInfoResp() {
            this.data = new Data();
        }
    }

    public XLLiveGetOtherUserInfoRequest(String str, String str2, String str3) {
        this(str, str2, str3, a.d);
    }

    public XLLiveGetOtherUserInfoRequest(String str, String str2, String str3, String str4) {
        super(str, str2);
        this.mOtherUserId = str3;
        this.mRoomId = str4;
    }

    protected String onGetURL() {
        String toString = new StringBuilder("http://biz.live.xunlei.com/caller?c=user&a=getUserInfo&touserid=").append(this.mOtherUserId).toString();
        return !TextUtils.isEmpty(this.mRoomId) ? toString + "&roomid=" + this.mRoomId : toString;
    }

    protected Class<?> onGetObjectClass() {
        return GetOtherUserInfoResp.class;
    }
}
