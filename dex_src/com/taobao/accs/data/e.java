package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.taobao.accs.IAppReceiver;
import com.taobao.accs.IAppReceiverV1;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.AccsDataListener;
import com.taobao.accs.base.TaoBaseService.ConnectInfo;
import com.taobao.accs.client.AccsConfig;
import com.taobao.accs.client.AccsConfig.ACCS_GROUP;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.Constants.Operate;
import com.taobao.accs.common.a;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.h;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public class e {
    private static Set<String> a;
    private static volatile e b;

    static {
        b = null;
    }

    public static e c() {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    if (AccsConfig.mGroup == ACCS_GROUP.ALIYUN) {
                        b = new a();
                    } else {
                        b = new e();
                    }
                }
            }
        }
        return b;
    }

    public static void a(Context context, Intent intent) {
        try {
            a.a().execute(new f(context, intent));
        } catch (Throwable th) {
            ALog.e("MsgDistribute", "distribMessage", th, new Object[0]);
            UTMini.getInstance().commitEvent(UT.EVENT_ID, "MsgToBuss8", new StringBuilder("distribMessage").append(th.toString()).toString(), Integer.valueOf(Constants.SDK_VERSION_CODE));
        }
    }

    private void b(Context context, Intent intent) {
        Object action = intent.getAction();
        if (ALog.isPrintLog(Level.D)) {
            ALog.d("MsgDistribute", new StringBuilder("action:").append(action).toString(), new Object[0]);
        }
        if (TextUtils.isEmpty(action)) {
            ALog.e("MsgDistribute", "action null", new Object[0]);
            UTMini.getInstance().commitEvent(UT.EVENT_ID, "MsgToBuss9", "action null", Integer.valueOf(Constants.SDK_VERSION_CODE));
            return;
        }
        String str = null;
        int i = 0;
        try {
            if (TextUtils.equals(action, Constants.ACTION_RECEIVE)) {
                i = intent.getIntExtra(IntentUtil.AGOO_COMMAND, -1);
                String stringExtra = intent.getStringExtra(Constants.KEY_USER_ID);
                int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                str = intent.getStringExtra(Constants.KEY_SERVICE_ID);
                String stringExtra2 = intent.getStringExtra(Constants.KEY_DATA_ID);
                if (intent.getPackage() == null) {
                    intent.setPackage(context.getPackageName());
                }
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i("MsgDistribute", new StringBuilder("command:").append(i).append(" serviceId:").append(str).append(" dataId:").append(stringExtra2).toString(), new Object[0]);
                }
                if (!a(context, intent, stringExtra2)) {
                    if (i < 0) {
                        ALog.e("MsgDistribute", new StringBuilder("command error:").append(i).toString(), new Object[0]);
                        return;
                    } else if (!a(i, str) && !b(context, intent, stringExtra2)) {
                        IAppReceiver appReceiver = GlobalClientInfo.getInstance(context).getAppReceiver();
                        if (!a(context, stringExtra2, intent, appReceiver)) {
                            a(context, intent, i, stringExtra, str, stringExtra2, appReceiver, intExtra);
                            if (TextUtils.isEmpty(str)) {
                                a(context, appReceiver, intent, i, intExtra);
                                return;
                            } else {
                                a(context, appReceiver, intent, str, stringExtra2, i, intExtra);
                                return;
                            }
                        }
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            ALog.e("MsgDistribute", new StringBuilder("action error ").append(action).toString(), new Object[0]);
            UTMini.getInstance().commitEvent(UT.EVENT_ID, "MsgToBuss10", action, Integer.valueOf(Constants.SDK_VERSION_CODE));
        } catch (Throwable th) {
            ALog.e("MsgDistribute", "distribMessage :", th, new Object[0]);
            b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, str, MessageService.MSG_DB_NOTIFY_REACHED, new StringBuilder("distribute error ").append(i).append(UtilityImpl.getStackMsg(th)).toString());
        }
    }

    protected boolean a(int i, String str) {
        if (!(i == 100 || GlobalClientInfo.AGOO_SERVICE_ID.equals(str))) {
            long usableSpace = UtilityImpl.getUsableSpace();
            if (usableSpace != -1 && usableSpace <= 5242880) {
                b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, str, MessageService.MSG_DB_NOTIFY_REACHED, new StringBuilder("space low ").append(usableSpace).toString());
                ALog.e("MsgDistribute", "user space low, don't distribute", "size", Long.valueOf(usableSpace));
                return true;
            }
        }
        return false;
    }

    protected boolean a(Context context, String str, Intent intent, IAppReceiver iAppReceiver) {
        if (iAppReceiver != null || UtilityImpl.isMainProcess(context)) {
            return false;
        }
        ALog.i("MsgDistribute", "start MsgDistributeService", Constants.KEY_DATA_ID, str);
        intent.setClassName(intent.getPackage(), b());
        context.startService(intent);
        return true;
    }

    private void a(Context context, Intent intent, int i, String str, String str2, String str3, IAppReceiver iAppReceiver, int i2) {
        if (iAppReceiver != null) {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (iAppReceiver instanceof IAppReceiverV1) {
                        ((IAppReceiverV1) iAppReceiver).onBindApp(i2, null);
                    } else {
                        iAppReceiver.onBindApp(i2);
                    }
                    if (i2 == 200) {
                        try {
                            ALog.i("MsgDistribute", "start election by bindapp....", new Object[0]);
                            a(context);
                            return;
                        } catch (Throwable th) {
                            ALog.e("MsgDistribute", new StringBuilder("start election is error,e=").append(th).toString(), new Object[0]);
                        }
                    }
                    return;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (i2 == 200) {
                        UtilityImpl.disableService(context);
                    }
                    iAppReceiver.onUnbindApp(i2);
                    return;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    iAppReceiver.onBindUser(str, i2);
                    return;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    iAppReceiver.onUnbindUser(i2);
                    return;
                case R.styleable.AppCompatTheme_buttonStyle:
                    if (TextUtils.isEmpty(str2)) {
                        iAppReceiver.onSendData(str3, i2);
                        return;
                    }
                    return;
                case R.styleable.AppCompatTheme_buttonStyleSmall:
                    if (TextUtils.isEmpty(str2)) {
                        ALog.d("MsgDistribute", "serviceId isEmpty", new Object[0]);
                        byte[] byteArrayExtra = intent.getByteArrayExtra(SocializeConstants.JSON_DATA);
                        if (byteArrayExtra != null) {
                            iAppReceiver.onData(str, str3, byteArrayExtra);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
        b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, str2, MessageService.MSG_DB_NOTIFY_REACHED, "appReceiver null return");
        ALog.e("MsgDistribute", "appReceiver null!", new Object[0]);
        UTMini.getInstance().commitEvent(UT.EVENT_ID, "MsgToBuss7", new StringBuilder("commandId=").append(i).toString(), new StringBuilder("serviceId=").append(str2).append(" errorCode=").append(i2).append(" dataId=").append(str3).toString(), Integer.valueOf(Constants.SDK_VERSION_CODE));
    }

    protected void a(Context context, IAppReceiver iAppReceiver, Intent intent, String str, String str2, int i, int i2) {
        CharSequence charSequence = null;
        if (iAppReceiver != null) {
            charSequence = iAppReceiver.getService(str);
        }
        String service;
        if (TextUtils.isEmpty(service)) {
            service = GlobalClientInfo.getInstance(context).getService(str);
        }
        if (TextUtils.isEmpty(service)) {
            AccsDataListener listener = GlobalClientInfo.getInstance(context).getListener(str);
            if (listener != null) {
                AccsAbstractDataListener.onReceiveData(context, intent, listener);
            } else {
                ALog.e("MsgDistribute", new StringBuilder("callback is null dataId:").append(str2).append(" serviceId\uff1a").append(str).append(" command:").append(i).toString(), new Object[0]);
                b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, str, MessageService.MSG_DB_NOTIFY_REACHED, "service is null");
            }
        } else {
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("MsgDistribute", new StringBuilder("to start service:").append(service).toString(), new Object[0]);
            }
            intent.setClassName(context, service);
            context.startService(intent);
        }
        UTMini.getInstance().commitEvent(UT.EVENT_ID, "MsgToBuss", new StringBuilder("commandId=").append(i).toString(), new StringBuilder("serviceId=").append(str).append(" errorCode=").append(i2).append(" dataId=").append(str2).toString(), Integer.valueOf(Constants.SDK_VERSION_CODE));
        b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_TO_BUSS, new StringBuilder("2commandId=").append(i).append("serviceId=").append(str).toString(), 0.0d);
    }

    protected void a(Context context, IAppReceiver iAppReceiver, Intent intent, int i, int i2) {
        Map allServices;
        if (iAppReceiver != null) {
            allServices = iAppReceiver.getAllServices();
        } else {
            allServices = null;
        }
        String stringExtra;
        String str;
        Object service;
        if (i == 103) {
            Serializable serializable;
            if (allServices != null) {
                for (String stringExtra2 : allServices.keySet()) {
                    if (h.NAMESPACE.equals(stringExtra2) || "windvane".equals(stringExtra2) || "motu-remote".equals(stringExtra2)) {
                        str = (String) allServices.get(stringExtra2);
                        if (TextUtils.isEmpty(str)) {
                            service = GlobalClientInfo.getInstance(context).getService(stringExtra2);
                        }
                        if (!TextUtils.isEmpty(service)) {
                            intent.setClassName(context, service);
                            context.startService(intent);
                        }
                    }
                }
            }
            boolean booleanExtra = intent.getBooleanExtra(Constants.KEY_CONNECT_AVAILABLE, false);
            stringExtra2 = intent.getStringExtra(com.taobao.accs.internal.b.ELECTION_KEY_HOST);
            String stringExtra3 = intent.getStringExtra(Constants.KEY_ERROR_DETAIL);
            boolean booleanExtra2 = intent.getBooleanExtra(Constants.KEY_TYPE_INAPP, false);
            boolean booleanExtra3 = intent.getBooleanExtra(Constants.KEY_CENTER_HOST, false);
            if (TextUtils.isEmpty(stringExtra2)) {
                serializable = null;
            } else {
                if (booleanExtra) {
                    serializable = new ConnectInfo(stringExtra2, booleanExtra2, booleanExtra3);
                } else {
                    serializable = new ConnectInfo(stringExtra2, booleanExtra2, booleanExtra3, i2, stringExtra3);
                }
                serializable.connected = booleanExtra;
            }
            if (serializable != null) {
                Intent intent2 = new Intent(Constants.ACTION_CONNECT_INFO);
                intent2.setPackage(context.getPackageName());
                intent2.putExtra(Constants.KEY_CONNECT_INFO, serializable);
                context.sendBroadcast(intent2);
                return;
            }
            ALog.e("MsgDistribute", "connect info null, host empty", new Object[0]);
        } else if (i != 104) {
            ALog.i("MsgDistribute", new StringBuilder("distribMessage serviceId is null!! command:").append(i).toString(), new Object[0]);
        } else if (allServices != null) {
            for (String stringExtra22 : allServices.keySet()) {
                str = (String) allServices.get(stringExtra22);
                if (TextUtils.isEmpty(str)) {
                    service = GlobalClientInfo.getInstance(context).getService(stringExtra22);
                }
                if (!TextUtils.isEmpty(service)) {
                    intent.setClassName(context, service);
                    context.startService(intent);
                }
            }
        }
    }

    protected String a() {
        return com.taobao.accs.utl.a.channelService;
    }

    protected String b() {
        return com.taobao.accs.utl.a.msgService;
    }

    private void a(Context context) {
        if (h.c()) {
            Intent intent = new Intent(com.taobao.accs.a.a.b());
            intent.putExtra("operate", Operate.TRY_ELECTION);
            intent.setPackage(context.getPackageName());
            intent.setClassName(context.getPackageName(), a());
            context.startService(intent);
        }
    }

    private boolean a(Context context, Intent intent, String str) {
        boolean booleanExtra = intent.getBooleanExtra("routingAck", false);
        boolean booleanExtra2 = intent.getBooleanExtra("routingMsg", false);
        if (booleanExtra) {
            ALog.i("MsgDistribute", "recieve routiong ack", Constants.KEY_DATA_ID, str);
            if (a != null) {
                a.remove(str);
            }
            b.a(h.NAMESPACE, BaseMonitor.ALARM_MSG_ROUTING_RATE, com.umeng.a.d);
            booleanExtra = true;
        } else {
            booleanExtra = false;
        }
        if (booleanExtra2) {
            try {
                String stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
                ALog.i("MsgDistribute", "send routiong ack", Constants.KEY_DATA_ID, str, "to pkg", stringExtra);
                Intent intent2 = new Intent(Constants.ACTION_COMMAND);
                intent2.putExtra(IntentUtil.AGOO_COMMAND, R.styleable.AppCompatTheme_ratingBarStyle);
                intent2.setClassName(stringExtra, com.taobao.accs.utl.a.channelService);
                intent2.putExtra("routingAck", true);
                intent2.putExtra(JsInterface.KEY_APK_NAME, stringExtra);
                intent2.putExtra(Constants.KEY_DATA_ID, str);
                context.startService(intent2);
            } catch (Throwable th) {
                ALog.e("MsgDistribute", "send routing ack", th, new Object[0]);
            }
        }
        return booleanExtra;
    }

    private boolean b(Context context, Intent intent, String str) {
        if (context.getPackageName().equals(intent.getPackage())) {
            return false;
        }
        try {
            ALog.i("MsgDistribute", "start MsgDistributeService", "receive pkg", context.getPackageName(), "target pkg", intent.getPackage());
            intent.setClassName(intent.getPackage(), com.taobao.accs.utl.a.msgService);
            intent.putExtra("routingMsg", true);
            intent.putExtra(JsInterface.KEY_APK_NAME, context.getPackageName());
            context.startService(intent);
            if (a == null) {
                a = new HashSet();
            }
            a.add(str);
            a.a(new g(this, str, context, intent), 10, TimeUnit.SECONDS);
            return true;
        } catch (Throwable th) {
            b.a(h.NAMESPACE, BaseMonitor.ALARM_MSG_ROUTING_RATE, com.umeng.a.d, "exception", th.toString());
            ALog.e("MsgDistribute", "routing msg error, try election", Constants.KEY_DATA_ID, str, th);
            a(context);
            return true;
        }
    }
}
