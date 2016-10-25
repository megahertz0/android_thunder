package android.support.design.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewOutlineProvider;

// compiled from: ViewUtils.java
final class bq {
    static final d a;
    private static final a b;

    // compiled from: ViewUtils.java
    private static interface a {
        void a(View view);
    }

    // compiled from: ViewUtils.java
    private static class b implements a {
        private b() {
        }

        public final void a(View view) {
        }
    }

    // compiled from: ViewUtils.java
    private static class c implements a {
        private c() {
        }

        public final void a(View view) {
            view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
        }
    }

    static {
        a = new br();
        if (VERSION.SDK_INT >= 21) {
            b = new c();
        } else {
            b = new b();
        }
    }

    static void a(View view) {
        b.a(view);
    }

    static bf a() {
        return a.a();
    }
}
