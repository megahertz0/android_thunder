package android.support.design.widget;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

// compiled from: CollapsingToolbarLayout.java
final class m implements OnApplyWindowInsetsListener {
    final /* synthetic */ CollapsingToolbarLayout a;

    m(CollapsingToolbarLayout collapsingToolbarLayout) {
        this.a = collapsingToolbarLayout;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return CollapsingToolbarLayout.a(this.a, windowInsetsCompat);
    }
}
