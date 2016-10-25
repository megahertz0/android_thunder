package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.web.base.KandanListActivity;
import com.xunlei.downloadprovider.web.base.KandanListActivity.From;

// compiled from: ChoicenessShortVideoItemViewPoster.java
final class g implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ ChoicenessShortVideoItemViewPoster b;

    g(ChoicenessShortVideoItemViewPoster choicenessShortVideoItemViewPoster, a aVar) {
        this.b = choicenessShortVideoItemViewPoster;
        this.a = aVar;
    }

    public final void onClick(View view) {
        KandanListActivity.a(this.b.getContext(), From.HOMEPAGE1, this.a.d, this.a.k);
        ChoicenessReporter.a(this.a.d, this.a.b, "pic", this.a.f());
    }
}
