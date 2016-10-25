package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;

// compiled from: XZBTaskInfoDetailFragment.java
final class aw implements OnClickListener {
    final /* synthetic */ XZBTaskInfoDetailFragment a;

    aw(XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment) {
        this.a = xZBTaskInfoDetailFragment;
    }

    public final void onClick(View view) {
        if (XZBTaskInfoDetailFragment.d(this.a) != null) {
            XZBTaskInfoDetailFragment.d(this.a).a();
        }
        TextView textView = (TextView) view;
        textView.setText("\u6062\u590d\u4e2d");
        textView.setClickable(false);
        List arrayList = new ArrayList();
        if (XZBTaskInfoDetailFragment.c(this.a) != null) {
            arrayList.add(XZBTaskInfoDetailFragment.c(this.a));
        }
        XZBTaskInfoDetailFragment.e(this.a).c.a(arrayList, BuildConfig.VERSION_NAME, new ax(this, textView));
    }
}
