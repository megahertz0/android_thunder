package com.xunlei.common.accelerator.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.xunlei.common.accelerator.bean.KeepResultBean;
import com.xunlei.common.accelerator.bean.PortalResultBean;
import com.xunlei.common.accelerator.bean.XLAccelUser;
import com.xunlei.common.accelerator.http.XLAccelHttpReqInfo;
import com.xunlei.common.accelerator.interactor.XLAcceleratorInteractor;
import com.xunlei.common.accelerator.model.XLAccelModel;
import com.xunlei.common.accelerator.utils.CountDownTimer;
import com.xunlei.common.accelerator.utils.ErrorCodeUtils;
import com.xunlei.common.accelerator.utils.XLParameterUtils;
import com.xunlei.common.base.XLAlarmBaseTimer;
import com.xunlei.common.base.XLAlarmBaseTimer.TimerListener;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class XLAccelController extends BaseController<XLAcceleratorInteractor, XLAccelModel> implements TimerListener {
    private static final String TAG = "XLAccelController";
    private String XLA_BASE_PREFIX;
    private int errorCode;
    private String errorMessage;
    private boolean isForbidQueryPortal;
    private XLAccelUser keepAliveUser;
    private CountTimer mCountTimer;
    private String mDialAccount;
    private Handler mHandler;
    private final int mKeepAliveTimeID;
    private int mPortalRetryCount;
    private int mRemainTimerCounter;
    private boolean mTimeCounting;
    private int mTotalTimerCounter;
    private String portalURL;
    private int retryPortalCount;
    private int seq;
    private XLParameterUtils xlParameterUtils;

    class AnonymousClass_7 implements BaseHttpClientListener {
        final /* synthetic */ boolean val$isUpgrade;

        AnonymousClass_7(boolean z) {
            this.val$isUpgrade = z;
        }

        public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            if (i == 200) {
                try {
                    KeepResultBean parseKeepAliveData = ((XLAccelModel) XLAccelController.this.model).parseKeepAliveData(new String(bArr, CharsetConvert.UTF_8));
                    if (parseKeepAliveData == null) {
                        XLAccelController.this.errorCode = SimpleLog.LOG_LEVEL_ERROR;
                        XLAccelController.this.errorMessage = ErrorCodeUtils.getErrorDesc(XLAccelController.this.errorCode);
                        XLAccelController.this.seq = 0;
                    } else {
                        XLAccelController.this.errorCode = parseKeepAliveData.getError();
                        XLAccelController.this.errorMessage = ErrorCodeUtils.parseLink(XLAccelController.this.errorCode, parseKeepAliveData.getRichmessage());
                        parseKeepAliveData.setRichmessage(XLAccelController.this.errorMessage);
                        XLAccelController.this.seq = parseKeepAliveData.getSeq();
                        if (XLAccelController.this.errorCode == 0) {
                            if (this.val$isUpgrade) {
                                ((XLAcceleratorInteractor) XLAccelController.this.interator).onAlreadyUpgrade(parseKeepAliveData.getClient_type());
                                return;
                            }
                            return;
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    XLAccelController.this.errorCode = MqttConnectOptions.MQTT_VERSION_3_1_1;
                    XLAccelController.this.errorMessage = ErrorCodeUtils.getErrorDesc(XLAccelController.this.errorCode);
                    XLAccelController.this.seq = 0;
                }
            } else {
                XLAccelController.this.errorCode = SimpleLog.LOG_LEVEL_DEBUG;
                XLAccelController.this.errorMessage = ErrorCodeUtils.getErrorDesc(XLAccelController.this.errorCode);
                XLAccelController.this.seq = 0;
            }
            ((XLAcceleratorInteractor) XLAccelController.this.interator).onKeepAlive(XLAccelController.this.seq, XLAccelController.this.errorCode, XLAccelController.this.errorMessage);
        }

        public void onFailure(Throwable th, byte[] bArr) {
            XLAccelController.this.errorCode = MqttConnectOptions.MQTT_VERSION_3_1;
            XLAccelController.this.errorMessage = ErrorCodeUtils.getErrorDesc(XLAccelController.this.errorCode);
            XLAccelController.this.seq = 0;
            ((XLAcceleratorInteractor) XLAccelController.this.interator).onKeepAlive(XLAccelController.this.seq, XLAccelController.this.errorCode, XLAccelController.this.errorMessage);
        }
    }

    class CountTimer extends CountDownTimer {
        public CountTimer(long j, long j2) {
            super(j, j2);
        }

        public void onTick(long j) {
            XLAccelController.this.mRemainTimerCounter = (int) (j / 1000);
            ((XLAcceleratorInteractor) XLAccelController.this.interator).onTimeCounterTimerTick(XLAccelController.this.mTotalTimerCounter, XLAccelController.this.mRemainTimerCounter, false);
        }

        public void onFinish() {
            ((XLAcceleratorInteractor) XLAccelController.this.interator).onTimeCounterTimerTick(XLAccelController.this.mTotalTimerCounter, XLAccelController.this.mRemainTimerCounter, true);
        }
    }

    static /* synthetic */ int access$208(XLAccelController xLAccelController) {
        int i = xLAccelController.mPortalRetryCount;
        xLAccelController.mPortalRetryCount = i + 1;
        return i;
    }

    public XLAccelController(Context context, XLAcceleratorInteractor xLAcceleratorInteractor, XLParameterUtils xLParameterUtils) {
        super(context, xLAcceleratorInteractor);
        this.mPortalRetryCount = 0;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.XLA_BASE_PREFIX = "http://";
        this.portalURL = XLAccelHttpReqInfo.getPortalAdress();
        this.retryPortalCount = 2;
        this.isForbidQueryPortal = false;
        this.mKeepAliveTimeID = 4112;
        this.mTotalTimerCounter = 0;
        this.mRemainTimerCounter = 0;
        this.mTimeCounting = false;
        this.mCountTimer = null;
        this.xlParameterUtils = xLParameterUtils;
        this.model = new XLAccelModel();
        XLAlarmBaseTimer.init(context);
    }

    public void setPortalRetryCount(int i) {
        this.mPortalRetryCount = i;
    }

    public boolean forbidQueryPortal() {
        return this.isForbidQueryPortal;
    }

    public void setPortalSwitchAddress() {
        this.portalURL = XLAccelHttpReqInfo.getPortalAdress();
        this.retryPortalCount = 2;
        this.isForbidQueryPortal = false;
    }

    public void queryPortal() {
        if (this.mPortalRetryCount > this.retryPortalCount) {
            this.seq = 0;
            ((XLAcceleratorInteractor) this.interator).onQueryPortal(this.seq, this.errorCode);
            this.isForbidQueryPortal = true;
            ((XLAcceleratorInteractor) this.interator).countTimeToRecoverQuery();
            return;
        }
        excuteRequest(this.portalURL, new BaseHttpClientListener() {
            public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                if (i == 200) {
                    try {
                        PortalResultBean parsePortalData = ((XLAccelModel) XLAccelController.this.model).parsePortalData(new String(bArr, CharsetConvert.UTF_8));
                        if (parsePortalData == null) {
                            XLAccelController.this.errorCode = SimpleLog.LOG_LEVEL_ERROR;
                            XLAccelController.this.seq = 0;
                            XLAccelController.access$208(XLAccelController.this);
                            XLAccelController.this.retryPortalCount = SimpleLog.LOG_LEVEL_DEBUG;
                        } else {
                            XLAccelController.this.errorCode = parsePortalData.getError();
                            XLAccelController.this.seq = parsePortalData.getSeq();
                            if (XLAccelController.this.errorCode == 0) {
                                XLAccelController.this.XLA_BASE_PREFIX = parsePortalData.getAddress();
                                XLAccelController.this.portalURL = XLAccelHttpReqInfo.getPortalAdress();
                            } else {
                                XLAccelController.access$208(XLAccelController.this);
                                XLAccelController.this.retryPortalCount = SimpleLog.LOG_LEVEL_DEBUG;
                                XLAccelController.this.XLA_BASE_PREFIX = "http://";
                            }
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        XLAccelController.this.errorCode = MqttConnectOptions.MQTT_VERSION_3_1_1;
                        XLAccelController.this.seq = 0;
                        XLAccelController.access$208(XLAccelController.this);
                        XLAccelController.this.retryPortalCount = SimpleLog.LOG_LEVEL_DEBUG;
                    }
                    ((XLAcceleratorInteractor) XLAccelController.this.interator).onQueryPortal(XLAccelController.this.seq, XLAccelController.this.errorCode);
                    return;
                }
                XLAccelController.this.errorCode = SimpleLog.LOG_LEVEL_DEBUG;
                XLAccelController.this.retryPortalCount = MqttConnectOptions.MQTT_VERSION_3_1_1;
                XLAccelController.this.switchPortalAddress();
                XLAccelController.this.recoverPortal();
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLAccelController.this.errorCode = MqttConnectOptions.MQTT_VERSION_3_1;
                XLAccelController.this.retryPortalCount = MqttConnectOptions.MQTT_VERSION_3_1_1;
                XLAccelController.this.switchPortalAddress();
                XLAccelController.this.recoverPortal();
            }
        });
    }

    private void switchPortalAddress() {
        if (this.mPortalRetryCount >= 2) {
            this.portalURL = XLAccelHttpReqInfo.getPortalAdressSecond();
        }
    }

    private void recoverPortal() {
        this.mPortalRetryCount++;
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                XLAccelController.this.queryPortal();
            }
        }, 2000);
    }

    public void getBandInfo(XLAccelUser xLAccelUser) {
        System.out.println("colin http  getBandInfo");
        if (xLAccelUser == null) {
            xLAccelUser = new XLAccelUser();
        }
        excuteRequest(this.xlParameterUtils.parameterToHttpAddress(this.XLA_BASE_PREFIX, XLAccelHttpReqInfo.GET_ACCELINFO_INTERFACE, xLAccelUser), new BaseHttpClientListener() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSuccess(int r7, org.apache.http.Header[] r8, byte[] r9) {
                throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.accelerator.controller.XLAccelController.AnonymousClass_3.onSuccess(int, org.apache.http.Header[], byte[]):void");
                /*
                this = this;
                r4 = 0;
                r1 = 0;
                r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                if (r7 != r0) goto L_0x00b3;
            L_0x0006:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x0090 }
                r0 = r0.model;	 Catch:{ UnsupportedEncodingException -> 0x0090 }
                r0 = (com.xunlei.common.accelerator.model.XLAccelModel) r0;	 Catch:{ UnsupportedEncodingException -> 0x0090 }
                r2 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0090 }
                r3 = "UTF-8";
                r2.<init>(r9, r3);	 Catch:{ UnsupportedEncodingException -> 0x0090 }
                r0 = r0.parseBandInfoData(r2);	 Catch:{ UnsupportedEncodingException -> 0x0090 }
                if (r0 != 0) goto L_0x0052;
            L_0x001a:
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = 5;
                r1.errorCode = r2;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = r2.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1.errorMessage = r2;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = 0;
                r1.seq = r2;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = r0;
            L_0x0036:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r0 = r0.interator;
                r0 = (com.xunlei.common.accelerator.interactor.XLAcceleratorInteractor) r0;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.seq;
                r3 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r3 = r3.errorCode;
                r4 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r4 = r4.errorMessage;
                r0.onGetBandInfo(r2, r3, r4, r1);
                return;
            L_0x0052:
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = r0.getError();	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1.errorCode = r2;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = r2.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r3 = r0.getRichmessage();	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.parseLink(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1.errorMessage = r2;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = r1.errorMessage;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r0.setRichmessage(r1);	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = r0.getSeq();	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1.seq = r2;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r2 = r0.getDialAccount();	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1.mDialAccount = r2;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00cf }
                r1 = r0;
                goto L_0x0036;
            L_0x0090:
                r0 = move-exception;
                r5 = r0;
                r0 = r1;
                r1 = r5;
            L_0x0094:
                r1.printStackTrace();
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = 4;
                r1.errorCode = r2;
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.errorCode;
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);
                r1.errorMessage = r2;
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r1.seq = r4;
                r1 = r0;
                goto L_0x0036;
            L_0x00b3:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = 2;
                r0.errorCode = r2;
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.errorCode;
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);
                r0.errorMessage = r2;
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r0.seq = r4;
                goto L_0x0036;
            L_0x00cf:
                r1 = move-exception;
                goto L_0x0094;
                */
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLAccelController.this.errorCode = MqttConnectOptions.MQTT_VERSION_3_1;
                XLAccelController.this.errorMessage = ErrorCodeUtils.getErrorDesc(XLAccelController.this.errorCode);
                XLAccelController.this.seq = 0;
                ((XLAcceleratorInteractor) XLAccelController.this.interator).onGetBandInfo(XLAccelController.this.seq, XLAccelController.this.errorCode, XLAccelController.this.errorMessage, null);
            }
        });
    }

    public void getTryAccelInfo(XLAccelUser xLAccelUser) {
        System.out.println("colin http  getTryAccelInfo");
        if (xLAccelUser == null) {
            xLAccelUser = new XLAccelUser();
        }
        excuteRequest(this.xlParameterUtils.getTryAccelInfoAddress(this.XLA_BASE_PREFIX, XLAccelHttpReqInfo.GET_TRY_ACCELINFO_INTERFACE, xLAccelUser), new BaseHttpClientListener() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSuccess(int r7, org.apache.http.Header[] r8, byte[] r9) {
                throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.accelerator.controller.XLAccelController.AnonymousClass_4.onSuccess(int, org.apache.http.Header[], byte[]):void");
                /*
                this = this;
                r4 = 0;
                r1 = 0;
                r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                if (r7 != r0) goto L_0x00a5;
            L_0x0006:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = r0.model;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = (com.xunlei.common.accelerator.model.XLAccelModel) r0;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r2 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r3 = "UTF-8";
                r2.<init>(r9, r3);	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = r0.parseTryAccelInfoData(r2);	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                if (r0 != 0) goto L_0x0052;
            L_0x001a:
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = 5;
                r1.errorCode = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r2.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorMessage = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = 0;
                r1.seq = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r0;
            L_0x0036:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r0 = r0.interator;
                r0 = (com.xunlei.common.accelerator.interactor.XLAcceleratorInteractor) r0;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.seq;
                r3 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r3 = r3.errorCode;
                r4 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r4 = r4.errorMessage;
                r0.onGetTryAccelInfo(r2, r3, r4, r1);
                return;
            L_0x0052:
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r0.getError();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorCode = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r2.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r3 = r0.getRichmessage();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.parseLink(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorMessage = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r1.errorMessage;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r0.setRichmessage(r1);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r0.getSeq();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.seq = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r0;
                goto L_0x0036;
            L_0x0082:
                r0 = move-exception;
                r5 = r0;
                r0 = r1;
                r1 = r5;
            L_0x0086:
                r1.printStackTrace();
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = 4;
                r1.errorCode = r2;
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.errorCode;
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);
                r1.errorMessage = r2;
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r1.seq = r4;
                r1 = r0;
                goto L_0x0036;
            L_0x00a5:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = 2;
                r0.errorCode = r2;
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.errorCode;
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);
                r0.errorMessage = r2;
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r0.seq = r4;
                goto L_0x0036;
            L_0x00c1:
                r1 = move-exception;
                goto L_0x0086;
                */
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLAccelController.this.errorCode = MqttConnectOptions.MQTT_VERSION_3_1;
                XLAccelController.this.errorMessage = ErrorCodeUtils.getErrorDesc(XLAccelController.this.errorCode);
                XLAccelController.this.seq = 0;
                ((XLAcceleratorInteractor) XLAccelController.this.interator).onGetTryAccelInfo(XLAccelController.this.seq, XLAccelController.this.errorCode, XLAccelController.this.errorMessage, null);
            }
        });
    }

    public void startAccel(XLAccelUser xLAccelUser) {
        System.out.println("colin http  startAccel");
        if (xLAccelUser == null) {
            xLAccelUser = new XLAccelUser();
        }
        excuteRequest(this.xlParameterUtils.getStartOrStopOrAliveAddress(this.XLA_BASE_PREFIX, XLAccelHttpReqInfo.START_ACCEL, xLAccelUser, this.mDialAccount), new BaseHttpClientListener() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSuccess(int r7, org.apache.http.Header[] r8, byte[] r9) {
                throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.accelerator.controller.XLAccelController.AnonymousClass_5.onSuccess(int, org.apache.http.Header[], byte[]):void");
                /*
                this = this;
                r4 = 0;
                r1 = 0;
                r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                if (r7 != r0) goto L_0x00a5;
            L_0x0006:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = r0.model;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = (com.xunlei.common.accelerator.model.XLAccelModel) r0;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r2 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r3 = "UTF-8";
                r2.<init>(r9, r3);	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = r0.parseStartAccelData(r2);	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                if (r0 != 0) goto L_0x0052;
            L_0x001a:
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = 5;
                r1.errorCode = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r2.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorMessage = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = 0;
                r1.seq = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r0;
            L_0x0036:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r0 = r0.interator;
                r0 = (com.xunlei.common.accelerator.interactor.XLAcceleratorInteractor) r0;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.seq;
                r3 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r3 = r3.errorCode;
                r4 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r4 = r4.errorMessage;
                r0.onStartAccel(r2, r3, r4, r1);
                return;
            L_0x0052:
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r0.getError();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorCode = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r2.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r3 = r0.getRichmessage();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.parseLink(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorMessage = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r1.errorMessage;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r0.setRichmessage(r1);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r0.getSeq();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.seq = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r0;
                goto L_0x0036;
            L_0x0082:
                r0 = move-exception;
                r5 = r0;
                r0 = r1;
                r1 = r5;
            L_0x0086:
                r1.printStackTrace();
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = 4;
                r1.errorCode = r2;
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.errorCode;
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);
                r1.errorMessage = r2;
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r1.seq = r4;
                r1 = r0;
                goto L_0x0036;
            L_0x00a5:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = 2;
                r0.errorCode = r2;
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.errorCode;
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);
                r0.errorMessage = r2;
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r0.seq = r4;
                goto L_0x0036;
            L_0x00c1:
                r1 = move-exception;
                goto L_0x0086;
                */
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLAccelController.this.errorCode = MqttConnectOptions.MQTT_VERSION_3_1;
                XLAccelController.this.errorMessage = ErrorCodeUtils.getErrorDesc(XLAccelController.this.errorCode);
                XLAccelController.this.seq = 0;
                ((XLAcceleratorInteractor) XLAccelController.this.interator).onStartAccel(XLAccelController.this.seq, XLAccelController.this.errorCode, XLAccelController.this.errorMessage, null);
            }
        });
    }

    public void stopAccel(XLAccelUser xLAccelUser) {
        System.out.println("colin http  stopAccel");
        if (xLAccelUser == null) {
            xLAccelUser = new XLAccelUser();
        }
        excuteRequest(this.xlParameterUtils.getStartOrStopOrAliveAddress(this.XLA_BASE_PREFIX, XLAccelHttpReqInfo.STOP_ACCEL, xLAccelUser, this.mDialAccount), new BaseHttpClientListener() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSuccess(int r7, org.apache.http.Header[] r8, byte[] r9) {
                throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.accelerator.controller.XLAccelController.AnonymousClass_6.onSuccess(int, org.apache.http.Header[], byte[]):void");
                /*
                this = this;
                r4 = 0;
                r1 = 0;
                r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                if (r7 != r0) goto L_0x00a5;
            L_0x0006:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = r0.model;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = (com.xunlei.common.accelerator.model.XLAccelModel) r0;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r2 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r3 = "UTF-8";
                r2.<init>(r9, r3);	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                r0 = r0.parseStopAccelData(r2);	 Catch:{ UnsupportedEncodingException -> 0x0082 }
                if (r0 != 0) goto L_0x0052;
            L_0x001a:
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = 5;
                r1.errorCode = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r2.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorMessage = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = 0;
                r1.seq = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r0;
            L_0x0036:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r0 = r0.interator;
                r0 = (com.xunlei.common.accelerator.interactor.XLAcceleratorInteractor) r0;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.seq;
                r3 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r3 = r3.errorCode;
                r4 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r4 = r4.errorMessage;
                r0.onStopAccel(r2, r3, r4, r1);
                return;
            L_0x0052:
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r0.getError();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorCode = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r2.errorCode;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r3 = r0.getRichmessage();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.parseLink(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.errorMessage = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r1.errorMessage;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r0.setRichmessage(r1);	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r2 = r0.getSeq();	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1.seq = r2;	 Catch:{ UnsupportedEncodingException -> 0x00c1 }
                r1 = r0;
                goto L_0x0036;
            L_0x0082:
                r0 = move-exception;
                r5 = r0;
                r0 = r1;
                r1 = r5;
            L_0x0086:
                r1.printStackTrace();
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = 4;
                r1.errorCode = r2;
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.errorCode;
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);
                r1.errorMessage = r2;
                r1 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r1.seq = r4;
                r1 = r0;
                goto L_0x0036;
            L_0x00a5:
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = 2;
                r0.errorCode = r2;
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r2 = r2.errorCode;
                r2 = com.xunlei.common.accelerator.utils.ErrorCodeUtils.getErrorDesc(r2);
                r0.errorMessage = r2;
                r0 = com.xunlei.common.accelerator.controller.XLAccelController.this;
                r0.seq = r4;
                goto L_0x0036;
            L_0x00c1:
                r1 = move-exception;
                goto L_0x0086;
                */
            }

            public void onFailure(Throwable th, byte[] bArr) {
                XLAccelController.this.errorCode = MqttConnectOptions.MQTT_VERSION_3_1;
                XLAccelController.this.errorMessage = ErrorCodeUtils.getErrorDesc(XLAccelController.this.errorCode);
                XLAccelController.this.seq = 0;
                ((XLAcceleratorInteractor) XLAccelController.this.interator).onStopAccel(XLAccelController.this.seq, XLAccelController.this.errorCode, XLAccelController.this.errorMessage, null);
            }
        });
    }

    public int getRemainTime() {
        return this.mRemainTimerCounter;
    }

    public void startKeepAlive(XLAccelUser xLAccelUser) {
        this.keepAliveUser = xLAccelUser;
        XLAlarmBaseTimer.getInstance().registerTimer(4112, XLAccelHttpReqInfo.getXLKeepAliveTime(), true, this);
    }

    public void onceKeepAlive(XLAccelUser xLAccelUser) {
        this.keepAliveUser = xLAccelUser;
        keepAlive(true);
    }

    public void stopKeepAlive() {
        XLAlarmBaseTimer.getInstance().unRegisterTimer(4112);
    }

    public void onTimerTick(int i) {
        System.out.println("keep time ");
        if (i == 4112) {
            keepAlive(false);
        }
    }

    public void keepAlive(boolean z) {
        if (this.keepAliveUser != null) {
            System.out.println("colin http  keepAlive");
            excuteRequest(this.xlParameterUtils.getStartOrStopOrAliveAddress(this.XLA_BASE_PREFIX, XLAccelHttpReqInfo.KEEP_ALIVE, this.keepAliveUser, this.mDialAccount), new AnonymousClass_7(z));
        }
    }

    public void startTimeCount(int i) {
        if (!this.mTimeCounting) {
            this.mTimeCounting = true;
            this.mTotalTimerCounter = i;
            this.mRemainTimerCounter = this.mTotalTimerCounter;
            if (this.mCountTimer == null) {
                createCountTimer();
            }
            this.mCountTimer.start();
        }
    }

    public void stopTimeCount() {
        if (this.mTimeCounting) {
            this.mTimeCounting = false;
            cancelCountTimer();
        }
    }

    private void createCountTimer() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.mCountTimer = new CountTimer((long) (this.mTotalTimerCounter * 1000), 1000);
            return;
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                XLAccelController.this.mCountTimer = new CountTimer((long) (XLAccelController.this.mTotalTimerCounter * 1000), 1000);
            }
        });
    }

    public void unInitTimer() {
        XLAlarmBaseTimer.unInit();
        cancelCountTimer();
    }

    private void cancelCountTimer() {
        if (this.mCountTimer != null) {
            this.mCountTimer.cancel();
            this.mCountTimer = null;
        }
    }
}
