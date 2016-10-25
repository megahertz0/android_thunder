package com.xunlei.common.lixian;

import com.umeng.a;
import com.xunlei.tdlive.R;
import java.io.Serializable;

public class XLLixianTask implements Serializable {
    private static final long serialVersionUID = -2654474433891369207L;
    protected XLLX_TASKBASIC m_task;
    protected XLLX_TASKDETAIL m_taskdetail;

    protected XLLixianTask(long j, int i) {
        this.m_task = new XLLX_TASKBASIC();
        this.m_taskdetail = new XLLX_TASKDETAIL();
        this.m_task.taskid = j;
        this.m_task.res_type = i;
    }

    public XLLX_TASKDETAIL getDetailInfo() {
        return this.m_taskdetail;
    }

    public String getPreviewImageUrl_140x180() {
        if (this.m_taskdetail == null || this.m_taskdetail.gcid.isEmpty()) {
            return a.d;
        }
        return String.format("http://i%d.xlpan.kanimg.com/poster/%s_140X180.jpg", new Object[]{Integer.valueOf(Integer.parseInt(this.m_taskdetail.gcid.substring(0, 1), R.styleable.Toolbar_titleMarginBottom) % 5), this.m_taskdetail.gcid});
    }

    public XLLX_RESTYPE getResType() {
        return XLLX_RESTYPE.get(this.m_task.res_type);
    }

    public long getTaskId() {
        return this.m_task.taskid;
    }

    public boolean isBtTask() {
        return getResType() == XLLX_RESTYPE.Bt_All;
    }

    public boolean isNormalTask() {
        return getResType() != XLLX_RESTYPE.Bt_All;
    }

    public boolean setData(XLLX_TASKBASIC xllx_taskbasic) {
        if (xllx_taskbasic.taskid != this.m_task.taskid) {
            return false;
        }
        this.m_task = xllx_taskbasic;
        return true;
    }

    public boolean setData(XLLX_TASKDETAIL xllx_taskdetail) {
        if (xllx_taskdetail.taskid != getTaskId()) {
            return false;
        }
        this.m_taskdetail = xllx_taskdetail;
        return true;
    }
}
