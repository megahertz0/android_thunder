package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import com.xunlei.tdlive.R;

// compiled from: AppCompatDelegateImplV7.java
final class x extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ AppCompatDelegateImplV7 a;

    x(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.a = appCompatDelegateImplV7;
    }

    public final void onAnimationEnd(View view) {
        ViewCompat.setAlpha(this.a.r, 1.0f);
        this.a.u.setListener(null);
        this.a.u = null;
    }

    public final void onAnimationStart(View view) {
        this.a.r.setVisibility(0);
        this.a.r.sendAccessibilityEvent(R.styleable.AppCompatTheme_actionModeCutDrawable);
        if (this.a.r.getParent() != null) {
            ViewCompat.requestApplyInsets((View) this.a.r.getParent());
        }
    }
}
