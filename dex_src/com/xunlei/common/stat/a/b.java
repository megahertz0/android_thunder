package com.xunlei.common.stat.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xunlei.download.DownloadManager;

// compiled from: XLStatDBHelper.java
public final class b extends SQLiteOpenHelper {
    private static final String a = "xl-acc-stat.db";
    private static final int b = 2;

    public b(Context context) {
        super(context, a, null, 2);
    }

    private b(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    private b(Context context, String str, CursorFactory cursorFactory, int i, byte b) {
        super(context, str, cursorFactory, i);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE IF NOT EXISTS xl_acc_stat_list(_id INTEGER PRIMARY KEY AUTOINCREMENT,url VARCHAR,error INTEGER,respt DOUBLE,retry INTEGER,ip VARCHAR,domain VARCHAR,cmd INTEGER,bt INTEGER,date VARCHAR,uid INTEGER)");
        sQLiteDatabase.execSQL(stringBuffer.toString());
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 == 2) {
            sQLiteDatabase.execSQL("ALTER TABLE xl_acc_stat_list ADD COLUMN uid");
            Cursor rawQuery = sQLiteDatabase.rawQuery("select * from xl_acc_stat_list", null);
            while (rawQuery.moveToNext()) {
                int i3 = rawQuery.getInt(rawQuery.getColumnIndex(DownloadManager.COLUMN_ID));
                ContentValues contentValues = new ContentValues();
                contentValues.put(ParamKey.UID, Integer.valueOf(0));
                sQLiteDatabase.update("xl_acc_stat_list", contentValues, "_id=?", new String[]{String.valueOf(i3)});
            }
        }
    }
}
