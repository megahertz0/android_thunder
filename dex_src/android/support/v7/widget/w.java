package android.support.v7.widget;

import android.support.v7.widget.ListPopupWindow.b;
import android.view.View;

// compiled from: AppCompatSpinner.java
final class w extends b {
    final /* synthetic */ b a;
    final /* synthetic */ AppCompatSpinner b;

    w(AppCompatSpinner appCompatSpinner, View view, b bVar) {
        this.b = appCompatSpinner;
        this.a = bVar;
        super(view);
    }

    public final ListPopupWindow a() {
        return this.a;
    }

    public final boolean b() {
        if (!this.b.j.c.isShowing()) {
            this.b.j.b();
        }
        return true;
    }
}
