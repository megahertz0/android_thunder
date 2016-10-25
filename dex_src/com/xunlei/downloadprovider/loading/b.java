package com.xunlei.downloadprovider.loading;

// compiled from: LoadingActivity.java
final class b implements Runnable {
    final /* synthetic */ LoadingActivity a;

    b(LoadingActivity loadingActivity) {
        this.a = loadingActivity;
    }

    public final void run() {
        if (LoadingActivity.a(this.a) != null) {
            LoadingActivity.c(this.a);
        } else {
            LoadingActivity.b(this.a);
        }
    }
}
