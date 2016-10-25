package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import anet.channel.AccsSessionManager;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.client.AccsConfig;
import com.taobao.accs.client.AccsConfig.ACCS_GROUP;
import com.taobao.accs.client.AccsConfig.SECURITY_TYPE;
import com.taobao.accs.utl.ALog;
import com.taobao.agoo.TaobaoRegister;
import com.umeng.common.UmLog;
import com.umeng.common.UmengMessageDeviceConfig;
import com.umeng.message.UTrack.ICallBack;
import com.umeng.message.proguard.g;
import com.umeng.message.tag.TagManager;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Random;

public class PushAgent {
    public static boolean DEBUG;
    private static PushAgent a;
    private static boolean d;
    private static final String e;
    private TagManager b;
    private Context c;
    private UHandler f;
    private UHandler g;
    private boolean h;
    private Handler i;
    private IUmengRegisterCallback j;
    private IUmengCallback k;

    static {
        d = false;
        DEBUG = false;
        e = PushAgent.class.getName();
    }

    private PushAgent(Context context) {
        this.h = false;
        try {
            this.c = context;
            this.b = TagManager.getInstance(context);
            this.f = new UmengMessageHandler();
            this.g = new UmengNotificationClickHandler();
        } catch (Exception e) {
            UmLog.e(e, e.getMessage());
        }
        this.i = new AnonymousClass_1(this, context.getMainLooper());
    }

    public static synchronized PushAgent getInstance(Context context) {
        PushAgent pushAgent;
        synchronized (PushAgent.class) {
            if (a == null) {
                a = new PushAgent(context.getApplicationContext());
            }
            pushAgent = a;
        }
        return pushAgent;
    }

    private void b() {
        try {
            if (VERSION.SDK_INT < 11) {
                UmLog.e(e, "Push SDK does not work for Android Verion < 11");
            } else if (g.a(this.c, this.i)) {
                UmLog.d(e, "The AndroidManifest config is right");
                g.a(this.c, UmengMessageCallbackHandlerService.class);
                ALog.setUseTlog(false);
                anet.channel.util.ALog.setUseTlog(false);
                ACCSManager.setMode(this.c, 0);
                AccsConfig.setGroup(ACCS_GROUP.OPEN);
                AccsConfig.setSecurityGuardOff(SECURITY_TYPE.SECURITY_OFF);
                AccsConfig.setTnetPubkey(XZBDevice.Success, XZBDevice.Success);
                AccsSessionManager.getInstance().setCallback(new AnonymousClass_2(this));
                AccsConfig.setAccsCenterHosts("umengacs.m.taobao.com", "umengacs.m.taobao.com", "umengacs.m.taobao.com");
                AccsConfig.setAccsCenterIps(new String[]{"140.205.160.76"}, new String[]{"110.75.206.79"}, new String[]{"10.125.50.231"});
                AccsConfig.setChannelHosts("umengjmacs.m.taobao.com", "umengjmacs.m.taobao.com", "umengjmacs.m.taobao.com");
                AccsConfig.setChannelIps(new String[]{"140.205.163.94"}, new String[]{"110.75.206.79"}, new String[]{"10.125.50.231"});
                new StringBuilder("appkey:").append(getMessageAppkey()).append(",secret:").append(getMessageSecret());
                TaobaoRegister.setAgooMsgReceiveService("com.umeng.message.UmengIntentService");
                TaobaoRegister.register(this.c, new StringBuilder("umeng:").append(getMessageAppkey()).toString(), getMessageSecret(), "android@umeng", new AnonymousClass_3(this));
            } else {
                UmLog.e(e, "Need to correct AndroidManifest config according to instruction from http://dev.umeng.com/push/android/integration");
            }
        } catch (Exception e) {
            UmLog.e(e, e.getMessage());
        }
    }

    private void a(String str) {
        Intent intent = new Intent();
        intent.setPackage(this.c.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION);
        intent.putExtra(MsgConstant.KEY_REGISTRATION_ID, str);
        intent.putExtra(Impl.COLUMN_STATUS, true);
        this.c.startService(intent);
    }

    private void a(String str, String str2) {
        Intent intent = new Intent();
        intent.setPackage(this.c.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION);
        intent.putExtra(Impl.COLUMN_STATUS, false);
        intent.putExtra("s", str);
        intent.putExtra("s1", str2);
        this.c.startService(intent);
    }

    private void c() {
        TaobaoRegister.bindAgoo(this.c, new StringBuilder("umeng:").append(getMessageAppkey()).toString(), "android@umeng", new AnonymousClass_4(this));
    }

    private void d() {
        try {
            UmLog.i(e, "unBindAgoo");
            TaobaoRegister.unBindAgoo(this.c, new StringBuilder("umeng:").append(getMessageAppkey()).toString(), "android@umeng", new AnonymousClass_5(this));
        } catch (Exception e) {
            UmLog.e(e, e.getMessage());
        }
    }

    public void register(IUmengRegisterCallback iUmengRegisterCallback) {
        setRegisterCallback(iUmengRegisterCallback);
        b();
    }

    public void enable(IUmengCallback iUmengCallback) {
        setCallback(iUmengCallback);
        c();
    }

    public void disable(IUmengCallback iUmengCallback) {
        setCallback(iUmengCallback);
        d();
    }

    public void setMessageHandler(UHandler uHandler) {
        this.f = uHandler;
    }

    public UHandler getMessageHandler() {
        return this.f;
    }

    public void setNotificationClickHandler(UHandler uHandler) {
        this.g = uHandler;
    }

    public UHandler getNotificationClickHandler() {
        return this.g;
    }

    public TagManager getTagManager() {
        return this.b;
    }

    public void addAlias(String str, String str2, ICallBack iCallBack) {
        UTrack.getInstance(this.c).addAlias(str, str2, iCallBack);
    }

    public void addExclusiveAlias(String str, String str2, ICallBack iCallBack) {
        UTrack.getInstance(this.c).addExclusiveAlias(str, str2, iCallBack);
    }

    public void removeAlias(String str, String str2, ICallBack iCallBack) {
        UTrack.getInstance(this.c).removeAlias(str, str2, iCallBack);
    }

    public String getMessageSecret() {
        String messageAppSecret = MessageSharedPrefs.getInstance(this.c).getMessageAppSecret();
        return TextUtils.isEmpty(messageAppSecret) ? UmengMessageDeviceConfig.getMetaData(this.c, "UMENG_MESSAGE_SECRET") : messageAppSecret;
    }

    public String getMessageAppkey() {
        String messageAppKey = MessageSharedPrefs.getInstance(this.c).getMessageAppKey();
        return TextUtils.isEmpty(messageAppKey) ? UmengMessageDeviceConfig.getAppkey(this.c) : messageAppKey;
    }

    public String getMessageChannel() {
        String messageChannel = MessageSharedPrefs.getInstance(this.c).getMessageChannel();
        return TextUtils.isEmpty(messageChannel) ? UmengMessageDeviceConfig.getChannel(this.c) : messageChannel;
    }

    public void onAppStart() {
        UTrack.getInstance(this.c).sendAliasFailLog();
        UTrack.getInstance(this.c).trackAppLaunch(10000);
        long j = 0;
        if (isAppLaunchByMessage()) {
            j = Math.abs(new Random().nextLong() % MsgConstant.a);
        }
        UTrack.getInstance(this.c).sendCachedMsgLog(j);
    }

    public <U extends UmengMessageService> void setPushIntentServiceClass(Class<U> cls) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).setPushIntentServiceClass(cls);
        }
    }

    public String getPushIntentServiceClass() {
        return MessageSharedPrefs.getInstance(this.c).getPushIntentServiceClass();
    }

    public void setDebugMode(boolean z) {
        UmLog.LOG = z;
        ALog.setPrintLog(z);
        anet.channel.util.ALog.setPrintLog(z);
    }

    public void setNoDisturbMode(int i, int i2, int i3, int i4) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).a(i, i2, i3, i4);
        }
    }

    public int getNoDisturbStartHour() {
        return MessageSharedPrefs.getInstance(this.c).a();
    }

    public int getNoDisturbStartMinute() {
        return MessageSharedPrefs.getInstance(this.c).b();
    }

    public int getNoDisturbEndHour() {
        return MessageSharedPrefs.getInstance(this.c).c();
    }

    public int getNoDisturbEndMinute() {
        return MessageSharedPrefs.getInstance(this.c).d();
    }

    public static void setAppLaunchByMessage() {
        d = true;
    }

    public static boolean isAppLaunchByMessage() {
        return d;
    }

    public String getRegistrationId() {
        return MessageSharedPrefs.getInstance(this.c).getDeviceToken();
    }

    public void setDisplayNotificationNumber(int i) {
        if (g.d(this.c) && i >= 0 && i <= 10) {
            MessageSharedPrefs.getInstance(this.c).setDisplayNotificationNumber(i);
        }
    }

    public int getDisplayNotificationNumber() {
        return MessageSharedPrefs.getInstance(this.c).getDisplayNotificationNumber();
    }

    public void setAppkeyAndSecret(String str, String str2) {
        if (g.d(this.c)) {
            String messageAppKey = MessageSharedPrefs.getInstance(this.c).getMessageAppKey();
            String messageAppSecret = MessageSharedPrefs.getInstance(this.c).getMessageAppSecret();
            if (!(messageAppKey.equals(str) || messageAppSecret.equals(str2))) {
                MessageSharedPrefs.getInstance(this.c).removeMessageAppKey();
                MessageSharedPrefs.getInstance(this.c).removeMessageAppSecret();
            }
            MessageSharedPrefs.getInstance(this.c).setMessageAppKey(str);
            MessageSharedPrefs.getInstance(this.c).setMessageAppSecret(str2);
            UTrack.getInstance(this.c).updateHeader();
        }
    }

    public void setMessageChannel(String str) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).setMessageChannel(str);
            UTrack.getInstance(this.c).updateHeader();
        }
    }

    public void setRegisterCallback(IUmengRegisterCallback iUmengRegisterCallback) {
        this.j = iUmengRegisterCallback;
    }

    public IUmengRegisterCallback getRegisterCallback() {
        return this.j;
    }

    public void setCallback(IUmengCallback iUmengCallback) {
        this.k = iUmengCallback;
    }

    public IUmengCallback getCallback() {
        return this.k;
    }

    public void setMuteDurationSeconds(int i) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).setMuteDuration(i);
        }
    }

    public int getMuteDurationSeconds() {
        return MessageSharedPrefs.getInstance(this.c).getMuteDuration();
    }

    public boolean isIncludesUmengUpdateSDK() {
        Class forName;
        try {
            forName = Class.forName("com.umeng.update.UmengUpdateAgent");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            forName = null;
        }
        return forName != null;
    }

    public boolean getNotificationOnForeground() {
        return MessageSharedPrefs.getInstance(this.c).getNotificaitonOnForeground();
    }

    public void setNotificaitonOnForeground(boolean z) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).setNotificaitonOnForeground(z);
        }
    }

    public String getResourcePackageName() {
        return MessageSharedPrefs.getInstance(this.c).getResourcePackageName();
    }

    public void setResourcePackageName(String str) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).setResourcePackageName(str);
        }
    }

    public boolean isPushCheck() {
        return this.h;
    }

    public void setPushCheck(boolean z) {
        this.h = z;
    }

    public int getNotificationPlayVibrate() {
        return MessageSharedPrefs.getInstance(this.c).getNotificationPlayVibrate();
    }

    public void setNotificationPlayVibrate(int i) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).setNotificationPlayVibrate(i);
        }
    }

    public int getNotificationPlayLights() {
        return MessageSharedPrefs.getInstance(this.c).getNotificationPlayLights();
    }

    public void setNotificationPlayLights(int i) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).setNotificationPlayLights(i);
        }
    }

    public int getNotificationPlaySound() {
        return MessageSharedPrefs.getInstance(this.c).getNotificationPlaySound();
    }

    public void setNotificationPlaySound(int i) {
        if (g.d(this.c)) {
            MessageSharedPrefs.getInstance(this.c).setNotificationPlaySound(i);
        }
    }

    public void setLocationInterval(int i) {
        MessageSharedPrefs.getInstance(this.c).setLocationInterval(i);
    }

    public int getLocationInterval() {
        return MessageSharedPrefs.getInstance(this.c).getLocationInterval();
    }
}
