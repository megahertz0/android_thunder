package com.xunlei.androidvip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.util.Base64;
import com.xunlei.androidvip.parameter.AndroidVipGetTaskId;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedBillingReqParam;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedBillingResponse;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedFluxReqParam;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedFluxResponse;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedTaskReqParam;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedTaskResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineBtCommitReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineBtCommitResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineBtListReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineBtListResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineCommitReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineCommitResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineDeleteReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineDeleteResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineTaskReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineTaskResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineTasklistReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineTasklistResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineUserInfoReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineUserInfoResponse;
import com.xunlei.androidvip.parameter.VipTryCommitResult;
import com.xunlei.androidvip.parameter.VipTryParam;
import com.xunlei.androidvip.parameter.VipTryQueryResult;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class XLAndroidVipManager {
    public static final int ALREADY_INIT = 7003;
    public static final int NET_TYPE_MOBILE = 3;
    public static final int NET_TYPE_UNKNOW = 1;
    public static final int NET_TYPE_WIFI = 2;
    public static final int NOT_INIT = 7004;
    public static final int PARAM_ERROR = 7001;
    public static final int RUNNING = 7002;
    public static final int SUCCESS = 0;
    private static final String TAG = "XLAndroidVipManager";
    public static final int TASK_NOT_EXIST = 7005;
    public static final int XL_JNI_ERROR = 7000;
    private static XLAndroidVipManager mInstance;
    private Context mContext;
    private XLAndroidVipLoader mLoader;
    private NetworkChangeReceiver mReceiver;

    private class NetworkChangeReceiver extends BroadcastReceiver implements Runnable {
        private static final String TAG = "TAG_VipNetReceiver";
        int mNettype;
        Thread mThread;

        private NetworkChangeReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            XLLog.d(TAG, "call onReceive");
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                int networkType = XLAndroidVipUtil.getNetworkType(context);
                XLLog.d(TAG, new StringBuilder("onReceive nettype=").append(networkType).toString());
                synchronized (this) {
                    this.mNettype = networkType;
                    if (this.mThread != null) {
                        return;
                    }
                    this.mThread = new Thread(this);
                    this.mThread.start();
                }
            }
        }

        public void run() {
            while (true) {
                int i = this.mNettype;
                XLAndroidVipManager.this.AndroidVipSetNetWorkType(i);
                synchronized (this) {
                    if (i == this.mNettype) {
                        this.mThread = null;
                        return;
                    }
                }
            }
        }
    }

    static {
        mInstance = null;
    }

    public static synchronized XLAndroidVipManager getInstance() {
        XLAndroidVipManager xLAndroidVipManager;
        synchronized (XLAndroidVipManager.class) {
            if (mInstance == null) {
                mInstance = new XLAndroidVipManager();
            }
            xLAndroidVipManager = mInstance;
        }
        return xLAndroidVipManager;
    }

    public static synchronized XLAndroidVipManager getInstance(Context context) {
        XLAndroidVipManager xLAndroidVipManager;
        synchronized (XLAndroidVipManager.class) {
            if (mInstance == null) {
                mInstance = new XLAndroidVipManager(context);
            }
            xLAndroidVipManager = mInstance;
        }
        return xLAndroidVipManager;
    }

    private XLAndroidVipManager() {
        this.mReceiver = null;
        this.mContext = null;
        this.mLoader = new XLAndroidVipLoader();
        XLLog.init(new File(Environment.getExternalStorageDirectory().getPath(), "xunlei_ds_log.ini").getPath());
    }

    private XLAndroidVipManager(Context context) {
        this.mReceiver = null;
        this.mContext = null;
        this.mLoader = new XLAndroidVipLoader(context);
        XLLog.init(new File(Environment.getExternalStorageDirectory().getPath(), "xunlei_ds_log.ini").getPath());
    }

    public int AndroidVipInit(Context context, int i, String str) {
        if (context == null) {
            XLLog.e(TAG, new StringBuilder("AndroidVipInit context is null, thunder_flag=[").append(i).append("]").toString());
            return PARAM_ERROR;
        }
        this.mContext = context;
        String peerid = getPeerid();
        String path = context.getFilesDir().getPath();
        XLLog.d(TAG, new StringBuilder("AndroidVipInit beg, thunder_flag=[").append(i).append("] Peerid=[").append(Base64.encode(peerid.getBytes(), SUCCESS)).append("]").toString());
        int AndroidVipInit = this.mLoader.AndroidVipInit(peerid, path, i, str);
        if (AndroidVipInit != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipInit AndroidVipInit failed, thunder_flag=[").append(i).append("] errno=[").append(AndroidVipInit).append("]").toString());
            return AndroidVipInit;
        }
        AndroidVipSetNetWorkType(XLAndroidVipUtil.getNetworkType(context));
        doMonitorNetworkChange();
        XLLog.d(TAG, new StringBuilder("AndroidVipInit end, thunder_flag=[").append(i).append("] Peerid=[").append(Base64.encode(peerid.getBytes(), SUCCESS)).append("]").toString());
        return 0;
    }

    public int AndroidVipUninit() {
        XLLog.d(TAG, "AndroidVipUninit beg");
        undoMonitorNetworkChange();
        int AndroidVipUninit = this.mLoader.AndroidVipUninit();
        if (AndroidVipUninit != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipUninit end, errno=[").append(AndroidVipUninit).append("]").toString());
        } else {
            XLLog.d(TAG, "AndroidVipUninit end");
        }
        return AndroidVipUninit;
    }

    public int AndroidVipOfflineTasklistReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineTasklistReqParam androidVipOfflineTasklistReqParam) {
        return (androidVipGetTaskId == null || androidVipOfflineTasklistReqParam == null) ? PARAM_ERROR : this.mLoader.AndroidVipOfflineTasklistReq(androidVipGetTaskId, androidVipOfflineTasklistReqParam);
    }

    public int AndroidVipGetOfflineTasklistResp(long j, AndroidVipOfflineTasklistResponse androidVipOfflineTasklistResponse) {
        return (j == 0 || androidVipOfflineTasklistResponse == null) ? PARAM_ERROR : this.mLoader.AndroidVipGetOfflineTasklistResp(j, androidVipOfflineTasklistResponse);
    }

    public int AndroidVipDestroyOfflineTasklistReq(long j) {
        return j == 0 ? PARAM_ERROR : this.mLoader.AndroidVipDestroyOfflineTasklistReq(j);
    }

    public int AndroidVipOfflineCommitReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineCommitReqParam androidVipOfflineCommitReqParam) {
        if (androidVipGetTaskId == null || androidVipOfflineCommitReqParam == null) {
            XLLog.e(TAG, "AndroidVipOfflineCommitReq param is null");
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipOfflineCommitReq beg, TaskId=[").append(androidVipGetTaskId.getTaskId()).append("] mUserId=[").append(androidVipOfflineCommitReqParam.mUserId).append("] mKey=[").append(androidVipOfflineCommitReqParam.mKey).append("] mVipLevel=[").append(androidVipOfflineCommitReqParam.mVipLevel).append("] mAutoCharge=[").append(androidVipOfflineCommitReqParam.mAutoCharge).append("] mUrl=[").append(androidVipOfflineCommitReqParam.mUrl).append("] mRefUrl=[").append(androidVipOfflineCommitReqParam.mRefUrl).append("] mCookie=[").append(androidVipOfflineCommitReqParam.mCookie).append("] mTaskName=[").append(androidVipOfflineCommitReqParam.mTaskName).append("] mCid=[").append(androidVipOfflineCommitReqParam.mCid.length()).append("] mGcid=[").append(androidVipOfflineCommitReqParam.mGcid.length()).append("] mFileSize=[").append(androidVipOfflineCommitReqParam.mFileSize).append("] mFileType=[").append(androidVipOfflineCommitReqParam.mFileType).append("]").toString());
        int AndroidVipOfflineCommitReq = this.mLoader.AndroidVipOfflineCommitReq(androidVipGetTaskId, androidVipOfflineCommitReqParam);
        if (AndroidVipOfflineCommitReq != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipOfflineCommitReq AndroidVipOfflineCommitReq failed, errno=[").append(AndroidVipOfflineCommitReq).append("]").toString());
            return AndroidVipOfflineCommitReq;
        }
        XLLog.d(TAG, "AndroidVipOfflineCommitReq end success");
        return AndroidVipOfflineCommitReq;
    }

    public int AndroidVipGetOfflineCommitResp(long j, AndroidVipOfflineCommitResponse androidVipOfflineCommitResponse) {
        if (j == 0 || androidVipOfflineCommitResponse == null) {
            XLLog.e(TAG, "AndroidVipGetOfflineCommitResp param is null");
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipGetOfflineCommitResp beg, TaskId=[").append(j).append("]").toString());
        int AndroidVipGetOfflineCommitResp = this.mLoader.AndroidVipGetOfflineCommitResp(j, androidVipOfflineCommitResponse);
        if (7002 == AndroidVipGetOfflineCommitResp) {
            XLLog.d(TAG, new StringBuilder("AndroidVipGetOfflineCommitResp AndroidVipGetOfflineCommitResp end is running, TaskId=[").append(j).append("] errno=[").append(AndroidVipGetOfflineCommitResp).append("]").toString());
            return RUNNING;
        } else if (AndroidVipGetOfflineCommitResp != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipGetOfflineCommitResp AndroidVipGetOfflineCommitResp failed, TaskId=[").append(j).append("] errno=[").append(AndroidVipGetOfflineCommitResp).append("]").toString());
            return AndroidVipGetOfflineCommitResp;
        } else {
            XLLog.d(TAG, new StringBuilder("AndroidVipGetOfflineCommitResp end success, TaskId=[").append(j).append("] mResult=[").append(androidVipOfflineCommitResponse.mResult).append("] mMessage=[").append(androidVipOfflineCommitResponse.mMessage).append("] mAvailableSpace=[").append(androidVipOfflineCommitResponse.mAvailableSpace).append("] mMaxStore=[").append(androidVipOfflineCommitResponse.mMaxStore).append("] mMaxTaskNum=[").append(androidVipOfflineCommitResponse.mMaxTaskNum).append("] mCurrentTaskNum=[").append(androidVipOfflineCommitResponse.mCurrentTaskNum).append("] mTaskNum=[").append(androidVipOfflineCommitResponse.mTaskNum).append("]").toString());
            return AndroidVipGetOfflineCommitResp;
        }
    }

    public int AndroidVipDestroyOfflineCommitReq(long j) {
        if (j == 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipDestroyOfflineCommitReq TaskId is invalid, TaskId=[").append(j).append("]").toString());
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipDestroyOfflineCommitReq beg, TaskId=[").append(j).append("]").toString());
        int AndroidVipDestroyOfflineCommitReq = this.mLoader.AndroidVipDestroyOfflineCommitReq(j);
        if (AndroidVipDestroyOfflineCommitReq != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipDestroyOfflineCommitReq AndroidVipDestroyOfflineCommitReq failed, TaskId=[").append(j).append("] errno=[").append(AndroidVipDestroyOfflineCommitReq).append("]").toString());
            return AndroidVipDestroyOfflineCommitReq;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipDestroyOfflineCommitReq end, TaskId=[").append(j).append("]").toString());
        return SUCCESS;
    }

    public int AndroidVipOfflineBtCommitReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineBtCommitReqParam androidVipOfflineBtCommitReqParam) {
        if (androidVipGetTaskId == null || androidVipOfflineBtCommitReqParam == null) {
            XLLog.e(TAG, "AndroidVipOfflineBtCommitReq param is null");
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipOfflineBtCommitReq beg, vipTaskId=[").append(androidVipGetTaskId.getTaskId()).append("] mKey=[").append(androidVipOfflineBtCommitReqParam.mKey).append("] mUserId=[").append(androidVipOfflineBtCommitReqParam.mUserId).append("] mVipLevel=[").append(androidVipOfflineBtCommitReqParam.mVipLevel).append("] mAutoCharge=[").append(androidVipOfflineBtCommitReqParam.mAutoCharge).append("] mInfoHash=[").append(androidVipOfflineBtCommitReqParam.mInfoHash).append("] mBtTitle=[").append(androidVipOfflineBtCommitReqParam.mBtTitle).append("] mRefUrl=[").append(androidVipOfflineBtCommitReqParam.mRefUrl).append("] mFilePath=[").append(androidVipOfflineBtCommitReqParam.mFilePath).append("] mFileListNum=[").append(androidVipOfflineBtCommitReqParam.mFileListNum).append("] mMainTaskId=[").append(androidVipOfflineBtCommitReqParam.mMainTaskId).append("]").toString());
        int AndroidVipOfflineBtCommitReq = this.mLoader.AndroidVipOfflineBtCommitReq(androidVipGetTaskId, androidVipOfflineBtCommitReqParam);
        if (AndroidVipOfflineBtCommitReq != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipOfflineBtCommitReq AndroidVipOfflineBtCommitReq failed, vipTaskId=[").append(androidVipGetTaskId.getTaskId()).append("] errno=[").append(AndroidVipOfflineBtCommitReq).append("]").toString());
            return AndroidVipOfflineBtCommitReq;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipOfflineBtCommitReq end success, vipTaskId=[").append(androidVipGetTaskId.getTaskId()).append("]").toString());
        return SUCCESS;
    }

    public int AndroidVipGetOfflineBtCommitResp(long j, AndroidVipOfflineBtCommitResponse androidVipOfflineBtCommitResponse) {
        if (0 == j || androidVipOfflineBtCommitResponse == null) {
            XLLog.e(TAG, new StringBuilder("AndroidVipGetOfflineBtCommitResp param is invalid, TaskId=[").append(j).append("]").toString());
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipGetOfflineBtCommitResp beg, TaskId=[").append(j).append("]").toString());
        int AndroidVipGetOfflineBtCommitResp = this.mLoader.AndroidVipGetOfflineBtCommitResp(j, androidVipOfflineBtCommitResponse);
        if (AndroidVipGetOfflineBtCommitResp != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipGetOfflineBtCommitResp AndroidVipGetOfflineBtCommitResp failed, TaskId=[").append(j).append("] errno=[").append(AndroidVipGetOfflineBtCommitResp).append("]").toString());
            return AndroidVipGetOfflineBtCommitResp;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipGetOfflineBtCommitResp end success, TaskId=[").append(j).append("] mResult=[").append(androidVipOfflineBtCommitResponse.mResult).append("] mMessage=[").append(androidVipOfflineBtCommitResponse.mMessage).append("] mInfoHash=[").append(androidVipOfflineBtCommitResponse.mInfoHash).append("] mAvailableSpace=[").append(androidVipOfflineBtCommitResponse.mAvailableSpace).append("] mMaxStore=[").append(androidVipOfflineBtCommitResponse.mMaxStore).append("] mFileSize=[").append(androidVipOfflineBtCommitResponse.mFileSize).append("] mMaxTaskNum=[").append(androidVipOfflineBtCommitResponse.mMaxTaskNum).append("] mCurrentTaskNum=[").append(androidVipOfflineBtCommitResponse.mCurrentTaskNum).append("] mMainTaskId=[").append(androidVipOfflineBtCommitResponse.mMainTaskId).append("] mTaskIdListNum=[").append(androidVipOfflineBtCommitResponse.mTaskIdListNum).append("] mTaskNum=[").append(androidVipOfflineBtCommitResponse.mTaskNum).append("] mClassValue=[").append(androidVipOfflineBtCommitResponse.mClassValue).append("] mLeftLiveTime=[").append(androidVipOfflineBtCommitResponse.mLeftLiveTime).append("] mCommitTime=[").append(androidVipOfflineBtCommitResponse.mCommitTime).append("] mFileAttr=[").append(androidVipOfflineBtCommitResponse.mFileAttr).append("] mProgress=[").append(androidVipOfflineBtCommitResponse.mProgress).append("]").toString());
        return SUCCESS;
    }

    public int AndroidVipDestroyOfflineBtCommitReq(long j) {
        return j == 0 ? PARAM_ERROR : this.mLoader.AndroidVipDestroyOfflineBtCommitReq(j);
    }

    public int AndroidVipOfflineDeleteReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineDeleteReqParam androidVipOfflineDeleteReqParam) {
        return (androidVipGetTaskId == null || androidVipOfflineDeleteReqParam == null) ? PARAM_ERROR : this.mLoader.AndroidVipOfflineDeleteReq(androidVipGetTaskId, androidVipOfflineDeleteReqParam);
    }

    public int AndroidVipGetOfflineDeleteResp(long j, AndroidVipOfflineDeleteResponse androidVipOfflineDeleteResponse) {
        return (j == 0 || androidVipOfflineDeleteResponse == null) ? PARAM_ERROR : this.mLoader.AndroidVipGetOfflineDeleteResp(j, androidVipOfflineDeleteResponse);
    }

    public int AndroidVipDestroyOfflineDeleteReq(long j) {
        return j == 0 ? PARAM_ERROR : this.mLoader.AndroidVipDestroyOfflineDeleteReq(j);
    }

    public int AndroidVipOfflineBtListReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineBtListReqParam androidVipOfflineBtListReqParam) {
        return (androidVipGetTaskId == null || androidVipOfflineBtListReqParam == null) ? PARAM_ERROR : this.mLoader.AndroidVipOfflineBtListReq(androidVipGetTaskId, androidVipOfflineBtListReqParam);
    }

    public int AndroidVipGetOfflineBtListResp(long j, AndroidVipOfflineBtListResponse androidVipOfflineBtListResponse) {
        return (j == 0 || androidVipOfflineBtListResponse == null) ? PARAM_ERROR : this.mLoader.AndroidVipGetOfflineBtListResp(j, androidVipOfflineBtListResponse);
    }

    public int AndroidVipDestroyOfflineBtListReq(long j) {
        return j == 0 ? PARAM_ERROR : this.mLoader.AndroidVipDestroyOfflineBtListReq(j);
    }

    public int AndroidVipOfflineUserInfoReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineUserInfoReqParam androidVipOfflineUserInfoReqParam) {
        return (androidVipGetTaskId == null || androidVipOfflineUserInfoReqParam == null) ? PARAM_ERROR : this.mLoader.AndroidVipOfflineUserInfoReq(androidVipGetTaskId, androidVipOfflineUserInfoReqParam);
    }

    public int AndroidVipGetOfflineUserInfoResp(long j, AndroidVipOfflineUserInfoResponse androidVipOfflineUserInfoResponse) {
        return (j == 0 || androidVipOfflineUserInfoResponse == null) ? PARAM_ERROR : this.mLoader.AndroidVipGetOfflineUserInfoResp(j, androidVipOfflineUserInfoResponse);
    }

    public int AndroidVipDestroyOfflineUserInfoReq(long j) {
        return j == 0 ? PARAM_ERROR : this.mLoader.AndroidVipDestroyOfflineUserInfoReq(j);
    }

    public int AndroidVipOfflineTaskReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineTaskReqParam androidVipOfflineTaskReqParam) {
        return (androidVipGetTaskId == null || androidVipOfflineTaskReqParam == null) ? PARAM_ERROR : this.mLoader.AndroidVipOfflineTaskReq(androidVipGetTaskId, androidVipOfflineTaskReqParam);
    }

    public int AndroidVipGetOfflineTaskResp(long j, AndroidVipOfflineTaskResponse androidVipOfflineTaskResponse) {
        return (j == 0 || androidVipOfflineTaskResponse == null) ? PARAM_ERROR : this.mLoader.AndroidVipGetOfflineTaskResp(j, androidVipOfflineTaskResponse);
    }

    public int AndroidVipDestroyOfflineTaskReq(long j) {
        return j == 0 ? PARAM_ERROR : this.mLoader.AndroidVipDestroyOfflineTaskReq(j);
    }

    public int AndroidVipCreateEnterHighSpeedTask(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipHighSpeedTaskReqParam androidVipHighSpeedTaskReqParam) {
        if (androidVipGetTaskId == null || androidVipHighSpeedTaskReqParam == null) {
            XLLog.e(TAG, "AndroidVipCreateEnterHighSpeedTask param is null");
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipCreateEnterHighSpeedTask beg,  mUserId=[").append(androidVipHighSpeedTaskReqParam.mUserId).append("] mGcid=[").append(androidVipHighSpeedTaskReqParam.mGcid.length()).append("] mCid=[").append(androidVipHighSpeedTaskReqParam.mCid.length()).append("] mFileSize=[").append(androidVipHighSpeedTaskReqParam.mFileSize).append("]").toString());
        int AndroidVipCreateEnterHighSpeedTask = this.mLoader.AndroidVipCreateEnterHighSpeedTask(androidVipGetTaskId, androidVipHighSpeedTaskReqParam);
        if (AndroidVipCreateEnterHighSpeedTask != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipCreateEnterHighSpeedTask AndroidVipCreateEnterHighSpeedTask failed, errno=[").append(AndroidVipCreateEnterHighSpeedTask).append("]").toString());
            return AndroidVipCreateEnterHighSpeedTask;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipCreateEnterHighSpeedTask end success, vipTaskId=[").append(androidVipGetTaskId.getTaskId()).append("]").toString());
        return SUCCESS;
    }

    public int AndroidVipGetHighSpeedTaskResp(long j, AndroidVipHighSpeedTaskResponse androidVipHighSpeedTaskResponse) {
        if (j == 0 || androidVipHighSpeedTaskResponse == null) {
            XLLog.e(TAG, new StringBuilder("AndroidVipGetHighSpeedTaskResp param is invalid, TaskId=[").append(j).append("]").toString());
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipGetHighSpeedTaskResp beg, TaskId=[").append(j).append("]").toString());
        int AndroidVipGetHighSpeedTaskResp = this.mLoader.AndroidVipGetHighSpeedTaskResp(j, androidVipHighSpeedTaskResponse);
        if (AndroidVipGetHighSpeedTaskResp != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipGetHighSpeedTaskResp AndroidVipGetHighSpeedTaskResp failed, TaskId=[").append(j).append("] errno=[").append(j).append("]").toString());
            return AndroidVipGetHighSpeedTaskResp;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipGetHighSpeedTaskResp end success, TaskId=[").append(j).append("] mResult=[").append(androidVipHighSpeedTaskResponse.mResult).append("] mPeerCount=[").append(androidVipHighSpeedTaskResponse.mPeerCount).append("] mServerCount=[").append(androidVipHighSpeedTaskResponse.mServerCount).append("]").toString());
        return SUCCESS;
    }

    public int AndroidVipDestroyHighSpeedTask(long j) {
        if (j == 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipDestroyHighSpeedTask TaskId is invalid, TaskId=[").append(j).append("]").toString());
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipDestroyHighSpeedTask beg, TaskId=[").append(j).append("]").toString());
        int AndroidVipDestroyHighSpeedTask = this.mLoader.AndroidVipDestroyHighSpeedTask(j);
        if (AndroidVipDestroyHighSpeedTask != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipDestroyHighSpeedTask AndroidVipDestroyHighSpeedTask failed, TaskId=[").append(j).append("] errno=[").append(AndroidVipDestroyHighSpeedTask).append("]").toString());
            return AndroidVipDestroyHighSpeedTask;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipDestroyHighSpeedTask end, TaskId=[").append(j).append("]").toString());
        return AndroidVipDestroyHighSpeedTask;
    }

    public int AndroidVipCreateFluxQueryTask(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipHighSpeedFluxReqParam androidVipHighSpeedFluxReqParam) {
        return (androidVipGetTaskId == null || androidVipHighSpeedFluxReqParam == null) ? PARAM_ERROR : this.mLoader.AndroidVipCreateFluxQueryTask(androidVipGetTaskId, androidVipHighSpeedFluxReqParam);
    }

    public int AndroidVipGetFluxQueryTaskResp(long j, AndroidVipHighSpeedFluxResponse androidVipHighSpeedFluxResponse) {
        return (j == 0 || androidVipHighSpeedFluxResponse == null) ? PARAM_ERROR : this.mLoader.AndroidVipGetFluxQueryTaskResp(j, androidVipHighSpeedFluxResponse);
    }

    public int AndroidVipDestroyFluxQueryTask(long j) {
        return j == 0 ? PARAM_ERROR : this.mLoader.AndroidVipDestroyFluxQueryTask(j);
    }

    public int AndroidVipCreateHighSpeedBillingTask(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipHighSpeedBillingReqParam androidVipHighSpeedBillingReqParam) {
        if (androidVipGetTaskId == null || androidVipHighSpeedBillingReqParam == null) {
            XLLog.e(TAG, new StringBuilder("AndroidVipCreateHighSpeedBillingTask param is null, vipTaskId=[").append(androidVipGetTaskId.getTaskId()).append("]").toString());
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipCreateHighSpeedBillingTask beg, vipTaskId=[").append(androidVipGetTaskId.getTaskId()).append("] mUserId=[").append(androidVipHighSpeedBillingReqParam.mUserId).append("] mKey=[").append(androidVipHighSpeedBillingReqParam.mKey).append("] mResType=[").append(androidVipHighSpeedBillingReqParam.mResType).append("] mResId=[").append(androidVipHighSpeedBillingReqParam.mResId).append("] mGcid=[").append(androidVipHighSpeedBillingReqParam.mGcid.length()).append("] mCid=[").append(androidVipHighSpeedBillingReqParam.mCid.length()).append("] mFileSize=[").append(androidVipHighSpeedBillingReqParam.mFileSize).append("] mFileIndex=[").append(androidVipHighSpeedBillingReqParam.mFileIndex).append("] mFileName=[").append(androidVipHighSpeedBillingReqParam.mFileName).append("] mBussinessFlag=[").append(androidVipHighSpeedBillingReqParam.mBussinessFlag).append("]").toString());
        int AndroidVipCreateHighSpeedBillingTask = this.mLoader.AndroidVipCreateHighSpeedBillingTask(androidVipGetTaskId, androidVipHighSpeedBillingReqParam);
        if (AndroidVipCreateHighSpeedBillingTask != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipCreateHighSpeedBillingTask AndroidVipCreateHighSpeedBillingTask failed, vipTaskId=[").append(androidVipGetTaskId.getTaskId()).append("] errno=[").append(AndroidVipCreateHighSpeedBillingTask).append("]").toString());
            return AndroidVipCreateHighSpeedBillingTask;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipCreateHighSpeedBillingTask end success, vipTaskId=[").append(androidVipGetTaskId.getTaskId()).append("]").toString());
        return SUCCESS;
    }

    public int AndroidVipGetHighSpeedBillingTaskResp(long j, AndroidVipHighSpeedBillingResponse androidVipHighSpeedBillingResponse) {
        if (j == 0 || androidVipHighSpeedBillingResponse == null) {
            XLLog.e(TAG, new StringBuilder("AndroidVipGetHighSpeedBillingTaskResp param is null, TaskId=[").append(j).append("]").toString());
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipGetHighSpeedBillingTaskResp beg, TaskId=[").append(j).append("]").toString());
        int AndroidVipGetHighSpeedBillingTaskResp = this.mLoader.AndroidVipGetHighSpeedBillingTaskResp(j, androidVipHighSpeedBillingResponse);
        if (7002 == AndroidVipGetHighSpeedBillingTaskResp) {
            XLLog.d(TAG, new StringBuilder("AndroidVipGetHighSpeedBillingTaskResp AndroidVipGetHighSpeedBillingTaskResp end is running, TaskId=[").append(j).append("] errno=[").append(AndroidVipGetHighSpeedBillingTaskResp).append("]").toString());
            return RUNNING;
        } else if (AndroidVipGetHighSpeedBillingTaskResp != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipGetHighSpeedBillingTaskResp AndroidVipGetHighSpeedBillingTaskResp failed, TaskId=[").append(j).append("] errno=[").append(AndroidVipGetHighSpeedBillingTaskResp).append("]").toString());
            return AndroidVipGetHighSpeedBillingTaskResp;
        } else {
            XLLog.d(TAG, new StringBuilder("AndroidVipGetHighSpeedBillingTaskResp end success, TaskId=[").append(j).append("] mResult=[").append(androidVipHighSpeedBillingResponse.mResult).append("] mMessage=[").append(androidVipHighSpeedBillingResponse.mMessage).append("] mCapacity=[").append(androidVipHighSpeedBillingResponse.mCapacity).append("] mRemain=[").append(androidVipHighSpeedBillingResponse.mRemain).append("] mNeeded=[").append(androidVipHighSpeedBillingResponse.mNeeded).append("]").toString());
            return SUCCESS;
        }
    }

    public int AndroidVipDestroyHighSpeedBillingTask(long j) {
        if (j == 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipDestroyHighSpeedBillingTask TaskId is invalid, TaskId=[").append(j).append("]").toString());
            return PARAM_ERROR;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipDestroyHighSpeedBillingTask beg, TaskId=[").append(j).append("]").toString());
        int AndroidVipDestroyHighSpeedBillingTask = this.mLoader.AndroidVipDestroyHighSpeedBillingTask(j);
        if (AndroidVipDestroyHighSpeedBillingTask != 0) {
            XLLog.e(TAG, new StringBuilder("AndroidVipDestroyHighSpeedBillingTask AndroidVipDestroyHighSpeedBillingTask failed, TaskId=[").append(j).append("] errno=[").append(AndroidVipDestroyHighSpeedBillingTask).append("]").toString());
            return AndroidVipDestroyHighSpeedBillingTask;
        }
        XLLog.d(TAG, new StringBuilder("AndroidVipDestroyHighSpeedBillingTask end, TaskId=[").append(j).append("]").toString());
        return SUCCESS;
    }

    public int vipCreateHighSpeedTryTask(VipTryParam vipTryParam, AndroidVipGetTaskId androidVipGetTaskId) {
        if (vipTryParam == null || androidVipGetTaskId == null || !vipTryParam.checkMemberVar()) {
            return -1;
        }
        if (vipTryParam.mResType == 1) {
            vipTryParam.mUrl = new StringBuilder("bt://").append(vipTryParam.mResId).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(vipTryParam.mFileIndex).toString();
            XLLog.i(TAG, new StringBuilder("BT Url: ").append(vipTryParam.mUrl).toString());
        }
        return this.mLoader.AndroidVipCreateHighSpeedTryTask(vipTryParam, androidVipGetTaskId);
    }

    public int vipGetHighSpeedTryResult(long j, VipTryQueryResult vipTryQueryResult) {
        return vipTryQueryResult != null ? this.mLoader.AndroidVipGetHighSpeedTryResult(j, vipTryQueryResult) : -1;
    }

    public int vipDestoryHighSpeedTryTask(long j) {
        return this.mLoader != null ? this.mLoader.AndroidVipDestoryHighSpeedTryTask(j) : -1;
    }

    public int vipCreateHighSpeedTryCommitTask(VipTryParam vipTryParam, AndroidVipGetTaskId androidVipGetTaskId) {
        if (vipTryParam == null || androidVipGetTaskId == null || !vipTryParam.checkMemberVar() || this.mLoader == null) {
            return -1;
        }
        if (vipTryParam.mResType == 1) {
            vipTryParam.mUrl = new StringBuilder("bt://").append(vipTryParam.mResId).append(MqttTopic.TOPIC_LEVEL_SEPARATOR).append(vipTryParam.mFileIndex).toString();
            XLLog.i(TAG, new StringBuilder("BT Url: ").append(vipTryParam.mUrl).toString());
        }
        return this.mLoader.AndroidVipCreateHighSpeedTryCommitTask(vipTryParam, androidVipGetTaskId);
    }

    public int vipGetHighSpeedTryCommitResult(long j, VipTryCommitResult vipTryCommitResult) {
        return vipTryCommitResult != null ? this.mLoader.AndroidVipGetHighSpeedTryCommitResult(j, vipTryCommitResult) : -1;
    }

    public int vipDestoryHighSpeedTryCommitTask(long j) {
        return this.mLoader.AndroidVipDestoryHighSpeedTryCommitTask(j);
    }

    public int vipSetTaskRetryFlag(long j, int i) {
        int AndroidVipSetTaskRetryFlag = this.mLoader.AndroidVipSetTaskRetryFlag(j, i);
        XLLog.d(TAG, new StringBuilder("AndroidVipSetTaskRetryFlag TaskId=[").append(j).append("], flag=[").append(i).append("], ret=[").append(AndroidVipSetTaskRetryFlag).append("]").toString());
        return AndroidVipSetTaskRetryFlag;
    }

    public int AndroidVipSetNetWorkType(int i) {
        return (i <= 0 || i > 3) ? PARAM_ERROR : this.mLoader.AndroidVipSetNetWorkType(i);
    }

    private String getPeerid() {
        String peerid = XLAndroidVipUtil.getPeerid(this.mContext);
        return peerid == null ? "000000000000000V" : peerid;
    }

    private void doMonitorNetworkChange() {
        XLLog.i(TAG, "doMonitorNetworkChange()");
        if (this.mContext != null && this.mReceiver == null) {
            this.mReceiver = new NetworkChangeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            XLLog.i(TAG, "register Receiver");
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }
    }

    private void undoMonitorNetworkChange() {
        XLLog.i(TAG, "undoMonitorNetworkChange()");
        if (this.mContext != null && this.mReceiver != null) {
            this.mContext.unregisterReceiver(this.mReceiver);
            XLLog.i(TAG, "unregister Receiver");
            this.mReceiver = null;
        }
    }
}
