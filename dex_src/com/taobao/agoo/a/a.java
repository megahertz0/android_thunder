package com.taobao.agoo.a;

import android.text.TextUtils;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.d;
import com.taobao.agoo.ICallback;
import com.taobao.agoo.IRegister;
import com.taobao.agoo.TaobaoConstants;
import com.taobao.agoo.a.a.b;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: Taobao
public class a extends AccsAbstractDataListener {
    public Map<String, ICallback> a;

    public a() {
        this.a = new HashMap();
    }

    public void onResponse(String str, String str2, int i, byte[] bArr, ExtraInfo extraInfo) {
        try {
            if (TaobaoConstants.SERVICE_ID_DEVICECMD.equals(str)) {
                ICallback iCallback = (ICallback) this.a.get(str2);
                if (i == 200) {
                    ALog.i("RequestListener", "RequestListener onResponse", Constants.KEY_DATA_ID, str2, "listener", iCallback, "json", new String(bArr, "utf-8"));
                    JSONObject jSONObject = new JSONObject(a);
                    String a = d.a(jSONObject, b.JSON_ERRORCODE, null);
                    String a2 = d.a(jSONObject, b.JSON_CMD, null);
                    if (!MsgConstant.KEY_SUCCESS.equals(a)) {
                        if (iCallback != null) {
                            iCallback.onFailure(String.valueOf(a), "agoo server error");
                        }
                        if (TaobaoConstants.SERVICE_ID_DEVICECMD.equals(str)) {
                            this.a.remove(str2);
                            return;
                        }
                        return;
                    } else if (MiPushClient.COMMAND_REGISTER.equals(a2)) {
                        a = d.a(jSONObject, org.android.agoo.common.b.KEY_DEVICE_TOKEN, null);
                        if (!TextUtils.isEmpty(a)) {
                            if (iCallback != null) {
                                if (iCallback instanceof IRegister) {
                                    ((IRegister) iCallback).onSuccess(a);
                                }
                            }
                            org.android.agoo.common.b.a(GlobalClientInfo.getContext(), a);
                            com.taobao.accs.client.b.a(GlobalClientInfo.getContext()).f(GlobalClientInfo.getContext().getPackageName());
                        } else if (iCallback != null) {
                            iCallback.onFailure(com.umeng.a.d, "agoo server error deviceid null");
                        }
                        if (TaobaoConstants.SERVICE_ID_DEVICECMD.equals(str)) {
                            this.a.remove(str2);
                            return;
                        }
                        return;
                    } else if (com.taobao.agoo.a.a.a.JSON_CMD_SETALIAS.equals(a2)) {
                        a(jSONObject, iCallback);
                        if (TaobaoConstants.SERVICE_ID_DEVICECMD.equals(str)) {
                            this.a.remove(str2);
                            return;
                        }
                        return;
                    } else if (com.taobao.agoo.a.a.a.JSON_CMD_REMOVEALIAS.equals(a2)) {
                        org.android.agoo.common.b.b(GlobalClientInfo.getContext(), null);
                        if (iCallback != null) {
                            iCallback.onSuccess();
                        }
                        com.taobao.accs.client.b.a(GlobalClientInfo.getContext()).a();
                        if (TaobaoConstants.SERVICE_ID_DEVICECMD.equals(str)) {
                            this.a.remove(str2);
                            return;
                        }
                        return;
                    }
                } else if (iCallback != null) {
                    iCallback.onFailure(String.valueOf(i), "accs channel error");
                }
            }
            if (TaobaoConstants.SERVICE_ID_DEVICECMD.equals(str)) {
                this.a.remove(str2);
            }
        } catch (Throwable th) {
            try {
                ALog.e("RequestListener", "onResponse", th, new Object[0]);
                if (TaobaoConstants.SERVICE_ID_DEVICECMD.equals(str)) {
                    this.a.remove(str2);
                }
            } catch (Throwable th2) {
                if (TaobaoConstants.SERVICE_ID_DEVICECMD.equals(str)) {
                    this.a.remove(str2);
                }
            }
        }
    }

    public void onData(String str, String str2, String str3, byte[] bArr, ExtraInfo extraInfo) {
    }

    public void onBind(String str, int i, ExtraInfo extraInfo) {
    }

    public void onUnbind(String str, int i, ExtraInfo extraInfo) {
    }

    public void onSendData(String str, String str2, int i, ExtraInfo extraInfo) {
    }

    private void a(JSONObject jSONObject, ICallback iCallback) throws JSONException {
        String a = d.a(jSONObject, com.taobao.agoo.a.a.a.JSON_PUSH_USER_TOKEN, null);
        if (!TextUtils.isEmpty(a)) {
            org.android.agoo.common.b.b(GlobalClientInfo.getContext(), a);
            if (iCallback != null) {
                iCallback.onSuccess();
                com.taobao.accs.client.b.a(GlobalClientInfo.getContext()).h(iCallback.extra);
            }
        } else if (iCallback != null) {
            iCallback.onFailure(com.umeng.a.d, "agoo server error-pushtoken null");
        }
    }
}
