package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.mediav.ads.sdk.adcore.HttpCacher;
import com.taobao.accs.data.Message;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.android.f;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.service.v;
import com.xiaomi.push.service.z;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.i;
import com.xiaomi.xmpush.thrift.m;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.s;
import com.xiaomi.xmpush.thrift.x;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public abstract class MiPushClient {
    public static final String ACCEPT_TIME_SEPARATOR = ",";
    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_SET_ACCEPT_TIME = "accept-time";
    public static final String COMMAND_SET_ACCOUNT = "set-account";
    public static final String COMMAND_SET_ALIAS = "set-alias";
    public static final String COMMAND_SUBSCRIBE_TOPIC = "subscribe-topic";
    public static final String COMMAND_UNSET_ACCOUNT = "unset-account";
    public static final String COMMAND_UNSET_ALIAS = "unset-alias";
    public static final String COMMAND_UNSUBSCRIBE_TOPIC = "unsubscibe-topic";
    public static final String PREF_EXTRA = "mipush_extra";
    private static boolean awakeService;
    private static Context sContext;
    private static long sCurMsgId;

    @Deprecated
    public static abstract class MiPushClientCallback {
        private String category;

        protected String getCategory() {
            return this.category;
        }

        public void onCommandResult(String str, long j, String str2, List<String> list) {
        }

        public void onInitializeResult(long j, String str, String str2) {
        }

        public void onReceiveMessage(MiPushMessage miPushMessage) {
        }

        public void onReceiveMessage(String str, String str2, String str3, boolean z) {
        }

        public void onSubscribeResult(long j, String str, String str2) {
        }

        public void onUnsubscribeResult(long j, String str, String str2) {
        }

        protected void setCategory(String str) {
            this.category = str;
        }
    }

    static {
        awakeService = true;
        sCurMsgId = System.currentTimeMillis();
    }

    private static boolean acceptTimeSet(Context context, String str, String str2) {
        return TextUtils.equals(context.getSharedPreferences(PREF_EXTRA, 0).getString("accept_time", a.d), str + ACCEPT_TIME_SEPARATOR + str2);
    }

    public static long accountSetTime(Context context, String str) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getLong(new StringBuilder("account_").append(str).toString(), -1);
    }

    static synchronized void addAcceptTime(Context context, String str, String str2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putString("accept_time", str + ACCEPT_TIME_SEPARATOR + str2).commit();
        }
    }

    static synchronized void addAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong(new StringBuilder("account_").append(str).toString(), System.currentTimeMillis()).commit();
        }
    }

    static synchronized void addAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong(new StringBuilder("alias_").append(str).toString(), System.currentTimeMillis()).commit();
        }
    }

    private static void addPullNotificationTime(Context context) {
        context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong("last_pull_notification", System.currentTimeMillis()).commit();
    }

    private static void addRegRequestTime(Context context) {
        context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong("last_reg_request", System.currentTimeMillis()).commit();
    }

    static synchronized void addTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().putLong(new StringBuilder("topic_").append(str).toString(), System.currentTimeMillis()).commit();
        }
    }

    public static long aliasSetTime(Context context, String str) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getLong(new StringBuilder("alias_").append(str).toString(), -1);
    }

    public static void awakeApps(Context context, String[] strArr) {
        new Thread(new f(strArr, context)).start();
    }

    private static void awakePushServiceByPackageInfo(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                    try {
                        Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                        Intent intent = new Intent();
                        intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                        intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                        context.startService(intent);
                        return;
                    } catch (Throwable th) {
                    }
                }
            }
        }
    }

    private static void awakePushServices(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_EXTRA, 0);
        if (System.currentTimeMillis() - 600000 >= sharedPreferences.getLong("wake_up", 0)) {
            sharedPreferences.edit().putLong("wake_up", System.currentTimeMillis()).commit();
            if (!shouldUseMIUIPush(context) && 1 == a.a(context).m()) {
                new Thread(new e(context)).start();
            }
        }
    }

    public static void checkManifest(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4100);
            checkReceivers(context);
            checkServices(context, packageInfo);
            checkPermissions(context, packageInfo);
        } catch (Throwable e) {
            b.a(e);
        }
    }

    private static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(new StringBuilder("param ").append(str).append(" is not nullable").toString());
        }
    }

    private static void checkPermissions(Context context, PackageInfo packageInfo) {
        int i;
        Set hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(new String[]{MsgConstant.PERMISSION_INTERNET, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, context.getPackageName() + ".permission.MIPUSH_RECEIVE", MsgConstant.PERMISSION_ACCESS_WIFI_STATE, MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_GET_TASKS, "android.permission.VIBRATE"}));
        if (packageInfo.permissions != null) {
            PermissionInfo[] permissionInfoArr = packageInfo.permissions;
            int length = permissionInfoArr.length;
            for (i = 0; i < length; i++) {
                if (r4.equals(permissionInfoArr[i].name)) {
                    i = 1;
                    break;
                }
            }
        }
        i = 0;
        if (i == 0) {
            throw new a(String.format("<permission android:name=\"%1$s\" /> is undefined.", new Object[]{r4}), null);
        }
        if (packageInfo.requestedPermissions != null) {
            String[] strArr = packageInfo.requestedPermissions;
            int length2 = strArr.length;
            for (i = 0; i < length2; i++) {
                CharSequence charSequence = strArr[i];
                if (!TextUtils.isEmpty(charSequence) && hashSet.contains(charSequence)) {
                    hashSet.remove(charSequence);
                    if (hashSet.isEmpty()) {
                        break;
                    }
                }
            }
        }
        if (!hashSet.isEmpty()) {
            throw new a(String.format("<use-permission android:name=\"%1$s\" /> is missing.", new Object[]{hashSet.iterator().next()}), null);
        }
    }

    private static void checkReceivers(Context context) {
        int i;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Intent intent = new Intent("android.net.conn.CONNECTIVITY_CHANGE");
        intent.setPackage(packageName);
        findAndCheckReceiverInfo(packageManager, intent, NetworkStatusReceiver.class, new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(true)});
        intent = new Intent(z.o);
        intent.setPackage(packageName);
        try {
            findAndCheckReceiverInfo(packageManager, intent, Class.forName("com.xiaomi.push.service.receivers.PingReceiver"), new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(false)});
        } catch (Throwable e) {
            b.a(e);
        }
        intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(packageName);
        int i2 = 0;
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, Message.FLAG_REQ_BIT1)) {
            boolean z;
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null) {
                try {
                } catch (Throwable e2) {
                    b.a(e2);
                }
                if (!TextUtils.isEmpty(activityInfo.name) && PushMessageReceiver.class.isAssignableFrom(Class.forName(activityInfo.name)) && activityInfo.enabled) {
                    z = true;
                    if (z) {
                        break;
                    }
                    boolean z2 = z;
                }
            }
            i = 0;
            if (z) {
                break;
            }
            boolean z22 = z;
        }
        i = i2;
        if (!z) {
            throw new a("Receiver: none of the subclasses of PushMessageReceiver is enabled or defined.", null);
        }
    }

    private static void checkServices(Context context, PackageInfo packageInfo) {
        Map hashMap = new HashMap();
        hashMap.put("com.xiaomi.push.service.XMPushService", new Object{Boolean.valueOf(true), Boolean.valueOf(false), a.d});
        hashMap.put(PushMessageHandler.class.getCanonicalName(), new Object{Boolean.valueOf(true), Boolean.valueOf(true), a.d});
        hashMap.put(MessageHandleService.class.getCanonicalName(), new Object{Boolean.valueOf(true), Boolean.valueOf(false), a.d});
        hashMap.put("com.xiaomi.push.service.XMJobService", new Object{Boolean.valueOf(true), Boolean.valueOf(false), "android.permission.BIND_JOB_SERVICE"});
        if (packageInfo.services != null) {
            ServiceInfo[] serviceInfoArr = packageInfo.services;
            int length = serviceInfoArr.length;
            for (int i = 0; i < length; i++) {
                PackageItemInfo packageItemInfo = serviceInfoArr[i];
                if (!TextUtils.isEmpty(packageItemInfo.name) && hashMap.containsKey(packageItemInfo.name)) {
                    Object[] objArr = (Object[]) hashMap.remove(packageItemInfo.name);
                    boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
                    boolean booleanValue2 = ((Boolean) objArr[1]).booleanValue();
                    String str = (String) objArr[2];
                    if (booleanValue != packageItemInfo.enabled) {
                        throw new a(String.format("Wrong attribute: %n    <service android:name=\"%1$s\" .../> android:enabled should be %<b.", new Object[]{packageItemInfo.name, Boolean.valueOf(booleanValue)}), packageItemInfo);
                    } else if (booleanValue2 != packageItemInfo.exported) {
                        throw new a(String.format("Wrong attribute: %n    <service android:name=\"%1$s\" .../> android:exported should be %<b.", new Object[]{packageItemInfo.name, Boolean.valueOf(booleanValue2)}), packageItemInfo);
                    } else if (TextUtils.isEmpty(str) || TextUtils.equals(str, packageItemInfo.permission)) {
                        if (hashMap.isEmpty()) {
                            break;
                        }
                    } else {
                        throw new a(String.format("Wrong attribute: %n    <service android:name=\"%1$s\" .../> android:permission should be \"%2$s\".", new Object[]{packageItemInfo.name, str}), packageItemInfo);
                    }
                }
            }
        }
        if (!hashMap.isEmpty()) {
            throw new a(String.format("<service android:name=\"%1$s\" /> is missing or disabled.", new Object[]{hashMap.keySet().iterator().next()}), null);
        }
    }

    protected static void clearExtras(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_EXTRA, 0);
        long j = sharedPreferences.getLong("wake_up", 0);
        Editor edit = sharedPreferences.edit();
        edit.clear();
        if (j > 0) {
            edit.putLong("wake_up", j);
        }
        edit.commit();
    }

    public static void clearLocalNotificationType(Context context) {
        j.a(context).e();
    }

    public static void clearNotification(Context context) {
        j.a(context).a(-1);
    }

    public static void clearNotification(Context context, int i) {
        j.a(context).a(i);
    }

    private static void findAndCheckReceiverInfo(PackageManager packageManager, Intent intent, Class<?> cls, Boolean[] boolArr) {
        int i;
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, Message.FLAG_REQ_BIT1)) {
            PackageItemInfo packageItemInfo = resolveInfo.activityInfo;
            if (packageItemInfo != null && cls.getCanonicalName().equals(packageItemInfo.name)) {
                if (boolArr[0].booleanValue() != packageItemInfo.enabled) {
                    throw new a(String.format("Wrong attribute: %n    <receiver android:name=\"%1$s\" .../> android:enabled should be %<b.", new Object[]{packageItemInfo.name, boolArr[0]}), packageItemInfo);
                } else if (boolArr[1].booleanValue() != packageItemInfo.exported) {
                    throw new a(String.format("Wrong attribute: %n    <receiver android:name=\"%1$s\" .../> android:exported should be %<b.", new Object[]{packageItemInfo.name, boolArr[1]}), packageItemInfo);
                } else {
                    i = 1;
                    if (i == 0) {
                        throw new a(String.format("<receiver android:name=\"%1$s\" /> is missing or disabled.", new Object[]{cls.getCanonicalName()}), null);
                    }
                }
            }
        }
        i = 0;
        if (i == 0) {
            throw new a(String.format("<receiver android:name=\"%1$s\" /> is missing or disabled.", new Object[]{cls.getCanonicalName()}), null);
        }
    }

    protected static synchronized String generatePacketID() {
        String str;
        synchronized (MiPushClient.class) {
            str = d.a(XZBDevice.DOWNLOAD_LIST_ALL) + sCurMsgId;
            sCurMsgId++;
        }
        return str;
    }

    public static List<String> getAllAlias(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences(PREF_EXTRA, 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring(R.styleable.Toolbar_contentInsetEnd));
            }
        }
        return arrayList;
    }

    public static List<String> getAllTopic(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences(PREF_EXTRA, 0).getAll().keySet()) {
            if (str.startsWith("topic_") && !str.contains("**ALL**")) {
                arrayList.add(str.substring(R.styleable.Toolbar_contentInsetEnd));
            }
        }
        return arrayList;
    }

    public static List<String> getAllUserAccount(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences(PREF_EXTRA, 0).getAll().keySet()) {
            if (str.startsWith("account_")) {
                arrayList.add(str.substring(XZBDevice.Wait));
            }
        }
        return arrayList;
    }

    private static boolean getDefaultSwitch() {
        return !e.a();
    }

    public static String getRegId(Context context) {
        return a.a(context).i() ? a.a(context).e() : null;
    }

    @Deprecated
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        boolean z = false;
        checkNotNull(context, "context");
        checkNotNull(str, "appID");
        checkNotNull(str2, "appToken");
        try {
            Context applicationContext = context.getApplicationContext();
            sContext = applicationContext;
            if (applicationContext == null) {
                sContext = context;
            }
            if (miPushClientCallback != null) {
                PushMessageHandler.a(miPushClientCallback);
            }
            if (a.a(sContext).m() != Constants.a()) {
                z = true;
            }
            if (z || shouldSendRegRequest(sContext)) {
                if (z || !a.a(sContext).a(str, str2) || a.a(sContext).n()) {
                    String a = d.a(R.styleable.Toolbar_contentInsetEnd);
                    a.a(sContext).h();
                    a.a(sContext).a(Constants.a());
                    a.a(sContext).a(str, str2, a);
                    clearExtras(sContext);
                    s sVar = new s();
                    sVar.a(generatePacketID());
                    sVar.b(str);
                    sVar.e(str2);
                    sVar.d(context.getPackageName());
                    sVar.f(a);
                    sVar.c(com.xiaomi.channel.commonutils.android.b.a(context, context.getPackageName()));
                    sVar.b(com.xiaomi.channel.commonutils.android.b.b(context, context.getPackageName()));
                    sVar.g("3_0_3");
                    sVar.a(30003);
                    sVar.h(com.xiaomi.push.service.d.b(sContext));
                    a = com.xiaomi.push.service.d.d(sContext);
                    if (!TextUtils.isEmpty(a)) {
                        if (!e.a()) {
                            sVar.i(a);
                        }
                        sVar.k(d.a(a));
                    }
                    sVar.j(com.xiaomi.push.service.d.a());
                    int b = com.xiaomi.push.service.d.b();
                    if (b >= 0) {
                        sVar.c(b);
                    }
                    j.a(sContext).a(sVar, z);
                } else {
                    if (1 == PushMessageHelper.getPushMode(context)) {
                        checkNotNull(miPushClientCallback, com.alipay.sdk.authjs.a.c);
                        miPushClientCallback.onInitializeResult(0, null, a.a(context).e());
                    } else {
                        List arrayList = new ArrayList();
                        arrayList.add(a.a(context).e());
                        PushMessageHelper.sendCommandMessageBroadcast(sContext, PushMessageHelper.generateCommandMessage(COMMAND_REGISTER, arrayList, 0, null, null));
                    }
                    j.a(context).a();
                    if (a.a(sContext).a()) {
                        org.apache.thrift.b rVar = new r();
                        rVar.b(a.a(context).c());
                        rVar.c("client_info_update");
                        rVar.a(generatePacketID());
                        rVar.h = new HashMap();
                        rVar.h.put(org.android.agoo.common.b.PROPERTY_APP_VERSION, com.xiaomi.channel.commonutils.android.b.a(sContext, sContext.getPackageName()));
                        rVar.h.put("app_version_code", Integer.toString(com.xiaomi.channel.commonutils.android.b.b(sContext, sContext.getPackageName())));
                        rVar.h.put("push_sdk_vn", "3_0_3");
                        rVar.h.put("push_sdk_vc", Integer.toString(30003));
                        CharSequence g = a.a(sContext).g();
                        if (!TextUtils.isEmpty(g)) {
                            rVar.h.put("deviceid", g);
                        }
                        j.a(context).a(rVar, com.xiaomi.xmpush.thrift.a.i, false, null);
                    }
                    if (!f.a(sContext, "update_devId", false)) {
                        updateIMEI();
                        f.b(sContext, "update_devId", true);
                    }
                    if (shouldUseMIUIPush(sContext) && shouldPullNotification(sContext)) {
                        org.apache.thrift.b rVar2 = new r();
                        rVar2.b(a.a(sContext).c());
                        rVar2.c("pull");
                        rVar2.a(generatePacketID());
                        rVar2.a(false);
                        j.a(sContext).a(rVar2, com.xiaomi.xmpush.thrift.a.i, false, null, false);
                        addPullNotificationTime(sContext);
                    }
                }
                if (awakeService) {
                    awakePushServices(sContext);
                }
                addRegRequestTime(sContext);
                scheduleOcVersionCheckJob();
                loadPlugin();
                return;
            }
            j.a(context).a();
            b.a("Could not send  register message within 5s repeatly .");
        } catch (Throwable th) {
            b.a(th);
        }
    }

    private static void loadPlugin() {
        if (v.a(sContext).a(com.xiaomi.xmpush.thrift.b.z.a(), getDefaultSwitch())) {
            com.xiaomi.channel.commonutils.misc.d.a(sContext).a(new c(), XZBDevice.Stop);
        }
    }

    public static void pausePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 0, 0, str);
    }

    static void reInitialize(Context context) {
        if (a.a(context).i()) {
            String a = d.a(R.styleable.Toolbar_contentInsetEnd);
            String c = a.a(context).c();
            String d = a.a(context).d();
            a.a(context).h();
            a.a(context).a(c, d, a);
            s sVar = new s();
            sVar.a(generatePacketID());
            sVar.b(c);
            sVar.e(d);
            sVar.f(a);
            sVar.d(context.getPackageName());
            sVar.c(com.xiaomi.channel.commonutils.android.b.a(context, context.getPackageName()));
            j.a(context).a(sVar, false);
        }
    }

    public static void registerPush(Context context, String str, String str2) {
        new Thread(new b(context, str, str2)).start();
    }

    static synchronized void removeAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove(new StringBuilder("account_").append(str).toString()).commit();
        }
    }

    static synchronized void removeAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove(new StringBuilder("alias_").append(str).toString()).commit();
        }
    }

    static synchronized void removeTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences(PREF_EXTRA, 0).edit().remove(new StringBuilder("topic_").append(str).toString()).commit();
        }
    }

    static void reportIgnoreRegMessageClicked(Context context, String str, i iVar, String str2, String str3) {
        org.apache.thrift.b rVar = new r();
        if (TextUtils.isEmpty(str3)) {
            b.d("do not report clicked message");
            return;
        }
        rVar.b(str3);
        rVar.c("bar:click");
        rVar.a(str);
        rVar.a(false);
        j.a(context).a(rVar, com.xiaomi.xmpush.thrift.a.i, false, true, iVar, true, str2, str3);
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        i iVar = new i();
        iVar.a(miPushMessage.getMessageId());
        iVar.b(miPushMessage.getTopic());
        iVar.d(miPushMessage.getDescription());
        iVar.c(miPushMessage.getTitle());
        iVar.c(miPushMessage.getNotifyId());
        iVar.a(miPushMessage.getNotifyType());
        iVar.b(miPushMessage.getPassThrough());
        iVar.a(miPushMessage.getExtra());
        reportMessageClicked(context, miPushMessage.getMessageId(), iVar, null);
    }

    @Deprecated
    public static void reportMessageClicked(Context context, String str) {
        reportMessageClicked(context, str, null, null);
    }

    static void reportMessageClicked(Context context, String str, i iVar, String str2) {
        Object rVar = new r();
        if (!TextUtils.isEmpty(str2)) {
            rVar.b(str2);
        } else if (a.a(context).b()) {
            rVar.b(a.a(context).c());
        } else {
            b.d("do not report clicked message");
            return;
        }
        rVar.c("bar:click");
        rVar.a(str);
        rVar.a(false);
        j.a(context).a(rVar, com.xiaomi.xmpush.thrift.a.i, false, iVar);
    }

    public static void resumePush(Context context, String str) {
        setAcceptTime(context, 0, 0, R.styleable.Toolbar_titleTextColor, R.styleable.AppCompatTheme_toolbarNavigationButtonStyle, str);
    }

    private static void scheduleOcVersionCheckJob() {
        com.xiaomi.channel.commonutils.misc.d.a(sContext).a(new g(sContext), v.a(sContext).a(com.xiaomi.xmpush.thrift.b.A.a(), HttpCacher.TIME_DAY), XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
    }

    public static void setAcceptTime(Context context, int i, int i2, int i3, int i4, String str) {
        if (i < 0 || i >= 24 || i3 < 0 || i3 >= 24 || i2 < 0 || i2 >= 60 || i4 < 0 || i4 >= 60) {
            throw new IllegalArgumentException("the input parameter is not valid.");
        }
        long rawOffset = (long) (((TimeZone.getTimeZone("GMT+08").getRawOffset() - TimeZone.getDefault().getRawOffset()) / 1000) / 60);
        long j = ((((long) ((i * 60) + i2)) + rawOffset) + 1440) % 1440;
        rawOffset = ((rawOffset + ((long) ((i3 * 60) + i4))) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(j / 60), Long.valueOf(j % 60)}));
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(rawOffset / 60), Long.valueOf(rawOffset % 60)}));
        List arrayList2 = new ArrayList();
        arrayList2.add(String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        arrayList2.add(String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}));
        if (!acceptTimeSet(context, (String) arrayList.get(0), (String) arrayList.get(1))) {
            setCommand(context, COMMAND_SET_ACCEPT_TIME, arrayList, str);
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.a(context, str, COMMAND_SET_ACCEPT_TIME, 0, null, arrayList2);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ACCEPT_TIME, arrayList2, 0, null, null));
        }
    }

    public static void setAlias(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            setCommand(context, COMMAND_SET_ALIAS, str, str2);
        }
    }

    protected static void setCommand(Context context, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (!COMMAND_SET_ALIAS.equalsIgnoreCase(str) || System.currentTimeMillis() - aliasSetTime(context, str2) >= 3600000) {
            if (COMMAND_UNSET_ALIAS.equalsIgnoreCase(str) && aliasSetTime(context, str2) < 0) {
                b.a(new StringBuilder("Don't cancel alias for ").append(arrayList).append(" is unseted").toString());
            } else if (!COMMAND_SET_ACCOUNT.equalsIgnoreCase(str) || System.currentTimeMillis() - accountSetTime(context, str2) >= 3600000) {
                if (!COMMAND_UNSET_ACCOUNT.equalsIgnoreCase(str) || accountSetTime(context, str2) >= 0) {
                    setCommand(context, str, arrayList, str3);
                } else {
                    b.a(new StringBuilder("Don't cancel account for ").append(arrayList).append(" is unseted").toString());
                }
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.a(context, str3, str, 0, null, arrayList);
            } else {
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ACCOUNT, arrayList, 0, null, null));
            }
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.a(context, str3, str, 0, null, arrayList);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ALIAS, arrayList, 0, null, null));
        }
    }

    protected static void setCommand(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (!TextUtils.isEmpty(a.a(context).c())) {
            org.apache.thrift.b mVar = new m();
            mVar.a(generatePacketID());
            mVar.b(a.a(context).c());
            mVar.c(str);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                mVar.d((String) it.next());
            }
            mVar.f(str2);
            mVar.e(context.getPackageName());
            j.a(context).a(mVar, com.xiaomi.xmpush.thrift.a.j, null);
        }
    }

    public static void setLocalNotificationType(Context context, int i) {
        j.a(context).b(i & -1);
    }

    public static void setUserAccount(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            setCommand(context, COMMAND_SET_ACCOUNT, str, str2);
        }
    }

    private static boolean shouldPullNotification(Context context) {
        return System.currentTimeMillis() - context.getSharedPreferences(PREF_EXTRA, 0).getLong("last_pull_notification", -1) > 300000;
    }

    private static boolean shouldSendRegRequest(Context context) {
        return System.currentTimeMillis() - context.getSharedPreferences(PREF_EXTRA, 0).getLong("last_reg_request", -1) > 5000;
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return j.a(context).b();
    }

    public static void subscribe(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(a.a(context).c()) && !TextUtils.isEmpty(str)) {
            if (System.currentTimeMillis() - topicSubscribedTime(context, str) > 86400000) {
                org.apache.thrift.b xVar = new x();
                xVar.a(generatePacketID());
                xVar.b(a.a(context).c());
                xVar.c(str);
                xVar.d(context.getPackageName());
                xVar.e(str2);
                j.a(context).a(xVar, com.xiaomi.xmpush.thrift.a.c, null);
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.a(context, str2, 0, null, str);
            } else {
                List arrayList = new ArrayList();
                arrayList.add(str);
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SUBSCRIBE_TOPIC, arrayList, 0, null, null));
            }
        }
    }

    public static long topicSubscribedTime(Context context, String str) {
        return context.getSharedPreferences(PREF_EXTRA, 0).getLong(new StringBuilder("topic_").append(str).toString(), -1);
    }

    public static void unregisterPush(Context context) {
        if (a.a(context).b()) {
            com.xiaomi.xmpush.thrift.z zVar = new com.xiaomi.xmpush.thrift.z();
            zVar.a(generatePacketID());
            zVar.b(a.a(context).c());
            zVar.c(a.a(context).e());
            zVar.e(a.a(context).d());
            zVar.d(context.getPackageName());
            j.a(context).a(zVar);
            PushMessageHandler.a();
            a.a(context).k();
            clearExtras(context);
            clearLocalNotificationType(context);
            clearNotification(context);
        }
    }

    public static void unsetAlias(Context context, String str, String str2) {
        setCommand(context, COMMAND_UNSET_ALIAS, str, str2);
    }

    public static void unsetUserAccount(Context context, String str, String str2) {
        setCommand(context, COMMAND_UNSET_ACCOUNT, str, str2);
    }

    public static void unsubscribe(Context context, String str, String str2) {
        if (!a.a(context).b()) {
            return;
        }
        if (topicSubscribedTime(context, str) < 0) {
            b.a(new StringBuilder("Don't cancel subscribe for ").append(str).append(" is unsubscribed").toString());
            return;
        }
        org.apache.thrift.b abVar = new ab();
        abVar.a(generatePacketID());
        abVar.b(a.a(context).c());
        abVar.c(str);
        abVar.d(context.getPackageName());
        abVar.e(str2);
        j.a(context).a(abVar, com.xiaomi.xmpush.thrift.a.d, null);
    }

    private static void updateIMEI() {
        new Thread(new d()).start();
    }
}
