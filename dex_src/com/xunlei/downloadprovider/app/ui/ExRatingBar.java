package com.xunlei.downloadprovider.app.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ExRatingBar extends LinearLayout {
    private final int a;
    private final int b;
    private int c;
    private int d;
    private int e;
    private Drawable f;
    private Drawable g;

    public ExRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 12;
        this.b = 5;
        setOrientation(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExRatingBar);
        this.c = obtainStyledAttributes.getDimensionPixelSize(0, com.xunlei.xllib.R.styleable.Toolbar_titleMargins);
        this.d = (int) obtainStyledAttributes.getDimension(1, 5.0f);
        this.e = (int) obtainStyledAttributes.getDimension(SimpleLog.LOG_LEVEL_DEBUG, 0.0f);
        this.f = obtainStyledAttributes.getDrawable(MqttConnectOptions.MQTT_VERSION_3_1);
        this.g = obtainStyledAttributes.getDrawable(MqttConnectOptions.MQTT_VERSION_3_1_1);
        obtainStyledAttributes.recycle();
        a(this.d, this.c, this.f);
        setRating(this.e);
    }

    private void a(int i, int i2, Drawable drawable) {
        removeAllViews();
        for (int i3 = 0; i3 < i; i3++) {
            View imageView = new ImageView(getContext());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
            layoutParams.rightMargin = g.a(getContext(), 1.0f);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            addView(imageView, layoutParams);
        }
    }

    public void setRating(int i) {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i2;
            if (i > childCount) {
                i2 = childCount;
            } else {
                i2 = i;
            }
            if (i2 < 0) {
                Object obj = null;
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (i3 < i2) {
                    if (childAt instanceof ImageView) {
                        ((ImageView) childAt).setImageDrawable(this.g);
                    }
                } else if (childAt instanceof ImageView) {
                    ((ImageView) childAt).setImageDrawable(this.f);
                }
            }
        }
    }
}
