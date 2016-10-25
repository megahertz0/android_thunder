package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.xunlei.xllib.R;

public class RefreshPromptView extends TextView {
    private Handler a;
    private ObjectAnimator b;
    private Runnable c;

    public RefreshPromptView(Context context) {
        super(context);
        this.a = new Handler();
        this.c = new ag(this);
    }

    public RefreshPromptView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Handler();
        this.c = new ag(this);
    }

    public RefreshPromptView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Handler();
        this.c = new ag(this);
    }

    public final void a(int i) {
        this.a.removeCallbacks(this.c);
        CharSequence charSequence = i + " \u6761\u5185\u5bb9\u66f4\u65b0";
        String valueOf = String.valueOf(i);
        int indexOf = charSequence.indexOf(valueOf);
        if (indexOf >= 0) {
            CharSequence spannableStringBuilder = new SpannableStringBuilder(charSequence);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#1294f6")), indexOf, valueOf.length() + indexOf, R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) ((getResources().getDisplayMetrics().density * 14.0f) + 0.5f)), indexOf, valueOf.length() + indexOf, R.styleable.AppCompatTheme_actionModePasteDrawable);
            setText(spannableStringBuilder);
            if (this.b != null && this.b.isRunning()) {
                this.b.cancel();
            }
            this.b = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, new float[]{getTranslationY(), 0.0f});
            this.b.setDuration(400);
            this.b.start();
            this.a.postDelayed(this.c, 1600);
        }
    }

    public final void a() {
        this.a.removeCallbacks(this.c);
        if (this.b != null && this.b.isRunning()) {
            this.b.cancel();
        }
        this.b = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, new float[]{getTranslationY(), (float) (-getHeight())});
        this.b.setDuration(400);
        this.b.start();
    }
}
