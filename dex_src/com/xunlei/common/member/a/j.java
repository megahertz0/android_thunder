package com.xunlei.common.member.a;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.common.member.act.XLQQLoginActivity;
import org.json.JSONObject;

// compiled from: XLQQAuthListener.java
public class j implements IUiListener {
    private String a;
    private XLQQLoginActivity b;

    public j(XLQQLoginActivity xLQQLoginActivity) {
        this.a = j.class.getSimpleName();
        this.b = null;
        this.b = xLQQLoginActivity;
    }

    public void onComplete(Object obj) {
        XLLog.v(this.a, "XLQQAuthListener onComplete");
        int i = XLErrorCode.AUTH_USER_ERROR;
        if (obj != null) {
            JSONObject jSONObject = (JSONObject) obj;
            i = 0;
            try {
                CharSequence string = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                CharSequence string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                Object string3 = jSONObject.getString(SocialConstants.PARAM_OPEN_ID);
                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                    XLLog.v(this.a, new StringBuilder("getOpenidAndToken succeed opeid = ").append(string3).toString());
                }
            } catch (Exception e) {
                XLLog.v(this.a, new StringBuilder("getOpenidAndToken error ").append(e.getMessage()).toString());
            }
        } else {
            obj = null;
        }
        this.b.acceptQQAuthResult(i, obj);
    }

    public void onError(UiError uiError) {
        XLLog.v(this.a, new StringBuilder("XLQQAuthListener onError = ").append(uiError.errorMessage).toString());
        this.b.acceptQQAuthResult(XLErrorCode.AUTH_USER_ERROR, null);
    }

    public void onCancel() {
        XLLog.v(this.a, "XLQQAuthListener onCancel");
        this.b.acceptQQAuthResult(XLErrorCode.AUTH_USER_CANCLE, null);
    }

    private void a(JSONObject jSONObject) {
        try {
            CharSequence string = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
            CharSequence string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
            Object string3 = jSONObject.getString(SocialConstants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                XLLog.v(this.a, new StringBuilder("getOpenidAndToken succeed opeid = ").append(string3).toString());
            }
        } catch (Exception e) {
            XLLog.v(this.a, new StringBuilder("getOpenidAndToken error ").append(e.getMessage()).toString());
        }
    }
}
