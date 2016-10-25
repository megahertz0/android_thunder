package android.support.v7.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: ActivityChooserView.java
final class l implements OnGlobalLayoutListener {
    final /* synthetic */ ActivityChooserView a;

    l(ActivityChooserView activityChooserView) {
        this.a = activityChooserView;
    }

    public final void onGlobalLayout() {
        if (!this.a.c()) {
            return;
        }
        if (this.a.isShown()) {
            this.a.getListPopupWindow().b();
            if (this.a.a != null) {
                this.a.a.subUiVisibilityChanged(true);
                return;
            }
            return;
        }
        this.a.getListPopupWindow().d();
    }
}
