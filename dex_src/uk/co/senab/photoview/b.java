package uk.co.senab.photoview;

import android.graphics.RectF;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import uk.co.senab.photoview.d.e;

// compiled from: DefaultOnDoubleTapListener.java
public final class b implements OnDoubleTapListener {
    private d a;

    public b(d dVar) {
        this.a = dVar;
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.a == null) {
            return false;
        }
        this.a.c();
        if (this.a.j != null) {
            RectF b = this.a.b();
            if (b != null && b.contains(motionEvent.getX(), motionEvent.getY())) {
                float f = b.left;
                b.width();
                f = b.top;
                b.height();
                d$d uk_co_senab_photoview_d_d = this.a.j;
                return true;
            }
        }
        if (this.a.k == null) {
            return false;
        }
        e eVar = this.a.k;
        motionEvent.getX();
        motionEvent.getY();
        eVar.a();
        return false;
    }

    public final boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.a == null) {
            return false;
        }
        try {
            float d = this.a.d();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (d < this.a.d) {
                this.a.a(this.a.d, x, y, true);
                return true;
            } else if (d < this.a.d || d >= this.a.e) {
                this.a.a(this.a.c, x, y, true);
                return true;
            } else {
                this.a.a(this.a.e, x, y, true);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}
