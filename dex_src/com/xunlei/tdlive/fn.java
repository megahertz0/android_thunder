package com.xunlei.tdlive;

import android.os.Handler;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.tdlive.modal.JsonWrapper;

// compiled from: WebBrowserActivity.java
class fn implements XLOnPayListener {
    final /* synthetic */ JsonWrapper a;
    final /* synthetic */ fm b;

    fn(fm fmVar, JsonWrapper jsonWrapper) {
        this.b = fmVar;
        this.a = jsonWrapper;
    }

    public void onWxPay(int i, String str, Object obj, int i2) {
        a(i, str);
    }

    public void onAliPay(int i, String str, Object obj, int i2) {
        a(i, str);
    }

    public void onNbPay(int i, String str, Object obj, int i2) {
    }

    public void onGetPrice(int i, String str, Object obj, int i2, String str2) {
    }

    public void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
    }

    private void a(int i, String str) {
        new Handler().post(new fo(this, this));
        JsonWrapper jsonWrapper = new JsonWrapper("{}");
        jsonWrapper.putInt(KEYS.RET, i);
        jsonWrapper.putString(SocialConstants.PARAM_SEND_MSG, str);
        jsonWrapper.put(SocializeConstants.JSON_DATA, this.a);
        this.b.a.callJS(this.b.c, jsonWrapper);
    }
}
