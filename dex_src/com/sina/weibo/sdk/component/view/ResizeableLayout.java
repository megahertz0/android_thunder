package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ResizeableLayout extends RelativeLayout {
    private int mHeight;
    private SizeChangeListener mSizeChangeListener;
    private int mWidth;

    public static interface SizeChangeListener {
        void onSizeChanged(int i, int i2, int i3, int i4);
    }

    public ResizeableLayout(Context context) {
        super(context);
        this.mHeight = 0;
        this.mSizeChangeListener = null;
        this.mWidth = 0;
    }

    public ResizeableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHeight = 0;
        this.mSizeChangeListener = null;
        this.mWidth = 0;
    }

    public ResizeableLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHeight = 0;
        this.mSizeChangeListener = null;
        this.mWidth = 0;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mSizeChangeListener != null) {
            this.mSizeChangeListener.onSizeChanged(getWidth(), getHeight(), this.mWidth, this.mHeight);
        }
        this.mHeight = getHeight();
        this.mWidth = getWidth();
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setSizeChangeListener(SizeChangeListener sizeChangeListener) {
        this.mSizeChangeListener = sizeChangeListener;
    }
}
