package com.xunlei.tdlive.im;

import com.xunlei.xiazaibao.BuildConfig;

public class SysNotifyMessage extends BaseMessage {
    public static final int FLAG_SYS_NOTIFY_FOLLOW = 2;
    public static final int FLAG_SYS_NOTIFY_ROOMSTATE_PAUSE = 0;
    public static final int FLAG_SYS_NOTIFY_ROOMSTATE_RESUME = 1;
    public static final int FLAG_SYS_NOTIFY_SHARE = 4;
    public static final int FLAG_SYS_NOTIFY_SYSMSG = 3;
    public int flag;
    public String msg;
    public String nickname;

    public SysNotifyMessage() {
        this.flag = -1;
        this.msg = BuildConfig.VERSION_NAME;
        this.nickname = BuildConfig.VERSION_NAME;
    }
}
