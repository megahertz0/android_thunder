package com.xunlei.common.lixian;

import java.io.Serializable;

public class XLLX_TASKBASIC implements Serializable {
    private static final long serialVersionUID = -5793105529930967620L;
    public long classvalue;
    public long commit_time;
    public int database;
    public long delete_time;
    public byte download_status;
    public int flag;
    public int res_type;
    public int status;
    public int suffix_type;
    public long taskid;

    public XLLX_TASKBASIC() {
        this.flag = 0;
    }
}
