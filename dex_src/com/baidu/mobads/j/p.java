package com.baidu.mobads.j;

import android.net.wifi.ScanResult;
import java.util.Comparator;

class p implements Comparator<ScanResult> {
    final /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((ScanResult) obj, (ScanResult) obj2);
    }

    public int a(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult2.level - scanResult.level;
    }
}
