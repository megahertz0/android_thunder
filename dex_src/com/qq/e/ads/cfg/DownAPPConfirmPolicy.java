package com.qq.e.ads.cfg;

public enum DownAPPConfirmPolicy {
    Default(0),
    NOConfirm(2);
    private final int a;

    static {
        Default = new DownAPPConfirmPolicy("Default", 0, 0);
        NOConfirm = new DownAPPConfirmPolicy("NOConfirm", 1, 2);
        b = new DownAPPConfirmPolicy[]{Default, NOConfirm};
    }

    private DownAPPConfirmPolicy(int i) {
        this.a = i;
    }

    public final int value() {
        return this.a;
    }
}
