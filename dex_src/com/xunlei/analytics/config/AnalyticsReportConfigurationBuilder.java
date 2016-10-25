package com.xunlei.analytics.config;

public class AnalyticsReportConfigurationBuilder {
    int batchUploadCount;
    long deleteExpirationDayTime;
    boolean isWifiOnly;
    long reportCheckInterval;
    int reportRetryCount;
    int reportRule;
    long sessionContinueMillis;

    public AnalyticsReportConfigurationBuilder() {
        this.reportRule = 0;
        this.batchUploadCount = 10;
        this.reportCheckInterval = 180000;
        this.deleteExpirationDayTime = 604800000;
        this.isWifiOnly = false;
        this.reportRetryCount = 3;
        this.sessionContinueMillis = 5000;
    }

    public AnalyticsReportConfigurationBuilder batchUploadCount(int i) {
        this.batchUploadCount = i;
        return this;
    }

    public AnalyticsReportConfiguration build() {
        return new AnalyticsReportConfiguration(this);
    }

    public AnalyticsReportConfigurationBuilder deleteExpirationDayTime(int i) {
        this.deleteExpirationDayTime = (long) i;
        return this;
    }

    public AnalyticsReportConfigurationBuilder reportCheckInterval(long j) {
        this.reportCheckInterval = j;
        return this;
    }

    public AnalyticsReportConfigurationBuilder reportRetryCount(int i) {
        this.reportRetryCount = i;
        return this;
    }

    public AnalyticsReportConfigurationBuilder reportRule(int i) {
        this.reportRule = i;
        return this;
    }

    public AnalyticsReportConfigurationBuilder sessionContinueMillis(long j) {
        this.sessionContinueMillis = j;
        return this;
    }

    public AnalyticsReportConfigurationBuilder uploadInWifiOnly(boolean z) {
        this.isWifiOnly = z;
        return this;
    }
}
