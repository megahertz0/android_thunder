package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.xunlei.tdlive.R;

public class FullScreenLayout extends FrameLayout {
    public View mFullScreenButton;

    public FullScreenLayout(Context context) {
        super(context);
        a();
    }

    public FullScreenLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public FullScreenLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.xllive_full_screen_layout, this);
        this.mFullScreenButton = findViewById(R.id.publish_full_sreen_btn);
    }
}
