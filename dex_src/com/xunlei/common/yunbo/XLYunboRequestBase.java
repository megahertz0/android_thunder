package com.xunlei.common.yunbo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.LinkedList;
import java.util.List;

public abstract class XLYunboRequestBase implements Comparable<XLYunboRequestBase> {
    private static int s_seq;
    private final int MSG_CALLLISTENER;
    private int m_currentFireListener;
    private Handler m_handler;
    private List<XLYunboListener> m_lsnlist;
    private int m_priority;
    private int m_seq;
    private Object m_userdata;

    class RequestMessage extends XLYunboMassage {
        public Object[] args;
        public XLYunboListener listener;

        public RequestMessage(int i) {
            super(XLYunboRequestBase.this, i);
        }
    }

    public abstract boolean execute();

    public abstract boolean fireEvent(XLYunboListener xLYunboListener, Object... objArr);

    static {
        s_seq = 1000;
    }

    public XLYunboRequestBase() {
        this.m_handler = null;
        this.m_userdata = null;
        this.m_priority = 100;
        int i = s_seq;
        s_seq = i + 1;
        this.m_seq = i;
        this.m_lsnlist = new LinkedList();
        this.m_currentFireListener = 0;
        this.MSG_CALLLISTENER = 1000001;
        if (Looper.myLooper() != null) {
            this.m_handler = new Handler() {
                public void handleMessage(Message message) {
                    XLYunboRequestBase.this.handleMessage(message);
                }
            };
        }
    }

    public void handleMessage(Message message) {
        if (message.what == 1000001) {
            RequestMessage requestMessage = (RequestMessage) message.obj;
            if (fireEvent(requestMessage.listener, requestMessage.args)) {
                doFireListener(requestMessage.args);
            }
        }
    }

    public void attachListener(XLYunboListener xLYunboListener) {
        if (xLYunboListener != null) {
            this.m_lsnlist.add(xLYunboListener);
        }
    }

    public void detachListener(XLYunboListener xLYunboListener) {
        this.m_lsnlist.remove(xLYunboListener);
    }

    public int getId() {
        return this.m_seq;
    }

    public int compareTo(XLYunboRequestBase xLYunboRequestBase) {
        return this.m_priority - xLYunboRequestBase.m_priority;
    }

    public void fireListener(Object... objArr) {
        this.m_currentFireListener = -1;
        doFireListener(objArr);
    }

    private void doFireListener(Object... objArr) {
        XLYunboListener xLYunboListener;
        do {
            this.m_currentFireListener++;
            if (this.m_currentFireListener >= 0 && this.m_currentFireListener < this.m_lsnlist.size()) {
                Object obj;
                xLYunboListener = (XLYunboListener) this.m_lsnlist.get(this.m_currentFireListener);
                Handler handler = this.m_handler;
                if (xLYunboListener.isGlobal() || handler == null) {
                    handler = XLYunboUtil.getInstance().getHandler();
                }
                int i;
                if (Looper.myLooper() == null) {
                    i = 1;
                } else if (Looper.myLooper().getThread().getId() != handler.getLooper().getThread().getId()) {
                    i = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    RequestMessage requestMessage = new RequestMessage(1000001);
                    requestMessage.listener = xLYunboListener;
                    requestMessage.args = objArr;
                    requestMessage.sendMessage(handler);
                    return;
                }
            } else {
                return;
            }
        } while (fireEvent(xLYunboListener, objArr));
    }

    public int commitTask() {
        return XLYunboUtil.getInstance().commitRequest(this);
    }

    public boolean cancelTask() {
        return XLYunboUtil.getInstance().cancelRequest(this);
    }

    public void putUserData(Object obj) {
        this.m_userdata = obj;
    }

    public Object getUserData() {
        return this.m_userdata;
    }

    public XLYB_INITDATA getInitData() {
        return XLYunboUtil.getInstance().getInitData();
    }
}
