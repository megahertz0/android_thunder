package com.xunlei.downloadprovider.web.base.model;

import android.database.sqlite.SQLiteDatabase;
import com.xunlei.downloadprovider.c.a.b;

// compiled from: ShortMovieDetailDataLoader.java
final class s implements a<Long> {
    final /* synthetic */ d$e a;

    s(d$e com_xunlei_downloadprovider_web_base_model_d_e) {
        this.a = com_xunlei_downloadprovider_web_base_model_d_e;
    }

    public final /* synthetic */ void a(Object obj) {
        Long l = (Long) obj;
        a aVar = this.a.a.b;
        long longValue = l.longValue();
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = aVar.getWritableDatabase();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE comment_zan_info SET has_commit=1 WHERE comment_id=").append(longValue);
            sQLiteDatabase.execSQL(stringBuilder.toString());
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
            }
        }
    }

    public final void a(b bVar) {
        new StringBuilder("sync local zan data to server failed:").append(bVar.b);
    }
}
