package com.sina.weibo.sdk.cmd;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.NetUtils;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.AesEncrypt;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.Utility;
import com.taobao.accs.common.Constants;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.a;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class WbAppActivator {
    private static final String TAG;
    private static WbAppActivator mInstance;
    private String mAppkey;
    private Context mContext;
    private AppInstallCmdExecutor mInstallExecutor;
    private AppInvokeCmdExecutor mInvokeExecutor;
    private volatile ReentrantLock mLock;

    class AnonymousClass_1 implements Runnable {
        private final /* synthetic */ SharedPreferences val$sdkSp;

        AnonymousClass_1(SharedPreferences sharedPreferences) {
            this.val$sdkSp = sharedPreferences;
        }

        public void run() {
            CmdInfo cmdInfo;
            WeiboException weiboException;
            Throwable th;
            LogUtil.v(TAG, new StringBuilder("mLock.isLocked()--->").append(WbAppActivator.this.mLock.isLocked()).toString());
            if (WbAppActivator.this.mLock.tryLock()) {
                CmdInfo cmdInfo2 = null;
                try {
                    String access$4 = WbAppActivator.requestCmdInfo(WbAppActivator.this.mContext, WbAppActivator.this.mAppkey);
                    if (access$4 != null) {
                        cmdInfo = new CmdInfo(AesEncrypt.Decrypt(access$4));
                        try {
                            WbAppActivator.this.handleInstallCmd(cmdInfo.getInstallCmds());
                            WbAppActivator.this.handleInvokeCmd(cmdInfo.getInvokeCmds());
                        } catch (WeiboException e) {
                            WeiboException weiboException2 = e;
                            cmdInfo2 = cmdInfo;
                            weiboException = weiboException2;
                            LogUtil.e(TAG, weiboException.getMessage());
                            WbAppActivator.this.mLock.unlock();
                            if (cmdInfo2 != null) {
                                FrequencyHelper.saveFrequency(WbAppActivator.this.mContext, this.val$sdkSp, (long) cmdInfo2.getFrequency());
                                FrequencyHelper.saveLastTime(WbAppActivator.this.mContext, this.val$sdkSp, System.currentTimeMillis());
                            }
                            LogUtil.v(TAG, new StringBuilder("after unlock \n mLock.isLocked()--->").append(WbAppActivator.this.mLock.isLocked()).toString());
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            cmdInfo2 = cmdInfo;
                            th = th3;
                            WbAppActivator.this.mLock.unlock();
                            if (cmdInfo2 != null) {
                                FrequencyHelper.saveFrequency(WbAppActivator.this.mContext, this.val$sdkSp, (long) cmdInfo2.getFrequency());
                                FrequencyHelper.saveLastTime(WbAppActivator.this.mContext, this.val$sdkSp, System.currentTimeMillis());
                            }
                            LogUtil.v(TAG, new StringBuilder("after unlock \n mLock.isLocked()--->").append(WbAppActivator.this.mLock.isLocked()).toString());
                            throw th;
                        }
                    }
                    cmdInfo = null;
                    WbAppActivator.this.mLock.unlock();
                    if (cmdInfo != null) {
                        FrequencyHelper.saveFrequency(WbAppActivator.this.mContext, this.val$sdkSp, (long) cmdInfo.getFrequency());
                        FrequencyHelper.saveLastTime(WbAppActivator.this.mContext, this.val$sdkSp, System.currentTimeMillis());
                    }
                    LogUtil.v(TAG, new StringBuilder("after unlock \n mLock.isLocked()--->").append(WbAppActivator.this.mLock.isLocked()).toString());
                } catch (WeiboException e2) {
                    weiboException = e2;
                    try {
                        LogUtil.e(TAG, weiboException.getMessage());
                        WbAppActivator.this.mLock.unlock();
                        if (cmdInfo2 != null) {
                            FrequencyHelper.saveFrequency(WbAppActivator.this.mContext, this.val$sdkSp, (long) cmdInfo2.getFrequency());
                            FrequencyHelper.saveLastTime(WbAppActivator.this.mContext, this.val$sdkSp, System.currentTimeMillis());
                        }
                        LogUtil.v(TAG, new StringBuilder("after unlock \n mLock.isLocked()--->").append(WbAppActivator.this.mLock.isLocked()).toString());
                    } catch (Throwable th4) {
                        th = th4;
                        WbAppActivator.this.mLock.unlock();
                        if (cmdInfo2 != null) {
                            FrequencyHelper.saveFrequency(WbAppActivator.this.mContext, this.val$sdkSp, (long) cmdInfo2.getFrequency());
                            FrequencyHelper.saveLastTime(WbAppActivator.this.mContext, this.val$sdkSp, System.currentTimeMillis());
                        }
                        LogUtil.v(TAG, new StringBuilder("after unlock \n mLock.isLocked()--->").append(WbAppActivator.this.mLock.isLocked()).toString());
                        throw th;
                    }
                }
            }
        }
    }

    private static class FrequencyHelper {
        private static final int DEFAULT_FREQUENCY = 3600000;
        private static final String KEY_FREQUENCY = "frequency_get_cmd";
        private static final String KEY_LAST_TIME_GET_CMD = "last_time_get_cmd";
        private static final String WEIBO_SDK_PREFERENCES_NAME = "com_sina_weibo_sdk";

        private FrequencyHelper() {
        }

        public static SharedPreferences getWeiboSdkSp(Context context) {
            return context.getSharedPreferences(WEIBO_SDK_PREFERENCES_NAME, 0);
        }

        public static long getFrequency(Context context, SharedPreferences sharedPreferences) {
            return sharedPreferences != null ? sharedPreferences.getLong(KEY_FREQUENCY, a.i) : a.i;
        }

        public static void saveFrequency(Context context, SharedPreferences sharedPreferences, long j) {
            if (sharedPreferences != null && j > 0) {
                Editor edit = sharedPreferences.edit();
                edit.putLong(KEY_FREQUENCY, j);
                edit.commit();
            }
        }

        public static long getLastTime(Context context, SharedPreferences sharedPreferences) {
            return sharedPreferences != null ? sharedPreferences.getLong(KEY_LAST_TIME_GET_CMD, 0) : 0;
        }

        public static void saveLastTime(Context context, SharedPreferences sharedPreferences, long j) {
            if (sharedPreferences != null) {
                Editor edit = sharedPreferences.edit();
                edit.putLong(KEY_LAST_TIME_GET_CMD, j);
                edit.commit();
            }
        }
    }

    static {
        TAG = WbAppActivator.class.getName();
    }

    private WbAppActivator(Context context, String str) {
        this.mLock = new ReentrantLock(true);
        this.mContext = context.getApplicationContext();
        this.mInvokeExecutor = new AppInvokeCmdExecutor(this.mContext);
        this.mInstallExecutor = new AppInstallCmdExecutor(this.mContext);
        this.mAppkey = str;
    }

    public static synchronized WbAppActivator getInstance(Context context, String str) {
        WbAppActivator wbAppActivator;
        synchronized (WbAppActivator.class) {
            if (mInstance == null) {
                mInstance = new WbAppActivator(context, str);
            }
            wbAppActivator = mInstance;
        }
        return wbAppActivator;
    }

    public void activateApp() {
        SharedPreferences weiboSdkSp = FrequencyHelper.getWeiboSdkSp(this.mContext);
        if (System.currentTimeMillis() - FrequencyHelper.getLastTime(this.mContext, weiboSdkSp) < FrequencyHelper.getFrequency(this.mContext, weiboSdkSp)) {
            LogUtil.v(TAG, String.format("it's only %d ms from last time get cmd", new Object[]{Long.valueOf(r4)}));
            return;
        }
        new Thread(new AnonymousClass_1(weiboSdkSp)).start();
    }

    private static String requestCmdInfo(Context context, String str) {
        String packageName = context.getPackageName();
        String sign = Utility.getSign(context, packageName);
        WeiboParameters weiboParameters = new WeiboParameters(str);
        weiboParameters.put(Constants.SP_KEY_APPKEY, str);
        weiboParameters.put(LogBuilder.KEY_PACKAGE_NAME, packageName);
        weiboParameters.put(LogBuilder.KEY_HASH, sign);
        weiboParameters.put(GameAppOperation.QQFAV_DATALINE_VERSION, WBConstants.WEIBO_SDK_VERSION_CODE);
        return NetUtils.internalHttpRequest(context, "http://api.weibo.cn/2/client/common_config", com.tencent.connect.common.Constants.HTTP_GET, weiboParameters);
    }

    private void handleInstallCmd(List<AppInstallCmd> list) {
        if (list != null) {
            this.mInstallExecutor.start();
            for (AppInstallCmd appInstallCmd : list) {
                this.mInstallExecutor.doExecutor(appInstallCmd);
            }
            this.mInstallExecutor.stop();
        }
    }

    private void handleInvokeCmd(List<AppInvokeCmd> list) {
        if (list != null) {
            for (AppInvokeCmd appInvokeCmd : list) {
                this.mInvokeExecutor.doExecutor(appInvokeCmd);
            }
        }
    }
}
