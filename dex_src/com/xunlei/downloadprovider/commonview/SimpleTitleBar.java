package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class SimpleTitleBar extends FrameLayout {
    private ImageView a;
    private TextView b;
    private TextView c;

    public SimpleTitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public SimpleTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SimpleTitleBar(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.simple_title_bar, null);
        this.a = (ImageView) inflate.findViewById(R.id.simple_title_left);
        this.b = (TextView) inflate.findViewById(R.id.simple_title_title);
        this.c = (TextView) inflate.findViewById(R.id.simple_title_right);
        addView(inflate);
    }

    public void setBtnLeftListener(OnClickListener onClickListener) {
        this.a.setOnClickListener(onClickListener);
    }

    public void setTitleText(String str) {
        this.b.setText(str);
    }

    public void setTitleText(int i) {
        this.b.setText(i);
    }

    public void setBtnRightListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void setBtnRightText(String str) {
        this.c.setText(str);
    }

    public void setBtnRightText(int i) {
        this.c.setText(i);
    }

    public void setBtnRightVis(int i) {
        this.c.setVisibility(i);
    }

    public void setBtnRightTextSize(int i) {
        this.c.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, (float) i);
    }

    public void setBtnRightBg(int i) {
        this.c.setBackgroundResource(i);
    }
}
