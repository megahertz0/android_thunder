package com.xunlei.downloadprovider.member.payment.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;
import com.android.volley.toolbox.t;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.external.OperType;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.core.ThunderWebView;
import com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentH5Activity extends BasePayActivity implements OnClickListener {
    private j d;
    private ThunderWebView e;
    private f f;
    private View g;
    private TextView h;
    private OperType i;
    private String j;
    private a k;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[OperType.values().length];
            try {
                a[OperType.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[OperType.UPGRADE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public PaymentH5Activity() {
        this.d = j.a();
        this.k = new an(this);
    }

    static /* synthetic */ int a(OperType operType) {
        switch (AnonymousClass_1.a[operType.ordinal()]) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return 1;
            default:
                return 0;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.xunlei.downloadprovider.member.payment.a.a();
        setContentView(2130968907);
        this.f = new f((Activity) this);
        this.g = findViewById(R.id.titlebar_left);
        this.g.setOnClickListener(this);
        this.h = (TextView) findViewById(R.id.titlebar_title);
        this.h.setText(getResources().getString(2131231711));
        this.f.k.setVisibility(0);
        this.f.k.setText(getResources().getString(2131230806));
        this.f.k.setTextColor(getResources().getColor(com.xunlei.tdlive.R.color.global_text_color_2));
        this.f.k.setOnClickListener(this);
        this.e = (ThunderWebView) findViewById(com.xunlei.tdlive.R.id.webView);
        this.e.setJsCallbackMessageListener(this.k);
        this.e.a.getSettings().setCacheMode(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.e.a("http://m.sjzhushou.com/v2/pay/index.html");
    }

    protected final void a(Intent intent) {
        super.a(intent);
        this.i = this.c.b;
        this.j = this.c.a.toFrom();
    }

    protected final void a(int i) {
        this.e.setCanLoadingShow(true);
        this.e.setCurShowView(CurrentShowState.show_loading);
    }

    protected final void b(int i) {
        this.e.setCanLoadingShow(false);
        this.e.setCurShowView(CurrentShowState.show_webview);
    }

    protected void onResume() {
        super.onResume();
        b((int) XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_left:
                finish();
            case com.xunlei.tdlive.R.id.titlebar_right_1:
                StatReporter.reportPayWayPay();
                StatReporter.reportClickMemberRecharge("activation");
                startActivity(new Intent(this, ActivationActivity.class));
            default:
                break;
        }
    }

    public final void a(int i, String str, XLPayParam xLPayParam, int i2) {
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                a(i2, xLPayParam);
                break;
            case com.xunlei.tdlive.R.styleable.AppCompatTheme_buttonStyleSmall:
                break;
            default:
                popupOneBtnDialog(str, null);
                break;
        }
        com.xunlei.downloadprovider.member.payment.a.a(this.j, xLPayParam.mMonth, i);
    }

    static /* synthetic */ void a(PaymentH5Activity paymentH5Activity, String str) {
        paymentH5Activity.a((int) XZBDevice.DOWNLOAD_LIST_ALL);
        com.xunlei.downloadprovider.j.a.a().e().a(new t("http://pay.vip.xunlei.com/shoulei/android/price_config/5.11.js", new ao(paymentH5Activity, str), new ap(paymentH5Activity, str)));
    }

    static /* synthetic */ void b(PaymentH5Activity paymentH5Activity, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("monthOrTDays");
            int i2 = jSONObject.getInt("orderType");
            int i3 = jSONObject.getInt("vasType");
            String optString = jSONObject.optString("payRefer", "Payment");
            paymentH5Activity.a = null;
            com.xunlei.downloadprovider.member.payment.ui.widget.a aVar = new com.xunlei.downloadprovider.member.payment.ui.widget.a(paymentH5Activity);
            aVar.a = new i(paymentH5Activity, aVar, optString, i, i2, i3);
            aVar.show();
        } catch (Throwable e) {
            XLLog.e("PaymentH5Activity", e);
        }
    }

    static /* synthetic */ void a(PaymentH5Activity paymentH5Activity, String str, String str2) {
        try {
            String a = PayUtil.a(new JSONObject(str).getString(com.alipay.sdk.authjs.a.c), str2);
            ThunderWebView thunderWebView = paymentH5Activity.e;
            if (thunderWebView.a instanceof WebView) {
                thunderWebView.a.loadUrl(a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
