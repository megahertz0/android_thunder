package com.xunlei.common.lixian;

final class c extends XLLixianListener {
    private /* synthetic */ XLLixianBtTask a;

    c(XLLixianBtTask xLLixianBtTask) {
        this.a = xLLixianBtTask;
    }

    public final boolean OnObtainBtSeedFileContent(int i, String str, int i2, byte[] bArr, Object obj) {
        if (i == 0) {
            XLLixianBtTask.access$002(this.a, bArr);
        }
        return true;
    }
}
