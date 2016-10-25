package org.android.agoo.control;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.client.a;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.h;
import com.taobao.agoo.TaobaoConstants;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.agoo.common.MsgDO;
import org.android.agoo.common.b;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.message.MessageService;

// compiled from: Taobao
public abstract class BaseIntentService extends IntentService {
    private static final String TAG = "BaseIntentService";
    private static final String msgStatus = "4";
    private AgooFactory agooFactory;
    private Context mContext;
    private MessageService messageService;
    private NotifManager notifyManager;

    public abstract void onError(Context context, String str);

    public abstract void onMessage(Context context, Intent intent);

    public abstract void onRegistered(Context context, String str);

    public BaseIntentService() {
        super("AgooIntentService");
        this.mContext = null;
    }

    public void onUserCommand(Context context, Intent intent) {
    }

    public void onCreate() {
        a.g.incrementAndGet();
        this.notifyManager = new NotifManager();
        this.notifyManager.init(getApplicationContext());
        this.messageService = new MessageService();
        this.messageService.a(getApplicationContext());
        this.agooFactory = new AgooFactory();
        this.agooFactory.init(getApplicationContext(), this.notifyManager, this.messageService);
        super.onCreate();
    }

    protected void onHandleIntent(Intent intent) {
        this.mContext = getApplicationContext();
        if (intent != null) {
            CharSequence action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                CharSequence agooCommand = IntentUtil.getAgooCommand(this.mContext);
                CharSequence thirdPushCommand = IntentUtil.getThirdPushCommand(this.mContext);
                ALog.i(TAG, new StringBuilder("onHandleIntent,action=").append(action).append(",agooCommand=").append(agooCommand).append(",mipushCommand=").append(thirdPushCommand).toString(), new Object[0]);
                try {
                    if (TextUtils.equals(action, agooCommand)) {
                        action = intent.getStringExtra(IntentUtil.AGOO_COMMAND);
                        ALog.d(TAG, new StringBuilder("actionCommand --->[").append(action).append("]").toString(), new Object[0]);
                        if (TextUtils.equals(action, "message_readed") || TextUtils.equals(action, "message_deleted")) {
                            onUserCommand(this.mContext, intent);
                        }
                    } else if (TextUtils.equals(action, thirdPushCommand)) {
                        action = intent.getStringExtra(IntentUtil.AGOO_COMMAND);
                        String stringExtra = intent.getStringExtra("thirdPushId");
                        if (TextUtils.equals(action, "mipushId_report")) {
                            this.notifyManager.reportThirdPushToken(stringExtra, "MI_TOKEN");
                        } else if (TextUtils.equals(action, "huaweipushId_report")) {
                            ALog.d(TAG, new StringBuilder("HW_TOKEN report begin..regid=").append(stringExtra).toString(), new Object[0]);
                            this.notifyManager.reportThirdPushToken(stringExtra, "HW_TOKEN");
                        } else if (TextUtils.equals(action, "gcmpushId_report")) {
                            ALog.i(TAG, new StringBuilder("GCM_TOKEN report begin..regid=").append(stringExtra).toString(), new Object[0]);
                            this.notifyManager.reportThirdPushToken(stringExtra, TaobaoConstants.MESSAGE_SYSTEM_SOURCE_GCM);
                        }
                    } else if (action.equals("org.agoo.android.intent.action.RECEIVE")) {
                        handleRemoteMessage(this.mContext, intent);
                    } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                        handleRemovePackage(this.mContext, intent);
                    } else if (TextUtils.equals(action, "org.agoo.android.intent.action.REPORT") || TextUtils.equals(action, "android.net.conn.CONNECTIVITY_CHANGE") || TextUtils.equals(action, "android.intent.action.BOOT_COMPLETED") || TextUtils.equals(action, "android.intent.action.PACKAGE_ADDED") || TextUtils.equals(action, "android.intent.action.PACKAGE_REPLACED") || TextUtils.equals(action, "android.intent.action.USER_PRESENT") || TextUtils.equals(action, "android.intent.action.ACTION_POWER_CONNECTED") || TextUtils.equals(action, "android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        try {
                            ALog.i(TAG, new StringBuilder("is report cache msg,Config.isReportCacheMsg(mContext)=").append(b.a(this.mContext)).toString(), new Object[0]);
                            if (b.a(this.mContext) && com.taobao.accs.utl.a.c(this.mContext)) {
                                b.b(this.mContext);
                                this.agooFactory.reportCacheMsg();
                                this.messageService.a();
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            if (ALog.isPrintLog(Level.I)) {
                                ALog.i(TAG, new StringBuilder("is clear all msg=").append(b.b(this.mContext, currentTimeMillis)).toString(), new Object[0]);
                            }
                            if (b.b(this.mContext, currentTimeMillis)) {
                                b.a(this.mContext, currentTimeMillis);
                                this.messageService.a();
                            }
                        } catch (Throwable th) {
                            ALog.e(TAG, "reportCacheMsg", th, new Object[0]);
                        }
                    }
                    a.g.incrementAndGet();
                } catch (Throwable th2) {
                    if (ALog.isPrintLog(Level.E)) {
                        ALog.e(TAG, "onHandleIntent deal error", th2, new Object[0]);
                    }
                    a.g.incrementAndGet();
                }
            }
        }
    }

    private final void handleRemovePackage(Context context, Intent intent) {
        if (intent != null && context != null) {
            String str = null;
            Uri data = intent.getData();
            if (data != null) {
                str = data.getSchemeSpecificPart();
            }
            if (!TextUtils.isEmpty(str)) {
                boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d(TAG, new StringBuilder("handleRemovePackage---->[replacing:").append(booleanExtra).append("],uninstallPack=").append(str).toString(), new Object[0]);
                }
                if (!booleanExtra) {
                    this.notifyManager.doUninstall(str, booleanExtra);
                }
            }
        }
    }

    private final void handleRemoteMessage(Context context, Intent intent) {
        try {
            String stringExtra;
            String str;
            int parseInt;
            CharSequence stringExtra2;
            String stringExtra3 = intent.getStringExtra(SocializeConstants.WEIBO_ID);
            String stringExtra4 = intent.getStringExtra("body");
            String stringExtra5 = intent.getStringExtra(JsInterface.FUNPLAY_AD_TRPE);
            String stringExtra6 = intent.getStringExtra("message_source");
            String stringExtra7 = intent.getStringExtra("report");
            String stringExtra8 = intent.getStringExtra("encrypted");
            Object obj = null;
            String str2 = null;
            try {
                ExtraInfo extraInfo;
                Context context2 = context;
                getTrace(context2, Long.valueOf(intent.getLongExtra("trace", -1)).longValue());
                Bundle bundleExtra = intent.getBundleExtra("msg_agoo_bundle");
                if (bundleExtra != null) {
                    extraInfo = (ExtraInfo) bundleExtra.getSerializable("accs_extra");
                }
                str2 = intent.getStringExtra(SocialConstants.PARAM_SOURCE);
                if (TextUtils.isEmpty(str2)) {
                    str2 = "oldsdk";
                }
                stringExtra = intent.getStringExtra("fromAppkey");
                ExtraInfo extraInfo2 = extraInfo;
            } catch (Throwable th) {
                ALog.e(TAG, new StringBuilder("_trace,t=").append(th).toString(), new Object[0]);
                stringExtra = null;
                extraInfo2 = null;
            }
            if (ALog.isPrintLog(Level.I)) {
                ALog.i(TAG, "handleRemoteMessage", Constants.SHARED_MESSAGE_ID_FILE, stringExtra4, SocialConstants.PARAM_SOURCE, stringExtra6, "msgId", stringExtra3, MsgConstant.KEY_UTDID, com.taobao.accs.utl.a.b(context), "fromPkg", str2, "fromAppkey", stringExtra);
            }
            MsgDO msgDO = new MsgDO();
            msgDO.msgIds = stringExtra3;
            msgDO.messageSource = stringExtra6;
            msgDO.msgStatus = msgStatus;
            msgDO.reportStr = stringExtra7;
            msgDO.fromPkg = str2;
            msgDO.fromAppkey = stringExtra;
            msgDO.isStartProc = a.d();
            if (!TextUtils.isEmpty(stringExtra4)) {
                if (Integer.toString(0).equals(stringExtra8)) {
                    ALog.i(TAG, "normal msg, onMessage() will be excuted", new Object[0]);
                    str = stringExtra4;
                } else if (Integer.toString(XZBDevice.DOWNLOAD_LIST_ALL).equals(stringExtra8)) {
                    if (!intent.getBooleanExtra("has_decrypted", false)) {
                        ALog.i(TAG, "message is encrypted, attemp to decrypt msg", new Object[0]);
                        str = AgooFactory.parseEncryptedMsg(stringExtra4);
                    }
                } else {
                    ALog.e(TAG, "msg encrypted flag not exist~~", new Object[0]);
                    try {
                        msgDO.errorCode = com.tencent.connect.common.Constants.VIA_REPORT_TYPE_DATALINE;
                        this.notifyManager.report(msgDO, extraInfo2);
                        return;
                    } catch (Throwable th2) {
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    intent.putExtra("body", str);
                    try {
                        this.notifyManager.report(msgDO, extraInfo2);
                        com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE_ID, msgDO.msgIds, 0.0d);
                        com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, "arrive", 0.0d);
                    } catch (Throwable th3) {
                        ALog.e(TAG, new StringBuilder("report message Throwable--->t=").append(th3.toString()).toString(), new Object[0]);
                    }
                    if (this.messageService.a(stringExtra3)) {
                        if (ALog.isPrintLog(Level.I)) {
                            ALog.i(TAG, new StringBuilder("handleMessage--->[").append(str).append("],[").append(stringExtra6).append("]").toString(), new Object[0]);
                        }
                        CharSequence stringExtra9 = intent.getStringExtra("duplicate");
                        if (!TextUtils.isEmpty(stringExtra9) && TextUtils.equals(stringExtra9, MessageService.MSG_DB_NOTIFY_REACHED)) {
                            if (this.messageService.a(stringExtra3, str.hashCode())) {
                                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_dupbody", 0.0d);
                                return;
                            }
                        }
                        try {
                            parseInt = Integer.parseInt(intent.getStringExtra("notify"));
                        } catch (Throwable th4) {
                            parseInt = -1;
                        }
                        stringExtra = com.umeng.a.d;
                        try {
                            stringExtra2 = intent.getStringExtra("has_test");
                            if (TextUtils.isEmpty(stringExtra2) && TextUtils.equals(stringExtra2, MessageService.MSG_DB_NOTIFY_REACHED)) {
                                this.messageService.a(stringExtra3, str, stringExtra5, parseInt);
                                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_test", 0.0d);
                                return;
                            }
                            stringExtra = getClass().getName();
                            this.messageService.a(stringExtra3, str, stringExtra5, parseInt);
                            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE_REAL_ID, msgDO.msgIds, 0.0d);
                            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, new StringBuilder("arrive_real_").append(stringExtra).toString(), 0.0d);
                            onMessage(context, intent);
                            return;
                        } catch (Throwable th5) {
                        }
                    } else {
                        if (ALog.isPrintLog(Level.I)) {
                            ALog.i(TAG, new StringBuilder("handleRemoteMessage hasMessageDuplicate,messageId=").append(stringExtra3).append(",utdid=").append(com.taobao.accs.utl.a.b(context)).toString(), new Object[0]);
                        }
                        com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_dup", 0.0d);
                        return;
                    }
                }
                try {
                    msgDO.errorCode = com.tencent.connect.common.Constants.VIA_REPORT_TYPE_QQFAVORITES;
                    this.notifyManager.report(msgDO, extraInfo2);
                } catch (Throwable th6) {
                }
                ALog.e(TAG, "handleMessage--->[null]", new Object[0]);
            }
            str = stringExtra4;
            if (TextUtils.isEmpty(str)) {
                intent.putExtra("body", str);
                this.notifyManager.report(msgDO, extraInfo2);
                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE_ID, msgDO.msgIds, 0.0d);
                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, "arrive", 0.0d);
                if (this.messageService.a(stringExtra3)) {
                    if (ALog.isPrintLog(Level.I)) {
                        ALog.i(TAG, new StringBuilder("handleMessage--->[").append(str).append("],[").append(stringExtra6).append("]").toString(), new Object[0]);
                    }
                    try {
                        CharSequence stringExtra92 = intent.getStringExtra("duplicate");
                        if (this.messageService.a(stringExtra3, str.hashCode())) {
                            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_dupbody", 0.0d);
                            return;
                        }
                    } catch (Throwable th32) {
                        if (ALog.isPrintLog(Level.E)) {
                            ALog.e(TAG, new StringBuilder("hasMessageDuplicate message,e=").append(th32.toString()).toString(), new Object[0]);
                        }
                    }
                    parseInt = Integer.parseInt(intent.getStringExtra("notify"));
                    stringExtra = com.umeng.a.d;
                    stringExtra2 = intent.getStringExtra("has_test");
                    if (TextUtils.isEmpty(stringExtra2)) {
                    }
                    stringExtra = getClass().getName();
                    this.messageService.a(stringExtra3, str, stringExtra5, parseInt);
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE_REAL_ID, msgDO.msgIds, 0.0d);
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, new StringBuilder("arrive_real_").append(stringExtra).toString(), 0.0d);
                    onMessage(context, intent);
                    return;
                }
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i(TAG, new StringBuilder("handleRemoteMessage hasMessageDuplicate,messageId=").append(stringExtra3).append(",utdid=").append(com.taobao.accs.utl.a.b(context)).toString(), new Object[0]);
                }
                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, "arrive_dup", 0.0d);
                return;
            }
            msgDO.errorCode = com.tencent.connect.common.Constants.VIA_REPORT_TYPE_QQFAVORITES;
            this.notifyManager.report(msgDO, extraInfo2);
            ALog.e(TAG, "handleMessage--->[null]", new Object[0]);
        } catch (Throwable th322) {
            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_AGOO_ARRIVE, new StringBuilder("arrive_exception").append(th322.toString()).toString(), 0.0d);
        }
    }

    private final String getTrace(Context context, long j) {
        String str;
        String str2 = null;
        if (TextUtils.isEmpty(null)) {
            str = "unknow";
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(null)) {
            str2 = "unknow";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Constants.SP_KEY_APPKEY);
        stringBuffer.append("|");
        stringBuffer.append(j);
        stringBuffer.append("|");
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append("|");
        stringBuffer.append(str);
        stringBuffer.append("|");
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static final void runIntentInService(Context context, Intent intent, String str) {
        try {
            intent.setClassName(context, str);
            context.startService(intent);
        } catch (Throwable th) {
            ALog.w(TAG, "runIntentInService", th, new Object[0]);
        }
    }
}
