package com.xunlei.tdlive.user;

import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;

// compiled from: GlobalXLOnUserListener.java
class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void run() {
        XLUserInfo currentUser = XLUserUtil.getInstance().getCurrentUser();
        String stringValue = currentUser.getStringValue(USERINFOKEY.UserID);
        String stringValue2 = currentUser.getStringValue(USERINFOKEY.SessionID);
        String stringValue3 = currentUser.getStringValue(USERINFOKEY.NickName);
        if (stringValue3 == null || stringValue3.length() <= 0) {
            stringValue3 = currentUser.getStringValue(USERINFOKEY.UserName);
        }
        if (stringValue3 == null || stringValue3.length() <= 0) {
            stringValue3 = currentUser.getStringValue(USERINFOKEY.UserNewNo);
        }
        this.a.a(stringValue, stringValue2, stringValue3, currentUser.getStringValue(USERINFOKEY.Sex));
    }
}
