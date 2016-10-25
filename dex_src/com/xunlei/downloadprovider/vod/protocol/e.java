package com.xunlei.downloadprovider.vod.protocol;

import android.text.TextUtils;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.task.d;
import java.io.File;

// compiled from: DownloadVodUtil.java
final class e implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ TaskInfo b;
    final /* synthetic */ b c;

    e(b bVar, int i, TaskInfo taskInfo) {
        this.c = bVar;
        this.a = i;
        this.b = taskInfo;
    }

    public final void run() {
        if (b.b(this.c) != null) {
            b.b(this.c).c = -1;
            if (this.a == 0 && this.b != null) {
                d.a();
                Object c = d.c(this.b.mLocalFileName);
                if (TextUtils.isEmpty(c)) {
                    if (this.b.mTaskStatus == 4 || this.b.mTaskStatus == 16) {
                        d.a();
                        d.b(false, new long[]{this.b.mTaskId});
                    } else if (this.b.mTaskStatus == 8) {
                        try {
                            if (!new File(this.b.mLocalFileName).exists()) {
                                d.a();
                                d.a(false, new long[]{this.b.mTaskId});
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    d.a().b(this.b.mTaskId);
                    int i = 0;
                    while (i < 3) {
                        i++;
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        d.a();
                        Object c2 = d.c(this.b.mLocalFileName);
                        if (!TextUtils.isEmpty(c2)) {
                            b.b(this.c).c = 0;
                            b.b(this.c).b.b = c2;
                            b.b(this.c).b.c = this.b.mTaskId;
                            b.b(this.c).b.e = this.b.mCID;
                            b.b(this.c).b.f = this.b.mGCID;
                            b.b(this.c).b.g = this.b.mFileSize;
                            d.a().b(-1);
                            break;
                        }
                    }
                }
                b.b(this.c).c = 0;
                b.b(this.c).b.b = c;
            }
            b.a(this.c, b.b(this.c));
            b.b(this.c, null);
        }
    }
}
