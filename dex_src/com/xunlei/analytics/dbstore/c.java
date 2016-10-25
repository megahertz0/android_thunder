package com.xunlei.analytics.dbstore;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

final class c extends SQLiteOpenHelper {
    final /* synthetic */ b a;

    public c(b bVar, Context context) {
        this.a = bVar;
        super(context, AnalyticsConstant.DB_NAME, null, 1);
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE analytics(_id INTEGER PRIMARY KEY AUTOINCREMENT,_appId TEXT, _interId TEXT, _eventData TEXT, _eventTime BIGINT);");
    }

    public final void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS analytics");
                b(sQLiteDatabase);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            b(sQLiteDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase != null) {
            try {
                a(sQLiteDatabase);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
