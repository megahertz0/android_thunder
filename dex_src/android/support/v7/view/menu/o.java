package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.Menu;
import android.view.MenuItem;

// compiled from: MenuWrapperFactory.java
public final class o {
    public static Menu a(Context context, SupportMenu supportMenu) {
        if (VERSION.SDK_INT >= 14) {
            return new p(context, supportMenu);
        }
        throw new UnsupportedOperationException();
    }

    public static MenuItem a(Context context, SupportMenuItem supportMenuItem) {
        if (VERSION.SDK_INT >= 16) {
            return new k(context, supportMenuItem);
        }
        if (VERSION.SDK_INT >= 14) {
            return new j(context, supportMenuItem);
        }
        throw new UnsupportedOperationException();
    }
}
