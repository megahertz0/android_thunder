package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import com.xunlei.tdlive.R;

// compiled from: AppCompatDelegateImplV7.java
final class v implements Runnable {
    final /* synthetic */ AppCompatDelegateImplV7 a;

    v(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.a = appCompatDelegateImplV7;
    }

    public final void run() {
        this.a.s.showAtLocation(this.a.r, R.styleable.AppCompatTheme_dividerVertical, 0, 0);
        this.a.m();
        ViewCompat.setAlpha(this.a.r, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        this.a.u = ViewCompat.animate(this.a.r).alpha(1.0f);
        this.a.u.setListener(new w(this));
    }
}
