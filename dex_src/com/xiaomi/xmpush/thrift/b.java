package com.xiaomi.xmpush.thrift;

public enum b {
    UploadSwitch(1),
    UploadFrequency(2),
    ScreenSizeCollectionSwitch(3),
    MacCollectionSwitch(4),
    IMSICollectionSwitch(5),
    AndroidVnCollectionSwitch(6),
    AndroidVcCollectionSwitch(7),
    AndroidIdCollectionSwitch(8),
    DeviceInfoCollectionFrequency(9),
    AppInstallListCollectionSwitch(10),
    AppInstallListCollectionFrequency(11),
    AppActiveListCollectionSwitch(12),
    AppActiveListCollectionFrequency(13),
    BluetoothCollectionSwitch(14),
    BluetoothCollectionFrequency(15),
    LocationCollectionSwitch(16),
    LocationCollectionFrequency(17),
    AccountCollectionSwitch(18),
    AccountCollectionFrequency(19),
    WifiCollectionSwitch(20),
    WifiCollectionFrequency(21),
    CellularCollectionSwitch(22),
    CellularCollectionFrequency(23),
    TopAppCollectionSwitch(24),
    TopAppCollectionFrequency(25),
    DataCollectionSwitch(26),
    OcVersionCheckFrequency(27),
    CollectionDataPluginVersion(1001),
    CollectionPluginDownloadUrl(1002),
    CollectionPluginMd5(1003),
    CollectionPluginForceStop(1004);
    private final int F;

    static {
        a = new b("UploadSwitch", 0, 1);
        b = new b("UploadFrequency", 1, 2);
        c = new b("ScreenSizeCollectionSwitch", 2, 3);
        d = new b("MacCollectionSwitch", 3, 4);
        e = new b("IMSICollectionSwitch", 4, 5);
        f = new b("AndroidVnCollectionSwitch", 5, 6);
        g = new b("AndroidVcCollectionSwitch", 6, 7);
        h = new b("AndroidIdCollectionSwitch", 7, 8);
        i = new b("DeviceInfoCollectionFrequency", 8, 9);
        j = new b("AppInstallListCollectionSwitch", 9, 10);
        k = new b("AppInstallListCollectionFrequency", 10, 11);
        l = new b("AppActiveListCollectionSwitch", 11, 12);
        m = new b("AppActiveListCollectionFrequency", 12, 13);
        n = new b("BluetoothCollectionSwitch", 13, 14);
        o = new b("BluetoothCollectionFrequency", 14, 15);
        p = new b("LocationCollectionSwitch", 15, 16);
        q = new b("LocationCollectionFrequency", 16, 17);
        r = new b("AccountCollectionSwitch", 17, 18);
        s = new b("AccountCollectionFrequency", 18, 19);
        t = new b("WifiCollectionSwitch", 19, 20);
        u = new b("WifiCollectionFrequency", 20, 21);
        v = new b("CellularCollectionSwitch", 21, 22);
        w = new b("CellularCollectionFrequency", 22, 23);
        x = new b("TopAppCollectionSwitch", 23, 24);
        y = new b("TopAppCollectionFrequency", 24, 25);
        z = new b("DataCollectionSwitch", 25, 26);
        A = new b("OcVersionCheckFrequency", 26, 27);
        B = new b("CollectionDataPluginVersion", 27, 1001);
        C = new b("CollectionPluginDownloadUrl", 28, 1002);
        D = new b("CollectionPluginMd5", 29, 1003);
        E = new b("CollectionPluginForceStop", 30, 1004);
        G = new b[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E};
    }

    private b(int i) {
        this.F = i;
    }

    public final int a() {
        return this.F;
    }
}
