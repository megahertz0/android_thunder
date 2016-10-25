package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

// compiled from: SubMenuWrapperICS.java
final class r extends p implements SubMenu {
    r(Context context, SupportSubMenu supportSubMenu) {
        super(context, supportSubMenu);
    }

    public final SubMenu setHeaderTitle(int i) {
        ((SupportSubMenu) this.d).setHeaderTitle(i);
        return this;
    }

    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        ((SupportSubMenu) this.d).setHeaderTitle(charSequence);
        return this;
    }

    public final SubMenu setHeaderIcon(int i) {
        ((SupportSubMenu) this.d).setHeaderIcon(i);
        return this;
    }

    public final SubMenu setHeaderIcon(Drawable drawable) {
        ((SupportSubMenu) this.d).setHeaderIcon(drawable);
        return this;
    }

    public final SubMenu setHeaderView(View view) {
        ((SupportSubMenu) this.d).setHeaderView(view);
        return this;
    }

    public final void clearHeader() {
        ((SupportSubMenu) this.d).clearHeader();
    }

    public final SubMenu setIcon(int i) {
        ((SupportSubMenu) this.d).setIcon(i);
        return this;
    }

    public final SubMenu setIcon(Drawable drawable) {
        ((SupportSubMenu) this.d).setIcon(drawable);
        return this;
    }

    public final MenuItem getItem() {
        return a(((SupportSubMenu) this.d).getItem());
    }
}
