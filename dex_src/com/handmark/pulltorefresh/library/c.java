package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import org.android.spdy.SpdyAgent;

@TargetApi(9)
// compiled from: OverscrollHelper.java
public final class c {

    // compiled from: OverscrollHelper.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Orientation.values().length];
            try {
                a[Orientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Orientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static void a(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, boolean z) {
        a(pullToRefreshBase, i, i2, i3, i4, 0, z);
    }

    public static void a(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, boolean z) {
        a(pullToRefreshBase, i, i2, i3, i4, i5, 0, 1.0f, z);
    }

    public static void a(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, int i6, float f, boolean z) {
        int scrollX;
        switch (AnonymousClass_1.a[pullToRefreshBase.getPullToRefreshScrollDirection().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                scrollX = pullToRefreshBase.getScrollX();
                break;
            default:
                scrollX = pullToRefreshBase.getScrollY();
                i2 = i4;
                i = i3;
                break;
        }
        if (pullToRefreshBase.j() && !pullToRefreshBase.k()) {
            Mode mode = pullToRefreshBase.getMode();
            if (mode.b() && !z && i != 0) {
                int i7 = i + i2;
                if (i7 < 0 - i6) {
                    if (mode.showHeaderLoadingLayout()) {
                        if (scrollX == 0) {
                            pullToRefreshBase.a(State.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.setScrollValue((int) (((float) (scrollX + i7)) * f));
                    }
                } else if (i7 > i5 + i6) {
                    if (mode.showFooterLoadingLayout()) {
                        if (scrollX == 0) {
                            pullToRefreshBase.a(State.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.setScrollValue((int) (((float) ((scrollX + i7) - i5)) * f));
                    }
                } else if (Math.abs(i7) <= i6 || Math.abs(i7 - i5) <= i6) {
                    pullToRefreshBase.a(State.RESET, new boolean[0]);
                }
            } else if (z && State.OVERSCROLLING == pullToRefreshBase.getState()) {
                pullToRefreshBase.a(State.RESET, new boolean[0]);
            }
        }
    }
}
