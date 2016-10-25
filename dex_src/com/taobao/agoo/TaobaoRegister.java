package com.taobao.agoo;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.h;
import com.taobao.agoo.a.a;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import org.android.agoo.accs.AgooService;
import org.android.agoo.common.CallBack;
import org.android.agoo.common.MsgDO;
import org.android.agoo.common.b;
import org.android.agoo.control.NotifManager;
import org.android.agoo.message.MessageService;

public final class TaobaoRegister {
    private static final int EVENT_ID = 66001;
    static final String PREFERENCES = "Agoo_AppStore";
    static final String PROPERTY_APP_NOTIFICATION_CUSTOM_SOUND = "app_notification_custom_sound";
    static final String PROPERTY_APP_NOTIFICATION_ICON = "app_notification_icon";
    static final String PROPERTY_APP_NOTIFICATION_SOUND = "app_notification_sound";
    static final String PROPERTY_APP_NOTIFICATION_VIBRATE = "app_notification_vibrate";
    private static final String SERVICEID = "agooSend";
    protected static final String TAG = "TaobaoRegister";
    private static a mRequestListener;

    private TaobaoRegister() {
        throw new UnsupportedOperationException();
    }

    public static void register(Context context, String str, String str2, String str3, IRegister iRegister) {
        if (context == null) {
            ALog.e(TAG, "register context null", new Object[0]);
            return;
        }
        ALog.i(TAG, MiPushClient.COMMAND_REGISTER, Constants.KEY_APP_KEY, str, Constants.KEY_TTID, str3);
        ACCSManager.bindApp(context, str, str2, str3, new b(context.getApplicationContext(), iRegister, str, str3));
    }

    public static void setAlias(Context context, String str, ICallback iCallback) {
        ALog.i(TAG, com.taobao.agoo.a.a.a.JSON_CMD_SETALIAS, MsgConstant.KEY_ALIAS, str);
        Object appkey = UtilityImpl.getAppkey(context);
        Object e = b.e(context);
        if (TextUtils.isEmpty(appkey) || TextUtils.isEmpty(e) || context == null || TextUtils.isEmpty(str)) {
            if (iCallback != null) {
                iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "input params null!!");
            }
            ALog.e(TAG, "setAlias param null", Constants.SP_KEY_APPKEY, appkey, b.KEY_DEVICE_TOKEN, e, MsgConstant.KEY_ALIAS, str, "context", context);
            return;
        }
        try {
            if (com.taobao.accs.client.b.a(context.getApplicationContext()).i(str)) {
                ALog.i(TAG, "Alias already set", MsgConstant.KEY_ALIAS, str);
                if (iCallback != null) {
                    iCallback.onSuccess();
                }
            } else if (com.taobao.accs.client.b.a(context).c(context.getPackageName())) {
                if (mRequestListener == null) {
                    mRequestListener = new a();
                    ACCSManager.registerDataListener(context, TaobaoConstants.SERVICE_ID_DEVICECMD, mRequestListener);
                }
                CharSequence sendRequest = ACCSManager.sendRequest(context, new AccsRequest(null, TaobaoConstants.SERVICE_ID_DEVICECMD, com.taobao.agoo.a.a.a.a(appkey, e, str), null));
                if (TextUtils.isEmpty(sendRequest)) {
                    if (iCallback != null) {
                        iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "accs channel disabled!");
                    }
                } else if (iCallback != null) {
                    iCallback.extra = str;
                    mRequestListener.a.put(sendRequest, iCallback);
                }
            } else if (iCallback != null) {
                iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "bindApp first!!");
            }
        } catch (Throwable th) {
            ALog.e(TAG, com.taobao.agoo.a.a.a.JSON_CMD_SETALIAS, th, new Object[0]);
        }
    }

    public static void removeAlias(Context context, ICallback iCallback) {
        ALog.i(TAG, com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS, new Object[0]);
        try {
            Object appkey = UtilityImpl.getAppkey(context);
            Object e = b.e(context);
            Object f = b.f(context);
            if (TextUtils.isEmpty(appkey) || TextUtils.isEmpty(e) || context == null || TextUtils.isEmpty(f)) {
                if (iCallback != null) {
                    iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "input params null!!");
                }
                ALog.e(TAG, "setAlias param null", Constants.SP_KEY_APPKEY, appkey, b.KEY_DEVICE_TOKEN, e, com.taobao.agoo.a.a.a.JSON_PUSH_USER_TOKEN, f, "context", context);
                return;
            }
            if (mRequestListener == null) {
                mRequestListener = new a();
                ACCSManager.registerDataListener(context, TaobaoConstants.SERVICE_ID_DEVICECMD, mRequestListener);
            }
            CharSequence sendRequest = ACCSManager.sendRequest(context, new AccsRequest(null, TaobaoConstants.SERVICE_ID_DEVICECMD, com.taobao.agoo.a.a.a.b(appkey, e, f), null));
            if (TextUtils.isEmpty(sendRequest)) {
                if (iCallback != null) {
                    iCallback.onFailure(TaobaoConstants.ALIAS_ERROR, "accs channel disabled!");
                }
            } else if (iCallback != null) {
                mRequestListener.a.put(sendRequest, iCallback);
            }
        } catch (Throwable th) {
            ALog.e(TAG, com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS, th, new Object[0]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void bindAgoo(android.content.Context r4, java.lang.String r5, java.lang.String r6, org.android.agoo.common.CallBack r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.agoo.TaobaoRegister.bindAgoo(android.content.Context, java.lang.String, java.lang.String, org.android.agoo.common.CallBack):void");
        /*
        if (r4 != 0) goto L_0x0019;
    L_0x0002:
        r0 = new java.lang.NullPointerException;	 Catch:{ Throwable -> 0x000b }
        r1 = "Context==null";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x000b }
        throw r0;	 Catch:{ Throwable -> 0x000b }
    L_0x000b:
        r0 = move-exception;
        r1 = "TaobaoRegister";
        r2 = "bindAgoo";
        r3 = 0;
        r3 = new java.lang.Object[r3];
        com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);
    L_0x0018:
        return;
    L_0x0019:
        r0 = android.text.TextUtils.isEmpty(r5);	 Catch:{ Throwable -> 0x000b }
        if (r0 == 0) goto L_0x0028;
    L_0x001f:
        r0 = new java.lang.NullPointerException;	 Catch:{ Throwable -> 0x000b }
        r1 = "appkey==null";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x000b }
        throw r0;	 Catch:{ Throwable -> 0x000b }
    L_0x0028:
        r0 = android.text.TextUtils.isEmpty(r6);	 Catch:{ Throwable -> 0x000b }
        if (r0 == 0) goto L_0x0037;
    L_0x002e:
        r0 = new java.lang.NullPointerException;	 Catch:{ Throwable -> 0x000b }
        r1 = "ttId==null";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x000b }
        throw r0;	 Catch:{ Throwable -> 0x000b }
    L_0x0037:
        org.android.agoo.common.b.a(r4, r5, r6);	 Catch:{ Throwable -> 0x000b }
        org.android.agoo.accs.AgooService.a = r7;	 Catch:{ Throwable -> 0x000b }
        r0 = "agooSend";
        com.taobao.accs.ACCSManager.bindService(r4, r0);	 Catch:{ Throwable -> 0x000b }
        r0 = com.taobao.accs.utl.UTMini.getInstance();	 Catch:{ Throwable -> 0x000b }
        r1 = 66001; // 0x101d1 float:9.2487E-41 double:3.2609E-319;
        r2 = "bindAgoo";
        r3 = com.taobao.accs.utl.UtilityImpl.getDeviceId(r4);	 Catch:{ Throwable -> 0x000b }
        r0.commitEvent(r1, r2, r3);	 Catch:{ Throwable -> 0x000b }
        goto L_0x0018;
        */
    }

    public static void unBindAgoo(Context context, String str, String str2, CallBack callBack) {
        if (context == null) {
            throw new NullPointerException("context==null");
        } else if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("appkey==null");
        } else if (TextUtils.isEmpty(str2)) {
            throw new NullPointerException("ttId==null");
        } else {
            AgooService.b = callBack;
            ACCSManager.unbindService(context, SERVICEID);
            UTMini.getInstance().commitEvent(EVENT_ID, "unregister", UtilityImpl.getDeviceId(context));
        }
    }

    public static void clickMessage(Context context, String str, String str2) {
        try {
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(TAG, new StringBuilder("clickMessage,messageId=").append(str).append(",taskId=").append(str2).toString(), new Object[0]);
            }
            String str3 = h.NAMESPACE;
            String str4 = MessageService.MSG_ACCS_NOTIFY_CLICK;
            if (TextUtils.isEmpty(str)) {
                ALog.d(TAG, "messageId == null", new Object[0]);
                return;
            }
            NotifManager notifManager = new NotifManager();
            notifManager.init(context);
            MsgDO msgDO = new MsgDO();
            msgDO.msgIds = str;
            msgDO.messageSource = str3;
            msgDO.msgStatus = str4;
            notifManager.reportNotifyMessage(msgDO);
        } catch (Throwable th) {
            ALog.e(TAG, new StringBuilder("clickMessage,error=").append(th).toString(), new Object[0]);
        }
    }

    public static void dismissMessage(Context context, String str, String str2) {
        try {
            if (ALog.isPrintLog(Level.D)) {
                ALog.d(TAG, new StringBuilder("clickMessage,messageId=").append(str).append(",taskId=").append(str2).toString(), new Object[0]);
            }
            String str3 = h.NAMESPACE;
            String str4 = MessageService.MSG_ACCS_NOTIFY_DISMISS;
            if (TextUtils.isEmpty(str)) {
                ALog.d(TAG, "messageId == null", new Object[0]);
                return;
            }
            NotifManager notifManager = new NotifManager();
            notifManager.init(context);
            MsgDO msgDO = new MsgDO();
            msgDO.msgIds = str;
            msgDO.messageSource = str3;
            msgDO.msgStatus = str4;
            notifManager.reportNotifyMessage(msgDO);
        } catch (Throwable th) {
            ALog.e(TAG, new StringBuilder("clickMessage,error=").append(th).toString(), new Object[0]);
        }
    }

    public static void pingApp(Context context, String str, String str2, String str3, int i) {
        NotifManager notifManager = new NotifManager();
        notifManager.init(context);
        notifManager.pingApp(str, str2, str3, i);
    }

    public static void isEnableDaemonServer(Context context, boolean z) {
        if (ALog.isPrintLog(Level.I)) {
            ALog.i(TAG, new StringBuilder("isEnableDaemonServer begin,enable=").append(z).toString(), new Object[0]);
        }
        b.a(context, z);
    }

    public static void setAgooMsgReceiveService(String str) {
        com.taobao.accs.client.a.b = str;
    }

    @Deprecated
    public static void setNotificationIcon(Context context, int i) {
    }

    @Deprecated
    public static void setNotificationSound(Context context, boolean z) {
    }

    @Deprecated
    public static void setBuilderSound(Context context, String str) {
    }

    @Deprecated
    public static void setNotificationVibrate(Context context, boolean z) {
    }

    @Deprecated
    public static void unregister(Context context, CallBack callBack) {
        ALog.i(TAG, new StringBuilder("unregister,success,deviceid=").append(UtilityImpl.getDeviceId(context)).toString(), new Object[0]);
        UTMini.getInstance().commitEvent(EVENT_ID, "unregister", UtilityImpl.getDeviceId(context));
        ACCSManager.unbindService(context, SERVICEID);
    }
}
