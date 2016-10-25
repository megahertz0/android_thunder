package com.baidu.mobads.production.e;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.baidu.mobads.j.j;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class c implements OnTouchListener {
    int a;
    int b;
    int c;
    int d;
    final /* synthetic */ int e;
    final /* synthetic */ int f;
    final /* synthetic */ View g;
    final /* synthetic */ int h;
    final /* synthetic */ int i;
    final /* synthetic */ b j;

    c(b bVar, int i, int i2, View view, int i3, int i4) {
        this.j = bVar;
        this.e = i;
        this.f = i2;
        this.g = view;
        this.h = i3;
        this.i = i4;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        View a = this.j.z;
        try {
            int i;
            int i2;
            switch (motionEvent.getAction()) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    this.a = (int) motionEvent.getRawX();
                    this.b = (int) motionEvent.getRawY();
                    this.c = this.a;
                    this.d = this.b;
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    boolean z;
                    if (Math.abs(this.c - this.a) < 15) {
                        Math.abs(this.d - this.b);
                    }
                    if (a.getLeft() + (a.getWidth() / 2) < this.e / 2) {
                        i = 1;
                    } else {
                        z = false;
                    }
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = this.e - a.getWidth();
                    }
                    Animation translateAnimation = new TranslateAnimation((float) a.getLeft(), (float) i2, 0.0f, 0.0f);
                    translateAnimation.setDuration(500);
                    this.g.startAnimation(translateAnimation);
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.h, this.i);
                    if (z) {
                        layoutParams.leftMargin = 0;
                        layoutParams.topMargin = a.getTop();
                        this.g.setLayoutParams(layoutParams);
                    } else {
                        layoutParams.rightMargin = 0;
                        layoutParams.topMargin = a.getTop();
                        this.g.setLayoutParams(layoutParams);
                        new Handler().postDelayed(new d(this, layoutParams, a), 501);
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    i = ((int) motionEvent.getRawX()) - this.a;
                    int rawY = ((int) motionEvent.getRawY()) - this.b;
                    int left = this.j.z.getLeft() + i;
                    i2 = this.j.z.getTop() + rawY;
                    int right = this.j.z.getRight() + i;
                    i = this.j.z.getBottom() + rawY;
                    if (left < 0) {
                        right = this.j.z.getWidth() + 0;
                        left = 0;
                    }
                    if (right > this.e) {
                        right = this.e;
                        left = right - this.j.z.getWidth();
                    }
                    if (i2 < this.j.E) {
                        i2 = this.j.E;
                        i = this.j.z.getHeight() + i2;
                    }
                    if (i > this.f) {
                        i = this.f;
                        i2 = i - this.j.z.getHeight();
                    }
                    this.j.z.layout(left, i2, right, i);
                    this.a = (int) motionEvent.getRawX();
                    this.b = (int) motionEvent.getRawY();
                    break;
            }
        } catch (Throwable e) {
            j.a().e(e);
        }
        return false;
    }
}
