package com.xunlei.common.lixian;

import android.os.Handler;
import android.os.Looper;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.lixian.a.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.LinkedList;
import java.util.List;

public abstract class XLLixianRequestBase implements Comparable {
    private static int s_seq;
    private Handler m_handler;
    private List m_lsnlist;
    private int m_priority;
    private int m_seq;
    private Object m_userdata;

    static {
        s_seq = 1000;
    }

    public XLLixianRequestBase() {
        this.m_handler = null;
        this.m_userdata = null;
        this.m_priority = 100;
        int i = s_seq;
        s_seq = i + 1;
        this.m_seq = i;
        this.m_lsnlist = new LinkedList();
        if (Looper.myLooper() != null) {
            this.m_handler = new g(this);
        }
    }

    public static String readUTF8(b bVar) {
        byte[] f = bVar.f();
        return f != null ? CharsetConvert.testUTF8(f) ? new String(f, CharsetConvert.UTF_8) : new String(new String(f, "GB18030").getBytes(CharsetConvert.UTF_8), CharsetConvert.UTF_8) : BuildConfig.VERSION_NAME;
    }

    public void attachListener(XLLixianListener xLLixianListener) {
        if (xLLixianListener != null && !this.m_lsnlist.contains(xLLixianListener)) {
            this.m_lsnlist.add(xLLixianListener);
        }
    }

    public boolean cancelTask() {
        return XLLixianUtil.getInstance().cancelRequest(this);
    }

    public int commitTask() {
        return XLLixianUtil.getInstance().commitRequest(this);
    }

    public int compareTo(XLLixianRequestBase xLLixianRequestBase) {
        return this.m_priority - xLLixianRequestBase.m_priority;
    }

    public void detachListener(XLLixianListener xLLixianListener) {
        this.m_lsnlist.remove(xLLixianListener);
    }

    public abstract boolean execute();

    public abstract boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr);

    public void fireListener(Object... objArr) {
        int i = 0;
        if (this.m_lsnlist.size() > 0) {
            XLLixianListener xLLixianListener = (XLLixianListener) this.m_lsnlist.remove(0);
            Handler handler = this.m_handler;
            if (xLLixianListener.isGlobal() || handler == null) {
                handler = XLLixianUtil.getInstance().getHandler();
            }
            if (Looper.myLooper() == null) {
                i = 1;
            } else if (Looper.myLooper().getThread().getId() != handler.getLooper().getThread().getId()) {
                i = 1;
            }
            if (i != 0) {
                h hVar = new h(this);
                hVar.a = xLLixianListener;
                hVar.b = objArr;
                XLLixianMessage xLLixianMessage = new XLLixianMessage(100001, this);
                xLLixianMessage.putContent(hVar);
                xLLixianMessage.sendMessage(handler);
            } else if (fireEvent(xLLixianListener, objArr)) {
                fireListener(objArr);
            }
        }
    }

    public int getId() {
        return this.m_seq;
    }

    public XLLX_INITDATA getInitData() {
        return XLLixianUtil.getInstance().getInitData();
    }

    public Object getUserData() {
        return this.m_userdata;
    }

    public void handleMessage(XLLixianMessage xLLixianMessage) {
        if (xLLixianMessage.mWhat == 100001) {
            h hVar = (h) xLLixianMessage.mContent;
            if (fireEvent(hVar.a, hVar.b)) {
                fireListener(hVar.b);
            }
        }
    }

    public void putUserData(Object obj) {
        this.m_userdata = obj;
    }
}
