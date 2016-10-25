package com.xunlei.downloadprovider.frame.user;

import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.frame.user.a.b.a;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import org.android.spdy.SpdyProtocol;

// compiled from: UserCenterFragment.java
final class bl implements g {
    final /* synthetic */ UserCenterFragment a;

    bl(UserCenterFragment userCenterFragment) {
        this.a = userCenterFragment;
    }

    public final void a() {
        if (UserCenterFragment.r(this.a)) {
            UserCenterFragment.a(this.a, new a());
            if (this.a.isAdded()) {
                UserCenterFragment.t(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                UserCenterFragment.u(this.a).setVisibility(0);
                UserCenterFragment.v(this.a).setVisibility(0);
                UserCenterFragment.f(this.a).setBackgroundResource(R.drawable.user_center_default_avatar);
                UserCenterFragment.a = false;
                UserCenterFragment.c();
                UserCenterFragment.w(this.a);
            }
        }
    }
}
