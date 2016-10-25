package com.xunlei.tdlive.play.view;

public enum NormalScreenLayout$a {
    CHAT_BAR,
    PLAY_BTN_BAR,
    PUBLISH_BTN_BAR,
    NONE;

    static {
        a = new NormalScreenLayout$a("CHAT_BAR", 0);
        b = new NormalScreenLayout$a("PLAY_BTN_BAR", 1);
        c = new NormalScreenLayout$a("PUBLISH_BTN_BAR", 2);
        d = new NormalScreenLayout$a("NONE", 3);
        e = new NormalScreenLayout$a[]{a, b, c, d};
    }
}
