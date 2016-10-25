package com.xunlei.downloadprovider.download.util;

import com.xunlei.xiazaibao.BuildConfig;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.spdy.SpdyProtocol;

// compiled from: EpisodeUtil.java
private class c$b {
    public static String[] a;
    public static String[] b;
    public static String[] c;
    final a d;
    final a e;
    final a f;

    // compiled from: EpisodeUtil.java
    static abstract class a {
        protected Pattern b;

        protected abstract com.xunlei.downloadprovider.download.util.c.a a(String str, Matcher matcher);

        public abstract Pattern a();

        public final com.xunlei.downloadprovider.download.util.c.a a(String str) {
            return a(str, a().matcher(str));
        }
    }

    static {
        a = new String[]{"(?:([0-9]+)(?:\u96c6|\u8bdd|\u56de))", "(?:EP(?:isode)?[\\s\\.]*([0-9]+))", "(?:(?:\u7b2c[^\\s]*(?:\u5b63|\u90e8).*)E(?:(?:P|pisode)?)?[\\s\\.]*([0-9]+))", "(?:S(?:[0-9]+)E[P]?([0-9]+))"};
        b = new String[]{"S([0-9]+)E[P]?([0-9]+)"};
        c = new String[]{"([\\u4e00-\\u9fa5]+)([0-9]+)\\."};
    }

    public c$b() {
        this.d = new d(this);
        this.e = new e(this);
        this.f = new f(this);
    }

    public static int a(String str) throws NumberFormatException {
        return Integer.parseInt(str.replaceAll("[^\\d]+", BuildConfig.VERSION_NAME), SpdyProtocol.PUBKEY_SEQ_OPEN);
    }
}
