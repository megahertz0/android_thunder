package com.xunlei.tdlive;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.protocol.BannerInfo;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.q.a;

// compiled from: RechargeActivity.java
class dv implements OnClickListener {
    final /* synthetic */ BannerInfo a;
    final /* synthetic */ RechargeActivity b;

    dv(RechargeActivity rechargeActivity, BannerInfo bannerInfo) {
        this.b = rechargeActivity;
        this.a = bannerInfo;
    }

    public void onClick(View view) {
        a aVar = new a();
        aVar.a("payid", this.b.l).a("clickid", "activity_bar");
        q.a("pay_page_click", null, null, aVar.a());
        DispatcherActivity.a(this.b, this.a.type, this.a.title, this.a.url, 0);
    }
}
