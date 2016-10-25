package com.xunlei.downloadprovider.vod.protocol;

public enum VodSourceType {
    normal,
    local_appinner,
    local_system,
    webpage,
    shortVideo,
    lixian,
    cloudlist,
    vod_history,
    third_server,
    sniffing_list,
    old_detail_other,
    download_detail,
    uc_cloud,
    space_his;

    static {
        normal = new VodSourceType("normal", 0);
        local_appinner = new VodSourceType("local_appinner", 1);
        local_system = new VodSourceType("local_system", 2);
        webpage = new VodSourceType("webpage", 3);
        shortVideo = new VodSourceType("shortVideo", 4);
        lixian = new VodSourceType("lixian", 5);
        cloudlist = new VodSourceType("cloudlist", 6);
        vod_history = new VodSourceType("vod_history", 7);
        third_server = new VodSourceType("third_server", 8);
        sniffing_list = new VodSourceType("sniffing_list", 9);
        old_detail_other = new VodSourceType("old_detail_other", 10);
        download_detail = new VodSourceType("download_detail", 11);
        uc_cloud = new VodSourceType("uc_cloud", 12);
        space_his = new VodSourceType("space_his", 13);
        a = new VodSourceType[]{normal, local_appinner, local_system, webpage, shortVideo, lixian, cloudlist, vod_history, third_server, sniffing_list, old_detail_other, download_detail, uc_cloud, space_his};
    }
}
