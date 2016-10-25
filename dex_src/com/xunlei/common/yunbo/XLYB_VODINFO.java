package com.xunlei.common.yunbo;

import com.umeng.a;
import java.io.Serializable;

public class XLYB_VODINFO implements Serializable {
    private static final long serialVersionUID = 9028475733114673966L;
    public String cid;
    public String createtime;
    public int duration;
    public String filename;
    public long filesize;
    public String gcid;
    public int playflag;
    public String playtime;
    public String src_url;
    public int tasktype;
    public String url;
    public String url_hash;

    public XLYB_VODINFO() {
        this.createtime = a.d;
        this.playtime = a.d;
        this.tasktype = 0;
        this.filename = a.d;
        this.cid = a.d;
        this.gcid = a.d;
        this.url = a.d;
        this.filesize = 0;
        this.duration = 0;
        this.playflag = 255;
        this.url_hash = a.d;
        this.src_url = a.d;
    }
}
