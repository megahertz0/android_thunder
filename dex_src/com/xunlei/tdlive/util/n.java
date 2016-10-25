package com.xunlei.tdlive.util;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.widget.TextView;
import com.xunlei.tdlive.R;

// compiled from: SpannedStringUtil.java
public class n {
    private static Drawable a;
    private static Drawable b;

    public static void a(TextView textView, Spannable spannable) {
        if (a == null) {
            a = textView.getResources().getDrawable(R.drawable.xllive_user_grade_zero_sq);
        }
        if (b == null) {
            b = textView.getResources().getDrawable(R.drawable.xllive_loading);
        }
        b(textView, spannable);
        String toString = spannable.toString();
        int indexOf = toString.indexOf("[GIFTX_");
        int indexOf2 = toString.indexOf("]", indexOf >= 0 ? indexOf : 0);
        if (!(indexOf == -1 || indexOf2 == -1)) {
            spannable.setSpan(b(textView, b), indexOf, indexOf2 + 1, com.xunlei.xllib.R.styleable.Toolbar_collapseIcon);
        }
        b(textView, spannable, "[GRADE_", a, new o(textView, spannable));
    }

    private static void b(TextView textView, Spannable spannable) {
        Object obj;
        String toString = spannable.toString();
        int indexOf = toString.indexOf("[HEART_");
        int indexOf2 = toString.indexOf("]", indexOf >= 0 ? indexOf : 0);
        if (indexOf == -1 || indexOf2 == -1) {
            obj = null;
        } else {
            try {
                obj = b(textView, new BitmapDrawable(textView.getResources(), e.a(textView.getContext(), Color.parseColor(toString.substring(indexOf + 7, indexOf2)))));
            } catch (Exception e) {
                obj = null;
            }
        }
        if (obj != null) {
            spannable.setSpan(obj, indexOf, indexOf2 + 1, com.xunlei.xllib.R.styleable.Toolbar_collapseIcon);
        }
        textView.setText(spannable);
    }

    private static void b(TextView textView, Spannable spannable, String str, Drawable drawable, Runnable runnable) {
        String toString = spannable.toString();
        int indexOf = toString.indexOf(str);
        int indexOf2 = toString.indexOf("]", indexOf >= 0 ? indexOf : 0);
        if (indexOf == -1 || indexOf2 == -1) {
            textView.setText(spannable);
        } else {
            a.a(textView.getContext()).a(textView, toString.substring(indexOf + 7, indexOf2), new p(spannable, drawable, indexOf, indexOf2, runnable));
        }
    }

    private static ImageSpan b(TextView textView, Drawable drawable) {
        int minimumWidth = drawable.getMinimumWidth();
        int minimumHeight = drawable.getMinimumHeight();
        float textSize = textView.getTextSize() - d.b(textView.getContext(), 2.0f);
        drawable.setBounds(0, 0, (int) (minimumHeight == 0 ? textSize : (((float) minimumWidth) * textSize) / ((float) minimumHeight)), (int) textSize);
        return new ImageSpan(drawable, 1);
    }
}
