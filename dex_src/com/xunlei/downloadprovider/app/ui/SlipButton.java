package com.xunlei.downloadprovider.app.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SlipButton extends ImageView implements OnClickListener {
    public a a;
    private boolean b;
    private boolean c;
    private float d;
    private Bitmap e;
    private Bitmap f;

    public static interface a {
        void a(View view, boolean z);
    }

    public SlipButton(Context context) {
        super(context);
        this.b = false;
        this.c = false;
        this.a = null;
        a();
    }

    public SlipButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
        this.c = false;
        this.a = null;
        a();
    }

    public final void a(boolean z, boolean z2) {
        boolean z3 = this.b;
        this.b = z;
        if (this.b) {
            this.d = (float) this.e.getWidth();
            setImageResource(2130839051);
        } else {
            this.d = 0.0f;
            setImageResource(2130839050);
        }
        invalidate();
        if (z2 && this.a != null && z3 != z) {
            this.a.a(this, z);
        }
    }

    public boolean getSwitchState() {
        new StringBuilder("get state : ").append(this.b);
        return this.b;
    }

    private void a() {
        this.e = BitmapFactory.decodeResource(getResources(), 2130839051);
        this.f = BitmapFactory.decodeResource(getResources(), 2130839050);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.b) {
            this.d = (float) this.e.getWidth();
        } else {
            this.d = 0.0f;
        }
        invalidate();
    }

    public void onClick(View view) {
        boolean z = this.b;
        if (this.b) {
            this.b = false;
            setImageResource(2130839051);
        } else {
            this.b = true;
            setImageResource(2130839051);
        }
        if (this.a != null && z != this.b) {
            new StringBuilder("state changed to : ").append(this.b);
            this.a.a(this, this.b);
        }
    }
}
