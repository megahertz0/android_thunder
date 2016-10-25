package com.xunlei.downloadprovider.vod;

import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;

// compiled from: VodPlayerActivity.java
final class z implements g {
    final /* synthetic */ VodPlayerActivity a;

    z(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void a() {
        VodPlayerActivity.TAG;
        this.a.isLogout = true;
        this.a.pausePlayer(true);
        String string = this.a.getString(2131233103);
        String string2 = this.a.getString(2131233104);
        OnClickListener aaVar = new aa(this);
        if (this.a.mVodPlayerView != null) {
            this.a.mVodPlayerView.showOneButtonDialog(string, string2, aaVar, null);
        }
    }
}
