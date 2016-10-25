package com.xunlei.downloadprovider.web.base.core;

public enum MethodName {
    xlGetNetworkInfo,
    xlCheckAppInstalled,
    xlShowToast,
    xlOpenUrl,
    xlGetAppMetaData,
    xlAddPlayRecord,
    xlAddTask,
    xlSocialShare,
    xlGetUserInfo,
    xlHttpRequestForward,
    xlReportStatistics,
    xlShowLoading,
    xlHideLoading,
    xlGetPageFrom,
    xlVideoPlay,
    xlLogout,
    xlSendDataToApp,
    xlOpenLocalPage,
    xlOpenApp,
    xlPay,
    xlGetSniffConfig,
    xlStartSniff;

    static {
        xlGetNetworkInfo = new MethodName("xlGetNetworkInfo", 0);
        xlCheckAppInstalled = new MethodName("xlCheckAppInstalled", 1);
        xlShowToast = new MethodName("xlShowToast", 2);
        xlOpenUrl = new MethodName("xlOpenUrl", 3);
        xlGetAppMetaData = new MethodName("xlGetAppMetaData", 4);
        xlAddPlayRecord = new MethodName("xlAddPlayRecord", 5);
        xlAddTask = new MethodName("xlAddTask", 6);
        xlSocialShare = new MethodName("xlSocialShare", 7);
        xlGetUserInfo = new MethodName("xlGetUserInfo", 8);
        xlHttpRequestForward = new MethodName("xlHttpRequestForward", 9);
        xlReportStatistics = new MethodName("xlReportStatistics", 10);
        xlShowLoading = new MethodName("xlShowLoading", 11);
        xlHideLoading = new MethodName("xlHideLoading", 12);
        xlGetPageFrom = new MethodName("xlGetPageFrom", 13);
        xlVideoPlay = new MethodName("xlVideoPlay", 14);
        xlLogout = new MethodName("xlLogout", 15);
        xlSendDataToApp = new MethodName("xlSendDataToApp", 16);
        xlOpenLocalPage = new MethodName("xlOpenLocalPage", 17);
        xlOpenApp = new MethodName("xlOpenApp", 18);
        xlPay = new MethodName("xlPay", 19);
        xlGetSniffConfig = new MethodName("xlGetSniffConfig", 20);
        xlStartSniff = new MethodName("xlStartSniff", 21);
        a = new MethodName[]{xlGetNetworkInfo, xlCheckAppInstalled, xlShowToast, xlOpenUrl, xlGetAppMetaData, xlAddPlayRecord, xlAddTask, xlSocialShare, xlGetUserInfo, xlHttpRequestForward, xlReportStatistics, xlShowLoading, xlHideLoading, xlGetPageFrom, xlVideoPlay, xlLogout, xlSendDataToApp, xlOpenLocalPage, xlOpenApp, xlPay, xlGetSniffConfig, xlStartSniff};
    }

    public static MethodName getMethod(String str) {
        try {
            return valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }
}
