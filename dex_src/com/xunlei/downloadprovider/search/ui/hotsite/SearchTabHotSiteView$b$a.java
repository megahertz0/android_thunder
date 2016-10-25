package com.xunlei.downloadprovider.search.ui.hotsite;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.i;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.a;
import com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.b;

class SearchTabHotSiteView$b$a implements OnClickListener {
    final /* synthetic */ b a;
    private int b;

    SearchTabHotSiteView$b$a(b bVar, int i) {
        this.a = bVar;
        this.b = i;
    }

    public final void onClick(View view) {
        new StringBuilder().append(getClass()).append("---position---").append(this.b);
        new StringBuilder().append(getClass()).append("---onClick---").append(view.getId());
        new StringBuilder().append(getClass()).append("---holder.dowmbutton.getId()---").append(this.a.a.e.getId());
        view.getId();
        this.a.a.f.getId();
        b bVar = this.a;
        int i = this.b;
        if (i.a().d() >= 100) {
            XLToast.a(BrothersApplication.a, XLToastType.XLTOAST_TYPE_ALARM, "\u6536\u85cf\u5931\u8d25\uff0c\u6570\u91cf\u5df2\u8fbe\u4e0a\u9650");
        } else if (((a) SearchTabHotSiteView.a(bVar.c).get(i)).e == 0) {
            ((a) SearchTabHotSiteView.a(bVar.c).get(i)).e = 1;
            XLToast.a(BrothersApplication.a, XLToastType.XLTOAST_TYPE_SUC, bVar.c.getResources().getString(R.string.search_tab_title_hotsite_collectsuccess));
            bVar = new com.xunlei.downloadprovider.model.b();
            bVar.b = ((a) SearchTabHotSiteView.a(bVar.c).get(i)).b;
            bVar.c = ((a) SearchTabHotSiteView.a(bVar.c).get(i)).d;
            bVar.d = 1;
            i.a().a(bVar);
        } else {
            ((a) SearchTabHotSiteView.a(bVar.c).get(i)).e = 0;
            XLToast.a(BrothersApplication.a, XLToastType.XLTOAST_TYPE_ALARM, bVar.c.getResources().getString(R.string.search_tab_title_hotsite_collectcancel));
            bVar = new com.xunlei.downloadprovider.model.b();
            bVar.c = ((a) SearchTabHotSiteView.a(bVar.c).get(i)).d;
            i.a().b(bVar.c);
        }
        bVar.notifyDataSetChanged();
        StatReporter.reportHotsiteItem("star");
    }
}
