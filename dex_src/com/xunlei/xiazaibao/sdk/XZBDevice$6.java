package com.xunlei.xiazaibao.sdk;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.xiazaibao.sdk.base.AsynTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.synctasks.XZBDownloadLogin;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import org.android.spdy.TnetStatusCode;
import org.json.JSONException;
import org.json.JSONObject;

class XZBDevice$6 extends AsynTask {
    final /* synthetic */ XZBDevice this$0;
    final /* synthetic */ Object val$userData;

    XZBDevice$6(XZBDevice xZBDevice, Object obj, String str, Object obj2, Object obj3) {
        this.this$0 = xZBDevice;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        HttpResponse httpGet = new XZBDownloadLogin(this.this$0.getPid()).httpGet();
        if (httpGet.getStatusCode() != 200) {
            XZBLog.d(XZBDevice.access$300(), new StringBuilder("downloadLoginTask error errorcode = ").append(httpGet.getStatusCode()).append(" userData = ").append(this.val$userData).toString());
            fireCallback(new Object[]{Integer.valueOf(httpGet.getStatusCode()), Integer.valueOf(getTaskId()), "login request error", this.val$userData});
            return;
        }
        String stringBody = httpGet.getStringBody();
        XZBLog.d(XZBDevice.access$300(), new StringBuilder("downloadLoginTask response = ").append(stringBody).append(" userData = ").append(this.val$userData).toString());
        try {
            JSONObject jSONObject = new JSONObject(stringBody);
            int i = jSONObject.getInt("rtn");
            String string = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_MSG);
            if (i != 0) {
                fireCallback(new Object[]{Integer.valueOf(i), Integer.valueOf(getTaskId()), new StringBuilder("login request error ").append(string).toString(), this.val$userData});
                return;
            }
            fireCallback(new Object[]{Integer.valueOf(0), Integer.valueOf(getTaskId()), "login request success", this.val$userData});
        } catch (JSONException e) {
            fireCallback(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(getTaskId()), "login request error json exception", this.val$userData});
        }
    }
}
