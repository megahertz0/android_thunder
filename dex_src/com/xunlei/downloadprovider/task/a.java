package com.xunlei.downloadprovider.task;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.task.b;

// compiled from: ThunderTask.java
final class a implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ b b;
    final /* synthetic */ ThunderTask c;

    a(ThunderTask thunderTask, a aVar, b bVar) {
        this.c = thunderTask;
        this.a = aVar;
        this.b = bVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
        if (this.a.a == 0) {
            BrothersApplication.a(true);
            DownloadService.a().a(this.a.c, this.a.b, this.a.f, this.a.g, this.a.h, false, this.a.i.a, this.a.j, this.a.k, this.a.m, this.b);
        } else {
            BrothersApplication.a(true);
            if (this.a.d != null || this.a.e == null) {
                DownloadService.a().a(this.a.b, this.a.h, this.a.d, this.a.f, this.a.e, this.a.i.a, this.a.j, this.a.k, this.a.m);
            } else {
                DownloadService.a().a(this.a.b, this.a.h, this.a.e, this.a.f, this.a.i.a, this.a.j, this.a.k, this.a.m);
            }
        }
        this.c.onCreateTask(true, this.a.i.a);
    }
}
