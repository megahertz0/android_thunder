package com.xunlei.downloadprovider.service.downloads.task.a;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.Query;
import com.xunlei.downloadprovider.service.downloads.kernel.b;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: RunningTaskCursorLoader.java
public final class m {
    protected CursorLoader a;

    public final void a(Context context, Uri uri, OnLoadCompleteListener<Cursor> onLoadCompleteListener) {
        try {
            Query query = new Query();
            query.setFilterByStatus(R.styleable.AppCompatTheme_actionModeCloseDrawable);
            query.orderBy(DownloadManager.COLUMN_ID, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            this.a = new b(context, uri, query.getProjection(), query.getSelection(), query.getSelectionArgs(), query.getSortOrder());
            this.a.registerListener(0, onLoadCompleteListener);
            this.a.startLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a() {
        if (this.a != null) {
            this.a.stopLoading();
        }
    }
}
