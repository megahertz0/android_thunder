package uk.co.senab.photoview.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

@TargetApi(8)
// compiled from: FroyoGestureDetector.java
public final class c extends b {
    protected final ScaleGestureDetector f;

    public c(Context context) {
        super(context);
        this.f = new ScaleGestureDetector(context, new d(this));
    }

    public final boolean a() {
        return this.f.isInProgress();
    }

    public final boolean c(MotionEvent motionEvent) {
        this.f.onTouchEvent(motionEvent);
        return super.c(motionEvent);
    }
}
