package com.xunlei.downloadprovider.util;

import android.content.pm.PackageInfo;
import com.xunlei.downloadprovider.a.c;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.downloadprovider.util.h.a;
import java.io.File;

// compiled from: FileManager.java
public final class i extends a {
    public String a;
    final /* synthetic */ h b;

    public i(h hVar, String str) {
        this.b = hVar;
        super(str);
    }

    public final boolean a(File file) {
        if (!c.b(BrothersApplication.a, file.getAbsolutePath())) {
            return false;
        }
        PackageInfo packageArchiveInfo = BrothersApplication.a.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1);
        if (packageArchiveInfo == null) {
            return false;
        }
        return this.a.equals(packageArchiveInfo.packageName);
    }

    public final void b(File file) {
        d.a();
        d.b(file.getAbsolutePath());
    }
}
