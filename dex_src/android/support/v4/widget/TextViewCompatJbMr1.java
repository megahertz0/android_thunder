package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

class TextViewCompatJbMr1 {
    TextViewCompatJbMr1() {
    }

    public static void setCompoundDrawablesRelative(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        Drawable drawable5;
        if (textView.getLayoutDirection() == 1) {
            int i = 1;
        } else {
            Object obj = null;
        }
        if (obj != null) {
            drawable5 = drawable3;
        } else {
            drawable5 = drawable;
        }
        if (obj == null) {
            drawable = drawable3;
        }
        textView.setCompoundDrawables(drawable5, drawable2, drawable, drawable4);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        Drawable drawable5;
        if (textView.getLayoutDirection() == 1) {
            int i = 1;
        } else {
            Object obj = null;
        }
        if (obj != null) {
            drawable5 = drawable3;
        } else {
            drawable5 = drawable;
        }
        if (obj == null) {
            drawable = drawable3;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable5, drawable2, drawable, drawable4);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textView, int i, int i2, int i3, int i4) {
        int i5;
        if (textView.getLayoutDirection() == 1) {
            int i6 = 1;
        } else {
            Object obj = null;
        }
        if (obj != null) {
            i5 = i3;
        } else {
            i5 = i;
        }
        if (obj == null) {
            i = i3;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(i5, i2, i, i4);
    }
}
