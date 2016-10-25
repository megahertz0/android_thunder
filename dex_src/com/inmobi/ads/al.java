package com.inmobi.ads;

import android.content.ContentValues;
import com.inmobi.commons.core.b.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.umeng.socialize.common.SocializeConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// compiled from: PlacementDao.java
public class al {
    public static final String[] a;
    private static final String b;
    private static al c;
    private static Object d;

    static {
        b = al.class.getSimpleName();
        d = new Object();
        a = new String[]{SocializeConstants.WEIBO_ID, "placement_id", "tp_key", "last_accessed_ts"};
    }

    public static al a() {
        al alVar = c;
        if (alVar == null) {
            synchronized (d) {
                alVar = c;
                if (alVar == null) {
                    alVar = new al();
                    c = alVar;
                }
            }
        }
        return alVar;
    }

    private al() {
        b a = b.a();
        a.a("placement", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL,tp_key TEXT,last_accessed_ts INTEGER NOT NULL,UNIQUE(placement_id,tp_key))");
        a.b();
    }

    public int a(long j) {
        b a = b.a();
        long currentTimeMillis = System.currentTimeMillis() - (1000 * j);
        int a2 = a.a("placement", "last_accessed_ts<?", new String[]{String.valueOf(currentTimeMillis)});
        Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Deleted ").append(a2).append(" expired pids from cache").toString());
        a.b();
        return a2;
    }

    public synchronized int a(List<ak> list, int i) {
        int i2;
        int i3 = 0;
        synchronized (this) {
            if (list != null) {
                if (list.size() != 0) {
                    b a = b.a();
                    for (int i4 = 0; i4 < list.size(); i4++) {
                        String[] strArr = new String[]{String.valueOf(((ak) list.get(i4)).c()), ((ak) list.get(i4)).d()};
                        a.a("placement", ((ak) list.get(i4)).f(), "placement_id = ? AND tp_key=?", strArr);
                    }
                    int a2 = a.a("placement") - i;
                    if (a2 > 0) {
                        List a3 = a.a("placement", new String[]{SocializeConstants.WEIBO_ID}, null, null, null, null, "last_accessed_ts ASC", String.valueOf(a2));
                        String[] strArr2 = new String[a3.size()];
                        while (i3 < a3.size()) {
                            strArr2[i3] = String.valueOf(((ContentValues) a3.get(i3)).getAsInteger(SocializeConstants.WEIBO_ID));
                            i3++;
                        }
                        a.a("placement", new StringBuilder("id IN ").append(Arrays.toString(strArr2).replace("[", SocializeConstants.OP_OPEN_PAREN).replace("]", SocializeConstants.OP_CLOSE_PAREN)).toString(), null);
                    }
                    a.b();
                    i2 = a2;
                }
            }
            i2 = 0;
        }
        return i2;
    }

    public List<ak> b() {
        List<ak> arrayList = new ArrayList();
        b a = b.a();
        List<ContentValues> a2 = a.a("placement", a, null, null, null, null, null, null);
        a.b();
        for (ContentValues contentValues : a2) {
            arrayList.add(new ak(contentValues));
        }
        return arrayList;
    }
}
