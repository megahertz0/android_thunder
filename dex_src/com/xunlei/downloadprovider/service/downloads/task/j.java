package com.xunlei.downloadprovider.service.downloads.task;

// compiled from: DownloadTaskRequest.java
public final class j implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Object b;
    final /* synthetic */ i c;

    public j(i iVar, int i, Object obj) {
        this.c = iVar;
        this.a = i;
        this.b = obj;
    }

    public final void run() {
        this.c.a(this.b);
    }
}
