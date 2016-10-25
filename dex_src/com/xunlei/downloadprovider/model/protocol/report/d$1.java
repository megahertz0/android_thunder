package com.xunlei.downloadprovider.model.protocol.report;

import com.xunlei.downloadprovider.model.protocol.report.ReportContants.Vod.VodReportPlayState;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;

// compiled from: NewReportBox.java
/* synthetic */ class d$1 {
    static final /* synthetic */ int[] a;
    static final /* synthetic */ int[] b;

    static {
        b = new int[VodReportPlayState.values().length];
        try {
            b[VodReportPlayState.failed.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[VodReportPlayState.success.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        a = new int[VodSourceType.values().length];
        try {
            a[VodSourceType.local_appinner.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[VodSourceType.local_system.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[VodSourceType.normal.ordinal()] = 3;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[VodSourceType.webpage.ordinal()] = 4;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[VodSourceType.lixian.ordinal()] = 5;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[VodSourceType.cloudlist.ordinal()] = 6;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[VodSourceType.vod_history.ordinal()] = 7;
        } catch (NoSuchFieldError e9) {
        }
    }
}
