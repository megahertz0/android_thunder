package com.xunlei.tdlive.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.xunlei.tdlive.R;

public class LoadCircleAni extends ImageView {
    private Animation a;

    public LoadCircleAni(Context context) {
        super(context);
    }

    public LoadCircleAni(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LoadCircleAni(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        clearAnimation();
        try {
            this.a = AnimationUtils.loadAnimation(getContext(), R.anim.xllive_loading_circle_ani);
            startAnimation(this.a);
        } catch (Exception e) {
            this.a = null;
        }
    }

    public void setVisibility(int i) {
        if (i == 0) {
            onFinishInflate();
        } else {
            clearAnimation();
        }
        super.setVisibility(i);
    }
}
