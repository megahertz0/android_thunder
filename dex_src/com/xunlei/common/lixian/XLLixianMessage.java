package com.xunlei.common.lixian;

import android.os.Handler;

public class XLLixianMessage {
    public static final int MSG_CALLLISTENER = 100001;
    public static final int MSG_TASKBEGINEXEC = 10001;
    public static final int MSG_TASKCANCELED = 10003;
    public static final int MSG_TASKCORE_MAX = 200000;
    public static final int MSG_TASKCORE_MIN = 100000;
    public static final int MSG_TASKCREATED = 10000;
    public static final int MSG_TASKFINISHED = 10002;
    public Object mContent;
    public XLLixianRequestBase mSender;
    public int mWhat;

    public XLLixianMessage(int i) {
        this.mWhat = 0;
        this.mSender = null;
        this.mContent = null;
        this.mWhat = i;
    }

    public XLLixianMessage(int i, XLLixianRequestBase xLLixianRequestBase) {
        this.mWhat = 0;
        this.mSender = null;
        this.mContent = null;
        this.mWhat = i;
        this.mSender = xLLixianRequestBase;
    }

    public void putContent(Object obj) {
        this.mContent = obj;
    }

    public void sendMessage(Handler handler) {
        handler.obtainMessage(this.mWhat, this).sendToTarget();
    }
}
