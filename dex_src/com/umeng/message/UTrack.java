package com.umeng.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.taobao.agoo.TaobaoRegister;
import com.umeng.common.UmLog;
import com.umeng.common.UmengMessageDeviceConfig;
import com.umeng.common.b;
import com.umeng.common.inter.IUtrack;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.k;
import com.xunlei.download.Downloads.Impl.RequestHeaders;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

public class UTrack {
    private static final String a;
    private static UTrack d;
    private static IUtrack e;
    private static boolean j;
    private static boolean k;
    private static boolean l;
    private static boolean m;
    private JSONObject b;
    private JSONObject c;
    private ScheduledThreadPoolExecutor f;
    private Context g;
    private boolean h;
    private final String i;

    static {
        a = UTrack.class.getName();
        j = false;
        k = false;
        l = false;
        m = false;
    }

    public void setClearPrevMessage(boolean z) {
        this.h = z;
    }

    private UTrack(Context context) {
        this.i = Constants.SP_KEY_APPKEY;
        this.g = context.getApplicationContext();
        c();
        this.f = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
    }

    public static synchronized UTrack getInstance(Context context) {
        UTrack uTrack;
        synchronized (UTrack.class) {
            if (d == null) {
                d = new UTrack(context);
                try {
                    e = (IUtrack) Class.forName("com.umeng.common.impl.json.JUtrack").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            uTrack = d;
        }
        return uTrack;
    }

    public void trackMsgArrival(UMessage uMessage) {
        if (uMessage != null && uMessage.msg_id != null) {
            a(uMessage.msg_id, 0, uMessage.random_min * 60000);
        }
    }

    public void trackMsgClick(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            a(uMessage.msg_id, 1, uMessage.random_min * 60000);
        }
        if (!(uMessage == null || uMessage.message_id == null)) {
            a(uMessage.message_id, uMessage.task_id, MessageService.MSG_ACCS_NOTIFY_CLICK);
        }
        if (this.h) {
            ((UmengMessageHandler) PushAgent.getInstance(this.g).getMessageHandler()).setPrevMessage(null);
        }
    }

    public void trackMiPushMsgClick(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            a(uMessage.msg_id, (int) R.styleable.Toolbar_navigationContentDescription, uMessage.random_min * 60000);
        }
        if (this.h) {
            ((UmengMessageHandler) PushAgent.getInstance(this.g).getMessageHandler()).setPrevMessage(null);
        }
    }

    public void trackMsgDismissed(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            a(uMessage.msg_id, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE, uMessage.random_min * 60000);
        }
        if (!(uMessage == null || uMessage.message_id == null)) {
            a(uMessage.message_id, uMessage.task_id, MessageService.MSG_ACCS_NOTIFY_DISMISS);
        }
        if (this.h) {
            ((UmengMessageHandler) PushAgent.getInstance(this.g).getMessageHandler()).setPrevMessage(null);
        }
    }

    private void a(String str, int i, long j) {
        if (!d()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            UmLog.e(a, "trackMsgLog: empty msgId");
            return;
        }
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            k.a(this.g).a(str, i, currentTimeMillis);
        } catch (Exception e) {
            e.printStackTrace();
            UmLog.d(a, new StringBuilder("trackMsgLog: ").append(e.toString()).toString());
        }
        AnonymousClass_1 anonymousClass_1 = new AnonymousClass_1(this, str, i, currentTimeMillis);
        if (j <= 0 || i == 1 || i == 21) {
            j2 = 0;
        } else {
            j2 = Math.abs(new Random().nextLong() % j);
        }
        UmLog.d(a, String.format("trackMsgLog(msgId=%s, actionType=%d, random=%d, delay=%d)", new Object[]{str, Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2)}));
        this.f.schedule(anonymousClass_1, j2, TimeUnit.MILLISECONDS);
    }

    private void a(String str, String str2, String str3) {
        if (!d()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            UmLog.e(a, "trackMsgLogForAgoo: empty msgId");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            k.a(this.g).a(str, str2, str3, currentTimeMillis);
        } catch (Exception e) {
            e.printStackTrace();
            UmLog.d(a, new StringBuilder("trackMsgLog: ").append(e.toString()).toString());
        }
        this.f.submit(new AnonymousClass_4(this, str, str2, str3, currentTimeMillis));
    }

    private synchronized void b(String str, int i, long j) {
        JSONObject jSONObject = null;
        try {
            jSONObject = g();
            jSONObject.put(MsgConstant.KEY_MSG_ID, str);
            jSONObject.put(MsgConstant.KEY_ACTION_TYPE, i);
            jSONObject.put(MsgConstant.KEY_TS, j);
            e.sendMsgLog(jSONObject, str, i, j, true);
        } catch (Exception e) {
            e.printStackTrace();
            UmLog.d(a, e.toString());
            if (e.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                try {
                    e.sendMsgLog(jSONObject, str, i, j, false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public synchronized void sendMsgLogForAgoo(String str, String str2, String str3, long j) {
        if (str3.equalsIgnoreCase(MessageService.MSG_ACCS_NOTIFY_CLICK)) {
            TaobaoRegister.clickMessage(this.g, str, str2);
        } else {
            TaobaoRegister.dismissMessage(this.g, str, str2);
        }
    }

    public void sendCachedMsgLog(long j) {
        if (!d()) {
            return;
        }
        if (j || k) {
            UmLog.d(a, "sendCachedMsgLog already in queue, abort this request.");
            return;
        }
        UmLog.d(a, "sendCachedMsgLog start, set cacheLogSending flag");
        j = true;
        k = true;
        AnonymousClass_5 anonymousClass_5 = new AnonymousClass_5(this);
        UmLog.d(a, String.format("sendCachedMsgLog(delay=%d)", new Object[]{Long.valueOf(j)}));
        this.f.schedule(anonymousClass_5, j, TimeUnit.MILLISECONDS);
        this.f.submit(new AnonymousClass_6(this));
    }

    public void trackAppLaunch(long j) {
        if (!d()) {
            return;
        }
        if (MessageSharedPrefs.getInstance(this.g).getAppLaunchLogSendPolicy() == 1) {
            UmLog.d(a, "launch_policy=1, skip sending app launch info.");
        } else if (!MessageSharedPrefs.getInstance(this.g).hasAppLaunchLogSentToday()) {
            a(j);
        }
    }

    private void a(long j) {
        if (l) {
            UmLog.d(a, "trackAppLaunch already in queue, abort this request.");
            return;
        }
        UmLog.d(a, "trackAppLaunch start, set appLaunchSending flag");
        l = true;
        AnonymousClass_7 anonymousClass_7 = new AnonymousClass_7(this);
        UmLog.d(a, String.format("trackAppLaunch(delay=%d)", new Object[]{Long.valueOf(j)}));
        this.f.schedule(anonymousClass_7, j, TimeUnit.MILLISECONDS);
    }

    public void trackRegister() {
        if (!d() || MessageSharedPrefs.getInstance(this.g).getHasRegister()) {
            return;
        }
        if (m) {
            UmLog.d(a, "sendRegisterLog already in queue, abort this request.");
            return;
        }
        UmLog.d(a, "trackRegisterLog start, set registerSending flag");
        m = true;
        AnonymousClass_8 anonymousClass_8 = new AnonymousClass_8(this);
        UmLog.d(a, String.format("trackRegister(delay=%d)", new Object[]{Integer.valueOf(0)}));
        this.f.schedule(anonymousClass_8, 0, TimeUnit.MILLISECONDS);
    }

    public void trackLocation(byte[] bArr) {
        if (d()) {
            this.f.schedule(new AnonymousClass_9(this, bArr), 0, TimeUnit.MILLISECONDS);
        }
    }

    private void c() {
        b bVar;
        if (this.b == null) {
            bVar = new b();
            bVar.b(this.g, new String[0]);
            bVar.a(this.g, new String[]{PushAgent.getInstance(this.g).getMessageAppkey(), PushAgent.getInstance(this.g).getMessageChannel()});
            this.b = new JSONObject();
            try {
                bVar.b(this.b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.c == null) {
            bVar = new b();
            bVar.c(this.g, new String[0]);
            bVar.a(this.g, new String[]{PushAgent.getInstance(this.g).getMessageAppkey(), PushAgent.getInstance(this.g).getMessageChannel()});
            this.c = new JSONObject();
            try {
                bVar.c(this.c);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public JSONObject getHeader() {
        return this.b;
    }

    public void sendAliasFailLog() {
        new Thread(new AnonymousClass_10(this)).start();
    }

    public boolean addAlias(String str, String str2, ICallBack iCallBack) {
        new Thread(new AnonymousClass_11(this, str2, str, iCallBack)).start();
        return false;
    }

    public void addExclusiveAlias(String str, String str2, ICallBack iCallBack) {
        new Thread(new AnonymousClass_2(this, str2, str, iCallBack)).start();
    }

    public void removeAlias(String str, String str2, ICallBack iCallBack) {
        new Thread(new AnonymousClass_3(this, str2, str, iCallBack)).start();
    }

    private boolean d() {
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.g))) {
            UmLog.e(a, "UTDID is empty");
            return false;
        } else if (!TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.g).getDeviceToken())) {
            return true;
        } else {
            UmLog.e(a, "RegistrationId is empty");
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    private String e() {
        IOException e;
        Throwable th;
        try {
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return null;
            }
            String str = Environment.getExternalStorageDirectory().getPath() + "/data/" + this.g.getPackageName() + "/";
            UmLog.d(a, new StringBuilder("path=").append(str).toString());
            File file = new File(str, "umeng-message.config");
            if (!file.exists()) {
                return null;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                do {
                    try {
                        str = bufferedReader.readLine();
                        if (str == null) {
                            try {
                                bufferedReader.close();
                                return null;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return null;
                            }
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                    } catch (IOException e4) {
                        e2 = e4;
                    }
                } while (!str.startsWith("sign="));
                str = str.substring(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (Exception e6) {
            e6.printStackTrace();
            return null;
        }
    }

    private JSONObject f() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.g).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.g);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(RequestHeaders.COLUMN_HEADER, this.b);
        jSONObject.put(MsgConstant.KEY_UTDID, utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    private JSONObject g() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.g).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.g);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(RequestHeaders.COLUMN_HEADER, this.c);
        jSONObject.put(MsgConstant.KEY_UTDID, utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    private JSONObject h() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(this.g).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(this.g);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.SP_KEY_APPKEY, PushAgent.getInstance(this.g).getMessageAppkey());
        jSONObject.put(MsgConstant.KEY_UTDID, utdid);
        jSONObject.put(MsgConstant.KEY_DEVICE_TOKEN, deviceToken);
        return jSONObject;
    }

    public void updateHeader() {
        b bVar = new b();
        bVar.b(this.g, new String[0]);
        bVar.a(this.g, new String[]{PushAgent.getInstance(this.g).getMessageAppkey(), PushAgent.getInstance(this.g).getMessageChannel()});
        this.b = new JSONObject();
        try {
            bVar.b(this.b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bVar = new b();
        bVar.c(this.g, new String[0]);
        bVar.a(this.g, new String[]{PushAgent.getInstance(this.g).getMessageAppkey(), PushAgent.getInstance(this.g).getMessageChannel()});
        this.c = new JSONObject();
        try {
            bVar.c(this.c);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
