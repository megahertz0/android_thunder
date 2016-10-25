package com.xunlei.downloadprovider.vod;

import android.view.SurfaceView;
import com.umeng.a;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.vod.VodPlayerView.c;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: VodPlayerActivity.java
final class ag implements c {
    final /* synthetic */ VodPlayerActivity a;

    ag(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void a(boolean z) {
        VodPlayerActivity.access$500();
        if (z) {
            VodPlayerActivity.access$1500(this.a);
            if (VodPlayerActivity.access$1000(this.a).isAccelBtnVisible()) {
                String str = a.d;
                String str2 = a.d;
                LoginHelper.a();
                if (LoginHelper.c()) {
                    str = LoginHelper.a().j;
                    str2 = LoginHelper.a().h;
                }
                com.xunlei.downloadprovidercommon.a.c a = com.xunlei.downloadprovidercommon.a.a.a("android_player", "bxbb_vipspeedup_btn_show");
                a.a("product_type", str2);
                a.a("userid", str);
                d.a(a);
                return;
            }
            return;
        }
        VodPlayerActivity.access$1600(this.a);
    }

    public final void a() {
        VodPlayerActivity.access$1700(this.a);
    }

    public final void a(SurfaceView surfaceView) {
        if (VodPlayerActivity.access$1200(this.a) != null) {
            VodPlayerActivity.access$1200(this.a).SetView(surfaceView);
        }
    }
}
