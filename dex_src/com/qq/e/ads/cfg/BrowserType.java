package com.qq.e.ads.cfg;

public enum BrowserType {
    Default(0),
    Inner(1),
    Sys(2);
    private final int a;

    static {
        Default = new BrowserType("Default", 0, 0);
        Inner = new BrowserType("Inner", 1, 1);
        Sys = new BrowserType("Sys", 2, 2);
        b = new BrowserType[]{Default, Inner, Sys};
    }

    private BrowserType(int i) {
        this.a = i;
    }

    public final int value() {
        return this.a;
    }
}
