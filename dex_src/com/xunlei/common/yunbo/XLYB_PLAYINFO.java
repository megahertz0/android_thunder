package com.xunlei.common.yunbo;

import com.xunlei.xiazaibao.BuildConfig;

public class XLYB_PLAYINFO {
    public long duration;
    public int remain_time;
    public XLYB_REQPLAYINFO reqdata;
    public int result;
    public int state;
    public PLAYINFO[] videoList;

    public static class PLAYINFO {
        public String cid;
        public long filesize;
        public String gcid;
        public int has_subtitle;
        public int resolution_type;
        public String vod_url;

        public PLAYINFO() {
            this.resolution_type = 0;
            this.vod_url = BuildConfig.VERSION_NAME;
            this.gcid = BuildConfig.VERSION_NAME;
            this.cid = BuildConfig.VERSION_NAME;
            this.filesize = 0;
            this.has_subtitle = 0;
        }
    }

    public XLYB_PLAYINFO() {
        this.reqdata = null;
        this.result = 0;
        this.state = 0;
        this.duration = 0;
        this.remain_time = 0;
        this.videoList = null;
    }
}
