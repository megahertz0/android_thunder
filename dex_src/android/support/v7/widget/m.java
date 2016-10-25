package android.support.v7.widget;

import android.support.v7.widget.ListPopupWindow.b;
import android.view.View;

// compiled from: ActivityChooserView.java
final class m extends b {
    final /* synthetic */ ActivityChooserView a;

    m(ActivityChooserView activityChooserView, View view) {
        this.a = activityChooserView;
        super(view);
    }

    public final ListPopupWindow a() {
        return this.a.getListPopupWindow();
    }

    protected final boolean b() {
        this.a.a();
        return true;
    }

    protected final boolean c() {
        this.a.b();
        return true;
    }
}
