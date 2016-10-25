package com.xunlei.common.yunbo;

import com.xunlei.xiazaibao.BuildConfig;
import java.io.Serializable;

public class XLYB_INITDATA implements Serializable {
    private static final long serialVersionUID = 9028468333153278510L;
    public int platform;
    public String productName;
    public String productVer;
    public long userId;
    public boolean userIsLogin;
    public String userSessionId;
    public int userVipLevel;

    public XLYB_INITDATA() {
        this.userIsLogin = false;
        this.userId = 0;
        this.userSessionId = BuildConfig.VERSION_NAME;
        this.userVipLevel = 0;
        this.productVer = BuildConfig.VERSION_NAME;
        this.productName = BuildConfig.VERSION_NAME;
        this.platform = 2;
    }

    public void copyfrom(XLYB_INITDATA xlyb_initdata) {
        if (xlyb_initdata != null) {
            this.userIsLogin = xlyb_initdata.userIsLogin;
            this.platform = xlyb_initdata.platform;
            this.productName = xlyb_initdata.productName;
            this.productVer = xlyb_initdata.productVer;
            this.userId = xlyb_initdata.userId;
            this.userSessionId = xlyb_initdata.userSessionId;
        }
    }
}
