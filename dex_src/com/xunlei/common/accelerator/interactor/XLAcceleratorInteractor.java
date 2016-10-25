package com.xunlei.common.accelerator.interactor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.xunlei.common.accelerator.XLAccelerator;
import com.xunlei.common.accelerator.XLOnAccelListener;
import com.xunlei.common.accelerator.bean.AccelInfoResultBean;
import com.xunlei.common.accelerator.bean.KnParams;
import com.xunlei.common.accelerator.bean.StartAccelResultBean;
import com.xunlei.common.accelerator.bean.StopAccelResultBean;
import com.xunlei.common.accelerator.bean.TryInfoResultBean;
import com.xunlei.common.accelerator.bean.XLAccelBackupInfo;
import com.xunlei.common.accelerator.bean.XLAccelBackupInfo.AccelStatus;
import com.xunlei.common.accelerator.bean.XLAccelBandInfo;
import com.xunlei.common.accelerator.bean.XLAccelTryInfo;
import com.xunlei.common.accelerator.bean.XLAccelUser;
import com.xunlei.common.accelerator.controller.XLAccelController;
import com.xunlei.common.accelerator.http.XLAccelHttpReqInfo;
import com.xunlei.common.accelerator.model.XLAccelModel;
import com.xunlei.common.accelerator.utils.ErrorCodeUtils;
import com.xunlei.common.accelerator.utils.XLNetworkUtils;
import com.xunlei.common.accelerator.utils.XLParameterUtils;
import com.xunlei.common.member.XLAvatarItem;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLHspeedCapacity;
import com.xunlei.common.member.XLLixianCapacity;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.thundersniffer.sniff.SniffingError;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONObject;

public class XLAcceleratorInteractor extends BaseInteractor implements XLAccelerator {
    private static final String TAG = "XLAcceleratorInteractor.java";
    private XLAccelUser alreadyAccelUser;
    private String automaticGetAccelErrorMessage;
    private String changeClientType;
    private int changeUserCount;
    private int getAccelInfoRetry;
    private int getTryAccelInfoRetry;
    private int isKuaiNiao;
    private boolean isPaySuccess;
    private boolean isReLogin;
    private final CountDownLatch latch;
    private XLAccelUser mAccelUser;
    private AccelInfoResultBean mBandInfo;
    private Context mContext;
    private int mCurrentAccelStatus;
    private int mCurrentStatus;
    private int mGetAccelInfoCode;
    private boolean mHadPortal;
    private Handler mHandler;
    private boolean mInited;
    private boolean mIsLoading;
    private boolean mIsReInit;
    private int mKeepAliveRetry;
    private boolean mKeepAliving;
    private KnParams mKnParams;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private int mPortalErrorCode;
    private StartAccelResultBean mStartInfo;
    private StopAccelResultBean mStopInfo;
    private TryInfoResultBean mTryInfo;
    private List<XLOnAccelListener> mUserListeners;
    XLOnUserListener mXLOnUserListener;
    private Runnable recoverQueryRunnable;
    private int startAccelRetry;
    private XLAccelController xLAccelController;
    private XLParameterUtils xlParameterUtils;

    private class NetworkChangeReceiver extends BroadcastReceiver {
        private final int MAX_KEEPALIVE_RETRY_TIME;
        private boolean needPortal;
        private int needRetry;

        private NetworkChangeReceiver() {
            this.MAX_KEEPALIVE_RETRY_TIME = 1;
            this.needRetry = 0;
            this.needPortal = false;
        }

        public void setNeedRetry() {
            int i = this.needRetry + 1;
            this.needRetry = i;
            if (i > 1) {
                XLAcceleratorInteractor.this.setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
                this.needRetry = 0;
            }
        }

        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                return;
            }
            if (XLNetworkUtils.isWIFIActive(context)) {
                if (this.needRetry > 0) {
                    this.needRetry = 0;
                    XLAcceleratorInteractor.this.restartKeepAlive();
                }
                if (this.needPortal) {
                    XLAcceleratorInteractor.this.mCurrentStatus = 0;
                    XLAcceleratorInteractor.this.recoverQueryCount();
                    XLAcceleratorInteractor.this.reInitQueryStatus();
                    XLAcceleratorInteractor.this.queryStatus();
                    this.needPortal = false;
                }
            } else if (XLNetworkUtils.getSpecificNetworkType(XLAcceleratorInteractor.this.mContext) == 0) {
                this.needPortal = true;
                XLAcceleratorInteractor.this.mCurrentStatus = R.styleable.Toolbar_titleMargins;
                XLAcceleratorInteractor.this.dispatchCallBack(XLAcceleratorInteractor.this.mCurrentStatus, SpdyProtocol.PUBKEY_SEQ_OPEN, ErrorCodeUtils.getErrorDesc(SpdyProtocol.PUBKEY_SEQ_OPEN));
                XLAcceleratorInteractor.this.stopKeepAlive();
                XLAcceleratorInteractor.this.stopTimeCount();
            } else {
                this.needPortal = true;
                XLAcceleratorInteractor.this.mCurrentStatus = SpdyProtocol.PUBKEY_PSEQ_OPEN;
                XLAcceleratorInteractor.this.dispatchCallBack(XLAcceleratorInteractor.this.mCurrentStatus, SpdyProtocol.PUBKEY_PSEQ_OPEN, ErrorCodeUtils.getErrorDesc(SpdyProtocol.PUBKEY_PSEQ_OPEN));
                XLAcceleratorInteractor.this.stopKeepAlive();
                XLAcceleratorInteractor.this.stopTimeCount();
            }
        }
    }

    public XLAcceleratorInteractor() {
        this.mInited = false;
        this.mContext = null;
        this.mIsLoading = false;
        this.mIsReInit = false;
        this.mCurrentStatus = 0;
        this.mHadPortal = false;
        this.mPortalErrorCode = 65535;
        this.mGetAccelInfoCode = 65535;
        this.mUserListeners = new LinkedList();
        this.mAccelUser = new XLAccelUser();
        this.getAccelInfoRetry = 0;
        this.getTryAccelInfoRetry = 0;
        this.startAccelRetry = 0;
        this.mKeepAliveRetry = 0;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.isKuaiNiao = 0;
        this.mCurrentAccelStatus = 0;
        this.mKeepAliving = false;
        this.changeUserCount = 1;
        this.isReLogin = false;
        this.isPaySuccess = false;
        this.automaticGetAccelErrorMessage = BuildConfig.VERSION_NAME;
        this.alreadyAccelUser = null;
        this.mXLOnUserListener = new XLOnUserListener() {
            public boolean onUserLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
                XLAcceleratorInteractor.this.isReLogin = true;
                return false;
            }

            public boolean onUserThirdLogin(int i, XLUserInfo xLUserInfo, int i2, int i3, Object obj, String str, int i4) {
                return false;
            }

            public boolean onUserTokenLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserSessionidLogin(int i, String str, XLUserInfo xLUserInfo, Object obj, int i2) {
                return false;
            }

            public boolean onUserLogout(int i, XLUserInfo xLUserInfo, Object obj, int i2) {
                XLAcceleratorInteractor.this.checkUser(i, null);
                return false;
            }

            public boolean onUserInfoCatched(int i, List<USERINFOKEY> list, XLUserInfo xLUserInfo, Object obj, int i2) {
                if (XLAcceleratorInteractor.this.isReLogin) {
                    XLAcceleratorInteractor.this.isReLogin = false;
                    XLAcceleratorInteractor.this.checkUser(i, xLUserInfo);
                }
                return false;
            }

            public boolean onUserGetCityInfo(int i, JSONObject jSONObject, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserSetInfo(int i, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserActivated(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserPing(int i, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserVerifyCodeUpdated(int i, String str, int i2, byte[] bArr, Object obj, int i3) {
                return false;
            }

            public boolean onUserPreVerifyedCode(int i, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserVerifyedCode(int i, Object obj, String str, int i2) {
                return false;
            }

            public boolean onHighSpeedCatched(int i, XLUserInfo xLUserInfo, XLHspeedCapacity xLHspeedCapacity, Object obj, int i2) {
                return false;
            }

            public boolean onLixianCatched(int i, XLUserInfo xLUserInfo, XLLixianCapacity xLLixianCapacity, Object obj, int i2) {
                return false;
            }

            public boolean onUserSuspended(int i) {
                return false;
            }

            public boolean onUserResumed(int i) {
                return false;
            }

            public boolean onUserQRCodeLoginAuth(int i, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserGetQRCode(int i, String str, byte[] bArr, Object obj, String str2, int i2) {
                return false;
            }

            public boolean onUserQRCodeLogin(int i, XLUserInfo xLUserInfo, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserGetRecommendAvatars(int i, XLAvatarItem[] xLAvatarItemArr, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserSelectRecommendAvatar(int i, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserSetAvatar(int i, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserGetBindedOtherAccount(int i, XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr, Object obj, String str, int i2) {
                return false;
            }

            public boolean onUserBindedOtherAccount(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
                return false;
            }

            public boolean onUserGetOtherAccountInfo(int i, int i2, XLThirdUserInfo xLThirdUserInfo, Object obj, String str, int i3) {
                return false;
            }

            public boolean onUserUnBindeOtherAccount(int i, int i2, Object obj, String str, int i3) {
                return false;
            }

            public boolean onUserAqSendMessage(int i, String str, String str2, Object obj, int i2) {
                return false;
            }

            public boolean onUserAqBindMobile(int i, String str, String str2, Object obj, int i2) {
                return false;
            }
        };
        this.latch = new CountDownLatch(1);
        this.mNetworkChangeReceiver = new NetworkChangeReceiver();
        this.recoverQueryRunnable = new Runnable() {
            public void run() {
                XLAcceleratorInteractor.this.recoverQueryCount();
            }
        };
    }

    public boolean init(Context context, String str, String str2) {
        if (this.mInited || context == null || str == null || str2 == null) {
            return false;
        }
        this.mContext = context;
        this.xlParameterUtils = new XLParameterUtils(str2, str);
        this.xLAccelController = new XLAccelController(context, this, this.xlParameterUtils);
        this.mInited = true;
        restoreState();
        registerNetworkReceiver();
        XLUserUtil.getInstance().attachListener(this.mXLOnUserListener);
        return true;
    }

    public boolean uninit() {
        if (!this.mInited) {
            return false;
        }
        this.mInited = false;
        this.mHadPortal = false;
        unRegisterReceiver();
        if (this.xLAccelController != null) {
            this.xLAccelController.unInitTimer();
        }
        this.mCurrentStatus = 0;
        saveAccelState(this.mContext);
        this.mUserListeners.removeAll(this.mUserListeners);
        XLUserUtil.getInstance().detachListener(this.mXLOnUserListener);
        this.mHandler.removeCallbacks(this.recoverQueryRunnable);
        return true;
    }

    private void checkUser(int i, XLUserInfo xLUserInfo) {
        boolean z = true;
        if (i != 0 || xLUserInfo == null) {
            this.isKuaiNiao = 0;
            changeUser(xLUserInfo, this.isKuaiNiao, i, null);
            return;
        }
        int intValue = xLUserInfo.getIntValue(USERINFOKEY.other_VasType);
        int intValue2 = xLUserInfo.getIntValue(USERINFOKEY.VasType);
        if ((intValue == 2 || intValue == 3) && intValue2 != 4) {
            this.isKuaiNiao = 1;
            KnParams knParams = new KnParams();
            knParams.setOther_ExpireDate(xLUserInfo.getStringValue(USERINFOKEY.other_ExpireDate));
            knParams.setOther_IsVip(xLUserInfo.getIntValue(USERINFOKEY.other_IsVip));
            if (xLUserInfo.getIntValue(USERINFOKEY.other_IsYear) != 1) {
                z = false;
            }
            knParams.setOther_IsYear(z);
            changeUser(xLUserInfo, this.isKuaiNiao, i, knParams);
            return;
        }
        this.isKuaiNiao = 0;
        changeUser(xLUserInfo, this.isKuaiNiao, i, null);
    }

    public void reInit() {
        this.mCurrentStatus = 0;
        recoverQueryCount();
        reInitQueryStatus();
        if (this.mIsLoading) {
            this.mIsReInit = true;
        } else {
            queryStatus();
        }
    }

    private void userQueryPortal() {
        if (this.mIsLoading || !this.mInited) {
            return;
        }
        if (!this.xLAccelController.forbidQueryPortal()) {
            this.mStartInfo = null;
            this.mTryInfo = null;
            this.mBandInfo = null;
            this.mStopInfo = null;
            this.mIsLoading = true;
            if (this.mCurrentStatus != 1) {
                this.mCurrentStatus = 1;
                dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
            }
            this.xLAccelController.queryPortal();
        } else if (!this.mHadPortal) {
            this.mCurrentStatus = 2;
            dispatchCallBack(this.mCurrentStatus, this.mPortalErrorCode, ErrorCodeUtils.getErrorDesc(this.mPortalErrorCode));
        }
    }

    private void userGetAccelInfo() {
        if (!this.mIsLoading) {
            if (this.getAccelInfoRetry <= 3) {
                this.mIsLoading = true;
                this.xLAccelController.getBandInfo(this.mAccelUser);
            } else if (this.mBandInfo == null) {
                this.mCurrentStatus = 2;
                dispatchCallBack(this.mCurrentStatus, this.mGetAccelInfoCode, this.automaticGetAccelErrorMessage);
            }
        }
    }

    private void userGetTryAccelInfo() {
        if (!this.mIsLoading) {
            this.mIsLoading = true;
            if (this.mCurrentStatus != 17) {
                this.mCurrentStatus = 17;
                dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
            }
            this.xLAccelController.getTryAccelInfo(this.mAccelUser);
        }
    }

    private void userStartAccel() {
        if (!this.mIsLoading) {
            this.mIsLoading = true;
            if (this.mCurrentStatus != 8) {
                this.mCurrentStatus = 8;
                dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
            }
            this.xLAccelController.startAccel(this.mAccelUser);
        }
    }

    private void userStopAccel() {
        if (!this.mIsLoading) {
            this.mIsLoading = true;
            if (this.mCurrentStatus != 15) {
                this.mCurrentStatus = 15;
                dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
            }
            if (this.alreadyAccelUser != null) {
                this.xLAccelController.stopAccel(this.alreadyAccelUser);
            }
        }
    }

    public void saveAccelState(Context context) {
        XLAccelBackupInfo xLAccelBackupInfo = new XLAccelBackupInfo();
        if (this.mAccelUser != null) {
            xLAccelBackupInfo.setAccelerateStatus(this.mCurrentAccelStatus, this.mAccelUser.mUserType);
        } else {
            xLAccelBackupInfo.setAccelerateStatus(this.mCurrentAccelStatus, 0);
        }
        if (this.mCurrentAccelStatus == 1) {
            xLAccelBackupInfo.setBandwidthInfo(this.mStartInfo.getXlAccelBandInfo());
            xLAccelBackupInfo.setUserInfo(this.mAccelUser);
            if (this.mAccelUser.mUserType == 2) {
                xLAccelBackupInfo.setTryInfo(this.mTryInfo.getXlAccelTryInfo());
            }
        }
        xLAccelBackupInfo.saveState(context);
    }

    public void attachListener(XLOnAccelListener xLOnAccelListener) {
        this.mUserListeners.add(xLOnAccelListener);
    }

    public void detachListener(XLOnAccelListener xLOnAccelListener) {
        this.mUserListeners.remove(xLOnAccelListener);
    }

    public String getVersion() {
        return this.xlParameterUtils.getmVersion();
    }

    public void onQueryPortal(int i, int i2) {
        this.mIsLoading = false;
        if (this.mIsReInit) {
            this.mIsReInit = false;
            queryStatus();
            return;
        }
        this.latch.countDown();
        this.mPortalErrorCode = i2;
        if (i2 == 0) {
            this.xLAccelController.setPortalRetryCount(0);
            this.mHadPortal = true;
            userGetAccelInfo();
            return;
        }
        this.mCurrentStatus = 2;
        dispatchCallBack(this.mCurrentStatus, i2, ErrorCodeUtils.getErrorDesc(i2));
    }

    public void onGetBandInfo(int i, int i2, String str, AccelInfoResultBean accelInfoResultBean) {
        this.mIsLoading = false;
        if (this.mIsReInit) {
            this.mIsReInit = false;
            queryStatus();
            return;
        }
        this.mGetAccelInfoCode = i2;
        if (accelInfoResultBean != null) {
            this.automaticGetAccelErrorMessage = accelInfoResultBean.getRichmessage();
        } else {
            this.automaticGetAccelErrorMessage = ErrorCodeUtils.getErrorDesc(i2);
        }
        if (i2 == 711) {
            if (getAccelInfoRetry()) {
                return;
            }
        } else if (!(i2 == 0 || i2 == 520 || !getAccelInfoRetry())) {
            return;
        }
        this.mBandInfo = accelInfoResultBean;
        if (i2 == 0) {
            this.getAccelInfoRetry = 0;
            if (this.mAccelUser == null || this.mAccelUser.isTryUser()) {
                this.mCurrentStatus = 3;
                dispatchCallBack(this.mCurrentStatus, i2, str);
                return;
            }
            userStartAccel();
            return;
        }
        this.mBandInfo = null;
        this.getAccelInfoRetry++;
        if (this.getAccelInfoRetry > 3) {
            countTimeToRecoverQuery();
        }
        this.mCurrentStatus = 2;
        dispatchCallBack(this.mCurrentStatus, i2, str);
    }

    public void onGetTryAccelInfo(int i, int i2, String str, TryInfoResultBean tryInfoResultBean) {
        this.mIsLoading = false;
        if (this.mIsReInit) {
            this.mIsReInit = false;
            queryStatus();
            return;
        }
        if (i2 == 711) {
            if (getTryAccelInfoRetry()) {
                return;
            }
        } else if (!(i2 == 508 || i2 == 715 || i2 == 717 || i2 == 718 || i2 == 520 || i2 == 0 || !getTryAccelInfoRetry())) {
            return;
        }
        this.mTryInfo = tryInfoResultBean;
        this.getTryAccelInfoRetry = 0;
        if (i2 == 0) {
            if (this.mTryInfo.getXlAccelTryInfo().mNumOfTry > 0) {
                userStartAccel();
                return;
            }
            this.mCurrentStatus = 7;
            dispatchCallBack(this.mCurrentStatus, i2, str);
        } else if (this.mTryInfo == null || this.mTryInfo.getXlAccelTryInfo() == null || this.mTryInfo.getXlAccelTryInfo().mNumOfTry >= 0) {
            this.mCurrentStatus = 6;
            dispatchCallBack(this.mCurrentStatus, i2, str);
        } else {
            this.mCurrentStatus = 7;
            dispatchCallBack(this.mCurrentStatus, i2, str);
        }
    }

    public void onStartAccel(int i, int i2, String str, StartAccelResultBean startAccelResultBean) {
        this.mIsLoading = false;
        if (this.mIsReInit) {
            this.mIsReInit = false;
            queryStatus();
            return;
        }
        if (i2 == 711) {
            if (startAccelRetry()) {
                return;
            }
        } else if (!(i2 == 0 || i2 == 812 || i2 == 508 || i2 == 715 || i2 == 717 || i2 == 718 || i2 == 520 || i2 == 720 || i2 == 722 || i2 == 518 || !startAccelRetry())) {
            return;
        }
        this.startAccelRetry = 0;
        this.mStartInfo = startAccelResultBean;
        if (i2 == 0) {
            this.alreadyAccelUser = this.mAccelUser;
            if (this.mAccelUser == null || this.mAccelUser.isTryUser()) {
                this.mCurrentStatus = 4;
                setAccelStatus(1);
                this.mCurrentAccelStatus = 1;
                return;
            }
            this.mCurrentStatus = 9;
            dispatchCallBack(this.mCurrentStatus, i2, str);
            setAccelStatus(1);
        } else if (i2 == 812) {
            this.alreadyAccelUser = this.mAccelUser;
            this.mCurrentStatus = 16;
            this.xLAccelController.onceKeepAlive(this.mAccelUser);
            this.xLAccelController.startKeepAlive(this.mAccelUser);
            this.mKeepAliving = true;
            this.mCurrentAccelStatus = 1;
        } else {
            if (!this.mAccelUser.isTryUser()) {
                this.mCurrentStatus = 10;
            } else if (this.mTryInfo.getXlAccelTryInfo().mNumOfTry <= 0) {
                this.mCurrentStatus = 7;
            } else {
                this.mCurrentStatus = 6;
            }
            dispatchCallBack(this.mCurrentStatus, i2, str);
        }
    }

    public void onStopAccel(int i, int i2, String str, StopAccelResultBean stopAccelResultBean) {
        this.mIsLoading = false;
        if (this.mIsReInit) {
            this.mIsReInit = false;
            queryStatus();
            return;
        }
        this.mStopInfo = stopAccelResultBean;
        setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
        if (i2 == 0) {
            if (this.mAccelUser == null || this.mAccelUser.isTryUser()) {
                this.mCurrentStatus = 5;
            } else {
                this.mCurrentStatus = 14;
            }
            dispatchCallBack(this.mCurrentStatus, i2, str);
            return;
        }
        if (this.mAccelUser.isTryUser()) {
            this.mCurrentStatus = 4;
        } else {
            this.mCurrentStatus = 9;
        }
        dispatchCallBack(this.mCurrentStatus, i2, str);
    }

    public void onKeepAlive(int i, int i2, String str) {
        if (i2 == 513) {
            if (this.mCurrentStatus == 16) {
                dispatchCallBack(this.mCurrentStatus, i2, new StringBuilder("aaa").append(str).toString());
            }
            setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
            if (this.mAccelUser.isTryUser()) {
                userStopAccel();
            } else {
                userStartAccel();
            }
        } else if (i2 == 520) {
            if (XLNetworkUtils.isWIFIActive(this.mContext)) {
                setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
            } else {
                this.mNetworkChangeReceiver.setNeedRetry();
            }
            userStopAccel();
        } else if (i2 == 708) {
            setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
        } else if (i2 == 704) {
            if (this.mAccelUser.isTryUser()) {
                userStopAccel();
                setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
                return;
            }
            userStartAccel();
        } else if (i2 == 3) {
            this.mNetworkChangeReceiver.setNeedRetry();
        } else if (this.mKeepAliveRetry <= 0) {
            this.mKeepAliveRetry++;
        } else {
            this.mKeepAliveRetry = 0;
            setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
            userStartAccel();
        }
    }

    public void onTimeCounterTimerTick(int i, int i2, boolean z) {
        if (z) {
            setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
            if (this.mAccelUser != null && this.mAccelUser.mUserType == 2) {
                userStopAccel();
            }
            onStopAccel(0, SimpleLog.LOG_LEVEL_OFF, ErrorCodeUtils.getErrorDesc(SimpleLog.LOG_LEVEL_OFF), null);
        }
        if (this.mTryInfo == null) {
            this.mTryInfo = new TryInfoResultBean();
            this.mTryInfo.setXlAccelTryInfo(new XLAccelTryInfo());
        }
        this.mTryInfo.getXlAccelTryInfo().mRemainTime = i2;
        if (z) {
            this.mCurrentStatus = 5;
        } else {
            this.mCurrentStatus = 4;
        }
        dispatchCallBack(this.mCurrentStatus, i2, new StringBuilder("\u5012\u8ba1\u65f6").append(i2).append("\u79d2").toString());
    }

    public void onAlreadyUpgrade(String str) {
        this.changeClientType = ErrorCodeUtils.getChangeClientType(str) + "\u5df2\u7ecf\u52a0\u901f";
        dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_ALREADY_UPGRADED, this.changeClientType);
    }

    public void changeUser(XLUserInfo xLUserInfo, int i, int i2, KnParams knParams) {
        this.isKuaiNiao = i;
        this.mKnParams = knParams;
        if (xLUserInfo != null) {
            this.mAccelUser = new XLAccelUser(xLUserInfo, i, knParams);
        } else {
            this.mAccelUser = new XLAccelUser();
        }
        if (this.isPaySuccess) {
            this.isPaySuccess = false;
            dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_PAY_SUCCESS, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_PAY_SUCCESS));
        }
        if (this.mCurrentStatus == 9) {
            if (this.mIsLoading) {
                this.mIsReInit = true;
            }
            userStopAccel();
            setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
        }
        if (this.mCurrentStatus == 4 && !this.mAccelUser.isTryUser()) {
            if (this.mIsLoading) {
                this.mIsReInit = true;
            }
            setAccelStatus(SimpleLog.LOG_LEVEL_DEBUG);
        }
        if (this.mCurrentStatus == 2) {
            this.mCurrentStatus = 0;
        } else {
            this.mCurrentStatus = 3;
        }
        if (!this.mIsLoading) {
            queryStatus();
        } else if (this.changeUserCount > 1 && this.mIsLoading) {
            this.mIsReInit = true;
        }
        this.changeUserCount++;
    }

    private void dispatchCallBack(int i, int i2, String str) {
        System.out.println(new StringBuilder("colin statusCode = ").append(i).append(";  errorCode = ").append(i2).append(";   errorDesc=").append(str).toString());
        for (int i3 = 0; i3 < this.mUserListeners.size(); i3++) {
            ((XLOnAccelListener) this.mUserListeners.get(i3)).callBack(i, i2, str);
        }
    }

    private boolean getAccelInfoRetry() {
        if (this.getAccelInfoRetry > 3) {
            return false;
        }
        this.getAccelInfoRetry++;
        if (this.getAccelInfoRetry > 3) {
            countTimeToRecoverQuery();
        }
        userGetAccelInfo();
        return true;
    }

    private boolean getTryAccelInfoRetry() {
        if (this.getTryAccelInfoRetry > 0) {
            return false;
        }
        this.getTryAccelInfoRetry++;
        userGetTryAccelInfo();
        return true;
    }

    private boolean startAccelRetry() {
        if (this.startAccelRetry > 0) {
            return false;
        }
        this.startAccelRetry++;
        userStartAccel();
        return true;
    }

    private void setAccelStatus(int i) {
        if (i != this.mCurrentAccelStatus) {
            this.mCurrentAccelStatus = i;
            switch (this.mCurrentAccelStatus) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    if (this.mHadPortal && this.mInited && this.mAccelUser != null) {
                        restartKeepAlive();
                        if (this.mAccelUser.mUserType == 2) {
                            if (this.mTryInfo == null) {
                                this.mTryInfo = new TryInfoResultBean();
                                this.mTryInfo.setXlAccelTryInfo(new XLAccelTryInfo());
                                this.mTryInfo.getXlAccelTryInfo().mNumOfTry = 0;
                            }
                            startTimeCount();
                        }
                    }
                case SimpleLog.LOG_LEVEL_DEBUG:
                    stopKeepAlive();
                    stopTimeCount();
                default:
                    break;
            }
        }
    }

    private void stopKeepAlive() {
        if (this.mKeepAliving) {
            this.mKeepAliving = false;
            this.xLAccelController.stopKeepAlive();
        }
    }

    private void restartKeepAlive() {
        if (this.mKeepAliving) {
            this.xLAccelController.stopKeepAlive();
        }
        this.xLAccelController.startKeepAlive(this.mAccelUser);
        this.mKeepAliving = true;
    }

    private void startTimeCount() {
        int i;
        XLAccelController xLAccelController = this.xLAccelController;
        if (this.mTryInfo.getXlAccelTryInfo().mRemainTime > 0) {
            i = this.mTryInfo.getXlAccelTryInfo().mRemainTime;
        } else {
            i = this.mTryInfo.getXlAccelTryInfo().mTryDuration * 60;
        }
        xLAccelController.startTimeCount(i);
    }

    private void stopTimeCount() {
        this.xLAccelController.stopTimeCount();
    }

    private void restoreState() {
        new Thread() {
            public void run() {
                if (XLAccelBackupInfo.getSaveStateSuccessFlag(XLAcceleratorInteractor.this.mContext)) {
                    XLAccelBackupInfo.setSaveStateSuccessFlag(XLAcceleratorInteractor.this.mContext, false);
                    while (!XLAcceleratorInteractor.this.mHadPortal) {
                        try {
                            XLAcceleratorInteractor.this.latch.await(5, TimeUnit.MINUTES);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!XLAcceleratorInteractor.this.mIsLoading) {
                            return;
                        }
                    }
                    if (XLAcceleratorInteractor.this.mHadPortal) {
                        XLAccelBackupInfo restoreState = XLAccelBackupInfo.restoreState(XLAcceleratorInteractor.this.mContext);
                        if (restoreState != null && restoreState.getAccelerateStatus() != AccelStatus.NOT_ACCELERATE) {
                            long currentTimeMillis = (System.currentTimeMillis() - restoreState.mBackupSystemTime) / 1000;
                            XLAccelUser restoreUserInfo = restoreState.restoreUserInfo(new XLAccelUser());
                            if (XLAcceleratorInteractor.this.mAccelUser == null || XLAcceleratorInteractor.this.mAccelUser.mUserID.equals(restoreUserInfo.mUserID)) {
                                XLAcceleratorInteractor.this.mAccelUser = restoreUserInfo;
                                if (restoreState.getAccelerateStatus() == AccelStatus.IS_ACCELERATE) {
                                    XLAcceleratorInteractor.this.mHandler.post(new Runnable() {
                                        public void run() {
                                            XLAcceleratorInteractor.this.setAccelStatus(1);
                                        }
                                    });
                                } else if (restoreState.getAccelerateStatus() == AccelStatus.IS_TRYING) {
                                    if (XLAcceleratorInteractor.this.mTryInfo == null) {
                                        XLAcceleratorInteractor.this.mTryInfo = new TryInfoResultBean();
                                    }
                                    XLAcceleratorInteractor.this.mTryInfo.setXlAccelTryInfo(restoreState.restoreTryInfo(new XLAccelTryInfo()));
                                    long j = restoreState.mTryRemainTime - currentTimeMillis;
                                    if (j <= 0) {
                                        XLAcceleratorInteractor.this.onTimeCounterTimerTick(XLAcceleratorInteractor.this.mTryInfo.getXlAccelTryInfo().mTryDuration * 60, 0, true);
                                        return;
                                    }
                                    XLAcceleratorInteractor.this.mTryInfo.getXlAccelTryInfo().mRemainTime = (int) j;
                                    XLAcceleratorInteractor.this.mHandler.post(new Runnable() {
                                        public void run() {
                                            XLAcceleratorInteractor.this.setAccelStatus(1);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }.start();
    }

    private void registerNetworkReceiver() {
        if (this.mContext != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.mContext.registerReceiver(this.mNetworkChangeReceiver, intentFilter);
        }
    }

    private void unRegisterReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mNetworkChangeReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queryStatus() {
        int i = SniffingError.USER_ERROR_FLAGS;
        int i2 = ErrorCodeUtils.XLA_STATUS_LOADING;
        if (this.mIsLoading) {
            dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
            return;
        }
        String errorDesc = ErrorCodeUtils.getErrorDesc(SniffingError.USER_ERROR_FLAGS);
        switch (this.mCurrentStatus) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
                errorDesc = ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING);
                if (this.mBandInfo != null) {
                    i2 = this.mBandInfo.getError();
                    errorDesc = this.mBandInfo.getRichmessage();
                }
                if (this.mCurrentStatus != 0) {
                    dispatchCallBack(this.mCurrentStatus, i2, errorDesc);
                }
                restartQueryInfo();
                return;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (this.mBandInfo != null) {
                    i2 = this.mBandInfo.getError();
                    errorDesc = this.mBandInfo.getRichmessage();
                }
                i2 = 65535;
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
            case SimpleLog.LOG_LEVEL_ERROR:
                i2 = 65535;
                break;
            case SimpleLog.LOG_LEVEL_FATAL:
                if (this.mStartInfo != null) {
                    dispatchCallBack(this.mCurrentStatus, this.mStartInfo.getError(), this.mStartInfo.getRichmessage());
                    return;
                } else if (this.mTryInfo != null) {
                    dispatchCallBack(this.mCurrentStatus, this.mTryInfo.getError(), this.mTryInfo.getRichmessage());
                    return;
                } else {
                    dispatchCallBack(this.mCurrentStatus, SniffingError.USER_ERROR_FLAGS, errorDesc);
                    return;
                }
            case SimpleLog.LOG_LEVEL_OFF:
                if (this.mStartInfo != null) {
                    i = this.mStartInfo.getError();
                    errorDesc = this.mStartInfo.getRichmessage();
                }
                dispatchCallBack(this.mCurrentStatus, i, errorDesc);
                return;
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
                return;
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                dispatchCallBack(this.mCurrentStatus, 0, ErrorCodeUtils.getErrorDesc(0));
                return;
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                if (this.mStartInfo != null) {
                    i = this.mStartInfo.getError();
                    errorDesc = this.mStartInfo.getRichmessage();
                }
                dispatchCallBack(this.mCurrentStatus, i, errorDesc);
                return;
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                dispatchCallBack(this.mCurrentStatus, SpdyProtocol.PUBKEY_PSEQ_OPEN, ErrorCodeUtils.getErrorDesc(SpdyProtocol.PUBKEY_PSEQ_OPEN));
                return;
            case R.styleable.Toolbar_titleMargins:
                dispatchCallBack(this.mCurrentStatus, SpdyProtocol.PUBKEY_SEQ_OPEN, ErrorCodeUtils.getErrorDesc(SpdyProtocol.PUBKEY_SEQ_OPEN));
                return;
            case R.styleable.Toolbar_titleMarginStart:
                dispatchCallBack(this.mCurrentStatus, SniffingError.USER_ERROR_FLAGS, ErrorCodeUtils.getErrorDesc(SniffingError.USER_ERROR_FLAGS));
                return;
            case R.styleable.Toolbar_titleMarginEnd:
                if (this.mStopInfo != null) {
                    i = this.mStartInfo.getError();
                    errorDesc = this.mStopInfo.getRichmessage();
                }
                dispatchCallBack(this.mCurrentStatus, i, errorDesc);
                return;
            case R.styleable.Toolbar_titleMarginTop:
                dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
                return;
            case SpdyProtocol.CUSTOM:
                if (this.mStartInfo != null) {
                    i = this.mStartInfo.getError();
                }
                dispatchCallBack(this.mCurrentStatus, i, this.changeClientType);
                return;
            case R.styleable.Toolbar_maxButtonHeight:
                dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
                return;
            default:
                return;
        }
        if (this.mStartInfo != null) {
            i2 = this.mStartInfo.getError();
            errorDesc = this.mStartInfo.getRichmessage();
        }
        dispatchCallBack(this.mCurrentStatus, i2, errorDesc);
        if (!this.mAccelUser.isTryUser() && this.mBandInfo != null) {
            userStartAccel();
        }
    }

    public void startAccel() {
        if (this.mIsLoading) {
            dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
        } else if (this.mCurrentStatus != 3 && this.mCurrentStatus != 10 && this.mCurrentStatus != 14 && this.mCurrentStatus != 5 && this.mCurrentStatus != 6) {
            queryStatus();
        } else if (this.mAccelUser.isTryUser()) {
            userGetTryAccelInfo();
        } else {
            userStartAccel();
        }
    }

    public void stopAccel() {
        if (this.mIsLoading) {
            dispatchCallBack(this.mCurrentStatus, ErrorCodeUtils.XLA_STATUS_LOADING, ErrorCodeUtils.getErrorDesc(ErrorCodeUtils.XLA_STATUS_LOADING));
        } else if (this.mCurrentStatus == 9) {
            userStopAccel();
        }
    }

    public void recoverQuery() {
        recoverQueryCount();
        this.getAccelInfoRetry = 2;
        userQueryPortal();
    }

    public String getBandInfo() {
        return this.mBandInfo == null ? BuildConfig.VERSION_NAME : XLAccelModel.bandInfoToJson(this.mBandInfo.getSeq(), this.mBandInfo.getError(), this.mBandInfo.getRichmessage(), this.mBandInfo.getXlAccelBandInfo());
    }

    public XLAccelBandInfo getBandInfoObject() {
        return this.mBandInfo == null ? null : this.mBandInfo.getXlAccelBandInfo();
    }

    public String getTryInfo() {
        if (this.mTryInfo == null) {
            this.mTryInfo = new TryInfoResultBean();
            XLAccelTryInfo xLAccelTryInfo = new XLAccelTryInfo();
            xLAccelTryInfo.mNumOfTry = 1;
            xLAccelTryInfo.mRemainTime = 0;
            xLAccelTryInfo.mTryDuration = 0;
            this.mTryInfo.setXlAccelTryInfo(xLAccelTryInfo);
        }
        return XLAccelModel.tryInfoToJson(this.mTryInfo.getSeq(), this.mTryInfo.getError(), this.mTryInfo.getRichmessage(), this.mTryInfo.getXlAccelTryInfo());
    }

    public String getUserInfo() {
        return XLAccelModel.UserInfoToJsonJ(this.mAccelUser);
    }

    public int getRemainTime() {
        return this.xLAccelController.getRemainTime();
    }

    public KnParams getKnParams() {
        return this.mKnParams;
    }

    public int isKuaiNiao() {
        return this.isKuaiNiao;
    }

    public void updateUserInfo() {
        this.isReLogin = true;
        this.isPaySuccess = true;
        XLUserUtil.getInstance().userGetInfo(null, this.mXLOnUserListener, R.styleable.Toolbar_titleMarginEnd, "update");
    }

    private void restartQueryInfo() {
        if (!this.mHadPortal) {
            userQueryPortal();
        } else if (this.mBandInfo == null) {
            userGetAccelInfo();
        }
    }

    private void reInitQueryStatus() {
        this.mHadPortal = false;
        this.mBandInfo = null;
    }

    public void countTimeToRecoverQuery() {
        this.mHandler.removeCallbacks(this.recoverQueryRunnable);
        this.mHandler.postDelayed(this.recoverQueryRunnable, XLAccelHttpReqInfo.getRecoverQueryCountTime());
    }

    private void recoverQueryCount() {
        this.xLAccelController.setPortalRetryCount(0);
        this.xLAccelController.setPortalSwitchAddress();
        this.getAccelInfoRetry = 0;
    }
}
