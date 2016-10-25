package com.baidu.mobads.a;

public class b {
    public static final Boolean a;
    public static final Boolean b;

    public static double a() {
        try {
            return Double.parseDouble("8.27");
        } catch (Exception e) {
            return 0.0d;
        }
    }

    public static int b() {
        try {
            return Integer.valueOf("8.27".split("\\.")[0]).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    static {
        a = Boolean.valueOf(false);
        b = Boolean.valueOf(false);
    }
}
