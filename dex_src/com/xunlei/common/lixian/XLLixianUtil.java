package com.xunlei.common.lixian;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.umeng.a;
import com.xunlei.common.httpclient.AsyncHttpClient;
import com.xunlei.common.httpclient.BaseHttpClient;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.b.c;
import com.xunlei.common.lixian.b.e;
import com.xunlei.common.lixian.b.o;
import com.xunlei.common.lixian.b.s;
import com.xunlei.common.lixian.b.y;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.LinkedList;
import java.util.List;

public class XLLixianUtil {
    private static final XLLixianUtil s_instance;
    private boolean isInited;
    private Context m_context;
    private Handler m_handler;
    private BaseHttpClient m_httpclient;
    private XLLX_INITDATA m_initdata;
    private List m_lsnlist;
    private String m_peerid;
    private XLLX_USERINFO m_userInfo;
    private String m_version;

    static {
        s_instance = new XLLixianUtil();
    }

    private XLLixianUtil() {
        this.m_peerid = a.d;
        this.m_context = null;
        this.m_initdata = new XLLX_INITDATA();
        this.m_userInfo = new XLLX_USERINFO();
        this.m_lsnlist = new LinkedList();
        this.m_handler = null;
        this.m_version = "1.1.2.156662";
        this.isInited = false;
        this.m_httpclient = null;
    }

    private int deleteLixianTask(long j, int i, Object obj, XLLixianListener xLLixianListener) {
        e eVar = new e();
        eVar.attachListener(xLLixianListener);
        eVar.putUserData(obj);
        eVar.a(i);
        if (getLixianTask(j) != null) {
            eVar.a(j);
        }
        return eVar.commitTask();
    }

    private int deleteLixianTask(long[] jArr, int i, Object obj, XLLixianListener xLLixianListener) {
        e eVar = new e();
        eVar.attachListener(xLLixianListener);
        eVar.putUserData(obj);
        eVar.a(i);
        for (int i2 = 0; i2 < jArr.length; i2++) {
            if (getLixianTask(jArr[i2]) != null) {
                eVar.a(jArr[i2]);
            }
        }
        return eVar.commitTask();
    }

    public static XLLixianUtil getInstance() {
        return s_instance;
    }

    public void attachListener(XLLixianListener xLLixianListener) {
        if (this.isInited) {
            xLLixianListener.setGlobal(true);
            this.m_lsnlist.add(xLLixianListener);
        }
    }

    public boolean cancelRequest(XLLixianRequestBase xLLixianRequestBase) {
        if (this.isInited) {
            for (int i = 0; i < this.m_lsnlist.size(); i++) {
                xLLixianRequestBase.detachListener((XLLixianListener) this.m_lsnlist.get(i));
            }
        }
        return false;
    }

    public int commitRequest(XLLixianRequestBase xLLixianRequestBase) {
        if (!this.isInited) {
            return -1;
        }
        for (int i = 0; i < this.m_lsnlist.size(); i++) {
            xLLixianRequestBase.attachListener((XLLixianListener) this.m_lsnlist.get(i));
        }
        return xLLixianRequestBase.execute() ? xLLixianRequestBase.getId() : 0;
    }

    public int createLixianBtTask(String str, String str2, String str3, int[] iArr, Object obj, XLLixianListener xLLixianListener) {
        if (!this.isInited) {
            return -1;
        }
        com.xunlei.common.lixian.b.a aVar = new com.xunlei.common.lixian.b.a();
        aVar.a(str, str3, str2, iArr);
        aVar.putUserData(obj);
        aVar.attachListener(xLLixianListener);
        return aVar.commitTask();
    }

    public int createLixianTask(XLLX_NEWTASK xllx_newtask, Object obj, XLLixianListener xLLixianListener) {
        if (!this.isInited) {
            return -1;
        }
        c cVar = new c();
        cVar.a(xllx_newtask);
        cVar.putUserData(obj);
        cVar.attachListener(xLLixianListener);
        return cVar.commitTask();
    }

    public int deleteTaskFromTrash(long j, Object obj, XLLixianListener xLLixianListener) {
        return !this.isInited ? -1 : deleteLixianTask(j, (int) XZBDevice.DOWNLOAD_LIST_ALL, obj, xLLixianListener);
    }

    public int deleteTaskToTrash(long j, Object obj, XLLixianListener xLLixianListener) {
        return !this.isInited ? -1 : deleteLixianTask(j, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, obj, xLLixianListener);
    }

    public int deleteTasksFromTrash(long[] jArr, Object obj, XLLixianListener xLLixianListener) {
        return !this.isInited ? -1 : deleteLixianTask(jArr, (int) XZBDevice.DOWNLOAD_LIST_ALL, obj, xLLixianListener);
    }

    public int deleteTasksToTrash(long[] jArr, Object obj, XLLixianListener xLLixianListener) {
        return !this.isInited ? -1 : deleteLixianTask(jArr, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, obj, xLLixianListener);
    }

    public void detachListener(XLLixianListener xLLixianListener) {
        if (this.isInited) {
            this.m_lsnlist.remove(xLLixianListener);
        }
    }

    public Context getContext() {
        return !this.isInited ? null : this.m_context;
    }

    public Handler getHandler() {
        return !this.isInited ? null : this.m_handler;
    }

    public BaseHttpClient getHttpClient() {
        if (this.m_httpclient == null) {
            this.m_httpclient = new AsyncHttpClient();
        }
        return this.m_httpclient;
    }

    public XLLX_INITDATA getInitData() {
        return !this.isInited ? null : this.m_initdata;
    }

    public XLLixianTask getLixianTask(long j) {
        return !this.isInited ? null : XLLixianTaskManager.getTask(j);
    }

    public String getPeerId() {
        return !this.isInited ? a.d : this.m_peerid;
    }

    public XLLX_USERINFO getUserInfo() {
        return !this.isInited ? null : this.m_userInfo;
    }

    public String getVersion() {
        return this.m_version;
    }

    public boolean init(Context context, XLLX_INITDATA xllx_initdata) {
        if (context == null || xllx_initdata == null || Looper.myLooper() == null) {
            return false;
        }
        if (xllx_initdata.userId == 0 || xllx_initdata.userJumpKey == a.d || xllx_initdata.userSessionId == a.d) {
            return false;
        }
        this.m_context = context;
        this.m_initdata = xllx_initdata;
        this.m_handler = new i(this);
        d.a().a(context);
        this.isInited = true;
        return true;
    }

    public int obtainLixianTasks(long j, boolean z, int i, int i2, Object obj, XLLixianListener xLLixianListener) {
        int i3 = -1;
        if (!this.isInited) {
            return -1;
        }
        if (i2 > 0) {
            i3 = i2;
        }
        s sVar = new s();
        sVar.a(j, z, i, i3);
        sVar.putUserData(obj);
        sVar.attachListener(xLLixianListener);
        return sVar.commitTask();
    }

    public int obtainLixianUserInfo(Object obj, XLLixianListener xLLixianListener) {
        if (!this.isInited) {
            return -1;
        }
        y yVar = new y();
        yVar.putUserData(obj);
        yVar.attachListener(xLLixianListener);
        return yVar.commitTask();
    }

    public void setHttpClient(BaseHttpClient baseHttpClient) {
        this.m_httpclient = baseHttpClient;
    }

    public void uninit() {
        this.m_context = null;
        this.m_initdata = null;
        this.isInited = false;
        XLLixianTaskManager.clear();
        this.m_lsnlist.clear();
    }

    public int updateLixianTasksDetail(XLLixianTask[] xLLixianTaskArr, Object obj, XLLixianListener xLLixianListener) {
        if (!this.isInited) {
            return -1;
        }
        o oVar = new o();
        oVar.a(xLLixianTaskArr);
        oVar.attachListener(xLLixianListener);
        oVar.putUserData(obj);
        return oVar.commitTask();
    }
}
