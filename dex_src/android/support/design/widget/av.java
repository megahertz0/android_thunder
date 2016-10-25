package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.View;
import android.view.ViewParent;

// compiled from: SwipeDismissBehavior.java
final class av extends Callback {
    final /* synthetic */ SwipeDismissBehavior a;
    private int b;
    private int c;

    av(SwipeDismissBehavior swipeDismissBehavior) {
        this.a = swipeDismissBehavior;
        this.c = -1;
    }

    public final boolean tryCaptureView(View view, int i) {
        return this.c == -1 && this.a.b(view);
    }

    public final void onViewCaptured(View view, int i) {
        this.c = i;
        this.b = view.getLeft();
        ViewParent parent = view.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    public final void onViewDragStateChanged(int i) {
        if (this.a.b != null) {
            this.a.b.a(i);
        }
    }

    public final void onViewReleased(View view, float f, float f2) {
        boolean z;
        int i;
        boolean z2 = true;
        this.c = -1;
        int width = view.getWidth();
        Object obj;
        if (f != 0.0f) {
            if (ViewCompat.getLayoutDirection(view) == 1) {
                z = true;
            } else {
                obj = null;
            }
            if (this.a.c == 2) {
                z = true;
            } else if (this.a.c == 0) {
                if (z) {
                    if (f < 0.0f) {
                        z = true;
                    } else {
                        obj = null;
                    }
                } else if (f > 0.0f) {
                    z = true;
                } else {
                    obj = null;
                }
            } else if (this.a.c != 1) {
                obj = null;
            } else if (z) {
                if (f > 0.0f) {
                    z = true;
                } else {
                    obj = null;
                }
            } else if (f < 0.0f) {
                z = true;
            } else {
                obj = null;
            }
        } else {
            if (Math.abs(view.getLeft() - this.b) >= Math.round(((float) view.getWidth()) * this.a.i)) {
                z = true;
            } else {
                obj = null;
            }
        }
        if (z) {
            i = view.getLeft() < this.b ? this.b - width : this.b + width;
        } else {
            i = this.b;
            z2 = false;
        }
        if (this.a.a.settleCapturedViewAt(i, view.getTop())) {
            ViewCompat.postOnAnimation(view, new b(view, z2));
        } else if (z2 && this.a.b != null) {
            this.a.b.a(view);
        }
    }

    public final int getViewHorizontalDragRange(View view) {
        return view.getWidth();
    }

    public final int clampViewPositionHorizontal(View view, int i, int i2) {
        int i3;
        int i4;
        if (ViewCompat.getLayoutDirection(view) == 1) {
            i3 = 1;
        } else {
            Object obj = null;
        }
        if (this.a.c == 0) {
            if (obj != null) {
                i3 = this.b - view.getWidth();
                i4 = this.b;
            } else {
                i3 = this.b;
                i4 = this.b + view.getWidth();
            }
        } else if (this.a.c != 1) {
            i3 = this.b - view.getWidth();
            i4 = this.b + view.getWidth();
        } else if (obj != null) {
            i3 = this.b;
            i4 = this.b + view.getWidth();
        } else {
            i3 = this.b - view.getWidth();
            i4 = this.b;
        }
        return Math.min(Math.max(i3, i), i4);
    }

    public final int clampViewPositionVertical(View view, int i, int i2) {
        return view.getTop();
    }

    public final void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
        float width = ((float) this.b) + (((float) view.getWidth()) * this.a.d);
        float width2 = ((float) this.b) + (((float) view.getWidth()) * this.a.e);
        if (((float) i) <= width) {
            ViewCompat.setAlpha(view, 1.0f);
        } else if (((float) i) >= width2) {
            ViewCompat.setAlpha(view, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        } else {
            ViewCompat.setAlpha(view, SwipeDismissBehavior.a(1.0f - SwipeDismissBehavior.a(width, width2, (float) i)));
        }
    }
}
