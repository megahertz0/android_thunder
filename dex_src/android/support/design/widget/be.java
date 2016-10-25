package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.R;

// compiled from: ThemeUtils.java
final class be {
    private static final int[] a;

    static {
        a = new int[]{R.attr.colorPrimary};
    }

    static void a(Context context) {
        int i = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(a);
        if (!obtainStyledAttributes.hasValue(0)) {
            i = 1;
        }
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        if (i != 0) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
        }
    }
}
