package com.taobao.accs.internal;

import android.content.Context;
import android.content.Intent;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.UtilityImpl;
import com.xunlei.downloadprovider.web.core.JsInterface;

// compiled from: Taobao
class a implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ ACCSManagerImpl b;

    a(ACCSManagerImpl aCCSManagerImpl, Context context) {
        this.b = aCCSManagerImpl;
        this.a = context;
    }

    public void run() {
        Intent intent = new Intent(Constants.ACTION_START_SERVICE);
        intent.putExtra(Constants.KEY_APP_KEY, UtilityImpl.getAppkey(this.a));
        intent.putExtra(Constants.KEY_TTID, UtilityImpl.getTtId(this.a));
        intent.putExtra(JsInterface.KEY_APK_NAME, this.a.getPackageName());
        intent.putExtra(Constants.SP_APP_SECRET, GlobalClientInfo.getInstance(this.a).getAppSecret());
        intent.setClassName(this.a.getPackageName(), com.taobao.accs.utl.a.channelService);
        this.a.startService(intent);
        intent = new Intent();
        intent.setAction("org.agoo.android.intent.action.REPORT");
        intent.setPackage(this.a.getPackageName());
        intent.setClassName(this.a.getPackageName(), com.taobao.accs.client.a.b(this.a.getPackageName()));
        this.a.startService(intent);
    }
}
