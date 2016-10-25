package android.support.design.widget;

import android.support.design.widget.AppBarLayout.Behavior;

// compiled from: AppBarLayout.java
final class c implements c {
    final /* synthetic */ CoordinatorLayout a;
    final /* synthetic */ AppBarLayout b;
    final /* synthetic */ Behavior c;

    c(Behavior behavior, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        this.c = behavior;
        this.a = coordinatorLayout;
        this.b = appBarLayout;
    }

    public final void a(bf bfVar) {
        this.c.a_(this.a, this.b, bfVar.a.c());
    }
}
