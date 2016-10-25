package android.support.v7.widget;

import android.support.v7.widget.ListPopupWindow.b;
import android.view.View;

// compiled from: ActionMenuPresenter.java
final class h extends b {
    final /* synthetic */ ActionMenuPresenter a;
    final /* synthetic */ d b;

    h(d dVar, View view, ActionMenuPresenter actionMenuPresenter) {
        this.b = dVar;
        this.a = actionMenuPresenter;
        super(view);
    }

    public final ListPopupWindow a() {
        return this.b.a.A == null ? null : this.b.a.A.c;
    }

    public final boolean b() {
        this.b.a.e();
        return true;
    }

    public final boolean c() {
        if (this.b.a.o != null) {
            return false;
        }
        this.b.a.f();
        return true;
    }
}
