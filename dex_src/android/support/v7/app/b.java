package android.support.v7.app;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: AlertController.java
final class b implements OnClickListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        Message obtain;
        if (view == this.a.n && this.a.p != null) {
            obtain = Message.obtain(this.a.p);
        } else if (view == this.a.q && this.a.s != null) {
            obtain = Message.obtain(this.a.s);
        } else if (view != this.a.t || this.a.v == null) {
            obtain = null;
        } else {
            obtain = Message.obtain(this.a.v);
        }
        if (obtain != null) {
            obtain.sendToTarget();
        }
        this.a.M.obtainMessage(1, this.a.b).sendToTarget();
    }
}
