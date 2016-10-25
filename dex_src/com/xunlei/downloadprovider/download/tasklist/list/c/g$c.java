package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.text.TextUtils;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;

// compiled from: TaskDownloadCardViewHolder.java
public class g$c {
    public static boolean a(TaskRunningInfo taskRunningInfo) {
        return b(taskRunningInfo) == EFileCategoryType.E_VIDEO_CATEGORY;
    }

    public static EFileCategoryType b(TaskRunningInfo taskRunningInfo) {
        if (taskRunningInfo == null) {
            return null;
        }
        if (!(taskRunningInfo instanceof a)) {
            return !TextUtils.isEmpty(taskRunningInfo.mLocalFileName) ? XLFileTypeUtil.a(taskRunningInfo.mLocalFileName) : XLFileTypeUtil.a(taskRunningInfo.mTitle);
        } else {
            a aVar = (a) taskRunningInfo;
            if (aVar.g != null && aVar.g != EFileCategoryType.E_OTHER_CATEGORY) {
                return aVar.g;
            }
            EFileCategoryType a;
            if (TextUtils.isEmpty(taskRunningInfo.mLocalFileName)) {
                a = XLFileTypeUtil.a(taskRunningInfo.mTitle);
            } else {
                a = XLFileTypeUtil.a(taskRunningInfo.mLocalFileName);
            }
            aVar.g = a;
            return a;
        }
    }
}
