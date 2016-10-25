package android.support.v7.widget;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.PopupWindow.OnDismissListener;

// compiled from: AppCompatSpinner.java
final class z implements OnDismissListener {
    final /* synthetic */ OnGlobalLayoutListener a;
    final /* synthetic */ b b;

    z(b bVar, OnGlobalLayoutListener onGlobalLayoutListener) {
        this.b = bVar;
        this.a = onGlobalLayoutListener;
    }

    public final void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.b.b.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.a);
        }
    }
}
