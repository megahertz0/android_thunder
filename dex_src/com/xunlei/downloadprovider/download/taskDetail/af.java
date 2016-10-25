package com.xunlei.downloadprovider.download.taskDetail;

import com.xunlei.downloadprovider.d.c;
import com.xunlei.downloadprovider.download.taskDetail.bt.BtTaskItemFileInfo;
import com.xunlei.downloadprovider.service.downloads.task.a.k.a;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: DownloadTaskDetailBtListFragment.java
final class af extends a<ArrayList<BtTaskItemFileInfo>> {
    final /* synthetic */ DownloadTaskDetailBtListFragment a;

    af(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment, ArrayList arrayList) {
        this.a = downloadTaskDetailBtListFragment;
        super(arrayList);
    }

    public final /* synthetic */ void a(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        if (!(arrayList == null || arrayList.isEmpty())) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) it.next();
                btTaskItemFileInfo.mIsFileMissing = false;
                if (btTaskItemFileInfo.mTaskStatus == 8 && !c.b(btTaskItemFileInfo.mLocalFileName)) {
                    btTaskItemFileInfo.mIsFileMissing = true;
                }
                if (btTaskItemFileInfo.mFileCategoryType == null) {
                    btTaskItemFileInfo.mFileCategoryType = DownloadTaskDetailBtListFragment.c(btTaskItemFileInfo);
                }
            }
        }
        this.a.B.sendEmptyMessageDelayed(0, 2000);
    }
}
