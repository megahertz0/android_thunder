package com.xunlei.downloadprovider.download.create;

import android.annotation.SuppressLint;
import java.io.File;
import java.io.FileFilter;

// compiled from: CreateBtTask.java
final class a implements FileFilter {
    final /* synthetic */ CreateBtTask a;

    a(CreateBtTask createBtTask) {
        this.a = createBtTask;
    }

    @SuppressLint({"DefaultLocale"})
    public final boolean accept(File file) {
        String name = file.getName();
        if (file.isDirectory()) {
            return true;
        }
        return name.toLowerCase().endsWith(".torrent") && !name.toLowerCase().startsWith(".");
    }
}
