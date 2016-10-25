package uk.co.senab.photoview.c;

import android.annotation.TargetApi;
import android.content.Context;

@TargetApi(14)
// compiled from: IcsScroller.java
public final class b extends a {
    public b(Context context) {
        super(context);
    }

    public final boolean a() {
        return this.a.computeScrollOffset();
    }
}
