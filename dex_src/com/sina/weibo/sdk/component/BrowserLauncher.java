package com.sina.weibo.sdk.component;

// compiled from: BrowserRequestParamBase.java
enum BrowserLauncher {
    AUTH,
    SHARE,
    WIDGET,
    COMMON,
    GAME;

    static {
        AUTH = new BrowserLauncher("AUTH", 0);
        SHARE = new BrowserLauncher("SHARE", 1);
        WIDGET = new BrowserLauncher("WIDGET", 2);
        COMMON = new BrowserLauncher("COMMON", 3);
        GAME = new BrowserLauncher("GAME", 4);
        ENUM$VALUES = new BrowserLauncher[]{AUTH, SHARE, WIDGET, COMMON, GAME};
    }
}
