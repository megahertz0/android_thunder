package com.xunlei.downloadprovider.service.downloads.task.b.a;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;

// compiled from: ThunderTaskDatabase.java
public class b$b extends a {
    static final String[][] c;
    static String d;
    private static String e;

    static {
        r0 = new String[14][];
        r0[0] = new String[]{AgooConstants.MESSAGE_TASK_ID, "INTEGER NOT NULL PRIMARY KEY"};
        r0[1] = new String[]{SHubBatchQueryKeys.url, "TEXT"};
        r0[2] = new String[]{"referer", "TEXT"};
        r0[3] = new String[]{SHubBatchQueryKeys.cid, "TEXT"};
        r0[4] = new String[]{SHubBatchQueryKeys.gcid, "TEXT"};
        r0[5] = new String[]{"info_hash", "TEXT"};
        r0[6] = new String[]{"create_origin", "TEXT"};
        r0[7] = new String[]{"task_report_value", "INTEGER DEFAULT 0"};
        r0[8] = new String[]{"seen", "INTEGER DEFAULT 0"};
        r0[9] = new String[]{"max_speed", "INTEGER DEFAULT 0"};
        r0[10] = new String[]{"sniff_key_word", "TEXT"};
        r0[11] = new String[]{"website_name", "TEXT"};
        r0[12] = new String[]{"icon_url", "TEXT"};
        r0[13] = new String[]{"display_name", "TEXT"};
        c = r0;
        d = "task_extra";
        e = BuildConfig.VERSION_NAME;
    }

    public b$b() {
        super(1, 6);
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
