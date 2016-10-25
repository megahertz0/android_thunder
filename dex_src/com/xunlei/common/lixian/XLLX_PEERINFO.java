package com.xunlei.common.lixian;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.Serializable;

public class XLLX_PEERINFO implements Serializable {
    private static final long serialVersionUID = -3691197426143506489L;
    public int peer_ability;
    public String peerid;
    public String peerip;
    public int res_origin;
    public short tcpport;
    public short udpport;

    public XLLX_PEERINFO() {
        this.peerid = BuildConfig.VERSION_NAME;
        this.peerip = BuildConfig.VERSION_NAME;
        this.tcpport = (short) 0;
        this.udpport = (short) 0;
        this.peer_ability = 0;
        this.res_origin = 0;
    }
}
