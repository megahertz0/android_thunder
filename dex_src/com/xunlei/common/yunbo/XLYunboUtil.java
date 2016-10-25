package com.xunlei.common.yunbo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.umeng.a;
import com.xunlei.common.httpclient.AsyncHttpClient;
import com.xunlei.common.httpclient.BaseHttpClient;
import com.xunlei.common.yunbo.request.XLYunboAddPlayRecord;
import com.xunlei.common.yunbo.request.XLYunboDeletePlayHistroy;
import com.xunlei.common.yunbo.request.XLYunboGetBTSubList;
import com.xunlei.common.yunbo.request.XLYunboGetPlayList;
import com.xunlei.common.yunbo.request.XLYunboObtainVideoPlayUrl;
import com.xunlei.common.yunbo.request.XLYunboQueryCloudProcess;
import com.xunlei.common.yunbo.request.XLYunboQueryScreenShot;
import java.util.LinkedList;
import java.util.List;

public class XLYunboUtil {
    private static XLYunboUtil s_instance;
    private boolean isInited;
    private Context m_context;
    private Handler m_handler;
    private BaseHttpClient m_httpclient;
    private XLYB_INITDATA m_initdata;
    private List<XLYunboListener> m_lsnlist;
    private String m_peerid;
    private String m_version;

    static {
        s_instance = new XLYunboUtil();
    }

    private XLYunboUtil() {
        this.m_peerid = a.d;
        this.m_context = null;
        this.m_initdata = new XLYB_INITDATA();
        this.m_lsnlist = new LinkedList();
        this.m_handler = null;
        this.m_httpclient = null;
        this.m_version = "@version@";
        this.isInited = false;
    }

    public static XLYunboUtil getInstance() {
        return s_instance;
    }

    @SuppressLint({"HandlerLeak"})
    public boolean init(Context context, XLYB_INITDATA xlyb_initdata) {
        if (context == null || xlyb_initdata == null || Looper.myLooper() == null) {
            return false;
        }
        this.m_context = context;
        if (this.m_initdata == null) {
            this.m_initdata = new XLYB_INITDATA();
        }
        this.m_initdata.copyfrom(xlyb_initdata);
        this.m_handler = new AnonymousClass_1(this);
        this.isInited = true;
        return true;
    }

    public void uninit() {
        this.isInited = false;
        this.m_context = null;
        this.m_initdata = new XLYB_INITDATA();
    }

    public void setHttpClient(BaseHttpClient baseHttpClient) {
        this.m_httpclient = baseHttpClient;
    }

    public BaseHttpClient getHttpClient() {
        if (this.m_httpclient == null) {
            this.m_httpclient = new AsyncHttpClient();
        }
        return this.m_httpclient;
    }

    public String getVersion() {
        return this.m_version;
    }

    public void setUserInfo(boolean z, long j, String str, int i) {
        if (this.isInited) {
            if (this.m_initdata == null) {
                this.m_initdata = new XLYB_INITDATA();
            }
            this.m_initdata.userIsLogin = z;
            this.m_initdata.userId = j;
            this.m_initdata.userSessionId = str;
            this.m_initdata.userVipLevel = i;
        }
    }

    public void attachListener(XLYunboListener xLYunboListener) {
        if (this.isInited) {
            xLYunboListener.setGlobal(true);
            this.m_lsnlist.add(xLYunboListener);
        }
    }

    public void detachListener(XLYunboListener xLYunboListener) {
        if (this.isInited) {
            this.m_lsnlist.remove(xLYunboListener);
        }
    }

    public XLYB_INITDATA getInitData() {
        return !this.isInited ? null : this.m_initdata;
    }

    public String getPeerId() {
        return this.m_peerid;
    }

    public Context getContext() {
        return this.m_context;
    }

    public Handler getHandler() {
        return this.m_handler;
    }

    public int commitRequest(XLYunboRequestBase xLYunboRequestBase) {
        if (!this.isInited) {
            return -1;
        }
        for (int i = 0; i < this.m_lsnlist.size(); i++) {
            xLYunboRequestBase.attachListener((XLYunboListener) this.m_lsnlist.get(i));
        }
        return xLYunboRequestBase.execute() ? xLYunboRequestBase.getId() : 0;
    }

    public boolean cancelRequest(XLYunboRequestBase xLYunboRequestBase) {
        for (int i = 0; i < this.m_lsnlist.size(); i++) {
            xLYunboRequestBase.detachListener((XLYunboListener) this.m_lsnlist.get(i));
        }
        return false;
    }

    public int obtainVideoList(int i, int i2, int i3, int i4, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboGetPlayList xLYunboGetPlayList = new XLYunboGetPlayList();
        xLYunboGetPlayList.setReqInfo(i, i2, i3, i4);
        xLYunboGetPlayList.putUserData(obj);
        xLYunboGetPlayList.attachListener(xLYunboListener);
        return xLYunboGetPlayList.commitTask();
    }

    public int obtainVideoPlayUrl(String str, String str2, String str3, String str4, int i, int i2, XLYB_VODINFO xlyb_vodinfo, int i3, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYB_REQPLAYINFO xlyb_reqplayinfo = new XLYB_REQPLAYINFO();
        xlyb_reqplayinfo.clientid = str;
        xlyb_reqplayinfo.rsa_module = str2;
        xlyb_reqplayinfo.rsa_exponent = str3;
        xlyb_reqplayinfo.verify = str4;
        xlyb_reqplayinfo.operationid = i;
        xlyb_reqplayinfo.vod_type = i2;
        xlyb_reqplayinfo.url = xlyb_vodinfo.src_url;
        xlyb_reqplayinfo.cid = xlyb_vodinfo.cid;
        xlyb_reqplayinfo.gcid = xlyb_vodinfo.gcid;
        xlyb_reqplayinfo.filesize = xlyb_vodinfo.filesize;
        xlyb_reqplayinfo.index = i3;
        return obtainVideoPlayUrl(xlyb_reqplayinfo, obj, xLYunboListener);
    }

    public int obtainVideoPlayUrl(XLYB_REQPLAYINFO xlyb_reqplayinfo, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboObtainVideoPlayUrl xLYunboObtainVideoPlayUrl = new XLYunboObtainVideoPlayUrl();
        xLYunboObtainVideoPlayUrl.setUrlInfo(xlyb_reqplayinfo);
        xLYunboObtainVideoPlayUrl.putUserData(obj);
        xLYunboObtainVideoPlayUrl.attachListener(xLYunboListener);
        return xLYunboObtainVideoPlayUrl.commitTask();
    }

    public int obtainVideoProcess(String str, int i, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboQueryCloudProcess xLYunboQueryCloudProcess = new XLYunboQueryCloudProcess();
        xLYunboQueryCloudProcess.addUrl(new String[]{str}, i);
        xLYunboQueryCloudProcess.putUserData(obj);
        xLYunboQueryCloudProcess.attachListener(xLYunboListener);
        return xLYunboQueryCloudProcess.commitTask();
    }

    public int obtainVideosProcess(String[] strArr, int i, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboQueryCloudProcess xLYunboQueryCloudProcess = new XLYunboQueryCloudProcess();
        xLYunboQueryCloudProcess.addUrl(strArr, i);
        xLYunboQueryCloudProcess.putUserData(obj);
        xLYunboQueryCloudProcess.attachListener(xLYunboListener);
        return xLYunboQueryCloudProcess.commitTask();
    }

    public int obtainVideoScreenShot(String str, int i, int i2, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboQueryScreenShot xLYunboQueryScreenShot = new XLYunboQueryScreenShot();
        xLYunboQueryScreenShot.setVideos(str, false, i, i2);
        xLYunboQueryScreenShot.putUserData(obj);
        xLYunboQueryScreenShot.attachListener(xLYunboListener);
        return xLYunboQueryScreenShot.commitTask();
    }

    public int obtainVideoPosters(String str, int i, int i2, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboQueryScreenShot xLYunboQueryScreenShot = new XLYunboQueryScreenShot();
        xLYunboQueryScreenShot.setVideos(str, true, i, i2);
        xLYunboQueryScreenShot.putUserData(obj);
        xLYunboQueryScreenShot.attachListener(xLYunboListener);
        return xLYunboQueryScreenShot.commitTask();
    }

    public int obtainBtSubfileList(String str, int i, int i2, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboGetBTSubList xLYunboGetBTSubList = new XLYunboGetBTSubList();
        xLYunboGetBTSubList.setBtInfo(str, i, i2);
        xLYunboGetBTSubList.putUserData(obj);
        xLYunboGetBTSubList.attachListener(xLYunboListener);
        return xLYunboGetBTSubList.commitTask();
    }

    public int deleteVideo(String str, String str2, int i, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboDeletePlayHistroy xLYunboDeletePlayHistroy = new XLYunboDeletePlayHistroy();
        xLYunboDeletePlayHistroy.setTarget(i);
        xLYunboDeletePlayHistroy.delete(str, str2);
        xLYunboDeletePlayHistroy.putUserData(obj);
        xLYunboDeletePlayHistroy.attachListener(xLYunboListener);
        return xLYunboDeletePlayHistroy.commitTask();
    }

    public int deleteVideos(XLYB_VODINFO[] xlyb_vodinfoArr, int i, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboDeletePlayHistroy xLYunboDeletePlayHistroy = new XLYunboDeletePlayHistroy();
        for (XLYB_VODINFO xlyb_vodinfo : xlyb_vodinfoArr) {
            xLYunboDeletePlayHistroy.delete(xlyb_vodinfo.url, xlyb_vodinfo.url_hash);
        }
        xLYunboDeletePlayHistroy.setTarget(i);
        xLYunboDeletePlayHistroy.putUserData(obj);
        xLYunboDeletePlayHistroy.attachListener(xLYunboListener);
        return xLYunboDeletePlayHistroy.commitTask();
    }

    public int deleteAllVideos(int i, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboDeletePlayHistroy xLYunboDeletePlayHistroy = new XLYunboDeletePlayHistroy();
        xLYunboDeletePlayHistroy.deleteAll();
        xLYunboDeletePlayHistroy.setTarget(i);
        xLYunboDeletePlayHistroy.putUserData(obj);
        xLYunboDeletePlayHistroy.attachListener(xLYunboListener);
        return xLYunboDeletePlayHistroy.commitTask();
    }

    public int addVideo2Favorite(XLYB_ADDVIDEO[] xlyb_addvideoArr, Object obj, XLYunboListener xLYunboListener) {
        if (!this.isInited) {
            return -1;
        }
        XLYunboAddPlayRecord xLYunboAddPlayRecord = new XLYunboAddPlayRecord();
        xLYunboAddPlayRecord.addUrl(xlyb_addvideoArr);
        xLYunboAddPlayRecord.putUserData(obj);
        xLYunboAddPlayRecord.attachListener(xLYunboListener);
        return xLYunboAddPlayRecord.commitTask();
    }
}
