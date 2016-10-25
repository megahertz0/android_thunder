package android.support.v4.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public final class ScrollerCompat {
    static final int CHASE_FRAME_TIME = 16;
    private static final String TAG = "ScrollerCompat";
    ScrollerCompatImpl mImpl;
    Object mScroller;

    static interface ScrollerCompatImpl {
        void abortAnimation(Object obj);

        boolean computeScrollOffset(Object obj);

        Object createScroller(Context context, Interpolator interpolator);

        void fling(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

        void fling(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

        float getCurrVelocity(Object obj);

        int getCurrX(Object obj);

        int getCurrY(Object obj);

        int getFinalX(Object obj);

        int getFinalY(Object obj);

        boolean isFinished(Object obj);

        boolean isOverScrolled(Object obj);

        void notifyHorizontalEdgeReached(Object obj, int i, int i2, int i3);

        void notifyVerticalEdgeReached(Object obj, int i, int i2, int i3);

        boolean springBack(Object obj, int i, int i2, int i3, int i4, int i5, int i6);

        void startScroll(Object obj, int i, int i2, int i3, int i4);

        void startScroll(Object obj, int i, int i2, int i3, int i4, int i5);
    }

    static class ScrollerCompatImplBase implements ScrollerCompatImpl {
        ScrollerCompatImplBase() {
        }

        public Object createScroller(Context context, Interpolator interpolator) {
            return interpolator != null ? new Scroller(context, interpolator) : new Scroller(context);
        }

        public boolean isFinished(Object obj) {
            return ((Scroller) obj).isFinished();
        }

        public int getCurrX(Object obj) {
            return ((Scroller) obj).getCurrX();
        }

        public int getCurrY(Object obj) {
            return ((Scroller) obj).getCurrY();
        }

        public float getCurrVelocity(Object obj) {
            return AutoScrollHelper.RELATIVE_UNSPECIFIED;
        }

        public boolean computeScrollOffset(Object obj) {
            return ((Scroller) obj).computeScrollOffset();
        }

        public void startScroll(Object obj, int i, int i2, int i3, int i4) {
            ((Scroller) obj).startScroll(i, i2, i3, i4);
        }

        public void startScroll(Object obj, int i, int i2, int i3, int i4, int i5) {
            ((Scroller) obj).startScroll(i, i2, i3, i4, i5);
        }

        public void fling(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ((Scroller) obj).fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void fling(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            ((Scroller) obj).fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void abortAnimation(Object obj) {
            ((Scroller) obj).abortAnimation();
        }

        public void notifyHorizontalEdgeReached(Object obj, int i, int i2, int i3) {
        }

        public void notifyVerticalEdgeReached(Object obj, int i, int i2, int i3) {
        }

        public boolean isOverScrolled(Object obj) {
            return false;
        }

        public int getFinalX(Object obj) {
            return ((Scroller) obj).getFinalX();
        }

        public int getFinalY(Object obj) {
            return ((Scroller) obj).getFinalY();
        }

        public boolean springBack(Object obj, int i, int i2, int i3, int i4, int i5, int i6) {
            return false;
        }
    }

    static class ScrollerCompatImplGingerbread implements ScrollerCompatImpl {
        ScrollerCompatImplGingerbread() {
        }

        public Object createScroller(Context context, Interpolator interpolator) {
            return ScrollerCompatGingerbread.createScroller(context, interpolator);
        }

        public boolean isFinished(Object obj) {
            return ScrollerCompatGingerbread.isFinished(obj);
        }

        public int getCurrX(Object obj) {
            return ScrollerCompatGingerbread.getCurrX(obj);
        }

        public int getCurrY(Object obj) {
            return ScrollerCompatGingerbread.getCurrY(obj);
        }

        public float getCurrVelocity(Object obj) {
            return AutoScrollHelper.RELATIVE_UNSPECIFIED;
        }

        public boolean computeScrollOffset(Object obj) {
            return ScrollerCompatGingerbread.computeScrollOffset(obj);
        }

        public void startScroll(Object obj, int i, int i2, int i3, int i4) {
            ScrollerCompatGingerbread.startScroll(obj, i, i2, i3, i4);
        }

        public void startScroll(Object obj, int i, int i2, int i3, int i4, int i5) {
            ScrollerCompatGingerbread.startScroll(obj, i, i2, i3, i4, i5);
        }

        public void fling(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ScrollerCompatGingerbread.fling(obj, i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void fling(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            ScrollerCompatGingerbread.fling(obj, i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
        }

        public void abortAnimation(Object obj) {
            ScrollerCompatGingerbread.abortAnimation(obj);
        }

        public void notifyHorizontalEdgeReached(Object obj, int i, int i2, int i3) {
            ScrollerCompatGingerbread.notifyHorizontalEdgeReached(obj, i, i2, i3);
        }

        public void notifyVerticalEdgeReached(Object obj, int i, int i2, int i3) {
            ScrollerCompatGingerbread.notifyVerticalEdgeReached(obj, i, i2, i3);
        }

        public boolean isOverScrolled(Object obj) {
            return ScrollerCompatGingerbread.isOverScrolled(obj);
        }

        public int getFinalX(Object obj) {
            return ScrollerCompatGingerbread.getFinalX(obj);
        }

        public int getFinalY(Object obj) {
            return ScrollerCompatGingerbread.getFinalY(obj);
        }

        public boolean springBack(Object obj, int i, int i2, int i3, int i4, int i5, int i6) {
            return ScrollerCompatGingerbread.springBack(obj, i, i2, i3, i4, i5, i6);
        }
    }

    static class ScrollerCompatImplIcs extends ScrollerCompatImplGingerbread {
        ScrollerCompatImplIcs() {
        }

        public float getCurrVelocity(Object obj) {
            return ScrollerCompatIcs.getCurrVelocity(obj);
        }
    }

    public static ScrollerCompat create(Context context) {
        return create(context, null);
    }

    public static ScrollerCompat create(Context context, Interpolator interpolator) {
        return new ScrollerCompat(VERSION.SDK_INT, context, interpolator);
    }

    private ScrollerCompat(int i, Context context, Interpolator interpolator) {
        if (i >= 14) {
            this.mImpl = new ScrollerCompatImplIcs();
        } else if (i >= 9) {
            this.mImpl = new ScrollerCompatImplGingerbread();
        } else {
            this.mImpl = new ScrollerCompatImplBase();
        }
        this.mScroller = this.mImpl.createScroller(context, interpolator);
    }

    public final boolean isFinished() {
        return this.mImpl.isFinished(this.mScroller);
    }

    public final int getCurrX() {
        return this.mImpl.getCurrX(this.mScroller);
    }

    public final int getCurrY() {
        return this.mImpl.getCurrY(this.mScroller);
    }

    public final int getFinalX() {
        return this.mImpl.getFinalX(this.mScroller);
    }

    public final int getFinalY() {
        return this.mImpl.getFinalY(this.mScroller);
    }

    public final float getCurrVelocity() {
        return this.mImpl.getCurrVelocity(this.mScroller);
    }

    public final boolean computeScrollOffset() {
        return this.mImpl.computeScrollOffset(this.mScroller);
    }

    public final void startScroll(int i, int i2, int i3, int i4) {
        this.mImpl.startScroll(this.mScroller, i, i2, i3, i4);
    }

    public final void startScroll(int i, int i2, int i3, int i4, int i5) {
        this.mImpl.startScroll(this.mScroller, i, i2, i3, i4, i5);
    }

    public final void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.mImpl.fling(this.mScroller, i, i2, i3, i4, i5, i6, i7, i8);
    }

    public final void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.mImpl.fling(this.mScroller, i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    public final boolean springBack(int i, int i2, int i3, int i4, int i5, int i6) {
        return this.mImpl.springBack(this.mScroller, i, i2, i3, i4, i5, i6);
    }

    public final void abortAnimation() {
        this.mImpl.abortAnimation(this.mScroller);
    }

    public final void notifyHorizontalEdgeReached(int i, int i2, int i3) {
        this.mImpl.notifyHorizontalEdgeReached(this.mScroller, i, i2, i3);
    }

    public final void notifyVerticalEdgeReached(int i, int i2, int i3) {
        this.mImpl.notifyVerticalEdgeReached(this.mScroller, i, i2, i3);
    }

    public final boolean isOverScrolled() {
        return this.mImpl.isOverScrolled(this.mScroller);
    }
}
