package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager.HttpGet;
import com.xiaomi.network.HostManagerV2;
import com.xiaomi.push.thrift.a;
import com.xiaomi.stats.e;
import com.xiaomi.stats.g;
import java.io.IOException;
import java.util.ArrayList;

class aa$b extends HostManagerV2 {
    protected aa$b(Context context, HostFilter hostFilter, HttpGet httpGet, String str) {
        super(context, hostFilter, httpGet, str);
    }

    protected String getRemoteFallbackJSON(ArrayList<String> arrayList, String str, String str2) {
        try {
            if (e.a().c()) {
                str2 = ag.e();
            }
            return super.getRemoteFallbackJSON(arrayList, str, str2);
        } catch (IOException e) {
            IOException iOException = e;
            g.a(0, a.r.a(), 1, null, d.d(this.sAppContext) ? 1 : 0);
            throw iOException;
        }
    }
}
