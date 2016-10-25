package com.inmobi.commons.core.c;

import android.content.ContentValues;
import com.inmobi.commons.core.b.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;

// compiled from: TelemetryDao.java
public class d {
    private static final String a;

    static {
        a = d.class.getSimpleName();
    }

    public d() {
        b a = b.a();
        a.a("telemetry", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL, ts TEXT NOT NULL)");
        a.a("metric", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL )");
        a.b();
    }

    public void a(e eVar) {
        b a = b.a();
        a.a("telemetry", eVar.e());
        a.b();
    }

    public void a(String str, String str2, String str3) {
        b a = b.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("componentType", str);
        contentValues.put("eventType", str2);
        contentValues.put("payload", str3);
        a.a("metric", contentValues);
        a.b();
    }

    public List<ContentValues> a() {
        b a = b.a();
        List<ContentValues> a2 = a.a("metric", null, null, null, null, null, null, null);
        a.b();
        b();
        return a2;
    }

    public void b() {
        b a = b.a();
        a.a("metric", null, null);
        a.b();
    }

    public void a(List<e> list) {
        b a = b.a();
        for (e eVar : list) {
            a.a("telemetry", eVar.e());
        }
        a.b();
    }

    public List<e> a(int i) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Querying db for events");
        b a = b.a();
        List<ContentValues> a2 = a.a("telemetry", null, null, null, null, null, "ts ASC", String.valueOf(i));
        b(a2);
        List<e> arrayList = new ArrayList();
        a.b();
        for (ContentValues contentValues : a2) {
            arrayList.add(e.a(contentValues));
        }
        return arrayList;
    }

    private void b(List<ContentValues> list) {
        if (!list.isEmpty()) {
            b a = b.a();
            String str = a.d;
            int i = 0;
            while (i < list.size() - 1) {
                String str2 = str + ((ContentValues) list.get(i)).getAsString(SocializeConstants.WEIBO_ID) + MiPushClient.ACCEPT_TIME_SEPARATOR;
                i++;
                str = str2;
            }
            str = str + ((ContentValues) list.get(list.size() - 1)).getAsString(SocializeConstants.WEIBO_ID);
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Deleting events with id: ").append(str).toString());
            int a2 = a.a("telemetry", new StringBuilder("id IN (").append(str).append(SocializeConstants.OP_CLOSE_PAREN).toString(), null);
            a.b();
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Deleted Count: ").append(a2).toString());
        }
    }

    public int c() {
        b a = b.a();
        int a2 = a.a("telemetry");
        a.b();
        return a2;
    }
}
