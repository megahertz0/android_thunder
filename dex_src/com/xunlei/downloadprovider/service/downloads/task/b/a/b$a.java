package com.xunlei.downloadprovider.service.downloads.task.b.a;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;

// compiled from: ThunderTaskDatabase.java
public class b$a extends a {
    static final String[][] c;
    private static String d;
    private static String e;

    static {
        String[][] strArr = new String[1][];
        strArr[0] = new String[]{AgooConstants.MESSAGE_TASK_ID, "INTEGER NOT NULL PRIMARY KEY"};
        c = strArr;
        d = "task_consume";
        e = BuildConfig.VERSION_NAME;
    }

    public b$a() {
        super(4, 4);
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        String a;
        if (TextUtils.isEmpty(e)) {
            a = a(d, c);
            e = a;
        } else {
            a = e;
        }
        sQLiteDatabase.execSQL(a);
    }
}
