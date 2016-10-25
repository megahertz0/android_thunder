package com.xunlei.tdlive;

import android.content.SharedPreferences;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.protocol.XLLiveGetGiftListRequest.GetGiftListResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: LivePlayerDialog.java
class ax implements ObjectCallBack {
    final /* synthetic */ SharedPreferences a;
    final /* synthetic */ au b;

    ax(au auVar, SharedPreferences sharedPreferences) {
        this.b = auVar;
        this.a = sharedPreferences;
    }

    public void onResponse(int i, String str, Object obj) {
        GetGiftListResp getGiftListResp = (GetGiftListResp) obj;
        if (i == 0 && getGiftListResp != null && getGiftListResp.data != null) {
            Object obj2 = null;
            String string = this.a.getString("gift_ver", BuildConfig.VERSION_NAME);
            if (string.length() > 0 && !string.equals(getGiftListResp.data.gift_ver)) {
                obj2 = 1;
                au.b(this.b, e.w);
            }
            if (obj2 != null || string.length() == 0) {
                this.a.edit().putString("gift_ver", getGiftListResp.data.gift_ver).apply();
            }
        }
    }
}
