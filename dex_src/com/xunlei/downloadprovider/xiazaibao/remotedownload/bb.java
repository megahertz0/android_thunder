package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;

// compiled from: XZBTaskInfoDetailFragment.java
final class bb implements OnClickListener {
    final /* synthetic */ XZBTaskInfoDetailFragment a;

    bb(XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment) {
        this.a = xZBTaskInfoDetailFragment;
    }

    public final void onClick(View view) {
        XZBTaskInfoDetailFragment.b(this.a).dismiss();
        List arrayList = new ArrayList();
        arrayList.add(XZBTaskInfoDetailFragment.c(this.a));
        if (XZBTaskInfoDetailFragment.d(this.a) != null) {
            XZBTaskInfoDetailFragment.d(this.a).a();
        }
        XZBTaskInfoDetailFragment.e(this.a).c.a(arrayList, BuildConfig.VERSION_NAME, new bc(this));
    }
}
