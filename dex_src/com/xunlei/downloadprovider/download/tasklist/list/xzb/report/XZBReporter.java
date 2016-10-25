package com.xunlei.downloadprovider.download.tasklist.list.xzb.report;

import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

public class XZBReporter {
    private static final String a;

    public enum SaveToXZBEntry {
        task,
        task_detail,
        top,
        other;

        static {
            task = new com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry("task", 0);
            task_detail = new com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry("task_detail", 1);
            top = new com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry("top", 2);
            other = new com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry("other", 3);
            a = new com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry[]{task, task_detail, top, other};
        }
    }

    static {
        a = XZBReporter.class.getSimpleName();
    }

    private XZBReporter() {
    }

    private static void a(g gVar) {
        new StringBuilder("[STAT_EVENT]").append(gVar);
        ThunderReporter.a(gVar, true);
    }

    public static void a() {
        String str = "dl_x9_show";
        g a = g.a("android_dl_X9", str, str);
        a.b("x9_stat", (long) (XZBShouleiUtil.getInstance().getDefaultDevice() == null ? 0 : 1));
        a(a);
    }

    public static void a(XZBCardClickArea xZBCardClickArea) {
        new StringBuilder("clickXZBCard: \u70b9\u51fb\u7684\u4f4d\u7f6e --> ").append(xZBCardClickArea.toString());
        String str = "dl_x9_click";
        g a = g.a("android_dl_X9", str, str);
        a.b("x9_stat", (long) (XZBShouleiUtil.getInstance().getDefaultDevice() == null ? 0 : 1));
        a.a("clickid", xZBCardClickArea.toString(), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void b() {
        String str = "dl_x9_close";
        g a = g.a("android_dl_X9", str, str);
        a.b("x9_stat", (long) (XZBShouleiUtil.getInstance().getDefaultDevice() == null ? 0 : 1));
        a(a);
    }

    public static void a(SaveToXZBEntry saveToXZBEntry, int i) {
        new StringBuilder("saveTaskToXZB: \u8f6c\u5b58\u70b9\u51fb\u4f86\u6e90 --> ").append(saveToXZBEntry.toString());
        String str = "dl_x9_store";
        g a = g.a("android_dl_X9", str, str);
        a.a("from", saveToXZBEntry.toString(), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.b("x9_stat", (long) (XZBShouleiUtil.getInstance().getDefaultDevice() == null ? 0 : 1));
        if (saveToXZBEntry.equals(SaveToXZBEntry.top)) {
            a.b("tasknum", (long) i);
        }
        a(a);
    }

    public static void b(SaveToXZBEntry saveToXZBEntry, int i) {
        new StringBuilder("saveTaskToXZBSuccess: \u8f6c\u5b58\u6210\u529f\u4f86\u6e90 --> ").append(saveToXZBEntry.toString());
        String str = "dl_x9_store_success";
        g a = g.a("android_dl_X9", str, str);
        a.a("from", saveToXZBEntry.toString(), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.b("tasknum", (long) i);
        a(a);
    }

    public static void c(SaveToXZBEntry saveToXZBEntry, int i) {
        new StringBuilder("saveTaskToXZBFailed: \u8f6c\u5b58\u5931\u6557\u4f86\u6e90 --> ").append(saveToXZBEntry.toString());
        String str = "dl_x9_store_fail";
        g a = g.a("android_dl_X9", str, str);
        a.a("from", saveToXZBEntry.toString(), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a.b("tasknum", (long) i);
        a(a);
    }

    public static void a(SaveToXZBEntry saveToXZBEntry) {
        new StringBuilder("fixNoXZBDeviceClick: \u8f6c\u5b58\u5f48\u6846\u4f86\u6e90 --> ").append(saveToXZBEntry.toString());
        String str = "dl_x9_altert_click";
        g a = g.a("android_dl_X9", str, str);
        a.b("x9_stat", (long) (XZBShouleiUtil.getInstance().getDefaultDevice() == null ? 0 : 1));
        a.a("from", saveToXZBEntry.toString(), (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        a(a);
    }

    public static void c() {
        String str = "dl_x9_config";
        g a = g.a("android_dl_X9", str, str);
        a.b("x9_stat", (long) (XZBShouleiUtil.getInstance().getDefaultDevice() == null ? 0 : 1));
        a(a);
    }

    public static void a(int i) {
        String str = "dl_x9_config_click";
        g a = g.a("android_dl_X9", str, str);
        a.b("x9_stat", (long) (XZBShouleiUtil.getInstance().getDefaultDevice() == null ? 0 : 1));
        a.b("clickid", (long) i);
        a(a);
    }
}
