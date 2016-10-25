package com.xunlei.downloadprovider.web.base.core;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class WebTitleBar extends FrameLayout {
    public CustomWebView a;
    private String b;
    private ImageView c;
    private View d;
    private TextView e;
    private TextView f;
    private View g;

    public WebTitleBar(Context context) {
        super(context);
        a(context);
    }

    public WebTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public WebTitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2130969051, this, false);
        addView(inflate, new LayoutParams(-1, -2));
        this.c = (ImageView) inflate.findViewById(2131756160);
        this.c.setOnClickListener(new q(this, context));
        this.d = inflate.findViewById(2131757221);
        this.d.setOnClickListener(new r(this, context));
        this.e = (TextView) inflate.findViewById(R.id.title);
        this.f = (TextView) inflate.findViewById(R.id.right_text);
        this.g = inflate.findViewById(2131756162);
    }

    public String getTitleText() {
        return this.b;
    }

    public void setTitleText(String str) {
        this.b = str;
        this.e.setText(str);
    }

    public void setOnRightTextClickListener(OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
    }

    public void setRightText(String str) {
        this.f.setText(str);
    }

    private void a() {
        if (this.a == null) {
            throw new UnsupportedOperationException("should call bindWebView() method");
        }
    }

    public void setTitleAlpha(float f) {
        if (f == 0.0f) {
            this.c.setImageResource(2130839158);
        } else {
            this.c.setImageResource(2130839458);
        }
        int i = (int) (255.0f * f);
        setBackgroundColor(Color.argb(i, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
        this.e.setTextColor(Color.argb(i, R.styleable.AppCompatTheme_buttonBarButtonStyle, R.styleable.AppCompatTheme_buttonBarButtonStyle, R.styleable.AppCompatTheme_buttonBarButtonStyle));
        int color = getResources().getColor(2131689799);
        this.g.setBackgroundColor(Color.argb(i, Color.red(color), Color.green(color), Color.blue(color)));
    }

    public void setOnGoBackClickListener(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    static /* synthetic */ void c(WebTitleBar webTitleBar) {
        webTitleBar.a();
        if (webTitleBar.a.f()) {
            webTitleBar.d.setVisibility(0);
        } else {
            webTitleBar.d.setVisibility(XZBDevice.Wait);
        }
    }
}
