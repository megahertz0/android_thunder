package android.support.v7.widget;

import android.support.v4.view.KeyEventCompat;
import android.support.v7.widget.SearchView.SearchAutoComplete;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

// compiled from: SearchView.java
final class by implements OnKeyListener {
    final /* synthetic */ SearchView a;

    by(SearchView searchView) {
        this.a = searchView;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (this.a.L == null) {
            return false;
        }
        if (this.a.d.isPopupShowing() && this.a.d.getListSelection() != -1) {
            return this.a.a(i, keyEvent);
        }
        if (SearchAutoComplete.a(this.a.d) || !KeyEventCompat.hasNoModifiers(keyEvent) || keyEvent.getAction() != 1 || i != 66) {
            return false;
        }
        view.cancelLongPress();
        this.a.a(this.a.d.getText().toString());
        return true;
    }
}
