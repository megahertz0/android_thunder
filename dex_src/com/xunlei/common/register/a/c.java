package com.xunlei.common.register.a;

import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.umeng.socialize.weixin.BuildConfig;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.register.XLRegisterListener;
import com.xunlei.common.register.b.a;
import com.xunlei.common.register.b.b;
import com.xunlei.common.register.b.d;
import com.xunlei.common.register.b.e;
import com.xunlei.common.register.b.f;
import com.xunlei.common.register.b.g;
import com.xunlei.common.register.b.h;
import com.xunlei.common.register.b.i;
import com.xunlei.common.register.b.j;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.mediaserver.Utility;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: XLRegisterUtilProxy.java
public final class c {
    private static int a = 0;
    private static int b = 1;
    private static int c = 2;
    private static int d = 3;
    private static int e = 4;
    private static int f = 5;
    private static int g = 6;
    private static int h = 7;
    private static int i = 8;
    private static int j = 9;
    private static int k = 268439588;
    private static c l;
    private List<XLRegisterListener> m;
    private Context n;
    private Handler o;
    private String p;
    private int q;
    private boolean r;
    private String s;
    private String t;
    private String u;
    private Activity v;
    private boolean w;
    private XLStatUtil x;
    private d y;
    private ContentObserver z;

    // compiled from: XLRegisterUtilProxy.java
    final class AnonymousClass_1 extends ContentObserver {
        AnonymousClass_1(Handler handler) {
            super(handler);
        }

        public final void onChange(boolean z) {
            super.onChange(z);
            c.this.o.sendEmptyMessage(268439588);
        }
    }

    static {
        l = null;
    }

    private c() {
        this.m = new Vector();
        this.n = null;
        this.o = null;
        this.p = "test";
        this.q = -1;
        this.r = false;
        this.s = "1.6.6.177628";
        this.t = BuildConfig.VERSION_NAME;
        this.u = "ABCDEF";
        this.v = null;
        this.w = false;
        this.x = null;
        this.y = null;
        this.z = new AnonymousClass_1(this.o);
    }

    private static String j() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            XLLog.e("WifiPreference IpAddress", e.toString());
        }
        return null;
    }

    private InetAddress k() throws UnknownHostException {
        int ipAddress = ((WifiManager) this.n.getSystemService(Utility.NETWORK_WIFI)).getConnectionInfo().getIpAddress();
        return InetAddress.getByName(String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf(ipAddress >>> 24)}));
    }

    public final void a(int i, Object... objArr) {
        this.o.obtainMessage(i, objArr).sendToTarget();
    }

    private void a(Message message) {
        int i = message.what;
        Object[] objArr = (Object[]) message.obj;
        int size;
        if (i == 0) {
            for (size = this.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) this.m.get(size)).onPhoneRegister(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 2) {
            for (size = this.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) this.m.get(size)).onCheckBind(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
            }
        } else if (i == 3) {
            for (size = this.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) this.m.get(size)).onSendMessage(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 4) {
            for (size = this.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) this.m.get(size)).onCheckNeedVerifyCode(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 5) {
            for (int size2 = this.m.size() - 1; size2 >= 0; size2--) {
                ((XLRegisterListener) this.m.get(size2)).onGetVerifyCode(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (byte[]) objArr[3], (String) objArr[4], (String) objArr[5], (String) objArr[6], (String) objArr[7]);
            }
        } else if (i == 1) {
            for (size = this.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) this.m.get(size)).onEmailRegister(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 7) {
            for (size = this.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) this.m.get(size)).onCheckPassWordStrength(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
            }
        } else if (i == 8) {
            for (size = this.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) this.m.get(size)).onPhoneRegAndLogin(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 9) {
            for (size = this.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) this.m.get(size)).onOldUserNameRegister(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 268439588) {
            String str;
            Activity activity = this.v;
            String str2 = com.xunlei.xiazaibao.BuildConfig.VERSION_NAME;
            if (activity != null) {
                Cursor managedQuery = activity.managedQuery(Uri.parse("content://sms/inbox"), new String[]{"address", AgooConstants.MESSAGE_BODY}, "read=0", new String[0], "date desc");
                if (managedQuery != null && managedQuery.getCount() > 0) {
                    managedQuery.moveToFirst();
                    Object string = managedQuery.getString(managedQuery.getColumnIndex(AgooConstants.MESSAGE_BODY));
                    if (string.indexOf("\u8fc5\u96f7\u7f51\u7edc") != -1) {
                        str = com.xunlei.xiazaibao.BuildConfig.VERSION_NAME;
                        Matcher matcher = Pattern.compile("\\d{6}").matcher(string);
                        if (matcher.find()) {
                            str = matcher.group();
                        }
                        i = SimpleLog.LOG_LEVEL_DEBUG;
                        if (string.indexOf("\u6ce8\u518c") != -1) {
                            i = 1;
                        }
                        str = i + str;
                        if (str != null && str != com.xunlei.xiazaibao.BuildConfig.VERSION_NAME) {
                            String substring = str.substring(0, 1);
                            String substring2 = str.substring(1);
                            for (i = this.m.size() - 1; i >= 0; i--) {
                                ((XLRegisterListener) this.m.get(i)).onMobileVerifyCodeAccept(substring2, Integer.valueOf(substring).intValue());
                            }
                            this.v = null;
                            return;
                        }
                    }
                }
            }
            str = str2;
            if (str != null) {
            }
        }
    }

    public static c a() {
        if (l != null) {
            return l;
        }
        synchronized (c.class) {
            if (l == null) {
                l = new c();
            }
        }
        return l;
    }

    public final boolean a(Context context, int i, String str, String str2, String str3, String str4) {
        if (this.r || Looper.myLooper() == null) {
            return false;
        }
        this.o = new Handler() {
            public final void handleMessage(Message message) {
                c.a(c.this, message);
            }
        };
        a.a().a(context);
        this.n = context;
        this.q = i;
        this.p = str;
        this.r = true;
        this.t = str2;
        this.u = str3;
        d.a = str4;
        if (!this.w) {
            this.n.getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, this.z);
            this.w = true;
        }
        this.x = XLStatUtil.getInstance();
        this.x.init(this.n, this.q, this.t, this.s, this.u);
        this.y = new d(this.x);
        a(this.y);
        return true;
    }

    private void l() {
        this.t = this.s;
        this.u = "ABCDEF";
    }

    public final boolean b() {
        b(this.y);
        a.a();
        this.x.uninit();
        return true;
    }

    public final void a(XLRegisterListener xLRegisterListener) {
        if (this.r && !this.m.contains(xLRegisterListener)) {
            this.m.add(xLRegisterListener);
        }
    }

    public final void b(XLRegisterListener xLRegisterListener) {
        if (this.r && this.m.contains(xLRegisterListener)) {
            this.m.remove(xLRegisterListener);
        }
    }

    private void m() {
        if (this.r) {
            this.m.clear();
        }
    }

    public final Context c() {
        return this.n;
    }

    public final int d() {
        return this.q;
    }

    public final String e() {
        return this.s;
    }

    public final String f() {
        return this.t;
    }

    public final String g() {
        return this.p;
    }

    public final String h() {
        return this.n.getApplicationInfo().packageName;
    }

    private void n() {
        if (!this.w) {
            this.n.getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, this.z);
            this.w = true;
        }
    }

    private void o() {
        if (this.w) {
            this.n.getContentResolver().unregisterContentObserver(this.z);
            this.w = false;
        }
    }

    private static String c(String str) {
        String str2 = com.xunlei.xiazaibao.BuildConfig.VERSION_NAME;
        Matcher matcher = Pattern.compile("\\d{6}").matcher(str);
        return matcher.find() ? matcher.group() : str2;
    }

    private String a(Activity activity) {
        String str = com.xunlei.xiazaibao.BuildConfig.VERSION_NAME;
        if (activity != null) {
            Activity activity2 = activity;
            Cursor managedQuery = activity2.managedQuery(Uri.parse("content://sms/inbox"), new String[]{"address", AgooConstants.MESSAGE_BODY}, "read=0", new String[0], "date desc");
            if (managedQuery != null && managedQuery.getCount() > 0) {
                managedQuery.moveToFirst();
                Object string = managedQuery.getString(managedQuery.getColumnIndex(AgooConstants.MESSAGE_BODY));
                if (string.indexOf("\u8fc5\u96f7\u7f51\u7edc") != -1) {
                    int i;
                    String str2 = com.xunlei.xiazaibao.BuildConfig.VERSION_NAME;
                    Matcher matcher = Pattern.compile("\\d{6}").matcher(string);
                    if (matcher.find()) {
                        str2 = matcher.group();
                    }
                    if (string.indexOf("\u6ce8\u518c") != -1) {
                        i = 1;
                    } else {
                        i = 2;
                    }
                    return i + str2;
                }
            }
        }
        return str;
    }

    private boolean p() {
        return this.v != null;
    }

    public final int a(String str, int i) {
        if (!this.r) {
            return -1;
        }
        a aVar = new a(this);
        aVar.d(str);
        aVar.a(i);
        aVar.e();
        return aVar.a();
    }

    public final int a(String str, int i, Activity activity, String str2, String str3, String str4, boolean z) {
        if (!this.r) {
            return -1;
        }
        this.v = activity;
        j jVar = new j(this);
        jVar.a(str2, str3, str4);
        jVar.a(i);
        jVar.d(str);
        jVar.a(true);
        jVar.e();
        this.x.registerStatReq(jVar.a());
        this.x.registerSpecialStatReq(jVar.a(), i);
        return jVar.a();
    }

    public final int a(String str, String str2, String str3) {
        if (!this.r) {
            return -1;
        }
        i iVar = new i(this);
        iVar.e(str2);
        iVar.d(str);
        iVar.a(0);
        iVar.e();
        this.x.registerStatReq(iVar.a());
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200011;
        if (XLStatUtil.mAcceptPhoneCode) {
            xLStatPack.mCommandID = 200010;
            XLStatUtil.mAcceptPhoneCode = false;
        }
        xLStatPack.mErrorCode = 0;
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        this.x.reportSpecialStat(SimpleLog.LOG_LEVEL_DEBUG, xLStatPack);
        this.x.registerSpecialStatReq(iVar.a(), SimpleLog.LOG_LEVEL_DEBUG);
        return iVar.a();
    }

    public final int a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (!this.r) {
            return -1;
        }
        e eVar = new e(this);
        eVar.d(str);
        eVar.e(str2);
        eVar.g(str3);
        eVar.h(str4);
        eVar.i(str5);
        try {
            eVar.a(k().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        eVar.e();
        this.x.registerStatReq(eVar.a());
        return eVar.a();
    }

    public final int a(String str, String str2, String str3, String str4) {
        if (!this.r) {
            return -1;
        }
        h hVar = new h(this);
        hVar.a(str, str2, str3);
        try {
            hVar.a(k().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        hVar.e();
        this.x.registerStatReq(hVar.a());
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 200011;
        if (XLStatUtil.mAcceptPhoneCode) {
            xLStatPack.mCommandID = 200010;
            XLStatUtil.mAcceptPhoneCode = false;
        }
        xLStatPack.mErrorCode = 0;
        xLStatPack.mSvrDomain = "zhuce.xunlei.com";
        this.x.reportSpecialStat(1, xLStatPack);
        this.x.registerSpecialStatReq(hVar.a(), 1);
        return hVar.a();
    }

    public final int b(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (!this.r) {
            return -1;
        }
        g gVar = new g(this);
        gVar.a(str, str2, str3, str4, str5);
        try {
            gVar.a(k().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        gVar.e();
        this.x.registerStatReq(gVar.a());
        return gVar.a();
    }

    public final int i() {
        if (!this.r) {
            return -1;
        }
        b bVar = new b(this);
        try {
            bVar.a(k().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bVar.e();
        return bVar.a();
    }

    public final int a(String str) {
        if (!this.r) {
            return -1;
        }
        f fVar = new f(this);
        fVar.d(str);
        fVar.e();
        this.x.registerStatReq(fVar.a());
        return fVar.a();
    }

    public final int b(String str) {
        if (!this.r) {
            return -1;
        }
        com.xunlei.common.register.b.c cVar = new com.xunlei.common.register.b.c(this);
        cVar.d(str);
        cVar.e();
        return cVar.a();
    }

    static /* synthetic */ void a(c cVar, Message message) {
        int i = message.what;
        Object[] objArr = (Object[]) message.obj;
        int size;
        if (i == 0) {
            for (size = cVar.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) cVar.m.get(size)).onPhoneRegister(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 2) {
            for (size = cVar.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) cVar.m.get(size)).onCheckBind(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
            }
        } else if (i == 3) {
            for (size = cVar.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) cVar.m.get(size)).onSendMessage(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 4) {
            for (size = cVar.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) cVar.m.get(size)).onCheckNeedVerifyCode(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 5) {
            for (int size2 = cVar.m.size() - 1; size2 >= 0; size2--) {
                ((XLRegisterListener) cVar.m.get(size2)).onGetVerifyCode(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (byte[]) objArr[3], (String) objArr[4], (String) objArr[5], (String) objArr[6], (String) objArr[7]);
            }
        } else if (i == 1) {
            for (size = cVar.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) cVar.m.get(size)).onEmailRegister(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 7) {
            for (size = cVar.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) cVar.m.get(size)).onCheckPassWordStrength(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
            }
        } else if (i == 8) {
            for (size = cVar.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) cVar.m.get(size)).onPhoneRegAndLogin(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 9) {
            for (size = cVar.m.size() - 1; size >= 0; size--) {
                ((XLRegisterListener) cVar.m.get(size)).onOldUserNameRegister(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue(), (String) objArr[4]);
            }
        } else if (i == 268439588) {
            String str;
            Activity activity = cVar.v;
            String str2 = com.xunlei.xiazaibao.BuildConfig.VERSION_NAME;
            if (activity != null) {
                Cursor managedQuery = activity.managedQuery(Uri.parse("content://sms/inbox"), new String[]{"address", AgooConstants.MESSAGE_BODY}, "read=0", new String[0], "date desc");
                if (managedQuery != null && managedQuery.getCount() > 0) {
                    managedQuery.moveToFirst();
                    Object string = managedQuery.getString(managedQuery.getColumnIndex(AgooConstants.MESSAGE_BODY));
                    if (string.indexOf("\u8fc5\u96f7\u7f51\u7edc") != -1) {
                        str = com.xunlei.xiazaibao.BuildConfig.VERSION_NAME;
                        Matcher matcher = Pattern.compile("\\d{6}").matcher(string);
                        if (matcher.find()) {
                            str = matcher.group();
                        }
                        i = SimpleLog.LOG_LEVEL_DEBUG;
                        if (string.indexOf("\u6ce8\u518c") != -1) {
                            i = 1;
                        }
                        str = i + str;
                        if (str != null && str != com.xunlei.xiazaibao.BuildConfig.VERSION_NAME) {
                            String substring = str.substring(0, 1);
                            String substring2 = str.substring(1);
                            for (i = cVar.m.size() - 1; i >= 0; i--) {
                                ((XLRegisterListener) cVar.m.get(i)).onMobileVerifyCodeAccept(substring2, Integer.valueOf(substring).intValue());
                            }
                            cVar.v = null;
                            return;
                        }
                    }
                }
            }
            str = str2;
            if (str != null) {
            }
        }
    }
}
