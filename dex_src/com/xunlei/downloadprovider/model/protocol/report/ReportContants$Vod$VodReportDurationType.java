package com.xunlei.downloadprovider.model.protocol.report;

public enum ReportContants$Vod$VodReportDurationType {
    play,
    buffer,
    first_buffer,
    vod_use;

    static {
        play = new ReportContants$Vod$VodReportDurationType("play", 0);
        buffer = new ReportContants$Vod$VodReportDurationType("buffer", 1);
        first_buffer = new ReportContants$Vod$VodReportDurationType("first_buffer", 2);
        vod_use = new ReportContants$Vod$VodReportDurationType("vod_use", 3);
        a = new ReportContants$Vod$VodReportDurationType[]{play, buffer, first_buffer, vod_use};
    }
}
