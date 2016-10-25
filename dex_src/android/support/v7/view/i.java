package android.support.v7.view;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

// compiled from: ViewPropertyAnimatorCompatSet.java
final class i extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ h a;
    private boolean b;
    private int c;

    i(h hVar) {
        this.a = hVar;
        this.b = false;
        this.c = 0;
    }

    public final void onAnimationStart(View view) {
        if (!this.b) {
            this.b = true;
            if (this.a.b != null) {
                this.a.b.onAnimationStart(null);
            }
        }
    }

    public final void onAnimationEnd(View view) {
        int i = this.c + 1;
        this.c = i;
        if (i == this.a.a.size()) {
            if (this.a.b != null) {
                this.a.b.onAnimationEnd(null);
            }
            this.c = 0;
            this.b = false;
            this.a.c = false;
        }
    }
}
