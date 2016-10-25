package com.xunlei.downloadprovider.member.payment.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.common.pay.param.XLAliPayContractParam;
import com.xunlei.common.pay.param.XLPriceParam;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.bean.OpenPriceParam;
import com.xunlei.downloadprovider.member.payment.bean.PayConfigurationParam;
import com.xunlei.downloadprovider.member.payment.bean.PayMealItem;
import com.xunlei.downloadprovider.member.payment.external.PayBaseConstants;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.f;
import com.xunlei.downloadprovider.member.payment.ui.a.b;
import com.xunlei.downloadprovider.member.payment.ui.widget.UnScrollGridView;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class PayOpenFragment extends BasePayPageFragment {
    private boolean A;
    private TextView B;
    private f C;
    private int o;
    private OpenPriceParam p;
    private int q;
    private UnScrollGridView r;
    private PayMealItem s;
    private View t;
    private RelativeLayout u;
    private CheckBox v;
    private TextView w;
    private int x;
    private int y;
    private boolean z;

    public PayOpenFragment() {
        this.x = 0;
        this.z = false;
        this.A = false;
        this.C = new ae(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e();
        XLPayUtil.getInstance().attachListener(this.C);
        XLAliPayContractParam b = PayUtil.b(1, this.f, 0);
        b.mQueryAllContract = true;
        this.y = XLPayUtil.getInstance().userGetXLContractor(SpdyProtocol.SLIGHTSSL_0_RTT_MODE).userQuery(b, b);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.pay_open_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.r = (UnScrollGridView) view.findViewById(R.id.meal_item_gv);
        this.t = view.findViewById(R.id.renew_voucher_line);
        this.u = (RelativeLayout) view.findViewById(R.id.auto_renew_layout);
        this.v = (CheckBox) view.findViewById(R.id.auto_renew_select_cb);
        this.w = (TextView) view.findViewById(R.id.auto_renew_acount_tv);
        this.B = (TextView) view.findViewById(R.id.wxpay_tv_tip);
        this.u.setOnClickListener(new ab(this));
    }

    protected final void e() {
        XLPriceParam xLPriceParam = new XLPriceParam();
        xLPriceParam.mUserId = (int) j.a().a.j;
        xLPriceParam.mAccessToken = BuildConfig.VERSION_NAME;
        xLPriceParam.mOrderType = this.g.toXLSdkOrderType();
        xLPriceParam.mVasType = this.f;
        this.o = XLPayUtil.getInstance().userGetPrice(xLPriceParam, xLPriceParam);
    }

    private void a(PayConfigurationParam payConfigurationParam, OpenPriceParam openPriceParam) {
        Object bVar = new b(getActivity());
        this.r.setChoiceMode(1);
        this.r.setVerticalSpacing(getActivity().getResources().getDimensionPixelSize(R.dimen.payment_11_dp));
        this.r.setHorizontalSpacing(getActivity().getResources().getDimensionPixelSize(R.dimen.payment_11_dp));
        this.r.setNumColumns(MqttConnectOptions.MQTT_VERSION_3_1_1);
        this.r.setAdapter(bVar);
        this.r.setOnItemClickListener(new ac(this));
        ArrayList monthList = payConfigurationParam.getMonthList(false);
        if (monthList == null || monthList.size() <= 0) {
            c();
            return;
        }
        boolean z;
        String str;
        this.q = payConfigurationParam.getRecommondMonth();
        PayMealItem payMealItem = null;
        Object arrayList = new ArrayList();
        if (payConfigurationParam.getMode() == 0) {
            z = true;
        } else {
            z = false;
        }
        float monthPrice = openPriceParam.getMonthPrice();
        int size = monthList.size();
        int i = 0;
        while (i < size) {
            PayMealItem payMealItem2;
            int intValue = ((Integer) monthList.get(i)).intValue();
            Float f = (Float) openPriceParam.getPriceArray().get(intValue);
            if (f != null && f.floatValue() >= 0.0f) {
                PayMealItem payMealItem3 = new PayMealItem();
                payMealItem3.totalMoney = f.floatValue();
                payMealItem3.month = intValue;
                payMealItem3.monthUnitPrice = monthPrice;
                payMealItem3.isTotalPriceMode = z;
                payMealItem3.isRecommend = this.q == intValue;
                arrayList.add(payMealItem3);
                if (payMealItem3.isRecommend) {
                    payMealItem2 = payMealItem3;
                    i++;
                    payMealItem = payMealItem2;
                }
            }
            payMealItem2 = payMealItem;
            i++;
            payMealItem = payMealItem2;
        }
        bVar.a(arrayList);
        this.r.post(new ad(this, arrayList, payMealItem));
        TextView textView = this.w;
        Object[] objArr = new Object[1];
        if (this.f == 5) {
            str = "25";
        } else {
            str = "12.6";
        }
        objArr[0] = str;
        textView.setText(Html.fromHtml(getResouceString(R.string.pay_auto_renew_tip_acount, objArr)));
    }

    private void a(PayMealItem payMealItem) {
        float f = 0.0f;
        this.s = payMealItem;
        new StringBuilder("onMealItemSelected totalMoney=").append(payMealItem.totalMoney).append(", month=").append(payMealItem.month).append(", monthUnitPrice=").append(payMealItem.monthUnitPrice).append(" ,isTotalPriceMode=").append(payMealItem.isTotalPriceMode).append(" ,isRecommend=").append(payMealItem.isRecommend);
        a((int) payMealItem.totalMoney, 0);
        if (this.k.getVisibility() == 0) {
            this.t.setVisibility(0);
        } else {
            this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        this.e = payMealItem.month;
        a(payMealItem.totalMoney, 0.0f);
        int i = payMealItem.month;
        float monthPrice = (((float) i) * this.p.getMonthPrice()) - payMealItem.totalMoney;
        if (monthPrice >= 0.0f) {
            f = monthPrice;
        }
        a(f);
    }

    protected final void a(int i, int i2, String str) {
        if (this.o == i2) {
            new StringBuilder("onPriceLoad--errorCode").append(i).append("|jsonString=").append(str);
            OpenPriceParam parseFrom = OpenPriceParam.parseFrom(str);
            if (parseFrom == null || !parseFrom.isSuccess()) {
                c();
                if (((PayActivity) getActivity()) != null) {
                    com.xunlei.downloadprovider.member.payment.b.a(PayBaseConstants.PAY_PAGE_SHOW_FAIL, ((PayActivity) getActivity()).i, ((PayActivity) getActivity()).h, this.h.getType());
                    return;
                }
                return;
            }
            this.p = parseFrom;
            PayConfigurationParam payConfigurationParam = this.h;
            if (payConfigurationParam != null) {
                d();
                a(payConfigurationParam, parseFrom);
            }
            if (((PayActivity) getActivity()) != null) {
                PayActivity payActivity = (PayActivity) getActivity();
                if (payActivity.i == this.f) {
                    new StringBuilder("PayOpenFragment onPriceLoad---").append(payActivity.h);
                    com.xunlei.downloadprovider.member.payment.b.a(payActivity.j, payActivity.i, payActivity.h, this.q, -1);
                }
            }
        }
    }

    protected final boolean f() {
        String string;
        switch (this.f) {
            case MqttConnectOptions.MQTT_VERSION_3_1:
                string = getString(R.string.pay_open_platinum_back_tip);
                break;
            case SimpleLog.LOG_LEVEL_ERROR:
                string = getString(R.string.pay_open_super_back_tip);
                break;
            case 204:
                string = getString(R.string.pay_open_kuainiao_back_tip);
                break;
            default:
                string = getString(R.string.pay_open_back_tip, new Object[]{PayUtil.a(this.f)});
                break;
        }
        a(string);
        return true;
    }

    protected final int g() {
        return this.q;
    }

    protected final int h() {
        return -1;
    }

    protected final int i() {
        return this.x;
    }

    protected final void b(boolean z) {
        if (z && this.s != null) {
            a(this.s);
        }
    }

    protected final void a(int i) {
        if (i == 2) {
            if (this.z) {
                ValueAnimator a = a(this.u, g.a(getActivity(), 40.0f), 0);
                a.addListener(new af(this));
                a.start();
            }
        } else if (i == 1 && !this.z && this.A) {
            this.z = true;
            this.u.setVisibility(0);
            a(this.u, 0, g.a(getActivity(), 40.0f)).start();
        }
    }

    private ValueAnimator a(View view, int i, int i2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
        ofInt.addUpdateListener(new ag(this, view));
        return ofInt;
    }

    public void onDestroy() {
        super.onDestroy();
        XLPayUtil.getInstance().detachListener(this.C);
    }

    protected final boolean b() {
        com.xunlei.downloadprovider.member.payment.b.a(((PayActivity) getActivity()).j, ((PayActivity) getActivity()).i, ((PayActivity) getActivity()).h, this.q, -1, this.f, this.g, this.e, this.d, LoginHelper.c(), this.m.g(), this.m.d(), -1, getString(com.xunlei.downloadprovidershare.R.string.version), j() != 0 ? j() : -1, this.x);
        return true;
    }
}
