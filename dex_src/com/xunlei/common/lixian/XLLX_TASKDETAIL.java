package com.xunlei.common.lixian;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.Serializable;

public class XLLX_TASKDETAIL implements Serializable {
    private static final long serialVersionUID = -5137325878370685738L;
    public String cid;
    public int classid;
    public long classvalue;
    public long commit_time;
    public String cookies;
    public XLLX_DOWNLOADSTATUS download_status;
    public String[] extLixianUrllist;
    public int file_attr;
    public long filesize;
    public XLLixianFileType filetype;
    public String gcid;
    public int leftLiveTime;
    public String lixian_url;
    public String message;
    public XLLX_PEERINFO[] peerList;
    public int progress;
    public String ref_url;
    public int speed;
    public int status;
    public int suffix_type;
    public long taskid;
    public String taskname;
    public String url;
    public int usedTime;

    public XLLX_TASKDETAIL() {
        this.status = 0;
        this.message = BuildConfig.VERSION_NAME;
        this.taskid = 0;
        this.url = BuildConfig.VERSION_NAME;
        this.ref_url = BuildConfig.VERSION_NAME;
        this.cookies = BuildConfig.VERSION_NAME;
        this.taskname = BuildConfig.VERSION_NAME;
        this.cid = BuildConfig.VERSION_NAME;
        this.gcid = BuildConfig.VERSION_NAME;
        this.filesize = 0;
        this.filetype = new XLLixianFileType(0);
        this.classid = 0;
        this.classvalue = 0;
        this.download_status = XLLX_DOWNLOADSTATUS.unknown;
        this.progress = 0;
        this.lixian_url = BuildConfig.VERSION_NAME;
        this.leftLiveTime = 0;
        this.suffix_type = 0;
        this.commit_time = 0;
        this.speed = 0;
        this.usedTime = 0;
        this.extLixianUrllist = new String[0];
        this.peerList = new XLLX_PEERINFO[0];
        this.file_attr = 0;
    }
}
