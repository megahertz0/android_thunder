package android.support.v7.widget;

import android.support.v7.view.menu.a;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: ToolbarWidgetWrapper.java
final class cs implements OnClickListener {
    final a a;
    final /* synthetic */ cr b;

    cs(cr crVar) {
        this.b = crVar;
        this.a = new a(this.b.a.getContext(), this.b.b);
    }

    public final void onClick(View view) {
        if (this.b.c != null && this.b.d) {
            this.b.c.onMenuItemSelected(0, this.a);
        }
    }
}
