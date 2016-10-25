package com.xunlei.tdlive.play.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.control.StrokeTextView;
import com.xunlei.tdlive.util.r;
import com.xunlei.tdlive.util.v;

public class GiftReminder extends LinearLayout implements OnClickListener {
    private TextView a;
    private TextView b;
    private RoundImageView c;
    private RoundImageView d;
    private ImageView e;
    private StrokeTextView f;
    private r g;
    private a h;
    private int i;
    private com.xunlei.tdlive.modal.b.a j;

    public static interface a {
        void onGiftReminderViewState(GiftReminder giftReminder);
    }

    public GiftReminder(Context context) {
        super(context);
        this.i = 6;
    }

    public GiftReminder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 6;
    }

    public GiftReminder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = 6;
    }

    @TargetApi(21)
    public GiftReminder(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.i = 6;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (TextView) findViewById(R.id.name);
        this.b = (TextView) findViewById(R.id.msg);
        this.c = (RoundImageView) findViewById(R.id.head_image);
        this.d = (RoundImageView) findViewById(R.id.experience_grade_image);
        this.e = (ImageView) findViewById(R.id.gift_image);
        this.f = (StrokeTextView) findViewById(R.id.combo_num);
        this.e.setOnClickListener(this);
    }

    public void setName(String str) {
        this.a.setText(v.a(str, com.xunlei.xllib.R.styleable.Toolbar_titleMarginEnd));
    }

    public void setMsg(String str) {
        this.b.setText(str);
    }

    public void getImageView(ImageView imageView, String str) {
        getImageView(imageView, str, R.drawable.xllive_avatar_default);
    }

    public void getImageView(ImageView imageView, String str, int i) {
        if (str != null) {
            int intValue;
            int i2 = R.id.gift_image;
            Object tag = imageView.getTag(i2);
            if (tag != null) {
                intValue = ((Integer) tag).intValue();
            } else {
                intValue = -1;
            }
            int hashCode = str.hashCode();
            if (hashCode != intValue) {
                imageView.setTag(i2, Integer.valueOf(hashCode));
                com.xunlei.tdlive.util.a.a(getContext()).a(imageView, str, com.xunlei.tdlive.util.a.a(getContext(), i));
            }
        }
    }

    public void setGiftImage(String str) {
        getImageView(this.e, str, R.drawable.xllive_transparent);
    }

    public void startAnim() {
        setVisibility(0);
        startAnimation(getAniStartTranslateSet());
    }

    public void act(com.xunlei.tdlive.modal.b.a aVar, a aVar2) {
        this.j = aVar;
        this.h = aVar2;
        setDrawableCache(false);
        b();
        setName(aVar.b);
        setMsg(aVar.j);
        getImageView(this.c, aVar.c);
        setGiftImage(aVar.k);
        a(aVar.d);
        a();
        a(aVar.l);
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d.setVisibility(0);
            getImageView(this.d, str);
        }
    }

    private void setDrawableCache(boolean z) {
        setAnimationCacheEnabled(z);
        setDrawingCacheEnabled(z);
    }

    private void a() {
        if (getVisibility() != 0) {
            startAnim();
        }
    }

    private void b() {
        if (this.g == null) {
            this.g = new r(400, new j(this));
            this.g.b();
        }
    }

    private void c() {
        f();
        if (this.i < 0) {
            e();
            d();
        }
    }

    private AnimationSet getAniStartTranslateSet() {
        AnimationSet translateSet = getTranslateSet();
        translateSet.addAnimation(a(-1.0f, 0.0f));
        translateSet.addAnimation(new AlphaAnimation(0.2f, 1.0f));
        return translateSet;
    }

    private void d() {
        if (getVisibility() == 0) {
            startAnimation(getAniEndTranslateSet());
        }
    }

    private Animation getAniEndTranslateSet() {
        Animation translateSet = getTranslateSet();
        translateSet.addAnimation(a(0.0f, -1.0f));
        translateSet.addAnimation(new AlphaAnimation(1.0f, 0.2f));
        translateSet.setAnimationListener(new k(this));
        return translateSet;
    }

    private TranslateAnimation a(float f, float f2) {
        return new TranslateAnimation(1, f, 1, f2, 1, 0.0f, 1, 0.0f);
    }

    private AnimationSet getTranslateSet() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(500);
        animationSet.setFillAfter(false);
        return animationSet;
    }

    private synchronized void e() {
        this.i = 6;
    }

    private synchronized void f() {
        this.i--;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.gift_image && this.j != null) {
            Intent intent = new Intent("com.xunlei.tdlive.GIFT_CLICKED");
            intent.putExtra("giftid", this.j.h);
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        }
    }

    private void a(int i) {
        if (this.f.getVisibility() != 0) {
            this.f.setVisibility(0);
        }
        this.f.setTextSize(b(i));
        this.f.setTextColor(c(i));
        StrokeTextView strokeTextView = this.f;
        StringBuilder stringBuilder = new StringBuilder(" x ");
        if (i <= 1) {
            i = 1;
        }
        strokeTextView.setText(stringBuilder.append(i).toString());
        this.f.setDrawingCacheEnabled(false);
        this.f.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.xllive_gift_num_shink));
        e();
    }

    private float b(int i) {
        if (i < 51) {
            return 24.0f;
        }
        if (i < 201) {
            return 25.0f;
        }
        if (i < 501) {
            return 26.0f;
        }
        return i < 1001 ? 27.0f : 30.0f;
    }

    private int c(int i) {
        if (i < 51) {
            return -16015767;
        }
        if (i < 201) {
            return -28876;
        }
        if (i < 501) {
            return -5425422;
        }
        return i < 1001 ? -903489 : -51644;
    }
}
