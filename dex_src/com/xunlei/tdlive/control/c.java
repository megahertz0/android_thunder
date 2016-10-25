package com.xunlei.tdlive.control;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

// compiled from: CountDownAnim.java
public class c {
    private TextView a;
    private View b;
    private Animation c;
    private a d;

    // compiled from: CountDownAnim.java
    public static interface a {
        void a();
    }

    public void a(TextView textView, View view, a aVar) {
        if (textView != null) {
            this.a = textView;
            this.b = view;
            this.d = aVar;
            b();
        }
    }

    private Animation a() {
        if (this.c == null) {
            this.c = new ScaleAnimation(1.0f, 0.2f, 1.0f, 0.2f, 1, 0.5f, 1, 0.5f);
            this.c.setDuration(1000);
        }
        return this.c;
    }

    private void b() {
        this.b.setVisibility(0);
        this.a.setText(com.xunlei.analytics.b.c.c);
        Animation a = a();
        a.setAnimationListener(new d(this));
        this.a.startAnimation(a);
    }

    private void c() {
        this.a.setText(com.xunlei.analytics.b.c.e);
        Animation a = a();
        a.setDuration(1000);
        a.setAnimationListener(new e(this));
        this.a.startAnimation(a);
    }

    private void d() {
        this.a.setText(com.xunlei.analytics.b.c.f);
        Animation a = a();
        a.setDuration(1000);
        a.setAnimationListener(new f(this));
        this.a.startAnimation(a);
    }
}
