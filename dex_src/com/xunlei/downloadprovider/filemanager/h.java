package com.xunlei.downloadprovider.filemanager;

import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.filemanager.model.i;
import com.xunlei.downloadprovider.filemanager.ui.FileManagerDirView.d;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;

// compiled from: FileManagerDirActivity.java
final class h implements d {
    final /* synthetic */ FileManagerDirActivity a;

    h(FileManagerDirActivity fileManagerDirActivity) {
        this.a = fileManagerDirActivity;
    }

    public final boolean a(int i) {
        this.a.x = i;
        EFileCategoryType eFileCategoryType = ((i) this.a.j.getData().get(i)).j;
        eFileCategoryType = EFileCategoryType.E_XLDIR_CATEGORY;
        this.a.a(this.a.z);
        StatReporter.reportClick(XLErrorCode.ALI_AUTH_USER_CANCLE, "longClick", this.a.d());
        return true;
    }
}
