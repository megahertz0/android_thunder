package com.xunlei.downloadprovider.frame.user;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.f;
import com.nostra13.universalimageloader.core.c;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.taobao.accs.common.Constants;
import com.tencent.open.utils.SystemUtils;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.commonview.AnimationDot;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.frame.user.a.b.a;
import com.xunlei.downloadprovider.frame.user.account.ui.UserAccountInfoActivityNew;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.member.login.LoginHelper.o;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.member.login.ui.ad;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.j;
import com.xunlei.downloadprovider.model.k;
import com.xunlei.downloadprovider.model.l;
import com.xunlei.downloadprovider.model.m;
import com.xunlei.downloadprovider.model.n;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceActivity;
import com.xunlei.downloadprovider.personal.playrecord.PlayRecordActivity;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.ui.RedEnvelopesActivity;
import com.xunlei.downloadprovider.personal.settings.SettingsIndexActivity;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.downloadprovider.util.v;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovider.web.record.FavorAndHistroyActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.util.Calendar;
import java.util.regex.Pattern;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public class UserCenterFragment extends BaseFragment implements OnClickListener {
    public static boolean a;
    private static int e;
    private static int f;
    private static int g;
    private static bx j;
    private View A;
    private View B;
    private View C;
    private View D;
    private View E;
    private View F;
    private View G;
    private View H;
    private View I;
    private TextView J;
    private TextView K;
    private ImageView L;
    private View M;
    private AnimationDot N;
    private TextView O;
    private TextView P;
    private View Q;
    private ImageView R;
    private ImageView S;
    private TextView T;
    private ImageView U;
    private int V;
    private boolean W;
    private boolean X;
    private a Y;
    private boolean Z;
    private boolean aa;
    private boolean ab;
    private final int ac;
    private boolean ad;
    private boolean ae;
    private h.a af;
    private b ag;
    private d ah;
    private final g ai;
    private final p aj;
    private o ak;
    LoginHelper.b b;
    private final int c;
    private final int d;
    private final int h;
    private final int i;
    private LoginHelper k;
    private View l;
    private View m;
    private ImageView n;
    private ImageView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private ImageView s;
    private TextView t;
    private ImageView u;
    private TextView v;
    private TextView w;
    private ImageView x;
    private ImageView y;
    private ImageView z;

    static /* synthetic */ void a(UserCenterFragment userCenterFragment, int i, int i2) {
        if (userCenterFragment.ab) {
            userCenterFragment.g();
            if (i2 == 0 && i == 0) {
                com.xunlei.downloadprovider.homepage.a.a.d.b.clear();
                j = null;
                String valueOf = String.valueOf(userCenterFragment.k.j);
                userCenterFragment.Y = com.xunlei.downloadprovider.frame.user.a.b.a(userCenterFragment.mActivity).f(valueOf);
                userCenterFragment.Y.a = valueOf;
                userCenterFragment.Z = true;
                if (TextUtils.isEmpty(userCenterFragment.k.l)) {
                    userCenterFragment.ag.sendEmptyMessageDelayed(R.styleable.AppCompatTheme_checkedTextViewStyle, TabsImpl.SYNC_TIME_OUT);
                }
                userCenterFragment.k.s();
                if (userCenterFragment.isAdded()) {
                    userCenterFragment.f();
                    userCenterFragment.Y = new a();
                    userCenterFragment.W = false;
                    userCenterFragment.X = false;
                    userCenterFragment.r.setVisibility(XZBDevice.Wait);
                    userCenterFragment.v.setVisibility(XZBDevice.Wait);
                    userCenterFragment.a(true, userCenterFragment.k.f(), userCenterFragment.k.l, userCenterFragment.k.e);
                    userCenterFragment.a(true, userCenterFragment.k.f());
                    userCenterFragment.d();
                }
                a = true;
            }
        }
    }

    static {
        e = 0;
        f = 1;
        g = 2;
    }

    public UserCenterFragment() {
        this.c = 100;
        this.d = 103;
        this.h = 3;
        this.i = 4;
        this.ac = 101;
        this.af = new az(this);
        this.ag = new b(this.af);
        this.ah = new bj(this);
        this.ai = new bl(this);
        this.aj = new bm(this);
        this.ak = new bc(this);
        this.b = new bd(this);
        this.k = LoginHelper.a();
        this.k.a(this.ah);
        this.k.a(this.ai);
        this.k.a(this.aj);
        this.k.a(this.ak);
        this.k.a(this.b);
        if (LoginHelper.c()) {
            this.Z = true;
        }
    }

    private void a(int i) {
        this.r.setText(i + "\u79ef\u5206");
        this.r.setVisibility(0);
        this.C.setVisibility(0);
        a(this.v, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
    }

    private void a(boolean z) {
        if (z) {
            this.q.setText(this.mActivity.getResources().getString(2131231708));
            this.D.setVisibility(0);
            this.E.setVisibility(XZBDevice.Wait);
            this.F.setVisibility(XZBDevice.Wait);
        } else {
            this.D.setVisibility(XZBDevice.Wait);
            this.E.setVisibility(0);
            this.F.setVisibility(0);
        }
        d();
    }

    private void d() {
        if (this.r.getVisibility() == 8 && this.v.getVisibility() == 8) {
            this.C.setVisibility(XZBDevice.Wait);
        } else {
            this.C.setVisibility(0);
        }
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
            String replace = str.replace("/50x50", "/300x300");
            c.a aVar = new c.a();
            aVar.a = 2130839592;
            aVar.h = true;
            com.nostra13.universalimageloader.core.d.a().a(replace, this.o, aVar.a().b(), new be(this));
        }
    }

    private static long c(String str) {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        if (TextUtils.isEmpty(str) || !Pattern.compile("[0-9]*").matcher(str).matches()) {
            return -1;
        }
        instance.set(Integer.parseInt(str.substring(0, XZBDevice.DOWNLOAD_LIST_ALL)), Integer.parseInt(str.substring(XZBDevice.DOWNLOAD_LIST_ALL, R.styleable.Toolbar_contentInsetEnd)) - 1, Integer.parseInt(str.substring(R.styleable.Toolbar_contentInsetEnd, XZBDevice.Wait)));
        long timeInMillis = instance.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        long j = timeInMillis % 86400000;
        timeInMillis /= 86400000;
        return j > 0 ? timeInMillis + 1 : timeInMillis;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mPageRoot == null) {
            this.mPageRoot = (ViewGroup) layoutInflater.inflate(2130968881, viewGroup, false);
        }
        ViewParent parent = this.mPageRoot.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.mPageRoot);
        }
        this.J = (TextView) findViewById(2131756667);
        this.o = (ImageView) findViewById(2131756615);
        this.o.setOnClickListener(this);
        this.p = (TextView) findViewById(2131756616);
        this.p.setOnClickListener(this);
        this.K = (TextView) findViewById(2131756634);
        this.L = (ImageView) findViewById(2131756635);
        this.r = (TextView) findViewById(2131756164);
        this.s = (ImageView) findViewById(2131756619);
        this.t = (TextView) findViewById(2131756620);
        this.u = (ImageView) findViewById(2131756621);
        this.v = (TextView) findViewById(2131756622);
        this.w = (TextView) findViewById(2131755958);
        this.w.setOnClickListener(this);
        this.x = (ImageView) findViewById(2131756623);
        this.y = (ImageView) findViewById(2131756624);
        this.z = (ImageView) findViewById(2131756625);
        this.C = findViewById(2131756617);
        this.R = (ImageView) findViewById(2131756636);
        this.Q = findViewById(2131756632);
        this.Q.setOnClickListener(this);
        findViewById(2131756637).setOnClickListener(this);
        findViewById(2131756639).setOnClickListener(this);
        findViewById(2131756638).setOnClickListener(this);
        this.D = findViewById(2131756491);
        this.D.setOnClickListener(this);
        this.E = findViewById(2131756486);
        ((TextView) findViewById(2131756487)).setOnClickListener(this);
        this.F = findViewById(2131756488);
        this.F.setOnClickListener(this);
        this.q = (TextView) findViewById(2131756494);
        if (com.xunlei.downloadprovider.a.b.t() <= 480) {
            this.p.setMaxWidth(com.xunlei.downloadprovider.a.g.a(this.mActivity, 100.0f));
        }
        this.A = findViewById(2131756641);
        this.A.setOnClickListener(this);
        this.B = findViewById(2131756646);
        this.B.setOnClickListener(this);
        this.m = findViewById(2131756614);
        this.l = findViewById(2131756626);
        this.l.setOnClickListener(this);
        this.n = (ImageView) findViewById(2131756628);
        this.I = findViewById(2131756650);
        this.I.setOnClickListener(this);
        ((RelativeLayout) findViewById(2131756654)).setOnClickListener(this);
        this.H = findViewById(2131756657);
        this.H.setOnClickListener(this);
        this.G = findViewById(2131756661);
        this.G.setOnClickListener(this);
        this.M = findViewById(2131756629);
        this.N = (AnimationDot) findViewById(2131756461);
        this.O = (TextView) findViewById(2131756630);
        this.P = (TextView) findViewById(2131756631);
        this.S = (ImageView) findViewById(2131756633);
        this.T = (TextView) findViewById(2131756664);
        this.U = (ImageView) findViewById(2131756665);
        this.ab = true;
        this.k.s();
        LoginHelper.a().a(new bb(this));
        return this.mPageRoot;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    final void a() {
        Handler handler = this.ag;
        long j = LoginHelper.a().j;
        Request cVar = new com.xunlei.downloadprovider.model.c(new StringBuilder("http://jifenshangcheng.m.xunlei.com/cgi-bin/integra_info?userId=").append(j).append("&pm=android&time=").append(System.currentTimeMillis()).toString(), new k(handler), new l(handler));
        com.android.volley.p d = com.xunlei.downloadprovider.j.a.a().d();
        cVar.setRetryPolicy(new f(5000, 1, 1.0f));
        d.a(cVar);
    }

    final void b() {
        Handler handler = this.ag;
        long j = LoginHelper.a().j;
        Request cVar = new com.xunlei.downloadprovider.model.c(new StringBuilder("http://scoremall.niu.xunlei.com:17016/ScoreMall/call?a=getScore&uid=").append(j).append("&t=").append(System.currentTimeMillis()).toString(), new m(handler), new n(handler));
        com.android.volley.p d = com.xunlei.downloadprovider.j.a.a().d();
        cVar.setRetryPolicy(new f(5000, 1, 1.0f));
        d.a(cVar);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onResume() {
        String valueOf;
        super.onResume();
        this.T.setVisibility(XZBDevice.Wait);
        this.U.setVisibility(XZBDevice.Wait);
        if (XZBShouleiUtil.getInstance().getDefaultDevice() != null) {
            e.a();
            if (e.f()) {
                this.T.setVisibility(0);
                this.U.setVisibility(0);
            }
        }
        this.ag.post(new bf(this));
        n();
        boolean c = LoginHelper.c();
        boolean f = this.k.f();
        if (NetworkHelper.isNetworkAvailable(getApplicationContext())) {
            aa.a(getApplicationContext(), "isvip", f);
        }
        a(c, f, this.k.l, this.k.e);
        a(c, f);
        e();
        if ((j != null && j.a) || LoginHelper.c()) {
            valueOf = String.valueOf(j.d);
            if (LoginHelper.c()) {
                valueOf = String.valueOf(this.k.j);
            }
            this.Y = com.xunlei.downloadprovider.frame.user.a.b.a(this.mActivity).f(valueOf);
        }
        if (this.Y != null) {
            f();
        }
        this.k.s();
        this.k.v();
        com.xunlei.downloadprovider.member.login.b bVar = LoginHelper.a().D;
        if (bVar == null || bVar.a != 0) {
            this.k.w();
        } else {
            a(0, bVar);
        }
        if (NetworkHelper.isNetworkAvailable(getApplicationContext())) {
            valueOf = com.umeng.a.d;
            f = this.k.f();
            int i = this.k.h;
            if (f || aa.c(getApplicationContext(), "isvip")) {
                switch (i) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        valueOf = "\u8ff7\u4f60\u4f1a\u5458";
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        valueOf = "\u666e\u901a\u4f1a\u5458";
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        valueOf = "\u767d\u91d1\u4f1a\u5458";
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        valueOf = "\u7816\u77f3\u4f1a\u5458";
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        valueOf = "\u8d85\u7ea7\u4f1a\u5458";
                        break;
                }
            }
            aa.a(getApplicationContext(), "usermembertype", valueOf);
        }
        if (NetworkHelper.isNetworkAvailable(getApplicationContext())) {
            aa.a(getApplicationContext(), "usermemberdate", PayUtil.a(new bx().f()));
        }
        if (NetworkHelper.isNetworkAvailable(getApplicationContext())) {
            int i2 = this.k.f;
            int a = ad.a(i2);
            aa.a(getApplicationContext(), "usermemberscore", i2 + "/" + ad.b(a));
            aa.a(getApplicationContext(), "usermemberlevel", new StringBuilder("LV").append(a).toString());
        }
        if (LoginHelper.c() && NetworkHelper.isNetworkAvailable(this.mActivity)) {
            b();
            a();
        }
        if (j == null || !j.a) {
            g();
        }
        m();
        DownloadService a2 = DownloadService.a();
        if (a2 != null) {
            h();
            a2.b(this.ag);
        }
        if (this.aa && LoginHelper.c()) {
            a(false, true, null);
        }
        this.aa = false;
        this.ad = true;
        if (this.ae) {
            b(true);
        }
    }

    private void e() {
        bx bxVar = new bx();
        j = bxVar;
        if (bxVar.a) {
            this.m.setVisibility(0);
            this.l.setVisibility(XZBDevice.Wait);
            TextView textView = this.p;
            bx bxVar2 = j;
            CharSequence charSequence = bxVar2.f;
            if (charSequence == null || charSequence.equals(com.umeng.a.d)) {
                charSequence = String.valueOf(bxVar2.d);
            }
            textView.setText(charSequence);
            a(j.e(), j.f(), j.g());
            b(j.e);
            a(j.e(), j.b, j.c, j.g());
            a(j.e(), j.c, j.g());
            a(true, j.e());
        }
    }

    private void f() {
        a(this.Y.c);
        a(this.Y.b);
    }

    public void onCreateTask(boolean z, int i) {
        super.onCreateTask(z, i);
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void g() {
        if (LoginHelper.p()) {
            this.N.a();
            this.l.setClickable(false);
            this.M.setVisibility(0);
            this.n.setVisibility(XZBDevice.Wait);
            this.P.setVisibility(XZBDevice.Wait);
            this.O.setVisibility(XZBDevice.Wait);
            this.q.setVisibility(XZBDevice.Wait);
            this.D.setVisibility(0);
            this.E.setVisibility(XZBDevice.Wait);
            this.F.setVisibility(XZBDevice.Wait);
            return;
        }
        if (j == null || !j.a) {
            this.N.b();
            this.l.setClickable(true);
            this.M.setVisibility(XZBDevice.Wait);
            this.P.setVisibility(XZBDevice.Wait);
            this.O.setVisibility(0);
        }
        this.q.setVisibility(0);
        this.D.setVisibility(XZBDevice.Wait);
        this.E.setVisibility(0);
        this.F.setVisibility(0);
    }

    private void h() {
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        int k = com.xunlei.downloadprovider.service.downloads.task.d.k();
        if (k == 0) {
            this.K.setVisibility(XZBDevice.Wait);
            this.L.setVisibility(XZBDevice.Wait);
            return;
        }
        int a;
        this.K.setVisibility(0);
        CharSequence valueOf = String.valueOf(k);
        int length = valueOf.length();
        LayoutParams layoutParams = (LayoutParams) this.K.getLayoutParams();
        if (length == 1) {
            a = com.xunlei.downloadprovider.a.g.a(this.mActivity, 15.0f);
        } else if (length == 2) {
            a = com.xunlei.downloadprovider.a.g.a(this.mActivity, 22.0f);
        } else if (length >= 3) {
            a = com.xunlei.downloadprovider.a.g.a(this.mActivity, 22.0f);
        } else {
            a = 0;
        }
        int i = length == 1 ? R.drawable.download_entrance_num_bkg_white_in_blue : length == 2 ? R.drawable.download_entrance_num_2digits_bkg_white_in_blue : R.drawable.download_entrance_num_3digits_bkg_white_in_blue;
        layoutParams.width = a;
        this.K.setLayoutParams(layoutParams);
        this.K.setText(valueOf);
        if (length >= 3) {
            this.K.setVisibility(XZBDevice.Wait);
            this.L.setVisibility(0);
            return;
        }
        this.K.setVisibility(0);
        this.L.setVisibility(XZBDevice.Wait);
        this.K.setBackgroundResource(i);
    }

    private void a(boolean z, boolean z2) {
        if (z && z2) {
            this.A.setVisibility(0);
            this.B.setVisibility(XZBDevice.Wait);
            if (VERSION.SDK_INT >= 23) {
                this.J.setVisibility(0);
                return;
            }
            return;
        }
        this.A.setVisibility(XZBDevice.Wait);
        this.B.setVisibility(0);
        this.J.setVisibility(XZBDevice.Wait);
    }

    private void a(boolean z, boolean z2, String str, int i) {
        if (z) {
            this.m.setVisibility(0);
            this.l.setVisibility(XZBDevice.Wait);
            this.p.setText(this.k.o());
            a(z2, LoginHelper.a().n(), LoginHelper.a().l());
            b(str);
            a(z2, i, this.k.h, this.k.l());
            a(z2, this.k.h, this.k.l());
            return;
        }
        this.m.setVisibility(XZBDevice.Wait);
        this.l.setVisibility(0);
    }

    private void a(boolean z, String str, boolean z2) {
        if (z || z2) {
            this.C.setVisibility(0);
            long c = c(str);
            if (c >= 0) {
                if (c == 0) {
                    this.v.setText("\u4eca\u65e5\u5230\u671f");
                } else {
                    this.v.setText(new StringBuilder("\u5269").append(c).append("\u5929\u5230\u671f").toString());
                }
                this.v.setVisibility(0);
                if (this.r.getVisibility() == 0) {
                    a(this.v, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                    return;
                } else {
                    a(this.v, 0);
                    return;
                }
            }
            this.v.setVisibility(XZBDevice.Wait);
            return;
        }
        this.v.setVisibility(XZBDevice.Wait);
    }

    public void onClick(View view) {
        int i = 1;
        int i2;
        switch (view.getId()) {
            case 2131755958:
                c(false);
            case 2131756487:
                if (LoginHelper.c()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                ThunderReporter.g gVar = new ThunderReporter.g();
                gVar.a = "android_personal_click";
                gVar.b = "per_cl_sign_click";
                gVar.c = "per_cl_sign_click";
                gVar.b(SystemUtils.IS_LOGIN, (long) i2);
                ThunderReporter.a(gVar, true);
                if (LoginHelper.c()) {
                    a(false, true, Constants.KEY_SECURITY_SIGN);
                } else if (j == null || NetworkHelper.isNetworkAvailable(this.mActivity) || !j.a) {
                    this.aa = true;
                    LoginHelper.a().a(getActivity(), null, (int) XZBDevice.Delete, "_user_sign");
                } else {
                    XLToast.d(this.mActivity, XLToastType.XLTOAST_TYPE_NORMAL, this.mActivity.getResources().getString(2131231706));
                }
            case 2131756488:
                a(true, false, "task");
            case 2131756491:
                a(true, false, Constants.KEY_SECURITY_SIGN);
            case 2131756615:
            case 2131756616:
                Intent intent = new Intent();
                intent.setClass(this.mActivity, UserAccountInfoActivityNew.class);
                this.mActivity.startActivity(intent);
                a("per_cl_usericon", "per_cl_usericon", null);
            case 2131756620:
                k();
            case 2131756626:
                LoginHelper.a().a(getActivity(), null, (int) XZBDevice.Delete);
            case 2131756632:
                DownloadCenterActivity.a(getActivity(), DLCenterEntry.personal_center.toString());
                a("per_cl_dlCenter", "per_cl_dlCenter", null);
            case 2131756637:
                this.mActivity.startActivity(new Intent(this.mActivity, PlayRecordActivity.class));
            case 2131756638:
                this.mActivity.startActivity(new Intent(this.mActivity, FavorAndHistroyActivity.class));
                a("per_cl_collect", "per_cl_collect", null);
            case 2131756639:
                l();
            case 2131756641:
                k();
            case 2131756646:
                k();
            case 2131756650:
                l();
            case 2131756654:
                this.mActivity.startActivity(new Intent(this.mActivity, RedEnvelopesActivity.class));
            case 2131756657:
                BrowserUtil.a();
                Context context = this.mActivity;
                Bundle bundle = new Bundle();
                bundle.putString(JsInterface.FROM_KEY, "Usercenterfragment_2_feedback");
                BrowserUtil.a(context, "http://xlzh.xlkf.xunlei.com/?companyID=8950&configID=21&enterurl=m.help.xunlei.com&policyId=14&live800_domain=m.help.xunlei.com&live800_robot_ud_Android=Android", "\u5e2e\u52a9\u53cd\u9988", 2071, bundle);
                a("per_cl_help", "per_cl_help", null);
            case 2131756661:
                getActivity().startActivity(new Intent(getActivity(), SettingsIndexActivity.class));
                if (this.k.f()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (!LoginHelper.c()) {
                    i = 0;
                }
                ak.a(i2, i);
            default:
                break;
        }
    }

    private void a(boolean z, int i, boolean z2) {
        if (z) {
            switch (i) {
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    b((int) XZBDevice.DOWNLOAD_LIST_ALL);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    i();
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    b((int) XZBDevice.DOWNLOAD_LIST_FAILED);
                default:
                    if (z2) {
                        i();
                    }
            }
        } else if (z2) {
            i();
        } else {
            this.w.setText(2131232990);
            this.V = e;
            b(this.ad);
        }
    }

    private void i() {
        long c = c(new bx().f());
        if (c != -1) {
            if (c < 31) {
                b((int) XZBDevice.DOWNLOAD_LIST_FAILED);
            } else {
                b((int) XZBDevice.DOWNLOAD_LIST_ALL);
            }
        }
    }

    private void b(int i) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.w.setText(2131232991);
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.w.setText(2131232992);
                break;
        }
        b(this.ad);
    }

    private void b(boolean z) {
        if (z) {
            this.x.setVisibility(0);
            this.x.setBackgroundResource(2130839600);
            AnimationDrawable animationDrawable = (AnimationDrawable) this.x.getBackground();
            animationDrawable.start();
            int i = 0;
            for (int i2 = 0; i2 < animationDrawable.getNumberOfFrames(); i2++) {
                i += animationDrawable.getDuration(i2);
            }
            new Handler().postDelayed(new bg(this, animationDrawable), (long) i);
            this.ad = false;
        }
    }

    private void a(boolean z, int i, int i2, boolean z2) {
        int i3 = 0;
        switch (i2) {
            case SpdyAgent.ACCS_TEST_SERVER:
                if (z2) {
                    j();
                } else {
                    this.s.setImageResource(2130838703);
                    this.t.setVisibility(XZBDevice.Wait);
                }
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.s.setImageResource(2130838703);
                this.t.setVisibility(XZBDevice.Wait);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (z) {
                    this.t.setTextColor(this.mActivity.getResources().getColor(2131689721));
                    this.s.setImageResource(2130838701);
                    c(i);
                } else if (z2) {
                    j();
                } else {
                    this.t.setTextColor(this.mActivity.getResources().getColor(2131689722));
                    this.s.setImageResource(2130838702);
                    c(i);
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                if (z) {
                    this.t.setTextColor(this.mActivity.getResources().getColor(2131689721));
                    this.s.setImageResource(2130838704);
                    d(i);
                } else if (z2) {
                    j();
                } else {
                    this.t.setTextColor(this.mActivity.getResources().getColor(2131689722));
                    this.s.setImageResource(2130838707);
                    d(i);
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                if (z) {
                    this.t.setTextColor(this.mActivity.getResources().getColor(2131689721));
                    if (i >= 6) {
                        this.s.setImageResource(2130838705);
                    } else {
                        this.s.setImageResource(2130838709);
                    }
                } else {
                    this.t.setTextColor(this.mActivity.getResources().getColor(2131689722));
                    if (i >= 6) {
                        this.s.setImageResource(2130838706);
                    } else {
                        this.s.setImageResource(2130838712);
                    }
                }
                if (i > 0) {
                    this.t.setText(String.valueOf(i));
                    this.s.setVisibility(0);
                    this.t.setVisibility(0);
                } else {
                    this.s.setImageResource(2130838703);
                    this.t.setVisibility(XZBDevice.Wait);
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (z) {
                    this.t.setTextColor(this.mActivity.getResources().getColor(2131689721));
                    this.s.setImageResource(2130838709);
                    e(i);
                } else if (z2) {
                    j();
                } else {
                    this.t.setTextColor(this.mActivity.getResources().getColor(2131689722));
                    this.s.setImageResource(2130838712);
                    e(i);
                }
                break;
            default:
                if (z2) {
                    j();
                }
                break;
        }
        if (z || z2) {
            bx bxVar = new bx();
            if (!bxVar.g()) {
                new StringBuilder("paid id=").append(bxVar.j);
                if (bxVar.j <= 200 && bxVar.j % 5 == 0 && bxVar.j % 10 != 0) {
                    i3 = 1;
                }
            } else if (bxVar.g <= 200 && bxVar.g % 5 == 0 && bxVar.g % 10 != 0) {
                i3 = 1;
            }
            if (i3 != 0) {
                this.u.setImageResource(2130838696);
                return;
            } else {
                this.u.setImageResource(2130838697);
                return;
            }
        }
        this.u.setImageResource(2130838697);
    }

    private void a(View view, int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(com.xunlei.downloadprovider.a.g.a(this.mActivity, (float) i), layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    private void j() {
        this.s.setImageResource(2130838700);
        this.t.setVisibility(XZBDevice.Wait);
    }

    private void c(int i) {
        if (i > 0) {
            this.t.setText(String.valueOf(i));
            this.s.setVisibility(0);
            this.t.setVisibility(0);
            return;
        }
        this.s.setImageResource(2130838703);
        this.t.setVisibility(XZBDevice.Wait);
    }

    private void d(int i) {
        if (i > 0) {
            this.t.setText(String.valueOf(i));
            this.s.setVisibility(0);
            this.t.setVisibility(0);
            return;
        }
        this.s.setImageResource(2130838703);
        this.t.setVisibility(XZBDevice.Wait);
    }

    private void e(int i) {
        if (i > 0) {
            this.t.setText(String.valueOf(i));
            this.s.setVisibility(0);
            this.t.setVisibility(0);
            return;
        }
        this.s.setImageResource(2130838703);
        this.t.setVisibility(XZBDevice.Wait);
    }

    private void k() {
        String string = this.mActivity.getString(2131233244);
        WebViewNormalActivity.a(getActivity(), com.umeng.a.d, "http://act.vip.xunlei.com/vip/2015/shoulei_v2/", string);
        a("per_cl_vipCenter", "per_cl_vipCenter", null);
    }

    private void a(boolean z, boolean z2, String str) {
        String str2 = "http://m.sjzhushou.com/v2/store/task_list.html?sign=0";
        if (z2) {
            str2 = "http://m.sjzhushou.com/v2/store/task_list.html?sign=1";
        }
        BrowserUtil.a();
        BrowserUtil.a(this.mActivity, str2, "\u6211\u7684\u4efb\u52a1", R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, null);
        if (z) {
            a("per_cl_task", "per_cl_task", str);
        }
    }

    private void c(boolean z) {
        PayFrom payFrom = PayFrom.PERSONAL_CENTER_TOP;
        if (z) {
            payFrom = PayFrom.PERSONAL_CENTER_RENEWTIP;
        }
        int i = com.xunlei.downloadprovider.homepage.a.a.d.a;
        PayEntryParam payEntryParam = new PayEntryParam(payFrom);
        payEntryParam.c = i;
        PaymentEntryActivity.a(getActivity(), payEntryParam);
        if (!z) {
            if (this.V == g) {
                d("personal_center");
            }
            int c = (int) c(this.k.n());
            int i2 = this.V;
            i = 0;
            if (this.k.f()) {
                i = 1;
            }
            if (i2 == 2) {
                i2 = 1;
            }
            com.xunlei.downloadprovider.model.protocol.report.b.a("android_personal_click", "per_cl_pay", new com.xunlei.downloadprovider.model.protocol.report.b.a().a("is_vip", (long) i).a("rest_days", (long) c).a("isrenew", (long) i2));
            StatReporter.reportPersonalPayClick(i, c, i2);
        }
    }

    private void l() {
        this.mActivity.startActivity(new Intent(this.mActivity, LixianSpaceActivity.class));
        a("per_cl_cloudLixian", "per_cl_cloudLixian", null);
    }

    private void a(String str, String str2, String str3) {
        int i;
        int i2 = 1;
        if (this.k.f()) {
            i = 1;
        } else {
            i = 0;
        }
        if (!LoginHelper.c()) {
            i2 = 0;
        }
        com.xunlei.downloadprovider.model.protocol.report.b.a a = new com.xunlei.downloadprovider.model.protocol.report.b.a().a("is_vip", (long) i);
        if (!str.equals("per_cl_usericon")) {
            a.a(SystemUtils.IS_LOGIN, (long) i2);
        }
        if (!TextUtils.isEmpty(str3)) {
            a.a("position", str3);
        }
        com.xunlei.downloadprovider.model.protocol.report.b.a("android_personal_click", str, a);
        StatReporter.reportPersonalNormalClick(str2, i, i2, str3);
    }

    private void m() {
        SQLiteDatabase writableDatabase;
        SQLiteDatabase sQLiteDatabase;
        SQLiteException sQLiteException;
        Throwable th;
        long j = 0;
        Cursor cursor = null;
        if (com.xunlei.downloadprovider.homepage.a.a.d.b != null && com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(com.tencent.connect.common.Constants.VIA_SHARE_TYPE_TEXT)) {
            com.xunlei.downloadprovider.homepage.a.a.d dVar = (com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(com.tencent.connect.common.Constants.VIA_SHARE_TYPE_TEXT);
            if (dVar != null) {
                String str = dVar.d;
                String str2 = dVar.e;
                if (!TextUtils.isEmpty(r2) && LoginHelper.c()) {
                    if (!com.xunlei.downloadprovider.frame.user.a.b.a(this.mActivity).d(String.valueOf(this.k.j)) && ((MainTabActivity) this.mActivity).c == this) {
                        long j2;
                        com.xunlei.downloadprovider.commonview.dialog.k kVar = new com.xunlei.downloadprovider.commonview.dialog.k(this.mActivity);
                        if (r2 != null) {
                            kVar.a.setText(r2);
                        }
                        kVar.setCanceledOnTouchOutside(false);
                        if (r1 != null) {
                            kVar.b.setText(r1);
                        }
                        kVar.a(new ba(this, kVar));
                        kVar.show();
                        ThunderReporter.g gVar = new ThunderReporter.g();
                        gVar.a = "android_renewTip";
                        gVar.b = "renewTip_show";
                        gVar.c = "renewTip_show";
                        gVar.a("from", "personal_center_tip", (int) XZBDevice.DOWNLOAD_LIST_FAILED);
                        gVar.b(SystemUtils.IS_LOGIN, LoginHelper.c() ? 1 : 0);
                        String str3 = "is_vip";
                        if (this.k.f()) {
                            j2 = 1;
                        } else {
                            j2 = 0;
                        }
                        gVar.b(str3, j2);
                        gVar.b("renewdays", (long) com.xunlei.downloadprovider.homepage.a.a.d.a);
                        ThunderReporter.a(gVar, true);
                        Object valueOf = String.valueOf(this.k.j);
                        com.xunlei.downloadprovider.frame.user.a.b a = com.xunlei.downloadprovider.frame.user.a.b.a(this.mActivity);
                        if (!TextUtils.isEmpty(valueOf)) {
                            try {
                                writableDatabase = a.getWritableDatabase();
                                try {
                                    boolean z;
                                    writableDatabase.beginTransaction();
                                    cursor = writableDatabase.query("User_Vip_Continue_Tip_Table", null, "userId = ?", new String[]{valueOf}, null, null, null);
                                    str2 = com.umeng.a.d;
                                    if (cursor == null || !cursor.moveToFirst()) {
                                        str = str2;
                                        z = false;
                                    } else {
                                        j = cursor.getLong(XZBDevice.DOWNLOAD_LIST_FAILED);
                                        str = cursor.getString(XZBDevice.DOWNLOAD_LIST_ALL);
                                        z = true;
                                    }
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.clear();
                                    contentValues.put("userId", valueOf);
                                    contentValues.put("viplastTipTime", Long.valueOf(System.currentTimeMillis()));
                                    contentValues.put("tasklastTipTime", Long.valueOf(j));
                                    contentValues.put("extTip1", str);
                                    if (z) {
                                        writableDatabase.update("User_Vip_Continue_Tip_Table", contentValues, "userId = ?", new String[]{valueOf});
                                    } else {
                                        writableDatabase.insert("User_Vip_Continue_Tip_Table", null, contentValues);
                                    }
                                    writableDatabase.setTransactionSuccessful();
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (writableDatabase != null && writableDatabase.isOpen()) {
                                        writableDatabase.endTransaction();
                                        writableDatabase.close();
                                        return;
                                    }
                                    return;
                                } catch (SQLiteConstraintException e) {
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (writableDatabase == null) {
                                    }
                                } catch (SQLiteException e2) {
                                    SQLiteException sQLiteException2 = e2;
                                    sQLiteDatabase = writableDatabase;
                                    sQLiteException = sQLiteException2;
                                    try {
                                        sQLiteException.printStackTrace();
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (sQLiteDatabase == null) {
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        sQLiteDatabase.endTransaction();
                                        sQLiteDatabase.close();
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    Throwable th4 = th3;
                                    sQLiteDatabase = writableDatabase;
                                    th = th4;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    sQLiteDatabase.endTransaction();
                                    sQLiteDatabase.close();
                                    throw th;
                                }
                            } catch (SQLiteConstraintException e3) {
                                writableDatabase = null;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (writableDatabase == null && writableDatabase.isOpen()) {
                                    writableDatabase.endTransaction();
                                    writableDatabase.close();
                                }
                            } catch (SQLiteException e4) {
                                sQLiteException = e4;
                                sQLiteDatabase = null;
                                sQLiteException.printStackTrace();
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (sQLiteDatabase == null && sQLiteDatabase.isOpen()) {
                                    sQLiteDatabase.endTransaction();
                                    sQLiteDatabase.close();
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                sQLiteDatabase = null;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                    sQLiteDatabase.endTransaction();
                                    sQLiteDatabase.close();
                                }
                                throw th;
                            }
                        }
                        return;
                    }
                    return;
                }
            }
        }
        CharSequence charSequence = null;
        CharSequence charSequence2 = null;
        if (!TextUtils.isEmpty(charSequence2)) {
        }
    }

    private void n() {
        v.a().a(System.currentTimeMillis(), "user_center");
        ((MainTabActivity) this.mActivity).b(false);
    }

    private static void d(String str) {
        ThunderReporter.g gVar = new ThunderReporter.g();
        gVar.a = "android_renewTip";
        gVar.b = "renewTip_click";
        gVar.c = "renewTip_click";
        gVar.a("from", str, (int) XZBDevice.DOWNLOAD_LIST_FAILED);
        ThunderReporter.a(gVar, true);
    }

    private void a(int i, com.xunlei.downloadprovider.member.login.b bVar) {
        if (LoginHelper.a().f() && i == 0 && bVar != null && bVar.a == 0 && bVar.e != 0) {
            long j = bVar.e - bVar.c;
            long j2 = bVar.e;
            new StringBuilder("UserCenterFragment() =====>> totalSize  >>").append(j2).append(", usedSize >>").append(j);
            if (NetworkHelper.isNetworkAvailable(getApplicationContext())) {
                aa.a(getApplicationContext(), "maxspace", j2);
                aa.a(getApplicationContext(), "availablespace", j);
            }
        }
    }

    static /* synthetic */ void a(UserCenterFragment userCenterFragment, j.b bVar) {
        if (bVar != null) {
            Object valueOf = String.valueOf(userCenterFragment.k.j);
            if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(bVar.c) && valueOf.equals(bVar.c) && bVar.b == 0) {
                boolean z;
                a aVar = userCenterFragment.Y;
                if (TextUtils.isEmpty(bVar.a) || bVar.a.equals(MessageService.MSG_DB_READY_REPORT)) {
                    z = false;
                } else {
                    z = true;
                }
                aVar.b = z;
                if (bVar.e > 0) {
                    userCenterFragment.Y.d = bVar.e;
                }
                if (userCenterFragment.isAdded()) {
                    userCenterFragment.a(userCenterFragment.Y.b);
                }
                if (userCenterFragment.Y.b) {
                    com.xunlei.downloadprovider.frame.user.a.b.a(userCenterFragment.mActivity).a(String.valueOf(userCenterFragment.k.j), userCenterFragment.Y.d, System.currentTimeMillis());
                }
            }
        }
    }

    static /* synthetic */ void a(UserCenterFragment userCenterFragment, j.a aVar) {
        if (aVar != null) {
            Object valueOf = String.valueOf(userCenterFragment.k.j);
            if (!TextUtils.isEmpty(valueOf) && !TextUtils.isEmpty(aVar.b) && valueOf.equals(aVar.b) && aVar.a == 0) {
                userCenterFragment.Y.c = aVar.c;
                if (userCenterFragment.isAdded()) {
                    userCenterFragment.a(userCenterFragment.Y.c);
                }
                com.xunlei.downloadprovider.frame.user.a.b.a(userCenterFragment.mActivity).a(String.valueOf(userCenterFragment.k.j), userCenterFragment.Y.c);
            }
        }
    }

    static /* synthetic */ void j(UserCenterFragment userCenterFragment) {
        int width = (userCenterFragment.Q.getWidth() - userCenterFragment.S.getWidth()) / 2;
        LayoutParams layoutParams = (LayoutParams) userCenterFragment.K.getLayoutParams();
        layoutParams.rightMargin = (width - (userCenterFragment.K.getWidth() / 2)) + com.xunlei.downloadprovider.a.g.a(userCenterFragment.getActivity(), 3.0f);
        int dimension = (int) userCenterFragment.mActivity.getResources().getDimension(2131362110);
        layoutParams.topMargin = (dimension - (userCenterFragment.K.getHeight() / 2)) + com.xunlei.downloadprovider.a.g.a(userCenterFragment.getActivity(), 4.0f);
        userCenterFragment.K.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) userCenterFragment.L.getLayoutParams();
        layoutParams.rightMargin = (width - (userCenterFragment.L.getWidth() / 2)) + 3;
        layoutParams.topMargin = dimension - (userCenterFragment.L.getHeight() / 2);
        userCenterFragment.L.setLayoutParams(layoutParams);
        int width2 = userCenterFragment.R.getWidth();
        layoutParams = (LayoutParams) userCenterFragment.R.getLayoutParams();
        layoutParams.rightMargin = (width - (width2 / 2)) + 2;
        layoutParams.topMargin = (dimension - (width2 / 2)) + 2;
        userCenterFragment.R.setLayoutParams(layoutParams);
    }

    static /* synthetic */ void l(UserCenterFragment userCenterFragment) {
        userCenterFragment.y.setVisibility(0);
        Animation loadAnimation = AnimationUtils.loadAnimation(userCenterFragment.y.getContext(), 2131034225);
        userCenterFragment.y.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new bh(userCenterFragment));
    }

    static /* synthetic */ void n(UserCenterFragment userCenterFragment) {
        userCenterFragment.z.setVisibility(0);
        Animation loadAnimation = AnimationUtils.loadAnimation(userCenterFragment.y.getContext(), 2131034225);
        userCenterFragment.z.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new bi(userCenterFragment));
    }
}
