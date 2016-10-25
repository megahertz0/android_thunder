package com.xunlei.tdlive;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xllib.R;

// compiled from: CollectService.java
class b extends SQLiteOpenHelper {
    final /* synthetic */ a a;

    b(a aVar, Context context, String str, CursorFactory cursorFactory, int i) {
        this.a = aVar;
        super(context, str, cursorFactory, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        onUpgrade(sQLiteDatabase, 0, R.styleable.AppCompatTheme_checkboxStyle);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        XLog.d("CollectService", new StringBuilder("onUpgrade ").append(i).append(", ").append(i2).toString());
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CollectService(_id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT);");
        } catch (Exception e) {
            XLog.e("CollectService", new StringBuilder("couldn't create table:").append(e.toString()).toString());
        }
    }
}
