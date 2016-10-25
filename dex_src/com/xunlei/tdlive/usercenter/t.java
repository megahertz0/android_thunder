package com.xunlei.tdlive.usercenter;

import android.net.Uri;
import android.text.TextUtils;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.protocol.XLLiveUpdateAvatarRequest.AvatarResp;
import com.xunlei.tdlive.user.f;

// compiled from: UserCenterFragment.java
class t implements ObjectCallBack {
    final /* synthetic */ q a;

    t(q qVar) {
        this.a = qVar;
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 && (obj instanceof AvatarResp)) {
            AvatarResp avatarResp = (AvatarResp) obj;
            if (!(avatarResp.data == null || TextUtils.isEmpty(avatarResp.data.avatar))) {
                f.a().a(avatarResp.data.avatar);
                if (!TextUtils.isEmpty(avatarResp.data.avatar)) {
                    q.a(this.a).setAvatar(Uri.parse(avatarResp.data.avatar));
                }
            }
        } else {
            q.a(this.a, this.a.getResources().getString(R.string.change_avatar_failed));
        }
        if (q.b(this.a) != null) {
            q.b(this.a).dismiss();
            q.a(this.a, null);
        }
    }
}
