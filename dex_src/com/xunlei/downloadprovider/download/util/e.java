package com.xunlei.downloadprovider.download.util;

import com.xunlei.downloadprovider.download.util.c.a;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: EpisodeUtil.java
final class e extends a {
    final /* synthetic */ c$b a;

    e(c$b com_xunlei_downloadprovider_download_util_c_b) {
        this.a = com_xunlei_downloadprovider_download_util_c_b;
    }

    public final Pattern a() {
        if (this.b == null) {
            this.b = Pattern.compile(c$b.b[0], SimpleLog.LOG_LEVEL_DEBUG);
        }
        return this.b;
    }

    protected final a a(String str, Matcher matcher) {
        a aVar = new a();
        if (matcher.find()) {
            MatchResult toMatchResult = matcher.toMatchResult();
            if (toMatchResult.start(1) <= toMatchResult.end(1) && toMatchResult.start(1) >= 0) {
                try {
                    aVar.a = c$b.a(str.substring(toMatchResult.start(1), toMatchResult.end(1)));
                } catch (NumberFormatException e) {
                    aVar.a = -1;
                }
            }
            if (toMatchResult.start(SimpleLog.LOG_LEVEL_DEBUG) <= toMatchResult.end(SimpleLog.LOG_LEVEL_DEBUG) && toMatchResult.start(SimpleLog.LOG_LEVEL_DEBUG) >= 0) {
                try {
                    aVar.b = c$b.a(str.substring(toMatchResult.start(SimpleLog.LOG_LEVEL_DEBUG), toMatchResult.end(SimpleLog.LOG_LEVEL_DEBUG)));
                } catch (NumberFormatException e2) {
                    aVar.b = -1;
                }
            }
        }
        return aVar;
    }
}
