package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import java.util.ArrayList;
import java.util.List;

// compiled from: RemoteDownloadTaskViewHolder.java
final class ai implements OnClickListener {
    final /* synthetic */ ac a;

    ai(ac acVar) {
        this.a = acVar;
    }

    public final void onClick(View view) {
        ac.g;
        ac.d(this.a);
        this.a.o.setText(R.string.download_item_task_status_wait_stop);
        this.a.t.setText(R.string.download_item_task_status_wait_stop);
        this.a.t.setEnabled(false);
        List arrayList = new ArrayList();
        arrayList.add(this.a.a);
        this.a.b.a(arrayList, "xzb_download_center", new aj(this));
    }
}
