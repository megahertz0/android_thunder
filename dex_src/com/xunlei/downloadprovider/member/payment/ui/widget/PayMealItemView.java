package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import org.android.spdy.SpdyProtocol;

public class PayMealItemView extends RelativeLayout implements Checkable {
    public TextView a;
    public TextView b;
    public TextView c;
    private ImageView d;
    private boolean e;

    public PayMealItemView(Context context) {
        super(context);
    }

    public PayMealItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PayMealItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (TextView) findViewById(R.id.meal_month);
        this.b = (TextView) findViewById(R.id.meal_price);
        this.c = (TextView) findViewById(R.id.meal_save);
        this.d = (ImageView) findViewById(R.id.meal_select_flag_iv);
    }

    public void setChecked(boolean z) {
        this.e = z;
        setSelected(this.e);
        this.d.setVisibility(this.e ? 0 : SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public boolean isChecked() {
        return this.e;
    }

    public void toggle() {
        setChecked(!this.e);
    }

    public static String a(int i, float f) {
        return PayUtil.a(f / ((float) i));
    }

    public static float a(int i, float f, float f2) {
        float f3 = (((float) i) * f) - f2;
        return f3 < 0.0f ? 0.0f : f3;
    }
}
