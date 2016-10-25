package com.taobao.accs.net;

import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;

// compiled from: Taobao
class k implements Runnable {
    final /* synthetic */ i a;

    k(i iVar) {
        this.a = iVar;
    }

    public void run() {
        try {
            if (this.a.b != null) {
                Object appkey = UtilityImpl.getAppkey(this.a.b);
                Object ttId = UtilityImpl.getTtId(this.a.b);
                String appSecret = GlobalClientInfo.getInstance(this.a.b).getAppSecret();
                if (!TextUtils.isEmpty(appkey) && !TextUtils.isEmpty(ttId)) {
                    ALog.i("InAppConnection", "mTryStartServiceRunable bindapp", new Object[0]);
                    ACCSManager.bindApp(this.a.b, appkey, appSecret, ttId, GlobalClientInfo.getInstance(this.a.b).getAppReceiver());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
