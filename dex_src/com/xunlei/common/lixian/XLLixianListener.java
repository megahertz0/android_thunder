package com.xunlei.common.lixian;

public class XLLixianListener {
    private boolean m_bGlobal;

    public XLLixianListener() {
        this.m_bGlobal = false;
    }

    public boolean OnCreateLixianBtTask(int i, String str, int i2, long j, String str2, int[] iArr, XLLixianTask[] xLLixianTaskArr, Object obj) {
        return true;
    }

    public boolean OnCreateLixianTask(int i, String str, int i2, XLLixianTask xLLixianTask, Object obj) {
        return true;
    }

    public boolean OnDeleteTasksFromTrash(int i, String str, int i2, XLLX_TASKRESPSTATUS[] xllx_taskrespstatusArr, Object obj) {
        return false;
    }

    public boolean OnDeleteTasksToTrash(int i, String str, int i2, XLLX_TASKRESPSTATUS[] xllx_taskrespstatusArr, Object obj) {
        return false;
    }

    public boolean OnObtainBtFileList(int i, String str, int i2, XLLX_BTFILE[] xllx_btfileArr, String str2, Object obj) {
        return true;
    }

    public boolean OnObtainBtSeedFileContent(int i, String str, int i2, byte[] bArr, Object obj) {
        return true;
    }

    public boolean OnObtainBtSubTasks(int i, String str, int i2, XLLixianTask xLLixianTask, XLLixianTask[] xLLixianTaskArr, int i3, int i4, Object obj) {
        return true;
    }

    public boolean OnObtainLixianTasks(int i, String str, int i2, XLLixianTask[] xLLixianTaskArr, int i3, Object obj) {
        return true;
    }

    public boolean OnObtainLixianUserInfo(int i, String str, int i2, XLLX_USERINFO xllx_userinfo, Object obj) {
        return true;
    }

    public boolean OnUpdateLixianTasksDetail(int i, String str, int i2, XLLixianTask[] xLLixianTaskArr, Object obj) {
        return true;
    }

    protected final boolean isGlobal() {
        return this.m_bGlobal;
    }

    protected final void setGlobal(boolean z) {
        this.m_bGlobal = z;
    }
}
