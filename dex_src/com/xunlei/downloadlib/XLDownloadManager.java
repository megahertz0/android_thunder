package com.xunlei.downloadlib;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.umeng.a;
import com.xunlei.downloadlib.XLUtil.GUID_TYPE;
import com.xunlei.downloadlib.XLUtil.GuidInfo;
import com.xunlei.downloadlib.parameter.BtIndexSet;
import com.xunlei.downloadlib.parameter.BtSubTaskDetail;
import com.xunlei.downloadlib.parameter.BtTaskParam;
import com.xunlei.downloadlib.parameter.BtTaskStatus;
import com.xunlei.downloadlib.parameter.CIDTaskParam;
import com.xunlei.downloadlib.parameter.EmuleTaskParam;
import com.xunlei.downloadlib.parameter.GetDownloadHead;
import com.xunlei.downloadlib.parameter.GetDownloadLibVersion;
import com.xunlei.downloadlib.parameter.GetFileName;
import com.xunlei.downloadlib.parameter.GetTaskId;
import com.xunlei.downloadlib.parameter.InitParam;
import com.xunlei.downloadlib.parameter.MagnetTaskParam;
import com.xunlei.downloadlib.parameter.MaxDownloadSpeedParam;
import com.xunlei.downloadlib.parameter.P2spTaskParam;
import com.xunlei.downloadlib.parameter.PeerResourceParam;
import com.xunlei.downloadlib.parameter.ServerResourceParam;
import com.xunlei.downloadlib.parameter.ThunderUrlInfo;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import com.xunlei.downloadlib.parameter.UrlQuickInfo;
import com.xunlei.downloadlib.parameter.XLConstant.XLManagerStatus;
import com.xunlei.downloadlib.parameter.XLProductInfo;
import com.xunlei.downloadlib.parameter.XLTaskInfo;
import com.xunlei.downloadlib.parameter.XLTaskInfoEx;
import com.xunlei.downloadlib.parameter.XLTaskLocalUrl;
import java.io.File;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class XLDownloadManager {
    private static final int GET_GUID_FIRST_TIME = 5000;
    private static final int GET_GUID_INTERVAL_TIME = 60000;
    private static final int QUERY_GUID_COUNT = 5;
    private static final String TAG = "XLDownloadManager";
    private static boolean mAllowExecution;
    public static XLManagerStatus mDownloadManagerState;
    private static Map<String, Object> mErrcodeStringMap;
    private static XLDownloadManager mInstance;
    private static boolean mIsLoadErrcodeMsg;
    private static int mRunningRefCount;
    private XLAppKeyChecker mAppkeyChecker;
    private Context mContext;
    private Timer mGetGuidTimer;
    private TimerTask mGetGuidTimerTask;
    private XLLoader mLoader;
    private int mQueryGuidCount;
    private NetworkChangeReceiver mReceiver;

    static /* synthetic */ int access$208(XLDownloadManager xLDownloadManager) {
        int i = xLDownloadManager.mQueryGuidCount;
        xLDownloadManager.mQueryGuidCount = i + 1;
        return i;
    }

    static {
        mDownloadManagerState = XLManagerStatus.MANAGER_UNINIT;
        mInstance = null;
        mRunningRefCount = 0;
        mErrcodeStringMap = null;
        mIsLoadErrcodeMsg = false;
        mAllowExecution = true;
    }

    public static synchronized XLDownloadManager getInstance() {
        XLDownloadManager xLDownloadManager;
        synchronized (XLDownloadManager.class) {
            if (mInstance == null) {
                mInstance = new XLDownloadManager();
            }
            xLDownloadManager = mInstance;
        }
        return xLDownloadManager;
    }

    private static synchronized XLDownloadManager getInstance(String str) {
        XLDownloadManager xLDownloadManager;
        synchronized (XLDownloadManager.class) {
            if (mInstance == null) {
                mInstance = new XLDownloadManager(str);
            }
            xLDownloadManager = mInstance;
        }
        return xLDownloadManager;
    }

    public static synchronized XLDownloadManager getInstance(Context context) {
        XLDownloadManager xLDownloadManager;
        synchronized (XLDownloadManager.class) {
            if (mInstance == null) {
                mInstance = new XLDownloadManager(context);
            }
            xLDownloadManager = mInstance;
        }
        return xLDownloadManager;
    }

    private XLDownloadManager() {
        this.mLoader = null;
        this.mContext = null;
        this.mReceiver = null;
        this.mAppkeyChecker = null;
        this.mQueryGuidCount = 0;
        this.mLoader = new XLLoader();
        XLLog.init(new File(Environment.getExternalStorageDirectory().getPath(), "xunlei_ds_log.ini").getPath());
    }

    private XLDownloadManager(String str) {
        this.mLoader = null;
        this.mContext = null;
        this.mReceiver = null;
        this.mAppkeyChecker = null;
        this.mQueryGuidCount = 0;
        this.mLoader = new XLLoader(str);
        XLLog.init(new File(Environment.getExternalStorageDirectory().getPath(), "xunlei_ds_log.ini").getPath());
    }

    private XLDownloadManager(Context context) {
        this.mLoader = null;
        this.mContext = null;
        this.mReceiver = null;
        this.mAppkeyChecker = null;
        this.mQueryGuidCount = 0;
        this.mLoader = new XLLoader(context);
        XLLog.init(new File(Environment.getExternalStorageDirectory().getPath(), "xunlei_ds_log.ini").getPath());
    }

    public XLManagerStatus getManagerStatus() {
        return mDownloadManagerState;
    }

    private void doMonitorNetworkChange() {
        XLLog.i(TAG, "doMonitorNetworkChange()");
        if (this.mContext != null && this.mReceiver == null) {
            this.mReceiver = new NetworkChangeReceiver(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            XLLog.i(TAG, "register Receiver");
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }
    }

    private void undoMonitorNetworkChange() {
        XLLog.i(TAG, "undoMonitorNetworkChange()");
        if (this.mContext != null && this.mReceiver != null) {
            try {
                this.mContext.unregisterReceiver(this.mReceiver);
                XLLog.i(TAG, "unregister Receiver");
            } catch (IllegalArgumentException e) {
                XLLog.e(TAG, "Receiver not registered");
            }
            this.mReceiver = null;
        }
    }

    private synchronized void increRefCount() {
        mRunningRefCount++;
    }

    private synchronized void decreRefCount() {
        mRunningRefCount--;
    }

    public synchronized int init(Context context, InitParam initParam) {
        return init(context, initParam, true);
    }

    public synchronized int init(Context context, InitParam initParam, boolean z) {
        int i = 9900;
        int i2 = 0;
        synchronized (this) {
            if (!mIsLoadErrcodeMsg) {
                loadErrcodeString(context);
                mIsLoadErrcodeMsg = true;
            }
            if (!(context == null || initParam == null || !initParam.checkMemberVar())) {
                this.mContext = context;
                mAllowExecution = z;
                if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING) {
                    XLLog.i(TAG, "XLDownloadManager is already init");
                } else if (this.mLoader != null) {
                    this.mAppkeyChecker = new XLAppKeyChecker(context, initParam.mAppKey);
                    if (this.mAppkeyChecker.verify()) {
                        XLLog.i(TAG, "appKey check successful");
                        String soAppKey = this.mAppkeyChecker.getSoAppKey();
                        String peerid = getPeerid();
                        String guid = getGuid();
                        XLLog.i(TAG, new StringBuilder("Peerid:").append(new String(Base64.encode(peerid.getBytes(), 0))).toString());
                        XLLog.i(TAG, new StringBuilder("Guid:").append(new String(Base64.encode(guid.getBytes(), 0))).toString());
                        if (mAllowExecution) {
                            i2 = XLUtil.getNetworkType(context);
                        }
                        i = this.mLoader.init(soAppKey, this.mContext.getPackageName(), initParam.mAppVersion, a.d, peerid, guid, initParam.mStatSavePath, initParam.mStatCfgSavePath, i2, initParam.mPermissionLevel);
                        if (i != 9000) {
                            mDownloadManagerState = XLManagerStatus.MANAGER_INIT_FAIL;
                        } else {
                            mDownloadManagerState = XLManagerStatus.MANAGER_RUNNING;
                            doMonitorNetworkChange();
                            setLocalProperty("PhoneModel", Build.MODEL);
                        }
                    } else {
                        XLLog.i(TAG, "appKey check failed");
                        i = 9901;
                    }
                }
            }
        }
        return i;
    }

    public synchronized int uninit() {
        int i = 9900;
        synchronized (this) {
            if (mRunningRefCount != 0) {
                XLLog.i(TAG, "some function of XLDownloadManager is running, uninit failed!");
            } else if (!(mDownloadManagerState == XLManagerStatus.MANAGER_UNINIT || this.mLoader == null)) {
                if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING) {
                    undoMonitorNetworkChange();
                }
                stopGetGuidTimer();
                i = this.mLoader.unInit();
                mDownloadManagerState = XLManagerStatus.MANAGER_UNINIT;
                this.mContext = null;
            }
        }
        return i;
    }

    int notifyNetWorkType(int i, XLLoader xLLoader) {
        if (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || xLLoader == null) {
            return 9900;
        }
        try {
            return xLLoader.notifyNetWorkType(i);
        } catch (Error e) {
            XLLog.e(TAG, new StringBuilder("notifyNetWorkType failed,").append(e.getMessage()).toString());
            return 9900;
        }
    }

    public int createP2spTask(P2spTaskParam p2spTaskParam, GetTaskId getTaskId) {
        int i = 9900;
        if (!(p2spTaskParam == null || getTaskId == null || !p2spTaskParam.checkMemberVar())) {
            increRefCount();
            if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
                i = this.mLoader.createP2spTask(p2spTaskParam.mUrl, p2spTaskParam.mRefUrl, p2spTaskParam.mCookie, p2spTaskParam.mUser, p2spTaskParam.mPass, p2spTaskParam.mFilePath, p2spTaskParam.mFileName, p2spTaskParam.mCreateMode, p2spTaskParam.mSeqId, getTaskId);
            }
            decreRefCount();
        }
        return i;
    }

    public int releaseTask(long j) {
        int i = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i = this.mLoader.releaseTask(j);
        }
        decreRefCount();
        return i;
    }

    int setTaskAppInfo(long j, String str, String str2, String str3) {
        return (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null || str2 == null || str3 == null) ? 9900 : this.mLoader.setTaskAppInfo(j, str, str2, str3);
    }

    public int setTaskAllowUseResource(long j, int i) {
        int i2 = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i2 = this.mLoader.setTaskAllowUseResource(j, i);
        }
        decreRefCount();
        return i2;
    }

    public int setTaskUid(long j, int i) {
        int i2 = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i2 = this.mLoader.setTaskUid(j, i);
        }
        decreRefCount();
        return i2;
    }

    public int startTask(long j) {
        int i = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i = this.mLoader.startTask(j);
        }
        decreRefCount();
        return i;
    }

    int switchOriginToAllResDownload(long j) {
        return (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null) ? 9900 : this.mLoader.switchOriginToAllResDownload(j);
    }

    public int stopTask(long j) {
        int i = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i = this.mLoader.stopTask(j);
        }
        XLLog.i(TAG, new StringBuilder("XLStopTask()----- ret=").append(i).toString());
        decreRefCount();
        return i;
    }

    public int stopTaskWithReason(long j, int i) {
        int i2 = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i2 = this.mLoader.stopTaskWithReason(j, i);
        }
        XLLog.i(TAG, new StringBuilder("XLStopTask()----- ret=").append(i2).toString());
        decreRefCount();
        return i2;
    }

    public int getTaskInfo(long j, int i, XLTaskInfo xLTaskInfo) {
        int i2 = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || xLTaskInfo == null)) {
            i2 = this.mLoader.getTaskInfo(j, i, xLTaskInfo);
        }
        decreRefCount();
        return i2;
    }

    public int getTaskInfoEx(long j, XLTaskInfoEx xLTaskInfoEx) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || xLTaskInfoEx == null)) {
            i = this.mLoader.getTaskInfoEx(j, xLTaskInfoEx);
        }
        decreRefCount();
        return i;
    }

    public int getLocalUrl(String str, XLTaskLocalUrl xLTaskLocalUrl) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || xLTaskLocalUrl == null)) {
            i = this.mLoader.getLocalUrl(str, xLTaskLocalUrl);
        }
        decreRefCount();
        return i;
    }

    public int addServerResource(long j, ServerResourceParam serverResourceParam) {
        int i = 9900;
        if (serverResourceParam != null && serverResourceParam.checkMemberVar()) {
            increRefCount();
            if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
                XLLog.i(TAG, new StringBuilder("respara.mUrl=").append(serverResourceParam.mUrl).toString());
                i = this.mLoader.addServerResource(j, serverResourceParam.mUrl, serverResourceParam.mRefUrl, serverResourceParam.mCookie, serverResourceParam.mResType, serverResourceParam.mStrategy);
            }
            decreRefCount();
        }
        return i;
    }

    public int addPeerResource(long j, PeerResourceParam peerResourceParam) {
        int i = 9900;
        if (peerResourceParam == null || !peerResourceParam.checkMemberVar()) {
            return 9900;
        }
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i = this.mLoader.addPeerResource(j, peerResourceParam.mPeerId, peerResourceParam.mUserId, peerResourceParam.mJmpKey, peerResourceParam.mVipCdnAuth, peerResourceParam.mInternalIp, peerResourceParam.mTcpPort, peerResourceParam.mUdpPort, peerResourceParam.mResLevel, peerResourceParam.mResPriority, peerResourceParam.mCapabilityFlag, peerResourceParam.mResType);
        }
        decreRefCount();
        return i;
    }

    public int removeServerResource(long j, int i) {
        int i2 = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i2 = this.mLoader.removeAddedServerResource(j, i);
        }
        decreRefCount();
        return i2;
    }

    int requeryTaskIndex(long j) {
        return (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null) ? 9900 : this.mLoader.requeryIndex(j);
    }

    public int setOriginUserAgent(long j, String str) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null)) {
            i = this.mLoader.setOriginUserAgent(j, str);
        }
        decreRefCount();
        return i;
    }

    public int setUserId(String str) {
        return (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null) ? 9900 : this.mLoader.setUserId(str);
    }

    public int getDownloadHeader(long j, GetDownloadHead getDownloadHead) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || getDownloadHead == null)) {
            i = this.mLoader.getDownloadHeader(j, getDownloadHead);
        }
        decreRefCount();
        return i;
    }

    public int setFileName(long j, String str) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null)) {
            i = this.mLoader.setFileName(j, str);
        }
        decreRefCount();
        return i;
    }

    int notifyNetWorkCarrier(int i) {
        return (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null) ? 9900 : this.mLoader.setNotifyNetWorkCarrier(i);
    }

    int notifyWifiBSSID(String str, XLLoader xLLoader) {
        if (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || xLLoader == null) {
            return 9900;
        }
        if (str == null || str.length() == 0 || str == "<unknown ssid>") {
            str = a.d;
        }
        try {
            return xLLoader.setNotifyWifiBSSID(str);
        } catch (Error e) {
            XLLog.e(TAG, new StringBuilder("setNotifyWifiBSSID failed,").append(e.getMessage()).toString());
            return 9900;
        }
    }

    public int setDownloadTaskOrigin(long j, String str) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null)) {
            i = this.mLoader.setDownloadTaskOrigin(j, str);
        }
        decreRefCount();
        return i;
    }

    int setMac(String str) {
        return (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null) ? 9900 : this.mLoader.setMac(str);
    }

    int setImei(String str) {
        return (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null) ? 9900 : this.mLoader.setImei(str);
    }

    private int setLocalProperty(String str, String str2) {
        return (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null || str2 == null) ? 9900 : this.mLoader.setLocalProperty(str, str2);
    }

    public int setOSVersion(String str) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null)) {
            i = this.mLoader.setMiUiVersion(str);
        }
        decreRefCount();
        return i;
    }

    public int setHttpHeaderProperty(long j, String str, String str2) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || str == null || str2 == null)) {
            i = this.mLoader.setHttpHeaderProperty(j, str, str2);
        }
        decreRefCount();
        return i;
    }

    public int getDownloadLibVersion(GetDownloadLibVersion getDownloadLibVersion) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || getDownloadLibVersion == null)) {
            i = this.mLoader.getDownloadLibVersion(getDownloadLibVersion);
        }
        decreRefCount();
        return i;
    }

    public int getProductInfo(XLProductInfo xLProductInfo) {
        increRefCount();
        if (mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mContext == null || xLProductInfo == null) {
            decreRefCount();
            return 9900;
        }
        xLProductInfo.mProductKey = this.mAppkeyChecker.getSoAppKey();
        xLProductInfo.mProductName = this.mContext.getPackageName();
        return 9000;
    }

    private String getPeerid() {
        if (!mAllowExecution) {
            return "000000000000000V";
        }
        String peerid = XLUtil.getPeerid(this.mContext);
        return peerid == null ? "000000000000000V" : peerid;
    }

    private String getGuid() {
        if (!mAllowExecution) {
            return "00000000000000_000000000000";
        }
        GuidInfo guidInfo = new GuidInfo();
        guidInfo = XLUtil.generateGuid(this.mContext);
        if (guidInfo.mType != GUID_TYPE.ALL) {
            XLLog.i(TAG, "Start the GetGuidTimer");
            startGetGuidTimer();
        }
        return guidInfo.mGuid;
    }

    private void startGetGuidTimer() {
        this.mGetGuidTimer = new Timer();
        this.mGetGuidTimerTask = new AnonymousClass_1(this);
        this.mGetGuidTimer.schedule(this.mGetGuidTimerTask, 5000, BuglyBroadcastRecevier.UPLOADLIMITED);
    }

    private void stopGetGuidTimer() {
        if (this.mGetGuidTimer instanceof Timer) {
            this.mGetGuidTimer.cancel();
            this.mGetGuidTimer.purge();
            this.mGetGuidTimer = null;
            XLLog.i(TAG, "stopGetGuidTimer");
        }
        if (this.mGetGuidTimerTask instanceof TimerTask) {
            this.mGetGuidTimerTask.cancel();
            this.mGetGuidTimerTask = null;
        }
    }

    public int enterPrefetchMode(long j) {
        int i = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i = this.mLoader.enterPrefetchMode(j);
        }
        decreRefCount();
        return i;
    }

    public int setTaskLxState(long j, int i, int i2) {
        int i3 = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i3 = this.mLoader.setTaskLxState(j, i, i2);
        }
        decreRefCount();
        return i3;
    }

    public int setTaskGsState(long j, int i, int i2) {
        int i3 = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i3 = this.mLoader.setTaskGsState(j, i, i2);
        }
        decreRefCount();
        return i3;
    }

    public int setReleaseLog(boolean z, String str, int i, int i2) {
        int i3 = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i3 = z ? this.mLoader.setReleaseLog(1, str, i, i2) : this.mLoader.setReleaseLog(0, null, 0, 0);
        }
        decreRefCount();
        return i3;
    }

    public int setReleaseLog(boolean z, String str) {
        return setReleaseLog(z, str, 0, 0);
    }

    public boolean isLogTurnOn() {
        boolean z = false;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            z = this.mLoader.isLogTurnOn();
        }
        decreRefCount();
        return z;
    }

    public int setStatReportSwitch(boolean z) {
        int i = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i = this.mLoader.setStatReportSwitch(z);
        }
        decreRefCount();
        return i;
    }

    public int createBtMagnetTask(MagnetTaskParam magnetTaskParam, GetTaskId getTaskId) {
        int i = 9900;
        if (!(magnetTaskParam == null || getTaskId == null || !magnetTaskParam.checkMemberVar())) {
            increRefCount();
            if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
                i = this.mLoader.createBtMagnetTask(magnetTaskParam.mUrl, magnetTaskParam.mFilePath, magnetTaskParam.mFileName, getTaskId);
            }
            decreRefCount();
        }
        return i;
    }

    public int createEmuleTask(EmuleTaskParam emuleTaskParam, GetTaskId getTaskId) {
        int i = 9900;
        if (!(emuleTaskParam == null || getTaskId == null || !emuleTaskParam.checkMemberVar())) {
            increRefCount();
            if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
                i = this.mLoader.createEmuleTask(emuleTaskParam.mUrl, emuleTaskParam.mFilePath, emuleTaskParam.mFileName, emuleTaskParam.mCreateMode, emuleTaskParam.mSeqId, getTaskId);
            }
            decreRefCount();
        }
        return i;
    }

    public int createBtTask(BtTaskParam btTaskParam, GetTaskId getTaskId) {
        int i = 9900;
        if (!(btTaskParam == null || getTaskId == null || !btTaskParam.checkMemberVar())) {
            increRefCount();
            if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
                i = this.mLoader.createBtTask(btTaskParam.mTorrentPath, btTaskParam.mFilePath, btTaskParam.mMaxConcurrent, btTaskParam.mCreateMode, btTaskParam.mSeqId, getTaskId);
            }
            decreRefCount();
        }
        return i;
    }

    public int getTorrentInfo(String str, TorrentInfo torrentInfo) {
        int i = 9900;
        increRefCount();
        if (!(this.mLoader == null || str == null || torrentInfo == null)) {
            i = this.mLoader.getTorrentInfo(str, torrentInfo);
        }
        decreRefCount();
        return i;
    }

    public int getBtSubTaskStatus(long j, BtTaskStatus btTaskStatus, int i, int i2) {
        int i3 = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || btTaskStatus == null)) {
            i3 = this.mLoader.getBtSubTaskStatus(j, btTaskStatus, i, i2);
        }
        decreRefCount();
        return i3;
    }

    public int getBtSubTaskInfo(long j, int i, BtSubTaskDetail btSubTaskDetail) {
        int i2 = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || btSubTaskDetail == null)) {
            i2 = this.mLoader.getBtSubTaskInfo(j, i, btSubTaskDetail);
        }
        decreRefCount();
        return i2;
    }

    public int selectBtSubTask(long j, BtIndexSet btIndexSet) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || btIndexSet == null)) {
            i = this.mLoader.selectBtSubTask(j, btIndexSet);
        }
        decreRefCount();
        return i;
    }

    public int deselectBtSubTask(long j, BtIndexSet btIndexSet) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || btIndexSet == null)) {
            i = this.mLoader.deselectBtSubTask(j, btIndexSet);
        }
        decreRefCount();
        return i;
    }

    public int btAddServerResource(long j, int i, ServerResourceParam serverResourceParam) {
        if (serverResourceParam == null) {
            XLLog.e(TAG, new StringBuilder("btAddServerResource serverResPara is null, task=[").append(j).append(":").append(i).append("]").toString());
            return 9112;
        }
        XLLog.d(TAG, new StringBuilder("btAddServerResource beg, task=[").append(j).append(":").append(i).append("] mUrl=[").append(serverResourceParam.mUrl).append("] mRefUrl=[").append(serverResourceParam.mRefUrl).append("] mCookie=[").append(serverResourceParam.mCookie).append("] mResType=[").append(serverResourceParam.mResType).append("] mStrategy=[").append(serverResourceParam.mStrategy).append("]").toString());
        if (serverResourceParam.checkMemberVar()) {
            increRefCount();
            if (this.mLoader == null) {
                XLLog.e(TAG, new StringBuilder("btAddServerResource mLoader is null, task=[").append(j).append(":").append(i).append("]").toString());
                decreRefCount();
                return 9900;
            } else if (XLManagerStatus.MANAGER_RUNNING != mDownloadManagerState) {
                XLLog.e(TAG, new StringBuilder("btAddServerResource mDownloadManagerState is invaild, task=[").append(j).append(":").append(i).append("] mDownloadManagerState=[").append(mDownloadManagerState).append("]").toString());
                decreRefCount();
                return 9900;
            } else {
                int btAddServerResource = this.mLoader.btAddServerResource(j, i, serverResourceParam.mUrl, serverResourceParam.mRefUrl, serverResourceParam.mCookie, serverResourceParam.mResType, serverResourceParam.mStrategy);
                if (9000 != btAddServerResource) {
                    XLLog.e(TAG, new StringBuilder("btAddServerResource btAddServerResource failed, task=[").append(j).append(":").append(i).append("] errno=[").append(btAddServerResource).append("]").toString());
                    decreRefCount();
                    return btAddServerResource;
                }
                XLLog.d(TAG, new StringBuilder("btAddServerResource end success, task=[").append(j).append(":").append(i).append("]").toString());
                decreRefCount();
                return 9000;
            }
        }
        XLLog.e(TAG, new StringBuilder("btAddServerResource checkMemberVar failed, task=[").append(j).append(":").append(i).append("] mUrl=[").append(serverResourceParam.mUrl).append("] mRefUrl=[").append(serverResourceParam.mRefUrl).append("] mCookie=[").append(serverResourceParam.mCookie).append("]").toString());
        return 9112;
    }

    public int btAddPeerResource(long j, int i, PeerResourceParam peerResourceParam) {
        if (peerResourceParam == null) {
            XLLog.e(TAG, new StringBuilder("btAddPeerResource peerResPara is null, task=[").append(j).append(":").append(i).append("]").toString());
            return 9112;
        }
        XLLog.d(TAG, new StringBuilder("btAddPeerResource beg, task=[").append(j).append(":").append(i).append("] mPeerId=[").append(peerResourceParam.mPeerId).append("] mUserId=[").append(peerResourceParam.mUserId).append("] mJmpKey=[").append(peerResourceParam.mJmpKey).append("] mVipCdnAuth=[").append(peerResourceParam.mVipCdnAuth).append("] mInternalIp=[").append(peerResourceParam.mInternalIp).append("] mTcpPort=[").append(peerResourceParam.mTcpPort).append("] mUdpPort=[").append(peerResourceParam.mUdpPort).append("] mResLevel=[").append(peerResourceParam.mResLevel).append("] mResPriority=[").append(peerResourceParam.mResPriority).append("] mCapabilityFlag=[").append(peerResourceParam.mCapabilityFlag).append("] mResType=[").append(peerResourceParam.mResType).append("]").toString());
        if (peerResourceParam.checkMemberVar()) {
            increRefCount();
            if (this.mLoader == null) {
                XLLog.e(TAG, new StringBuilder("btAddPeerResource mLoader is null, task=[").append(j).append(":").append(i).append("]").toString());
                decreRefCount();
                return 9900;
            } else if (XLManagerStatus.MANAGER_RUNNING != mDownloadManagerState) {
                XLLog.e(TAG, new StringBuilder("btAddPeerResource mDownloadManagerState is invaild, task=[").append(j).append(":").append(i).append("] mDownloadManagerState=[").append(mDownloadManagerState).append("]").toString());
                decreRefCount();
                return 9900;
            } else {
                int btAddPeerResource = this.mLoader.btAddPeerResource(j, i, peerResourceParam.mPeerId, peerResourceParam.mUserId, peerResourceParam.mJmpKey, peerResourceParam.mVipCdnAuth, peerResourceParam.mInternalIp, peerResourceParam.mTcpPort, peerResourceParam.mUdpPort, peerResourceParam.mResLevel, peerResourceParam.mResPriority, peerResourceParam.mCapabilityFlag, peerResourceParam.mResType);
                if (9000 != btAddPeerResource) {
                    XLLog.e(TAG, new StringBuilder("btAddPeerResource btAddPeerResource failed, task=[").append(j).append(":").append(i).append("] errno=[").append(btAddPeerResource).append("]").toString());
                    decreRefCount();
                    return btAddPeerResource;
                }
                XLLog.d(TAG, new StringBuilder("btAddPeerResource end success, task=[").append(j).append(":").append(i).append("]").toString());
                decreRefCount();
                return 9000;
            }
        }
        XLLog.e(TAG, new StringBuilder("btAddPeerResource peerResPara checkMemberVar failed, task=[").append(j).append(":").append(i).append("]").toString());
        return 9112;
    }

    public int btRemoveAddedResource(long j, int i, int i2) {
        int i3 = 9900;
        increRefCount();
        if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
            i3 = this.mLoader.btRemoveAddedResource(j, i, i2);
        }
        decreRefCount();
        return i3;
    }

    private void loadErrcodeString(Context context) {
        if (context == null) {
            XLLog.e(TAG, "loadErrcodeString, context invalid");
        } else {
            mErrcodeStringMap = XLUtil.parseJSONString("{  \"9000\": \"XL_NO_ERRNO                 \"  ,  \"9101\": \"XL_ALREADY_INIT             \"  ,  \"9102\": \"XL_SDK_NOT_INIT             \"  ,  \"9103\": \"XL_TASK_ALREADY_EXIST       \"  ,  \"9104\": \"XL_TASK_NOT_EXIST           \"  ,  \"9105\": \"XL_TASK_ALREADY_STOPPED     \"  ,  \"9106\": \"XL_TASK_ALREADY_RUNNING     \"  ,  \"9107\": \"XL_TASK_NOT_START           \"  ,  \"9108\": \"XL_TASK_STILL_RUNNING       \"  ,  \"9109\": \"XL_FILE_EXISTED             \"  ,  \"9110\": \"XL_DISK_FULL                \"  ,  \"9111\": \"XL_TOO_MUCH_TASK            \"  ,  \"9112\": \"XL_PARAM_ERROR              \"  ,  \"9113\": \"XL_SCHEMA_NOT_SUPPORT       \"  ,  \"9114\": \"XL_DYNAMIC_PARAM_FAIL       \"  ,  \"9115\": \"XL_CONTINUE_NO_NAME         \"  ,  \"9116\": \"XL_APPNAME_APPKEY_ERROR     \"  ,  \"9117\": \"XL_CREATE_THREAD_ERROR      \"  ,  \"9118\": \"XL_TASK_FINISH              \"  ,  \"9119\": \"XL_TASK_NOT_RUNNING         \"  ,  \"9120\": \"XL_TASK_NOT_IDLE            \"  ,  \"9121\": \"XL_TASK_TYPE_NOT_SUPPORT    \"  ,  \"9122\": \"XL_ADD_RESOURCE_ERROR       \"  ,  \"9123\": \"XL_TASK_LOADING_CFG         \"  ,  \"9301\": \"XL_NO_ENOUGH_BUFFER         \"  ,  \"9302\": \"XL_TORRENT_PARSE_ERROR      \"  ,  \"9303\": \"XL_INDEX_NOT_READY          \"  ,  \"9304\": \"XL_TORRENT_IMCOMPLETE       \"  ,  \"9900\": \"DOWNLOAD_MANAGER_ERROR      \"  ,  \"9901\": \"APPKEY_CHECKER_ERROR        \"  ,  \"111024\": \"COMMON_ERRCODE_BASE                \"  ,  \"111025\": \"TARGET_THREAD_STOPING              \"  ,  \"111026\": \"OUT_OF_MEMORY                      \"  ,  \"111031\": \"TASK_USE_TOO_MUCH_MEM              \"  ,  \"111032\": \"OUT_OF_FIXED_MEMORY                \"  ,  \"111033\": \"QUEUE_NO_ROOM                      \"  ,  \"111035\": \"MAP_UNINIT                         \"  ,  \"111036\": \"MAP_DUPLICATE_KEY                  \"  ,  \"111037\": \"MAP_KEY_NOT_FOUND                  \"  ,  \"111038\": \"INVALID_ITERATOR                   \"  ,  \"111039\": \"BUFFER_OVERFLOW                    \"  ,  \"111041\": \"INVALID_ARGUMENT                   \"  ,  \"111048\": \"INVALID_SOCKET_DESCRIPTOR          \"  ,  \"111050\": \"ERROR_INVALID_INADDR               \"  ,  \"111181\": \"REDIRECT_TOO_MUCH                  \"  ,  \"111057\": \"NOT_IMPLEMENT                      \"  ,  \"111074\": \"INVALID_TIMER_INDEX                \"  ,  \"111078\": \"DNS_INVALID_ADDR                   \"  ,  \"111083\": \"BAD_DIR_PATH                       \"  ,  \"111084\": \"FILE_CANNOT_TRUNCATE               \"  ,  \"111085\": \"INSUFFICIENT_DISK_SPACE            \"  ,  \"111086\": \"FILE_TOO_BIG                       \"  ,  \"111118\": \"DISPATCHER_ERRCODE_BASE            \"  ,  \"111119\": \"DATA_MGR_ERRCODE_BASE              \"  ,  \"111120\": \"ALLOC_INVALID_SIZE                 \"  ,  \"111121\": \"DATA_BUFFER_IS_FULL                \"  ,  \"111122\": \"BLOCK_NO_INVALID                   \"  ,  \"111123\": \"CHECK_DATA_BUFFER_NOT_ENOUG        \"  ,  \"111124\": \"BCID_CHECK_FAIL                    \"  ,  \"111125\": \"BCID_ONCE_CHECT_TOO_MUCH           \"  ,  \"111126\": \"READ_FILE_ERR                      \"  ,  \"111127\": \"WRITE_FILE_ERR                     \"  ,  \"111128\": \"OPEN_FILE_ERR                      \"  ,  \"111129\": \"FILE_PATH_TOO_LONG                 \"  ,  \"111130\": \"SD_INVALID_FILE_SIZE               \"  ,  \"111131\": \"FILE_CFG_MAGIC_ERROR               \"  ,  \"111132\": \"FILE_CFG_READ_ERROR                \"  ,  \"111133\": \"FILE_CFG_WRITE_ERROR               \"  ,  \"111134\": \"FILE_CFG_READ_HEADER_ERROR         \"  ,  \"111135\": \"FILE_CFG_RESOLVE_ERROR             \"  ,  \"111136\": \"TASK_FAILURE_NO_DATA_PIPE          \"  ,  \"111137\": \"NO_FILE_NAME                       \"  ,  \"111138\": \"CANNOT_GET_FILE_NAME               \"  ,  \"111139\": \"CREATE_FILE_FAIL                   \"  ,  \"111140\": \"OPEN_OLD_FILE_FAIL                 \"  ,  \"111141\": \"FILE_SIZE_NOT_BELIEVE              \"  ,  \"111142\": \"FILE_SIZE_TOO_SMALL                \"  ,  \"111143\": \"FILE_NOT_EXIST                     \"  ,  \"111144\": \"FILE_INVALID_PARA                  \"  ,  \"111145\": \"FILE_CREATING                      \"  ,  \"111146\": \"FIL_INFO_INVALID_DATA              \"  ,  \"111147\": \"FIL_INFO_RECVED_DATA               \"  ,  \"111159\": \"CONF_MGR_ERRCODE_BASE              \"  ,  \"111160\": \"SETTINGS_ERR_UNKNOWN               \"  ,  \"111161\": \"SETTINGS_ERR_INVALID_FILE_NAME     \"  ,  \"111162\": \"SETTINGS_ERR_CFG_FILE_NOT_EXIST    \"  ,  \"111163\": \"SETTINGS_ERR_INVALID_LINE          \"  ,  \"111164\": \"SETTINGS_ERR_INVALID_ITEM_NAME     \"  ,  \"111165\": \"SETTINGS_ERR_INVALID_ITEM_VALUE    \"  ,  \"111166\": \"SETTINGS_ERR_LIST_EMPTY            \"  ,  \"111167\": \"SETTINGS_ERR_ITEM_NOT_FOUND        \"  ,  \"111168\": \"NET_REACTOR_ERRCODE_BASE           \"  ,  \"111169\": \"NET_CONNECT_SSL_ERR                \"  ,  \"111170\": \"NET_BROKEN_PIPE                    \"  ,  \"111171\": \"NET_CONNECTION_REFUSED             \"  ,  \"111172\": \"NET_SSL_GET_FD_ERROR               \"  ,  \"111173\": \"NET_OP_CANCEL                      \"  ,  \"111174\": \"NET_UNKNOWN_ERROR                  \"  ,  \"111175\": \"NET_NORMAL_CLOSE                   \"  ,  \"111176\": \"TASK_FAIL_LONG_TIME_NO_RECV_DATA   \"  ,  \"111177\": \"TASK_FILE_SIZE_TOO_LARGE           \"  ,  \"111178\": \"TASK_RETRY_ALWAY_FAIL              \"  ,  \"111300\": \"ASYN_FILE_E_BASE                   \"  ,  \"111301\": \"ASYN_FILE_E_OP_NONE                \"  ,  \"111302\": \"ASYN_FILE_E_OP_BUSY                \"  ,  \"111303\": \"ASYN_FILE_E_FILE_NOT_OPEN          \"  ,  \"111304\": \"ASYN_FILE_E_FILE_REOPEN            \"  ,  \"111305\": \"ASYN_FILE_E_EMPTY_FILE             \"  ,  \"111306\": \"ASYN_FILE_E_FILE_SIZE_LESS         \"  ,  \"111307\": \"ASYN_FILE_E_TOO_MUCH_DATA          \"  ,  \"111308\": \"ASYN_FILE_E_FILE_CLOSING           \"  ,  \"112400\": \"ERR_PTL_PROTOCOL_NOT_SUPPORT       \"  ,  \"112500\": \"ERR_PTL_PEER_OFFLINE               \"  ,  \"112600\": \"ERR_PTL_GET_PEERSN_FAILED          \"  ,  \"11300\": \"P2P_PIPE_ERRCODE_BASE\t\t\t    \"  ,  \"11301\": \"ERR_P2P_VERSION_NOT_SUPPORT\t\t    \"  ,  \"11302\": \"ERR_P2P_WAITING_CLOSE\t\t\t    \"  ,  \"11303\": \"ERR_P2P_HANDSHAKE_RESP_FAIL\t\t    \"  ,  \"11304\": \"ERR_P2P_REQUEST_RESP_FAIL\t\t    \"  ,  \"11305\": \"ERR_P2P_UPLOAD_OVER_MAX\t\t\t    \"  ,  \"11306\": \"ERR_P2P_REMOTE_UNKNOWN_MY_CMD\t    \"  ,  \"11307\": \"ERR_P2P_NOT_SUPPORT_UDT\t\t\t    \"  ,  \"11308\": \"ERR_P2P_BROKER_CONNECT\t\t\t    \"  ,  \"11309\": \"ERR_P2P_INVALID_COMMAND\t\t\t    \"  ,  \"11310\": \"ERR_P2P_INVALID_PARAM\t\t\t    \"  ,  \"11311\": \"ERR_P2P_CONNECT_FAILED\t\t\t    \"  ,  \"11312\": \"ERR_P2P_CONNECT_UPLOAD_SLOW\t        \"  ,  \"11313\": \"ERR_P2P_ALLOC_MEM_ERR               \"  ,  \"11314\": \"ERR_P2P_SEND_HANDSHAKE              \"  ,  \"114001\": \"TASK_FAILURE_QUERY_EMULE_HUB_FAILED\"  ,  \"114101\": \"TASK_FAILURE_EMULE_NO_RECORD       \"  ,  \"114002\": \"TASK_FAILURE_SUBTASK_FAILED        \"  ,  \"114003\": \"TASK_FAILURE_CANNOT_START_SUBTASK  \"  ,  \"114004\": \"TASK_FAILURE_QUERY_BT_HUB_FAILED   \"  ,  \"114005\": \"TASK_FAILURE_PARSE_TORRENT_FAILED  \"  ,  \"114006\": \"TASK_FAILURE_GET_TORRENT_FAILED    \"  ,  \"114007\": \"TASK_FAILURE_SAVE_TORRENT_FAILED   \"  ,  \"115000\": \"RES_QUERY_E_BASE                   \"  ,  \"115100\": \"HTTP_HUB_CLIENT_E_BASE             \"  ,  \"116000\": \"IP6_ERRCODE_BASE                   \"  ,  \"116001\": \"ERR_INVALID_ADDRESS_FAMILY         \"  ,  \"116002\": \"IP6_INVALID_IN6ADDR                \"  ,  \"116003\": \"IP6_NOT_SUPPORT_SSL                \"  ,  \"117000\": \"PAUSE_TASK_WRITE_CFG_ERR           \"  ,  \"117001\": \"PAUSE_TASK_WRITE_DATA_TIMEOUT      \"   }");
        }
    }

    public String getErrorCodeMsg(int i) {
        String str = null;
        String toString = Integer.toString(i);
        if (!(mErrcodeStringMap == null || toString == null)) {
            Object obj = mErrcodeStringMap.get(toString);
            if (obj != null) {
                str = obj.toString().trim();
            }
            XLLog.i(TAG, new StringBuilder("errcode:").append(i).append(", errcodeMsg:").append(str).toString());
        }
        return str;
    }

    public int getUrlQuickInfo(long j, UrlQuickInfo urlQuickInfo) {
        int i = 9900;
        increRefCount();
        if (!(mDownloadManagerState != XLManagerStatus.MANAGER_RUNNING || this.mLoader == null || urlQuickInfo == null)) {
            i = this.mLoader.getUrlQuickInfo(j, urlQuickInfo);
        }
        decreRefCount();
        return i;
    }

    public int createCIDTask(CIDTaskParam cIDTaskParam, GetTaskId getTaskId) {
        int i = 9900;
        if (!(cIDTaskParam == null || getTaskId == null || !cIDTaskParam.checkMemberVar())) {
            increRefCount();
            if (mDownloadManagerState == XLManagerStatus.MANAGER_RUNNING && this.mLoader != null) {
                i = this.mLoader.createCIDTask(cIDTaskParam.mCid, cIDTaskParam.mGcid, cIDTaskParam.mBcid, cIDTaskParam.mFilePath, cIDTaskParam.mFileName, cIDTaskParam.mFileSize, cIDTaskParam.mCreateMode, cIDTaskParam.mSeqId, getTaskId);
            }
            decreRefCount();
        }
        return i;
    }

    public String parserThunderUrl(String str) {
        int i = 9900;
        ThunderUrlInfo thunderUrlInfo = new ThunderUrlInfo();
        if (!(this.mLoader == null || str == null)) {
            i = this.mLoader.parserThunderUrl(str, thunderUrlInfo);
        }
        return 9000 == i ? thunderUrlInfo.mUrl : null;
    }

    public int getFileNameFromUrl(String str, GetFileName getFileName) {
        return (this.mLoader == null || str == null || getFileName == null) ? 9900 : this.mLoader.getFileNameFromUrl(str, getFileName);
    }

    public int getNameFromUrl(String str, String str2) {
        return (this.mLoader == null || str == null || str2 == null) ? 9900 : this.mLoader.getNameFromUrl(str, str2);
    }

    public int setSpeedLimit(long j, long j2) {
        XLLog.d(TAG, new StringBuilder("debug: XLDownloadManager::setSpeedLimit beg, maxDownloadSpeed=[").append(j).append("] maxUploadSpeed=[").append(j2).append("]").toString());
        if (this.mLoader == null) {
            XLLog.e(TAG, new StringBuilder("error: XLDownloadManager::setSpeedLimit mLoader is null, maxDownloadSpeed=[").append(j).append("] maxUploadSpeed=[").append(j2).append("] ret=[9900]").toString());
            return 9900;
        }
        int speedLimit = this.mLoader.setSpeedLimit(j, j2);
        XLLog.d(TAG, new StringBuilder("debug: XLDownloadManager::setSpeedLimit end, maxDownloadSpeed=[").append(j).append("] maxUploadSpeed=[").append(j2).append("] ret=[").append(speedLimit).append("]").toString());
        return speedLimit;
    }

    public int setBtPriorSubTask(long j, int i) {
        XLLog.d(TAG, new StringBuilder("XLDownloadManager::setBtPriorSubTask beg, taskId=[").append(j).append("] fileIndex=[").append(i).append("]").toString());
        if (this.mLoader == null) {
            XLLog.e(TAG, new StringBuilder("XLDownloadManager::setBtPriorSubTask mLoader is null, taskId=[").append(j).append("] fileIndex=[").append(i).append("]").toString());
            return 9900;
        }
        int btPriorSubTask = this.mLoader.setBtPriorSubTask(j, i);
        if (9000 != btPriorSubTask) {
            XLLog.e(TAG, new StringBuilder("XLDownloadManager::setBtPriorSubTask end, taskId=[").append(j).append("] fileIndex=[").append(i).append("] ret=[").append(btPriorSubTask).append("]").toString());
            return btPriorSubTask;
        }
        XLLog.d(TAG, new StringBuilder(" XLDownloadManager::setBtPriorSubTask end, taskId=[").append(j).append("] fileIndex=[").append(i).append("]").toString());
        return 9000;
    }

    public int getMaxDownloadSpeed(MaxDownloadSpeedParam maxDownloadSpeedParam) {
        if (this.mLoader == null) {
            XLLog.e(TAG, "XLDownloadManager::getMaxDownloadSpeed mLoader is null");
            return 9900;
        }
        int maxDownloadSpeed = this.mLoader.getMaxDownloadSpeed(maxDownloadSpeedParam);
        if (9000 != maxDownloadSpeed) {
            XLLog.e(TAG, new StringBuilder("XLDownloadManager::getMaxDownloadSpeed end, ret=[").append(maxDownloadSpeed).append("]").toString());
            return maxDownloadSpeed;
        }
        XLLog.d(TAG, new StringBuilder("XLDownloadManager::getMaxDownloadSpeed end, speed=[").append(maxDownloadSpeedParam.mSpeed).append("] ret=[").append(maxDownloadSpeed).append("]").toString());
        return maxDownloadSpeed;
    }

    public int statExternalInfo(long j, int i, String str, String str2) {
        XLLog.d(TAG, new StringBuilder("XLDownloadManager::statExternalInfo beg, taskId=[").append(j).append("] fileIndex=[").append(i).append("] key=[").append(str).append("] value=[").append(str2).append("]").toString());
        if (this.mLoader == null) {
            XLLog.e(TAG, new StringBuilder("XLDownloadManager::statExternalInfo mLoader is null, taskId=[").append(j).append("] fileIndex=[").append(i).append("]").toString());
            return 9900;
        }
        int statExternalInfo = this.mLoader.statExternalInfo(j, i, str, str2);
        if (9000 != statExternalInfo) {
            XLLog.e(TAG, new StringBuilder("XLDownloadManager::statExternalInfo end, taskId=[").append(j).append("] fileIndex=[").append(i).append("] ret=[").append(statExternalInfo).append("]").toString());
            return statExternalInfo;
        }
        XLLog.d(TAG, new StringBuilder("XLDownloadManager::statExternalInfo end, taskId=[").append(j).append("] fileIndex=[").append(i).append("] ret=[").append(statExternalInfo).append("]").toString());
        return statExternalInfo;
    }

    public int statExternalInfo(long j, int i, String str, int i2) {
        return statExternalInfo(j, i, str, String.valueOf(i2));
    }
}
