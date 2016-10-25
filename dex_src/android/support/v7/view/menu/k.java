package android.support.v7.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.view.ActionProvider.VisibilityListener;
import android.view.MenuItem;
import android.view.View;

@TargetApi(16)
// compiled from: MenuItemWrapperJB.java
final class k extends j {

    // compiled from: MenuItemWrapperJB.java
    class a extends a implements VisibilityListener {
        ActionProvider.VisibilityListener c;

        public a(Context context, android.view.ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        public final View onCreateActionView(MenuItem menuItem) {
            return this.a.onCreateActionView(menuItem);
        }

        public final boolean overridesItemVisibility() {
            return this.a.overridesItemVisibility();
        }

        public final boolean isVisible() {
            return this.a.isVisible();
        }

        public final void refreshVisibility() {
            this.a.refreshVisibility();
        }

        public final void setVisibilityListener(ActionProvider.VisibilityListener visibilityListener) {
            VisibilityListener visibilityListener2;
            this.c = visibilityListener;
            android.view.ActionProvider actionProvider = this.a;
            if (visibilityListener == null) {
                visibilityListener2 = null;
            }
            actionProvider.setVisibilityListener(visibilityListener2);
        }

        public final void onActionProviderVisibilityChanged(boolean z) {
            if (this.c != null) {
                this.c.onActionProviderVisibilityChanged(z);
            }
        }
    }

    k(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    final a a(android.view.ActionProvider actionProvider) {
        return new a(this.a, actionProvider);
    }
}
