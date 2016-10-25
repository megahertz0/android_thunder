package com.taobao.accs.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.base.TaoBaseService.ConnectInfo;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.h;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.message.MessageService;

// compiled from: Taobao
public abstract class AccsAbstractDataListener implements AccsDataListener {
    private static final String TAG = "AccsAbstractDataListener";

    public void onConnected(ConnectInfo connectInfo) {
    }

    public void onDisconnected(ConnectInfo connectInfo) {
    }

    public void onAntiBrush(boolean z, ExtraInfo extraInfo) {
    }

    public static int onReceiveData(Context context, Intent intent, AccsDataListener accsDataListener) {
        Throwable e;
        if (accsDataListener == null || context == null) {
            ALog.e(TAG, "onReceiveData listener or context null", new Object[0]);
        } else if (intent != null) {
            int intExtra;
            int intExtra2;
            String stringExtra;
            String stringExtra2;
            String stringExtra3;
            String str = a.d;
            try {
                intExtra = intent.getIntExtra(IntentUtil.AGOO_COMMAND, -1);
                intExtra2 = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                stringExtra = intent.getStringExtra(Constants.KEY_USER_ID);
                stringExtra2 = intent.getStringExtra(Constants.KEY_DATA_ID);
                stringExtra3 = intent.getStringExtra(Constants.KEY_SERVICE_ID);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, str, MessageService.MSG_DB_NOTIFY_REACHED, new StringBuilder("callback error").append(e.toString()).toString());
                ALog.e(TAG, "onReceiveData", e, new Object[0]);
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            }
            try {
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i(TAG, new StringBuilder("onReceiveData dataId:").append(stringExtra2).append(" serviceId:").append(stringExtra3).append(" command:").append(intExtra).toString(), new Object[0]);
                }
                if (intExtra > 0) {
                    UTMini.getInstance().commitEvent(UT.EVENT_ID, "MsgToBuss5", new StringBuilder("commandId=").append(intExtra).toString(), new StringBuilder("serviceId=").append(stringExtra3).append(" dataId=").append(stringExtra2).toString(), Integer.valueOf(Constants.SDK_VERSION_CODE));
                    b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_TO_BUSS, new StringBuilder("3commandId=").append(intExtra).append("serviceId=").append(stringExtra3).toString(), 0.0d);
                    boolean booleanExtra;
                    switch (intExtra) {
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                            accsDataListener.onBind(stringExtra3, intExtra2, getExtraInfo(intent));
                            break;
                        case R.styleable.Toolbar_contentInsetEnd:
                            accsDataListener.onUnbind(stringExtra3, intExtra2, getExtraInfo(intent));
                            break;
                        case R.styleable.AppCompatTheme_buttonStyle:
                            String stringExtra4 = intent.getStringExtra(Constants.KEY_DATA_ID);
                            if (TextUtils.equals(Constants.SEND_TYPE_RES, intent.getStringExtra(Constants.KEY_SEND_TYPE))) {
                                accsDataListener.onResponse(stringExtra3, stringExtra4, intExtra2, intent.getByteArrayExtra(SocializeConstants.JSON_DATA), getExtraInfo(intent));
                            } else {
                                accsDataListener.onSendData(stringExtra3, stringExtra4, intExtra2, getExtraInfo(intent));
                            }
                            break;
                        case R.styleable.AppCompatTheme_buttonStyleSmall:
                            byte[] byteArrayExtra = intent.getByteArrayExtra(SocializeConstants.JSON_DATA);
                            booleanExtra = intent.getBooleanExtra(Constants.KEY_NEED_BUSINESS_ACK, false);
                            if (byteArrayExtra != null) {
                                String stringExtra5 = intent.getStringExtra(Constants.KEY_DATA_ID);
                                if (ALog.isPrintLog(Level.D)) {
                                    ALog.d(TAG, new StringBuilder("COMMAND_RECEIVE_DATA onData dataId:").append(stringExtra5).append(" serviceId:").append(stringExtra3).toString(), new Object[0]);
                                }
                                ExtraInfo extraInfo = getExtraInfo(intent);
                                if (booleanExtra) {
                                    ALog.i(TAG, new StringBuilder("try to send biz ack dataId ").append(stringExtra5).toString(), new Object[0]);
                                    sendBusinessAck(context, intent, stringExtra5, extraInfo.extHeader);
                                }
                                accsDataListener.onData(stringExtra3, stringExtra, stringExtra5, byteArrayExtra, extraInfo);
                            } else {
                                ALog.e(TAG, "COMMAND_RECEIVE_DATA msg null", new Object[0]);
                                b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, stringExtra3, MessageService.MSG_DB_NOTIFY_REACHED, "COMMAND_RECEIVE_DATA msg null");
                            }
                            break;
                        case R.styleable.AppCompatTheme_checkedTextViewStyle:
                            booleanExtra = intent.getBooleanExtra(Constants.KEY_CONNECT_AVAILABLE, false);
                            str = intent.getStringExtra(com.taobao.accs.internal.b.ELECTION_KEY_HOST);
                            String stringExtra6 = intent.getStringExtra(Constants.KEY_ERROR_DETAIL);
                            boolean booleanExtra2 = intent.getBooleanExtra(Constants.KEY_TYPE_INAPP, false);
                            boolean booleanExtra3 = intent.getBooleanExtra(Constants.KEY_CENTER_HOST, false);
                            if (!TextUtils.isEmpty(str)) {
                                if (booleanExtra) {
                                    accsDataListener.onConnected(new ConnectInfo(str, booleanExtra2, booleanExtra3));
                                } else {
                                    accsDataListener.onDisconnected(new ConnectInfo(str, booleanExtra2, booleanExtra3, intExtra2, stringExtra6));
                                }
                            }
                            break;
                        case R.styleable.AppCompatTheme_editTextStyle:
                            booleanExtra = intent.getBooleanExtra(Constants.KEY_ANTI_BRUSH_RET, false);
                            ALog.e(TAG, new StringBuilder("anti brush result:").append(booleanExtra).toString(), new Object[0]);
                            accsDataListener.onAntiBrush(booleanExtra, null);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                str = stringExtra3;
                e.printStackTrace();
                b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, str, MessageService.MSG_DB_NOTIFY_REACHED, new StringBuilder("callback error").append(e.toString()).toString());
                ALog.e(TAG, "onReceiveData", e, new Object[0]);
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            }
        }
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    private static Map<ExtHeaderType, String> getExtHeader(Intent intent) {
        Map map = null;
        if (intent != null) {
            try {
                ExtHeaderType[] values = ExtHeaderType.values();
                int length = values.length;
                for (int i = 0; i < length; i++) {
                    ExtHeaderType extHeaderType = values[i];
                    CharSequence stringExtra = intent.getStringExtra(extHeaderType.toString());
                    if (!TextUtils.isEmpty(stringExtra)) {
                        if (map == null) {
                            HashMap hashMap = new HashMap();
                        }
                        map.put(extHeaderType, stringExtra);
                    }
                }
            } catch (Exception e) {
                ALog.e(TAG, e.toString(), new Object[0]);
            }
        }
        return r0;
    }

    private static ExtraInfo getExtraInfo(Intent intent) {
        Map extHeader = getExtHeader(intent);
        Object stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
        Object stringExtra2 = intent.getStringExtra(com.taobao.accs.internal.b.ELECTION_KEY_HOST);
        if (extHeader == null && TextUtils.isEmpty(stringExtra) && TextUtils.isEmpty(stringExtra2)) {
            return null;
        }
        ExtraInfo extraInfo = new ExtraInfo();
        extraInfo.extHeader = extHeader;
        extraInfo.fromPackage = stringExtra;
        extraInfo.fromHost = stringExtra2;
        return extraInfo;
    }

    private static void sendBusinessAck(Context context, Intent intent, String str, Map<ExtHeaderType, String> map) {
        try {
            ALog.i(TAG, "sendBusinessAck", Constants.KEY_DATA_ID, str);
            if (intent != null) {
                String stringExtra = intent.getStringExtra(com.taobao.accs.internal.b.ELECTION_KEY_HOST);
                String stringExtra2 = intent.getStringExtra(SocialConstants.PARAM_SOURCE);
                ACCSManager.getManagerImpl(context).sendBusinessAck(intent.getStringExtra(Constants.KEY_TARGET), stringExtra2, str, intent.getShortExtra(Constants.KEY_FLAGS, (short) 0), stringExtra, map);
                b.a(h.NAMESPACE, BaseMonitor.COUNT_BUSINESS_ACK_SUCC, a.d, 0.0d);
            }
        } catch (Throwable th) {
            ALog.e(TAG, "sendBusinessAck", th, new Object[0]);
            b.a(h.NAMESPACE, BaseMonitor.COUNT_BUSINESS_ACK_FAIL, th.toString(), 0.0d);
        }
    }
}
