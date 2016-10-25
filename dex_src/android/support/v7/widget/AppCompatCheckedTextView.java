package android.support.v7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] a;
    private r b;
    private aa c;

    static {
        a = new int[]{16843016};
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(cj.a(context), attributeSet, i);
        this.c = aa.a((TextView) this);
        this.c.a(attributeSet, i);
        this.c.a();
        this.b = r.a();
        cm a = cm.a(getContext(), attributeSet, a, i);
        setCheckMarkDrawable(a.a(0));
        a.a.recycle();
    }

    public void setCheckMarkDrawable(int i) {
        if (this.b != null) {
            setCheckMarkDrawable(this.b.a(getContext(), i, false));
        } else {
            super.setCheckMarkDrawable(i);
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.c != null) {
            this.c.a(context, i);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.c != null) {
            this.c.a();
        }
    }
}
