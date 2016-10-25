package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.TypedValue;

// compiled from: ThemeUtils.java
final class ci {
    static final int[] a;
    static final int[] b;
    static final int[] c;
    static final int[] d;
    static final int[] e;
    static final int[] f;
    static final int[] g;
    static final int[] h;
    private static final ThreadLocal<TypedValue> i;
    private static final int[] j;

    static {
        i = new ThreadLocal();
        a = new int[]{-16842910};
        b = new int[]{16842908};
        c = new int[]{16843518};
        d = new int[]{16842919};
        e = new int[]{16842912};
        f = new int[]{16842913};
        g = new int[]{-16842919, -16842908};
        h = new int[0];
        j = new int[1];
    }

    public static int a(Context context, int i) {
        j[0] = i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, j);
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static ColorStateList b(Context context, int i) {
        j[0] = i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, j);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(0);
        obtainStyledAttributes.recycle();
        return colorStateList;
    }

    public static int c(Context context, int i) {
        ColorStateList b = b(context, i);
        if (b != null && b.isStateful()) {
            return b.getColorForState(a, b.getDefaultColor());
        }
        TypedValue typedValue = (TypedValue) i.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            i.set(typedValue);
        }
        context.getTheme().resolveAttribute(16842803, typedValue, true);
        return a(context, i, typedValue.getFloat());
    }

    static int a(Context context, int i, float f) {
        int a = a(context, i);
        return ColorUtils.setAlphaComponent(a, Math.round(((float) Color.alpha(a)) * f));
    }
}
