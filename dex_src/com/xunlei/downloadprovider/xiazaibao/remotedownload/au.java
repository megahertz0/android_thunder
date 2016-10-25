package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;

// compiled from: XZBTaskInfoDetailFragment.java
final class au implements OnClickListener {
    final /* synthetic */ XZBTaskInfoDetailFragment a;

    au(XZBTaskInfoDetailFragment xZBTaskInfoDetailFragment) {
        this.a = xZBTaskInfoDetailFragment;
    }

    public final void onClick(View view) {
        if (XZBTaskInfoDetailFragment.d(this.a) != null) {
            XZBTaskInfoDetailFragment.d(this.a).a();
        }
        TextView textView = (TextView) view;
        textView.setText(R.string.download_item_task_status_wait_stop);
        textView.setClickable(false);
        List arrayList = new ArrayList();
        if (XZBTaskInfoDetailFragment.c(this.a) != null) {
            arrayList.add(XZBTaskInfoDetailFragment.c(this.a));
        }
        XZBTaskInfoDetailFragment.e(this.a).c.a(arrayList, BuildConfig.VERSION_NAME, new av(this, textView));
    }
}
