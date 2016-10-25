package com.xunlei.analytics.config;

import com.xunlei.analytics.b.f;

public class AnalyticsReportConfiguration {
    public static final int REALTIME = 0;
    public int batchUploadCount;
    public long deleteExpirationDayTime;
    public boolean isWifiOnly;
    public long reportCheckInterval;
    public int reportRetryCount;
    public int reportRule;

    public AnalyticsReportConfiguration(AnalyticsReportConfigurationBuilder analyticsReportConfigurationBuilder) {
        this.reportRule = analyticsReportConfigurationBuilder.reportRule;
        this.deleteExpirationDayTime = analyticsReportConfigurationBuilder.deleteExpirationDayTime;
        this.batchUploadCount = analyticsReportConfigurationBuilder.batchUploadCount;
        this.reportCheckInterval = analyticsReportConfigurationBuilder.reportCheckInterval;
        this.isWifiOnly = analyticsReportConfigurationBuilder.isWifiOnly;
        this.reportRetryCount = analyticsReportConfigurationBuilder.reportRetryCount;
        f.a(analyticsReportConfigurationBuilder.sessionContinueMillis);
    }
}
