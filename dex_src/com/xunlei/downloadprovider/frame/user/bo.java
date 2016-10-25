package com.xunlei.downloadprovider.frame.user;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.toolbox.t;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.user.a.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.g;
import java.util.ArrayList;
import java.util.List;

// compiled from: UserCenterTaskManager.java
public class bo {
    private static final String b;
    private static bo c;
    public String a;

    static {
        b = bo.class.getSimpleName();
    }

    private bo() {
        this.a = a.d;
    }

    public static bo a() {
        if (c == null && c == null) {
            c = new bo();
        }
        return c;
    }

    public static void a(ag agVar) {
        b.a(BrothersApplication.a().getApplicationContext()).a(agVar);
    }

    public static void a(String str) {
        c.a = str;
    }

    public static void b(String str) {
        b.a(BrothersApplication.a().getApplicationContext()).a(str);
    }

    public static void a(String str, String str2) {
        b.a(BrothersApplication.a().getApplicationContext()).a(str, str2);
    }

    public static void b(String str, String str2) {
        b.a(BrothersApplication.a().getApplicationContext()).b(str, str2);
    }

    public static ag c(String str) {
        return b.a(BrothersApplication.a().getApplicationContext()).b(str);
    }

    public static List<ag> b() {
        ArrayList arrayList = new ArrayList();
        return b.a(BrothersApplication.a().getApplicationContext()).a();
    }

    public static void d(String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = b.a(BrothersApplication.a().getApplicationContext()).getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("actState", Integer.valueOf(1));
            sQLiteDatabase.update("User_Center_Task_List", contentValues, "downloadUrl = ?", new String[]{str});
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                sQLiteDatabase.endTransaction();
                sQLiteDatabase.close();
            }
        } catch (SQLiteException e) {
            try {
                e.printStackTrace();
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
            }
        }
    }

    public static int e(String str) {
        return b.a(BrothersApplication.a().getApplicationContext()).c(str);
    }

    public final void b(ag agVar) {
        a a = a.a();
        String str = LoginHelper.a().j;
        String str2 = agVar.a;
        bp bpVar = new bp(this, agVar);
        new StringBuilder("postUserOperation userId=").append(str).append(",optionType=4");
        StringBuilder append = new StringBuilder("http://jifenshangcheng.m.xunlei.com/cgi-bin/integra_busi?").append(new StringBuilder("userId=").append(str).toString()).append("&type=4").append(new StringBuilder("&extId=").append(str2).toString()).append(new StringBuilder("&versionCode=").append(com.xunlei.downloadprovider.a.b.x()).toString());
        append.append(new StringBuilder("&peerId=").append(com.xunlei.downloadprovider.a.b.d()).toString());
        String toString = append.toString();
        append.append("&sign=").append(g.a((toString.substring(toString.indexOf("?") + 1) + "&C63xmnzM+").getBytes()));
        Request tVar = new t(append.toString(), new f(a, bpVar), new g(a, bpVar));
        tVar.setShouldCache(false);
        tVar.setRetryPolicy(new f(10000, 1, 1.0f));
        a.a(tVar);
        StatReporter.reportUserCountsUserOption(str, XZBDevice.DOWNLOAD_LIST_ALL);
    }
}
