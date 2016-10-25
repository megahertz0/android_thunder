package com.xunlei.common.lixian;

import com.xunlei.common.lixian.b.u;

public class XLLixianNormalTask extends XLLixianTask {
    private static final long serialVersionUID = -6983275674632341141L;
    private XLLX_INDEXINFO m_indexinfo;

    protected XLLixianNormalTask(long j, int i) {
        super(j, i);
        this.m_indexinfo = new XLLX_INDEXINFO();
    }

    public boolean setData(XLLX_INDEXINFO xllx_indexinfo) {
        if (xllx_indexinfo.taskid != getTaskId()) {
            return false;
        }
        this.m_indexinfo = xllx_indexinfo;
        return true;
    }

    public int updateTaskDetail(XLLixianListener xLLixianListener) {
        u uVar = new u();
        uVar.a(getTaskId());
        uVar.attachListener(xLLixianListener);
        return uVar.commitTask();
    }
}
