package com.xunlei.downloadprovider.ad.home.a;

import android.content.Context;
import android.os.Handler;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.ui.n;

// compiled from: RemoveItemExecutor.java
public final class m extends a {
    n e;

    public m(Context context, a aVar, n nVar) {
        super(context, aVar);
        this.e = nVar;
    }

    public final void b() {
        new Handler().postDelayed(new n(this), 500);
    }
}
