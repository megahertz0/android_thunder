package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.h;
import android.support.v7.widget.RecyclerView.p;
import android.support.v7.widget.RecyclerView.p.a;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import org.android.spdy.SpdyAgent;

// compiled from: LinearSmoothScroller.java
public abstract class at extends p {
    private final float a;
    protected final LinearInterpolator b;
    protected final DecelerateInterpolator c;
    protected PointF d;
    protected int e;
    protected int f;

    public abstract PointF a(int i);

    public at(Context context) {
        this.b = new LinearInterpolator();
        this.c = new DecelerateInterpolator();
        this.e = 0;
        this.f = 0;
        this.a = 25.0f / ((float) context.getResources().getDisplayMetrics().densityDpi);
    }

    protected final void a(View view, a aVar) {
        int i = 1;
        int i2 = 0;
        int i3 = (this.d == null || this.d.x == 0.0f) ? 0 : this.d.x > 0.0f ? 1 : -1;
        h hVar = this.i;
        if (hVar == null || !hVar.e()) {
            i3 = 0;
        } else {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            i3 = a(h.e(view) - layoutParams.leftMargin, layoutParams.rightMargin + h.g(view), hVar.o(), hVar.y - hVar.q(), i3);
        }
        if (this.d == null || this.d.y == 0.0f) {
            i = 0;
        } else if (this.d.y <= 0.0f) {
            i = -1;
        }
        h hVar2 = this.i;
        if (hVar2 != null && hVar2.f()) {
            layoutParams = (LayoutParams) view.getLayoutParams();
            i2 = a(h.f(view) - layoutParams.topMargin, layoutParams.bottomMargin + h.h(view), hVar2.p(), hVar2.z - hVar2.r(), i);
        }
        int ceil = (int) Math.ceil(((double) b((int) Math.sqrt((double) ((i3 * i3) + (i2 * i2))))) / 0.3356d);
        if (ceil > 0) {
            aVar.a(-i3, -i2, ceil, this.c);
        }
    }

    protected final void a(int i, int i2, a aVar) {
        if (this.h.mLayout.n() == 0) {
            b();
            return;
        }
        this.e = a(this.e, i);
        this.f = a(this.f, i2);
        if (this.e == 0 && this.f == 0) {
            PointF a = a(this.g);
            if (a == null || (a.x == 0.0f && a.y == 0.0f)) {
                aVar.a = this.g;
                b();
                return;
            }
            double sqrt = Math.sqrt((double) ((a.x * a.x) + (a.y * a.y)));
            a.x = (float) (((double) a.x) / sqrt);
            a.y = (float) (((double) a.y) / sqrt);
            this.d = a;
            this.e = (int) (a.x * 10000.0f);
            this.f = (int) (a.y * 10000.0f);
            aVar.a((int) (((float) this.e) * 1.2f), (int) (((float) this.f) * 1.2f), (int) (((float) b(10000)) * 1.2f), this.b);
        }
    }

    protected final void a() {
        this.f = 0;
        this.e = 0;
        this.d = null;
    }

    private int b(int i) {
        return (int) Math.ceil((double) (((float) Math.abs(i)) * this.a));
    }

    private static int a(int i, int i2) {
        int i3 = i - i2;
        return i * i3 <= 0 ? 0 : i3;
    }

    private static int a(int i, int i2, int i3, int i4, int i5) {
        switch (i5) {
            case SniffingResourceGroup.PAGETYPE_NONE:
                return i3 - i;
            case SpdyAgent.ACCS_TEST_SERVER:
                int i6 = i3 - i;
                if (i6 > 0) {
                    return i6;
                }
                i6 = i4 - i2;
                return i6 >= 0 ? 0 : i6;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return i4 - i2;
            default:
                throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
    }
}
