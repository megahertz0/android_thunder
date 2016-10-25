package com.xunlei.downloadprovider.search.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.DownloadEntranceView;
import com.xunlei.downloadprovider.download.b.a;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;

public class HomeTopView extends FrameLayout {
    public DownloadEntranceView a;
    public a b;
    public Animation c;
    public Animation d;
    private DLCenterEntry e;

    public HomeTopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = DLCenterEntry.search;
        a(context);
    }

    public HomeTopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = DLCenterEntry.search;
        a(context);
    }

    public HomeTopView(Context context) {
        super(context);
        this.e = DLCenterEntry.search;
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.home_top_bar, this, true);
        com.xunlei.downloadprovider.model.protocol.d.a.a();
        this.a = (DownloadEntranceView) findViewById(R.id.search_titlebar_download_entrance);
        setDownloadEntranceBackground(com.xunlei.downloadprovidershare.R.drawable.home_top_download_entrance_icon_gray_selector);
        this.a.setEntranceNumberBackground(com.xunlei.downloadprovidershare.R.drawable.home_top_download_entrance_num_bkg_gray);
        this.a.setOnClickListener(new c(this));
        a();
    }

    public void setDlCenterEntry(DLCenterEntry dLCenterEntry) {
        this.e = dLCenterEntry;
    }

    public final void a() {
        if (this.b == null) {
            this.b = new a(this.a);
        }
        this.b.a();
    }

    public void setDownloadEntranceBackground(int i) {
        this.a.setEntranceIconResource(i);
    }
}
