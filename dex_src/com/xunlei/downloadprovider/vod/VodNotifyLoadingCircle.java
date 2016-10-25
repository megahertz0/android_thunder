package com.xunlei.downloadprovider.vod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VodNotifyLoadingCircle extends LinearLayout {
    public static final String a;
    ImageView b;
    private TextView c;
    private RotateAnimation d;

    static {
        a = VodNotifyLoadingCircle.class.getSimpleName();
    }

    @SuppressLint({"NewApi"})
    public VodNotifyLoadingCircle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    public VodNotifyLoadingCircle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public VodNotifyLoadingCircle(Context context) {
        super(context);
        c();
    }

    private void c() {
        this.d = new RotateAnimation(0.0f, 720.0f, 1, 0.5f, 1, 0.5f);
        this.d.setInterpolator(new LinearInterpolator());
        this.d.setDuration(1000);
        this.d.setRepeatCount(-1);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.c = (TextView) findViewById(2131757161);
        this.b = (ImageView) findViewById(2131756497);
        this.c.setBackgroundColor(0);
        this.c.setHighlightColor(0);
    }

    public void setLoadingText(CharSequence charSequence) {
        this.c.setText(charSequence);
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
        this.c.setFocusable(true);
    }

    public final void a() {
        if (getVisibility() != 0) {
            startAnimation(AnimationUtils.loadAnimation(getContext(), 2131034235));
            setVisibility(0);
            this.b.startAnimation(this.d);
        }
    }

    public final boolean b() {
        return getVisibility() == 0;
    }
}
