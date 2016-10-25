package com.xunlei.common.lixian;

final class d extends XLLixianListener {
    private /* synthetic */ XLLixianBtTask a;

    d(XLLixianBtTask xLLixianBtTask) {
        this.a = xLLixianBtTask;
    }

    public final boolean OnObtainBtFileList(int i, String str, int i2, XLLX_BTFILE[] xllx_btfileArr, String str2, Object obj) {
        if (i == 0) {
            for (Object obj2 : xllx_btfileArr) {
                XLLixianBtTask.access$100(this.a).add(obj2);
            }
        }
        return true;
    }
}
