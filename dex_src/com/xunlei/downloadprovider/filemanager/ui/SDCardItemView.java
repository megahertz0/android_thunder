package com.xunlei.downloadprovider.filemanager.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.downloadprovider.R;

public class SDCardItemView extends LinearLayout {
    private ProgressBar a;
    private final int b;

    public SDCardItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 10000;
        a();
    }

    public SDCardItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 10000;
        a();
    }

    private void a() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.frame_filemanager_sdcard_item, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 16;
        addView(inflate, layoutParams);
        this.a = (ProgressBar) inflate.findViewById(R.id.sdcard_use_progress);
        this.a.setMax(XLYunboMassage.MSG_TASKCREATED);
    }
}
