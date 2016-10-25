package com.xunlei.downloadprovider.download.center;

import android.content.Context;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import com.xunlei.download.DownloadManager.Query;
import com.xunlei.downloadprovider.download.tasklist.a.h;
import com.xunlei.downloadprovider.service.downloads.kernel.b;
import com.xunlei.downloadprovider.service.downloads.kernel.c;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;

class DownloadCenterActivityFragment$c {
    h a;
    boolean b;
    int c;
    boolean d;
    final /* synthetic */ DownloadCenterActivityFragment e;
    private LoaderCallbacks f;

    public final LoaderCallbacks a() {
        if (this.f == null) {
            this.f = new aj(this);
        }
        return this.f;
    }

    public DownloadCenterActivityFragment$c(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.e = downloadCenterActivityFragment;
        this.b = false;
        this.c = 0;
        this.d = false;
        this.a = h.a();
    }

    public static CursorLoader a(Context context) {
        Query query = new Query();
        query.setFilterByStatus(R.styleable.AppCompatTheme_actionModeCloseDrawable);
        query.orderBy("create_time", SimpleLog.LOG_LEVEL_DEBUG);
        try {
            return new b(context, c.a(context).getDownloadUri(), query.getProjection(), query.getSelection(), query.getSelectionArgs(), query.getSortOrder());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void b() {
        if (this.c <= 0) {
            synchronized (this) {
                this.c = 1;
                new Thread(new ak(this)).start();
            }
            return;
        }
        this.c++;
    }
}
