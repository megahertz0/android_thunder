package android.support.v7.app;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

// compiled from: AlertController.java
final class e implements OnScrollListener {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ a c;

    e(a aVar, View view, View view2) {
        this.c = aVar;
        this.a = view;
        this.b = view2;
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        a.a(absListView, this.a, this.b);
    }
}
