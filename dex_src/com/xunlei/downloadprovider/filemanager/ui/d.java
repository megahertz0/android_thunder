package com.xunlei.downloadprovider.filemanager.ui;

import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.filemanager.model.i;
import java.util.Comparator;

// compiled from: FileManagerDirView.java
final class d implements Comparator<i> {
    final /* synthetic */ FileManagerDirView a;

    d(FileManagerDirView fileManagerDirView) {
        this.a = fileManagerDirView;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        i iVar = (i) obj;
        i iVar2 = (i) obj2;
        if (iVar == null || iVar2 == null) {
            return iVar == null ? -1 : 1;
        } else {
            if (iVar.j == EFileCategoryType.E_XLFILE_UPPER || iVar2.j == EFileCategoryType.E_XLFILE_UPPER) {
                return 1;
            }
            boolean g = iVar.g();
            boolean g2 = iVar2.g();
            if (g || g2) {
                if (!g && g2) {
                    return 1;
                }
                if (g && !g2) {
                    return -1;
                }
            }
            return iVar.a().compareToIgnoreCase(iVar2.a());
        }
    }
}
