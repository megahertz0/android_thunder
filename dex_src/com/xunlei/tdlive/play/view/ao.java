package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.play.view.ah.a;
import com.xunlei.tdlive.util.h;

// compiled from: UserInfoWindowHelper.java
class ao implements OnClickListener {
    final /* synthetic */ ah a;

    ao(ah ahVar) {
        this.a = ahVar;
    }

    public void onClick(View view) {
        Context context = (Context) this.a.b.get();
        if (context != null) {
            a aVar = (a) this.a.e();
            h.a(context, aVar.f, aVar.b, aVar.i, aVar.j);
        }
    }
}
