package com.xunlei.tdlive.a;

import android.view.View;
import com.xunlei.tdlive.protocol.XLLiveFollowRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.user.f.b;
import com.xunlei.tdlive.usercenter.ah;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: RankListAdapter.java
class v implements b {
    final /* synthetic */ View a;
    final /* synthetic */ s b;

    v(s sVar, View view) {
        this.b = sVar;
        this.a = view;
    }

    public void a(boolean z) {
        boolean z2 = true;
        if (z) {
            b bVar = (b) this.a.getTag();
            if (bVar != null) {
                String string = this.b.a(bVar.j).getObject("user_info", "{}").getString("userid", BuildConfig.VERSION_NAME);
                String k = f.a().k();
                String l = f.a().l();
                this.a.setEnabled(false);
                new XLLiveFollowRequest(k, l, string, !this.a.isSelected()).send(new w(this));
                if (this.a.isSelected()) {
                    z2 = false;
                }
                ah.a(SimpleLog.LOG_LEVEL_OFF, z2, string);
            }
        }
    }
}
