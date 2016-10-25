package android.support.v7.widget;

import android.view.View;

// compiled from: ListPopupWindow.java
final class au implements Runnable {
    final /* synthetic */ ListPopupWindow a;

    au(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    public final void run() {
        View view = this.a.l;
        if (view != null && view.getWindowToken() != null) {
            this.a.b();
        }
    }
}
