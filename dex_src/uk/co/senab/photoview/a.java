package uk.co.senab.photoview;

import android.os.Build.VERSION;
import android.view.View;

// compiled from: Compat.java
public final class a {
    public static void a(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }
}
