package android.support.v7.widget;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

// compiled from: SearchView.java
final class bz implements OnEditorActionListener {
    final /* synthetic */ SearchView a;

    bz(SearchView searchView) {
        this.a = searchView;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        SearchView.j(this.a);
        return true;
    }
}
