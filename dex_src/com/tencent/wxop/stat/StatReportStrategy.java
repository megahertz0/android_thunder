package com.tencent.wxop.stat;

public enum StatReportStrategy {
    INSTANT(1),
    ONLY_WIFI(2),
    BATCH(3),
    APP_LAUNCH(4),
    DEVELOPER(5),
    PERIOD(6),
    ONLY_WIFI_NO_CACHE(7);
    int a;

    static {
        INSTANT = new StatReportStrategy("INSTANT", 0, 1);
        ONLY_WIFI = new StatReportStrategy("ONLY_WIFI", 1, 2);
        BATCH = new StatReportStrategy("BATCH", 2, 3);
        APP_LAUNCH = new StatReportStrategy("APP_LAUNCH", 3, 4);
        DEVELOPER = new StatReportStrategy("DEVELOPER", 4, 5);
        PERIOD = new StatReportStrategy("PERIOD", 5, 6);
        ONLY_WIFI_NO_CACHE = new StatReportStrategy("ONLY_WIFI_NO_CACHE", 6, 7);
        b = new StatReportStrategy[]{INSTANT, ONLY_WIFI, BATCH, APP_LAUNCH, DEVELOPER, PERIOD, ONLY_WIFI_NO_CACHE};
    }

    private StatReportStrategy(int i) {
        this.a = i;
    }

    public static StatReportStrategy getStatReportStrategy(int i) {
        StatReportStrategy[] values = values();
        int length = values.length;
        for (int i2 = 0; i2 < length; i2++) {
            StatReportStrategy statReportStrategy = values[i2];
            if (i == statReportStrategy.a()) {
                return statReportStrategy;
            }
        }
        return null;
    }

    public final int a() {
        return this.a;
    }
}
