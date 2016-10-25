package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.web.base.KandanListActivity;
import com.xunlei.downloadprovider.web.base.LongVideoDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: HomeChoicenessFragment.java
final class ad implements OnItemClickListener {
    final /* synthetic */ n a;
    final /* synthetic */ HomeChoicenessFragment b;

    ad(HomeChoicenessFragment homeChoicenessFragment, n nVar) {
        this.b = homeChoicenessFragment;
        this.a = nVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = ((ListView) HomeChoicenessFragment.g(this.b).getRefreshableView()).getHeaderViewsCount();
        n nVar = this.a;
        int i2 = i - headerViewsCount;
        a aVar = (a) nVar.getItem(i2);
        if (aVar != null && view != null && (view instanceof d) && !((d) view).a(i2, aVar)) {
            if (aVar.c()) {
                nVar.a.a = false;
                ShortMovieDetailActivity.a(nVar.e, aVar.b, aVar.j, From.HOME_PAGE, aVar.d, aVar.e, aVar.k, aVar.r, aVar.v, aVar.f, aVar.o, -1);
                ChoicenessReporter.a(aVar.d, aVar.b, BuildConfig.VERSION_NAME, aVar.f());
            } else if ("subject_list".equals(aVar.c)) {
                if (aVar.b == 3) {
                    KandanListActivity.a(nVar.e, KandanListActivity.From.HOMEPAGE3, aVar.d, aVar.k);
                    ChoicenessReporter.a(aVar.d, aVar.b, SetKey.TITLE, aVar.f());
                } else if (aVar.b == 4) {
                    KandanListActivity.a(nVar.e, KandanListActivity.From.HOMEPAGE1, aVar.d, aVar.k);
                    ChoicenessReporter.a(aVar.d, aVar.b, SetKey.TITLE, aVar.f());
                }
            } else if ("media".equals(aVar.c)) {
                LongVideoDetailActivity.a(nVar.e, "home_collect", aVar.r, aVar.k);
                ChoicenessReporter.a(aVar.d, aVar.b, BuildConfig.VERSION_NAME, aVar.f());
            } else if ("promotion".equals(aVar.c)) {
                WebViewNormalActivity.a(nVar.e, BuildConfig.VERSION_NAME, aVar.r, aVar.k);
                ChoicenessReporter.a(aVar.d, aVar.b, BuildConfig.VERSION_NAME, aVar.f());
            } else {
                WebViewNormalActivity.a(nVar.e, BuildConfig.VERSION_NAME, aVar.r, aVar.k);
            }
        }
    }
}
