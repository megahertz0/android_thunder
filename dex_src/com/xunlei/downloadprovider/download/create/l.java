package com.xunlei.downloadprovider.download.create;

import android.net.Uri;
import android.text.TextUtils;
import com.xunlei.downloadprovider.service.DownloadService;
import java.util.ArrayList;
import java.util.List;

// compiled from: DownloadBtFileExplorerActivity.java
final class l implements Runnable {
    final /* synthetic */ DownloadBtFileExplorerActivity a;

    l(DownloadBtFileExplorerActivity downloadBtFileExplorerActivity) {
        this.a = downloadBtFileExplorerActivity;
    }

    public final void run() {
        int i = 0;
        try {
            d dVar;
            List arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.a.mListView.getAdapter().getCount(); i2++) {
                c cVar = (c) this.a.mSeedInfos.get(i2);
                if (this.a.mSelected.contains(cVar) || this.a.isBtSubIndexDownloading(cVar.mFileIndex)) {
                    arrayList.add(Long.valueOf((long) cVar.mFileIndex));
                }
            }
            long[] jArr = new long[arrayList.size()];
            while (i < arrayList.size()) {
                jArr[i] = ((Long) arrayList.get(i)).longValue();
                i++;
            }
            if (this.a.mTaskId != -1) {
                dVar = new d(this.a.mTaskId, jArr, this.a.mBtTitle);
            } else {
                Uri data = this.a.getIntent().getData();
                String stringExtra = this.a.getIntent().getStringExtra(DownloadBtFileExplorerActivity.EXTRA_KEY_CREATE_ORIGIN_FROM);
                if (TextUtils.isEmpty(stringExtra)) {
                    stringExtra = "manual/manual_downloadedlist(bt)";
                }
                dVar = new d(data, jArr, this.a.mInfoHash, stringExtra, this.a.mBtTitle);
            }
            if (DownloadService.a() == null) {
                DownloadService.a(new u(dVar));
            } else {
                dVar.a(DownloadService.a());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
