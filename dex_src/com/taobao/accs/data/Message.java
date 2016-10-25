package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.f;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.android.agoo.intent.IntentUtil;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: Taobao
public class Message implements Serializable {
    public static int CONTROL_MAX_RETRY_TIMES = 0;
    public static final int EXT_HEADER_VALUE_MAX_LEN = 1023;
    public static final int FLAG_ACK_TYPE = 32;
    public static final int FLAG_BIZ_RET = 64;
    public static final int FLAG_DATA_TYPE = 32768;
    public static final int FLAG_ERR = 4096;
    public static final int FLAG_REQ_BIT1 = 16384;
    public static final int FLAG_REQ_BIT2 = 8192;
    public static final int FLAG_RET = 2048;
    public static final String KEY_BINDAPP = "ctrl_bindapp";
    public static final String KEY_BINDSERVICE = "ctrl_bindservice";
    public static final String KEY_BINDUSER = "ctrl_binduser";
    public static final String KEY_UNBINDAPP = "ctrl_unbindapp";
    public static final String KEY_UNBINDSERVICE = "ctrl_unbindservice";
    public static final String KEY_UNBINDUSER = "ctrl_unbinduser";
    public static final int MAX_RETRY_TIMES = 3;
    static long a;
    byte[] A;
    int B;
    long C;
    transient NetPerformanceMonitor D;
    public String appSign;
    byte b;
    public String bizId;
    byte c;
    public Integer command;
    public String cunstomDataId;
    short d;
    public String dataId;
    public long delyTime;
    short e;
    short f;
    public boolean force;
    byte g;
    byte h;
    public URL host;
    String i;
    public boolean isAck;
    public boolean isCancel;
    String j;
    int k;
    Map<ExtHeaderType, String> l;
    String m;
    Integer n;
    String o;
    Integer p;
    String q;
    String r;
    public int retryTimes;
    String s;
    public String serviceId;
    public long startSendTime;
    Integer t;
    public int timeout;
    String u;
    public String userinfo;
    String v;
    String w;
    String x;
    String y;
    String z;

    // compiled from: Taobao
    public enum ReqType {
        DATA,
        ACK,
        REQ,
        RES;

        public static com.taobao.accs.data.Message.ReqType valueOf(int i) {
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    return DATA;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return ACK;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return REQ;
                case MAX_RETRY_TIMES:
                    return RES;
                default:
                    return DATA;
            }
        }
    }

    // compiled from: Taobao
    public static class a {
        public static final int INVALID = -1;
        public static final int NEED_ACK = 1;
        public static final int NO_ACK = 0;

        public static int a(int i) {
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    return 0;
                default:
                    return NEED_ACK;
            }
        }

        public static String b(int i) {
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    return "NO_ACK";
                case NEED_ACK:
                    return "NEED_ACK";
                default:
                    return "INVALID";
            }
        }
    }

    // compiled from: Taobao
    public static class b {
        public static final int CONTROL = 0;
        public static final int DATA = 1;
        public static final int HANDSHAKE = 3;
        public static final int INVALID = -1;
        public static final int PING = 2;

        public static int a(int i) {
            switch (i) {
                case DATA:
                    return DATA;
                case PING:
                    return PING;
                case HANDSHAKE:
                    return HANDSHAKE;
                default:
                    return CONTROL;
            }
        }

        public static String b(int i) {
            switch (i) {
                case CONTROL:
                    return "CONTROL";
                case DATA:
                    return "DATA";
                case PING:
                    return "PING";
                case HANDSHAKE:
                    return "HANDSHAKE";
                default:
                    return "INVALID";
            }
        }
    }

    static {
        CONTROL_MAX_RETRY_TIMES = 5;
        a = 1;
    }

    private Message() {
        this.isAck = false;
        this.force = false;
        this.isCancel = false;
        this.b = (byte) 0;
        this.c = (byte) 0;
        this.k = -1;
        this.m = null;
        this.command = null;
        this.n = Integer.valueOf(0);
        this.o = null;
        this.appSign = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.userinfo = null;
        this.serviceId = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.delyTime = 0;
        this.retryTimes = 0;
        this.timeout = 40000;
        this.bizId = null;
        synchronized (Message.class) {
            long j = a;
            a = 1 + j;
            this.dataId = String.valueOf(j);
        }
        this.startSendTime = System.currentTimeMillis();
    }

    public int getNode() {
        return this.B;
    }

    public int getType() {
        return this.k;
    }

    public String getDataId() {
        return this.dataId;
    }

    public boolean isControlFrame() {
        return Constants.TARGET_CONTROL.equals(this.i);
    }

    public int getIntDataId() {
        try {
            return !this.isAck ? Integer.valueOf(this.dataId).intValue() : -((int) a);
        } catch (Exception e) {
            ALog.w("Message", new StringBuilder("parse int dataId error ").append(this.dataId).toString(), new Object[0]);
            return -1;
        }
    }

    public void setSendTime(long j) {
        this.C = j;
    }

    public NetPerformanceMonitor getNetPermanceMonitor() {
        return this.D;
    }

    public long getDelyTime() {
        return this.delyTime;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }

    public String getPackageName() {
        return this.m == null ? com.umeng.a.d : this.m;
    }

    public boolean isTimeOut() {
        boolean z = (System.currentTimeMillis() - this.startSendTime) + this.delyTime >= ((long) this.timeout);
        if (z) {
            ALog.e("Message", new StringBuilder("delay time:").append(this.delyTime).append(" beforeSendTime:").append(System.currentTimeMillis() - this.startSendTime).append(" timeout").append(this.timeout).toString(), new Object[0]);
        }
        return z;
    }

    public byte[] build(Context context, int i) {
        String str;
        byte[] bytes;
        int i2;
        try {
            b();
        } catch (Throwable e) {
            ALog.e("Message", "build1", e, new Object[0]);
        } catch (Throwable e2) {
            ALog.e("Message", "build2", e2, new Object[0]);
        }
        if (this.A != null) {
            str = new String(this.A);
        } else {
            str = com.umeng.a.d;
        }
        a();
        if (!this.isAck) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UtilityImpl.getDeviceId(context)).append("|").append(this.m).append("|").append(this.serviceId == null ? com.umeng.a.d : this.serviceId).append("|").append(this.userinfo == null ? com.umeng.a.d : this.userinfo);
            this.j = stringBuilder.toString();
        }
        try {
            bytes = (this.dataId).getBytes("utf-8");
            this.h = (byte) this.j.getBytes("utf-8").length;
            this.g = (byte) this.i.getBytes("utf-8").length;
        } catch (Throwable e22) {
            e22.printStackTrace();
            ALog.e("Message", "build3", e22, new Object[0]);
            bytes = (this.dataId).getBytes();
            this.h = (byte) this.j.getBytes().length;
            this.g = (byte) this.i.getBytes().length;
        }
        short a = a(this.l);
        int length = bytes.length + ((((this.g + 3) + 1) + this.h) + 1);
        if (this.A == null) {
            i2 = 0;
        } else {
            i2 = this.A.length;
        }
        this.e = (short) (((i2 + length) + a) + 2);
        this.d = (short) (this.e + 2);
        f fVar = new f((this.d + 2) + 4);
        ALog.d("Message", "Build Message", new Object[0]);
        try {
            fVar.a((byte) (this.b | 32));
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\tversion:2 compress:").append(this.b).toString(), new Object[0]);
            }
            if (i == 0) {
                fVar.a(Byte.MIN_VALUE);
                ALog.d("Message", "\tflag: 0x80", new Object[0]);
            } else {
                fVar.a((byte) 64);
                ALog.d("Message", "\tflag: 0x40", new Object[0]);
            }
            fVar.a(this.d);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\ttotalLength:").append(this.d).toString(), new Object[0]);
            }
            fVar.a(this.e);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\tdataLength:").append(this.e).toString(), new Object[0]);
            }
            fVar.a(this.f);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\tflags:").append(Integer.toHexString(this.f)).toString(), new Object[0]);
            }
            fVar.a(this.g);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\ttargetLength:").append(this.g).toString(), new Object[0]);
            }
            fVar.write(this.i.getBytes("utf-8"));
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\ttarget:").append(new String(this.i)).toString(), new Object[0]);
            }
            fVar.a(this.h);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\tsourceLength:").append(this.h).toString(), new Object[0]);
            }
            fVar.write(this.j.getBytes("utf-8"));
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\tsource:").append(new String(this.j)).toString(), new Object[0]);
            }
            fVar.a((byte) bytes.length);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\tdataIdLength:").append(bytes.length).toString(), new Object[0]);
            }
            fVar.write(bytes);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\tdataId:").append(new String(bytes)).toString(), new Object[0]);
            }
            fVar.a(a);
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\textHeader len:").append(a).toString(), new Object[0]);
            }
            if (this.l != null) {
                for (ExtHeaderType extHeaderType : this.l.keySet()) {
                    String str2 = (String) this.l.get(extHeaderType);
                    if (!TextUtils.isEmpty(str2)) {
                        fVar.a((short) ((((short) extHeaderType.ordinal()) << 10) | ((short) (str2.getBytes("utf-8").length & 1023))));
                        fVar.write(str2.getBytes("utf-8"));
                        if (ALog.isPrintLog(Level.D)) {
                            ALog.d("Message", new StringBuilder("\textHeader key:").append(extHeaderType).append(" value:").append(str2).toString(), new Object[0]);
                        }
                    }
                }
            }
            if (this.A != null) {
                fVar.write(this.A);
            }
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("Message", new StringBuilder("\toriData:").append(str).toString(), new Object[0]);
            }
            fVar.flush();
        } catch (Throwable e222) {
            ALog.e("Message", "build4", e222, new Object[0]);
        }
        bytes = fVar.toByteArray();
        a(bytes);
        try {
            fVar.close();
        } catch (Throwable e3) {
            ALog.e("Message", "build5", e3, new Object[0]);
        }
        return bytes;
    }

    short a(Map<ExtHeaderType, String> map) {
        short s = (short) 0;
        if (map != null) {
            try {
                for (ExtHeaderType extHeaderType : map.keySet()) {
                    short s2;
                    String str = (String) map.get(extHeaderType);
                    if (TextUtils.isEmpty(str)) {
                        s2 = s;
                    } else {
                        s2 = (short) ((((short) (str.getBytes("utf-8").length & 1023)) + 2) + s);
                    }
                    s = s2;
                }
            } catch (Exception e) {
                e.toString();
            }
        }
        return s;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a() {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.data.Message.a():void");
        /*
        this = this;
        r2 = 0;
        r0 = r5.A;	 Catch:{ Throwable -> 0x0032, all -> 0x0047 }
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r3 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x0032, all -> 0x0047 }
        r3.<init>();	 Catch:{ Throwable -> 0x0032, all -> 0x0047 }
        r1 = new java.util.zip.GZIPOutputStream;	 Catch:{ Throwable -> 0x005f, all -> 0x0057 }
        r1.<init>(r3);	 Catch:{ Throwable -> 0x005f, all -> 0x0057 }
        r0 = r5.A;	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
        r1.write(r0);	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
        r1.finish();	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
        r0 = r3.toByteArray();	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
        if (r0 == 0) goto L_0x0029;
    L_0x001e:
        r2 = r0.length;	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
        r4 = r5.A;	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
        r4 = r4.length;	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
        if (r2 >= r4) goto L_0x0029;
    L_0x0024:
        r5.A = r0;	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
        r0 = 1;
        r5.b = r0;	 Catch:{ Throwable -> 0x0063, all -> 0x005a }
    L_0x0029:
        r1.close();	 Catch:{ Exception -> 0x0030 }
        r3.close();	 Catch:{ Exception -> 0x0030 }
        goto L_0x0005;
    L_0x0030:
        r0 = move-exception;
        goto L_0x0005;
    L_0x0032:
        r0 = move-exception;
        r1 = r2;
    L_0x0034:
        r0.toString();	 Catch:{ all -> 0x005c }
        r0.printStackTrace();	 Catch:{ all -> 0x005c }
        if (r1 == 0) goto L_0x003f;
    L_0x003c:
        r1.close();	 Catch:{ Exception -> 0x0045 }
    L_0x003f:
        if (r2 == 0) goto L_0x0005;
    L_0x0041:
        r2.close();	 Catch:{ Exception -> 0x0045 }
        goto L_0x0005;
    L_0x0045:
        r0 = move-exception;
        goto L_0x0005;
    L_0x0047:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x004a:
        if (r1 == 0) goto L_0x004f;
    L_0x004c:
        r1.close();	 Catch:{ Exception -> 0x0055 }
    L_0x004f:
        if (r3 == 0) goto L_0x0054;
    L_0x0051:
        r3.close();	 Catch:{ Exception -> 0x0055 }
    L_0x0054:
        throw r0;
    L_0x0055:
        r1 = move-exception;
        goto L_0x0054;
    L_0x0057:
        r0 = move-exception;
        r1 = r2;
        goto L_0x004a;
    L_0x005a:
        r0 = move-exception;
        goto L_0x004a;
    L_0x005c:
        r0 = move-exception;
        r3 = r2;
        goto L_0x004a;
    L_0x005f:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0034;
    L_0x0063:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0034;
        */
    }

    void b() throws JSONException, UnsupportedEncodingException {
        if (this.command != null && this.command.intValue() != 100 && this.command.intValue() != 102 && this.command.intValue() != 105) {
            this.A = new com.taobao.accs.utl.d.a().a(IntentUtil.AGOO_COMMAND, this.command.intValue() == 100 ? null : this.command).a(Constants.KEY_APP_KEY, this.o).a(Constants.KEY_OS_TYPE, this.p).a(Constants.KEY_SECURITY_SIGN, this.appSign).a(Constants.KEY_SDK_VERSION, this.t).a(Constants.KEY_APP_VERSION, this.s).a(Constants.KEY_TTID, this.u).a(Constants.KEY_MODEL, this.w).a(Constants.KEY_BRAND, this.x).a(Constants.KEY_IMEI, this.z).a(Constants.KEY_IMSI, this.z).a(Constants.KEY_OS_VERSION, this.q).a().toString().getBytes("utf-8");
        }
    }

    void a(byte[] bArr) {
        String str = com.umeng.a.d;
        if (ALog.isPrintLog(Level.D)) {
            ALog.d("Message", new StringBuilder("len:").append(bArr.length).toString(), new Object[0]);
            if (bArr.length < 512) {
                String str2 = str;
                for (int i = 0; i < bArr.length; i++) {
                    str2 = str2 + Integer.toHexString(bArr[i] & 255) + " ";
                }
                ALog.d("Message", str2, new Object[0]);
            }
        }
    }

    public static Message BuildPing(boolean z, int i) {
        Message message = new Message();
        message.k = 2;
        message.command = Integer.valueOf(Constants.COMMAND_PING);
        message.force = z;
        message.delyTime = (long) i;
        return message;
    }

    public static Message buildHandshake(String str) {
        Message message = new Message();
        message.a((int) MAX_RETRY_TIMES, ReqType.DATA, 1);
        message.m = str;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(Impl.STATUS_SUCCESS);
        return message;
    }

    public static Message buildBindApp(Context context, Intent intent) {
        Message buildBindApp;
        try {
            String stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_USER_ID);
            String stringExtra3 = intent.getStringExtra(Constants.KEY_APP_KEY);
            String stringExtra4 = intent.getStringExtra(Constants.KEY_TTID);
            String stringExtra5 = intent.getStringExtra(Constants.KEY_SID);
            String stringExtra6 = intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE);
            buildBindApp = buildBindApp(context, stringExtra3, stringExtra, stringExtra4, intent.getStringExtra(Constants.KEY_APP_VERSION), stringExtra5, stringExtra2, stringExtra6);
            try {
                a(context, buildBindApp);
            } catch (Exception e) {
                Throwable e2 = e;
                ALog.e("Message", "buildBindApp", e2, new Object[0]);
                e2.printStackTrace();
                return buildBindApp;
            }
        } catch (Throwable e3) {
            e2 = e3;
            buildBindApp = null;
            ALog.e("Message", "buildBindApp", e2, new Object[0]);
            e2.printStackTrace();
            return buildBindApp;
        }
        return buildBindApp;
    }

    public static Message buildBindApp(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8 = null;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        Message message = new Message();
        try {
            message.B = 1;
            message.a(1, ReqType.DATA, 1);
            message.p = Integer.valueOf(1);
            message.q = VERSION.SDK_INT;
            message.m = str2;
            message.i = Constants.TARGET_CONTROL;
            message.command = Integer.valueOf(1);
            message.o = str;
            message.appSign = UtilityImpl.getAppsign(context, str, UtilityImpl.getDeviceId(context), null, null);
            message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
            message.s = str4;
            message.m = str2;
            message.u = str3;
            message.w = Build.MODEL;
            message.x = Build.BRAND;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            message.y = telephonyManager != null ? telephonyManager.getDeviceId() : null;
            if (telephonyManager != null) {
                str8 = telephonyManager.getSubscriberId();
            }
            message.z = str8;
            message.cunstomDataId = KEY_BINDAPP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public static Message buildUnbindApp(Context context, Intent intent) {
        Message buildUnbindApp;
        ALog.e("Message", new StringBuilder("buildUnbindApp1").append(UtilityImpl.getStackMsg(new Exception())).toString(), new Object[0]);
        try {
            buildUnbindApp = buildUnbindApp(context, intent.getStringExtra(JsInterface.KEY_APK_NAME), intent.getStringExtra(Constants.KEY_SID), intent.getStringExtra(Constants.KEY_USER_ID), intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(context, buildUnbindApp);
            } catch (Exception e) {
                Throwable e2 = e;
                ALog.e("Message", "buildUnbindApp1", e2, new Object[0]);
                e2.printStackTrace();
                return buildUnbindApp;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            buildUnbindApp = null;
            e2 = th;
            ALog.e("Message", "buildUnbindApp1", e2, new Object[0]);
            e2.printStackTrace();
            return buildUnbindApp;
        }
        return buildUnbindApp;
    }

    public static Message buildUnbindApp(Context context, String str, String str2, String str3, String str4) {
        try {
            ALog.e("Message", new StringBuilder("buildUnbindApp").append(UtilityImpl.getStackMsg(new Exception())).toString(), new Object[0]);
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            Message message = new Message();
            try {
                message.B = 1;
                message.a(1, ReqType.DATA, 1);
                message.m = str;
                message.i = Constants.TARGET_CONTROL;
                message.command = Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                message.m = str;
                message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
                message.cunstomDataId = KEY_UNBINDAPP;
                a(context, message);
                return message;
            } catch (Exception e) {
                Throwable e2 = e;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            message = null;
            e2 = th;
            ALog.e("Message", "buildUnbindApp", e2, new Object[0]);
            e2.printStackTrace();
            return message;
        }
    }

    public static Message buildBindService(Context context, Intent intent) {
        Message buildBindService;
        try {
            String stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_SERVICE_ID);
            String stringExtra3 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildBindService = buildBindService(context, stringExtra, intent.getStringExtra(Constants.KEY_APP_KEY), stringExtra2, intent.getStringExtra(Constants.KEY_SID), stringExtra3, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(context, buildBindService);
            } catch (Exception e) {
                Throwable e2 = e;
                ALog.e("Message", "buildBindService", e2, new Object[0]);
                e2.printStackTrace();
                return buildBindService;
            }
        } catch (Throwable e3) {
            e2 = e3;
            buildBindService = null;
            ALog.e("Message", "buildBindService", e2, new Object[0]);
            e2.printStackTrace();
            return buildBindService;
        }
        return buildBindService;
    }

    public static Message buildBindService(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.serviceId = str3;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        message.m = str;
        message.serviceId = str3;
        message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_BINDSERVICE;
        return message;
    }

    public static Message buildUnbindService(Context context, Intent intent) {
        Message buildUnbindService;
        try {
            String stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_SERVICE_ID);
            String stringExtra3 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildUnbindService = buildUnbindService(context, stringExtra, intent.getStringExtra(Constants.KEY_APP_KEY), stringExtra2, intent.getStringExtra(Constants.KEY_SID), stringExtra3, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(context, buildUnbindService);
            } catch (Exception e) {
                Throwable e2 = e;
                ALog.e("Message", "buildUnbindService", e2, new Object[0]);
                e2.printStackTrace();
                return buildUnbindService;
            }
        } catch (Throwable e3) {
            e2 = e3;
            buildUnbindService = null;
            ALog.e("Message", "buildUnbindService", e2, new Object[0]);
            e2.printStackTrace();
            return buildUnbindService;
        }
        return buildUnbindService;
    }

    public static Message buildUnbindService(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.serviceId = str3;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(R.styleable.Toolbar_contentInsetEnd);
        message.m = str;
        message.serviceId = str3;
        message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_UNBINDSERVICE;
        return message;
    }

    public static Message buildBindUser(Context context, Intent intent) {
        Message buildBindUser;
        try {
            String stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildBindUser = buildBindUser(context, stringExtra, intent.getStringExtra(Constants.KEY_APP_KEY), intent.getStringExtra(Constants.KEY_SID), stringExtra2, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(context, buildBindUser);
            } catch (Exception e) {
                Throwable e2 = e;
                ALog.e("Message", "buildBindUser", e2, new Object[0]);
                e2.printStackTrace();
                return buildBindUser;
            }
        } catch (Throwable e3) {
            e2 = e3;
            buildBindUser = null;
            ALog.e("Message", "buildBindUser", e2, new Object[0]);
            e2.printStackTrace();
            return buildBindUser;
        }
        return buildBindUser;
    }

    public static Message buildBindUser(Context context, String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str4)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.userinfo = str4;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(MAX_RETRY_TIMES);
        message.m = str;
        message.userinfo = str4;
        message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_BINDUSER;
        return message;
    }

    public static Message buildUnbindUser(Context context, Intent intent) {
        Message buildUnbindUser;
        try {
            String stringExtra = intent.getStringExtra(JsInterface.KEY_APK_NAME);
            String stringExtra2 = intent.getStringExtra(Constants.KEY_USER_ID);
            buildUnbindUser = buildUnbindUser(context, stringExtra, intent.getStringExtra(Constants.KEY_APP_KEY), intent.getStringExtra(Constants.KEY_SID), stringExtra2, intent.getStringExtra(Constants.KEY_ANTI_BRUSH_COOKIE));
            try {
                a(context, buildUnbindUser);
            } catch (Exception e) {
                Throwable e2 = e;
                ALog.e("Message", "buildUnbindUser", e2, new Object[0]);
                e2.printStackTrace();
                return buildUnbindUser;
            }
        } catch (Throwable e3) {
            e2 = e3;
            buildUnbindUser = null;
            ALog.e("Message", "buildUnbindUser", e2, new Object[0]);
            e2.printStackTrace();
            return buildUnbindUser;
        }
        return buildUnbindUser;
    }

    public static Message buildUnbindUser(Context context, String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.m = str;
        message.i = Constants.TARGET_CONTROL;
        message.command = Integer.valueOf(XZBDevice.DOWNLOAD_LIST_ALL);
        message.t = Integer.valueOf(Constants.SDK_VERSION_CODE);
        message.cunstomDataId = KEY_UNBINDUSER;
        return message;
    }

    public static Message buildElection(String str, Map<String, Integer> map) {
        if (map == null) {
            return null;
        }
        Message message = new Message();
        try {
            message.a(1, ReqType.DATA, 1);
            message.B = 1;
            message.m = str;
            message.i = Constants.TARGET_ELECTION;
            message.command = Integer.valueOf(R.styleable.AppCompatTheme_radioButtonStyle);
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : map.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Constants.KEY_ELECTION_PKG, entry.getKey());
                jSONObject.put(Constants.KEY_ELECTION_SDKV, entry.getValue());
                jSONArray.put(jSONObject);
            }
            message.A = new com.taobao.accs.utl.d.a().a(Constants.KEY_ELECTION_SDKV, Integer.valueOf(Constants.SDK_VERSION_CODE)).a(Constants.KEY_ELECTION_PACKS, jSONArray).a().toString().getBytes("utf-8");
        } catch (Throwable th) {
            ALog.e("Message", "buildElection", th, new Object[0]);
        }
        return message;
    }

    public static Message buildStatist(String str, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Message message = new Message();
        message.a(1, ReqType.DATA, 1);
        message.B = 1;
        message.A = bArr;
        message.m = str;
        message.i = Constants.TARGET_STATIST;
        message.command = Integer.valueOf(R.styleable.AppCompatTheme_checkboxStyle);
        return message;
    }

    public static Message buildSendData(Context context, String str, String str2, AccsRequest accsRequest) {
        return buildSendData(context, str, str2, accsRequest, true);
    }

    public static Message buildSendData(Context context, String str, String str2, AccsRequest accsRequest, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.DATA, 1);
        message.command = Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle);
        message.m = str;
        message.serviceId = accsRequest.serviceId;
        message.userinfo = accsRequest.userId;
        message.A = accsRequest.data;
        message.i = new StringBuilder(Constants.TARGET_SERVICE_PRE).append(TextUtils.isEmpty(accsRequest.targetServiceName) ? accsRequest.serviceId : accsRequest.targetServiceName).append("|").append(accsRequest.target == null ? com.umeng.a.d : accsRequest.target).toString();
        message.cunstomDataId = accsRequest.dataId;
        message.bizId = accsRequest.businessId;
        if (accsRequest.timeout > 0) {
            message.timeout = accsRequest.timeout;
        }
        if (z) {
            a(context, message, accsRequest);
        } else {
            message.host = accsRequest.host;
        }
        a(message, GlobalClientInfo.getInstance(context).getSid(), GlobalClientInfo.getInstance(context).getUserId(), GlobalClientInfo.a, accsRequest.businessId, accsRequest.tag);
        message.D = new NetPerformanceMonitor();
        message.D.setDataId(accsRequest.dataId);
        message.D.setServiceId(accsRequest.serviceId);
        message.D.setHost(message.host.toString());
        return message;
    }

    public static Message buildRequest(Context context, String str, String str2, AccsRequest accsRequest, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Message message = new Message();
        message.B = 1;
        message.a(1, ReqType.REQ, 1);
        message.command = Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle);
        message.m = str;
        message.serviceId = accsRequest.serviceId;
        message.userinfo = accsRequest.userId;
        message.A = accsRequest.data;
        message.i = new StringBuilder(Constants.TARGET_SERVICE_PRE).append(TextUtils.isEmpty(accsRequest.targetServiceName) ? accsRequest.serviceId : accsRequest.targetServiceName).append("|").append(accsRequest.target == null ? com.umeng.a.d : accsRequest.target).toString();
        message.cunstomDataId = accsRequest.dataId;
        message.bizId = accsRequest.businessId;
        if (accsRequest.timeout > 0) {
            message.timeout = accsRequest.timeout;
        }
        if (z) {
            a(context, message, accsRequest);
        } else {
            message.host = accsRequest.host;
        }
        a(message, GlobalClientInfo.getInstance(context).getSid(), GlobalClientInfo.getInstance(context).getUserId(), GlobalClientInfo.a, accsRequest.businessId, accsRequest.tag);
        message.D = new NetPerformanceMonitor();
        message.D.setDataId(accsRequest.dataId);
        message.D.setServiceId(accsRequest.serviceId);
        message.D.setHost(message.host.toString());
        return message;
    }

    public static Message buildRequest(Context context, String str, String str2, AccsRequest accsRequest) {
        return buildRequest(context, str, str2, accsRequest, true);
    }

    private static void a(Context context, Message message, AccsRequest accsRequest) {
        if (accsRequest.host == null) {
            try {
                message.host = new URL(com.taobao.accs.net.a.a(context, null));
                return;
            } catch (Throwable e) {
                ALog.e("Message", "setUnit", e, new Object[0]);
                e.printStackTrace();
            }
        }
        message.host = accsRequest.host;
    }

    private static void a(Context context, Message message) {
        try {
            message.host = new URL(com.taobao.accs.net.a.a(context, null));
        } catch (Throwable e) {
            ALog.e("Message", "setControlHost", e, new Object[0]);
        }
    }

    public static Message buildPushAck(String str, String str2, String str3, boolean z, short s, String str4, Map<ExtHeaderType, String> map) {
        Message message = new Message();
        message.B = 1;
        message.a(s, z);
        message.j = str;
        message.i = str2;
        message.dataId = str3;
        message.isAck = true;
        message.l = map;
        try {
            if (TextUtils.isEmpty(str4)) {
                message.host = new URL(com.taobao.accs.net.a.a(GlobalClientInfo.getContext(), null));
            } else {
                message.host = new URL(str4);
            }
            if (message.host == null) {
                try {
                    message.host = new URL(com.taobao.accs.net.a.a(GlobalClientInfo.getContext(), null));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable th) {
            try {
                ALog.e("Message", "buildPushAck", th, new Object[0]);
                if (message.host == null) {
                    try {
                        message.host = new URL(com.taobao.accs.net.a.a(GlobalClientInfo.getContext(), null));
                    } catch (MalformedURLException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Throwable th2) {
                if (message.host == null) {
                    try {
                        message.host = new URL(com.taobao.accs.net.a.a(GlobalClientInfo.getContext(), null));
                    } catch (MalformedURLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        return message;
    }

    public static Message buildParameterError(String str, int i) {
        Message message = new Message();
        message.a(1, ReqType.ACK, 0);
        message.command = Integer.valueOf(i);
        message.m = str;
        return message;
    }

    private static void a(Message message, String str, String str2, String str3, String str4, String str5) {
        if (!TextUtils.isEmpty(str4) || !TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str5) || str3 != null) {
            message.l = new HashMap();
            if (str4 != null && UtilityImpl.getByteLen(str4) <= 1023) {
                message.l.put(ExtHeaderType.TYPE_BUSINESS, str4);
            }
            if (str != null && UtilityImpl.getByteLen(str) <= 1023) {
                message.l.put(ExtHeaderType.TYPE_SID, str);
            }
            if (str2 != null && UtilityImpl.getByteLen(str2) <= 1023) {
                message.l.put(ExtHeaderType.TYPE_USERID, str2);
            }
            if (str5 != null && UtilityImpl.getByteLen(str5) <= 1023) {
                message.l.put(ExtHeaderType.TYPE_TAG, str5);
            }
            if (str3 != null && UtilityImpl.getByteLen(str3) <= 1023) {
                message.l.put(ExtHeaderType.TYPE_COOKIE, str3);
            }
        }
    }

    private void a(int i, ReqType reqType, int i2) {
        this.k = i;
        if (i != 2) {
            this.f = (short) (((((i & 1) << 4) | (reqType.ordinal() << 2)) | i2) << 11);
        }
    }

    private void a(short s, boolean z) {
        this.k = 1;
        this.f = s;
        this.f = (short) (this.f & -16385);
        this.f = (short) (this.f | 8192);
        this.f = (short) (this.f & -2049);
        this.f = (short) (this.f & -65);
        if (z) {
            this.f = (short) (this.f | 32);
        }
    }
}
