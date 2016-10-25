package com.xunlei.common.accelerator;

import android.content.Context;
import com.xunlei.common.accelerator.bean.KnParams;
import com.xunlei.common.accelerator.bean.XLAccelBandInfo;

public interface XLAccelerator {
    void attachListener(XLOnAccelListener xLOnAccelListener);

    void detachListener(XLOnAccelListener xLOnAccelListener);

    String getBandInfo();

    XLAccelBandInfo getBandInfoObject();

    KnParams getKnParams();

    int getRemainTime();

    String getTryInfo();

    String getUserInfo();

    String getVersion();

    boolean init(Context context, String str, String str2);

    int isKuaiNiao();

    void queryStatus();

    void reInit();

    void recoverQuery();

    void saveAccelState(Context context);

    void startAccel();

    void stopAccel();

    boolean uninit();

    void updateUserInfo();
}
