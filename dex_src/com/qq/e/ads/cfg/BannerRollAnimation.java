package com.qq.e.ads.cfg;

public enum BannerRollAnimation {
    Default(0),
    NoAnimation(-1);
    private final int a;

    static {
        Default = new BannerRollAnimation("Default", 0, 0);
        NoAnimation = new BannerRollAnimation("NoAnimation", 1, -1);
        b = new BannerRollAnimation[]{Default, NoAnimation};
    }

    private BannerRollAnimation(int i) {
        this.a = i;
    }

    public final int value() {
        return this.a;
    }
}
