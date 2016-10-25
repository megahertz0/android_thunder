package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.View;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: BottomSheetBehavior.java
final class e extends Callback {
    final /* synthetic */ BottomSheetBehavior a;

    e(BottomSheetBehavior bottomSheetBehavior) {
        this.a = bottomSheetBehavior;
    }

    public final boolean tryCaptureView(View view, int i) {
        if (this.a.g == 1) {
            return false;
        }
        if (this.a.r) {
            return false;
        }
        if (this.a.g == 3 && this.a.p == i) {
            View view2 = (View) this.a.n.get();
            if (view2 != null && ViewCompat.canScrollVertically(view2, -1)) {
                return false;
            }
        }
        return this.a.m != null && this.a.m.get() == view;
    }

    public final void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
        this.a.b();
    }

    public final void onViewDragStateChanged(int i) {
        if (i == 1) {
            this.a.a(1);
        }
    }

    public final void onViewReleased(View view, float f, float f2) {
        int g;
        int i = XZBDevice.DOWNLOAD_LIST_FAILED;
        if (f2 < 0.0f) {
            g = this.a.d;
        } else if (this.a.f && this.a.a(view, f2)) {
            g = this.a.l;
            i = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        } else if (f2 == 0.0f) {
            int top = view.getTop();
            if (Math.abs(top - this.a.d) < Math.abs(top - this.a.e)) {
                g = this.a.d;
            } else {
                g = this.a.e;
                i = 4;
            }
        } else {
            g = this.a.e;
            i = 4;
        }
        if (this.a.h.settleCapturedViewAt(view.getLeft(), g)) {
            this.a.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            ViewCompat.postOnAnimation(view, new b(view, i));
            return;
        }
        this.a.a(i);
    }

    public final int clampViewPositionVertical(View view, int i, int i2) {
        return ad.a(i, this.a.d, this.a.f ? this.a.l : this.a.e);
    }

    public final int clampViewPositionHorizontal(View view, int i, int i2) {
        return view.getLeft();
    }

    public final int getViewVerticalDragRange(View view) {
        return this.a.f ? this.a.l - this.a.d : this.a.e - this.a.d;
    }
}
