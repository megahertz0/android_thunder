package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.web.base.KandanListActivity;
import com.xunlei.downloadprovider.web.base.KandanListActivity.From;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: ChoicenessImageItemView.java
final class c implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ ChoicenessImageItemView b;

    c(ChoicenessImageItemView choicenessImageItemView, a aVar) {
        this.b = choicenessImageItemView;
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a.b == 13) {
            WebViewNormalActivity.a(this.b.getContext(), BuildConfig.VERSION_NAME, this.a.r, this.a.k);
        } else if (this.a.b == 4) {
            KandanListActivity.a(this.b.getContext(), From.HOMEPAGE1, this.a.d, this.a.k);
        }
        ChoicenessReporter.a(this.a.d, this.a.b, "pic", this.a.f());
    }
}
