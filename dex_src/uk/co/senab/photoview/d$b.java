package uk.co.senab.photoview;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import uk.co.senab.photoview.c.a;
import uk.co.senab.photoview.c.b;
import uk.co.senab.photoview.c.c;
import uk.co.senab.photoview.c.d;

// compiled from: PhotoViewAttacher.java
private class d$b implements Runnable {
    final d a;
    int b;
    int c;
    final /* synthetic */ d d;

    public d$b(d dVar, Context context) {
        d cVar;
        this.d = dVar;
        if (VERSION.SDK_INT < 9) {
            cVar = new c(context);
        } else if (VERSION.SDK_INT < 14) {
            cVar = new a(context);
        } else {
            cVar = new b(context);
        }
        this.a = cVar;
    }

    public final void run() {
        if (!this.a.c()) {
            View c = this.d.c();
            if (c != null && this.a.a()) {
                int d = this.a.d();
                int e = this.a.e();
                if (d.h()) {
                    uk.co.senab.photoview.b.a.a();
                    new StringBuilder("fling run(). CurrentX:").append(this.b).append(" CurrentY:").append(this.c).append(" NewX:").append(d).append(" NewY:").append(e);
                }
                d.b(this.d).postTranslate((float) (this.b - d), (float) (this.c - e));
                d.a(this.d, this.d.f());
                this.b = d;
                this.c = e;
                a.a(c, this);
            }
        }
    }
}
