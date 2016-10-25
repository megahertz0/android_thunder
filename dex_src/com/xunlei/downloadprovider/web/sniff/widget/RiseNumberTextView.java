package com.xunlei.downloadprovider.web.sniff.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xunlei.download.proguard.c;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;

@SuppressLint({"NewApi"})
public class RiseNumberTextView extends TextView {
    static final int[] a;
    private int b;
    private int c;
    private long d;
    private float e;
    private int f;
    private a g;
    private int h;
    private String i;
    private CharSequence j;
    private String k;

    public static interface a {
    }

    static {
        a = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    }

    public RiseNumberTextView(Context context) {
        super(context);
        this.d = 1500;
        this.e = 1.0f;
        this.f = 1;
        this.g = null;
        this.i = BuildConfig.VERSION_NAME;
        this.j = BuildConfig.VERSION_NAME;
        this.k = BuildConfig.VERSION_NAME;
    }

    public RiseNumberTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 1500;
        this.e = 1.0f;
        this.f = 1;
        this.g = null;
        this.i = BuildConfig.VERSION_NAME;
        this.j = BuildConfig.VERSION_NAME;
        this.k = BuildConfig.VERSION_NAME;
    }

    public RiseNumberTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 1500;
        this.e = 1.0f;
        this.f = 1;
        this.g = null;
        this.i = BuildConfig.VERSION_NAME;
        this.j = BuildConfig.VERSION_NAME;
        this.k = BuildConfig.VERSION_NAME;
    }

    public void setNumberText(int i) {
        this.h = i;
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(this.i);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(String.valueOf(i));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getTextColors().getDefaultColor()), length, spannableStringBuilder.length(), R.styleable.AppCompatTheme_actionModeCopyDrawable);
        spannableStringBuilder.append(this.j);
        setText(spannableStringBuilder);
    }

    public int getTextNumber() {
        return this.h;
    }

    public void setToNumber(int i) {
        this.b = i;
    }

    public void setFromNumber(int i) {
        this.c = i;
    }

    public void setDuration(long j) {
        if (j < 0) {
            j = c.x;
        }
        this.d = j;
        this.f = 1;
    }

    public void setOnEnd(a aVar) {
        this.g = aVar;
    }

    public void setSpeed(float f) {
        if (f < 0.0f) {
            this.e = 1.0f;
            return;
        }
        this.e = f;
        this.f = 2;
    }

    public void setTimeType(int i) {
        this.f = i;
    }

    public void setNumberPrefix(String str) {
        if (str == null) {
            str = BuildConfig.VERSION_NAME;
        }
        this.i = str;
    }

    public void setNumberSuffix(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = BuildConfig.VERSION_NAME;
        }
        this.j = charSequence;
    }

    public void setInitText(String str) {
        this.k = str;
    }
}
