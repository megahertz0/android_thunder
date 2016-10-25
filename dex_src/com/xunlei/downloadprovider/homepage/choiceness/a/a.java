package com.xunlei.downloadprovider.homepage.choiceness.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xunlei.downloadprovider.ad.common.CommonConst.AD_TYPE;
import com.xunlei.downloadprovider.ad.home.a.h;
import com.xunlei.downloadprovider.ad.home.a.i;
import com.xunlei.downloadprovider.ad.home.a.j;
import com.xunlei.downloadprovider.ad.home.a.k;
import com.xunlei.downloadprovider.ad.home.a.l;
import com.xunlei.downloadprovider.ad.home.a.m;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.b;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.c;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ChoicenessDataHelper.java
public class a {
    private static a g;
    public h a;
    public long b;
    public long c;
    public LinkedList<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> d;
    public Executor e;
    private Context f;
    private f h;
    private m i;
    private List<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> j;
    private List<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> k;
    private Handler l;
    private long m;

    // compiled from: ChoicenessDataHelper.java
    public static interface a {
        void a(List<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> list, b bVar);
    }

    // compiled from: ChoicenessDataHelper.java
    private class b implements Runnable {
        com.xunlei.downloadprovider.search.b.a<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> a;

        public b(com.xunlei.downloadprovider.search.b.a<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> aVar) {
            this.a = aVar;
        }

        public final void run() {
            List list;
            SQLiteDatabase writableDatabase = a.this.h.getWritableDatabase();
            Cursor rawQuery = writableDatabase.rawQuery("select * from choiceness limit ?", new String[]{"8"});
            if (rawQuery == null || rawQuery.getCount() == 0) {
                list = null;
            } else {
                int columnIndex = rawQuery.getColumnIndex("displayType");
                int columnIndex2 = rawQuery.getColumnIndex("resType");
                int columnIndex3 = rawQuery.getColumnIndex("resId");
                int columnIndex4 = rawQuery.getColumnIndex(SHubBatchQueryKeys.gcid);
                int columnIndex5 = rawQuery.getColumnIndex("sortId");
                int columnIndex6 = rawQuery.getColumnIndex("resCoverUrl");
                int columnIndex7 = rawQuery.getColumnIndex("subCategory");
                int columnIndex8 = rawQuery.getColumnIndex("subDisplayType");
                int columnIndex9 = rawQuery.getColumnIndex("resTitle");
                int columnIndex10 = rawQuery.getColumnIndex("subjectCount");
                int columnIndex11 = rawQuery.getColumnIndex("tag");
                int columnIndex12 = rawQuery.getColumnIndex("playCount");
                int columnIndex13 = rawQuery.getColumnIndex("likeCount");
                int columnIndex14 = rawQuery.getColumnIndex("doubanScore");
                int columnIndex15 = rawQuery.getColumnIndex("jumpUrl");
                int columnIndex16 = rawQuery.getColumnIndex("introduction");
                int columnIndex17 = rawQuery.getColumnIndex("duration");
                int columnIndex18 = rawQuery.getColumnIndex("play_url");
                int columnIndex19 = rawQuery.getColumnIndex("is_on_the_top");
                int columnIndex20 = rawQuery.getColumnIndex("s_ab");
                int columnIndex21 = rawQuery.getColumnIndex("params");
                list = new ArrayList();
                while (rawQuery.moveToNext()) {
                    com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = new com.xunlei.downloadprovider.homepage.choiceness.a.a.a();
                    a.this = rawQuery.getInt(columnIndex);
                    aVar.c = rawQuery.getString(columnIndex2);
                    aVar.d = rawQuery.getString(columnIndex3);
                    aVar.e = rawQuery.getString(columnIndex4);
                    aVar.u = rawQuery.getLong(columnIndex5);
                    aVar.f = rawQuery.getString(columnIndex6);
                    aVar.i = rawQuery.getString(columnIndex7);
                    aVar.j = rawQuery.getString(columnIndex8);
                    aVar.k = rawQuery.getString(columnIndex9);
                    aVar.m = rawQuery.getInt(columnIndex10);
                    aVar.v = rawQuery.getString(columnIndex18);
                    if (rawQuery.getInt(columnIndex19) == 1) {
                        aVar.a = true;
                    } else {
                        aVar.a = false;
                    }
                    aVar.w = rawQuery.getString(columnIndex20);
                    aVar.p = rawQuery.getString(columnIndex11);
                    aVar.n = rawQuery.getInt(columnIndex12);
                    aVar.o = rawQuery.getInt(columnIndex13);
                    aVar.q = rawQuery.getFloat(columnIndex14);
                    aVar.r = rawQuery.getString(columnIndex15);
                    aVar.s = rawQuery.getString(columnIndex16);
                    aVar.t = rawQuery.getInt(columnIndex17);
                    aVar.I = rawQuery.getString(columnIndex21);
                    if (aVar.m > 0) {
                        String[] strArr = new String[2];
                        strArr[0] = aVar.c;
                        strArr[1] = aVar.d;
                        Cursor rawQuery2 = writableDatabase.rawQuery("select * from subChoiceness where parent_res_type=? and parent_res_id=?", strArr);
                        if (rawQuery2 != null && rawQuery2.getCount() > 0) {
                            ArrayList arrayList = new ArrayList();
                            int columnIndex22 = rawQuery2.getColumnIndex("res_type");
                            int columnIndex23 = rawQuery2.getColumnIndex("parent_res_type");
                            int columnIndex24 = rawQuery2.getColumnIndex("res_id");
                            int columnIndex25 = rawQuery2.getColumnIndex("parent_res_id");
                            int columnIndex26 = rawQuery2.getColumnIndex(SetKey.TITLE);
                            int columnIndex27 = rawQuery2.getColumnIndex("cover_url");
                            int columnIndex28 = rawQuery2.getColumnIndex("like_count");
                            int columnIndex29 = rawQuery2.getColumnIndex("douban_score");
                            int columnIndex30 = rawQuery2.getColumnIndex("jump_url");
                            int columnIndex31 = rawQuery2.getColumnIndex("play_url");
                            while (rawQuery2.moveToNext()) {
                                c cVar = new c();
                                cVar.g = rawQuery2.getString(columnIndex25);
                                cVar.h = rawQuery2.getString(columnIndex23);
                                a.this = rawQuery2.getString(columnIndex24);
                                cVar.d = rawQuery2.getString(columnIndex22);
                                cVar.a = rawQuery2.getString(columnIndex26);
                                cVar.c = rawQuery2.getString(columnIndex27);
                                cVar.e = rawQuery2.getInt(columnIndex28);
                                cVar.f = rawQuery2.getFloat(columnIndex29);
                                cVar.i = rawQuery2.getString(columnIndex30);
                                cVar.j = rawQuery2.getString(columnIndex31);
                                arrayList.add(cVar);
                            }
                            aVar.l = arrayList;
                            rawQuery2.close();
                        }
                    }
                    list.add(aVar);
                }
                rawQuery.close();
            }
            if (this.a != null) {
                a.this.l.post(new e(this, list));
            }
        }
    }

    private a(Context context) {
        this.i = m.a();
        this.c = Long.MAX_VALUE;
        this.d = new LinkedList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = new Handler(Looper.getMainLooper());
        this.e = Executors.newSingleThreadExecutor();
        this.m = -1;
        this.a = new h();
        this.h = new f(context);
        this.f = context;
    }

    public static a a(Context context) {
        if (g == null) {
            synchronized (a.class) {
                if (g == null) {
                    g = new a(context);
                }
            }
        }
        return g;
    }

    private void a(com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar, boolean z) {
        if (r.c().e.a().i != 0) {
            if (z) {
                this.d.addFirst(aVar);
            } else {
                this.d.add(aVar);
            }
            if (com.xunlei.downloadprovider.ad.home.a.c.a(this.f).c.f || com.xunlei.downloadprovider.ad.home.a.c.a(this.f).c.e == null) {
                com.xunlei.downloadprovider.ad.home.a.c.a(this.f).c.f = true;
                com.xunlei.downloadprovider.ad.home.a.c a = com.xunlei.downloadprovider.ad.home.a.c.a(this.f);
                AD_TYPE ad_type = AD_TYPE.SOURCE_GDT_FLAG;
                switch (aVar.b) {
                    case SimpleLog.LOG_LEVEL_ERROR:
                        ad_type = com.xunlei.downloadprovider.ad.common.b.a(com.xunlei.downloadprovider.ad.home.b.a().c);
                        break;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        ad_type = com.xunlei.downloadprovider.ad.common.b.a(com.xunlei.downloadprovider.ad.home.b.a().b);
                        break;
                    case SpdyProtocol.CUSTOM:
                        ad_type = AD_TYPE.SOURCE_SSP_FLAG;
                        break;
                }
                com.xunlei.downloadprovider.ad.common.b.a bVar = new com.xunlei.downloadprovider.ad.home.a.b(a.b, aVar);
                com.xunlei.downloadprovider.ad.common.b.a mVar = new m(a.b, aVar, a.d);
                com.xunlei.downloadprovider.ad.common.b.a jVar;
                switch (c$1.a[ad_type.ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (aVar.b == 0) {
                            jVar = new j(a.b, aVar);
                            jVar.a = mVar;
                            jVar.b();
                            return;
                        }
                        jVar = new i(a.b, aVar);
                        bVar.a = mVar;
                        jVar.a = bVar;
                        jVar.b();
                        return;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        jVar = new h(a.b, aVar);
                        bVar.a = mVar;
                        jVar.a = bVar;
                        jVar.b();
                        return;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        jVar = new l(a.b, aVar);
                        if (aVar.b == 16) {
                            jVar.a = mVar;
                        } else {
                            com.xunlei.downloadprovider.ad.common.b.a iVar = new i(a.b, aVar);
                            jVar.a = iVar;
                            iVar.a = bVar;
                            bVar.a = mVar;
                        }
                        jVar.b();
                        return;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        jVar = new k(a.b, aVar);
                        bVar.a = mVar;
                        jVar.a = bVar;
                        jVar.b();
                        return;
                    default:
                        new m(a.b, aVar, a.d).b();
                        return;
                }
            }
            com.xunlei.downloadprovider.ad.home.a.c.a(this.f).c.f = true;
            com.xunlei.downloadprovider.ad.home.a.c.a(this.f).c.a(aVar.d, com.xunlei.downloadprovider.ad.home.a.c.a(this.f).c.e);
            aVar.b = 6;
        }
    }

    public final void a() {
        this.k.clear();
        this.k.addAll(this.j);
        this.k.addAll(this.d);
    }

    private void d() {
        this.k.clear();
        this.j.clear();
        this.d.clear();
        this.b = 0;
        this.c = Long.MAX_VALUE;
    }

    public final long b() {
        if (this.m < 0) {
            this.m = this.i.a.getLong("ts", 0);
        }
        return this.m;
    }

    private static String a(com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        return String.valueOf(System.currentTimeMillis()) + aVar.b;
    }

    public final void c() {
        if (!this.d.isEmpty()) {
            List arrayList = new ArrayList();
            int min = Math.min(this.d.size(), SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (!this.j.isEmpty()) {
                arrayList.addAll(this.j);
            }
            for (int i = 0; i < min; i++) {
                com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) this.d.get(i);
                if (!aVar.b() && !aVar.d()) {
                    arrayList.add(aVar);
                }
            }
            if (!arrayList.isEmpty()) {
                this.e.execute(new d(this, arrayList));
            }
        }
    }

    public final boolean a(a aVar) {
        boolean a = com.xunlei.c.a.b.a(this.f);
        if (!a) {
            aVar.a(this.k, null);
        }
        return a;
    }

    static /* synthetic */ void a(a aVar, long j) {
        aVar.m = j;
        aVar.i.a(j);
    }

    static /* synthetic */ void a(a aVar, b bVar) {
        if (bVar != null) {
            if (bVar.d) {
                aVar.d();
            }
            aVar.j.clear();
            Collection collection = bVar.b;
            if (collection != null) {
                aVar.j.addAll(collection);
            }
            List list = bVar.c;
            if (list != null && !list.isEmpty()) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar2 = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) list.get(size);
                    if (TextUtils.isEmpty(aVar2.d)) {
                        aVar2.d = a(aVar2);
                    }
                    if (aVar2.b()) {
                        aVar.a(aVar2, true);
                    } else {
                        aVar.d.addFirst(aVar2);
                    }
                }
            }
        }
    }

    static /* synthetic */ void a(a aVar, List list) {
        if (list != null && !list.isEmpty()) {
            long j = aVar.b;
            long j2 = aVar.c;
            long j3 = j;
            j = j2;
            for (com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar2 : list) {
                j2 = aVar2.u;
                if (j2 > 0) {
                    if (j2 > j3) {
                        j3 = j2;
                    }
                    if (j2 >= j) {
                        j2 = j;
                    }
                    j = j2;
                }
            }
            aVar.b = j3;
            aVar.c = j;
        }
    }

    static /* synthetic */ void b(a aVar, b bVar) {
        if (bVar != null) {
            if (bVar.d) {
                aVar.d();
            }
            List<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> list = bVar.c;
            if (list != null && !list.isEmpty()) {
                com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar2;
                for (com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar22 : list) {
                    if (TextUtils.isEmpty(aVar22.d)) {
                        aVar22.d = a(aVar22);
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    aVar22 = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) list.get(i);
                    if (aVar22.b()) {
                        aVar.a(aVar22, false);
                    } else {
                        aVar.d.add(aVar22);
                    }
                }
            }
        }
    }

    static /* synthetic */ void b(a aVar, List list) {
        if (list != null && !list.isEmpty()) {
            for (com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar2 : list) {
                if (aVar2.a) {
                    aVar.j.add(aVar2);
                } else {
                    aVar.d.add(aVar2);
                }
            }
        }
    }
}
