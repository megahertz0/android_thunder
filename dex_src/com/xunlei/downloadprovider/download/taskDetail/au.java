package com.xunlei.downloadprovider.download.taskDetail;

import android.text.TextUtils;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovidershare.b;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.tdlive.R;
import java.util.Locale;

// compiled from: TaskDetailShareHelper.java
public final class au {
    public static ShareBean a(a aVar, String str) {
        int i;
        com.xunlei.downloadprovidershare.data.a aVar2 = new com.xunlei.downloadprovidershare.data.a();
        aVar2.a = aVar.mTitle;
        aVar2.b = aVar.mFileSize;
        aVar2.e = aVar.mUrl;
        aVar2.c = (int) aVar.mTaskId;
        aVar2.d = aVar.mAppName;
        aVar2.g = aVar.mCID;
        aVar2.h = aVar.mGCID;
        aVar2.i = com.umeng.a.d;
        aVar2.j = aVar.mRefUrl;
        new StringBuilder("refUrl:    ").append(aVar.mRefUrl);
        if (aVar.mUrl.toLowerCase(Locale.getDefault()).startsWith("thunder://")) {
            aVar2.f = aVar.mUrl;
        } else {
            aVar2.f = com.xunlei.downloadprovider.download.util.a.a((TaskRunningInfo) aVar);
        }
        String str2 = "http://h5.m.xunlei.com/h5/page/share-xunlei/index.html";
        CharSequence charSequence = r.c().n.b;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence charSequence2 = charSequence;
        }
        String str3 = aVar2.f;
        StringBuilder stringBuilder = new StringBuilder(str2);
        new StringBuilder().append(stringBuilder.toString()).append("  \n shareUrl ").append(str3);
        String str4 = aVar2.a;
        int lastIndexOf = str4.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
        if (lastIndexOf > 0) {
            str4 = str4.substring(0, lastIndexOf);
        }
        ShareBean shareBean = new ShareBean(str, stringBuilder.toString(), com.umeng.a.d, str4, com.umeng.a.d);
        shareBean.l = (long) aVar2.c;
        shareBean.j = aVar2;
        if (aVar.mTaskType == TaskType.BT) {
            i = R.drawable.share_ic_task_file_bt;
        } else if (aVar.mTaskType == TaskType.MAGNET) {
            i = R.drawable.share_ic_task_file_magnet;
        } else {
            i = aVar.mFileName != null ? b.a(aVar.mFileName.trim()) : 0;
        }
        shareBean.i = i;
        return shareBean;
    }
}
