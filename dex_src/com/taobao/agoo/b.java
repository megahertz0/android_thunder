package com.taobao.agoo;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.ACCSManager.AccsRequest;
import com.taobao.accs.IAppReceiverV1;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.agoo.a.a;
import com.taobao.agoo.a.a.c;
import java.util.Map;

// compiled from: Taobao
final class b extends IAppReceiverV1 {
    final /* synthetic */ Context a;
    final /* synthetic */ IRegister b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;

    b(Context context, IRegister iRegister, String str, String str2) {
        this.a = context;
        this.b = iRegister;
        this.c = str;
        this.d = str2;
    }

    public final void onBindApp(int i, String str) {
        try {
            ALog.i("TaobaoRegister", "onBindApp", Constants.KEY_ERROR_CODE, Integer.valueOf(i));
            if (i == 200) {
                if (com.taobao.accs.client.b.a(this.a).g(this.a.getPackageName())) {
                    ALog.i("TaobaoRegister", "agoo aready Registered return ", new Object[0]);
                    if (this.b != null) {
                        this.b.onSuccess(org.android.agoo.common.b.e(this.a));
                        return;
                    }
                    return;
                }
                if (TaobaoRegister.mRequestListener == null) {
                    TaobaoRegister.mRequestListener = new a();
                    ACCSManager.registerDataListener(this.a, TaobaoConstants.SERVICE_ID_DEVICECMD, TaobaoRegister.mRequestListener);
                }
                byte[] a = c.a(this.a, this.c, this.d);
                if (a != null) {
                    CharSequence sendRequest = ACCSManager.sendRequest(this.a, new AccsRequest(null, TaobaoConstants.SERVICE_ID_DEVICECMD, a, null));
                    if (TextUtils.isEmpty(sendRequest)) {
                        if (this.b != null) {
                            this.b.onFailure(TaobaoConstants.REGISTER_ERROR, "accs channel disabled!");
                        }
                    } else if (this.b != null) {
                        TaobaoRegister.mRequestListener.a.put(sendRequest, this.b);
                    }
                } else if (this.b != null) {
                    this.b.onFailure(TaobaoConstants.REGISTER_ERROR, "req data null");
                }
            } else if (this.b != null) {
                this.b.onFailure(String.valueOf(i), "accs bindapp error!");
            }
        } catch (Throwable th) {
            ALog.e("TaobaoRegister", "register onBindApp", th, new Object[0]);
        }
    }

    public final void onUnbindApp(int i) {
    }

    public final void onBindUser(String str, int i) {
    }

    public final void onUnbindUser(int i) {
    }

    public final String getService(String str) {
        return GlobalClientInfo.getInstance(this.a).getService(str);
    }

    public final Map<String, String> getAllServices() {
        return null;
    }
}
