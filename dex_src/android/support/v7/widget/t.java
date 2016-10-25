package android.support.v7.widget;

import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

// compiled from: AppCompatPopupWindow.java
final class t implements OnScrollChangedListener {
    final /* synthetic */ Field a;
    final /* synthetic */ PopupWindow b;
    final /* synthetic */ OnScrollChangedListener c;

    t(Field field, PopupWindow popupWindow, OnScrollChangedListener onScrollChangedListener) {
        this.a = field;
        this.b = popupWindow;
        this.c = onScrollChangedListener;
    }

    public final void onScrollChanged() {
        try {
            WeakReference weakReference = (WeakReference) this.a.get(this.b);
            if (weakReference != null && weakReference.get() != null) {
                this.c.onScrollChanged();
            }
        } catch (IllegalAccessException e) {
        }
    }
}
