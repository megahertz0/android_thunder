package com.xunlei.downloadprovider.model;

import java.io.File;

// compiled from: DownloadEnginDbUpdate.java
public final class f extends Thread {
    final /* synthetic */ e a;

    public f(e eVar) {
        this.a = eVar;
    }

    public final void run() {
        try {
            File file = new File("/mnt/sdcard/xunlei/ThunderDB/");
            File file2 = new File("/storage/sdcard0/xunlei/ThunderDB/");
            if (new File(file, "etm_task_store.db").exists()) {
                e.a(this.a, file);
            } else if (new File(file2, "etm_task_store.db").exists()) {
                e.a(this.a, file2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
