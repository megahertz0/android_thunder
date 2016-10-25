package com.inmobi.ads;

import android.content.ContentValues;
import com.inmobi.commons.core.b.b;
import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.android.agoo.message.MessageService;

// compiled from: AdDao.java
public class c {
    public static final String[] a;
    private static final String b;
    private static c c;
    private static Object d;

    static {
        b = c.class.getSimpleName();
        d = new Object();
        a = new String[]{SocializeConstants.WEIBO_ID, "ad_content", "ad_type", "ad_size", "placement_id", "insertion_ts", "imp_id"};
    }

    public static c a() {
        c cVar = c;
        if (cVar == null) {
            synchronized (d) {
                cVar = c;
                if (cVar == null) {
                    cVar = new c();
                    c = cVar;
                }
            }
        }
        return cVar;
    }

    private c() {
        b a = b.a();
        a.a("ad", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL, ad_content TEXT NOT NULL, ad_type TEXT NOT NULL, ad_size TEXT, insertion_ts INTEGER NOT NULL, imp_id TEXT NOT NULL)");
        a.b();
    }

    public void a(String str, long j) {
        b a = b.a();
        long currentTimeMillis = System.currentTimeMillis() - (1000 * j);
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Deleted ").append(a.a("ad", "ad_type=? AND insertion_ts<?", new String[]{str, String.valueOf(currentTimeMillis)})).append(" expired ads from cache of type:").append(str).toString());
        a.b();
    }

    public int a(long j, String str) {
        int b;
        b a = b.a();
        if (str == null || str.trim().length() == 0) {
            b = a.b("ad", "placement_id=?", new String[]{String.valueOf(j)});
        } else {
            b = a.b("ad", "placement_id=? AND ad_size=?", new String[]{String.valueOf(j), str});
        }
        a.b();
        return b;
    }

    public synchronized a b(long j, String str) {
        List a;
        b a2 = b.a();
        if (str == null || str.trim().length() == 0) {
            a = a2.a("ad", a, "placement_id=?", new String[]{String.valueOf(j)}, null, null, "insertion_ts", MessageService.MSG_DB_NOTIFY_REACHED);
        } else {
            a = a2.a("ad", a, "placement_id=? AND ad_size=?", new String[]{String.valueOf(j), str}, null, null, "insertion_ts", MessageService.MSG_DB_NOTIFY_REACHED);
        }
        a2.a("ad", "id=?", new String[]{String.valueOf(((ContentValues) a.get(0)).getAsInteger(SocializeConstants.WEIBO_ID).intValue())});
        return new a(r1);
    }

    public synchronized void a(List<a> list, int i, String str) {
        if (i != 0) {
            if (list.size() != 0) {
                int i2;
                b a = b.a();
                for (i2 = 0; i2 < list.size(); i2++) {
                    a.a("ad", ((a) list.get(i2)).a());
                }
                int b = a.b("ad", "ad_type=?", new String[]{str}) - i;
                if (b > 0) {
                    Map hashMap = new HashMap();
                    hashMap.put(JsInterface.FUNPLAY_AD_TRPE, str);
                    hashMap.put(ParamKey.COUNT, Integer.valueOf(b));
                    a.a().a("ads", "DbSpaceOverflow", hashMap);
                    List a2 = a.a("ad", new String[]{SocializeConstants.WEIBO_ID}, "ad_type=?", new String[]{str}, null, null, "insertion_ts ASC", String.valueOf(b));
                    String[] strArr = new String[a2.size()];
                    for (i2 = 0; i2 < a2.size(); i2++) {
                        strArr[i2] = String.valueOf(((ContentValues) a2.get(i2)).getAsInteger(SocializeConstants.WEIBO_ID));
                    }
                    a.a("ad", new StringBuilder("id IN ").append(Arrays.toString(strArr).replace("[", SocializeConstants.OP_OPEN_PAREN).replace("]", SocializeConstants.OP_CLOSE_PAREN)).toString(), null);
                }
                a.b();
            }
        }
    }
}
