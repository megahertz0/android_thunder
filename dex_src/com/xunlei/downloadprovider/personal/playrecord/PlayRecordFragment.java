package com.xunlei.downloadprovider.personal.playrecord;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.member.login.LoginHelper.m;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.personal.playrecord.widget.PlayRecordListWidget;
import com.xunlei.downloadprovider.personal.playrecord.widget.PlayRecordListWidget.AnonymousClass_1;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Set;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

public class PlayRecordFragment extends BaseFragment implements OnClickListener {
    private final a A;
    private final Handler B;
    private boolean C;
    private boolean D;
    private final String a;
    private UnifiedLoadingView b;
    private PlayRecordListWidget c;
    private d d;
    private View e;
    private View f;
    private View g;
    private TextView h;
    private Button i;
    private Button j;
    private TextView k;
    private f l;
    private PlayRecordListState m;
    private boolean n;
    private final Set<String> o;
    private RelativeLayout p;
    private TextView q;
    private TextView r;
    private TextView s;
    private ImageView t;
    private m u;
    private final LoginHelper.d v;
    private final g w;
    private final a x;
    private final b y;
    private final p z;

    public PlayRecordFragment() {
        this.a = PlayRecordFragment.class.getSimpleName();
        this.b = null;
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
        this.m = PlayRecordListState.uninit;
        this.n = false;
        this.o = new HashSet();
        this.u = new a(this);
        this.v = new f(this);
        this.w = new g(this);
        this.x = new h(this);
        this.y = new i(this);
        this.z = new j(this);
        this.A = new l(this);
        this.B = new b(this.A);
        this.D = true;
        LoginHelper.a().a(this.u);
        LoginHelper.a().a(this.w);
        LoginHelper.a().a(this.v);
    }

    public void onResume() {
        super.onResume();
        this.C = true;
        LoginHelper.a().x();
        b();
        if (PayUtil.b) {
            LoginHelper.a().a(this.z);
            this.b.a();
            LoginHelper.a().s();
            PayUtil.b = false;
        }
        if (this.g.getAnimation() != null) {
            this.g.getAnimation().cancel();
            this.g.clearAnimation();
        }
        this.g.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        a();
        c();
    }

    public void onPause() {
        PayUtil.b = false;
        super.onPause();
    }

    public void onDestroy() {
        this.m = PlayRecordListState.destroy;
        LoginHelper.a().b(this.w);
        LoginHelper.a().b(this.v);
        LoginHelper.a().b(this.z);
        LoginHelper.a().b(this.u);
        super.onDestroy();
    }

    public boolean onBackPressed() {
        if (this.e.getVisibility() != 0 && this.f.getVisibility() != 0) {
            return super.onBackPressed();
        }
        this.D = true;
        d();
        return true;
    }

    @SuppressLint({"InflateParams"})
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mPageRoot = (ViewGroup) LayoutInflater.from(this.mActivity).inflate(2130968914, null);
        this.b = (UnifiedLoadingView) this.mPageRoot.findViewById(2131755563);
        this.c = (PlayRecordListWidget) this.mPageRoot.findViewById(2131756757);
        this.c.setBackgroundColor(getActivity().getResources().getColor(2131689504));
        this.e = this.mPageRoot.findViewById(2131755649);
        this.f = this.mPageRoot.findViewById(2131756428);
        this.g = this.mPageRoot.findViewById(2131756430);
        this.h = (TextView) this.mPageRoot.findViewById(2131756431);
        this.i = (Button) this.mPageRoot.findViewById(2131755653);
        this.i.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 15.0f);
        this.j = (Button) this.mPageRoot.findViewById(2131755651);
        this.j.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 15.0f);
        this.k = (TextView) this.mPageRoot.findViewById(2131755652);
        this.k.setText(2131231060);
        this.s = (TextView) this.mPageRoot.findViewById(R.id.common_delete_buttom_btn_text);
        this.t = (ImageView) this.mPageRoot.findViewById(R.id.common_delete_buttom_btn_icon);
        this.c.setOnRefreshClickListener(this);
        this.l = new f(this.mPageRoot);
        this.l.g.setOnClickListener(this);
        this.l.n.setOnClickListener(this);
        this.l.n.setVisibility(0);
        this.l.n.setImageResource(R.drawable.common_menu_delete_icon_black_selector);
        this.l.i.setText(2131231077);
        this.l.a();
        this.b.setOnClickListener(this);
        this.c.setOnListOperateCallback(this.y);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.p = (RelativeLayout) findViewById(2131755849);
        this.q = (TextView) findViewById(2131755851);
        this.r = (TextView) findViewById(2131755853);
        this.r.setOnClickListener(this);
        return this.mPageRoot;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755651:
                d();
            case 2131755653:
                f();
            case com.xunlei.xiazaibao.R.id.titlebar_left:
                this.mActivity.finish();
            case R.id.titlebar_right_iv:
                if (!this.n) {
                    f();
                }
            case 2131755853:
                LoginHelper.a();
                if (LoginHelper.c()) {
                    Object obj = com.umeng.a.d;
                    if (com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_DB_NOTIFY_DISMISS)) {
                        obj = ((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_DB_NOTIFY_DISMISS)).h;
                    }
                    if (TextUtils.isEmpty(r0)) {
                        if (!(TextUtils.isEmpty(this.r.getText()) || this.r.getText().equals("\u5f00\u901a\u4f1a\u5458"))) {
                            StatReporter.reportVip_ContinueClick("space_play_top");
                        }
                        obj = null;
                    } else {
                        BrowserUtil.a();
                        BrowserUtil.a(getActivity(), r0, "\u7eed\u8d39");
                        obj = 1;
                    }
                    if (obj == null) {
                        PayFrom payFrom = PayFrom.PLAY_LIST;
                        if (!this.r.getText().toString().contains("\u5f00\u901a")) {
                            payFrom = PayFrom.PLAY_LIST_RENEWTIP;
                        }
                        PayEntryParam payEntryParam = new PayEntryParam(payFrom);
                        payEntryParam.c = com.xunlei.downloadprovider.homepage.a.a.d.a;
                        PaymentEntryActivity.a(getActivity(), payEntryParam);
                        return;
                    }
                    return;
                }
                LoginHelper.a().a(getActivity(), new m(this), (int) XZBDevice.Wait, getResouceString(2131231077));
            case 2131756428:
                e();
            case 2131756757:
                b();
            default:
                break;
        }
    }

    public final void a() {
        LoginHelper.a();
        if (!LoginHelper.c()) {
            if (this.c.getListCount() != 0 && com.xunlei.c.a.b.a(BrothersApplication.a())) {
                this.p.setVisibility(0);
                this.r.setText(2131231048);
                this.q.setText(2131231094);
            }
            this.p.setVisibility(XZBDevice.Wait);
        } else if (LoginHelper.a().f()) {
            if (com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_DB_NOTIFY_DISMISS)) {
                if (this.c.getListCount() != 0) {
                    this.p.setVisibility(0);
                    this.q.setText(((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_DB_NOTIFY_DISMISS)).d);
                    this.r.setText(((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_DB_NOTIFY_DISMISS)).e);
                }
                this.p.setVisibility(XZBDevice.Wait);
            } else {
                this.p.setVisibility(XZBDevice.Wait);
            }
        } else if (com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_DB_NOTIFY_DISMISS)) {
            if (this.c.getListCount() != 0) {
                this.p.setVisibility(0);
                this.q.setText(((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_DB_NOTIFY_DISMISS)).d);
                this.r.setText(((com.xunlei.downloadprovider.homepage.a.a.d) com.xunlei.downloadprovider.homepage.a.a.d.b.get(MessageService.MSG_DB_NOTIFY_DISMISS)).e);
            }
            this.p.setVisibility(XZBDevice.Wait);
        } else {
            if (this.c.getListCount() != 0) {
                this.p.setVisibility(0);
                this.q.setText(2131231095);
                this.r.setText(2131231050);
            }
            this.p.setVisibility(XZBDevice.Wait);
        }
        this.c.c();
    }

    private final void b() {
        this.c.c();
        this.c.a(this.x);
        if (this.m == PlayRecordListState.uninit) {
            this.m = PlayRecordListState.initing;
            this.b.a();
        }
    }

    private final void c() {
        LoginHelper.a();
        if (!LoginHelper.c()) {
            return;
        }
        if (LoginHelper.a().f()) {
            if (!com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_DB_NOTIFY_DISMISS)) {
                return;
            }
            if (this.c.getListCount() == 0) {
                LoginHelper.a();
                StatReporter.reportVip_ContinueShow("space_play_middle", Boolean.valueOf(LoginHelper.c()), Boolean.valueOf(LoginHelper.a().f()), com.xunlei.downloadprovider.homepage.a.a.d.a);
                return;
            }
            LoginHelper.a();
            StatReporter.reportVip_ContinueShow("space_play_top", Boolean.valueOf(LoginHelper.c()), Boolean.valueOf(LoginHelper.a().f()), com.xunlei.downloadprovider.homepage.a.a.d.a);
        } else if (!com.xunlei.downloadprovider.homepage.a.a.d.b.containsKey(MessageService.MSG_DB_NOTIFY_DISMISS)) {
        } else {
            if (this.c.getListCount() == 0) {
                LoginHelper.a();
                StatReporter.reportVip_ContinueShow("space_play_middle", Boolean.valueOf(LoginHelper.c()), Boolean.valueOf(LoginHelper.a().f()), com.xunlei.downloadprovider.homepage.a.a.d.a);
                return;
            }
            LoginHelper.a();
            StatReporter.reportVip_ContinueShow("space_play_top", Boolean.valueOf(LoginHelper.c()), Boolean.valueOf(LoginHelper.a().f()), com.xunlei.downloadprovider.homepage.a.a.d.a);
        }
    }

    private final void a(boolean z) {
        this.n = z;
        if (this.n) {
            this.c.setListViewMode(Mode.DISABLED);
            this.e.setVisibility(0);
            this.f.setVisibility(0);
            this.f.setOnClickListener(this);
            g();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), 2131034207);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), 2131034136);
        loadAnimation.setAnimationListener(new n(this));
        this.e.startAnimation(loadAnimation);
        this.f.startAnimation(loadAnimation2);
        this.e.setVisibility(XZBDevice.Wait);
        this.f.setVisibility(XZBDevice.Wait);
    }

    private final void d() {
        if (this.c.e) {
            this.D = true;
            this.c.e();
        }
        this.k.setText(null);
        a(false);
    }

    private final void e() {
        if (this.c.getDeleteTaskCount() <= 0) {
            XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, getActivity().getString(2131231286));
        } else if (this.d == null || !(this.d == null || this.d.isShowing())) {
            this.d = new d(getActivity());
            this.d.a(String.format(BrothersApplication.a().getString(2131231063), new Object[]{Integer.valueOf(r0)}));
            this.d.d(BrothersApplication.a().getString(2131231062));
            this.d.c(BrothersApplication.a().getString(2131231061));
            this.d.b(new c(this));
            this.d.a(new d(this));
            this.d.setOnDismissListener(new e(this));
            this.d.show();
        }
    }

    private final void f() {
        if (!this.c.f()) {
            if (this.c.getListCount() > 0) {
                PlayRecordListWidget playRecordListWidget = this.c;
                boolean z = this.D;
                playRecordListWidget.h = true;
                if (z) {
                    playRecordListWidget.e();
                    if (playRecordListWidget.g != null) {
                        playRecordListWidget.g.a(playRecordListWidget.e);
                    }
                    playRecordListWidget.b();
                    playRecordListWidget.d.clear();
                } else if (playRecordListWidget.b.size() + playRecordListWidget.d.size() >= playRecordListWidget.a.size() || playRecordListWidget.c.size() + playRecordListWidget.d.size() >= playRecordListWidget.a.size()) {
                    playRecordListWidget.b();
                    playRecordListWidget.d.clear();
                } else {
                    for (CommixturePlayRecord commixturePlayRecord : playRecordListWidget.a) {
                        switch (AnonymousClass_1.a[commixturePlayRecord.c.ordinal()]) {
                            case SpdyAgent.ACCS_ONLINE_SERVER:
                                playRecordListWidget.a(commixturePlayRecord.a.src_url, commixturePlayRecord.a.url_hash);
                                break;
                            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                playRecordListWidget.d.add(String.valueOf(commixturePlayRecord.b.a));
                                break;
                            default:
                                break;
                        }
                    }
                }
                playRecordListWidget.a();
                this.D = false;
            }
            g();
        }
    }

    private final void g() {
        int deleteTaskCount = this.c.getDeleteTaskCount();
        int listCount = this.c.getListCount();
        if (deleteTaskCount > 0) {
            if (deleteTaskCount < listCount) {
                this.i.setText(2131230862);
            } else {
                this.i.setText(2131230861);
            }
            this.k.setText(String.format(BrothersApplication.a().getString(2131231368), new Object[]{Integer.valueOf(deleteTaskCount)}));
            this.f.setEnabled(true);
            b(true);
            return;
        }
        this.k.setText(2131231060);
        this.i.setText(2131230862);
        this.f.setEnabled(false);
        b(false);
    }

    private void b(boolean z) {
        if (z) {
            this.s.setEnabled(true);
            this.t.setEnabled(true);
            return;
        }
        this.s.setEnabled(false);
        this.t.setEnabled(false);
    }

    static /* synthetic */ void a(PlayRecordFragment playRecordFragment, CharSequence charSequence) {
        if (playRecordFragment.m != PlayRecordListState.destroy) {
            playRecordFragment.h.setText(charSequence);
            Animation loadAnimation = AnimationUtils.loadAnimation(playRecordFragment.getApplicationContext(), 2131034202);
            loadAnimation.setAnimationListener(new b(playRecordFragment));
            playRecordFragment.g.startAnimation(loadAnimation);
        }
    }
}
