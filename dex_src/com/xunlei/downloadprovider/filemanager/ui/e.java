package com.xunlei.downloadprovider.filemanager.ui;

import java.io.File;
import java.io.FileFilter;

// compiled from: FileManagerDirView.java
final class e implements FileFilter {
    final /* synthetic */ FileManagerDirView a;

    e(FileManagerDirView fileManagerDirView) {
        this.a = fileManagerDirView;
    }

    public final boolean accept(File file) {
        if (file.getName().startsWith(".")) {
            return false;
        }
        return !FileManagerDirView.a(this.a) || file.isDirectory();
    }
}
