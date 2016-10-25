package com.xunlei.common.yunbo;

import android.os.Handler;
import android.os.Message;

public class XLYunboMassage {
    public static final int MSG_SYSBASE = 1000000;
    public static final int MSG_TASKBEGINEXEC = 10001;
    public static final int MSG_TASKCANCELED = 10003;
    public static final int MSG_TASKCREATED = 10000;
    public static final int MSG_TASKFINISHED = 10002;
    public static final int MSG_USERBASE = 2000000;
    private int m_msgid;
    private XLYunboRequestBase m_reqTask;

    public XLYunboMassage(XLYunboRequestBase xLYunboRequestBase, int i) {
        this.m_reqTask = null;
        this.m_msgid = 0;
        this.m_reqTask = xLYunboRequestBase;
        this.m_msgid = i;
    }

    public void sendMessage(Handler handler) {
        Message obtainMessage = handler.obtainMessage(this.m_msgid);
        obtainMessage.setTarget(handler);
        obtainMessage.obj = this;
        handler.sendMessage(obtainMessage);
    }

    public XLYunboRequestBase getCurrentRequest() {
        return this.m_reqTask;
    }
}
