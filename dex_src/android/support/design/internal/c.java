package android.support.design.internal;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: NavigationMenuPresenter.java
final class c implements OnClickListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
        this.a.b(true);
        MenuItem itemData = navigationMenuItemView.getItemData();
        boolean a = this.a.c.a(itemData, this.a, 0);
        if (itemData != null && itemData.isCheckable() && a) {
            this.a.e.a(itemData);
        }
        this.a.b(false);
        this.a.a(false);
    }
}
