package com.xunlei.downloadprovider.member.payment.ui;

import android.os.Bundle;
import android.support.v4.widget.AutoScrollHelper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.umeng.a;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.common.pay.param.XLPriceParam;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.b;
import com.xunlei.downloadprovider.member.payment.bean.PayConfigurationParam;
import com.xunlei.downloadprovider.member.payment.bean.UpgradePriceParam;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.ui.widget.RangSeekBar;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.math.BigDecimal;

public class PayUpgradeFragment extends BasePayPageFragment {
    private int o;
    private TextView p;
    private TextView q;
    private TextView r;
    private UpgradePriceParam s;
    private RangSeekBar t;
    private TextView u;
    private TextView v;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130968904, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.p = (TextView) view.findViewById(2131756733);
        this.q = (TextView) view.findViewById(2131756734);
        this.r = (TextView) view.findViewById(2131756731);
        this.r.setText(PayUtil.a(this.g, this.f));
        this.t = (RangSeekBar) view.findViewById(2131756735);
        this.u = (TextView) view.findViewById(2131756737);
        this.v = (TextView) view.findViewById(2131756738);
    }

    protected final boolean b() {
        if (this.s == null) {
            return false;
        }
        int currentCoordValue = this.t.getCurrentCoordValue();
        if (currentCoordValue < 0 || currentCoordValue > this.s.getTdays()) {
            return false;
        }
        b.a(((PayActivity) getActivity()).j, ((PayActivity) getActivity()).i, ((PayActivity) getActivity()).h, -1, k(), this.f, this.g, -1, this.d, LoginHelper.c(), this.m.g(), this.m.d(), currentCoordValue, getString(R.string.version), j() != 0 ? j() : -1, 0);
        return true;
    }

    protected final void e() {
        XLPriceParam xLPriceParam = new XLPriceParam();
        xLPriceParam.mUserId = (int) this.m.a.j;
        xLPriceParam.mAccessToken = a.d;
        xLPriceParam.mOrderType = this.g.toXLSdkOrderType();
        if (this.f == 5 && this.m.b()) {
            xLPriceParam.mVasType = 209;
        } else {
            xLPriceParam.mVasType = this.f;
        }
        this.o = XLPayUtil.getInstance().userGetPrice(xLPriceParam, xLPriceParam);
    }

    protected final void a(int i, int i2, String str) {
        if (i2 == this.o) {
            UpgradePriceParam parseFrom = UpgradePriceParam.parseFrom(str);
            if (parseFrom == null) {
                c();
                if (((PayActivity) getActivity()) != null) {
                    b.a("pullfail", ((PayActivity) getActivity()).i, ((PayActivity) getActivity()).h, this.h.getType());
                }
            } else if (parseFrom.isSuccess()) {
                int intValue;
                this.s = parseFrom;
                d();
                PayConfigurationParam payConfigurationParam = this.h;
                long b = PayUtil.b(this.m.f());
                boolean b2 = j.a().b();
                if ((b <= 0 || b >= 31) && !b2) {
                    getView().findViewById(2131756732).setVisibility(0);
                    getView().findViewById(2131756736).setVisibility(XZBDevice.Wait);
                } else {
                    getView().findViewById(2131756732).setVisibility(XZBDevice.Wait);
                    getView().findViewById(2131756736).setVisibility(0);
                }
                this.q.setText(getString(2131231901, new Object[]{Integer.valueOf(parseFrom.getTdays())}));
                this.p.setText(getString(2131231903, new Object[]{Long.valueOf(b)}));
                this.t.a(Integer.valueOf(0), Integer.valueOf(parseFrom.getTdays()));
                int recommondUpMonth = payConfigurationParam.getRecommondUpMonth();
                if (recommondUpMonth != 0) {
                    int i3 = recommondUpMonth * 31;
                    if (i3 <= parseFrom.getTdays()) {
                        Object obj;
                        RangSeekBar rangSeekBar = this.t;
                        if (i3 % 31 == 0 && rangSeekBar.c.size() >= 2) {
                            int intValue2 = ((Integer) rangSeekBar.c.get(rangSeekBar.c.size() - 1)).intValue();
                            intValue = ((Integer) rangSeekBar.c.get(rangSeekBar.c.size() - 2)).intValue();
                            int i4 = intValue2 - i3;
                            if (i3 > intValue && i3 < intValue2 && i4 < 31) {
                                obj = 1;
                                if (obj == null) {
                                    intValue = i3;
                                    i3 = recommondUpMonth;
                                    this.t.setCurrentCoordValue(i3);
                                }
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            intValue = i3;
                            i3 = recommondUpMonth;
                            this.t.setCurrentCoordValue(i3);
                        }
                    }
                    intValue = parseFrom.getTdays();
                    i3 = -1;
                    this.t.setCurrentCoordValue(i3);
                } else {
                    intValue = parseFrom.getTdays();
                    this.t.setCurrentCoordValue(-1);
                }
                a(parseFrom, intValue);
                this.t.setOnRangeSeekBarChangeListener(new am(this, parseFrom));
                String str2 = this.f == 5 ? "\u8d85\u7ea7\u4f1a\u5458" : "\u767d\u91d1\u4f1a\u5458";
                this.u.setText(getString(2131231903, new Object[]{Long.valueOf(b)}));
                this.v.setText(Html.fromHtml(getString(2131231904, new Object[]{PayUtil.a(this.d), Integer.valueOf(parseFrom.getTdays()), str2})));
                if (((PayActivity) getActivity()) != null) {
                    PayActivity payActivity = (PayActivity) getActivity();
                    if (payActivity.i == this.f) {
                        new StringBuilder("pay upgraonPriceLoad---").append(payActivity.h);
                        b.a(payActivity.j, payActivity.i, payActivity.h, -1, k());
                    }
                }
            } else if (((PayActivity) getActivity()) != null) {
                ((PayActivity) getActivity()).g.b = false;
                ((PayActivity) getActivity()).h();
            }
        }
    }

    private void a(UpgradePriceParam upgradePriceParam, int i) {
        this.e = i;
        float uprateFromMap = upgradePriceParam.getUprateFromMap(i);
        float payCountOfDays = upgradePriceParam.getPayCountOfDays(i);
        float floatValue = new BigDecimal((double) payCountOfDays).setScale(1, XZBDevice.DOWNLOAD_LIST_ALL).floatValue();
        a((int) floatValue, 1);
        if (uprateFromMap >= 1.0f) {
            a(floatValue, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            a(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        } else if (uprateFromMap > 0.0f) {
            a((payCountOfDays / uprateFromMap) - floatValue);
            a(floatValue, (double) uprateFromMap);
        }
    }

    protected final boolean f() {
        if (this.s == null || this.s.getUprate() >= 1.0f) {
            Object obj = null;
        } else {
            boolean z = true;
        }
        if (!z) {
            return super.f();
        }
        a(new StringBuilder("\u5f53\u524d\u5347\u7ea7").append(PayUtil.a(this.f)).append("\u6709\u9650\u65f6\u6298\u6263\uff0c\u662f\u5426\u8981\u653e\u5f03\u8fd9\u6b21\u4f18\u60e0\uff1f").toString());
        return true;
    }

    protected final int g() {
        return -1;
    }

    protected final int h() {
        return k();
    }

    protected final int i() {
        return 0;
    }

    protected final void b(boolean z) {
        if (z && this.s != null && this.t != null) {
            a(this.s, this.t.getCurrentCoordValue());
        }
    }

    private int k() {
        int recommondUpMonth = this.h.getRecommondUpMonth();
        return recommondUpMonth != 0 ? recommondUpMonth * 31 : 0;
    }
}
