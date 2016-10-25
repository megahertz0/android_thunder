package com.xunlei.downloadprovider.frame.user.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xunlei.downloadprovider.model.protocol.e.a.c;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;

// compiled from: ShortTimeVideoListDbHelper.java
public final class a extends SQLiteOpenHelper {
    private static a a;

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a(context);
            }
            aVar = a;
        }
        return aVar;
    }

    private a(Context context) {
        super(context, "short_time_video_database", null, 6);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        new StringBuilder("onUpgrade oldVersion=").append(i).append(",newVersion=").append(i2);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS short_time_video_list");
        onCreate(sQLiteDatabase);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append("short_time_video_list( _ID INTEGER PRIMARY KEY AUTOINCREMENT, movieid , gcid , duration,poster,thumbup_count INTEGER ,title, url , video_type  INTEGER ,icon_url, categoryTitle, playCount INTEGER , commentNum INTEGER , durationSec INTEGER ,playUrl text, poster_width INTEGER ,poster_height INTEGER,have_favorite INTEGER ,uplineTime,stick INTEGER ,vStatus INTEGER ,VUrl,channel_sub_title,VComment)");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    public final ArrayList<c> a(int i) {
        SQLiteDatabase sQLiteDatabase = null;
        ArrayList<c> arrayList = new ArrayList();
        try {
            sQLiteDatabase = getWritableDatabase();
            Cursor rawQuery = sQLiteDatabase.rawQuery(new StringBuilder("SELECT * FROM short_time_video_list WHERE video_type=").append(i).toString(), null);
            while (rawQuery != null && rawQuery.moveToNext()) {
                c cVar = new c();
                cVar.a = rawQuery.getString(1);
                cVar.b = rawQuery.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                cVar.f = rawQuery.getString(XZBDevice.DOWNLOAD_LIST_FAILED);
                cVar.e = rawQuery.getString(XZBDevice.DOWNLOAD_LIST_ALL);
                cVar.g = rawQuery.getInt(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                cVar.c = rawQuery.getString(R.styleable.Toolbar_contentInsetEnd);
                cVar.d = rawQuery.getString(R.styleable.Toolbar_contentInsetLeft);
                cVar.h = rawQuery.getInt(XZBDevice.Wait);
                cVar.j = rawQuery.getString(XZBDevice.Pause);
                cVar.k = rawQuery.getString(XZBDevice.Stop);
                cVar.l = rawQuery.getInt(XZBDevice.Success);
                cVar.m = rawQuery.getInt(XZBDevice.Fail);
                cVar.n = rawQuery.getInt(XZBDevice.Upload);
                cVar.i = rawQuery.getString(XZBDevice.Predownload);
                cVar.o = rawQuery.getInt(XZBDevice.Delete);
                cVar.p = rawQuery.getInt(R.styleable.Toolbar_titleMarginBottom);
                if (rawQuery.getInt(R.styleable.Toolbar_maxButtonHeight) == 0) {
                    cVar.q = false;
                } else {
                    cVar.q = true;
                }
                cVar.s = Long.valueOf(rawQuery.getString(R.styleable.Toolbar_collapseIcon)).longValue();
                cVar.t = rawQuery.getInt(R.styleable.Toolbar_collapseContentDescription);
                cVar.u = rawQuery.getInt(R.styleable.Toolbar_navigationIcon);
                cVar.v = rawQuery.getString(R.styleable.Toolbar_navigationContentDescription);
                cVar.x = rawQuery.getString(R.styleable.Toolbar_logoDescription);
                cVar.w = rawQuery.getString(R.styleable.Toolbar_titleTextColor);
                arrayList.add(cVar);
            }
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
        return arrayList;
    }
}
