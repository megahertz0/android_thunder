package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.play.view.u.a;
import com.xunlei.tdlive.util.h;

// compiled from: PlayerInfoWindowHelper.java
class x implements OnClickListener {
    final /* synthetic */ u a;

    x(u uVar) {
        this.a = uVar;
    }

    public void onClick(View view) {
        Object e = this.a.e();
        if (e instanceof a) {
            a aVar = (a) e;
            Context context = (Context) this.a.b.get();
            if (context != null) {
                h.a(context, aVar.b, aVar.c, true, aVar.a);
            }
        }
    }
}
