package com.xunlei.tdlive.play.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.v;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class LevelUpgradeBar extends LinearLayout {
    private TextView a;
    private ImageView b;
    private View c;
    private Queue<a> d;
    private boolean e;

    public static final class a {
        public String a;
        public String b;
        public int c;
        public String d;
    }

    public LevelUpgradeBar(Context context) {
        super(context);
        this.e = false;
        a();
    }

    public LevelUpgradeBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = false;
        a();
    }

    public LevelUpgradeBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = false;
        a();
    }

    @TargetApi(21)
    public LevelUpgradeBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.e = false;
        a();
    }

    private void a() {
        this.d = new ArrayDeque();
        inflate(getContext(), R.layout.xllive_level_upgrade_bar, this);
        this.c = findViewById(R.id.barRoot);
        this.a = (TextView) this.c.findViewById(R.id.tvUserLevelBar);
        this.b = (ImageView) this.c.findViewById(R.id.ivLevel);
    }

    private void b() {
        a aVar = (a) this.d.poll();
        if (aVar != null) {
            a(aVar.a, aVar.b, aVar.c, aVar.d);
        }
    }

    public void showLevelUpgrade(a aVar) {
        if (this.e) {
            this.d.add(aVar);
        } else {
            a(aVar.a, aVar.b, aVar.c, aVar.d);
        }
    }

    private void a(String str, String str2, int i, String str3) {
        setVisibility(0);
        this.e = true;
        int parseColor = Color.parseColor("#fff000");
        this.a.setText(a(new String[]{"\u606d\u559c", v.a(str, com.xunlei.xllib.R.styleable.Toolbar_titleMargins), "\u5728", v.a(str2, com.xunlei.xllib.R.styleable.Toolbar_titleMargins), "\u7684\u76f4\u64ad\u95f4\u5347\u4e3a"}, new int[]{-1, parseColor, -1, parseColor, -1}));
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        windowManager.getDefaultDisplay().getMetrics(new DisplayMetrics());
        Animation translateAnimation = new TranslateAnimation(2, 0.0f, 2, -1.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(6000);
        translateAnimation.setAnimationListener(new m(this));
        startAnimation(translateAnimation);
        com.xunlei.tdlive.util.a.a(getContext()).a(str3, new n(this));
    }

    private SpannableString a(String[] strArr, int[] iArr) {
        int i = 0;
        if (strArr.length != iArr.length) {
            return null;
        }
        int i2;
        CharSequence charSequence;
        String str = BuildConfig.VERSION_NAME;
        for (i2 = 0; i2 < strArr.length; i2++) {
            charSequence = charSequence + strArr[i2];
        }
        SpannableString spannableString = new SpannableString(charSequence);
        i2 = 0;
        while (i < strArr.length) {
            int length = strArr[i].length();
            spannableString.setSpan(new ForegroundColorSpan(iArr[i]), i2, i2 + length, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
            i2 += length;
            i++;
        }
        return spannableString;
    }

    private void a(Bitmap bitmap) {
        int b = b(bitmap);
        XLog.d("LevelUpgradeBar", new StringBuilder("mainColor = ").append(b).toString());
        this.c.setBackgroundDrawable(a(b));
        this.b.setImageBitmap(bitmap);
    }

    private GradientDrawable a(int i) {
        int argb = Color.argb(Color.alpha(i) / 2, Color.red(i), Color.green(i), Color.blue(i));
        GradientDrawable gradientDrawable = new GradientDrawable(Orientation.LEFT_RIGHT, new int[]{argb, Color.parseColor("#80000000")});
        gradientDrawable.setCornerRadius(d.a(getContext(), 9.0f));
        return gradientDrawable;
    }

    private int b(Bitmap bitmap) {
        int i = 0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Random random = new Random();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i < 20) {
            int pixel = bitmap.getPixel(random.nextInt(width), random.nextInt(height));
            i5 += Color.red(pixel);
            i4 += Color.green(pixel);
            i3 += Color.blue(pixel);
            i2 += Color.alpha(pixel);
            i++;
        }
        return Color.argb(i2 / 20, i5 / 20, i4 / 20, i3 / 20);
    }
}
