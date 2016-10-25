package com.xunlei.common.lixian;

import com.xunlei.xiazaibao.BuildConfig;

public class XLLX_TASKRESPSTATUS {
    public String msg;
    public int status;
    public long taskid;

    public XLLX_TASKRESPSTATUS() {
        this.status = 0;
        this.msg = BuildConfig.VERSION_NAME;
        this.taskid = 0;
    }
}
