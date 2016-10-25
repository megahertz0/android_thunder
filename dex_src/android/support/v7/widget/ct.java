package android.support.v7.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

// compiled from: ToolbarWidgetWrapper.java
final class ct extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ int a;
    final /* synthetic */ cr b;
    private boolean c;

    ct(cr crVar, int i) {
        this.b = crVar;
        this.a = i;
        this.c = false;
    }

    public final void onAnimationStart(View view) {
        this.b.a.setVisibility(0);
    }

    public final void onAnimationEnd(View view) {
        if (!this.c) {
            this.b.a.setVisibility(this.a);
        }
    }

    public final void onAnimationCancel(View view) {
        this.c = true;
    }
}
