package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v4.util.ArrayMap;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Map;

// compiled from: BaseMenuWrapper.java
abstract class c<T> extends d<T> {
    final Context a;
    Map<SupportMenuItem, MenuItem> b;
    Map<SupportSubMenu, SubMenu> c;

    c(Context context, T t) {
        super(t);
        this.a = context;
    }

    final MenuItem a(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return menuItem;
        }
        SupportMenuItem supportMenuItem = (SupportMenuItem) menuItem;
        if (this.b == null) {
            this.b = new ArrayMap();
        }
        MenuItem menuItem2 = (MenuItem) this.b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        menuItem2 = o.a(this.a, supportMenuItem);
        this.b.put(supportMenuItem, menuItem2);
        return menuItem2;
    }

    final SubMenu a(SubMenu subMenu) {
        if (!(subMenu instanceof SupportSubMenu)) {
            return subMenu;
        }
        SupportSubMenu supportSubMenu = (SupportSubMenu) subMenu;
        if (this.c == null) {
            this.c = new ArrayMap();
        }
        SubMenu subMenu2 = (SubMenu) this.c.get(supportSubMenu);
        if (subMenu2 != null) {
            return subMenu2;
        }
        Context context = this.a;
        if (VERSION.SDK_INT >= 14) {
            subMenu2 = new r(context, supportSubMenu);
            this.c.put(supportSubMenu, subMenu2);
            return subMenu2;
        }
        throw new UnsupportedOperationException();
    }
}
