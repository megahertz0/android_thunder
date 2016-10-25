package com.xunlei.common.member;

public interface XLUserInfo {

    public enum USERINFOKEY {
        VasId,
        OtherVipError,
        UserName,
        EncryptedPassword,
        PasswordCheckNum,
        UserID,
        UserNewNo,
        IsSubAccount,
        NickName,
        Account,
        SessionID,
        JumpKey,
        IsVip,
        Rank,
        Order,
        ExpireDate,
        VasType,
        PayId,
        PayName,
        isExpVip,
        Country,
        Province,
        City,
        IsSpecialNum,
        Role,
        IsAutoDeduct,
        IsRemind,
        TodayScore,
        AllowScore,
        PersonalSign,
        VipGrow,
        VipDayGrow,
        ImgURL,
        vip_level,
        IsYear,
        Rigster,
        Sex,
        Birthday,
        PhoneNumber,
        other_IsVip,
        other_VipLevel,
        other_ExpireDate,
        other_VasType,
        other_PayName,
        other_VipGrow,
        other_VipDayGrow,
        other_IsAutoDeduct,
        other_IsRemind,
        other_PayId,
        other_IsYear,
        other_Register
    }

    void clearUserData();

    void dump();

    int getIntValue(USERINFOKEY userinfokey);

    long getLongValue(USERINFOKEY userinfokey);

    String getStringValue(USERINFOKEY userinfokey);
}
