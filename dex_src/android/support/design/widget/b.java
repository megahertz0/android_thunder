package android.support.design.widget;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

// compiled from: AppBarLayout.java
final class b implements OnApplyWindowInsetsListener {
    final /* synthetic */ AppBarLayout a;

    b(AppBarLayout appBarLayout) {
        this.a = appBarLayout;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return AppBarLayout.a(this.a, windowInsetsCompat);
    }
}
