package com.xunlei.downloadprovider.member.payment.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.TextView;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType;
import com.xunlei.downloadprovider.member.payment.external.b;
import com.xunlei.downloadprovider.web.core.ThunderWebView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.Random;

public class PaymentSuccessActivity extends BaseActivity {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private b h;
    private ThunderWebView i;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.h = (b) getIntent().getSerializableExtra("SuccessDestination");
        setContentView(2130968909);
        this.a = (TextView) findViewById(R.id.title);
        this.b = (TextView) findViewById(2131756749);
        this.c = (TextView) findViewById(2131756750);
        this.d = (TextView) findViewById(2131756752);
        this.e = (TextView) findViewById(2131756753);
        this.f = (TextView) findViewById(2131756754);
        this.g = (TextView) findViewById(2131756755);
        this.i = (ThunderWebView) findViewById(R.id.webView);
        WebSettings settings = this.i.a.getSettings();
        settings.setCacheMode(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 11) {
            boolean z;
            try {
                PackageManager packageManager = getPackageManager();
                z = packageManager.hasSystemFeature("android.hardware.touchscreen.multitouch") || packageManager.hasSystemFeature("android.hardware.faketouch.multitouch.distinct");
            } catch (Exception e) {
                z = false;
            }
            if (z) {
                z = false;
            } else {
                z = true;
            }
            settings.setDisplayZoomControls(z);
        }
        this.i.a(new StringBuilder("http://act.vip.xunlei.com/vip/slpayadv/?t=").append(new Random().nextLong()).toString());
        this.i.setThunderWebViewClient(new ar(this));
        Intent intent = getIntent();
        String e2 = j.a().a.e();
        int intExtra = intent.getIntExtra("MonthOrDays", -1);
        int intExtra2 = intent.getIntExtra("VasType", -1);
        OrderType orderType = (OrderType) intent.getSerializableExtra("OrderType");
        this.a.setText(getString(2131231886, new Object[]{orderType.getText(intent.getBooleanExtra("ExpiredToday", false))}));
        this.b.setText(getString(2131231885, new Object[]{r7}));
        this.c.setText(getString(2131231882, new Object[]{r7, e2}));
        this.d.setText(getString(2131231884, new Object[]{r7}));
        j.a();
        this.e.setText(r7 + PayUtil.a(intExtra2));
        r0 = orderType == OrderType.UPGRADE ? intExtra + "\u5929" : (orderType == OrderType.OPEN || orderType == OrderType.RENEW) ? intExtra >= 12 ? (intExtra / 12) + "\u5e74" : intExtra + "\u4e2a\u6708" : a.d;
        this.f.setText(getString(2131231883, new Object[]{r7, r0}));
        PayUtil.a = true;
        PayUtil.b = true;
        LoginHelper.a().a(new aq(this));
        PayUtil.a();
        LoginHelper.a().s();
        LoginHelper.a().v();
        LoginHelper.a().w();
        com.xunlei.downloadprovider.member.payment.b.c();
    }

    public void onBackPressed() {
        a();
    }

    private void a() {
        if (this.h == null) {
            setResult(-1);
            finish();
            return;
        }
        b bVar = this.h;
        Intent intent = new Intent(this, bVar.a);
        Object obj = bVar.b;
        if (obj != null) {
            if (obj instanceof Serializable) {
                intent.putExtra("UserData", (Serializable) obj);
            } else if (obj instanceof Parcelable) {
                intent.putExtra("UserData", (Parcelable) obj);
            }
        }
        intent.addFlags(67108864);
        startActivity(intent);
        finish();
    }

    public void onClickGoback(View view) {
        a();
    }

    public static void a(Activity activity, OrderType orderType, int i, int i2, b bVar) {
        a(activity, orderType, i, i2, false, bVar);
    }

    public static void a(Activity activity, OrderType orderType, int i, int i2, boolean z, b bVar) {
        Intent intent = new Intent(activity, PaymentSuccessActivity.class);
        intent.putExtra("OrderType", orderType);
        intent.putExtra("VasType", i);
        intent.putExtra("MonthOrDays", i2);
        if (!TextUtils.isEmpty(null)) {
            intent.putExtra("AnnualPromotion", null);
        }
        intent.putExtra("ExpiredToday", z);
        intent.putExtra("SuccessDestination", bVar);
        activity.startActivity(intent);
        activity.finish();
    }
}
