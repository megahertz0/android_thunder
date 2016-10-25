package com.xunlei.downloadprovider.download.report;

import com.sina.weibo.sdk.api.CmdObject;

public enum DLCenterEntry {
    home,
    browser,
    personal_center,
    search,
    icon,
    download_push,
    other;

    static {
        home = new DLCenterEntry(CmdObject.CMD_HOME, 0);
        browser = new DLCenterEntry("browser", 1);
        personal_center = new DLCenterEntry("personal_center", 2);
        search = new DLCenterEntry("search", 3);
        icon = new DLCenterEntry("icon", 4);
        download_push = new DLCenterEntry("download_push", 5);
        other = new DLCenterEntry("other", 6);
        a = new DLCenterEntry[]{home, browser, personal_center, search, icon, download_push, other};
    }
}
