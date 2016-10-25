package android.support.design.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.util.AttributeSet;
import android.view.View;

class ViewOffsetBehavior<V extends View> extends Behavior<V> {
    private bp a;
    private int b;
    private int c;

    public ViewOffsetBehavior() {
        this.b = 0;
        this.c = 0;
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        this.c = 0;
    }

    public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
        c(coordinatorLayout, v, i);
        if (this.a == null) {
            this.a = new bp(v);
        }
        this.a.a();
        if (this.b != 0) {
            this.a.a(this.b);
            this.b = 0;
        }
        if (this.c != 0) {
            bp bpVar = this.a;
            int i2 = this.c;
            if (bpVar.b != i2) {
                bpVar.b = i2;
                bpVar.b();
            }
            this.c = 0;
        }
        return true;
    }

    protected void c(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.a((View) v, i);
    }

    public boolean a(int i) {
        if (this.a != null) {
            return this.a.a(i);
        }
        this.b = i;
        return false;
    }

    public int c() {
        return this.a != null ? this.a.a : 0;
    }
}
