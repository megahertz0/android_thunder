package com.xunlei.downloadprovider.service;

import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: DownloadService.java
final class f implements Runnable {
    f() {
    }

    public final void run() {
        for (int i = 0; i < 3; i++) {
            LoginHelper.a().s();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
