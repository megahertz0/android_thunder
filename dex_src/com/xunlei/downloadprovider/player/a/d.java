package com.xunlei.downloadprovider.player.a;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import com.xunlei.downloadprovider.R;

// compiled from: AutoPlayUtil.java
public final class d {
    public static int a(Context context, Rect rect, View view) {
        Object obj = null;
        if (!view.getLocalVisibleRect(rect)) {
            return 0;
        }
        int height = view.getHeight();
        float dimension = context.getResources().getDimension(R.dimen.main_bottom_tab_height);
        if (rect.top > 0) {
            int i = 1;
        } else {
            Object obj2 = null;
        }
        if (obj2 != null) {
            return ((height - rect.top) * 100) / height;
        }
        if (rect.bottom > 0 && rect.bottom < height) {
            int i2 = 1;
        }
        return obj != null ? (((int) (((float) rect.bottom) - dimension)) * 100) / height : com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle;
    }
}
