package com.xunlei.downloadprovider.web;

import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

// compiled from: DetailPageBrowserActivity.java
final class i implements XLOnPayListener {
    final /* synthetic */ DetailPageBrowserActivity a;

    i(DetailPageBrowserActivity detailPageBrowserActivity) {
        this.a = detailPageBrowserActivity;
    }

    public final void onAliPay(int i, String str, Object obj, int i2) {
        XLPayUtil.getInstance().detachListener(this);
        if (!TextUtils.isEmpty(this.a.w)) {
            Map hashMap = new HashMap();
            hashMap.put("ErrorCode", String.valueOf(i));
            hashMap.put("Payment", Alipay.Name);
            JSONObject jSONObject = new JSONObject(hashMap);
            this.a.d.a(PayUtil.a(this.a.w, jSONObject.toString()));
            this.a.w = null;
        }
    }

    public final void onGetPrice(int i, String str, Object obj, int i2, String str2) {
    }

    public final void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
    }

    public final void onNbPay(int i, String str, Object obj, int i2) {
    }

    public final void onWxPay(int i, String str, Object obj, int i2) {
        XLPayUtil.getInstance().detachListener(this);
        if (!TextUtils.isEmpty(this.a.w)) {
            Map hashMap = new HashMap();
            hashMap.put(Constants.KEY_ERROR_CODE, String.valueOf(i));
            hashMap.put("payment", "weixin");
            JSONObject jSONObject = new JSONObject(hashMap);
            this.a.d.a(PayUtil.a(this.a.w, jSONObject.toString()));
            this.a.w = null;
        }
    }
}
