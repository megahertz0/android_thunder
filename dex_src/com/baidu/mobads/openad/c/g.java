package com.baidu.mobads.openad.c;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.mobads.j.m;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

public class g {
    private i a;
    private String b;

    public g(Context context) {
        this.a = new i(context);
        this.b = m.a().n().getCurrentProcessName(context);
    }

    public boolean a(String str, String str2) {
        Cursor rawQuery = this.a.getReadableDatabase().rawQuery("select count(*)  from download_info where url=? and local_file=? and process_name=?", new String[]{str, str2, this.b});
        rawQuery.moveToFirst();
        int i = rawQuery.getInt(0);
        rawQuery.close();
        return i > 0;
    }

    public void a(List<h> list) {
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        for (h hVar : list) {
            try {
                writableDatabase.execSQL("insert into download_info(thread_id,url,local_file,start_pos,end_pos,compelete_size,process_name) values (?,?,?,?,?,?,?)", new Object[]{Integer.valueOf(hVar.c()), hVar.b(), hVar.f(), Integer.valueOf(hVar.d()), Integer.valueOf(hVar.e()), Integer.valueOf(hVar.a()), this.b});
            } catch (Exception e) {
                m.a().f().e("OAdSqlLiteAccessObj", e.getMessage());
            }
        }
    }

    public List<h> b(String str, String str2) {
        List<h> arrayList = new ArrayList();
        Cursor rawQuery = this.a.getReadableDatabase().rawQuery("select thread_id, url, local_file, start_pos, end_pos,compelete_size from download_info where url=? and local_file=? and process_name=?", new String[]{str, str2, this.b});
        while (rawQuery.moveToNext()) {
            arrayList.add(new h(rawQuery.getInt(0), rawQuery.getString(1), rawQuery.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE), rawQuery.getInt(XZBDevice.DOWNLOAD_LIST_FAILED), rawQuery.getInt(XZBDevice.DOWNLOAD_LIST_ALL), rawQuery.getInt(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)));
        }
        rawQuery.close();
        return arrayList;
    }

    public void b(List<h> list) {
        SQLiteDatabase readableDatabase = this.a.getReadableDatabase();
        for (h hVar : list) {
            try {
                readableDatabase.execSQL("update download_info set compelete_size=? where thread_id=? and url=? and local_file=? and process_name=?", new Object[]{Integer.valueOf(hVar.a()), Integer.valueOf(hVar.c()), hVar.b(), hVar.f(), this.b});
            } catch (Exception e) {
                m.a().f().e("OAdSqlLiteAccessObj", e.getMessage());
            }
        }
    }
}
