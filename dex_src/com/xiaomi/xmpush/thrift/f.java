package com.xiaomi.xmpush.thrift;

import java.util.Map;

public enum f {
    Invalid("INVALID"),
    BarClick("bar:click"),
    BarCancel("bar:cancel"),
    AppOpen("app:open"),
    PackageUninstall("package uninstalled"),
    AppUninstall("app_uninstalled"),
    ClientInfoUpdate("client_info_update"),
    ClientInfoUpdateOk("client_info_update_ok"),
    PullOfflineMessage("pull"),
    IosSleep("ios_sleep"),
    IosWakeUp("ios_wakeup"),
    NormalClientConfigUpdate("normal_client_config_update"),
    CustomClientConfigUpdate("custom_client_config_update"),
    DailyCheckClientConfig("daily_check_client_config"),
    DataCollection("data_collection");
    public static Map<String, f> q;
    public String p;

    static {
        String str = "INVALID";
        a = new f("Invalid", 0, "INVALID");
        str = "bar:click";
        b = new f("BarClick", 1, "bar:click");
        str = "bar:cancel";
        c = new f("BarCancel", 2, "bar:cancel");
        str = "app:open";
        d = new f("AppOpen", 3, "app:open");
        str = "package uninstalled";
        e = new f("PackageUninstall", 4, "package uninstalled");
        String str2 = "app_uninstalled";
        f = new f("AppUninstall", 5, "app_uninstalled");
        str2 = "client_info_update";
        g = new f("ClientInfoUpdate", 6, "client_info_update");
        str2 = "client_info_update_ok";
        h = new f("ClientInfoUpdateOk", 7, "client_info_update_ok");
        str2 = "pull";
        i = new f("PullOfflineMessage", 8, "pull");
        str2 = "ios_sleep";
        j = new f("IosSleep", 9, "ios_sleep");
        str2 = "ios_wakeup";
        k = new f("IosWakeUp", 10, "ios_wakeup");
        str2 = "normal_client_config_update";
        l = new f("NormalClientConfigUpdate", 11, "normal_client_config_update");
        str2 = "custom_client_config_update";
        m = new f("CustomClientConfigUpdate", 12, "custom_client_config_update");
        str2 = "daily_check_client_config";
        n = new f("DailyCheckClientConfig", 13, "daily_check_client_config");
        str2 = "data_collection";
        o = new f("DataCollection", 14, "data_collection");
        r = new f[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o};
        q = null;
    }

    private f(String str) {
        this.p = str;
    }

    public final String toString() {
        return this.p;
    }
}
