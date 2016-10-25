package com.xunlei.downloadprovider.download.taskDetail;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.d.c;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.taskDetail.bt.BtTaskItemFileInfo;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.h.b;
import com.xunlei.downloadprovider.util.g;
import java.io.File;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadTaskDetailBtListFragment.java
final class ag implements OnItemClickListener {
    final /* synthetic */ DownloadTaskDetailBtListFragment a;

    ag(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment) {
        this.a = downloadTaskDetailBtListFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) this.a.x.getItem(i);
        if (i != 0 && i != 1 && btTaskItemFileInfo != null) {
            if (this.a.g) {
                b bVar = (b) view.getTag();
                if (btTaskItemFileInfo.isSelected) {
                    btTaskItemFileInfo.isSelected = false;
                    bVar.g.setImageResource(R.drawable.big_unselected);
                } else {
                    btTaskItemFileInfo.isSelected = true;
                    bVar.g.setImageResource(R.drawable.big_selected);
                }
                if (this.a.b != null) {
                    this.a.b.a(this.a.a());
                    return;
                }
                return;
            }
            boolean d = DownloadTaskDetailBtListFragment.e(btTaskItemFileInfo);
            boolean b = DownloadTaskDetailBtListFragment.b(btTaskItemFileInfo);
            boolean b2 = c.b(btTaskItemFileInfo.mLocalFileName);
            if (d && !b2) {
                return;
            }
            if (d || b) {
                DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment = this.a;
                if (btTaskItemFileInfo.mTaskStatus != 8) {
                    Object obj = btTaskItemFileInfo.mTitle;
                    if (TextUtils.isEmpty(obj) || XLFileTypeUtil.a(obj) != EFileCategoryType.E_VIDEO_CATEGORY) {
                        d = false;
                    } else {
                        d = true;
                    }
                    if (d) {
                        a.a("dl_bxbb", n.e(downloadTaskDetailBtListFragment.d), n.c(downloadTaskDetailBtListFragment.d) ? 1 : 0);
                        com.xunlei.downloadprovider.download.a.a.a(downloadTaskDetailBtListFragment.getActivity(), btTaskItemFileInfo.mLocalFileName, btTaskItemFileInfo.mTitle, downloadTaskDetailBtListFragment.d.mTaskId, btTaskItemFileInfo.mBTSubIndex, btTaskItemFileInfo.mCID, btTaskItemFileInfo.mGCID, "download_bxbb");
                        return;
                    }
                    XLToast.a(downloadTaskDetailBtListFragment.getActivity(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u4e0b\u8f7d\u672a\u5b8c\u6210");
                    return;
                }
                String str = btTaskItemFileInfo.mLocalFileName;
                if (!new File(str).exists()) {
                    XLToast.a(downloadTaskDetailBtListFragment.getActivity(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u4e0d\u5b58\u5728");
                } else if (g.b(btTaskItemFileInfo.mTitle)) {
                    DownloadBtFileExplorerActivity.startSelf(downloadTaskDetailBtListFragment.getActivity(), Uri.fromFile(new File(str)).toString(), SpdyProtocol.PUBKEY_PSEQ_ADASH, null);
                } else {
                    b.a(str, null, true);
                }
            }
        }
    }
}
