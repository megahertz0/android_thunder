package com.xunlei.common.lixian;

import com.umeng.a;

public class XLLX_INITDATA {
    public String peerId;
    public long userId;
    public String userJumpKey;
    public String userName;
    public String userOldName;
    public String userSessionId;
    public byte userVipLevel;

    public XLLX_INITDATA() {
        this.userId = 0;
        this.peerId = "0000000000000000";
        this.userName = a.d;
        this.userOldName = a.d;
        this.userVipLevel = (byte) 0;
        this.userSessionId = a.d;
        this.userJumpKey = a.d;
    }
}
