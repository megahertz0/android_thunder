package com.xunlei.downloadprovider.member.login;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.toolbox.t;
import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLixianUtil;
import com.xunlei.common.member.XLBindedOtherAccountItem;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLThirdUserInfo;
import com.xunlei.common.member.XLUserInfo;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.yunbo.XLYunboUtil;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.app.GuideActivity;
import com.xunlei.downloadprovider.launch.LaunchActivity;
import com.xunlei.downloadprovider.loading.LoadingActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper.SexType;
import com.xunlei.downloadprovider.member.login.LoginHelper.m;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.member.login.ui.XLTwoButtonDialogActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.qrcode.CameraActivity;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.android.spdy.SpdyAgent;
import org.json.JSONException;
import org.json.JSONObject;

public final class LoginHelper {
    public static Timer J;
    public static f K;
    private static String Q;
    private static String R;
    private static String S;
    private static LoginHelper T;
    public static int a;
    public static String b;
    public static String c;
    public static volatile boolean d;
    public String A;
    public n B;
    public boolean C;
    public b D;
    public ArrayList<b> E;
    public boolean F;
    public boolean G;
    protected a H;
    public XLOnUserListener I;
    public r L;
    public q M;
    public l N;
    public k O;
    private final String P;
    private SharedPreferences U;
    private volatile boolean V;
    private boolean W;
    private boolean X;
    private List<d> Y;
    private List<g> Z;
    private List<p> aa;
    private Handler ab;
    private Handler ac;
    private int ad;
    private int ae;
    private h af;
    private BroadcastReceiver ag;
    private List<m> ah;
    private j ai;
    private i aj;
    public int e;
    public int f;
    public int g;
    public int h;
    public String i;
    public long j;
    public String k;
    public String l;
    String m;
    public String n;
    public boolean o;
    public boolean p;
    int q;
    String r;
    int s;
    int t;
    public Notification u;
    public c v;
    public List<o> w;
    public long x;
    public long y;
    public int z;

    public static interface d {
        void a(int i, int i2, boolean z, Object obj);
    }

    public static interface g {
        void a();
    }

    public static interface p {
        void OnRefreshUserInfoCompleted(int i, boolean z);
    }

    public static interface c {
        void a(int i);
    }

    public static interface i {
        void a(int i, int i2);

        void a(int i, XLThirdUserInfo xLThirdUserInfo);
    }

    public static interface l {
        void a();

        void a(int i);
    }

    public static interface r {
        void a(int i);
    }

    public static interface q {
        void a(int i, String str);
    }

    public static interface k {
        void a(XLThirdUserInfo xLThirdUserInfo);
    }

    public static interface j {
        void a(XLBindedOtherAccountItem[] xLBindedOtherAccountItemArr);
    }

    public static interface m {
        void a();
    }

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[SexType.values().length];
            try {
                a[SexType.unKnow.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[SexType.male.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[SexType.female.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum SexType {
        unKnow,
        male,
        female;

        static {
            unKnow = new com.xunlei.downloadprovider.member.login.LoginHelper.SexType("unKnow", 0);
            male = new com.xunlei.downloadprovider.member.login.LoginHelper.SexType("male", 1);
            female = new com.xunlei.downloadprovider.member.login.LoginHelper.SexType("female", 2);
            a = new com.xunlei.downloadprovider.member.login.LoginHelper.SexType[]{unKnow, male, female};
        }
    }

    public static interface a {
        void a(int i, String str, byte[] bArr);
    }

    public static interface b {
        void a(int i, b bVar);
    }

    private class e extends Handler {
        private e() {
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 110001:
                    if (LoginHelper.this.ah != null) {
                        for (int i = 0; i < LoginHelper.this.ah.size(); i++) {
                            if (LoginHelper.this.ah.get(i) != null) {
                                ((m) LoginHelper.this.ah.get(i)).a();
                            }
                        }
                    }
                default:
                    break;
            }
        }
    }

    private class f extends TimerTask {
        private f() {
        }

        public final void run() {
            com.xunlei.downloadprovider.frame.user.a a = com.xunlei.downloadprovider.frame.user.a.a();
            String str = LoginHelper.this.j;
            new StringBuilder().append(a.getClass()).append("---postUserLogin(String userId)---").append(Thread.currentThread().getId());
            Request tVar = new t(new StringBuilder("http://jifenshangcheng.m.xunlei.com/cgi-bin/integra_busi_contin?").append(new StringBuilder("userId=").append(str).toString()).toString(), new com.xunlei.downloadprovider.frame.user.b(a), new com.xunlei.downloadprovider.frame.user.c(a));
            tVar.setShouldCache(false);
            tVar.setRetryPolicy(new com.android.volley.f(10000, 1, 1.0f));
            a.a(tVar);
        }
    }

    public static interface h {
        void a(int i);
    }

    public class n {
        public int a;
        public int b;
        public String c;
        public int d;
        public String e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public String l;
    }

    public static interface o {
        void a(int i, long j, long j2);
    }

    static {
        a = 2;
        Q = "USER_DATA_LOGIN_TYPE_AUTO_LOGIN";
        R = "USER_DATA_LOGIN_TYPE_MANUL_AUTO_LOGIN";
        S = "logintype_userdata_userUnionLogin";
        b = "logintype_userdata_userLoginHasVerifyCode";
        c = "logintype_userdata_logintype_userloginpage";
        T = null;
    }

    public static LoginHelper a() {
        if (T == null) {
            T = new LoginHelper();
        }
        return T;
    }

    public LoginHelper() {
        this.P = "LoginHelper";
        this.V = false;
        this.W = false;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = 0;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = com.umeng.a.d;
        this.p = false;
        this.q = 0;
        this.r = null;
        this.s = 0;
        this.t = 0;
        this.u = null;
        this.x = 0;
        this.y = 0;
        this.ac = null;
        this.z = 0;
        this.ad = 1;
        this.ae = 0;
        this.E = new ArrayList();
        this.F = false;
        this.G = false;
        this.I = new h(this);
        this.ag = new i(this);
        this.ah = new ArrayList();
        this.U = BrothersApplication.a.getSharedPreferences("login", 0);
        this.Y = new ArrayList();
        this.Z = new ArrayList();
        this.aa = new ArrayList();
        this.w = new ArrayList();
        this.ab = new e();
        y();
        XLUserUtil.getInstance().attachListener(new c(this));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        BrothersApplication.a().registerReceiver(this.ag, intentFilter);
    }

    public static void b() {
        XLUserUtil.getInstance().clearAutoLoginPassword(true);
    }

    public final void a(int i) {
        Intent intent = new Intent();
        Context a = BrothersApplication.a();
        intent.setClass(a, XLTwoButtonDialogActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(JsInterface.FUNPLAY_AD_TRPE, i);
        a.startActivity(intent);
        this.C = false;
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g gVar = new com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g();
        gVar.c = "login_try_alert";
        gVar.a = "android_user_login";
        gVar.b = "login_try_alert";
        ThunderReporter.a(gVar, true);
    }

    public static boolean c() {
        return XLUserUtil.getInstance().userIsOnline();
    }

    public static boolean d() {
        return XLUserUtil.getInstance().userIsLogined();
    }

    public final String e() {
        return this.U.getString("userName", null);
    }

    public final boolean f() {
        return this.z != 0 && this.z == 1;
    }

    public final boolean g() {
        return 2 == this.h;
    }

    public final boolean h() {
        return 1 == this.h;
    }

    public final boolean i() {
        return 3 == this.h;
    }

    public final boolean j() {
        return 4 == this.h;
    }

    public final boolean k() {
        return 5 == this.h;
    }

    public final boolean l() {
        if (!f()) {
            boolean z;
            if (this.B != null) {
                int i = this.B.a;
                if (i != this.ae && i == this.ad) {
                    z = true;
                    if (z) {
                        return true;
                    }
                }
            }
            Object obj = null;
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean m() {
        if (!l()) {
            return this.g <= 200 && this.g % 5 == 0 && this.g % 10 != 0;
        } else {
            if (this.B != null) {
                int i = this.B.k;
                if (i != this.ae && i == this.ad) {
                    return true;
                }
            }
            return false;
        }
    }

    public final String n() {
        return l() ? this.B.c : this.m;
    }

    public static boolean p() {
        new StringBuilder("isLogging()=").append(d);
        return d;
    }

    final void b(int i) {
        this.U.edit().putInt("level", i).apply();
    }

    private void d(int i) {
        this.U.edit().putInt("current_account", i).apply();
    }

    private void c(String str) {
        this.U.edit().putString("portait", str).apply();
    }

    private void d(String str) {
        this.U.edit().putString("nickName", str).apply();
    }

    private void b(boolean z) {
        this.U.edit().putBoolean("is_vip", z).apply();
    }

    public final void a(String str) {
        if (str != null) {
            this.U.edit().putString("userPwd", com.xunlei.xllib.b.g.a(str.getBytes())).apply();
            return;
        }
        this.U.edit().putString("userPwd", null).apply();
    }

    private void c(boolean z) {
        this.U.edit().putBoolean("is_diamond", z).apply();
    }

    public final void a(boolean z) {
        boolean z2;
        this.p = false;
        Object obj = z ? R : Q;
        j.a();
        if (j.a(this.I, obj) > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            d = true;
            this.F = false;
        }
    }

    public static XLUserInfo q() {
        return XLUserUtil.getInstance().getCurrentUser();
    }

    public static void r() {
        XLUserUtil.getInstance().getCurrentUser().clearUserData();
    }

    public final void a(int i, boolean z) {
        if (this.aa != null) {
            for (int i2 = 0; i2 < this.aa.size(); i2++) {
                if (this.aa.get(i2) != null) {
                    ((p) this.aa.get(i2)).OnRefreshUserInfoCompleted(i, z);
                }
            }
        }
    }

    public final void a(Context context, c cVar, int i) {
        this.o = false;
        this.v = cVar;
        String str = null;
        if (i == 18) {
            str = "download_vip_try";
        }
        a(context, i, str);
    }

    public final void a(Context context, c cVar, int i, String str) {
        this.o = false;
        this.v = cVar;
        if (str != null && ((((((((str.equals("\u8d44\u6e90\u8be6\u60c5_cloud") | str.equals("\u624b\u96f7\u522e\u522e\u4e50")) | str.equals("\u6e38\u620f\u4e2d\u5fc3")) | str.equals("\u79bb\u7ebf\u7a7a\u95f4")) | str.equals("\u64ad\u653e\u8bb0\u5f55")) | str.equals("\u8da3\u73a9\u9876\u90e8")) | str.equals("_user_sign")) | str.equals("_comment")) | str.equals("RemoteDownloadActivity")) == 0) {
            str = "_other";
        }
        a(context, i, str);
    }

    private static void a(Context context, int i, String str) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("login_from", context.getClass().getSimpleName());
        if (str != null) {
            if ("download_vip_try".equals(str)) {
                intent.putExtra("login_from", str);
            } else if (context.getClass().getSimpleName().equals(str)) {
                intent.putExtra("login_from", context.getClass().getSimpleName());
            } else {
                intent.putExtra("login_from", context.getClass().getSimpleName() + str);
            }
        }
        intent.putExtra("login_type", i);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private static String a(long j) {
        return 0 != j ? com.xunlei.downloadprovider.businessutil.a.b(BrothersApplication.a) + "big_" + String.valueOf(j) : null;
    }

    private void a(int i, int i2, int i3, String str, String str2, String str3, String str4, String str5, int i4, String str6, int i5, String str7, Object obj) {
        int i6;
        new StringBuilder("onUserLoginCompleted() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i)).append(" userData=").append(obj);
        if (i == 0) {
            v();
            w();
            this.W = true;
            this.k = str2;
            if (TextUtils.isEmpty(str)) {
                StatReporter.reportUseridCrash(str7);
            }
            this.j = Long.parseLong(str);
            z();
            this.g = i2;
            this.l = str3;
            this.m = str5;
            this.h = i3;
            this.f = i4;
            this.i = str4;
            this.n = str6;
            this.z = i5;
            d(this.f);
            b(f());
            c(j());
            String a = a(this.j);
            if (a != null) {
                File file = new File(a);
                if (!(file.exists() && file.isFile())) {
                    a = null;
                }
            }
            if (a != null) {
                this.l = a;
            }
            c(this.l);
            d(this.i);
            long j = this.j;
            if (j != 0) {
                Runnable gVar = new com.xunlei.downloadprovider.b.c.g(String.format("http://img.user.kanimg.com/usrimg/%1$s/100x100", new Object[]{Long.valueOf(j)}));
                gVar.b = new d(this, j);
                new Thread(gVar).start();
            }
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            com.xunlei.downloadprovider.service.downloads.task.d.a(this.j, this.n);
            if ((f() || this.y > 0) && com.xunlei.downloadprovider.businessutil.b.a().b()) {
                com.xunlei.downloadprovider.service.downloads.task.d.a();
                com.xunlei.downloadprovider.service.downloads.task.d.h();
            }
            XLLX_INITDATA xllx_initdata = new XLLX_INITDATA();
            xllx_initdata.peerId = com.xunlei.downloadprovider.a.b.d();
            xllx_initdata.userId = this.j;
            xllx_initdata.userJumpKey = this.n;
            xllx_initdata.userName = e();
            xllx_initdata.userSessionId = this.k;
            xllx_initdata.userVipLevel = (byte) this.e;
            XLLixianUtil.getInstance().init(BrothersApplication.a().getApplicationContext(), xllx_initdata);
        } else {
            this.W = false;
        }
        if (2 == i) {
            t();
            b(0);
            d(0);
            b(false);
            c(false);
            c(null);
            d(null);
            a(null);
        } else if (3 == i || 6 == i) {
            a(null);
        }
        if (m()) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i7 = this.h;
        if (!(i == 2 || i == 3)) {
            StatReporter.reportMemberLogin(i, i7, i6, 0);
        }
        J = new Timer();
        K = new f();
        J.schedule(K, com.umeng.analytics.a.i, com.umeng.analytics.a.i);
        this.V = this.W;
        d = false;
        if (XLUserUtil.getInstance().userIsOnline()) {
            if (this.p) {
                t();
                a(null);
            } else {
                if (this.v != null) {
                    this.v.a(i);
                    this.v = null;
                }
                if (this.Y != null) {
                    List arrayList = new ArrayList();
                    arrayList.addAll(this.Y);
                    for (i7 = 0; i7 < arrayList.size(); i7++) {
                        new StringBuilder("onUserLoginCompleted() call observer=").append(((d) arrayList.get(i7)).getClass().getName());
                        ((d) arrayList.get(i7)).a(0, i, this.o, obj);
                    }
                }
            }
            XZBShouleiUtil.getInstance().bindUser(Long.valueOf(a().j).longValue(), a().e(), a().k, a().n, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu);
            XZBShouleiUtil.getInstance().updateDeviceList("shoulei_xzb", new com.xunlei.downloadprovider.download.tasklist.list.xzb.i(com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a()));
        } else {
            if (this.v != null) {
                this.v.a(i);
                this.v = null;
            }
            if (this.Y != null) {
                for (i7 = 0; i7 < this.Y.size(); i7++) {
                    new StringBuilder("onUserLoginCompleted() call observer=").append(((d) this.Y.get(i7)).getClass().getName());
                    ((d) this.Y.get(i7)).a(0, i, this.o, obj);
                }
            }
        }
        com.xunlei.downloadprovider.member.b.b.a(null).a("9;13");
    }

    public final void t() {
        this.V = false;
        d = false;
        this.k = null;
        this.j = 0;
        this.h = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.i = null;
        this.m = null;
        this.l = null;
        this.n = null;
    }

    public final void a(h hVar) {
        this.af = hVar;
        j.a();
        j.a(this.I);
    }

    private void e(int i) {
        new StringBuilder("onUserLogoutCompleted() event=8, errCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        if (this.af != null) {
            this.af.a(i);
        }
        if (this.Z != null) {
            for (int i2 = 0; i2 < this.Z.size(); i2++) {
                if (this.Z.get(i2) != null) {
                    ((g) this.Z.get(i2)).a();
                    new StringBuilder("mLogoutByUser=").append(this.X);
                    XZBShouleiUtil.getInstance().clearUser();
                }
            }
        }
        t();
        this.x = 0;
        this.y = 0;
        t();
        a(null);
        XLLixianUtil.getInstance().uninit();
        XLYunboUtil.getInstance().uninit();
        this.G = false;
        this.F = false;
        if (J != null) {
            J.cancel();
            K = null;
        }
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        com.xunlei.downloadprovider.service.downloads.task.d.a(0, null);
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        com.xunlei.downloadprovider.service.downloads.task.d.b(true);
    }

    public final void u() {
        new StringBuilder("loginWithoutGUI() login_state=").append(this.V);
        if (com.xunlei.xllib.a.b.a(BrothersApplication.a)) {
            this.v = null;
            this.o = true;
            a(false);
        }
    }

    public final void w() {
        XLLixianUtil.getInstance().obtainLixianUserInfo(com.umeng.a.d, new f(this));
    }

    public final void a(int i, b bVar) {
        if (i == 0) {
            this.D = bVar;
        }
        this.ab.post(new g(this, i, bVar));
    }

    public final void a(b bVar) {
        this.E.add(bVar);
    }

    public final void a(d dVar) {
        int i = 0;
        while (i < this.Y.size()) {
            if (!dVar.equals(this.Y.get(i))) {
                i++;
            } else {
                return;
            }
        }
        this.Y.add(dVar);
    }

    public final void b(d dVar) {
        this.Y.remove(dVar);
    }

    public final void a(p pVar) {
        this.aa.add(pVar);
    }

    public final void b(p pVar) {
        this.aa.remove(pVar);
    }

    public final void a(g gVar) {
        if (!this.Z.contains(gVar)) {
            this.Z.add(gVar);
        }
    }

    public final void b(g gVar) {
        this.Z.remove(gVar);
    }

    public final void a(o oVar) {
        this.w.add(oVar);
    }

    private boolean y() {
        if (!this.U.getBoolean("fix_wrong_peerid", false)) {
            a(null);
            this.U.edit().putBoolean("fix_wrong_peerid", true).apply();
        }
        try {
            j a = j.a();
            boolean Init = XLUserUtil.getInstance().Init(BrothersApplication.a, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, BrothersApplication.a.getResources().getString(R.string.version), com.xunlei.downloadprovider.a.b.d(), "34a062aaa22f906fca4fefe9fb3a3021");
            XLUserUtil.getInstance().attachListener(a.a);
            return Init;
        } catch (Exception e) {
            return false;
        }
    }

    private int z() {
        return XLUserUtil.getInstance().userGetInfo(null, this.I, XZBDevice.Predownload, this.H);
    }

    public final void a(int i, i iVar) {
        this.aj = iVar;
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                XLUserUtil.getInstance().userBindOtherAccount(i, "4286195229", "http://m.xunlei.com", this.I, null);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                XLUserUtil.getInstance().userBindOtherAccount(i, "wx3e6556568beeebdd", com.umeng.a.d, this.I, null);
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                XLUserUtil.getInstance().userBindOtherAccount(i, "1101105049", com.umeng.a.d, this.I, null);
            default:
                break;
        }
    }

    public final void a(j jVar) {
        this.ai = jVar;
        XLUserUtil.getInstance().userGetBindedOtherAccount(this.I, null);
    }

    public static String c(int i) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "4286195229";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "wx3e6556568beeebdd";
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return "1101105049";
            default:
                return com.umeng.a.d;
        }
    }

    public final void a(a aVar) {
        this.H = aVar;
        XLUserUtil.getInstance().userGetVerifyCode(this.I, null);
    }

    public final void a(m mVar) {
        int i = 0;
        while (i < this.ah.size()) {
            if (!mVar.equals(this.ah.get(i))) {
                i++;
            } else {
                return;
            }
        }
        this.ah.add(mVar);
    }

    public final void b(m mVar) {
        if (this.ah != null) {
            this.ah.remove(mVar);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void x() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.member.login.LoginHelper.x():void");
        /*
        this = this;
        a();
        r0 = com.xunlei.common.member.XLUserUtil.getInstance();
        r0 = r0.userIsOnline();
        if (r0 == 0) goto L_0x00d9;
    L_0x000d:
        r10 = new com.xunlei.downloadprovider.homepage.a.a.a;
        r0 = r11.ab;
        r10.<init>(r0);
        r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00e5 }
        r1.<init>();	 Catch:{ Exception -> 0x00e5 }
        r0 = "userid";
        r2 = a();	 Catch:{ Exception -> 0x00e5 }
        r2 = r2.j;	 Catch:{ Exception -> 0x00e5 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x00e5 }
        r0 = "expire";
        r2 = com.xunlei.downloadprovider.homepage.a.a.c.a();	 Catch:{ Exception -> 0x00e5 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x00e5 }
        r0 = "vas_type";
        r2 = a();	 Catch:{ Exception -> 0x00e5 }
        r2 = r2.h;	 Catch:{ Exception -> 0x00e5 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x00e5 }
        r0 = a();	 Catch:{ Exception -> 0x00e5 }
        r0 = r0.m();	 Catch:{ Exception -> 0x00e5 }
        if (r0 == 0) goto L_0x00da;
    L_0x0045:
        r0 = "isyear";
        r2 = "1";
        r1.put(r0, r2);	 Catch:{ Exception -> 0x00e5 }
    L_0x004e:
        r0 = "level";
        r2 = a();	 Catch:{ Exception -> 0x00e5 }
        r2 = r2.e;	 Catch:{ Exception -> 0x00e5 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x00e5 }
        r0 = "style";
        r2 = "2;3;4;5;6";
        r1.put(r0, r2);	 Catch:{ Exception -> 0x00e5 }
        r0 = "version";
        r2 = com.xunlei.downloadprovider.a.b.w();	 Catch:{ Exception -> 0x00e5 }
        r1.put(r0, r2);	 Catch:{ Exception -> 0x00e5 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e5 }
        r0.<init>();	 Catch:{ Exception -> 0x00e5 }
        r2 = r10.getClass();	 Catch:{ Exception -> 0x00e5 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00e5 }
        r2 = "---showVipContinue---obj.toString()//////////////////////---";
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00e5 }
        r2 = r1.toString();	 Catch:{ Exception -> 0x00e5 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00e5 }
        r2 = "---";
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00e5 }
        r2 = java.lang.Thread.currentThread();	 Catch:{ Exception -> 0x00e5 }
        r2 = r2.getId();	 Catch:{ Exception -> 0x00e5 }
        r0.append(r2);	 Catch:{ Exception -> 0x00e5 }
        r0 = new com.xunlei.downloadprovider.b.c.a;	 Catch:{ Exception -> 0x00e5 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e5 }
        r3 = "http://bubble.vip.xunlei.com/service/bubble?request=querybubble&protocol=101&querystr=";
        r2.<init>(r3);	 Catch:{ Exception -> 0x00e5 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00e5 }
        r3 = "utf-8";
        r1 = java.net.URLEncoder.encode(r1, r3);	 Catch:{ Exception -> 0x00e5 }
        r1 = r2.append(r1);	 Catch:{ Exception -> 0x00e5 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00e5 }
        r2 = "GET";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = new com.xunlei.downloadprovider.homepage.a.a.e;	 Catch:{ Exception -> 0x00e5 }
        r6.<init>();	 Catch:{ Exception -> 0x00e5 }
        r7 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r8 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r9 = 1;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00e5 }
        r1 = new com.xunlei.downloadprovider.homepage.a.a.b;	 Catch:{ Exception -> 0x00e5 }
        r1.<init>(r10);	 Catch:{ Exception -> 0x00e5 }
        r0.setBpOnDataLoaderCompleteListener(r1);	 Catch:{ Exception -> 0x00e5 }
        r10.setBpFuture(r0);	 Catch:{ Exception -> 0x00e5 }
        com.xunlei.downloadprovider.homepage.a.a.a.runBox(r10);	 Catch:{ Exception -> 0x00e5 }
    L_0x00d9:
        return;
    L_0x00da:
        r0 = "isyear";
        r2 = "0";
        r1.put(r0, r2);	 Catch:{ Exception -> 0x00e5 }
        goto L_0x004e;
    L_0x00e5:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00d9;
        */
    }

    public final void b(String str) {
        try {
            XLUserUtil.getInstance().userSetInfo(new JSONObject(String.format("{\"nickname\":\"%s\"}", new Object[]{str})), com.umeng.a.d, com.umeng.a.d, this.I, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final void a(SexType sexType) {
        String str = com.umeng.a.d;
        switch (AnonymousClass_1.a[sexType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str = "u";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                str = "m";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                str = "f";
                break;
        }
        try {
            XLUserUtil.getInstance().userSetInfo(new JSONObject(String.format("{\"sex\":\"%s\"}", new Object[]{str})), com.umeng.a.d, com.umeng.a.d, this.I, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final void a(Bitmap bitmap, String str) {
        if (bitmap != null) {
            XLUserUtil.getInstance().userSetAvatar(bitmap, this.I, str);
        }
    }

    public final String o() {
        String str = this.i;
        return (str == null || str.equals(com.umeng.a.d)) ? String.valueOf(this.j) : str;
    }

    public final void s() {
        if (XLUserUtil.getInstance().userIsOnline()) {
            this.q = this.h;
            this.r = n();
            this.s = this.e;
            this.t = this.g;
            z();
            return;
        }
        a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, false);
    }

    public final void v() {
        XLUserUtil.getInstance().userGetHighSpeedCapacity(this.I, null);
    }

    static /* synthetic */ void a(LoginHelper loginHelper, int i) {
        new StringBuilder("LoginHelper onUserLogout() errorCode=").append(i).append(" errorDesc=").append(XLErrorCode.getErrorDesc(i));
        r();
        loginHelper.e(i);
        if (i == 0) {
            StatReporter.reportUserLogout("active_exit", 0);
        } else {
            StatReporter.reportUserLogout("passive_exit", i);
        }
        if (i == 4) {
            Context applicationContext = BrothersApplication.a().getApplicationContext();
            boolean a = com.xunlei.downloadprovider.a.a.a(applicationContext, CameraActivity.class);
            boolean a2 = com.xunlei.downloadprovider.a.a.a(applicationContext, VodPlayerActivity.class);
            if (BrothersApplication.a().g() && !a && !a2) {
                Activity activity = BrothersApplication.a().n;
                if ((activity instanceof LaunchActivity) || (activity instanceof GuideActivity) || (activity instanceof LoadingActivity) || (activity instanceof VodPlayerActivity)) {
                    loginHelper.C = true;
                } else {
                    loginHelper.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                }
            }
        } else if (i == 5 || i == 1) {
            loginHelper.a(false);
        }
    }

    static /* synthetic */ void a(LoginHelper loginHelper, byte[] bArr, long j) {
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        Throwable th;
        String a = a(j);
        if (a != null && bArr != null && bArr.length > 0 && j == loginHelper.j) {
            int i = 0;
            File file = new File(a);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                i = 0;
                try {
                    bufferedOutputStream.write(bArr, 0, bArr.length);
                    bufferedOutputStream.flush();
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                    }
                    if (file.exists() && file.isFile()) {
                        loginHelper.l = a;
                        loginHelper.c(a);
                        if (loginHelper.ac == null) {
                            loginHelper.ac = new Handler(Looper.getMainLooper());
                        }
                        loginHelper.ac.post(new e(loginHelper));
                    }
                } catch (Exception e2) {
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (!file.exists() && file.isFile()) {
                        loginHelper.l = a;
                        loginHelper.c(a);
                        if (loginHelper.ac == null) {
                            loginHelper.ac = new Handler(Looper.getMainLooper());
                        }
                        loginHelper.ac.post(new e(loginHelper));
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    bufferedOutputStream2 = bufferedOutputStream;
                    th = th3;
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (file.exists() && file.isFile()) {
                        loginHelper.l = a;
                        loginHelper.c(a);
                        if (loginHelper.ac == null) {
                            loginHelper.ac = new Handler(Looper.getMainLooper());
                        }
                        loginHelper.ac.post(new e(loginHelper));
                    }
                    throw th;
                }
            } catch (Exception e3) {
                bufferedOutputStream = i;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                if (!file.exists()) {
                }
            } catch (Throwable th4) {
                th = th4;
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException e5) {
                    }
                }
                loginHelper.l = a;
                loginHelper.c(a);
                if (loginHelper.ac == null) {
                    loginHelper.ac = new Handler(Looper.getMainLooper());
                }
                loginHelper.ac.post(new e(loginHelper));
                throw th;
            }
        }
    }

    static /* synthetic */ void a(LoginHelper loginHelper, int i, long j, long j2) {
        new StringBuilder("onQueryHighSpeedChannelFluxCallBack() status=").append(i).append(", capacity=").append(j).append(", remain=").append(j2);
        if (i == 2 || i == 0) {
            loginHelper.x = j;
            loginHelper.y = j2;
        }
        if (loginHelper.w != null) {
            for (int i2 = 0; i2 < loginHelper.w.size(); i2++) {
                ((o) loginHelper.w.get(i2)).a(i, j, j2);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.xunlei.downloadprovider.member.login.LoginHelper r24, int r25, com.xunlei.common.member.XLUserInfo r26, java.lang.Object r27) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.member.login.LoginHelper.a(com.xunlei.downloadprovider.member.login.LoginHelper, int, com.xunlei.common.member.XLUserInfo, java.lang.Object):void");
        /*
        r4 = 0;
        d = r4;
        if (r25 != 0) goto L_0x0038;
    L_0x0005:
        r4 = 1;
    L_0x0006:
        r0 = r24;
        r0.W = r4;
        if (r26 != 0) goto L_0x003a;
    L_0x000c:
        r0 = r24;
        r4 = r0.Y;
        if (r4 == 0) goto L_0x036c;
    L_0x0012:
        r4 = 0;
        r5 = r4;
    L_0x0014:
        r0 = r24;
        r4 = r0.Y;
        r4 = r4.size();
        if (r5 >= r4) goto L_0x036c;
    L_0x001e:
        r0 = r24;
        r4 = r0.Y;
        r4 = r4.get(r5);
        r4 = (com.xunlei.downloadprovider.member.login.LoginHelper.d) r4;
        r6 = 0;
        r0 = r24;
        r7 = r0.o;
        r0 = r25;
        r1 = r27;
        r4.a(r6, r0, r7, r1);
        r4 = r5 + 1;
        r5 = r4;
        goto L_0x0014;
    L_0x0038:
        r4 = 0;
        goto L_0x0006;
    L_0x003a:
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.vip_level;
        r0 = r26;
        r18 = r0.getIntValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.PayId;
        r0 = r26;
        r6 = r0.getIntValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.VasType;
        r0 = r26;
        r7 = r0.getIntValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.VasId;
        r0 = r26;
        r0.getIntValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.UserID;
        r0 = r26;
        r8 = r0.getStringValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.SessionID;
        r0 = r26;
        r9 = r0.getStringValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.ImgURL;
        r0 = r26;
        r10 = r0.getStringValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.NickName;
        r0 = r26;
        r11 = r0.getStringValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.ExpireDate;
        r0 = r26;
        r12 = r0.getStringValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.Account;
        r0 = r26;
        r13 = r0.getIntValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.JumpKey;
        r0 = r26;
        r14 = r0.getStringValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.PasswordCheckNum;
        r0 = r26;
        r5 = r0.getStringValue(r4);
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.IsVip;
        r0 = r26;
        r15 = r0.getIntValue(r4);
        if (r25 != 0) goto L_0x01a5;
    L_0x00a3:
        r0 = r24;
        r4 = r0.A;
        if (r4 == 0) goto L_0x00d4;
    L_0x00a9:
        r0 = r24;
        r4 = r0.A;
        r16 = "";
        r0 = r16;
        r4 = r4.equals(r0);
        if (r4 != 0) goto L_0x00d4;
    L_0x00b8:
        r0 = r24;
        r4 = r0.A;
        r0 = r24;
        r0 = r0.U;
        r16 = r0;
        r16 = r16.edit();
        r17 = "userName";
        r0 = r16;
        r1 = r17;
        r4 = r0.putString(r1, r4);
        r4.apply();
    L_0x00d4:
        r16 = java.lang.Long.parseLong(r8);
        com.xunlei.downloadprovider.model.protocol.report.b.a(r16);
        r4 = Q;
        r0 = r27;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0109;
    L_0x00e5:
        r4 = new com.xunlei.downloadprovider.homepage.interest.a.j;
        r4.<init>();
        r20 = java.lang.System.currentTimeMillis();
        r4 = r4.a;
        r19 = "key_last_sync_time";
        r22 = 0;
        r0 = r19;
        r1 = r22;
        r22 = r4.getLong(r0, r1);
        r20 = r20 - r22;
        r22 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r4 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1));
        if (r4 <= 0) goto L_0x036d;
    L_0x0106:
        r4 = 1;
    L_0x0107:
        if (r4 == 0) goto L_0x018a;
    L_0x0109:
        r4 = new com.xunlei.downloadprovider.homepage.interest.a.a;
        r4.<init>();
        r20 = 0;
        r19 = (r16 > r20 ? 1 : (r16 == r20 ? 0 : -1));
        if (r19 == 0) goto L_0x018a;
    L_0x0114:
        r19 = new com.xunlei.downloadprovider.homepage.interest.a.j;
        r19.<init>();
        r0 = r19;
        r0 = r0.a;
        r20 = r0;
        r21 = "key_user_id";
        r22 = 0;
        r20 = r20.getLong(r21, r22);
        r20 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1));
        if (r20 != 0) goto L_0x013d;
    L_0x012c:
        r0 = r19;
        r0 = r0.a;
        r20 = r0;
        r21 = "key_is_sync";
        r22 = 0;
        r20 = r20.getBoolean(r21, r22);
        if (r20 != 0) goto L_0x018a;
    L_0x013d:
        r0 = r19;
        r0 = r0.a;
        r20 = r0;
        r20 = r20.edit();
        r21 = "key_user_id";
        r0 = r20;
        r1 = r21;
        r2 = r16;
        r20 = r0.putLong(r1, r2);
        r20.apply();
        r20 = 0;
        r19.a(r20);
        r20 = 0;
        r22 = new com.xunlei.downloadprovider.homepage.interest.a.f;
        r0 = r22;
        r1 = r16;
        r3 = r19;
        r0.<init>(r4, r1, r3);
        r0 = r20;
        r2 = r22;
        r4.a(r0, r2);
        r0 = r19;
        r4 = r0.a;
        r4 = r4.edit();
        r16 = "key_last_sync_time";
        r20 = java.lang.System.currentTimeMillis();
        r0 = r16;
        r1 = r20;
        r4 = r4.putLong(r0, r1);
        r4.apply();
    L_0x018a:
        r4 = new com.xunlei.downloadprovider.homepage.n;
        r4.<init>();
        r4 = r4.a;
        r4 = r4.edit();
        r16 = "is_logged";
        r17 = 1;
        r0 = r16;
        r1 = r17;
        r4 = r4.putBoolean(r0, r1);
        r4.apply();
    L_0x01a5:
        if (r25 != 0) goto L_0x01c1;
    L_0x01a7:
        r4 = android.text.TextUtils.isEmpty(r5);
        if (r4 != 0) goto L_0x01c1;
    L_0x01ad:
        r0 = r24;
        r4 = r0.U;
        r4 = r4.edit();
        r16 = "passwordCheckNum";
        r0 = r16;
        r4 = r4.putString(r0, r5);
        r4.apply();
    L_0x01c1:
        r4 = Q;
        r0 = r27;
        if (r0 != r4) goto L_0x022b;
    L_0x01c7:
        if (r25 == 0) goto L_0x022b;
    L_0x01c9:
        r4 = new java.lang.StringBuilder;
        r5 = "handleLoginCallbackEvent(). Auto login fail. errorCode=";
        r4.<init>(r5);
        r0 = r25;
        r4 = r4.append(r0);
        r5 = " errorDesc=";
        r4 = r4.append(r5);
        r5 = com.xunlei.common.member.XLErrorCode.getErrorDesc(r25);
        r4.append(r5);
        r4 = com.xunlei.downloadprovider.app.BrothersApplication.a();
        r4 = com.xunlei.xllib.a.b.a(r4);
        if (r4 == 0) goto L_0x022b;
    L_0x01ef:
        r4 = 7;
        r0 = r25;
        if (r0 == r4) goto L_0x0210;
    L_0x01f4:
        r4 = 2;
        r0 = r25;
        if (r0 == r4) goto L_0x0210;
    L_0x01f9:
        r4 = 15;
        r0 = r25;
        if (r0 == r4) goto L_0x0210;
    L_0x01ff:
        r4 = 14;
        r0 = r25;
        if (r0 == r4) goto L_0x0210;
    L_0x0205:
        r4 = 12;
        r0 = r25;
        if (r0 == r4) goto L_0x0210;
    L_0x020b:
        r4 = 3;
        r0 = r25;
        if (r0 != r4) goto L_0x022b;
    L_0x0210:
        r4 = com.xunlei.downloadprovider.app.BrothersApplication.a();
        r4 = r4.n;
        r5 = r4 instanceof com.xunlei.downloadprovider.launch.LaunchActivity;
        if (r5 != 0) goto L_0x0226;
    L_0x021a:
        r5 = r4 instanceof com.xunlei.downloadprovider.app.GuideActivity;
        if (r5 != 0) goto L_0x0226;
    L_0x021e:
        r5 = r4 instanceof com.xunlei.downloadprovider.loading.LoadingActivity;
        if (r5 != 0) goto L_0x0226;
    L_0x0222:
        r4 = r4 instanceof com.xunlei.downloadprovider.vod.VodPlayerActivity;
        if (r4 == 0) goto L_0x0370;
    L_0x0226:
        r4 = 1;
        r0 = r24;
        r0.C = r4;
    L_0x022b:
        r4 = com.xunlei.common.member.XLUserInfo.USERINFOKEY.UserName;
        r0 = r26;
        r16 = r0.getStringValue(r4);
        r4 = r24;
        r5 = r25;
        r17 = r27;
        r4.a(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
        if (r27 == 0) goto L_0x0282;
    L_0x023e:
        r4 = b;
        r0 = r27;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x0378;
    L_0x0248:
        r4 = 1;
        a = r4;
    L_0x024b:
        r4 = S;
        r0 = r27;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x026a;
    L_0x0255:
        r0 = r24;
        r4 = r0.U;
        r4 = r4.edit();
        r5 = "userName";
        r6 = "";
        r4 = r4.putString(r5, r6);
        r4.apply();
    L_0x026a:
        r4 = Q;
        r0 = r27;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x038b;
    L_0x0274:
        if (r25 != 0) goto L_0x037d;
    L_0x0276:
        r4 = "success";
        r5 = r24.f();
        r0 = r25;
        com.xunlei.downloadprovider.model.protocol.report.StatReporter.reportNewAutoLogin(r4, r0, r5, r7);
    L_0x0282:
        r24.x();
        r4 = com.xunlei.downloadprovider.download.util.g.a();
        a();
        r5 = com.xunlei.common.member.XLUserUtil.getInstance();
        r5 = r5.userIsOnline();
        if (r5 == 0) goto L_0x030a;
    L_0x0296:
        a();
        r5 = com.xunlei.common.member.XLUserUtil.getInstance();
        r5 = r5.userIsOnline();
        if (r5 == 0) goto L_0x02d6;
    L_0x02a3:
        r5 = r4.g;
        if (r5 == 0) goto L_0x02d6;
    L_0x02a7:
        r5 = com.xunlei.downloadprovider.download.tasklist.a.h.a();
        r12 = r4.d;
        r5 = r5.a(r12);
        if (r5 == 0) goto L_0x02d6;
    L_0x02b3:
        r6 = r5.mTaskStatus;
        r9 = 4;
        if (r6 != r9) goto L_0x02d6;
    L_0x02b8:
        r6 = com.xunlei.downloadprovider.app.BrothersApplication.a();
        r6 = com.xunlei.xllib.a.b.a(r6);
        if (r6 != 0) goto L_0x03d2;
    L_0x02c2:
        r5 = com.xunlei.downloadprovider.app.BrothersApplication.a();
        r6 = com.xunlei.downloadprovider.commonview.XLToast.XLToastType.XLTOAST_TYPE_ALARM;
        r9 = com.xunlei.downloadprovider.app.BrothersApplication.a();
        r12 = 2131231758; // 0x7f08040e float:1.8079606E38 double:1.052968395E-314;
        r9 = r9.getString(r12);
        com.xunlei.downloadprovider.commonview.XLToast.a(r5, r6, r9);
    L_0x02d6:
        a();
        r5 = com.xunlei.common.member.XLUserUtil.getInstance();
        r5 = r5.userIsOnline();
        if (r5 == 0) goto L_0x042b;
    L_0x02e3:
        r5 = a();
        r5 = r5.f();
        if (r5 == 0) goto L_0x042b;
    L_0x02ed:
        r5 = r4.b();
        if (r5 == 0) goto L_0x02fe;
    L_0x02f3:
        r12 = r4.d;
        r5 = com.xunlei.downloadprovider.download.util.g.c;
        r6 = 0;
        r4.a(r12, r5, r6);
        r4.f();
    L_0x02fe:
        r12 = -1;
        r4.c(r12);
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        r4 = 0;
        com.xunlei.downloadprovider.service.downloads.task.d.b(r4);
    L_0x030a:
        r0 = r24;
        r4 = r0.W;
        if (r4 == 0) goto L_0x0317;
    L_0x0310:
        r4 = com.xunlei.downloadprovider.download.util.g.a();
        r5 = 0;
        r4.g = r5;
    L_0x0317:
        r4 = new java.lang.StringBuilder;
        r5 = "userId=";
        r4.<init>(r5);
        r4 = r4.append(r8);
        r5 = "\nusername=";
        r4 = r4.append(r5);
        r0 = r16;
        r4 = r4.append(r0);
        r5 = "\nnickName=";
        r4 = r4.append(r5);
        r4 = r4.append(r11);
        r5 = "\nisVip=";
        r4 = r4.append(r5);
        r4 = r4.append(r15);
        r5 = "\nlevel=";
        r4 = r4.append(r5);
        r0 = r18;
        r4 = r4.append(r0);
        r5 = "\nmemberType=";
        r4 = r4.append(r5);
        r4 = r4.append(r7);
        r5 = "\nportraitPath=";
        r4 = r4.append(r5);
        r4.append(r10);
        r4 = 1;
        com.xunlei.downloadprovider.pushmessage.a.a(r4);
    L_0x036c:
        return;
    L_0x036d:
        r4 = 0;
        goto L_0x0107;
    L_0x0370:
        r4 = 1;
        r0 = r24;
        r0.a(r4);
        goto L_0x022b;
    L_0x0378:
        r4 = 2;
        a = r4;
        goto L_0x024b;
    L_0x037d:
        r4 = "fail";
        r5 = r24.f();
        r0 = r25;
        com.xunlei.downloadprovider.model.protocol.report.StatReporter.reportNewAutoLogin(r4, r0, r5, r7);
        goto L_0x0282;
    L_0x038b:
        r4 = R;
        r0 = r27;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0282;
    L_0x0395:
        if (r25 != 0) goto L_0x03bb;
    L_0x0397:
        r4 = r24.f();
        r5 = "android_auto_login_success";
        r6 = "manual_auto_login_success";
        r5 = com.xunlei.downloadprovidercommon.a.a.a(r5, r6);
        r6 = "is_vip";
        if (r4 == 0) goto L_0x03b9;
    L_0x03aa:
        r4 = 1;
    L_0x03ab:
        r5.a(r6, r4);
        r4 = "vip_type";
        r5.a(r4, r7);
        com.xunlei.downloadprovider.member.login.a.a(r5);
        goto L_0x0282;
    L_0x03b9:
        r4 = 0;
        goto L_0x03ab;
    L_0x03bb:
        r4 = "android_auto_login_fail";
        r5 = "manual_auto_login_fail";
        r4 = com.xunlei.downloadprovidercommon.a.a.a(r4, r5);
        r5 = "failtype";
        r0 = r25;
        r4.a(r5, r0);
        com.xunlei.downloadprovider.member.login.a.a(r4);
        goto L_0x0282;
    L_0x03d2:
        r6 = com.xunlei.downloadprovider.app.BrothersApplication.a();
        r6 = com.xunlei.xllib.a.b.h(r6);
        if (r6 != 0) goto L_0x0421;
    L_0x03dc:
        r6 = new com.xunlei.downloadprovider.download.util.h;
        r6.<init>(r4, r5);
        r5 = com.xunlei.downloadprovider.app.BrothersApplication.a();
        r9 = new com.xunlei.downloadprovider.commonview.dialog.d;
        r9.<init>(r5);
        r12 = 2131231750; // 0x7f080406 float:1.807959E38 double:1.052968391E-314;
        r12 = r5.getString(r12);
        r9.a(r12);
        r12 = 2131231753; // 0x7f080409 float:1.8079596E38 double:1.0529683925E-314;
        r12 = r5.getString(r12);
        r9.c(r12);
        r12 = 2131231745; // 0x7f080401 float:1.807958E38 double:1.0529683885E-314;
        r5 = r5.getString(r12);
        r9.d(r5);
        r5 = 1;
        r9.setCanceledOnTouchOutside(r5);
        r5 = new com.xunlei.downloadprovider.download.util.i;
        r5.<init>(r4, r9, r6);
        r9.a(r5);
        r5 = new com.xunlei.downloadprovider.download.util.j;
        r5.<init>(r4, r9);
        r9.b(r5);
        r9.show();
        goto L_0x02d6;
    L_0x0421:
        r6 = com.xunlei.downloadprovider.download.a.n.a();
        r9 = 0;
        r6.a(r5, r9);
        goto L_0x02d6;
    L_0x042b:
        r5 = r4.g;
        if (r5 == 0) goto L_0x0436;
    L_0x042f:
        r5 = com.xunlei.downloadprovider.download.util.g.a();
        r5.c();
    L_0x0436:
        com.xunlei.downloadprovider.download.util.g.g();
        a();
        r5 = com.xunlei.common.member.XLUserUtil.getInstance();
        r5 = r5.userIsOnline();
        if (r5 == 0) goto L_0x030a;
    L_0x0446:
        r5 = r4.g;
        if (r5 == 0) goto L_0x030a;
    L_0x044a:
        r5 = 0;
        r4.g = r5;
        r5 = com.xunlei.downloadprovider.download.util.g.a();
        r12 = r4.d;
        r5.c(r12);
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        r12 = r4.d;
        r5 = com.xunlei.downloadprovider.service.downloads.task.d.d(r12);
        r12 = r5.mDownloadedSize;
        r6 = (float) r12;
        r12 = r5.mFileSize;
        r9 = (float) r12;
        r6 = r6 / r9;
        r12 = (double) r6;
        r20 = 4604480259023595110; // 0x3fe6666666666666 float:2.720083E23 double:0.7;
        r6 = (r12 > r20 ? 1 : (r12 == r20 ? 0 : -1));
        if (r6 >= 0) goto L_0x030a;
    L_0x0470:
        r6 = com.xunlei.downloadprovider.download.util.g.a();
        r12 = r5.mDownloadedSize;
        r6.b(r12);
        r5 = com.xunlei.downloadprovider.download.tasklist.a.h.a();
        r12 = r4.d;
        r5 = r5.a(r12);
        r6 = 1;
        r5.mIsEnteredHighSpeedTrial = r6;
        r12 = r4.d;
        r4.f(r12);
        r4 = com.xunlei.downloadprovider.download.util.g.a();
        r4.c();
        goto L_0x030a;
        */
    }
}
