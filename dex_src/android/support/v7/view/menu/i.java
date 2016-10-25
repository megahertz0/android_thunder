package android.support.v7.view.menu;

import android.support.v4.view.ActionProvider.VisibilityListener;

// compiled from: MenuItemImpl.java
final class i implements VisibilityListener {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public final void onActionProviderVisibilityChanged(boolean z) {
        this.a.b.f();
    }
}
