package com.xunlei.downloadprovider.homepage.choiceness.a;

import android.database.sqlite.SQLiteDatabase;
import java.util.List;

// compiled from: ChoicenessDataHelper.java
final class d implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ a b;

    d(a aVar, List list) {
        this.b = aVar;
        this.a = list;
    }

    public final void run() {
        f c = this.b.h;
        List list = this.a;
        if (list != null && !list.isEmpty()) {
            SQLiteDatabase writableDatabase = c.getWritableDatabase();
            writableDatabase.beginTransaction();
            writableDatabase.delete("subChoiceness", null, null);
            writableDatabase.delete("choiceness", null, null);
            f.a(writableDatabase, list);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
        }
    }
}
