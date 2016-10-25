package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.taskDetail.bt.BtTaskItemFileInfo;

// compiled from: DownloadTaskDetailBtListFragment.java
final class ah implements OnItemLongClickListener {
    final /* synthetic */ DownloadTaskDetailBtListFragment a;

    ah(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment) {
        this.a = downloadTaskDetailBtListFragment;
    }

    public final boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!this.a.g) {
            BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) this.a.x.getItem(i);
            if (!DownloadTaskDetailBtListFragment.a(this.a, i)) {
                return false;
            }
            b bVar = (b) view.getTag();
            btTaskItemFileInfo.isSelected = true;
            bVar.g.setImageResource(R.drawable.big_selected);
            if (this.a.b != null) {
                this.a.b.b();
            }
        }
        return true;
    }
}
