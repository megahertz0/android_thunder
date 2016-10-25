package com.xunlei.downloadprovider.download.create;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

// compiled from: DownloadBtFileExplorerActivity.java
final class p implements OnItemClickListener {
    final /* synthetic */ DownloadBtFileExplorerActivity a;

    p(DownloadBtFileExplorerActivity downloadBtFileExplorerActivity) {
        this.a = downloadBtFileExplorerActivity;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object obj = null;
        c cVar = (c) this.a.mSeedInfos.get(i);
        int i2 = cVar.mFileIndex;
        for (int i3 = 0; i3 < this.a.mSelected.size(); i3++) {
            if (((c) this.a.mSelected.get(i3)).mFileIndex == i2) {
                this.a.mSelected.remove(cVar);
                obj = 1;
                break;
            }
        }
        if (obj == null) {
            this.a.mSelected.add(cVar);
        }
        this.a.updateStorageTxt();
        if (this.a.mAdapter != null) {
            this.a.mAdapter.notifyDataSetChanged();
        }
    }
}
