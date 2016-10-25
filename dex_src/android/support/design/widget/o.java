package android.support.design.widget;

import android.support.design.widget.CoordinatorLayout.d;
import android.view.View;
import java.util.Comparator;

// compiled from: CoordinatorLayout.java
final class o implements Comparator<View> {
    final /* synthetic */ CoordinatorLayout a;

    o(CoordinatorLayout coordinatorLayout) {
        this.a = coordinatorLayout;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        View view = (View) obj;
        View view2 = (View) obj2;
        if (view != view2) {
            if (((d) view.getLayoutParams()).a(view2)) {
                return 1;
            }
            if (((d) view2.getLayoutParams()).a(view)) {
                return -1;
            }
        }
        return 0;
    }
}
