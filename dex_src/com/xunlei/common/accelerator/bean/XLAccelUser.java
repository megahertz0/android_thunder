package com.xunlei.common.accelerator.bean;

import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.xiazaibao.BuildConfig;

public class XLAccelUser {
    public String mSessionID;
    public String mUserID;
    public int mUserType;
    public int mVipType;

    public XLAccelUser() {
        this.mUserID = "0";
        this.mSessionID = BuildConfig.VERSION_NAME;
        this.mUserType = 2;
        this.mVipType = -1;
    }

    public XLAccelUser(XLUserInfo xLUserInfo, int i, KnParams knParams) {
        this.mUserID = "0";
        this.mSessionID = BuildConfig.VERSION_NAME;
        this.mUserType = 2;
        this.mVipType = -1;
        try {
            this.mUserID = String.valueOf(Long.parseLong(xLUserInfo.getStringValue(USERINFOKEY.UserID)));
            int intValue = xLUserInfo.getIntValue(USERINFOKEY.VasType);
            int intValue2 = xLUserInfo.getIntValue(USERINFOKEY.IsVip);
            this.mSessionID = xLUserInfo.getStringValue(USERINFOKEY.SessionID);
            this.mVipType = intValue;
            if (i == 1) {
                if (!(intValue == 5 && intValue2 == 1)) {
                    this.mVipType = 100;
                }
            }
            if (isTryUser(intValue, intValue2, i, knParams)) {
                this.mUserType = 2;
            } else {
                this.mUserType = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isTryUser(int i, int i2, int i3, KnParams knParams) {
        boolean z;
        if (i == 5) {
            z = false;
        } else {
            z = true;
        }
        if (i3 == 1 && knParams != null) {
            i2 = knParams.getOther_IsVip();
            z = false;
        }
        if (i2 != 0 && i2 != 2) {
            return z;
        }
        this.mVipType = 0;
        return true;
    }

    public boolean isTryUser() {
        return this.mUserType != 1;
    }
}
