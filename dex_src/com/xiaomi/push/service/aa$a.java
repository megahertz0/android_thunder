package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.network.HostManager.HttpGet;
import com.xiaomi.smack.util.h;
import com.xiaomi.stats.g;
import com.xunlei.xllib.R;
import java.net.URL;

class aa$a implements HttpGet {
    aa$a() {
    }

    public String a(String str) {
        URL url = new URL(str);
        int port = url.getPort() == -1 ? R.styleable.AppCompatTheme_panelMenuListTheme : url.getPort();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String a = d.a(h.a(), url);
            g.a(url.getHost() + ":" + port, (int) (System.currentTimeMillis() - currentTimeMillis), null);
            return a;
        } catch (Exception e) {
            g.a(url.getHost() + ":" + port, -1, e);
            throw e;
        }
    }
}
