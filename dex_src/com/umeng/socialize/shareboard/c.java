package com.umeng.socialize.shareboard;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.umeng.socialize.Config;
import com.umeng.socialize.shareboard.b.a;
import com.umeng.socialize.shareboard.b.b;
import com.umeng.socialize.utils.Log;
import com.xunlei.xllib.R;

// compiled from: UMActionBoard.java
public class c extends RelativeLayout {
    private static final int a = 150;
    private Context b;
    private b c;
    private Animation d;
    private View e;

    public c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public c(Context context) {
        super(context);
        this.b = context;
        b();
    }

    private void b() {
        int i = R.styleable.Toolbar_titleMargins;
        this.c = new b(this.b);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(Config.showShareBoardOnTop ? 10 : 12);
        this.c.setLayoutParams(layoutParams);
        this.d = new TranslateAnimation(0.0f, 0.0f, 80.0f, 0.0f);
        this.d.setDuration(150);
        this.e = new View(this.b);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        if (!Config.showShareBoardOnTop) {
            i = 10;
        }
        layoutParams2.addRule(i);
        this.e.setLayoutParams(layoutParams2);
        this.e.setBackgroundColor(Color.argb(R.styleable.AppCompatTheme_buttonBarStyle, 0, 0, 0));
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(com.xunlei.download.proguard.c.x);
        this.e.setAnimation(alphaAnimation);
        addView(this.e);
        addView(this.c);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        Log.d("onMeasure", new StringBuilder("ActionBoard, width = ").append(size).append(", height = ").append(size2).toString());
        LayoutParams layoutParams = this.c.getLayoutParams();
        layoutParams.height = this.c.e(size);
        layoutParams.width = size;
        LayoutParams layoutParams2 = this.e.getLayoutParams();
        layoutParams2.height = size2 - layoutParams.height;
        layoutParams2.width = size;
    }

    public void a(a aVar) {
        this.c.a(aVar);
        this.c.setBackgroundColor(-16777216);
    }

    public void a(OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void a() {
        this.c.startAnimation(this.d);
    }
}
