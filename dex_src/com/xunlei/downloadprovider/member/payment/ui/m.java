package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.member.payment.b;
import com.xunlei.downloadprovider.personal.settings.HelpActivity;

// compiled from: BasePayPageFragment.java
final class m implements OnClickListener {
    final /* synthetic */ BasePayPageFragment a;

    m(BasePayPageFragment basePayPageFragment) {
        this.a = basePayPageFragment;
    }

    public final void onClick(View view) {
        HelpActivity.a(this.a.getActivity(), "http://m.sjzhushou.com/ios_page/publish/vip_help/index.html", this.a.getResources().getString(R.string.member_service_agreement));
        b.f();
    }
}
