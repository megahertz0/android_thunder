package android.support.v7.app;

import com.xunlei.tdlive.R;

// compiled from: AppCompatDelegateImplV7.java
final class r implements Runnable {
    final /* synthetic */ AppCompatDelegateImplV7 a;

    r(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.a = appCompatDelegateImplV7;
    }

    public final void run() {
        if ((this.a.I & 1) != 0) {
            AppCompatDelegateImplV7.a(this.a, 0);
        }
        if ((this.a.I & 4096) != 0) {
            AppCompatDelegateImplV7.a(this.a, (int) R.styleable.AppCompatTheme_ratingBarStyleSmall);
        }
        this.a.H = false;
        this.a.I = 0;
    }
}
