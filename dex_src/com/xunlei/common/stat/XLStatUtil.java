package com.xunlei.common.stat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.a;
import com.xunlei.common.stat.base.a.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressLint({"UseSparseArrays"})
public final class XLStatUtil implements b {
    private static int MSG_REPORT_ERROR_OPERATION = 0;
    private static final String PLATFORM = "2";
    private static final String REPORT_BASE_URL = "http://stat.login.xunlei.com:1800/report?";
    private static final int REPORT_ONCE_NUM = 3;
    private static int REPORT_REMOVE_BASE = 0;
    private static int REPORT_TIMER_BASE = 0;
    private static final int REPORT_TIME_EXPIRE = 30000;
    public static boolean mAcceptPhoneCode;
    private static XLStatUtil mInstance;
    private String TAG;
    private String mAppName;
    private int mBusinessType;
    private String mClientVersion;
    private Context mContext;
    private int mCurrentRemoveMsgId;
    private int mCurrentStatTimerId;
    private String mDeviceMacAddress;
    private Handler mHandler;
    private HttpClient mHttpClient;
    private boolean mIsMoudleInit;
    private com.xunlei.common.stat.base.b mLocker;
    private XLStatItem mLoginFastPhoneCode;
    private String mPeerId;
    private XLStatItem mRegisterPhoneCode;
    private volatile boolean mReportThreadFin;
    private Runnable mRunner;
    private String mSDKVersion;
    private a mStatDBManager$797505f8;
    private Thread mThread;
    private boolean mTimerStarted;
    private List<XLStatPack> mXLReportStatList;
    private Map<Integer, XLStatItem> mXLStatRequestMap;

    static {
        mAcceptPhoneCode = false;
        REPORT_REMOVE_BASE = 251662626;
        REPORT_TIMER_BASE = 252269168;
        MSG_REPORT_ERROR_OPERATION = -268431324;
        mInstance = null;
    }

    private XLStatUtil() {
        this.TAG = XLStatUtil.class.getSimpleName();
        this.mDeviceMacAddress = null;
        this.mXLStatRequestMap = new HashMap();
        this.mXLReportStatList = new ArrayList();
        this.mStatDBManager$797505f8 = null;
        this.mAppName = com.umeng.a.d;
        this.mClientVersion = com.umeng.a.d;
        this.mSDKVersion = com.umeng.a.d;
        this.mBusinessType = -1;
        this.mCurrentStatTimerId = 0;
        this.mCurrentRemoveMsgId = 0;
        this.mPeerId = com.umeng.a.d;
        this.mContext = null;
        this.mHttpClient = null;
        this.mIsMoudleInit = false;
        this.mThread = null;
        this.mLocker = new com.xunlei.common.stat.base.b();
        this.mReportThreadFin = false;
        this.mRegisterPhoneCode = null;
        this.mLoginFastPhoneCode = null;
        this.mTimerStarted = false;
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                XLStatUtil.this.handleMessage(message);
            }
        };
        this.mRunner = new Runnable() {
            public void run() {
                while (true) {
                    XLStatUtil.this.mLocker.a(0);
                    XLLog.v(XLStatUtil.this.TAG, "wait lock release");
                    if (XLStatUtil.this.mReportThreadFin) {
                        XLLog.v(XLStatUtil.this.TAG, "report thread finish!");
                        return;
                    } else if (XLStatUtil.this.mXLReportStatList.size() >= 3) {
                        XLStatUtil.this.reportStatToServer(XLStatUtil.this.packReportStatUrl());
                    }
                }
            }
        };
    }

    public static XLStatUtil getInstance() {
        if (mInstance == null) {
            mInstance = new XLStatUtil();
        }
        return mInstance;
    }

    public final void init(Context context, int i, String str, String str2, String str3) {
        if (!this.mIsMoudleInit) {
            XLLog.d(this.TAG, new StringBuilder("init stat moudle id = ").append(hashCode()).toString());
            this.mBusinessType = i;
            this.mClientVersion = str;
            this.mSDKVersion = str2;
            this.mPeerId = str3;
            this.mXLStatRequestMap.clear();
            this.mContext = context;
            this.mHttpClient = new DefaultHttpClient();
            this.mDeviceMacAddress = getDeviceMac();
            this.mAppName = this.mContext.getApplicationInfo().packageName;
            int i2 = REPORT_TIMER_BASE;
            REPORT_TIMER_BASE = i2 + 1;
            this.mCurrentStatTimerId = i2;
            REPORT_REMOVE_BASE += 100;
            this.mCurrentRemoveMsgId = REPORT_REMOVE_BASE;
            com.xunlei.common.stat.base.a.a(this.mContext);
            this.mThread = new Thread(this.mRunner);
            this.mThread.start();
            this.mReportThreadFin = false;
            this.mStatDBManager$797505f8 = new a(this.mContext);
            loadReportsFromDataBase();
            if (this.mXLReportStatList.size() >= 3) {
                startStatTimer();
            }
            this.mIsMoudleInit = true;
        }
    }

    public final void uninit() {
        if (this.mIsMoudleInit) {
            com.xunlei.common.stat.base.a.a();
            saveReportsToDataBase();
            this.mReportThreadFin = true;
            this.mLocker.a();
            this.mStatDBManager$797505f8.c();
            this.mXLStatRequestMap.clear();
            this.mIsMoudleInit = false;
        }
    }

    public final int getBusinessType() {
        return this.mBusinessType;
    }

    public final synchronized void registerStatReq(int i) {
        XLStatItem xLStatItem = new XLStatItem();
        xLStatItem.mTaskCookie = i;
        xLStatItem.mRequestTime = getCurrentTime();
        this.mXLStatRequestMap.put(Integer.valueOf(i), xLStatItem);
    }

    public final synchronized void registerStatReq(int i, int i2) {
        XLStatItem xLStatItem = new XLStatItem();
        xLStatItem.mTaskCookie = i;
        xLStatItem.mRequestTime = getCurrentTime();
        xLStatItem.mRequestCommandID = i2;
        this.mXLStatRequestMap.put(Integer.valueOf(i), xLStatItem);
    }

    public final synchronized void registerSpecialStatReq(int i, int i2) {
        XLStatItem xLStatItem = new XLStatItem();
        xLStatItem.mTaskCookie = i;
        xLStatItem.mRequestTime = getCurrentTime();
        if (i2 == 1) {
            this.mRegisterPhoneCode = xLStatItem;
        } else {
            this.mLoginFastPhoneCode = xLStatItem;
        }
    }

    public final synchronized void reportSpecialStat(int i, XLStatPack xLStatPack) {
        XLStatItem xLStatItem = this.mLoginFastPhoneCode;
        if (i == 1) {
            xLStatItem = this.mRegisterPhoneCode;
        }
        if (xLStatItem != null) {
            xLStatPack.mRespTime = (double) (getCurrentTime() - xLStatItem.mRequestTime);
            xLStatPack.mReportDate = getReportDate();
            this.mXLReportStatList.add(0, xLStatPack);
            if (i == 1) {
                this.mRegisterPhoneCode = null;
            } else {
                this.mLoginFastPhoneCode = null;
            }
            if (this.mXLReportStatList.size() >= 3) {
                startStatTimer();
            }
            XLLog.d(this.TAG, new StringBuilder("report command id = ").append(xLStatPack.mCommandID).toString());
        }
    }

    private synchronized void unRegisterStatReq(int i) {
        this.mXLStatRequestMap.remove(Integer.valueOf(i));
    }

    public final synchronized void report(int i, XLStatPack xLStatPack) {
        XLStatItem statReq = getStatReq(i);
        if (statReq != null) {
            xLStatPack.mRespTime = (double) (getCurrentTime() - statReq.mRequestTime);
            xLStatPack.mReportDate = getReportDate();
            this.mXLReportStatList.add(0, xLStatPack);
            unRegisterStatReq(i);
            if (this.mXLReportStatList.size() >= 3) {
                startStatTimer();
            }
            XLLog.d(this.TAG, new StringBuilder("report command id = ").append(xLStatPack.mCommandID).toString());
        }
    }

    public final synchronized void report(int i, XLStatPack xLStatPack, boolean z) {
        XLStatItem statReq = getStatReq(i);
        if (statReq != null) {
            xLStatPack.mRespTime = (double) (getCurrentTime() - statReq.mRequestTime);
            xLStatPack.mReportDate = getReportDate();
            if (z) {
                xLStatPack.mCommandID = statReq.mRequestCommandID;
            }
            this.mXLReportStatList.add(0, xLStatPack);
            unRegisterStatReq(i);
            if (this.mXLReportStatList.size() >= 3) {
                startStatTimer();
            }
            XLLog.d(this.TAG, new StringBuilder("report command id = ").append(xLStatPack.mCommandID).toString());
        }
    }

    public final void onTimerTick(int i) {
        if (this.mCurrentStatTimerId == i) {
            XLLog.d(this.TAG, new StringBuilder("onTimerTick mXLReportStatList.size = ").append(this.mXLReportStatList.size()).append(" timerid = ").append(i).toString());
            if (this.mXLReportStatList.size() >= 3) {
                this.mLocker.a();
            }
        }
    }

    private long getCurrentTime() {
        return new Date().getTime();
    }

    private synchronized XLStatItem getStatReq(int i) {
        return (XLStatItem) this.mXLStatRequestMap.get(Integer.valueOf(i));
    }

    private void reportStatToServer(String str) {
        if (!TextUtils.isEmpty(str)) {
            HttpGet httpGet;
            XLLog.d(this.TAG, new StringBuilder("reportStatToServer url = ").append(str).toString());
            Object obj = null;
            try {
                httpGet = new HttpGet(new StringBuilder(REPORT_BASE_URL).append(str).toString());
            } catch (Exception e) {
                XLLog.e(this.TAG, new StringBuilder("report stat to server error = ").append(e.getMessage()).toString());
            }
            if (httpGet == null) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(this.mCurrentRemoveMsgId));
                return;
            }
            try {
                int statusCode = this.mHttpClient.execute(httpGet).getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    this.mHandler.sendMessage(this.mHandler.obtainMessage(this.mCurrentRemoveMsgId));
                } else {
                    XLLog.e(this.TAG, new StringBuilder("reportStatToServer error code = ").append(statusCode).toString());
                    this.mHandler.sendMessage(this.mHandler.obtainMessage(MSG_REPORT_ERROR_OPERATION));
                }
                if (httpGet != null) {
                    httpGet.abort();
                }
            } catch (Exception e2) {
                try {
                    e2.printStackTrace();
                    XLLog.e(this.TAG, new StringBuilder("reportStatToServer error desc = ").append(e2.getMessage()).toString());
                    this.mHandler.sendMessage(this.mHandler.obtainMessage(MSG_REPORT_ERROR_OPERATION));
                    if (httpGet != null) {
                        httpGet.abort();
                    }
                } catch (Throwable th) {
                    if (httpGet != null) {
                        httpGet.abort();
                    }
                }
            }
        }
    }

    private synchronized String packReportStatUrl() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("cnt=3&");
        int size = this.mXLReportStatList.size();
        XLLog.v(this.TAG, new StringBuilder("packReportStatUrl report list size = ").append(size).toString());
        if (size < 3) {
            str = null;
        } else {
            for (int i = 0; i < 3; i++) {
                XLStatPack xLStatPack = (XLStatPack) this.mXLReportStatList.get(size - (i + 1));
                try {
                    stringBuffer.append(SocialConstants.PARAM_URL).append(i).append("=").append(URLEncoder.encode(xLStatPack.mReqUrl, GameManager.DEFAULT_CHARSET)).append(com.alipay.sdk.sys.a.b);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                stringBuffer.append("errorcode").append(i).append("=").append(xLStatPack.mErrorCode).append(com.alipay.sdk.sys.a.b);
                stringBuffer.append("responsetime").append(i).append("=").append(xLStatPack.mRespTime).append(com.alipay.sdk.sys.a.b);
                stringBuffer.append("retrynum").append(i).append("=").append(xLStatPack.mRetryNum).append(com.alipay.sdk.sys.a.b);
                try {
                    stringBuffer.append("serverip").append(i).append("=").append(URLEncoder.encode(xLStatPack.mSvrIp, GameManager.DEFAULT_CHARSET)).append(com.alipay.sdk.sys.a.b);
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
                try {
                    stringBuffer.append(anet.channel.strategy.dispatch.a.DOMAIN).append(i).append("=").append(URLEncoder.encode(xLStatPack.mSvrDomain, GameManager.DEFAULT_CHARSET)).append(com.alipay.sdk.sys.a.b);
                } catch (UnsupportedEncodingException e22) {
                    e22.printStackTrace();
                }
                stringBuffer.append("cmdid").append(i).append("=").append(xLStatPack.mCommandID).append(com.alipay.sdk.sys.a.b);
                stringBuffer.append("b_type").append(i).append("=").append(this.mBusinessType).append(com.alipay.sdk.sys.a.b);
                stringBuffer.append(Constants.PARAM_PLATFORM).append(i).append("=2&");
                try {
                    stringBuffer.append("clientversion").append(i).append("=").append(URLEncoder.encode(this.mClientVersion, GameManager.DEFAULT_CHARSET)).append(com.alipay.sdk.sys.a.b);
                } catch (UnsupportedEncodingException e222) {
                    e222.printStackTrace();
                }
                try {
                    stringBuffer.append("sdkversion").append(i).append("=").append(URLEncoder.encode(this.mSDKVersion, GameManager.DEFAULT_CHARSET)).append(com.alipay.sdk.sys.a.b);
                } catch (UnsupportedEncodingException e2222) {
                    e2222.printStackTrace();
                }
                try {
                    stringBuffer.append("appname").append(i).append("=").append(URLEncoder.encode(this.mAppName, GameManager.DEFAULT_CHARSET)).append(com.alipay.sdk.sys.a.b);
                } catch (UnsupportedEncodingException e22222) {
                    e22222.printStackTrace();
                }
                try {
                    stringBuffer.append("mac").append(i).append("=").append(URLEncoder.encode(this.mDeviceMacAddress, GameManager.DEFAULT_CHARSET)).append(com.alipay.sdk.sys.a.b);
                } catch (UnsupportedEncodingException e222222) {
                    e222222.printStackTrace();
                }
                stringBuffer.append(ParamKey.UID).append(i).append("=").append(xLStatPack.mUserId);
                if (!TextUtils.isEmpty(xLStatPack.mErrorMessage)) {
                    try {
                        stringBuffer.append("&errormsg").append(i).append("=").append(URLEncoder.encode(xLStatPack.mErrorMessage, GameManager.DEFAULT_CHARSET));
                    } catch (UnsupportedEncodingException e3) {
                        e3.printStackTrace();
                    }
                }
                if (i != 2) {
                    stringBuffer.append(com.alipay.sdk.sys.a.b);
                }
            }
            str = stringBuffer.toString();
        }
        return str;
    }

    private synchronized void handleMessage(Message message) {
        if (this.mCurrentRemoveMsgId == message.what) {
            int i;
            for (i = 0; i < 3; i++) {
                int size = this.mXLReportStatList.size() - 1;
                if (size >= 0) {
                    this.mXLReportStatList.remove(size);
                }
            }
            i = this.mXLReportStatList.size();
            XLLog.d(this.TAG, new StringBuilder("remove report item size = ").append(i).toString());
            if (i < 3) {
                killStatTimer();
            }
        } else if (MSG_REPORT_ERROR_OPERATION == message.what) {
            XLLog.v("XLStatUtil", "stat error, kill stat timer, save data to database");
            killStatTimer();
            saveReportsToDataBase();
        }
    }

    private synchronized void loadReportsFromDataBase() {
        Collection b = this.mStatDBManager$797505f8.b();
        if (b.size() > 0) {
            XLLog.v("XLStatUtil", new StringBuilder("loadReportsFromDataBase size = ").append(b.size()).toString());
            this.mXLReportStatList.addAll(b);
            this.mStatDBManager$797505f8.a();
        }
    }

    private void saveReportsToDataBase() {
        XLLog.v("XLStatUtil", "save report list to database.");
        this.mStatDBManager$797505f8.a(this.mXLReportStatList);
        this.mXLReportStatList.clear();
    }

    private String getReportDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    private int getNetConnType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo == null || networkInfo.getState() != State.CONNECTED) {
            return (networkInfo2 == null || networkInfo2.getState() != State.CONNECTED) ? 0 : XZBDevice.DOWNLOAD_LIST_RECYCLE;
        } else {
            return 1;
        }
    }

    private String getDeviceMac() {
        String str = "ANDROID-MAC";
        try {
            WifiManager wifiManager = (WifiManager) this.mContext.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            WifiInfo connectionInfo = wifiManager == null ? null : wifiManager.getConnectionInfo();
            if (connectionInfo == null) {
                return str;
            }
            String macAddress = connectionInfo.getMacAddress();
            if (macAddress == null) {
                macAddress = "ANDROID-MAC";
            }
            return macAddress.replace(":", com.umeng.a.d);
        } catch (Exception e) {
            return "ANDROID-MAC";
        }
    }

    private synchronized void startStatTimer() {
        if (!this.mTimerStarted) {
            this.mTimerStarted = true;
            com.xunlei.common.stat.base.a.b().a(this.mCurrentStatTimerId, REPORT_TIME_EXPIRE, true, this);
            XLLog.v(this.TAG, "startStatTimer");
        }
    }

    private synchronized void killStatTimer() {
        if (this.mTimerStarted) {
            this.mTimerStarted = false;
            com.xunlei.common.stat.base.a.b().a(this.mCurrentStatTimerId);
            XLLog.v(this.TAG, "killStatTimer");
        }
    }
}
