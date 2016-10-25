package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;

// compiled from: StaggeredGridLayoutManager.java
final class cd extends at {
    final /* synthetic */ StaggeredGridLayoutManager a;

    cd(StaggeredGridLayoutManager staggeredGridLayoutManager, Context context) {
        this.a = staggeredGridLayoutManager;
        super(context);
    }

    public final PointF a(int i) {
        int a = StaggeredGridLayoutManager.a(this.a, i);
        if (a == 0) {
            return null;
        }
        return StaggeredGridLayoutManager.b(this.a) == 0 ? new PointF((float) a, 0.0f) : new PointF(0.0f, (float) a);
    }
}
