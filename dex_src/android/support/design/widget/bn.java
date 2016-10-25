package android.support.design.widget;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;

// compiled from: ViewGroupUtils.java
final class bn {
    private static final a a;

    // compiled from: ViewGroupUtils.java
    private static interface a {
        void a(ViewGroup viewGroup, View view, Rect rect);
    }

    // compiled from: ViewGroupUtils.java
    private static class b implements a {
        private b() {
        }

        public final void a(ViewGroup viewGroup, View view, Rect rect) {
            viewGroup.offsetDescendantRectToMyCoords(view, rect);
            rect.offset(view.getScrollX(), view.getScrollY());
        }
    }

    // compiled from: ViewGroupUtils.java
    private static class c implements a {
        private c() {
        }

        public final void a(ViewGroup viewGroup, View view, Rect rect) {
            bo.a(viewGroup, view, rect);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            a = new c();
        } else {
            a = new b();
        }
    }

    static void a(ViewGroup viewGroup, View view, Rect rect) {
        rect.set(0, 0, view.getWidth(), view.getHeight());
        a.a(viewGroup, view, rect);
    }
}
