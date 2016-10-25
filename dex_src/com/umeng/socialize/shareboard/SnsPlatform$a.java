package com.umeng.socialize.shareboard;

import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.HashMap;
import java.util.Map;

class SnsPlatform$a {
    static Map<SHARE_MEDIA, SnsPlatform> a;

    SnsPlatform$a() {
        a();
    }

    static {
        a = new HashMap();
    }

    static SnsPlatform a(SHARE_MEDIA share_media) {
        return (SnsPlatform) a.get(share_media);
    }

    private static void a() {
        SnsPlatform snsPlatform = new SnsPlatform();
        snsPlatform.mPlatform = SHARE_MEDIA.YNOTE;
        snsPlatform.mIcon = "umeng_socialize_ynote";
        snsPlatform.mGrayIcon = "umeng_socialize_ynote_gray";
        snsPlatform.mShowWord = "ynote_showword";
        a.put(SHARE_MEDIA.YNOTE, snsPlatform);
    }
}
