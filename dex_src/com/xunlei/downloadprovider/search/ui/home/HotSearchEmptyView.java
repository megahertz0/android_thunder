package com.xunlei.downloadprovider.search.ui.home;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;

public class HotSearchEmptyView extends FrameLayout {
    private TextView a;

    public HotSearchEmptyView(Context context) {
        super(context);
        a(context);
    }

    public HotSearchEmptyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.cloud_list_empty_view, this);
        this.a = (TextView) findViewById(R.id.cloud_list_empty_title);
    }

    public void setTitle(String str) {
        if (this.a != null) {
            this.a.setText(str);
        }
    }
}
