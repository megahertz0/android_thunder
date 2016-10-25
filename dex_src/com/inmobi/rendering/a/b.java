package com.inmobi.rendering.a;

import android.content.ContentValues;
import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: ClickDao.java
public class b {
    public static final String[] a;
    private static final String b;

    static {
        b = b.class.getSimpleName();
        a = new String[]{SocializeConstants.WEIBO_ID, "pending_attempts", SocialConstants.PARAM_URL, "ping_in_webview", "follow_redirect", MsgConstant.KEY_TS, "created_ts"};
    }

    public b() {
        com.inmobi.commons.core.b.b a = com.inmobi.commons.core.b.b.a();
        a.a("click", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, ping_in_webview TEXT NOT NULL, follow_redirect TEXT NOT NULL, ts TEXT NOT NULL, created_ts TEXT NOT NULL)");
        a.b();
    }

    public boolean a() {
        return com.inmobi.commons.core.b.b.a().a("click") == 0;
    }

    public synchronized boolean a(a aVar, int i) {
        ContentValues c = c(aVar);
        com.inmobi.commons.core.b.b a = com.inmobi.commons.core.b.b.a();
        if (a.a("click") >= i) {
            Map hashMap = new HashMap();
            hashMap.put(Constants.KEY_ERROR_CODE, "MaxDbLimitBreach");
            a.a().a("ads", "PingDiscarded", hashMap);
            Logger.a(InternalLogLevel.INTERNAL, b, "Pruning persistent store to remove the oldest entry ...");
            a a2 = a((ContentValues) a.a("click", a, "ts= (SELECT MIN(ts) FROM click LIMIT 1)", null, null, null, null, null).get(0));
            Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Deleting click (").append(a2.a).append(SocializeConstants.OP_CLOSE_PAREN).toString());
            b(a2);
        }
        a.a("click", c);
        a.b();
        return true;
    }

    public List<a> a(int i, int i2) {
        List<a> arrayList = new ArrayList();
        com.inmobi.commons.core.b.b a = com.inmobi.commons.core.b.b.a();
        if (a.a("click") == 0) {
            return arrayList;
        }
        List<ContentValues> a2 = a.a("click", a, null, null, MsgConstant.KEY_TS, new StringBuilder("ts < ").append(System.currentTimeMillis() - ((long) i2)).toString(), "ts ASC ", -1 == i ? null : Integer.toString(i));
        a.b();
        for (ContentValues contentValues : a2) {
            arrayList.add(a(contentValues));
        }
        return arrayList;
    }

    public void a(a aVar) {
        com.inmobi.commons.core.b.b a = com.inmobi.commons.core.b.b.a();
        a.b("click", c(aVar), "id = ?", new String[]{String.valueOf(aVar.a)});
        a.b();
    }

    public void b(a aVar) {
        com.inmobi.commons.core.b.b a = com.inmobi.commons.core.b.b.a();
        a.a("click", "id = ?", new String[]{String.valueOf(aVar.a)});
        a.b();
    }

    public a a(ContentValues contentValues) {
        int intValue = contentValues.getAsInteger(SocializeConstants.WEIBO_ID).intValue();
        int intValue2 = contentValues.getAsInteger("pending_attempts").intValue();
        return new a(intValue, contentValues.getAsString(SocialConstants.PARAM_URL), Boolean.valueOf(contentValues.getAsString("follow_redirect")).booleanValue(), Boolean.valueOf(contentValues.getAsString("ping_in_webview")).booleanValue(), intValue2, Long.valueOf(contentValues.getAsString(MsgConstant.KEY_TS)).longValue(), Long.valueOf(contentValues.getAsString("created_ts")).longValue());
    }

    public ContentValues c(a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SocializeConstants.WEIBO_ID, Integer.valueOf(aVar.a));
        contentValues.put(SocialConstants.PARAM_URL, aVar.b);
        contentValues.put("pending_attempts", Integer.valueOf(aVar.f));
        contentValues.put(MsgConstant.KEY_TS, Long.toString(aVar.d));
        contentValues.put("created_ts", Long.toString(aVar.e));
        contentValues.put("follow_redirect", Boolean.toString(aVar.i));
        contentValues.put("ping_in_webview", Boolean.toString(aVar.h));
        return contentValues;
    }
}
