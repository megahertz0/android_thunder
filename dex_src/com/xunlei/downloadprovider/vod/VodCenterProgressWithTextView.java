package com.xunlei.downloadprovider.vod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VodCenterProgressWithTextView extends LinearLayout {
    ProgressBar a;
    int b;
    private ImageView c;
    private TextView d;
    private TextView e;

    public VodCenterProgressWithTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = null;
        this.d = null;
        this.e = null;
        this.a = null;
        this.b = 0;
    }

    public VodCenterProgressWithTextView(Context context) {
        super(context);
        this.c = null;
        this.d = null;
        this.e = null;
        this.a = null;
        this.b = 0;
    }

    @SuppressLint({"NewApi"})
    public VodCenterProgressWithTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = null;
        this.d = null;
        this.e = null;
        this.a = null;
        this.b = 0;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.c = (ImageView) findViewById(2131757151);
        this.d = (TextView) findViewById(2131757153);
        this.e = (TextView) findViewById(2131757154);
        this.a = (ProgressBar) findViewById(2131757152);
    }

    public void setProgress(int i) {
        this.a.setProgress(i);
        if (i < this.b) {
            this.c.setBackgroundResource(2130839719);
        } else {
            this.c.setBackgroundResource(2130839718);
        }
    }

    public void setText(String str) {
        this.d.setText(str);
    }

    public void setSuffixText(String str) {
        this.e.setText(str);
    }
}
