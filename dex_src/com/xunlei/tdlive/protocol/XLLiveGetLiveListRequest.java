package com.xunlei.tdlive.protocol;

import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest.GetLiveListResp.Player;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest.GetLiveListResp.Seq2;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest.GetLiveListResp.User;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import java.util.List;

public class XLLiveGetLiveListRequest extends XLLiveRequest {
    int mCount;
    int mPage;
    String mSessionId;
    String mURL;
    String mUserId;

    public static class GetLiveListResp extends XLLiveRespBase {
        public List<Room> data;
        public int grayid;

        public static class Room {
            public String image;
            public long onlinenum;
            public String play_hls_url;
            public Player playerinfo;
            public String roomid;
            public Seq2 seq2;
            public String start_time;
            public int status;
            public String stream_pull;
            public int stream_server;
            public String title;
            public String userid;
            public User userinfo;

            public String getImageUrl() {
                if (TextUtils.isEmpty(this.image)) {
                    return this.userinfo != null ? this.userinfo.avatar : null;
                } else {
                    return this.image;
                }
            }
        }

        public static final class Seq2 {
            public int hot_level;
            public int is_follow;
            public int is_recommend;
            public int is_sign;
        }

        public static class User {
            public String avatar;
            public long current_gold_coin;
            public String nickname;
            public long total_gold_coin;
            public int type;
        }
    }

    public XLLiveGetLiveListRequest(boolean z, String str, String str2, String str3, int i, int i2) {
        super(str2, str3);
        this.mPage = -1;
        this.mCount = -1;
        this.mURL = a.d;
        this.mUserId = str2;
        this.mSessionId = str3;
        this.mPage = i;
        this.mCount = i2;
        this.mURL = str;
        if (this.mURL != null && this.mURL.length() > 0) {
            return;
        }
        if (z) {
            this.mURL = "http://biz.live.xunlei.com/caller?c=room&a=getlist";
        } else {
            this.mURL = "http://biz.live.xunlei.com/caller?c=room&a=getsllist";
        }
    }

    protected String onGetURL() {
        String str = this.mURL;
        if (TextUtils.isEmpty(this.mSessionId)) {
            str = str + "&hid=" + this.mUserId;
        }
        return (this.mPage < 0 || this.mCount <= 0) ? str : str + "&start=" + (this.mPage * this.mCount) + "&count=" + this.mCount;
    }

    protected Class<?> onGetObjectClass() {
        return GetLiveListResp.class;
    }
}
