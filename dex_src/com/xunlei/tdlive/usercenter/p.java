package com.xunlei.tdlive.usercenter;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.a;
import com.xunlei.tdlive.LivePlayerActivity;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest.GetOtherUserInfoResp.RoomInfo;

// compiled from: UserCenterActivity.java
class p implements OnClickListener {
    final /* synthetic */ RoomInfo a;
    final /* synthetic */ UserCenterActivity b;

    p(UserCenterActivity userCenterActivity, RoomInfo roomInfo) {
        this.b = userCenterActivity;
        this.a = roomInfo;
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.a.stream_pull)) {
            LivePlayerActivity.a(this.b, this.a.roomid, String.valueOf(this.a.userid), this.a.stream_pull, UserCenterActivity.d(this.b), this.a.image, a.d, 0, 0, a.d);
        }
    }
}
