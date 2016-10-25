package com.inmobi.commons.core.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.inmobi.commons.a.b;

// compiled from: DbHelper.java
public class a extends SQLiteOpenHelper {
    public static final String a;

    static {
        a = new StringBuilder("com.im_").append(b.c()).append(".db").toString();
    }

    public a(Context context) {
        super(context, a, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
