package com.xunlei.downloadprovider.member.payment.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.toolbox.t;
import com.tencent.connect.common.Constants;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.xunlei.common.accelerator.XLAccelUtil;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLPriceParam;
import com.xunlei.downloadprovider.commonview.SimpleLoadingPageView;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.f;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.a.k;
import com.xunlei.downloadprovider.member.payment.a.l;
import com.xunlei.downloadprovider.member.payment.a.m;
import com.xunlei.downloadprovider.member.payment.b;
import com.xunlei.downloadprovider.member.payment.bean.PayConfigurationParam;
import com.xunlei.downloadprovider.member.payment.external.OperType;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType;
import com.xunlei.downloadprovider.member.payment.external.g;
import com.xunlei.downloadprovider.member.payment.external.h;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public class PayActivity extends BasePayPagerActivity implements OnClickListener {
    g g;
    OrderType h;
    int i;
    String j;
    HashSet<String> k;
    private SimpleLoadingPageView l;
    private x m;
    private View n;
    private int o;
    private f p;
    private PayConfigurationParam q;
    private boolean r;
    private int s;
    private OperType t;
    private String u;
    private PayFrom v;
    private j w;
    private LocalBroadcastManager x;
    private MyBroadcastReceiver y;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.a().length];
            try {
                a[a.d - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.a - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("action.type.activation.pay.success")) {
                PayActivity.this.a(false);
            }
        }
    }

    private enum a {
        ;

        public static int[] a() {
            return (int[]) e.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = new int[]{a, b, c, d};
        }
    }

    public PayActivity() {
        this.p = f.a();
        this.r = LoginHelper.c();
        this.s = a.a;
        this.w = j.a();
        this.k = new HashSet(32);
    }

    protected final /* synthetic */ BaseFragment c(int i) {
        return d(i);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b.a();
        setContentView(2130968898);
        com.xunlei.downloadprovider.commonview.f fVar = new com.xunlei.downloadprovider.commonview.f((Activity) this);
        findViewById(R.id.titlebar_left).setOnClickListener(this);
        ((TextView) findViewById(R.id.titlebar_title)).setText(getResources().getString(2131231711));
        fVar.k.setVisibility(0);
        fVar.k.setText(getResources().getString(2131230806));
        fVar.k.setTextColor(getResources().getColor(com.xunlei.tdlive.R.color.global_text_color_2));
        fVar.k.setOnClickListener(this);
        this.n = findViewById(2131756711);
        this.n.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.l = (SimpleLoadingPageView) findViewById(2131756713);
        this.l.setTip(getString(2131231867));
        a(true);
        b.a(this.j, LoginHelper.c(), this.w.g(), this.w.d());
        this.x = LocalBroadcastManager.getInstance(getApplicationContext());
        this.y = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.type.activation.pay.success");
        this.x.registerReceiver(this.y, intentFilter);
        if ("download_noti".equals(getIntent().getStringExtra("from"))) {
            com.xunlei.downloadprovider.download.report.a.a("download_in", "in_vip_speedup");
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        a(intent);
    }

    protected void onStart() {
        super.onStart();
        if (!this.r && LoginHelper.c()) {
            int i;
            this.r = LoginHelper.c();
            l();
            BasePayPageFragment basePayPageFragment = (BasePayPageFragment) super.e();
            if (basePayPageFragment != null) {
                i = basePayPageFragment.f;
            } else {
                i = -1;
            }
            h();
            if (this.e.getCount() > 1) {
                BasePayPageFragment d = d(0);
                BasePayPageFragment d2 = d(1);
                if (!(d == null || d2 == null)) {
                    int i2 = d.f;
                    int i3 = d2.f;
                    int abs = Math.abs(i2 - i);
                    i = Math.abs(i3 - i);
                    if (abs > i) {
                        i = 1;
                    } else if (abs < i) {
                        i = 0;
                    } else if (i2 > i3) {
                        i = 0;
                    } else if (i2 < i3) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    this.d.setCurrentItem(i);
                }
            }
            i();
            switch (AnonymousClass_1.a[this.s - 1]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    n();
                    break;
            }
            this.s = a.a;
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected final void a(Intent intent) {
        super.a(intent);
        this.t = this.c.b;
        this.j = this.c.d;
        this.v = this.c.a;
        if (this.v != null) {
            this.u = this.v.toFrom();
            if (this.v.isFromKuaiNiao()) {
                this.w.b = true;
            } else {
                this.w.b = false;
            }
        }
        new StringBuilder("mOperType = ").append(this.t).append(",mReportRefer = ").append(this.j).append(",mPayFrom").append(this.u);
    }

    protected final BasePayPageFragment d(int i) {
        return (BasePayPageFragment) super.c(i);
    }

    private void a(boolean z) {
        this.g = new g();
        a(1);
        if (this.v != null && this.v.isFromKuaiNiao()) {
            this.q = null;
            k();
        } else if (b()) {
            XLPriceParam xLPriceParam = new XLPriceParam();
            xLPriceParam.mUserId = (int) this.w.a.j;
            xLPriceParam.mAccessToken = com.umeng.a.d;
            xLPriceParam.mOrderType = 1;
            xLPriceParam.mVasType = 5;
            this.o = XLPayUtil.getInstance().userGetPrice(xLPriceParam, Integer.valueOf(1));
        } else {
            this.p.a(new aa(this));
        }
        if (z) {
            j();
        }
    }

    private void j() {
        k a = k.a();
        com.xunlei.downloadprovider.member.payment.a.k.a yVar = new y(this);
        if (LoginHelper.c()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://dyact.vip.xunlei.com/cash/cash_list?");
            stringBuilder.append("userid=").append(a.a.a.j).append(com.alipay.sdk.sys.a.b);
            stringBuilder.append("sessionid=").append(a.a.a.k);
            Request tVar = new t(stringBuilder.toString(), new l(a, yVar), new m(a, yVar));
            tVar.setShouldCache(false);
            a.a(tVar);
            return;
        }
        yVar.a(false);
    }

    public final void a(int i, Object obj, int i2, String str) {
        if (this.o == i2) {
            this.p.a(new z(this, ((Integer) obj).intValue(), str));
        } else if (g()) {
            i();
            int count = this.e.getCount();
            for (int i3 = 0; i3 < count; i3++) {
                BasePayPageFragment d = d(i3);
                if (d != null) {
                    d.a(i, i2, str);
                }
            }
        }
    }

    private void k() {
        b(1);
        c();
        this.n.setVisibility(0);
        l();
    }

    private void l() {
        View findViewById = findViewById(2131756712);
        if (LoginHelper.d()) {
            findViewById.setVisibility(XZBDevice.Wait);
        } else {
            findViewById.setVisibility(0);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_left:
                m();
            case com.xunlei.tdlive.R.id.titlebar_right_1:
                this.s = a.d;
                if (LoginHelper.c()) {
                    n();
                } else {
                    a();
                }
            default:
                break;
        }
    }

    public void onBackPressed() {
        m();
    }

    protected final void a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.l.a();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (this.m == null) {
                    this.m = new x(this);
                }
                this.m.show();
            default:
                break;
        }
    }

    protected final void b(int i) {
        while (true) {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (this.l.getVisibility() == 0) {
                        this.l.b();
                        return;
                    }
                    return;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (this.m != null) {
                        this.m.dismiss();
                        return;
                    }
                    return;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    b(1);
                    break;
                default:
                    return;
            }
        }
    }

    protected final List<x> f() {
        PayConfigurationParam payConfigurationParam = null;
        int d;
        PayConfigurationParam defaultMatchParams;
        if (this.q == null) {
            g gVar = this.g;
            PayFrom payFrom = this.v;
            d = gVar.a.d();
            PayConfigurationParam defaultMatchParams2;
            if (payFrom == null || !payFrom.isFromKuaiNiao()) {
                List<x> arrayList = new ArrayList();
                switch (d) {
                    case 204:
                        defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                        defaultMatchParams2 = PayConfigurationParam.getDefaultMatchParams(0, 204, 1);
                        break;
                    default:
                        defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                        defaultMatchParams2 = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_FAILED, 1);
                        break;
                }
                gVar.a(arrayList, defaultMatchParams, 1);
                gVar.a(arrayList, defaultMatchParams2, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                return arrayList;
            }
            if (!gVar.a.c()) {
                d = 0;
            }
            List<x> arrayList2 = new ArrayList();
            switch (d) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(0, 204, 1, Constants.VIA_REPORT_TYPE_SET_AVATAR);
                    defaultMatchParams2 = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1, MessageService.MSG_DB_NOTIFY_REACHED);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(1, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                    defaultMatchParams2 = null;
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                    defaultMatchParams2 = null;
                    break;
                case 204:
                    defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(0, 204, 1, Constants.VIA_REPORT_TYPE_SET_AVATAR);
                    defaultMatchParams2 = PayConfigurationParam.getDefaultMatchParams(1, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                    break;
                default:
                    defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(0, 204, 1, MessageService.MSG_DB_NOTIFY_DISMISS);
                    defaultMatchParams2 = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1, MessageService.MSG_DB_NOTIFY_DISMISS);
                    break;
            }
            gVar.a(arrayList2, defaultMatchParams, 1);
            gVar.a(arrayList2, defaultMatchParams2, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            return arrayList2;
        }
        int vastype;
        PayConfigurationParam payConfigurationParam2;
        g gVar2 = this.g;
        PayConfigurationParam payConfigurationParam3 = this.q;
        PayFrom payFrom2 = this.v;
        int d2 = gVar2.a.d();
        int op = payConfigurationParam3.getOp();
        d = payConfigurationParam3.getVastype();
        if (op == 0 && d == 2) {
            if (payFrom2 == null || !payFrom2.isFromKuaiNiao()) {
                defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
            } else {
                defaultMatchParams = PayConfigurationParam.getDefaultMatchParams(0, 204, 1);
            }
            vastype = defaultMatchParams.getVastype();
            payConfigurationParam2 = defaultMatchParams;
        } else {
            payConfigurationParam2 = payConfigurationParam3;
            vastype = d;
        }
        if (payFrom2 == null || !payFrom2.isFromKuaiNiao()) {
            List<x> arrayList3 = new ArrayList();
            switch (d2) {
                case SpdyAgent.ACCS_TEST_SERVER:
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                case XZBDevice.DOWNLOAD_LIST_ALL:
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    if (vastype == 3) {
                        payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                    } else if (vastype == 5) {
                        payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_FAILED, 1);
                    }
                    break;
                case 204:
                    if (vastype == 204) {
                        payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                    } else if (vastype == 5) {
                        payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(0, 204, 1);
                    }
                    break;
                default:
                    if (vastype == 3) {
                        payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                    } else if (vastype == 5) {
                        payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_FAILED, 1);
                    }
                    break;
            }
            gVar2.a(arrayList3, payConfigurationParam2, 1);
            gVar2.a(arrayList3, payConfigurationParam, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            return arrayList3;
        }
        arrayList3 = new ArrayList();
        switch (d2) {
            case SpdyAgent.ACCS_TEST_SERVER:
                if (vastype == 204) {
                    payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1, MessageService.MSG_DB_NOTIFY_REACHED);
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                if (vastype == 5) {
                }
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (vastype == 5) {
                }
            case 204:
                if (vastype == 204) {
                    payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(1, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                }
                break;
            default:
                if (vastype == 204) {
                    payConfigurationParam = PayConfigurationParam.getDefaultMatchParams(0, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
                }
                break;
        }
        gVar2.a(arrayList3, payConfigurationParam2, 1);
        gVar2.a(arrayList3, payConfigurationParam, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        return arrayList3;
    }

    public void onClickGotoLogin(View view) {
        a();
    }

    private void m() {
        if (g()) {
            BasePayPageFragment basePayPageFragment = (BasePayPageFragment) super.e();
            if (!(basePayPageFragment == null || this.h == null)) {
                b.a(this.i, this.h, LoginHelper.c(), this.w.g(), this.w.d());
            }
            if (!LoginHelper.c() || basePayPageFragment == null) {
                finish();
                return;
            }
            boolean f = basePayPageFragment.f();
            if (!f) {
                for (int i = 0; i < this.e.getCount(); i++) {
                    f = d(i).f();
                    if (f) {
                        break;
                    }
                }
            }
            if (!f) {
                finish();
                return;
            }
            return;
        }
        finish();
    }

    private void n() {
        b.e();
        Intent intent = new Intent(this, ActivationActivity.class);
        intent.putExtra("pay_from", this.j);
        startActivity(intent);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(com.xunlei.tdlive.R.anim.translate_between_interface_left_in, com.xunlei.tdlive.R.anim.translate_between_interface_right_out);
    }

    private static void b(boolean z) {
        h.a().a(z);
        if (z) {
            XLAccelUtil.getAccelerator().updateUserInfo();
        }
    }

    protected void onDestroy() {
        this.x.unregisterReceiver(this.y);
        super.onDestroy();
    }

    private void i() {
        BasePayPageFragment basePayPageFragment = (BasePayPageFragment) super.e();
        if (basePayPageFragment != null) {
            this.h = basePayPageFragment.g;
            this.i = basePayPageFragment.f;
        }
    }

    protected final void a(int i, XLPayParam xLPayParam) {
        BasePayPageFragment basePayPageFragment = (BasePayPageFragment) super.e();
        PaymentSuccessActivity.a(this, a(b(), xLPayParam.mOrderType), xLPayParam.mVasType, xLPayParam.mMonth, this.g.a(), this.c.e);
        Object obj = com.umeng.a.d;
        if (i == 2) {
            obj = Alipay.Name;
        } else if (i == 1) {
            obj = "wechart";
        }
        if (basePayPageFragment != null && !TextUtils.isEmpty(obj)) {
            int i2;
            if (basePayPageFragment.j() != 0) {
                basePayPageFragment.j();
            }
            boolean z = basePayPageFragment instanceof PayUpgradeFragment;
            String str = this.j;
            int i3 = this.i;
            int i4 = basePayPageFragment.f;
            OrderType orderType = basePayPageFragment.g;
            if (z) {
                i2 = -1;
            } else {
                i2 = basePayPageFragment.e;
            }
            b.a(str, i3, i4, orderType, i2, this.w.d(), this.w.g(), obj, basePayPageFragment.d, z ? basePayPageFragment.e : -1, basePayPageFragment.j(), getString(com.xunlei.tdlive.R.string.version), basePayPageFragment.g(), basePayPageFragment.h(), this.h, basePayPageFragment.j(), basePayPageFragment.i());
        }
    }

    public final void a(int i, String str, XLPayParam xLPayParam, int i2) {
        int i3;
        BasePayPageFragment basePayPageFragment = (BasePayPageFragment) super.e();
        if (basePayPageFragment == null || !(basePayPageFragment instanceof PayUpgradeFragment)) {
            Object obj = null;
        } else {
            int i4 = 1;
        }
        int d;
        int g;
        String str2;
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                b(true);
                a(i2, xLPayParam);
                break;
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_buttonStyleSmall:
                b(false);
                if (basePayPageFragment != null) {
                    int i5;
                    int i6 = basePayPageFragment.f;
                    OrderType orderType = basePayPageFragment.g;
                    if (obj != null) {
                        i5 = -1;
                    } else {
                        i5 = basePayPageFragment.e;
                    }
                    d = this.w.d();
                    g = this.w.g();
                    String str3 = Alipay.Name;
                    str2 = this.j;
                    int j = basePayPageFragment.j();
                    if (obj != null) {
                        i4 = basePayPageFragment.e;
                    } else {
                        i4 = -1;
                    }
                    b.a(i6, orderType, i5, d, g, str3, str2, j, i4);
                }
                break;
            default:
                b(false);
                popupOneBtnDialog(str, null);
                if (basePayPageFragment != null) {
                    String str4 = this.j;
                    i3 = basePayPageFragment.f;
                    OrderType orderType2 = basePayPageFragment.g;
                    if (obj != null) {
                        d = -1;
                    } else {
                        d = basePayPageFragment.e;
                    }
                    g = this.w.d();
                    int g2 = this.w.g();
                    str2 = Alipay.Name;
                    if (obj != null) {
                        i4 = basePayPageFragment.e;
                    } else {
                        i4 = -1;
                    }
                    b.a(str4, i3, orderType2, d, g, g2, str2, i, i4, basePayPageFragment.j(), getString(com.xunlei.tdlive.R.string.version));
                }
                break;
        }
        i3 = this.e.getCount();
        for (i6 = 0; i6 < i3; i6++) {
            BasePayPageFragment d2 = d(i6);
            if (d2 != null) {
                d2.l = null;
                d2.a(d2.a());
            }
        }
        j();
    }

    protected final void d() {
        BasePayPageFragment basePayPageFragment = (BasePayPageFragment) super.e();
        if (basePayPageFragment != null) {
            new StringBuilder("onTabChange defaultVasType=").append(this.i).append(", vasType=").append(basePayPageFragment.f);
            b.a(this.i, basePayPageFragment.f, LoginHelper.c(), this.w.g(), this.w.d());
        }
    }

    protected final /* bridge */ /* synthetic */ BaseFragment e() {
        return (BasePayPageFragment) super.e();
    }
}
