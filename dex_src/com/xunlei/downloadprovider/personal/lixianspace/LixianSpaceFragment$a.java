package com.xunlei.downloadprovider.personal.lixianspace;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.xllib.R;

public class LixianSpaceFragment$a {
    public View a;
    public ImageView b;
    public TextView c;
    public TextView d;
    public View e;
    public TextView f;
    public View g;
    public ImageView h;
    public View i;
    public RelativeLayout j;
    public TextView k;
    public View l;
    public ImageView m;
    public View n;
    public TextView o;

    public LixianSpaceFragment$a() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
    }

    public final void a(String str, int i) {
        if (this.f != null) {
            Drawable drawable;
            this.f.setText(str);
            View view = this.f;
            if (i == -1) {
                drawable = null;
            } else if (VERSION.SDK_INT >= 21) {
                drawable = view.getResources().getDrawable(i, null);
            } else {
                drawable = view.getResources().getDrawable(i);
            }
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                this.f.setPadding(g.a(this.f.getContext(), 8.0f), this.f.getPaddingTop(), 0, this.f.getPaddingBottom());
                this.f.setGravity(8388627);
                this.f.setCompoundDrawables(drawable, null, null, null);
                return;
            }
            this.f.setPadding(0, this.f.getPaddingTop(), 0, this.f.getPaddingBottom());
            this.f.setGravity(R.styleable.Toolbar_maxButtonHeight);
            this.f.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }
}
