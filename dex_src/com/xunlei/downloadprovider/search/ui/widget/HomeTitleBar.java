package com.xunlei.downloadprovider.search.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.DownloadEntranceView;
import com.xunlei.downloadprovider.download.b.a;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;

public class HomeTitleBar extends FrameLayout {
    private TextView a;
    private DownloadEntranceView b;
    private a c;
    private DLCenterEntry d;

    public HomeTitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = DLCenterEntry.search;
        a(context);
    }

    public HomeTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = DLCenterEntry.search;
        a(context);
    }

    public HomeTitleBar(Context context) {
        super(context);
        this.d = DLCenterEntry.search;
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.home_title_bar, this, true);
        this.a = (TextView) findViewById(R.id.search_title_bar_title);
        CharSequence charSequence = com.xunlei.downloadprovider.model.protocol.d.a.a().c;
        if (!TextUtils.isEmpty(charSequence)) {
            this.a.setHint(charSequence);
        }
        this.b = (DownloadEntranceView) findViewById(R.id.search_titlebar_download_entrance);
        this.b.setOnClickListener(new b(this));
        a();
    }

    public void setDlCenterEntry(DLCenterEntry dLCenterEntry) {
        this.d = dLCenterEntry;
    }

    public void setHint(String str) {
        this.a.setHint(str);
    }

    public final void a() {
        if (this.c == null) {
            this.c = new a(this.b);
        }
        this.c.a();
    }

    public final void b() {
        if (this.c != null) {
            this.c.b();
        }
    }

    public void setOnTitleClickListener(OnClickListener onClickListener) {
        if (this.a != null) {
            this.a.setOnClickListener(onClickListener);
        }
    }

    public void setDownloadEntranceBackground(int i) {
        this.b.setEntranceIconResource(i);
    }

    public DownloadEntranceView getmDownloadEntranceView() {
        removeView(this.b);
        return this.b;
    }

    public void setmDownloadEntranceView(DownloadEntranceView downloadEntranceView) {
        addView(downloadEntranceView);
    }
}
