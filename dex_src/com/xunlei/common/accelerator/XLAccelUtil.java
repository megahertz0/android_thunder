package com.xunlei.common.accelerator;

import com.xunlei.common.accelerator.interactor.XLAcceleratorInteractor;

public final class XLAccelUtil {
    private static XLAccelerator mXlAccelerator;

    static {
        mXlAccelerator = null;
    }

    public static synchronized XLAccelerator getAccelerator() {
        XLAccelerator xLAccelerator;
        synchronized (XLAccelUtil.class) {
            if (mXlAccelerator == null) {
                mXlAccelerator = new XLAcceleratorInteractor();
            }
            xLAccelerator = mXlAccelerator;
        }
        return xLAccelerator;
    }
}
