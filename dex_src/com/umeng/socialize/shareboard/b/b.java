package com.umeng.socialize.shareboard.b;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.utils.Log;
import com.xunlei.xiazaibao.BuildConfig;
import java.lang.reflect.Array;

// compiled from: UMActionFrame.java
public class b extends ViewGroup {
    private static final int a = 3;
    private static final int b = 1;
    private static final int c = 2;
    private int d;
    private int e;
    private int[][] f;
    private a g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private Context m;

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 4;
        this.e = 0;
        this.f = null;
        this.j = 0;
        this.k = -1;
        this.l = 2;
        this.m = null;
        this.j = context.getResources().getColor(ResContainer.get(context).color("umeng_socialize_grid_divider_line"));
        this.m = context;
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 4;
        this.e = 0;
        this.f = null;
        this.j = 0;
        this.k = -1;
        this.l = 2;
        this.m = null;
        this.j = context.getResources().getColor(ResContainer.get(context).color("umeng_socialize_grid_divider_line"));
        this.m = context;
    }

    public b(Context context) {
        super(context);
        this.d = 4;
        this.e = 0;
        this.f = null;
        this.j = 0;
        this.k = -1;
        this.l = 2;
        this.m = null;
        this.j = context.getResources().getColor(ResContainer.get(context).color("umeng_socialize_grid_divider_line"));
        this.m = context;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.g != null) {
            Context context = getContext();
            a(this.g.a());
            removeAllViews();
            int length = this.f.length;
            int length2 = this.f[0].length;
            int i5 = (this.i - ((length - 1) * this.l)) / length;
            int i6 = (this.h - ((length2 - 1) * this.l)) / length2;
            Object obj = null;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i7 < length2) {
                int i10;
                int i11 = 0;
                int i12 = i10;
                while (i11 < length) {
                    if (this.f[i11][i7] == 1) {
                        i10 = i12 + 1;
                        View a = this.g.a(i12, this);
                        LayoutParams layoutParams = a.getLayoutParams();
                        if (layoutParams == null) {
                            a.setLayoutParams(new LayoutParams(i5, i6));
                        } else {
                            layoutParams.height = i6;
                            layoutParams.width = i5;
                        }
                        Object obj2 = i11 == length + -1 ? b : null;
                        int i13 = (i11 * i5) + i9;
                        int i14 = i13 + i5;
                        int i15 = (i7 * i6) + i8;
                        int i16 = i15 + i6;
                        addView(a);
                        measureChild(a, i5, i6);
                        a.layout(i13, i15, i14, i16);
                        if (obj2 == null && this.f[i11 + 1][i7] == 2) {
                            a = new View(context);
                            a.setBackgroundColor(this.k);
                            addView(a);
                            a.layout(i13 + i5, i15, i3, i16);
                        }
                        a = new View(context);
                        if (obj2 == null) {
                            a.setBackgroundColor(this.j);
                            i12 = this.l + i9;
                        } else {
                            a.setBackgroundColor(this.k);
                            i12 = 0;
                        }
                        addView(a);
                        a.layout(i13 + i5, i15, this.l + i14, i16);
                        int i17 = i10;
                        i10 = i12;
                        i12 = i17;
                    } else {
                        i10 = i9;
                    }
                    i11++;
                    i9 = i10;
                }
                if (i7 > 0) {
                    obj = this.f[0][i7 + -1] == 1 ? b : null;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    View view = new View(context);
                    view.setBackgroundColor(this.j);
                    addView(view);
                    i9 = (i7 * i6) + i8;
                    view.layout(i, i9 - this.l, i3, i9);
                }
                i7++;
                i8 += this.l;
                i10 = i12;
                i9 = 0;
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.h = MeasureSpec.getSize(i2);
        this.i = MeasureSpec.getSize(i);
        setMeasuredDimension(this.i, this.h);
    }

    public void a(int i) {
        int length = this.f.length;
        int length2 = this.f[0].length;
        int i2 = length * length2;
        if (i > i2) {
            i = i2;
        }
        int i3 = i % length;
        int i4 = (i2 - i) - (i3 > 0 ? length - i3 : 0);
        int i5 = i4 + i;
        int i6 = 0;
        i2 = 0;
        while (i6 < length2) {
            i3 = i2;
            for (i2 = 0; i2 < length; i2++) {
                if (i3 >= i4 && i3 < i5) {
                    this.f[i2][i6] = 1;
                } else if (i3 >= i5) {
                    this.f[i2][i6] = 2;
                } else {
                    this.f[i2][i6] = 3;
                }
                i3++;
            }
            i6++;
            i2 = i3;
        }
    }

    public a a() {
        return this.g;
    }

    private void b() {
        if (this.m == null || this.g == null) {
            this.f = (int[][]) Array.newInstance(Integer.TYPE, new int[]{4, 2});
            return;
        }
        if (this.m.getResources().getConfiguration().orientation == 2) {
            this.d = 6;
        }
        int a = this.g.a();
        this.e = this.g.a() / this.d;
        if (a % this.d > 0) {
            this.e++;
        }
        Log.d(BuildConfig.VERSION_NAME, new StringBuilder("###### row = ").append(this.e).append(", column = ").append(this.d).toString());
        this.f = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.d, this.e});
    }

    public void a(a aVar) {
        this.g = aVar;
        b();
        requestLayout();
    }

    public void b(int i) {
        this.j = i;
    }

    public void c(int i) {
        this.k = i;
    }

    public void d(int i) {
        this.l = i;
    }

    public int e(int i) {
        return (((i - ((this.d - 1) * this.l)) / this.d) * this.e) + ((this.e - 1) * this.l);
    }
}
