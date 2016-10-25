package com.xunlei.downloadprovider.player;

import android.text.TextUtils;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;
import org.android.agoo.message.MessageService;

// compiled from: AndroidPlayerReporter.java
public final class a {

    // compiled from: AndroidPlayerReporter.java
    public static class a {
        public String a;
        public String b;
        public long c;
        public long d;
        public long e;
        public String f;
        public String g;
        public String h;
        public String i;
        public long j;
        public String k;
        public String l;
        public String m;
        public String n;
        public long o;
        public long p;
        public long q;
        public long r;
    }

    // compiled from: AndroidPlayerReporter.java
    public static class b {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public long f;
        public String g;
        public String h;
        public String i;
        public long j;
        public String k;
        public String l;
        public String m;
        public String n;
        public String o;
        public String p;
        public String q;
        public String r;
        public String s;
        public long t;
        public String u;
        public String v;
        public String w;
        public String x;
    }

    public static void a(b bVar) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_play", "play_all_start");
        a.b("from", bVar.a);
        a.b("play_type", bVar.b);
        a.b("fileformat", bVar.c);
        a.b("player_type", bVar.d);
        a.b("suffix", bVar.e);
        a.b("movieid", bVar.m);
        a.b("filesize", bVar.f);
        a.b("fileurl", bVar.g);
        a.b("filename", bVar.h);
        a.b("fmovie_resolution", bVar.i);
        a.b("file_duration", bVar.j);
        a.b("play_sessionid", bVar.k);
        a.b("play_tec", bVar.l);
        a.b("channelid", bVar.n);
        a.b("server", bVar.p);
        a.b("rec_params", bVar.q);
        a.b("platformModel", bVar.r);
        a.b(com.alipay.sdk.app.statistic.c.a, bVar.s);
        a.b("load_time", bVar.t);
        a.b("speed_limit", bVar.u);
        if (!TextUtils.isEmpty(bVar.v)) {
            a.b(Impl.COLUMN_GCID, bVar.v);
            a.b("if_vip_bxbb", bVar.w);
        }
        a.b("if_xunlei_download", bVar.x == null ? MessageService.MSG_DB_READY_REPORT : bVar.x);
        new StringBuilder("[STAT_EVENT]").append(a);
        d.a(a);
    }

    public static void a(a aVar) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_play", "play_player_end");
        a.b("play_type", aVar.a);
        a.b("end_type", aVar.b);
        a.b("file_duration", aVar.c);
        a.b("play_duration", aVar.d);
        a.b("movieid", aVar.h);
        a.b("filesize", aVar.e);
        a.b("play_sessionid", aVar.f);
        a.b("play_tec", aVar.g);
        a.b("fplay_size", aVar.j);
        if (!TextUtils.isEmpty(aVar.k)) {
            a.b(Impl.COLUMN_GCID, aVar.k);
        }
        a.b("rec_params", aVar.l);
        a.b("platformModel", aVar.m);
        a.b(com.alipay.sdk.app.statistic.c.a, aVar.n);
        a.b("suspend_drag_time", aVar.o);
        a.b("suspend_drag_cnt", aVar.p);
        a.b("suspend_nodrag_time", aVar.q);
        a.b("suspend_nodrag_cnt", aVar.r);
        d.a(a);
    }

    public static void a(String str, String str2) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_player", "player_screen_click");
        a.a("movieid", str);
        a.a("clickid", str2);
        d.a(a);
        new StringBuilder("[STAT_EVENT]").append(a);
    }

    public static void b(String str, String str2) {
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_player", "player_pause");
        a.a("from", str);
        a.a("button_model", str2);
        d.a(a);
    }
}
