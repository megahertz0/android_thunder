package com.xunlei.common.accelerator.interactor;

import com.xunlei.common.accelerator.bean.AccelInfoResultBean;
import com.xunlei.common.accelerator.bean.StartAccelResultBean;
import com.xunlei.common.accelerator.bean.StopAccelResultBean;
import com.xunlei.common.accelerator.bean.TryInfoResultBean;

public abstract class BaseInteractor {
    public abstract void onAlreadyUpgrade(String str);

    public abstract void onGetBandInfo(int i, int i2, String str, AccelInfoResultBean accelInfoResultBean);

    public abstract void onGetTryAccelInfo(int i, int i2, String str, TryInfoResultBean tryInfoResultBean);

    public abstract void onKeepAlive(int i, int i2, String str);

    public abstract void onQueryPortal(int i, int i2);

    public abstract void onStartAccel(int i, int i2, String str, StartAccelResultBean startAccelResultBean);

    public abstract void onStopAccel(int i, int i2, String str, StopAccelResultBean stopAccelResultBean);

    public abstract void onTimeCounterTimerTick(int i, int i2, boolean z);
}
