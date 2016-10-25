package com.android.volley.toolbox;

import com.android.volley.toolbox.i.c;
import com.android.volley.toolbox.i.d;
import com.android.volley.w;

// compiled from: NetworkImageView.java
final class q implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ NetworkImageView b;

    q(NetworkImageView networkImageView, boolean z) {
        this.b = networkImageView;
        this.a = z;
    }

    public final void onErrorResponse(w wVar) {
        if (this.b.c != 0) {
            this.b.setImageResource(this.b.c);
        }
    }

    public final void a(c cVar, boolean z) {
        if (z && this.a) {
            this.b.post(new r(this, cVar));
        } else if (cVar.a != null) {
            this.b.setImageBitmap(cVar.a);
        } else if (this.b.b != 0) {
            this.b.setImageResource(this.b.b);
        }
    }
}
