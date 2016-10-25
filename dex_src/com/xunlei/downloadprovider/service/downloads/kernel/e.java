package com.xunlei.downloadprovider.service.downloads.kernel;

import android.content.Context;
import android.database.Cursor;
import com.umeng.message.proguard.j;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.Query;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: DownloadLoader.java
public final class e {
    public final a a;
    public Context b;
    public DownloadManager c;

    public e() {
        this.a = new a();
    }

    public e(Context context, DownloadManager downloadManager) {
        this.a = new a();
        this.b = context;
        this.c = downloadManager;
    }

    public final List<Long> a() {
        List arrayList = new ArrayList();
        try {
            Query query = new Query();
            query.setFilterByStatus(R.styleable.AppCompatTheme_actionModeCloseDrawable);
            query.orderBy(j.g, SimpleLog.LOG_LEVEL_DEBUG);
            String[] projection = query.getProjection();
            String selection = query.getSelection();
            String[] selectionArgs = query.getSelectionArgs();
            String sortOrder = query.getSortOrder();
            Cursor query2 = this.b.getContentResolver().query(this.c.getDownloadUri(), projection, selection, selectionArgs, sortOrder);
            this.a.a(query2);
            if (query2 != null) {
                while (query2.moveToNext()) {
                    long j = (long) query2.getInt(this.a.a);
                    if (j != -1) {
                        arrayList.add(Long.valueOf(j));
                    }
                }
                query2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
