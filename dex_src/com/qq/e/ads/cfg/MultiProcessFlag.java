package com.qq.e.ads.cfg;

import com.qq.e.comm.util.GDTLogger;

public class MultiProcessFlag {
    private static boolean a;
    private static boolean b;

    public static boolean isMultiProcess() {
        return a;
    }

    public static void setMultiProcess(boolean z) {
        if (b) {
            GDTLogger.w("MultiProcessFlag has already be setted,reset will not take any effect");
            return;
        }
        b = true;
        a = z;
    }
}
