package android.support.v7.widget;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

// compiled from: SearchView.java
final class bo implements Runnable {
    final /* synthetic */ SearchView a;

    bo(SearchView searchView) {
        this.a = searchView;
    }

    public final void run() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.a.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            a aVar = SearchView.a;
            View view = this.a;
            if (aVar.c != null) {
                try {
                    aVar.c.invoke(inputMethodManager, new Object[]{Integer.valueOf(0), null});
                    return;
                } catch (Exception e) {
                }
            }
            inputMethodManager.showSoftInput(view, 0);
        }
    }
}
