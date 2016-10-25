package android.support.v7.app;

import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.view.View;

// compiled from: AlertController.java
final class c implements OnScrollChangeListener {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ a c;

    c(a aVar, View view, View view2) {
        this.c = aVar;
        this.a = view;
        this.b = view2;
    }

    public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        a.a(nestedScrollView, this.a, this.b);
    }
}
