package android.support.v7.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: SearchView.java
final class bw implements OnGlobalLayoutListener {
    final /* synthetic */ SearchView a;

    bw(SearchView searchView) {
        this.a = searchView;
    }

    public final void onGlobalLayout() {
        SearchView.d(this.a);
    }
}
