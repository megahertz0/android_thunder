package com.taobao.accs.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Taobao
public class MsgDistributeService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            ALog.i("MsgDistributeService", "onStartCommand", JsInterface.KEY_ACTION, intent.getAction());
            if (TextUtils.isEmpty(intent.getAction()) || !TextUtils.equals(intent.getAction(), Constants.ACTION_SEND)) {
                e.a(getApplicationContext(), intent);
            } else {
                ACCSManager.getManagerImpl(getApplicationContext()).sendRequest(getApplicationContext(), (AccsRequest) intent.getSerializableExtra(Constants.KEY_SEND_REQDATA), intent.getStringExtra(JsInterface.KEY_APK_NAME), false);
            }
        } catch (Throwable th) {
            ALog.e("MsgDistributeService", "onStartCommand", th, new Object[0]);
        }
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }
}
