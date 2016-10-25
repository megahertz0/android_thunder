package com.xunlei.downloadprovider.member.payment.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.taobao.accs.data.Message;
import com.umeng.a;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLWxPayParam;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.PayUtil.OrderType;
import com.xunlei.downloadprovider.member.payment.external.f;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URLEncoder;
import org.json.JSONObject;

public class BasePayActivity extends BaseActivity {
    XLPayParam a;
    j b;
    PayEntryParam c;
    private int d;
    private XLPayParam e;
    private int f;
    private XLPayParam g;
    private f h;

    public BasePayActivity() {
        this.b = j.a();
        this.h = new j(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && this.c == null) {
            this.c = (PayEntryParam) bundle.getParcelable("PayEntryParam");
        }
        a(getIntent());
        new StringBuilder("BasePayActivity--onCreate--EntryParam=").append(this.c);
        XLPayUtil.getInstance().attachListener(this.h);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            Uri data = intent.getData();
            if (data == null || !data.getScheme().equals("shouleirenew")) {
                a(intent);
                return;
            }
            if ("T".equals(data.getQueryParameter("is_success"))) {
                a(0, a.d, this.e, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            } else {
                a((int) Impl.STATUS_SUCCESS, getResources().getString(2131231861), this.e, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            }
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null && this.c == null) {
            this.c = (PayEntryParam) bundle.getParcelable("PayEntryParam");
        }
        new StringBuilder("BasePayActivity--onRestoreInstanceState--EntryParam=").append(this.c);
    }

    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        new StringBuilder("BasePayActivity--onSaveInstanceState--EntryParam=").append(this.c);
        bundle.putParcelable("PayEntryParam", this.c);
        super.onSaveInstanceState(bundle, persistableBundle);
    }

    protected void a(Intent intent) {
        if (this.c == null) {
            this.c = (PayEntryParam) getIntent().getParcelableExtra("PayEntryParam");
        }
    }

    protected void onDestroy() {
        XLPayUtil.getInstance().detachListener(this.h);
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        b((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    private void a(XLPayParam xLPayParam, String str, int i, int i2, int i3) {
        xLPayParam.mMonth = i;
        xLPayParam.mReferFrom = str;
        xLPayParam.mOrderType = i2;
        xLPayParam.mSource = "shoulei_android";
        if (i2 == 1 && i3 == 5 && this.b.b()) {
            xLPayParam.mVasType = 209;
        } else {
            xLPayParam.mVasType = i3;
        }
    }

    protected final int a(XLAlipayParam xLAlipayParam) {
        a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
        XLPayUtil instance = XLPayUtil.getInstance();
        xLAlipayParam.mUserId = (int) this.b.a.j;
        return instance.userAliPay(xLAlipayParam, xLAlipayParam);
    }

    protected void a(int i) {
    }

    protected void b(int i) {
    }

    protected final void a() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("login_from", "BasePayActivity");
        intent.setFlags(268435456);
        intent.putExtra("login_type", 1);
        startActivity(intent);
    }

    public void a(int i, String str, XLPayParam xLPayParam, int i2) {
    }

    public void a(int i, Object obj, int i2, String str) {
    }

    protected void a(int i, XLPayParam xLPayParam) {
        PaymentSuccessActivity.a(this, a(b(), xLPayParam.mOrderType), xLPayParam.mVasType, xLPayParam.mMonth, this.c.e);
    }

    protected static OrderType a(boolean z, int i) {
        OrderType orderType = OrderType.OPEN;
        if (i == 1) {
            return OrderType.UPGRADE;
        }
        return z ? OrderType.RENEW : OrderType.OPEN;
    }

    private static String a(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cash", str);
            return URLEncoder.encode(jSONObject.toString(), "utf-8");
        } catch (Exception e) {
            return null;
        }
    }

    public final void a(String str, int i, int i2, int i3, String str2, int i4) {
        Object obj = 1;
        if (LoginHelper.c()) {
            boolean z;
            if (i4 != 1) {
                z = false;
            }
            if (z) {
                a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                XLPayParam c = PayUtil.c(i, i3, i2);
                c.mReferFrom = str;
                if (!TextUtils.isEmpty(str2)) {
                    c.mParamExt1 = a(str2);
                }
                c.mQueryAllContract = false;
                this.f = XLPayUtil.getInstance().userGetXLContractor(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).userContract(c, c);
                return;
            }
            XLPayParam xLWxPayParam = new XLWxPayParam();
            a(xLWxPayParam, str, i, i2, i3);
            xLWxPayParam.mAppId = "wx3e6556568beeebdd";
            if (!TextUtils.isEmpty(str2)) {
                xLWxPayParam.mParamExt1 = a(str2);
            }
            this.a = xLWxPayParam;
            a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            xLWxPayParam.mUserId = (int) this.b.a.j;
            XLPayUtil.getInstance().userWxPay(xLWxPayParam, xLWxPayParam);
            return;
        }
        a();
    }

    public final void b(String str, int i, int i2, int i3, String str2, int i4) {
        Object obj = 1;
        if (LoginHelper.c()) {
            if (i4 != 1) {
                obj = null;
            }
            if (obj == null) {
                XLAlipayParam xLAlipayParam = new XLAlipayParam();
                a(xLAlipayParam, str, i, i2, i3);
                xLAlipayParam.mActivity = this;
                if (!TextUtils.isEmpty(str2)) {
                    xLAlipayParam.mParamExt1 = a(str2);
                }
                this.a = xLAlipayParam;
                a(xLAlipayParam);
                return;
            }
            a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            XLPayParam b = PayUtil.b(i, i3, i2);
            b.mReferFrom = str;
            b.mContractResultScheme = "shouleirenew://contract";
            if (!TextUtils.isEmpty(str2)) {
                b.mParamExt1 = a(str2);
            }
            this.d = XLPayUtil.getInstance().userGetXLContractor(Message.FLAG_ERR).userContract(b, b);
            return;
        }
        a();
    }

    protected final boolean b() {
        return LoginHelper.c() && this.b.c() && !this.b.a.h();
    }

    static /* synthetic */ void b(BasePayActivity basePayActivity, XLPayParam xLPayParam) {
        d dVar = new d(basePayActivity);
        dVar.b();
        dVar.a();
        dVar.b(new k(basePayActivity, xLPayParam, dVar));
        dVar.show();
    }
}
