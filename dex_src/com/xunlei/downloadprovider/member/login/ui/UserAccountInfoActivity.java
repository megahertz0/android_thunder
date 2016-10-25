package com.xunlei.downloadprovider.member.login.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.homepage.a.a.d;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.b;
import com.xunlei.downloadprovider.member.login.LoginHelper.o;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.member.payment.external.OperType;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.personal.settings.HelpActivity;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.text.DecimalFormat;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public class UserAccountInfoActivity extends BaseActivity implements OnClickListener {
    public static boolean a;
    private TextView A;
    private RelativeLayout B;
    private f C;
    private TextView D;
    private TextView E;
    private PayEntryParam F;
    private a G;
    private Handler H;
    private p I;
    private o J;
    private boolean K;
    private String L;
    private final LoginHelper M;
    b b;
    private final int c;
    private final int d;
    private final String e;
    private final String f;
    private TextView g;
    private ImageView h;
    private UserinfoProgress i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private Button m;
    private LinearLayout n;
    private TextView o;
    private TextView p;
    private TextView q;
    private Button r;
    private TextView s;
    private TextView t;
    private View u;
    private ImageView v;
    private TextView w;
    private RelativeLayout x;
    private RelativeLayout y;
    private UserinfoProgress z;

    public UserAccountInfoActivity() {
        this.c = 100;
        this.d = 101;
        this.e = "flowtotal";
        this.f = "flowused";
        this.z = null;
        this.F = new PayEntryParam(PayFrom.ACCOUNT_CENTER);
        this.G = new x(this);
        this.H = new h.b(this.G);
        this.I = new z(this);
        this.J = new aa(this);
        this.M = LoginHelper.a();
        this.b = new ac(this);
    }

    static {
        a = false;
    }

    protected void onStop() {
        a = false;
        super.onStop();
    }

    public void logOutNotByUser(DialogInterface.OnClickListener onClickListener) {
        super.logOutNotByUser(new y(this));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass()).append("---onCreate ---").append(Thread.currentThread().getId());
        if (getIntent().hasExtra("callbackJson")) {
            this.L = getIntent().getExtras().getString("callbackJson");
        }
        LoginHelper.a().s();
        setContentView(2130968878);
        this.l = (TextView) findViewById(2131756613);
        this.l.setOnClickListener(this);
        this.n = (LinearLayout) findViewById(2131756596);
        this.m = (Button) findViewById(2131756597);
        this.m.setOnClickListener(this);
        this.o = (TextView) findViewById(2131756595);
        this.p = (TextView) findViewById(2131756603);
        this.p.setOnClickListener(this);
        this.E = (TextView) findViewById(2131756602);
        this.u = findViewById(2131756593);
        this.B = (RelativeLayout) findViewById(2131756572);
        this.w = (TextView) findViewById(2131756576);
        this.j = (ImageView) findViewById(2131756581);
        this.i = (UserinfoProgress) findViewById(2131756582);
        this.s = (TextView) findViewById(2131756610);
        this.z = (UserinfoProgress) findViewById(2131756587);
        this.A = (TextView) findViewById(2131756588);
        this.y = (RelativeLayout) findViewById(2131756584);
        this.x = (RelativeLayout) findViewById(2131756577);
        this.v = (ImageView) findViewById(2131756607);
        this.v.setOnClickListener(this);
        this.q = (TextView) findViewById(2131756599);
        this.r = (Button) findViewById(2131756600);
        this.r.setOnClickListener(this);
        if (this.M.f() || aa.c(getApplicationContext(), "isvip")) {
            this.r.setVisibility(0);
        } else {
            this.r.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
        this.t = (TextView) findViewById(2131756606);
        this.C = new f((Activity) this);
        this.C.i.setText(getResources().getString(2131233070));
        this.C.i.setOnClickListener(this);
        g.a(this, 14.0f);
        this.k = (TextView) findViewById(2131756583);
        this.g = (TextView) findViewById(R.id.user_level);
        this.h = (ImageView) findViewById(2131756579);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.D = (TextView) findViewById(2131756612);
        this.q.setText(PayUtil.a(new bx().f()));
        this.E.setText(new StringBuilder("LV").append(new bx().b).toString());
        if (new bx().e()) {
            this.p.setBackgroundResource(2130837612);
            this.p.setTextColor(-1699);
            if (new bx().a()) {
                this.B.setVisibility(XZBDevice.Wait);
                this.u.setVisibility(0);
                this.p.setBackgroundResource(2130837609);
                this.p.setText(new StringBuilder("VIP").append(new bx().b).toString());
            } else if (new bx().b()) {
                this.u.setVisibility(0);
                this.B.setVisibility(XZBDevice.Wait);
                if (new bx().b < 6) {
                    this.p.setBackgroundResource(2130837613);
                } else {
                    this.p.setBackgroundResource(2130837614);
                }
                this.p.setText(new StringBuilder("VIP").append(new bx().b).toString());
            } else if (new bx().c()) {
                this.u.setVisibility(0);
                this.B.setVisibility(XZBDevice.Wait);
                if (new bx().b < 6) {
                    this.p.setBackgroundResource(2130837617);
                } else {
                    this.p.setBackgroundResource(2130837614);
                }
                this.p.setText(new StringBuilder("VIP").append(new bx().b).toString());
            } else if (new bx().d()) {
                this.u.setVisibility(0);
                this.B.setVisibility(XZBDevice.Wait);
                if (new bx().b < 6) {
                    this.p.setBackgroundResource(2130837617);
                } else {
                    this.p.setBackgroundResource(2130837618);
                }
                this.p.setText(new StringBuilder("VIP").append(new bx().b).toString());
            }
        } else {
            this.B.setVisibility(XZBDevice.Wait);
            this.u.setVisibility(0);
            this.p.setBackgroundResource(2130837612);
            this.p.setTextColor(-1);
            if (new bx().a()) {
                this.B.setVisibility(XZBDevice.Wait);
                this.u.setVisibility(0);
                bx bxVar = new bx();
                this.p.setBackgroundResource(2130837610);
                this.p.setText(new StringBuilder("VIP").append(new bx().b).toString());
            } else if (new bx().b()) {
                this.u.setVisibility(0);
                this.B.setVisibility(XZBDevice.Wait);
                if (new bx().b < 6) {
                    this.p.setBackgroundResource(2130837615);
                } else {
                    this.p.setBackgroundResource(2130837616);
                }
                this.p.setText(new StringBuilder("VIP").append(new bx().b).toString());
            } else if (new bx().c()) {
                this.u.setVisibility(0);
                this.B.setVisibility(XZBDevice.Wait);
                if (new bx().b < 6) {
                    this.p.setBackgroundResource(2130837619);
                } else {
                    this.p.setBackgroundResource(2130837616);
                }
                this.p.setText(new StringBuilder("VIP").append(new bx().b).toString());
            } else if (new bx().d()) {
                this.u.setVisibility(0);
                this.B.setVisibility(XZBDevice.Wait);
                if (new bx().b < 6) {
                    this.p.setBackgroundResource(2130837619);
                } else {
                    this.p.setBackgroundResource(2130837620);
                }
                this.p.setText(new StringBuilder("VIP").append(new bx().b).toString());
            }
        }
        this.t.setText(new bx().h + "/" + ad.b(new bx().b));
        TextView textView = this.o;
        CharSequence charSequence = com.umeng.a.d;
        boolean f = this.M.f();
        int i = this.M.h;
        if (f || aa.c(getApplicationContext(), "isvip")) {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.n.setVisibility(0);
                    charSequence = "\u8ff7\u4f60\u4f1a\u5458";
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.n.setVisibility(0);
                    charSequence = "\u666e\u901a\u4f1a\u5458";
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.n.setVisibility(0);
                    charSequence = "\u767d\u91d1\u4f1a\u5458";
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    this.n.setVisibility(XZBDevice.Wait);
                    charSequence = "\u7816\u77f3\u4f1a\u5458";
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    this.n.setVisibility(XZBDevice.Wait);
                    charSequence = "\u8d85\u7ea7\u4f1a\u5458";
                    break;
            }
        }
        textView.setText(charSequence);
        LoginHelper a = LoginHelper.a();
        a.a(this.I);
        a.a(this.J);
        a.a(this.b);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.L = intent.getExtras().getString("callbackJson");
    }

    public void onResume() {
        LoginHelper a;
        super.onResume();
        LoginHelper.a();
        if (LoginHelper.c()) {
            a = LoginHelper.a();
            if (LoginHelper.c()) {
                this.p.setVisibility(XZBDevice.Wait);
                if (a.f()) {
                    this.p.setBackgroundResource(2130837612);
                    this.p.setTextColor(-1699);
                    if (a.g()) {
                        this.B.setVisibility(XZBDevice.Wait);
                        this.u.setVisibility(0);
                        this.p.setBackgroundResource(2130837609);
                        this.p.setText(new StringBuilder("VIP").append(a.e).toString());
                    } else if (a.i()) {
                        this.u.setVisibility(0);
                        this.B.setVisibility(XZBDevice.Wait);
                        if (a.e < 6) {
                            this.p.setBackgroundResource(2130837613);
                        } else {
                            this.p.setBackgroundResource(2130837614);
                        }
                        this.p.setText(new StringBuilder("VIP").append(a.e).toString());
                    } else if (a.j()) {
                        this.u.setVisibility(0);
                        this.B.setVisibility(XZBDevice.Wait);
                        if (a.e < 6) {
                            this.p.setBackgroundResource(2130837617);
                        } else {
                            this.p.setBackgroundResource(2130837614);
                        }
                        this.p.setText(new StringBuilder("VIP").append(a.e).toString());
                    } else if (a.k()) {
                        this.u.setVisibility(0);
                        this.B.setVisibility(XZBDevice.Wait);
                        if (a.e < 6) {
                            this.p.setBackgroundResource(2130837617);
                        } else {
                            this.p.setBackgroundResource(2130837618);
                        }
                        this.p.setText(new StringBuilder("VIP").append(a.e).toString());
                    }
                } else {
                    this.B.setVisibility(XZBDevice.Wait);
                    this.u.setVisibility(0);
                    this.p.setBackgroundResource(2130837612);
                    this.p.setTextColor(-1);
                    if (a.g()) {
                        this.B.setVisibility(XZBDevice.Wait);
                        this.u.setVisibility(0);
                        this.p.setBackgroundResource(2130837610);
                        this.p.setText(new StringBuilder("VIP").append(a.e).toString());
                    } else if (a.i()) {
                        this.u.setVisibility(0);
                        this.B.setVisibility(XZBDevice.Wait);
                        if (a.e < 6) {
                            this.p.setBackgroundResource(2130837615);
                        } else {
                            this.p.setBackgroundResource(2130837616);
                        }
                        this.p.setText(new StringBuilder("VIP").append(a.e).toString());
                    } else if (a.j()) {
                        this.u.setVisibility(0);
                        this.B.setVisibility(XZBDevice.Wait);
                        if (a.e < 6) {
                            this.p.setBackgroundResource(2130837619);
                        } else {
                            this.p.setBackgroundResource(2130837616);
                        }
                        this.p.setText(new StringBuilder("VIP").append(a.e).toString());
                    } else if (a.k()) {
                        this.u.setVisibility(0);
                        this.B.setVisibility(XZBDevice.Wait);
                        if (a.e < 6) {
                            this.p.setBackgroundResource(2130837619);
                        } else {
                            this.p.setBackgroundResource(2130837620);
                        }
                        this.p.setText(new StringBuilder("VIP").append(a.e).toString());
                    }
                }
            }
            b();
        }
        LoginHelper.a();
        if (LoginHelper.c() && com.xunlei.xllib.a.b.a(this)) {
            b();
            a = LoginHelper.a();
            a.s();
            a.v();
            com.xunlei.downloadprovider.member.login.b bVar = LoginHelper.a().D;
            if (bVar == null || bVar.a != 0) {
                a.w();
            } else {
                a(0, bVar);
            }
            LoginHelper.a().a(new ab(this));
        } else if (!com.xunlei.xllib.a.b.a(this)) {
            this.o.setText(aa.b(getApplicationContext(), "usermembertype"));
            this.q.setText(aa.b(getApplicationContext(), "usermemberdate"));
            this.E.setText(aa.b(getApplicationContext(), "usermemberlevel"));
            this.t.setText(aa.b(getApplicationContext(), "usermemberscore"));
            long a2 = aa.a(getApplicationContext(), "flowtotal");
            long a3 = aa.a(getApplicationContext(), "flowused");
            CharSequence charSequence = com.umeng.a.d;
            try {
                charSequence = a(com.xunlei.downloadprovider.d.a.a(a3, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE)) + "/" + a(com.xunlei.downloadprovider.d.a.a(a2, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE));
            } catch (Exception e) {
            }
            this.s.setText(charSequence);
            long a4 = aa.a(getApplicationContext(), "maxspace");
            String a5 = com.xunlei.downloadprovider.d.a.a(aa.a(getApplicationContext(), "availablespace"), (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            String a6 = com.xunlei.downloadprovider.d.a.a(a4, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            charSequence = com.umeng.a.d;
            try {
                charSequence = a(a5) + "/" + a(a6);
            } catch (Exception e2) {
            }
            this.D.setText(charSequence);
        }
    }

    protected void onStart() {
        a = true;
        super.onStart();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case com.xunlei.xiazaibao.R.id.titlebar_left:
                onBackPressed();
            case 2131756579:
            case R.id.user_level:
            case 2131756607:
                StatReporter.reportPayQuestion("question");
                HelpActivity.a((Context) this, "file:///android_asset/help/level2.html");
            case 2131756597:
                a();
                if (LoginHelper.d()) {
                    PaymentEntryActivity.a(this, this.F);
                }
            case 2131756600:
                PayFrom payFrom = PayFrom.ACCOUNT_CENTER;
                int i = d.a;
                PayEntryParam payEntryParam = new PayEntryParam(payFrom);
                payEntryParam.c = i;
                PaymentEntryActivity.a(this, payEntryParam);
            case 2131756603:
                new StringBuilder().append(getClass()).append("---R.id.iv_top_vip_icon ---").append(Thread.currentThread().getId());
                StatReporter.reportPer_accountpage_click(LoginHelper.a().f() ? MessageService.MSG_DB_NOTIFY_REACHED : MessageService.MSG_DB_READY_REPORT, "account_level");
                String string = getString(2131233244);
                Bundle bundle = new Bundle();
                bundle.putInt("from_entry", R.styleable.AppCompatTheme_textAppearanceLargePopupMenu);
                BrowserUtil.a();
                BrowserUtil.a((Context) this, "http://act.vip.xunlei.com/vip/2015/shoulei_v2/", string, bundle);
            case 2131756613:
                this.l.getBackground().setAlpha(143);
            case R.id.simple_title_left:
                finish();
            case R.id.simple_title_right:
                new StringBuilder().append(getClass()).append("---R.id.simple_title_right ---").append(Thread.currentThread().getId());
                StatReporter.reportPayExit("exit");
            default:
                break;
        }
    }

    private void b() {
        new StringBuilder().append(getClass()).append("---onUserInfoUpdated()---").append(Thread.currentThread().getId());
        LoginHelper a = LoginHelper.a();
        new StringBuilder("onUserInfoUpdate()...").append(LoginHelper.c());
        if (LoginHelper.c()) {
            int i = a.f;
            int a2 = ad.a(i);
            int b = ad.b(a2);
            if (this.B.getVisibility() == 0) {
                this.g.setText(a2 + "\u7ea7");
                this.i.setProgressBar((((float) this.j.getWidth()) * ((float) i)) / ((float) b));
                this.k.setText(i + "/" + b);
            } else if (this.u.getVisibility() == 0) {
                this.t.setText(i + "/" + b);
                this.E.setText(new StringBuilder("LV").append(a2).toString());
            }
            if (a.f()) {
                String n;
                if (a.g()) {
                    n = a.n();
                    this.B.setVisibility(XZBDevice.Wait);
                    this.u.setVisibility(0);
                    this.q.setText(PayUtil.a(n));
                    return;
                } else if (a.i()) {
                    this.u.setVisibility(0);
                    this.B.setVisibility(XZBDevice.Wait);
                    this.q.setText(PayUtil.a(a.n()));
                    return;
                } else if (a.j()) {
                    this.u.setVisibility(0);
                    this.B.setVisibility(XZBDevice.Wait);
                    this.p.setVisibility(XZBDevice.Wait);
                    this.q.setText(PayUtil.a(a.n()));
                    return;
                } else if (a.k()) {
                    new StringBuilder().append(getClass()).append("---onUserInfoUpdated()---loginHelper.isSuperMember() ---").append(Thread.currentThread().getId());
                    this.u.setVisibility(0);
                    this.B.setVisibility(XZBDevice.Wait);
                    this.p.setVisibility(XZBDevice.Wait);
                    this.q.setText(PayUtil.a(a.n()));
                    return;
                } else if (a.h()) {
                    n = a.n();
                    this.B.setVisibility(XZBDevice.Wait);
                    this.u.setVisibility(0);
                    this.q.setText(PayUtil.a(n));
                    return;
                }
            }
            this.u.setVisibility(0);
            this.B.setVisibility(XZBDevice.Wait);
            this.q.setText("\u8bf7\u5f00\u901a\u4f1a\u5458");
            this.v.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            this.g.setText(2131233056);
            this.D.setText("\u672a\u5f00\u901a");
        }
    }

    private void a(int i, com.xunlei.downloadprovider.member.login.b bVar) {
        if (!LoginHelper.a().f() || i != 0 || bVar == null || bVar.a != 0) {
            return;
        }
        if (bVar.e == 0) {
            this.D.setText(2131233059);
            return;
        }
        long j = bVar.e - bVar.c;
        long j2 = bVar.e;
        aa.a(getApplicationContext(), "maxspace", j2);
        aa.a(getApplicationContext(), "availablespace", j);
        String a = com.xunlei.downloadprovider.d.a.a(j, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        String a2 = com.xunlei.downloadprovider.d.a.a(j2, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        CharSequence charSequence = com.umeng.a.d;
        try {
            charSequence = a(a) + "/" + a(a2);
        } catch (Exception e) {
        }
        this.D.setText(charSequence);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public final String a() {
        OperType operType = OperType.NORMAL;
        String str = "\u7eed\u8d39\u767d\u91d1\u4f1a\u5458";
        int i = this.M.h;
        if (!this.M.f() || i == 1) {
            str = "\u5f00\u901a\u4f1a\u5458";
        } else if (i == 5) {
            str = "\u7eed\u8d39\u8d85\u7ea7\u4f1a\u5458";
        } else if (i == 4) {
            if (LoginHelper.a().e <= 0 || LoginHelper.a().e > 5) {
                str = "\u5347\u7ea7\u8d85\u7ea7\u4f1a\u5458";
                operType = OperType.UPGRADE;
            } else {
                str = "\u7eed\u8d39\u8d85\u7ea7\u4f1a\u5458";
            }
        } else if (i == 3) {
            str = "\u5347\u7ea7\u8d85\u7ea7\u4f1a\u5458";
            operType = OperType.UPGRADE;
        } else if (i == 2) {
            str = "\u5347\u7ea7\u767d\u91d1\u4f1a\u5458";
            operType = OperType.UPGRADE;
        }
        this.F.b = operType;
        return str;
    }

    private String a(String str) {
        String substring = str.substring(str.length() - 2, str.length());
        String substring2 = str.substring(0, str.length() - 2);
        if (substring2 == null || substring2.equals(com.umeng.a.d)) {
            return substring;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        new StringBuilder().append(getClass()).append("---totalString---totalString2---Unit---df.format(Double.valueOf(totalString2))+Unit---").append(str).append("---").append(substring2).append("---").append(substring).append("---").append(decimalFormat.format(Double.valueOf(substring2))).append(substring).append("---").append(Thread.currentThread().getId());
        return decimalFormat.format(Double.valueOf(substring2)) + substring;
    }

    protected void onDestroy() {
        LoginHelper a = LoginHelper.a();
        a.b(this.I);
        a.w.remove(this.J);
        a.E.remove(this.b);
        super.onDestroy();
    }
}
