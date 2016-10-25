package com.xunlei.downloadprovider.service.downloads.task.a;

import android.database.Cursor;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import com.xunlei.common.yunbo.XLYunboMassage;

// compiled from: CoreTaskManager.java
final class c implements OnLoadCompleteListener<Cursor> {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final /* synthetic */ void onLoadComplete(Loader loader, Object obj) {
        int i = XLYunboMassage.MSG_TASKCREATED;
        Cursor cursor = (Cursor) obj;
        a aVar = this.a;
        a aVar2 = this.a;
        int i2 = aVar2.i + 1;
        aVar2.i = i2;
        if (i2 <= 10000) {
            i = this.a.i;
        }
        aVar.i = i;
        a.a(this.a, cursor);
        if (!this.a.h) {
            this.a.d();
        }
        this.a.h = true;
    }
}
