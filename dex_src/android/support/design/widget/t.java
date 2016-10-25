package android.support.design.widget;

import android.support.design.widget.FloatingActionButton.Behavior;
import android.support.v4.view.ViewCompat;

// compiled from: FloatingActionButton.java
final class t implements c {
    final /* synthetic */ FloatingActionButton a;
    final /* synthetic */ Behavior b;

    t(Behavior behavior, FloatingActionButton floatingActionButton) {
        this.b = behavior;
        this.a = floatingActionButton;
    }

    public final void a(bf bfVar) {
        ViewCompat.setTranslationY(this.a, bfVar.a.d());
    }
}
