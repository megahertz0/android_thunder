package com.xunlei.downloadprovider.download.util;

import com.xunlei.downloadprovider.download.util.c.a;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: EpisodeUtil.java
final class f extends a {
    final /* synthetic */ c$b a;

    f(c$b com_xunlei_downloadprovider_download_util_c_b) {
        this.a = com_xunlei_downloadprovider_download_util_c_b;
    }

    public final Pattern a() {
        if (this.b == null) {
            this.b = Pattern.compile(c$b.c[0]);
        }
        return this.b;
    }

    protected final a a(String str, Matcher matcher) {
        a aVar = new a();
        if (matcher.find() && matcher.start(SimpleLog.LOG_LEVEL_DEBUG) <= matcher.end(SimpleLog.LOG_LEVEL_DEBUG) && matcher.start(SimpleLog.LOG_LEVEL_DEBUG) >= 0) {
            try {
                int intValue = Integer.valueOf(str.substring(matcher.start(SimpleLog.LOG_LEVEL_DEBUG), matcher.end(SimpleLog.LOG_LEVEL_DEBUG))).intValue();
                if (intValue < 400 && !str.substring(matcher.start(1), matcher.end(1)).contains("\u8c46\u74e3")) {
                    aVar.b = intValue;
                }
            } catch (NumberFormatException e) {
                aVar.b = -1;
            }
        }
        return aVar;
    }
}
