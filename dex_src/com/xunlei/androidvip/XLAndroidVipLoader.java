package com.xunlei.androidvip;

import android.content.Context;
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

class XLAndroidVipLoader {
    private static final String TAG = "XAndroidVipLoader";

    public native int AndroidVipCreateEnterHighSpeedTask(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipHighSpeedTaskReqParam androidVipHighSpeedTaskReqParam);

    public native int AndroidVipCreateFluxQueryTask(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipHighSpeedFluxReqParam androidVipHighSpeedFluxReqParam);

    public native int AndroidVipCreateHighSpeedBillingTask(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipHighSpeedBillingReqParam androidVipHighSpeedBillingReqParam);

    public native int AndroidVipCreateHighSpeedTryCommitTask(VipTryParam vipTryParam, AndroidVipGetTaskId androidVipGetTaskId);

    public native int AndroidVipCreateHighSpeedTryTask(VipTryParam vipTryParam, AndroidVipGetTaskId androidVipGetTaskId);

    public native int AndroidVipDestoryHighSpeedTryCommitTask(long j);

    public native int AndroidVipDestoryHighSpeedTryTask(long j);

    public native int AndroidVipDestroyFluxQueryTask(long j);

    public native int AndroidVipDestroyHighSpeedBillingTask(long j);

    public native int AndroidVipDestroyHighSpeedTask(long j);

    public native int AndroidVipDestroyOfflineBtCommitReq(long j);

    public native int AndroidVipDestroyOfflineBtListReq(long j);

    public native int AndroidVipDestroyOfflineCommitReq(long j);

    public native int AndroidVipDestroyOfflineDeleteReq(long j);

    public native int AndroidVipDestroyOfflineTaskReq(long j);

    public native int AndroidVipDestroyOfflineTasklistReq(long j);

    public native int AndroidVipDestroyOfflineUserInfoReq(long j);

    public native int AndroidVipGetFluxQueryTaskResp(long j, AndroidVipHighSpeedFluxResponse androidVipHighSpeedFluxResponse);

    public native int AndroidVipGetHighSpeedBillingTaskResp(long j, AndroidVipHighSpeedBillingResponse androidVipHighSpeedBillingResponse);

    public native int AndroidVipGetHighSpeedTaskResp(long j, AndroidVipHighSpeedTaskResponse androidVipHighSpeedTaskResponse);

    public native int AndroidVipGetHighSpeedTryCommitResult(long j, VipTryCommitResult vipTryCommitResult);

    public native int AndroidVipGetHighSpeedTryResult(long j, VipTryQueryResult vipTryQueryResult);

    public native int AndroidVipGetOfflineBtCommitResp(long j, AndroidVipOfflineBtCommitResponse androidVipOfflineBtCommitResponse);

    public native int AndroidVipGetOfflineBtListResp(long j, AndroidVipOfflineBtListResponse androidVipOfflineBtListResponse);

    public native int AndroidVipGetOfflineCommitResp(long j, AndroidVipOfflineCommitResponse androidVipOfflineCommitResponse);

    public native int AndroidVipGetOfflineDeleteResp(long j, AndroidVipOfflineDeleteResponse androidVipOfflineDeleteResponse);

    public native int AndroidVipGetOfflineTaskResp(long j, AndroidVipOfflineTaskResponse androidVipOfflineTaskResponse);

    public native int AndroidVipGetOfflineTasklistResp(long j, AndroidVipOfflineTasklistResponse androidVipOfflineTasklistResponse);

    public native int AndroidVipGetOfflineUserInfoResp(long j, AndroidVipOfflineUserInfoResponse androidVipOfflineUserInfoResponse);

    public native int AndroidVipInit(String str, String str2, int i, String str3);

    public native int AndroidVipOfflineBtCommitReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineBtCommitReqParam androidVipOfflineBtCommitReqParam);

    public native int AndroidVipOfflineBtListReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineBtListReqParam androidVipOfflineBtListReqParam);

    public native int AndroidVipOfflineCommitReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineCommitReqParam androidVipOfflineCommitReqParam);

    public native int AndroidVipOfflineDeleteReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineDeleteReqParam androidVipOfflineDeleteReqParam);

    public native int AndroidVipOfflineTaskReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineTaskReqParam androidVipOfflineTaskReqParam);

    public native int AndroidVipOfflineTasklistReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineTasklistReqParam androidVipOfflineTasklistReqParam);

    public native int AndroidVipOfflineUserInfoReq(AndroidVipGetTaskId androidVipGetTaskId, AndroidVipOfflineUserInfoReqParam androidVipOfflineUserInfoReqParam);

    public native int AndroidVipSetNetWorkType(int i);

    public native int AndroidVipSetTaskRetryFlag(long j, int i);

    public native int AndroidVipUninit();

    public XLAndroidVipLoader() {
        XLLog.i(TAG, "---------- XLLoader()");
        System.loadLibrary("xl_stat");
        System.loadLibrary("vip_channel2");
        System.loadLibrary("vip_channel2_iface");
    }

    public XLAndroidVipLoader(Context context) {
        XLLog.i(TAG, "---------- XLLoader()");
        ReLinker.loadLibrary(context, "xl_stat");
        ReLinker.loadLibrary(context, "vip_channel2");
        ReLinker.loadLibrary(context, "vip_channel2_iface");
    }

    public XLAndroidVipLoader(String str) {
        XLLog.i(TAG, new StringBuilder("begin to load, libPath: ").append(str).toString());
        if (str != null) {
            System.load(str + "libxl_stat.so");
            System.load(str + "libvip_channel2.so");
            System.load(str + "llibvip_channel2_iface.so");
        }
    }
}
