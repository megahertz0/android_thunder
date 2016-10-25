package com.xunlei.downloadprovider.web.sniff.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class AnimProgressBar extends ProgressBar {
    private Handler a;
    private a b;
    private int c;
    private boolean d;

    private class a implements Runnable {
        int a;
        boolean b;
        private int d;
        private Handler e;
        private boolean f;

        public a(int i, int i2, Handler handler, boolean z) {
            this.a = i;
            this.d = i2;
            this.e = handler;
            this.b = false;
            this.f = z;
        }

        public final void run() {
            this.b = true;
            while (this.a < this.d) {
                if (this.e != null) {
                    this.a++;
                    this.e.post(new b(this));
                }
                try {
                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.b = false;
            if (this.f) {
                this.e.post(new c(this));
            } else {
                AnimProgressBar.this.a(AnimProgressBar.this.d);
            }
        }
    }

    public AnimProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AnimProgressBar(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.a = new Handler();
    }

    public void setProgress(int i) {
    }

    public void setMax(int i) {
    }

    private void setRealProgress(int i) {
        super.setProgress(i);
    }

    public void setSmoothProgress(int i) {
        a(i, false);
    }

    public final void a(int i, boolean z) {
        if (i >= 0 && i <= getMax()) {
            this.c = i;
            this.d = z;
            if (this.b == null || !this.b.b) {
                a(z);
            }
        }
    }

    private void a(boolean z) {
        int progress = getProgress();
        if (progress > this.c) {
            this.c = 0;
            super.setProgress(0);
        } else if (progress != this.c) {
            this.b = new a(progress, this.c, this.a, z);
            new Thread(this.b).start();
        } else if (z) {
            post(new a(this));
        }
    }
}
