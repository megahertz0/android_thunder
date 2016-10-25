package com.xunlei.downloadprovider.frame.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;

public class XLTabView extends LinearLayout {
    public String a;
    private int b;
    private String c;
    private View d;
    private TextView e;
    private ImageView f;

    @SuppressLint({"NewApi"})
    public XLTabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public XLTabView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XLTabView(Context context) {
        super(context);
        a(context);
    }

    public String getTabTag() {
        return this.a;
    }

    public int getIconRes() {
        return this.b;
    }

    public final XLTabView a(int i) {
        this.b = i;
        this.e.setCompoundDrawablesWithIntrinsicBounds(0, i, 0, 0);
        return this;
    }

    public String getText() {
        return this.c;
    }

    public final XLTabView a(String str) {
        this.c = str;
        this.e.setText(str);
        return this;
    }

    @SuppressLint({"NewApi"})
    private void a(Context context) {
        this.d = LayoutInflater.from(context).inflate(2130968696, this);
        this.e = (TextView) this.d.findViewById(2131755636);
        this.f = (ImageView) this.d.findViewById(R.id.common_buttom_tab_point);
    }

    public void setSelection(boolean z) {
        this.d.setSelected(z);
        this.e.setSelected(z);
    }

    public void setPointVisible(int i) {
        this.f.setVisibility(i);
    }
}
