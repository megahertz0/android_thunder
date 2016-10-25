package com.baidu.mobads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

class ao extends RelativeLayout {
    private a a;

    public static interface a {
        @SuppressLint({"MissingSuperCall"})
        void a();

        void a(int i);

        void a(int i, int i2);

        void a(boolean z);

        boolean a(int i, KeyEvent keyEvent);

        void b();
    }

    public ao(Context context) {
        super(context);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && this.a != null) {
            this.a.a(getWidth(), getHeight());
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            this.a.b();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            this.a.a();
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.a != null) {
            this.a.a(z);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.a != null) {
            return this.a.a(i, keyEvent);
        }
        super.onKeyDown(i, keyEvent);
        return false;
    }
}
