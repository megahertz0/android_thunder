package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.play.view.ah.a;

// compiled from: UserInfoWindowHelper.java
class an implements OnClickListener {
    final /* synthetic */ ah a;

    an(ah ahVar) {
        this.a = ahVar;
    }

    public void onClick(View view) {
        Context context = (Context) this.a.b.get();
        if (context != null) {
            a aVar = (a) this.a.e();
            ah.a(this.a, context, aVar.f, aVar.j, aVar.b);
        }
    }
}
