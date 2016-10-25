package com.xunlei.tdlive.control;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class ToggleButton extends TextView {
    private CharSequence a;
    private CharSequence b;
    private int c;
    private int d;

    public ToggleButton(Context context) {
        super(context);
        this.c = -1;
        this.d = -1;
        this.c = getCurrentTextColor();
        this.d = getCurrentTextColor();
        a();
    }

    public ToggleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = -1;
        this.d = -1;
        this.c = getCurrentTextColor();
        this.d = getCurrentTextColor();
        a();
    }

    public ToggleButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = -1;
        this.d = -1;
        this.c = getCurrentTextColor();
        this.d = getCurrentTextColor();
        a();
    }

    @TargetApi(21)
    public ToggleButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.c = -1;
        this.d = -1;
        this.c = getCurrentTextColor();
        this.d = getCurrentTextColor();
        a();
    }

    public void setSelected(boolean z) {
        super.setSelected(z);
        a();
    }

    private void a() {
        boolean isSelected = isSelected();
        if (isSelected && this.a != null) {
            setText(this.a);
            setTextColor(this.c);
        } else if (!isSelected && this.b != null) {
            setText(this.b);
            setTextColor(this.d);
        }
    }

    public CharSequence getTextOn() {
        return this.a;
    }

    public void setTextOn(CharSequence charSequence) {
        this.a = charSequence;
        a();
    }

    public void setTextOnColor(int i) {
        this.c = i;
        a();
    }

    public CharSequence getTextOff() {
        return this.b;
    }

    public void setTextOff(CharSequence charSequence) {
        this.b = charSequence;
        a();
    }

    public void setTextOffColor(int i) {
        this.d = i;
        a();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.c = getCurrentTextColor();
        this.d = getCurrentTextColor();
        a();
    }
}
