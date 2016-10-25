package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;

// compiled from: LinearLayoutManager.java
final class ar extends at {
    final /* synthetic */ LinearLayoutManager a;

    ar(LinearLayoutManager linearLayoutManager, Context context) {
        this.a = linearLayoutManager;
        super(context);
    }

    public final PointF a(int i) {
        int i2 = 1;
        int i3 = 0;
        LinearLayoutManager linearLayoutManager = this.a;
        if (linearLayoutManager.n() == 0) {
            return null;
        }
        if (i < LinearLayoutManager.a(linearLayoutManager.e(0))) {
            i3 = 1;
        }
        if (r0 != linearLayoutManager.k) {
            i2 = -1;
        }
        return linearLayoutManager.i == 0 ? new PointF((float) i2, 0.0f) : new PointF(0.0f, (float) i2);
    }
}
