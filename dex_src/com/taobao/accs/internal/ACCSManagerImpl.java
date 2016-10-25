package com.taobao.accs.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import anet.channel.AccsSessionManager;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.strategy.StrategyCenter;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.IACCSManager;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.ILoginInfo;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.client.AccsConfig;
import com.taobao.accs.client.AccsConfig.SECURITY_TYPE;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.b;
import com.taobao.accs.data.e;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.h;
import com.taobao.accs.utl.h.a;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.connect.common.Constants;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public class ACCSManagerImpl implements IACCSManager {
    private int a;
    private b b;
    private com.taobao.accs.client.b c;

    public ACCSManagerImpl() {
        this.a = 0;
    }

    public void bindApp(Context context, String str, String str2, IAppReceiver iAppReceiver) {
        bindApp(context, str, h.NAMESPACE, str2, iAppReceiver);
    }

    public void bindApp(Context context, String str, String str2, String str3, IAppReceiver iAppReceiver) {
        if (context != null) {
            ALog.d("ACCSManagerImpl", new StringBuilder("bindApp APPKEY:").append(str).toString(), new Object[0]);
            Message buildParameterError = Message.buildParameterError(context.getPackageName(), 1);
            if (UtilityImpl.getFocusDisableStatus(context)) {
                a(context, buildParameterError, ErrorCode.ACCS_DISABLEED);
            } else if (AccsConfig.mSecurityType == SECURITY_TYPE.SECURITY_OFF && TextUtils.isEmpty(str2)) {
                a(context, buildParameterError, Constants.ERROR_QQVERSION_LOW);
            } else if (iAppReceiver == null) {
                a(context, buildParameterError, ErrorCode.APPRECEIVER_NULL);
            } else if (TextUtils.isEmpty(str)) {
                a(context, buildParameterError, Constants.ERROR_LOCATION_VERIFY_FAILED);
            } else {
                if (!TextUtils.equals(UtilityImpl.getAppkey(context), str)) {
                    UtilityImpl.setAppInfo(context, str, null, str3);
                }
                GlobalClientInfo.getInstance(context).setAppReceiver(iAppReceiver);
                GlobalClientInfo.getInstance(context).setAppSecret(str2);
                UtilityImpl.enableService(context);
                Intent a = a(context, 1);
                if (a != null) {
                    try {
                        String str4 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                        boolean appVersionChanged = UtilityImpl.appVersionChanged(context);
                        if (appVersionChanged) {
                            a.putExtra(com.taobao.accs.common.Constants.KEY_FOUCE_BIND, true);
                        }
                        a.putExtra(com.taobao.accs.common.Constants.KEY_APP_KEY, str);
                        a.putExtra(com.taobao.accs.common.Constants.KEY_TTID, str3);
                        a.putExtra(com.taobao.accs.common.Constants.KEY_APP_VERSION, str4);
                        a.putExtra(com.taobao.accs.common.Constants.SP_APP_SECRET, str2);
                        if (UtilityImpl.isMainProcess(context)) {
                            a(context, Message.buildBindApp(context, a), 1, appVersionChanged);
                        }
                        try {
                            h.a(new String[]{h.NAMESPACE}, new a());
                            h.d();
                        } catch (Throwable th) {
                            ALog.w("ACCSManagerImpl", "no orange sdk", new Object[0]);
                        }
                    } catch (Throwable th2) {
                        ALog.e("ACCSManagerImpl", "bindApp exception", th2, new Object[0]);
                    }
                }
            }
        }
    }

    private void a(Context context) {
        try {
            com.taobao.accs.common.a.a(new a(this, context), 10000, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            ALog.w("ACCSManagerImpl", "startChannelService", th, new Object[0]);
        }
    }

    private void a(Context context, Message message, int i, boolean z) {
        com.taobao.accs.net.a a = com.taobao.accs.net.a.a(context, 1);
        a.a();
        if (message == null) {
            ALog.e("ACCSManagerImpl", "message is null", new Object[0]);
            a(context, Message.buildParameterError(context.getPackageName(), i), Tabs.TAB_CREATE_REACH_MAX_COUNT);
        } else {
            boolean z2;
            if (this.c == null) {
                this.c = com.taobao.accs.client.b.a(context);
            }
            int i2;
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (this.c.c(message.getPackageName()) && !z) {
                        ALog.i("ACCSManagerImpl", message.getPackageName() + " isAppBinded", new Object[0]);
                        a(context, message, Impl.STATUS_SUCCESS);
                        i2 = 0;
                    }
                    z2 = true;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (this.c.d(message.getPackageName())) {
                        ALog.i("ACCSManagerImpl", message.getPackageName() + " isAppUnbinded", new Object[0]);
                        a(context, message, Impl.STATUS_SUCCESS);
                        i2 = 0;
                    }
                    z2 = true;
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (this.c.b(message.getPackageName(), message.userinfo) && !z) {
                        ALog.i("ACCSManagerImpl", message.getPackageName() + "/" + message.userinfo + " isUserBinded", "isForceBind", Boolean.valueOf(z));
                        a(context, message, Impl.STATUS_SUCCESS);
                        i2 = 0;
                    }
                    z2 = true;
                    break;
                default:
                    z2 = true;
                    break;
            }
            if (z2) {
                ALog.i("ACCSManagerImpl", "sendControlMessage", IntentUtil.AGOO_COMMAND, Integer.valueOf(i));
                a.b(message, true);
            }
        }
        a(context.getApplicationContext());
    }

    private void a(Context context, Message message, int i) {
        if (this.b == null) {
            this.b = b.a(context);
        }
        this.b.a(message, i);
    }

    public void unbindApp(Context context) {
        ALog.e("ACCSManagerImpl", new StringBuilder("unbindApp").append(UtilityImpl.getStackMsg(new Exception())).toString(), new Object[0]);
        if (!UtilityImpl.getFocusDisableStatus(context)) {
            Intent a = a(context, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            if (a == null) {
                a(context, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, null, null);
            } else if (UtilityImpl.isMainProcess(context)) {
                a(context, Message.buildUnbindApp(context, a), (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, false);
            } else {
                context.startService(a);
            }
        }
    }

    public void bindUser(Context context, String str) {
        bindUser(context, str, false);
    }

    public void bindUser(Context context, String str, boolean z) {
        try {
            ALog.i("ACCSManagerImpl", "bindUser", "userId", str);
            if (UtilityImpl.getFocusDisableStatus(context)) {
                ALog.e("ACCSManagerImpl", "accs disabled", new Object[0]);
                return;
            }
            Intent a = a(context, XZBDevice.DOWNLOAD_LIST_FAILED);
            if (a == null) {
                ALog.e("ACCSManagerImpl", "intent null", new Object[0]);
                a(context, (int) XZBDevice.DOWNLOAD_LIST_FAILED, null, null);
                return;
            }
            Object appkey = UtilityImpl.getAppkey(context);
            if (TextUtils.isEmpty(appkey)) {
                ALog.e("ACCSManagerImpl", "appKey null", new Object[0]);
                return;
            }
            if (UtilityImpl.appVersionChanged(context) || z) {
                ALog.i("ACCSManagerImpl", "force bind User", new Object[0]);
                a.putExtra(com.taobao.accs.common.Constants.KEY_FOUCE_BIND, true);
                z = true;
            }
            a.putExtra(com.taobao.accs.common.Constants.KEY_APP_KEY, appkey);
            a.putExtra(com.taobao.accs.common.Constants.KEY_USER_ID, str);
            if (UtilityImpl.isMainProcess(context)) {
                a(context, Message.buildBindUser(context, a), (int) XZBDevice.DOWNLOAD_LIST_FAILED, z);
            } else {
                context.startService(a);
            }
        } catch (Throwable th) {
            ALog.e("ACCSManagerImpl", "bindUser", th, new Object[0]);
        }
    }

    public void unbindUser(Context context) {
        if (!UtilityImpl.getFocusDisableStatus(context) && !UtilityImpl.getFocusDisableStatus(context)) {
            Intent a = a(context, XZBDevice.DOWNLOAD_LIST_ALL);
            if (a == null) {
                a(context, (int) XZBDevice.DOWNLOAD_LIST_ALL, null, null);
                return;
            }
            Object appkey = UtilityImpl.getAppkey(context);
            if (!TextUtils.isEmpty(appkey)) {
                a.putExtra(com.taobao.accs.common.Constants.KEY_APP_KEY, appkey);
                if (UtilityImpl.isMainProcess(context)) {
                    a(context, Message.buildUnbindUser(context, a), (int) XZBDevice.DOWNLOAD_LIST_ALL, false);
                } else {
                    context.startService(a);
                }
            }
        }
    }

    public void bindService(Context context, String str) {
        if (!UtilityImpl.getFocusDisableStatus(context) && !UtilityImpl.getFocusDisableStatus(context)) {
            Intent a = a(context, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            if (a == null) {
                a(context, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, str, null);
                return;
            }
            Object appkey = UtilityImpl.getAppkey(context);
            if (!TextUtils.isEmpty(appkey)) {
                a.putExtra(com.taobao.accs.common.Constants.KEY_APP_KEY, appkey);
                a.putExtra(com.taobao.accs.common.Constants.KEY_SERVICE_ID, str);
                if (UtilityImpl.isMainProcess(context)) {
                    a(context, Message.buildBindService(context, a), (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, false);
                } else {
                    context.startService(a);
                }
            }
        }
    }

    public void unbindService(Context context, String str) {
        if (!UtilityImpl.getFocusDisableStatus(context)) {
            Intent a = a(context, R.styleable.Toolbar_contentInsetEnd);
            if (a == null) {
                a(context, (int) R.styleable.Toolbar_contentInsetEnd, str, null);
                return;
            }
            Object appkey = UtilityImpl.getAppkey(context);
            if (!TextUtils.isEmpty(appkey)) {
                a.putExtra(com.taobao.accs.common.Constants.KEY_APP_KEY, appkey);
                a.putExtra(com.taobao.accs.common.Constants.KEY_SERVICE_ID, str);
                if (UtilityImpl.isMainProcess(context)) {
                    a(context, Message.buildUnbindService(context, a), (int) R.styleable.Toolbar_contentInsetEnd, false);
                } else {
                    context.startService(a);
                }
            }
        }
    }

    public String sendData(Context context, String str, String str2, byte[] bArr, String str3) {
        return sendData(context, str, str2, bArr, str3, null);
    }

    public String sendData(Context context, String str, String str2, byte[] bArr, String str3, String str4) {
        return sendData(context, str, str2, bArr, str3, str4, null);
    }

    public String sendData(Context context, String str, String str2, byte[] bArr, String str3, String str4, URL url) {
        return sendData(context, new AccsRequest(str, str2, bArr, str3, str4, url, null));
    }

    public String sendData(Context context, AccsRequest accsRequest) {
        try {
            boolean focusDisableStatus = UtilityImpl.getFocusDisableStatus(context);
            if (focusDisableStatus || accsRequest == null) {
                if (focusDisableStatus) {
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, MessageService.MSG_DB_NOTIFY_REACHED, "accs disable");
                } else {
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, com.umeng.a.d, MessageService.MSG_DB_NOTIFY_REACHED, "data null");
                }
                ALog.e("ACCSManagerImpl", new StringBuilder("send data dataInfo null or disable:").append(focusDisableStatus).toString(), new Object[0]);
                return null;
            }
            if (TextUtils.isEmpty(accsRequest.dataId)) {
                synchronized (ACCSManagerImpl.class) {
                    this.a++;
                    accsRequest.dataId = this.a;
                }
            }
            Object appkey = UtilityImpl.getAppkey(context);
            if (TextUtils.isEmpty(appkey)) {
                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, MessageService.MSG_DB_NOTIFY_REACHED, "data appkey null");
                ALog.e("ACCSManagerImpl", new StringBuilder("send data appkey null dataid:").append(accsRequest.dataId).toString(), new Object[0]);
                return null;
            }
            com.taobao.accs.net.a a = com.taobao.accs.net.a.a(context, 1);
            a.a();
            Message buildSendData = Message.buildSendData(context, context.getPackageName(), appkey, accsRequest);
            if (buildSendData.getNetPermanceMonitor() != null) {
                buildSendData.getNetPermanceMonitor().onSend();
            }
            a.b(buildSendData, true);
            return accsRequest.dataId;
        } catch (Throwable th) {
            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, accsRequest.serviceId, MessageService.MSG_DB_NOTIFY_REACHED, new StringBuilder("data ").append(th.toString()).toString());
            ALog.e("ACCSManagerImpl", new StringBuilder("send data dataid:").append(accsRequest.dataId).toString(), th, new Object[0]);
        }
    }

    public String sendRequest(Context context, String str, String str2, byte[] bArr, String str3, String str4) {
        return sendRequest(context, str, str2, bArr, str3, str4, null);
    }

    public String sendRequest(Context context, String str, String str2, byte[] bArr, String str3, String str4, URL url) {
        return sendRequest(context, new AccsRequest(str, str2, bArr, str3, str4, url, null));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String sendRequest(android.content.Context r9, com.taobao.accs.ACCSManager.AccsRequest r10, java.lang.String r11, boolean r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.internal.ACCSManagerImpl.sendRequest(android.content.Context, com.taobao.accs.ACCSManager$AccsRequest, java.lang.String, boolean):java.lang.String");
        /*
        this = this;
        r0 = 0;
        r7 = 0;
        if (r10 != 0) goto L_0x0021;
    L_0x0004:
        r1 = "ACCSManagerImpl";
        r2 = "sendRequest request null";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0045 }
        com.taobao.accs.utl.ALog.e(r1, r2, r3);	 Catch:{ Throwable -> 0x0045 }
        r1 = "accs";
        r2 = "send_fail";
        r3 = 0;
        r4 = "1";
        r5 = "request null";
        com.taobao.accs.utl.b.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0045 }
    L_0x0020:
        return r0;
    L_0x0021:
        r1 = com.taobao.accs.utl.UtilityImpl.getFocusDisableStatus(r9);	 Catch:{ Throwable -> 0x0045 }
        if (r1 == 0) goto L_0x0085;
    L_0x0027:
        r1 = "ACCSManagerImpl";
        r2 = "sendRequest disable";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0045 }
        com.taobao.accs.utl.ALog.e(r1, r2, r3);	 Catch:{ Throwable -> 0x0045 }
        r1 = "accs";
        r2 = "send_fail";
        r3 = r10.serviceId;	 Catch:{ Throwable -> 0x0045 }
        r4 = "1";
        r5 = "accs disable";
        com.taobao.accs.utl.b.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0020;
    L_0x0045:
        r0 = move-exception;
        r1 = "accs";
        r2 = "send_fail";
        r3 = r10.serviceId;
        r4 = "1";
        r5 = new java.lang.StringBuilder;
        r6 = "request ";
        r5.<init>(r6);
        r6 = r0.toString();
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.taobao.accs.utl.b.a(r1, r2, r3, r4, r5);
        r1 = "ACCSManagerImpl";
        r2 = new java.lang.StringBuilder;
        r3 = "sendRequest dataid:";
        r2.<init>(r3);
        r3 = r10.dataId;
        r2 = r2.append(r3);
        r2 = r2.toString();
        r3 = new java.lang.Object[r7];
        com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);
    L_0x0082:
        r0 = r10.dataId;
        goto L_0x0020;
    L_0x0085:
        r1 = r10.dataId;	 Catch:{ Throwable -> 0x0045 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x0045 }
        if (r1 == 0) goto L_0x00a8;
    L_0x008d:
        r1 = com.taobao.accs.internal.ACCSManagerImpl.class;
        monitor-enter(r1);	 Catch:{ Throwable -> 0x0045 }
        r2 = r8.a;	 Catch:{ all -> 0x00e0 }
        r2 = r2 + 1;
        r8.a = r2;	 Catch:{ all -> 0x00e0 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e0 }
        r2.<init>();	 Catch:{ all -> 0x00e0 }
        r3 = r8.a;	 Catch:{ all -> 0x00e0 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x00e0 }
        r2 = r2.toString();	 Catch:{ all -> 0x00e0 }
        r10.dataId = r2;	 Catch:{ all -> 0x00e0 }
        monitor-exit(r1);	 Catch:{ all -> 0x00e0 }
    L_0x00a8:
        r1 = com.taobao.accs.utl.UtilityImpl.getAppkey(r9);	 Catch:{ Throwable -> 0x0045 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x0045 }
        if (r2 == 0) goto L_0x00e3;
    L_0x00b2:
        r1 = "accs";
        r2 = "send_fail";
        r3 = r10.serviceId;	 Catch:{ Throwable -> 0x0045 }
        r4 = "1";
        r5 = "request appkey null";
        com.taobao.accs.utl.b.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0045 }
        r1 = "ACCSManagerImpl";
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0045 }
        r3 = "sendRequest appkey null dataid:";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0045 }
        r3 = r10.dataId;	 Catch:{ Throwable -> 0x0045 }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0045 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0045 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0045 }
        com.taobao.accs.utl.ALog.e(r1, r2, r3);	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0020;
    L_0x00e0:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ Throwable -> 0x0045 }
        throw r0;	 Catch:{ Throwable -> 0x0045 }
    L_0x00e3:
        r0 = 1;
        r0 = com.taobao.accs.net.a.a(r9, r0);	 Catch:{ Throwable -> 0x0045 }
        r0.a();	 Catch:{ Throwable -> 0x0045 }
        if (r11 != 0) goto L_0x00f1;
    L_0x00ed:
        r11 = r9.getPackageName();	 Catch:{ Throwable -> 0x0045 }
    L_0x00f1:
        r1 = com.taobao.accs.data.Message.buildRequest(r9, r11, r1, r10, r12);	 Catch:{ Throwable -> 0x0045 }
        r2 = r1.getNetPermanceMonitor();	 Catch:{ Throwable -> 0x0045 }
        if (r2 == 0) goto L_0x0102;
    L_0x00fb:
        r2 = r1.getNetPermanceMonitor();	 Catch:{ Throwable -> 0x0045 }
        r2.onSend();	 Catch:{ Throwable -> 0x0045 }
    L_0x0102:
        r2 = 1;
        r0.b(r1, r2);	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0082;
        */
    }

    public String sendRequest(Context context, AccsRequest accsRequest) {
        return sendRequest(context, accsRequest, null, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String sendPushResponse(android.content.Context r10, com.taobao.accs.ACCSManager.AccsRequest r11, com.taobao.accs.base.TaoBaseService.ExtraInfo r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.internal.ACCSManagerImpl.sendPushResponse(android.content.Context, com.taobao.accs.ACCSManager$AccsRequest, com.taobao.accs.base.TaoBaseService$ExtraInfo):java.lang.String");
        /*
        this = this;
        r8 = 0;
        r0 = 1;
        r1 = 0;
        if (r10 == 0) goto L_0x0007;
    L_0x0005:
        if (r11 != 0) goto L_0x0041;
    L_0x0007:
        r0 = "ACCSManagerImpl";
        r2 = "sendPushResponse input null";
        r3 = 6;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0065 }
        r4 = 0;
        r5 = "context";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0065 }
        r4 = 1;
        r3[r4] = r10;	 Catch:{ Throwable -> 0x0065 }
        r4 = 2;
        r5 = "response";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0065 }
        r4 = 3;
        r3[r4] = r11;	 Catch:{ Throwable -> 0x0065 }
        r4 = 4;
        r5 = "extraInfo";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0065 }
        r4 = 5;
        r3[r4] = r12;	 Catch:{ Throwable -> 0x0065 }
        com.taobao.accs.utl.ALog.e(r0, r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r0 = "accs";
        r2 = "send_fail";
        r3 = "";
        r4 = "1";
        r5 = "sendPushResponse null";
        com.taobao.accs.utl.b.a(r0, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0065 }
    L_0x0040:
        return r8;
    L_0x0041:
        r2 = "accs";
        r3 = "send_fail";
        r4 = "push response total";
        com.taobao.accs.utl.b.a(r2, r3, r4);	 Catch:{ Throwable -> 0x0065 }
        r2 = com.taobao.accs.utl.UtilityImpl.getFocusDisableStatus(r10);	 Catch:{ Throwable -> 0x0065 }
        if (r2 == 0) goto L_0x00a3;
    L_0x0053:
        r0 = "accs";
        r2 = "send_fail";
        r3 = r11.serviceId;	 Catch:{ Throwable -> 0x0065 }
        r4 = "1";
        r5 = "sendPushResponse accs disable";
        com.taobao.accs.utl.b.a(r0, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0040;
    L_0x0065:
        r0 = move-exception;
        r2 = "accs";
        r3 = "send_fail";
        r4 = r11.serviceId;
        r5 = "1";
        r6 = new java.lang.StringBuilder;
        r7 = "push response ";
        r6.<init>(r7);
        r7 = r0.toString();
        r6 = r6.append(r7);
        r6 = r6.toString();
        com.taobao.accs.utl.b.a(r2, r3, r4, r5, r6);
        r2 = "ACCSManagerImpl";
        r3 = new java.lang.StringBuilder;
        r4 = "sendPushResponse dataid:";
        r3.<init>(r4);
        r4 = r11.dataId;
        r3 = r3.append(r4);
        r3 = r3.toString();
        r1 = new java.lang.Object[r1];
        com.taobao.accs.utl.ALog.e(r2, r3, r0, r1);
        goto L_0x0040;
    L_0x00a3:
        r3 = com.taobao.accs.utl.UtilityImpl.getAppkey(r10);	 Catch:{ Throwable -> 0x0065 }
        r2 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x0065 }
        if (r2 == 0) goto L_0x00db;
    L_0x00ad:
        r0 = "accs";
        r2 = "send_fail";
        r3 = r11.serviceId;	 Catch:{ Throwable -> 0x0065 }
        r4 = "1";
        r5 = "sendPushResponse appkey null";
        com.taobao.accs.utl.b.a(r0, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0065 }
        r0 = "ACCSManagerImpl";
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0065 }
        r3 = "sendPushResponse appkey null dataid:";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0065 }
        r3 = r11.dataId;	 Catch:{ Throwable -> 0x0065 }
        r2 = r2.append(r3);	 Catch:{ Throwable -> 0x0065 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0065 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0065 }
        com.taobao.accs.utl.ALog.e(r0, r2, r3);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0040;
    L_0x00db:
        r2 = r11.dataId;	 Catch:{ Throwable -> 0x0065 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x0065 }
        if (r2 == 0) goto L_0x00fe;
    L_0x00e3:
        r2 = com.taobao.accs.internal.ACCSManagerImpl.class;
        monitor-enter(r2);	 Catch:{ Throwable -> 0x0065 }
        r4 = r9.a;	 Catch:{ all -> 0x01ce }
        r4 = r4 + 1;
        r9.a = r4;	 Catch:{ all -> 0x01ce }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01ce }
        r4.<init>();	 Catch:{ all -> 0x01ce }
        r5 = r9.a;	 Catch:{ all -> 0x01ce }
        r4 = r4.append(r5);	 Catch:{ all -> 0x01ce }
        r4 = r4.toString();	 Catch:{ all -> 0x01ce }
        r11.dataId = r4;	 Catch:{ all -> 0x01ce }
        monitor-exit(r2);	 Catch:{ all -> 0x01ce }
    L_0x00fe:
        if (r12 != 0) goto L_0x0105;
    L_0x0100:
        r12 = new com.taobao.accs.base.TaoBaseService$ExtraInfo;	 Catch:{ Throwable -> 0x0065 }
        r12.<init>();	 Catch:{ Throwable -> 0x0065 }
    L_0x0105:
        r2 = 0;
        r11.host = r2;	 Catch:{ Throwable -> 0x0065 }
        r2 = r12.fromPackage;	 Catch:{ Throwable -> 0x0065 }
        if (r2 != 0) goto L_0x011e;
    L_0x010c:
        r2 = com.taobao.accs.a.a.a(r10);	 Catch:{ Throwable -> 0x0065 }
        r2 = r2.a;	 Catch:{ Throwable -> 0x0065 }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x0065 }
        if (r4 == 0) goto L_0x011c;
    L_0x0118:
        r2 = r10.getPackageName();	 Catch:{ Throwable -> 0x0065 }
    L_0x011c:
        r12.fromPackage = r2;	 Catch:{ Throwable -> 0x0065 }
    L_0x011e:
        r2 = r12.fromHost;	 Catch:{ Throwable -> 0x0065 }
        if (r2 != 0) goto L_0x0144;
    L_0x0122:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0065 }
        r4 = "https://";
        r2.<init>(r4);	 Catch:{ Throwable -> 0x0065 }
        r4 = com.taobao.accs.net.a.b(r10);	 Catch:{ Throwable -> 0x0065 }
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x0065 }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x0065 }
        r12.fromHost = r2;	 Catch:{ Throwable -> 0x0065 }
        r2 = "ACCSManagerImpl";
        r4 = "response.host null, set channel host default";
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x0065 }
        com.taobao.accs.utl.ALog.w(r2, r4, r5);	 Catch:{ Throwable -> 0x0065 }
    L_0x0144:
        r2 = new java.net.URL;	 Catch:{ Throwable -> 0x0065 }
        r4 = r12.fromHost;	 Catch:{ Throwable -> 0x0065 }
        r2.<init>(r4);	 Catch:{ Throwable -> 0x0065 }
        r11.host = r2;	 Catch:{ Throwable -> 0x0065 }
        r2 = com.taobao.accs.net.a.b(r10);	 Catch:{ Throwable -> 0x0065 }
        r4 = r11.host;	 Catch:{ Throwable -> 0x0065 }
        r4 = r4.getHost();	 Catch:{ Throwable -> 0x0065 }
        r2 = r2.equals(r4);	 Catch:{ Throwable -> 0x0065 }
        if (r2 == 0) goto L_0x015e;
    L_0x015d:
        r0 = r1;
    L_0x015e:
        r2 = "ACCSManagerImpl";
        r4 = "sendPushResponse";
        r5 = 8;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x0065 }
        r6 = 0;
        r7 = "sendbyInapp";
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        r6 = 1;
        r7 = java.lang.Boolean.valueOf(r0);	 Catch:{ Throwable -> 0x0065 }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        r6 = 2;
        r7 = "host";
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        r6 = 3;
        r7 = r12.fromHost;	 Catch:{ Throwable -> 0x0065 }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        r6 = 4;
        r7 = "pkg";
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        r6 = 5;
        r7 = r12.fromPackage;	 Catch:{ Throwable -> 0x0065 }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        r6 = 6;
        r7 = "dataId";
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        r6 = 7;
        r7 = r11.dataId;	 Catch:{ Throwable -> 0x0065 }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        com.taobao.accs.utl.ALog.i(r2, r4, r5);	 Catch:{ Throwable -> 0x0065 }
        if (r0 == 0) goto L_0x01f6;
    L_0x019b:
        r0 = "ACCSManagerImpl";
        r2 = "sendPushResponse inapp by";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0065 }
        r4 = 0;
        r5 = "app";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0065 }
        r4 = 1;
        r5 = r12.fromPackage;	 Catch:{ Throwable -> 0x0065 }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0065 }
        com.taobao.accs.utl.ALog.i(r0, r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r0 = r10.getPackageName();	 Catch:{ Throwable -> 0x0065 }
        r2 = r12.fromPackage;	 Catch:{ Throwable -> 0x0065 }
        r0 = r0.equals(r2);	 Catch:{ Throwable -> 0x0065 }
        if (r0 == 0) goto L_0x01d1;
    L_0x01be:
        r0 = com.taobao.accs.utl.UtilityImpl.isMainProcess(r10);	 Catch:{ Throwable -> 0x0065 }
        if (r0 == 0) goto L_0x01d1;
    L_0x01c4:
        r0 = r10.getPackageName();	 Catch:{ Throwable -> 0x0065 }
        r2 = 0;
        r9.sendRequest(r10, r11, r0, r2);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0040;
    L_0x01ce:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ Throwable -> 0x0065 }
        throw r0;	 Catch:{ Throwable -> 0x0065 }
    L_0x01d1:
        r0 = new android.content.Intent;	 Catch:{ Throwable -> 0x0065 }
        r2 = "com.taobao.accs.intent.action.SEND";
        r0.<init>(r2);	 Catch:{ Throwable -> 0x0065 }
        r2 = r12.fromPackage;	 Catch:{ Throwable -> 0x0065 }
        r3 = "com.taobao.accs.data.MsgDistributeService";
        r0.setClassName(r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r2 = "packageName";
        r3 = r10.getPackageName();	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r2 = "reqdata";
        r0.putExtra(r2, r11);	 Catch:{ Throwable -> 0x0065 }
        r10.startService(r0);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0040;
    L_0x01f6:
        r0 = 100;
        r0 = a(r10, r0);	 Catch:{ Throwable -> 0x0065 }
        if (r0 != 0) goto L_0x0241;
    L_0x01fe:
        r0 = "accs";
        r2 = "send_fail";
        r3 = r11.serviceId;	 Catch:{ Throwable -> 0x0065 }
        r4 = "1";
        r5 = "push response intent null";
        com.taobao.accs.utl.b.a(r0, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0065 }
        r0 = 100;
        r2 = r11.serviceId;	 Catch:{ Throwable -> 0x0065 }
        r3 = r11.dataId;	 Catch:{ Throwable -> 0x0065 }
        r9.a(r10, r0, r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r0 = "ACCSManagerImpl";
        r2 = "sendPushResponse input null";
        r3 = 6;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0065 }
        r4 = 0;
        r5 = "context";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0065 }
        r4 = 1;
        r3[r4] = r10;	 Catch:{ Throwable -> 0x0065 }
        r4 = 2;
        r5 = "response";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0065 }
        r4 = 3;
        r3[r4] = r11;	 Catch:{ Throwable -> 0x0065 }
        r4 = 4;
        r5 = "extraInfo";
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0065 }
        r4 = 5;
        r3[r4] = r12;	 Catch:{ Throwable -> 0x0065 }
        com.taobao.accs.utl.ALog.e(r0, r2, r3);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0040;
    L_0x0241:
        r2 = "ACCSManagerImpl";
        r4 = "sendPushResponse channel by";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x0065 }
        r6 = 0;
        r7 = "app";
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        r6 = 1;
        r7 = r12.fromPackage;	 Catch:{ Throwable -> 0x0065 }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0065 }
        com.taobao.accs.utl.ALog.i(r2, r4, r5);	 Catch:{ Throwable -> 0x0065 }
        r2 = r12.fromPackage;	 Catch:{ Throwable -> 0x0065 }
        r4 = "com.taobao.accs.ChannelService";
        r0.setClassName(r2, r4);	 Catch:{ Throwable -> 0x0065 }
        r2 = "send_type";
        r4 = com.taobao.accs.data.Message.ReqType.REQ;	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r4);	 Catch:{ Throwable -> 0x0065 }
        r2 = "appKey";
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r2 = "userInfo";
        r3 = r11.userId;	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r2 = "serviceId";
        r3 = r11.serviceId;	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r2 = "data";
        r3 = r11.data;	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r2 = "dataId";
        r3 = r11.dataId;	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
        r2 = r11.businessId;	 Catch:{ Throwable -> 0x0065 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x0065 }
        if (r2 != 0) goto L_0x029e;
    L_0x0296:
        r2 = "businessId";
        r3 = r11.businessId;	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
    L_0x029e:
        r2 = r11.tag;	 Catch:{ Throwable -> 0x0065 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x0065 }
        if (r2 != 0) goto L_0x02ae;
    L_0x02a6:
        r2 = "extTag";
        r3 = r11.tag;	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
    L_0x02ae:
        r2 = r11.target;	 Catch:{ Throwable -> 0x0065 }
        if (r2 == 0) goto L_0x02ba;
    L_0x02b2:
        r2 = "target";
        r3 = r11.target;	 Catch:{ Throwable -> 0x0065 }
        r0.putExtra(r2, r3);	 Catch:{ Throwable -> 0x0065 }
    L_0x02ba:
        r10.startService(r0);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0040;
        */
    }

    public boolean isNetworkReachable(Context context) {
        return UtilityImpl.isNetworkConnected(context);
    }

    private static Intent a(Context context, int i) {
        if (i == 1 || UtilityImpl.getServiceEnabled(context)) {
            Intent intent = new Intent();
            intent.setAction(com.taobao.accs.common.Constants.ACTION_COMMAND);
            intent.setClassName(context.getPackageName(), com.taobao.accs.utl.a.channelService);
            intent.putExtra(JsInterface.KEY_APK_NAME, context.getPackageName());
            intent.putExtra(IntentUtil.AGOO_COMMAND, i);
            return intent;
        }
        ALog.e("ACCSManagerImpl", new StringBuilder("getIntent null command:").append(i).append(" serviceEnable:").append(UtilityImpl.getServiceEnabled(context)).toString(), new Object[0]);
        return null;
    }

    public void forceDisableService(Context context) {
        UtilityImpl.focusDisableService(context);
    }

    public void forceEnableService(Context context) {
        UtilityImpl.focusEnableService(context);
    }

    public void setMode(Context context, int i) {
        if (UtilityImpl.getMode(context) != i) {
            UtilityImpl.setMode(context, i);
            com.taobao.accs.client.b.a(context).j(com.taobao.accs.client.b.SP_BIND_FILE_NAME);
            com.taobao.accs.client.b.a(context).j(com.taobao.accs.client.b.SP_AGOO_BIND_FILE_NAME);
            UtilityImpl.killService(context);
        }
    }

    private void a(Context context, int i, String str, String str2) {
        Intent intent = new Intent(com.taobao.accs.common.Constants.ACTION_RECEIVE);
        intent.setPackage(context.getPackageName());
        intent.putExtra(IntentUtil.AGOO_COMMAND, i);
        intent.putExtra(com.taobao.accs.common.Constants.KEY_SERVICE_ID, str);
        intent.putExtra(com.taobao.accs.common.Constants.KEY_DATA_ID, str2);
        intent.putExtra(com.taobao.accs.common.Constants.KEY_ERROR_CODE, i == 2 ? Impl.STATUS_SUCCESS : XLErrorCode.OAUTH_PARAM_ERROR);
        e.a(context, intent);
    }

    public void setProxy(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences(com.taobao.accs.common.Constants.SP_FILE_NAME, 0).edit();
        if (!TextUtils.isEmpty(str)) {
            edit.putString(com.taobao.accs.common.Constants.KEY_PROXY_HOST, str);
        }
        edit.putInt(com.taobao.accs.common.Constants.KEY_PROXY_PORT, i);
        edit.apply();
    }

    public void startInAppConnection(Context context, String str, String str2, IAppReceiver iAppReceiver) {
        startInAppConnection(context, str, h.NAMESPACE, str2, iAppReceiver);
    }

    public void startInAppConnection(Context context, String str, String str2, String str3, IAppReceiver iAppReceiver) {
        GlobalClientInfo.getInstance(context).setAppReceiver(iAppReceiver);
        GlobalClientInfo.getInstance(context).setAppSecret(str2);
        if (UtilityImpl.isMainProcess(context)) {
            ALog.d("ACCSManagerImpl", new StringBuilder("startInAppConnection APPKEY:").append(str).toString(), new Object[0]);
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.equals(UtilityImpl.getAppkey(context), str)) {
                    UtilityImpl.setAppInfo(context, str, null, str3);
                }
                com.taobao.accs.net.a.a(context, 1).a();
                return;
            }
            return;
        }
        ALog.d("ACCSManagerImpl", "inapp only init in main process!", new Object[0]);
    }

    public void setLoginInfo(Context context, ILoginInfo iLoginInfo) {
        GlobalClientInfo.getInstance(context).setLoginInfoImpl(iLoginInfo);
    }

    public void clearLoginInfo(Context context) {
        GlobalClientInfo.getInstance(context).clearLoginInfoImpl();
    }

    public boolean cancel(Context context, String str) {
        return com.taobao.accs.net.a.a(context, 1).a(str);
    }

    public Map<String, Boolean> getChannelState() throws Exception {
        String userUnit = getUserUnit();
        String a = com.taobao.accs.net.a.a(GlobalClientInfo.getContext(), null);
        Map<String, Boolean> hashMap = new HashMap();
        hashMap.put(userUnit, Boolean.valueOf(false));
        hashMap.put(a, Boolean.valueOf(false));
        Session throwsException = SessionCenter.getInstance().getThrowsException(a, BuglyBroadcastRecevier.UPLOADLIMITED);
        Session throwsException2 = SessionCenter.getInstance().getThrowsException(userUnit, BuglyBroadcastRecevier.UPLOADLIMITED);
        if (throwsException != null) {
            hashMap.put(a, Boolean.valueOf(true));
        }
        if (throwsException2 != null) {
            hashMap.put(userUnit, Boolean.valueOf(true));
        }
        ALog.d("ACCSManagerImpl", new StringBuilder("getChannelState ").append(hashMap.toString()).toString(), new Object[0]);
        return hashMap;
    }

    public Map<String, Boolean> forceReConnectChannel() throws Exception {
        AccsSessionManager.getInstance().forceReCreateSession();
        return getChannelState();
    }

    public String getUserUnit() {
        Context context = GlobalClientInfo.getContext();
        if (context == null) {
            ALog.e("ACCSManagerImpl", "context is null", new Object[0]);
            return null;
        }
        String a = com.taobao.accs.net.a.a(context, StrategyCenter.getInstance().getUnitPrefix(GlobalClientInfo.getInstance(context).getUserId(), UtilityImpl.getDeviceId(context)));
        if (!ALog.isPrintLog(Level.D)) {
            return a;
        }
        ALog.d("ACCSManagerImpl", new StringBuilder("getUserUnit ").append(a).toString(), new Object[0]);
        return a;
    }

    public boolean isChannelError(int i) {
        return ErrorCode.isChannelError(i);
    }

    public void registerSerivce(Context context, String str, String str2) {
        GlobalClientInfo.getInstance(context).registerService(str, str2);
    }

    public void unRegisterSerivce(Context context, String str) {
        GlobalClientInfo.getInstance(context).unRegisterService(str);
    }

    public void registerDataListener(Context context, String str, AccsAbstractDataListener accsAbstractDataListener) {
        GlobalClientInfo.getInstance(context).registerListener(str, accsAbstractDataListener);
    }

    public void unRegisterDataListener(Context context, String str) {
        GlobalClientInfo.getInstance(context).unregisterListener(str);
    }

    public void sendBusinessAck(String str, String str2, String str3, short s, String str4, Map<ExtHeaderType, String> map) {
        com.taobao.accs.net.a.a(GlobalClientInfo.getContext(), 1).b(Message.buildPushAck(str, str2, str3, true, s, str4, map), true);
    }
}
