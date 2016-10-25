package com.xunlei.downloadprovider.service.downloads.kernel;

import android.database.Cursor;
import com.umeng.message.proguard.j;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;

// compiled from: ColumnIndex.java
public final class a {
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    public final boolean a(Cursor cursor) {
        if (cursor == null) {
            return false;
        }
        this.a = cursor.getColumnIndex(j.g);
        this.b = cursor.getColumnIndex(SHubBatchQueryKeys.cid);
        this.c = cursor.getColumnIndex(SHubBatchQueryKeys.gcid);
        this.d = cursor.getColumnIndex(SetKey.TITLE);
        this.e = cursor.getColumnIndex("uri");
        this.i = cursor.getColumnIndex("_data");
        this.f = cursor.getColumnIndex("total_bytes");
        this.n = cursor.getColumnIndex("mimetype");
        this.m = cursor.getColumnIndex("errorMsg");
        this.g = cursor.getColumnIndex("status");
        this.h = cursor.getColumnIndex("task_type");
        this.j = cursor.getColumnIndex("create_time");
        this.k = cursor.getColumnIndex("lastmod");
        this.l = cursor.getColumnIndex("download_duration");
        this.o = cursor.getColumnIndex("current_bytes");
        this.q = cursor.getColumnIndex("origin_receive_size");
        this.p = cursor.getColumnIndex("download_speed");
        this.r = cursor.getColumnIndex("origin_speed");
        this.t = cursor.getColumnIndex("p2s_speed");
        this.v = cursor.getColumnIndex("p2p_speed");
        this.u = cursor.getColumnIndex("p2s_receive_size");
        this.w = cursor.getColumnIndex("p2p_receive_size");
        this.s = cursor.getColumnIndex("xunlei_spdy");
        this.x = cursor.getColumnIndex("is_lx_speedup");
        this.z = cursor.getColumnIndex("addition_lx_speed");
        this.A = cursor.getColumnIndex("lx_receive_size");
        this.y = cursor.getColumnIndex("lx_status");
        this.B = cursor.getColumnIndex("lx_progress");
        this.C = cursor.getColumnIndex("is_vip_speedup");
        this.E = cursor.getColumnIndex("addition_vip_speed");
        this.F = cursor.getColumnIndex("vip_receive_size");
        this.D = cursor.getColumnIndex("vip_status");
        this.G = cursor.getColumnIndex("apk_package");
        this.H = cursor.getColumnIndex("apk_version");
        this.I = cursor.getColumnIndex("res_total");
        this.J = cursor.getColumnIndex("res_used_total");
        this.K = cursor.getColumnIndex(SetKey.ETAG);
        return true;
    }
}
