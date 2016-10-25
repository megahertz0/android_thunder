package android.support.design.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

// compiled from: TextInputLayout.java
final class ba extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ TextInputLayout a;

    ba(TextInputLayout textInputLayout) {
        this.a = textInputLayout;
    }

    public final void onAnimationStart(View view) {
        view.setVisibility(0);
    }
}
