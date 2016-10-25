package com.xunlei.downloadprovider.app.ui;

import java.io.File;
import java.util.Comparator;

// compiled from: FileManageView.java
final class b implements Comparator<File> {
    final /* synthetic */ FileManageView a;

    b(FileManageView fileManageView) {
        this.a = fileManageView;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        File file = (File) obj;
        File file2 = (File) obj2;
        if (file.isDirectory()) {
            return file2.isDirectory() ? 0 : -1;
        } else {
            if (file2.isDirectory()) {
                return 1;
            }
            if (file.length() > file2.length()) {
                return -1;
            }
            return file.length() < file2.length() ? 1 : 0;
        }
    }
}
