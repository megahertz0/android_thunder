package uk.co.senab.photoview;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

// compiled from: PhotoViewAttacher.java
final class e extends SimpleOnGestureListener {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public final void onLongPress(MotionEvent motionEvent) {
        if (d.a(this.a) != null) {
            d.a(this.a).onLongClick(this.a.c());
        }
    }
}
