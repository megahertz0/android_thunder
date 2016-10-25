package com.xunlei.downloadprovider.personal.lixianspace;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.q;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.member.login.LoginHelper.m;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Set;
import org.android.agoo.message.MessageService;

public class LixianSpaceFragment extends BaseFragment {
    private final g A;
    private final d B;
    private final c C;
    private boolean D;
    private final d E;
    private final p F;
    private final a G;
    private final Handler H;
    private boolean I;
    private boolean J;
    private m a;
    private final String b;
    private UnifiedLoadingView c;
    private LixianSpaceListWidget d;
    private com.xunlei.downloadprovider.commonview.dialog.d e;
    private View f;
    private View g;
    private View h;
    private TextView i;
    private Button j;
    private Button k;
    private TextView l;
    private f m;
    private q n;
    private int o;
    private boolean p;
    private final Set<String> q;
    private PopupWindow r;
    private TextView s;
    private View t;
    private RelativeLayout u;
    private TextView v;
    private TextView w;
    private TextView x;
    private ImageView y;
    private final OnClickListener z;

    public LixianSpaceFragment() {
        this.b = LixianSpaceFragment.class.getSimpleName();
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = b.a;
        this.p = false;
        this.q = new HashSet();
        this.r = null;
        this.s = null;
        this.t = null;
        this.z = new a(this);
        this.A = new h(this);
        this.B = new i(this);
        this.C = new j(this);
        this.E = new k(this);
        this.F = new l(this);
        this.G = new n(this);
        this.H = new b(this.G);
        this.J = true;
    }

    private boolean b() {
        if (this.r == null || !this.r.isShowing()) {
            return false;
        }
        if (!(getActivity() == null || getActivity().isFinishing())) {
            try {
                LayoutParams attributes = getActivity().getWindow().getAttributes();
                attributes.alpha = 1.0f;
                getActivity().getWindow().setAttributes(attributes);
            } catch (Exception e) {
            }
            try {
                this.r.dismiss();
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            }
        }
        return true;
    }

    @SuppressLint({"InflateParams"})
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = new o(this);
        LoginHelper.a().a(this.a);
        this.mPageRoot = (ViewGroup) LayoutInflater.from(this.mActivity).inflate(2130968857, null);
        this.c = (UnifiedLoadingView) this.mPageRoot.findViewById(2131755563);
        this.d = (LixianSpaceListWidget) this.mPageRoot.findViewById(2131756427);
        this.f = this.mPageRoot.findViewById(2131755649);
        View findViewById = this.f.findViewById(2131755654);
        if (findViewById != null) {
            findViewById.setVisibility(XZBDevice.Wait);
        }
        this.g = this.mPageRoot.findViewById(2131756428);
        this.h = this.mPageRoot.findViewById(2131756430);
        this.i = (TextView) this.mPageRoot.findViewById(2131756431);
        this.j = (Button) this.mPageRoot.findViewById(2131755653);
        this.k = (Button) this.mPageRoot.findViewById(2131755651);
        this.l = (TextView) this.mPageRoot.findViewById(2131755652);
        this.l.setText(2131231060);
        this.x = (TextView) this.mPageRoot.findViewById(R.id.common_delete_buttom_btn_text);
        this.y = (ImageView) this.mPageRoot.findViewById(R.id.common_delete_buttom_btn_icon);
        this.d.setOnRefreshClickListener(new p(this));
        this.m = new f(this.mPageRoot);
        this.m.g.setOnClickListener(this.z);
        this.m.n.setOnClickListener(this.z);
        this.m.n.setVisibility(0);
        this.m.n.setImageResource(R.drawable.common_menu_delete_icon_black_selector);
        this.m.i.setText(2131231075);
        this.m.a();
        this.c.setOnClickListener(this.z);
        this.d.setOnCloudListOperateListener(this.E);
        this.j.setOnClickListener(this.z);
        this.k.setOnClickListener(this.z);
        this.u = (RelativeLayout) findViewById(2131755849);
        this.v = (TextView) findViewById(2131755851);
        this.w = (TextView) findViewById(2131755853);
        this.w.setOnClickListener(this.z);
        this.o = b.a;
        LoginHelper.a().a(this.A);
        LoginHelper.a().a(this.B);
        return this.mPageRoot;
    }

    public void onResume() {
        LoginHelper.a().x();
        this.I = true;
        c();
        if (PayUtil.b) {
            LoginHelper.a().a(this.F);
            this.c.a();
            LoginHelper.a().s();
            PayUtil.b = false;
        }
        super.onResume();
        if (this.h.getAnimation() != null) {
            this.h.getAnimation().cancel();
            this.h.clearAnimation();
        }
        this.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        a();
        d();
    }

    public void onPause() {
        PayUtil.b = false;
        super.onPause();
    }

    public void onDestroy() {
        this.o = b.d;
        LoginHelper.a().b(this.A);
        LoginHelper.a().b(this.B);
        LoginHelper.a().b(this.F);
        if (this.d != null) {
            this.d.b();
        }
        LoginHelper.a().b(this.a);
        super.onDestroy();
    }

    public boolean onBackPressed() {
        if (this.o == b.c && this.d.d) {
            return super.onBackPressed();
        }
        if (this.f.getVisibility() != 0 && this.g.getVisibility() != 0) {
            return super.onBackPressed();
        }
        this.J = true;
        e();
        return true;
    }

    public final void a() {
        LoginHelper.a();
        if (!LoginHelper.c()) {
            if (this.d.getListCount() != 0 && com.xunlei.c.a.b.a(BrothersApplication.a())) {
                this.u.setVisibility(0);
                this.w.setText(2131231048);
                this.v.setText(2131231094);
            }
            this.u.setVisibility(XZBDevice.Wait);
        } else if (LoginHelper.a().f()) {
            if (com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_ACCS_READY_REPORT)) {
                if (this.d.getListCount() != 0) {
                    this.u.setVisibility(0);
                    this.v.setText(((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_ACCS_READY_REPORT)).d);
                    this.w.setText(((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_ACCS_READY_REPORT)).e);
                }
                this.u.setVisibility(XZBDevice.Wait);
            } else {
                this.u.setVisibility(XZBDevice.Wait);
            }
        } else if (com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_ACCS_READY_REPORT)) {
            if (this.d.getListCount() != 0) {
                this.u.setVisibility(0);
                this.v.setText(((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_ACCS_READY_REPORT)).d);
                this.w.setText(((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_ACCS_READY_REPORT)).e);
            }
            this.u.setVisibility(XZBDevice.Wait);
        } else {
            if (this.d.getListCount() != 0) {
                this.u.setVisibility(0);
                this.v.setText(2131231095);
                this.w.setText(2131231050);
            }
            this.u.setVisibility(XZBDevice.Wait);
        }
        this.d.c();
    }

    private final void c() {
        this.d.c();
        this.d.a(this.C);
        if (this.o == b.a) {
            this.o = b.b;
            this.c.a();
        }
    }

    private final void d() {
        a();
        LoginHelper.a();
        if (!LoginHelper.c()) {
            return;
        }
        if (LoginHelper.a().f()) {
            if (!com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_ACCS_READY_REPORT)) {
                return;
            }
            if (this.d.getListCount() == 0) {
                LoginHelper.a();
                StatReporter.reportVip_ContinueShow("space_lixian_middle", Boolean.valueOf(LoginHelper.c()), Boolean.valueOf(LoginHelper.a().f()), com.xunlei.downloadprovider.homepage.a.a.d.a);
                return;
            }
            LoginHelper.a();
            StatReporter.reportVip_ContinueShow("space_lixian_top", Boolean.valueOf(LoginHelper.c()), Boolean.valueOf(LoginHelper.a().f()), com.xunlei.downloadprovider.homepage.a.a.d.a);
        } else if (!com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_ACCS_READY_REPORT)) {
        } else {
            if (this.d.getListCount() == 0) {
                LoginHelper.a();
                StatReporter.reportVip_ContinueShow("space_lixian_middle", Boolean.valueOf(LoginHelper.c()), Boolean.valueOf(LoginHelper.a().f()), com.xunlei.downloadprovider.homepage.a.a.d.a);
                return;
            }
            LoginHelper.a();
            StatReporter.reportVip_ContinueShow("space_lixian_top", Boolean.valueOf(LoginHelper.c()), Boolean.valueOf(LoginHelper.a().f()), com.xunlei.downloadprovider.homepage.a.a.d.a);
        }
    }

    private final void a(boolean z) {
        this.p = z;
        if (this.p) {
            this.d.setListViewMode(Mode.DISABLED);
            this.f.setVisibility(0);
            this.g.setVisibility(0);
            this.g.setOnClickListener(this.z);
            g();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), 2131034207);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), 2131034136);
        loadAnimation.setAnimationListener(new c(this));
        this.f.startAnimation(loadAnimation);
        this.g.startAnimation(loadAnimation2);
        this.f.setVisibility(XZBDevice.Wait);
        this.g.setVisibility(XZBDevice.Wait);
    }

    private final void e() {
        if (this.d.e) {
            this.J = true;
            this.d.d();
        }
        this.l.setText(null);
        a(false);
    }

    private final void f() {
        if (!this.d.e()) {
            if (this.d.getListCount() > 0) {
                LixianSpaceListWidget lixianSpaceListWidget = this.d;
                if (this.J) {
                    lixianSpaceListWidget.d();
                    if (lixianSpaceListWidget.h != null) {
                        lixianSpaceListWidget.h.a(lixianSpaceListWidget.e);
                    }
                    lixianSpaceListWidget.b.clear();
                } else if (lixianSpaceListWidget.b.size() < lixianSpaceListWidget.a.size()) {
                    lixianSpaceListWidget.b.clear();
                    for (XLLixianTask xLLixianTask : lixianSpaceListWidget.a) {
                        lixianSpaceListWidget.b.add(Long.valueOf(xLLixianTask.getTaskId()));
                    }
                } else {
                    lixianSpaceListWidget.b.clear();
                }
                lixianSpaceListWidget.a();
                this.J = false;
            }
            g();
        }
    }

    private final void g() {
        int deleteTaskCount = this.d.getDeleteTaskCount();
        int listCount = this.d.getListCount();
        if (deleteTaskCount > 0) {
            if (deleteTaskCount < listCount) {
                this.j.setText(2131230862);
            } else {
                this.j.setText(2131230861);
            }
            this.l.setText(String.format(BrothersApplication.a().getString(2131231368), new Object[]{Integer.valueOf(deleteTaskCount)}));
            this.g.setEnabled(true);
            b(true);
            return;
        }
        this.l.setText(2131231060);
        this.j.setText(2131230862);
        this.g.setEnabled(false);
        b(false);
    }

    private void b(boolean z) {
        if (z) {
            this.x.setEnabled(true);
            this.y.setEnabled(true);
            return;
        }
        this.x.setEnabled(false);
        this.y.setEnabled(false);
    }

    static /* synthetic */ void c(LixianSpaceFragment lixianSpaceFragment) {
        lixianSpaceFragment.f();
        lixianSpaceFragment.b();
    }

    static /* synthetic */ void d(LixianSpaceFragment lixianSpaceFragment) {
        if (lixianSpaceFragment.d.getDeleteTaskCount() <= 0) {
            XLToast.a(lixianSpaceFragment.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, lixianSpaceFragment.getActivity().getString(2131231286));
        } else if (lixianSpaceFragment.e == null || !(lixianSpaceFragment.e == null || lixianSpaceFragment.e.isShowing())) {
            lixianSpaceFragment.e = new com.xunlei.downloadprovider.commonview.dialog.d(lixianSpaceFragment.getActivity());
            lixianSpaceFragment.e.a(String.format(BrothersApplication.a().getString(2131231063), new Object[]{Integer.valueOf(r0)}));
            lixianSpaceFragment.e.d(BrothersApplication.a().getString(2131231062));
            lixianSpaceFragment.e.c(BrothersApplication.a().getString(2131231061));
            lixianSpaceFragment.e.b(new e(lixianSpaceFragment));
            lixianSpaceFragment.e.a(new f(lixianSpaceFragment));
            lixianSpaceFragment.e.setOnDismissListener(new g(lixianSpaceFragment));
            lixianSpaceFragment.e.show();
        }
    }

    static /* synthetic */ void g(LixianSpaceFragment lixianSpaceFragment) {
        Object obj = com.umeng.a.d;
        if (com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_DB_NOTIFY_DISMISS)) {
            obj = ((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_DB_NOTIFY_DISMISS)).h;
        }
        if (TextUtils.isEmpty(r0)) {
            if (!(TextUtils.isEmpty(lixianSpaceFragment.w.getText()) || lixianSpaceFragment.w.getText().equals("\u5f00\u901a\u4f1a\u5458"))) {
                StatReporter.reportVip_ContinueClick("space_lixian_top");
            }
            obj = null;
        } else {
            BrowserUtil.a();
            BrowserUtil.a(lixianSpaceFragment.getActivity(), r0, "\u7eed\u8d39");
            obj = 1;
        }
        if (obj == null) {
            PayFrom payFrom = PayFrom.LIXIAN_SPACE;
            if (!lixianSpaceFragment.w.getText().toString().contains("\u5f00\u901a")) {
                payFrom = PayFrom.LIXIAN_SPACE_RENEWTIP;
            }
            PayEntryParam payEntryParam = new PayEntryParam(payFrom);
            payEntryParam.c = com.xunlei.downloadprovider.homepage.a.a.d.a;
            PaymentEntryActivity.a(lixianSpaceFragment.getActivity(), payEntryParam);
        }
    }

    static /* synthetic */ void a(LixianSpaceFragment lixianSpaceFragment, CharSequence charSequence) {
        if (lixianSpaceFragment.o != b.d) {
            lixianSpaceFragment.i.setText(charSequence);
            Animation loadAnimation = AnimationUtils.loadAnimation(lixianSpaceFragment.getApplicationContext(), 2131034202);
            loadAnimation.setAnimationListener(new d(lixianSpaceFragment));
            lixianSpaceFragment.h.startAnimation(loadAnimation);
        }
    }
}
