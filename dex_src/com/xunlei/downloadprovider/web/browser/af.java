package com.xunlei.downloadprovider.web.browser;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import com.xunlei.downloadprovider.a.b;

// compiled from: InputAutoCompleteView.java
final class af implements OnKeyListener {
    final /* synthetic */ InputAutoCompleteView a;

    af(InputAutoCompleteView inputAutoCompleteView) {
        this.a = inputAutoCompleteView;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 66 && keyEvent.getKeyCode() != 0) {
            return false;
        }
        b.a(this.a.g, view);
        return true;
    }
}
