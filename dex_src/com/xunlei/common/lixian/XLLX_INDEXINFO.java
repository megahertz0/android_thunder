package com.xunlei.common.lixian;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.Serializable;

public class XLLX_INDEXINFO implements Serializable {
    private static final long serialVersionUID = 2965604082775597019L;
    public String aichhash;
    public String bcid;
    public String bt_info_hash;
    public String cid;
    public int control_flag;
    public int download_strategy;
    public int dspider_ctrl_flag;
    public String emule_hash;
    public String file_suffix;
    public int fileindex;
    public long filesize;
    public String gcid;
    public int gcid_level;
    public int gcid_part_size;
    public int link_rank;
    public String parthash;
    public int pub_filesize_threshold;
    public int query_seed_svr_threshold;
    public int res_type;
    public int result;
    public long taskid;

    public XLLX_INDEXINFO() {
        this.result = 0;
        this.taskid = 0;
        this.gcid = BuildConfig.VERSION_NAME;
        this.cid = BuildConfig.VERSION_NAME;
        this.res_type = 0;
        this.filesize = 0;
        this.emule_hash = BuildConfig.VERSION_NAME;
        this.fileindex = 0;
        this.bt_info_hash = BuildConfig.VERSION_NAME;
        this.bcid = BuildConfig.VERSION_NAME;
        this.parthash = BuildConfig.VERSION_NAME;
        this.aichhash = BuildConfig.VERSION_NAME;
        this.query_seed_svr_threshold = 0;
        this.pub_filesize_threshold = 0;
        this.link_rank = 0;
        this.dspider_ctrl_flag = 0;
        this.file_suffix = BuildConfig.VERSION_NAME;
        this.gcid_part_size = 0;
        this.gcid_level = 0;
        this.control_flag = 0;
        this.download_strategy = 0;
    }
}
