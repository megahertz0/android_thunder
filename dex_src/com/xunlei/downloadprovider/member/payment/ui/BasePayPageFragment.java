package com.xunlei.downloadprovider.member.payment.ui;

import android.os.Bundle;
import android.support.v4.widget.AutoScrollHelper;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.a.k;
import com.xunlei.downloadprovider.member.payment.bean.PayConfigurationParam;
import com.xunlei.downloadprovider.member.payment.bean.VoucherDataBean;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType;
import com.xunlei.downloadprovider.member.payment.ui.widget.PaymentTypeView;
import com.xunlei.downloadprovider.member.payment.ui.widget.b;
import com.xunlei.downloadprovider.member.payment.ui.widget.b$c;
import com.xunlei.downloadprovider.member.payment.ui.widget.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Map.Entry;

public abstract class BasePayPageFragment extends BaseFragment {
    View a;
    View b;
    View c;
    float d;
    int e;
    int f;
    OrderType g;
    PayConfigurationParam h;
    public PaymentTypeView i;
    public int j;
    protected View k;
    b$c l;
    protected j m;
    View n;
    private TextView o;
    private OrderType p;
    private boolean q;
    private TextView r;
    private b s;
    private TextView t;
    private TextView u;
    private VoucherDataBean v;
    private View w;
    private float x;

    protected abstract void a(int i, int i2, String str);

    protected abstract void b(boolean z);

    protected abstract boolean b();

    protected abstract void e();

    protected abstract int g();

    protected abstract int h();

    protected abstract int i();

    public BasePayPageFragment() {
        this.f = -1;
        this.j = 1;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f = arguments.getInt("VasType");
            this.p = (OrderType) arguments.getSerializable("OrderType");
            this.g = (OrderType) arguments.getSerializable("RealOrderType");
            this.q = arguments.getBoolean("ExpiredToday");
            this.h = (PayConfigurationParam) arguments.getSerializable("extra_pay_config");
        }
        this.m = j.a();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.t = (TextView) view.findViewById(2131756723);
        CharSequence charSequence = null;
        switch (this.f) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                charSequence = getResouceString(2131231878);
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                charSequence = getResouceString(2131231898);
                break;
            case 204:
                charSequence = getResouceString(2131231866);
                break;
            default:
                charSequence = getResouceString(2131231898);
                break;
        }
        this.t.setText(charSequence);
        this.b = view.findViewById(2131756727);
        this.a = view.findViewById(2131756714);
        this.a.findViewById(2131756715).setOnClickListener(new l(this));
        this.c = view.findViewById(2131756724);
        this.w = view.findViewById(2131756135);
        this.w.setVisibility(XZBDevice.Wait);
        this.o = (TextView) view.findViewById(2131756136);
        this.u = (TextView) view.findViewById(2131756137);
        TextView textView = (TextView) view.findViewById(2131756153);
        textView.setText(new StringBuilder("\u7acb\u5373").append(this.g.getText()).toString());
        textView.setOnClickListener(new r(this));
        view.findViewById(2131756154).setOnClickListener(new m(this));
        view.findViewById(2131756155).setOnClickListener(new n(this));
        this.i = (PaymentTypeView) view.findViewById(2131756143);
        this.i.setOnPaymentTypeSelectListener(new o(this));
        this.i.setDefaultSelectType(this.j);
        this.r = (TextView) view.findViewById(2131756142);
        this.k = view.findViewById(2131756141);
        this.k.setOnClickListener(new p(this));
        this.n = view.findViewById(2131756728);
        this.n.setVisibility(XZBDevice.Wait);
    }

    protected void a(int i) {
    }

    protected final void a(int i, int i2) {
        new StringBuilder("amount=").append(i).append(", op=").append(i2);
        this.l = null;
        this.v = k.a().a(i, i2);
        if (this.v == null || this.v.getVoucherList() == null || this.v.getVoucherList().isEmpty()) {
            this.r.setText(null);
            this.k.setVisibility(XZBDevice.Wait);
            return;
        }
        new StringBuilder("changVoucherLayoutState voucher size=").append(this.v.getVoucherList().size()).append(", default price =").append(this.v.getDefaultVoucherNum()).append(", payamount=").append(i);
        a(a());
        this.k.setVisibility(0);
    }

    final boolean a() {
        boolean z;
        if (this.v != null && this.v.getVoucherList() != null) {
            z = false;
            for (String str : this.v.getVoucherList().values()) {
                z = ((PayActivity) getActivity()).k.contains(str);
                if (!z) {
                    break;
                }
            }
        }
        z = true;
        return !z;
    }

    final void a(boolean z) {
        if (!z) {
            this.r.setTextColor(getResources().getColor(2131689736));
            this.r.setText(getString(2131231910));
        } else if (this.l != null) {
            this.r.setText(Html.fromHtml(getString(2131231906, new Object[]{Integer.valueOf(this.l.a)})));
        } else {
            this.r.setTextColor(getResources().getColor(2131689736));
            this.r.setText(getString(2131231909));
        }
    }

    protected final void a(float f) {
        float j = ((float) j()) + f;
        this.x = j;
        if (j <= 0.0f) {
            this.u.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            return;
        }
        this.u.setVisibility(0);
        this.u.setText(Html.fromHtml(getResouceString(2131231888, new Object[]{PayUtil.a(j)})));
    }

    public final void c() {
        this.a.setVisibility(0);
        this.b.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.c.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.n.setVisibility(XZBDevice.Wait);
    }

    public final void d() {
        this.a.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.b.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.c.setVisibility(0);
        this.n.setVisibility(0);
    }

    protected final void a(float f, float f2) {
        b(f);
        a(f2);
    }

    protected final void a(float f, double d) {
        if (d >= 1.0d) {
            a(f, (float) AutoScrollHelper.RELATIVE_UNSPECIFIED);
        } else {
            b(f);
        }
    }

    private void b(float f) {
        float j = f - ((float) j());
        this.w.setVisibility(j > 0.0f ? 0 : XZBDevice.Wait);
        this.d = j;
        this.o.setText(Html.fromHtml(getResouceString(2131231840, new Object[]{PayUtil.a(this.d)})));
    }

    protected boolean f() {
        return false;
    }

    protected final void a(String str) {
        XLAlarmDialog xLAlarmDialog = new XLAlarmDialog(getActivity());
        xLAlarmDialog.setContent(str);
        xLAlarmDialog.setLeftBtnStr(getResouceString(2131231858));
        xLAlarmDialog.setRightBtnStr(getResouceString(2131231859));
        xLAlarmDialog.setRightBtnListener(new s(this));
        xLAlarmDialog.setLeftBtnListener(new t(this));
        xLAlarmDialog.show();
        com.xunlei.downloadprovider.member.payment.b.b();
    }

    public final int j() {
        return this.l != null ? this.l.a : 0;
    }

    static /* synthetic */ void a(BasePayPageFragment basePayPageFragment) {
        if (basePayPageFragment.s == null) {
            basePayPageFragment.s = new b(basePayPageFragment.getActivity());
            basePayPageFragment.s.c = new q(basePayPageFragment);
        }
        b bVar = basePayPageFragment.s;
        VoucherDataBean voucherDataBean = basePayPageFragment.v;
        HashSet hashSet = ((PayActivity) basePayPageFragment.getActivity()).k;
        if (!(voucherDataBean == null || voucherDataBean.getVoucherList() == null)) {
            bVar.d.clear();
            bVar.e = hashSet;
            for (Entry entry : voucherDataBean.getVoucherList().entrySet()) {
                b$c com_xunlei_downloadprovider_member_payment_ui_widget_b_c = new b$c();
                com_xunlei_downloadprovider_member_payment_ui_widget_b_c.a = ((Integer) entry.getKey()).intValue();
                com_xunlei_downloadprovider_member_payment_ui_widget_b_c.b = (String) entry.getValue();
                com_xunlei_downloadprovider_member_payment_ui_widget_b_c.c = hashSet.contains(com_xunlei_downloadprovider_member_payment_ui_widget_b_c.b) ? XZBDevice.DOWNLOAD_LIST_FAILED : 0;
                bVar.d.add(com_xunlei_downloadprovider_member_payment_ui_widget_b_c);
            }
            if (bVar.b != null) {
                bVar.b.a(bVar.d);
            }
        }
        if (!basePayPageFragment.s.isShowing()) {
            basePayPageFragment.s.show();
        }
        b bVar2 = basePayPageFragment.s;
        if (bVar2.a != null) {
            bVar2.a.post(new c(bVar2));
        }
    }

    static /* synthetic */ void d(BasePayPageFragment basePayPageFragment) {
        String str;
        if (basePayPageFragment.l != null) {
            str = basePayPageFragment.l.b;
        } else {
            str = null;
        }
        boolean c = LoginHelper.c();
        if (1 == basePayPageFragment.j) {
            ((PayActivity) basePayPageFragment.getActivity()).b(((PayActivity) basePayPageFragment.getActivity()).j, basePayPageFragment.e, basePayPageFragment.g.toXLSdkOrderType(), basePayPageFragment.f, str, basePayPageFragment.i());
            com.xunlei.downloadprovider.member.payment.b.a(((PayActivity) basePayPageFragment.getActivity()).j, c, basePayPageFragment.m.d(), basePayPageFragment.m.g(), Alipay.Name, basePayPageFragment.d);
        } else if (2 == basePayPageFragment.j) {
            ((PayActivity) basePayPageFragment.getActivity()).a(((PayActivity) basePayPageFragment.getActivity()).j, basePayPageFragment.e, basePayPageFragment.g.toXLSdkOrderType(), basePayPageFragment.f, str, basePayPageFragment.i());
            com.xunlei.downloadprovider.member.payment.b.a(((PayActivity) basePayPageFragment.getActivity()).j, c, basePayPageFragment.m.d(), basePayPageFragment.m.g(), "wechart", basePayPageFragment.d);
        }
    }
}
