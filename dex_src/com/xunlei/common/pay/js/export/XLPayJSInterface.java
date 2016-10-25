package com.xunlei.common.pay.js.export;

import android.webkit.JavascriptInterface;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.pay.a.a;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.js.c;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLWxPayParam;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class XLPayJSInterface {
    public static final String XL_JS_PREFIX = "javascript:";
    private String TAG;
    private Map<Integer, a> mRequestCallBackList;
    private c mXLPayJSRegister;

    public XLPayJSInterface() {
        this.TAG = XLPayJSInterface.class.getSimpleName();
        this.mRequestCallBackList = new HashMap();
        this.mXLPayJSRegister = null;
    }

    public void init(c cVar) {
        this.mXLPayJSRegister = cVar;
    }

    public void unInit() {
        this.mXLPayJSRegister = null;
    }

    public void receiveMessage(int i, String str, Object obj, int i2) {
        XLLog.v(this.TAG, new StringBuilder("receive pay callback message errorcode = ").append(i).append("#thread = ").append(Thread.currentThread().getId()).toString());
        a aVar = (a) this.mRequestCallBackList.get(Integer.valueOf(i2));
        if (aVar != null) {
            javaCallBackToJS$55092127(aVar, i, str);
            this.mRequestCallBackList.remove(Integer.valueOf(i2));
        }
    }

    private int dispatchMessage(String str, String str2) {
        if ("WX_PAY".equals(str)) {
            return userWxPay(str2);
        }
        if ("ALI_PAY".equals(str)) {
            return userAliPay(str2);
        }
        return "NB_PAY".equals(str) ? userNbPay(str2) : 0;
    }

    @JavascriptInterface
    public int sendMessage(String str, String str2, String str3, String str4) {
        XLLog.v(this.TAG, new StringBuilder("receive message from javascript method = ").append(str).append("#param = ").append(str2).append("#Thread = ").append(Thread.currentThread().getId()).toString());
        int dispatchMessage = dispatchMessage(str, str2);
        if (dispatchMessage > 0) {
            this.mRequestCallBackList.put(Integer.valueOf(dispatchMessage), new a(str, str4, str3));
        } else {
            XLLog.v(this.TAG, new StringBuilder("call sdk pay interface error = ").append(dispatchMessage).toString());
            javaCallBackToJS(str, str3, str4, Constants.COMMAND_PING, XLPayErrorCode.getErrorDesc(Constants.COMMAND_PING));
        }
        return dispatchMessage;
    }

    private void javaCallBackToJS$55092127(a aVar, int i, String str) {
        javaCallBackToJS(aVar.a, aVar.c, aVar.b, i, str);
    }

    private void javaCallBackToJS(String str, String str2, String str3, int i, String str4) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(XL_JS_PREFIX).append(str2).append(SocializeConstants.OP_OPEN_PAREN).append(i).append(",'").append(processStringParam(str4)).append("','").append(str).append("','").append(processStringParam(str3)).append("')");
        XLLog.v(this.TAG, new StringBuilder("call back to JS url = ").append(stringBuffer.toString()).toString());
        this.mXLPayJSRegister.a(stringBuffer.toString());
    }

    private String processStringParam(String str) {
        return str == null ? com.umeng.a.d : str.replace("\\", "\\\\").replace("'", "\\'");
    }

    private int userWxPay(String str) {
        JSONObject jsonParam = getJsonParam(str);
        if (jsonParam == null) {
            return -1;
        }
        XLWxPayParam xLWxPayParam = new XLWxPayParam();
        try {
            XLPayJSUserInfo c = this.mXLPayJSRegister.c();
            if (c == null) {
                return -1;
            }
            xLWxPayParam.mUserId = c.userId;
            xLWxPayParam.mAccessToken = c.accessToken;
            xLWxPayParam.mSessionId = c.sessionId;
            xLWxPayParam.mAppId = c.wxAppId;
            xLWxPayParam.mVasType = jsonParam.getInt("vasType");
            xLWxPayParam.mMonth = jsonParam.getInt("month");
            xLWxPayParam.mOrderType = jsonParam.getInt("orderType");
            xLWxPayParam.mSource = jsonParam.optString(SocialConstants.PARAM_SOURCE);
            xLWxPayParam.mReferFrom = jsonParam.optString("referfrom");
            xLWxPayParam.mParamExt1 = jsonParam.optString("ext1");
            xLWxPayParam.mOrderExtraParam = jsonParam.optString("orderExtraParam");
            xLWxPayParam.mOrderVoucher = jsonParam.optString("orderVoucher");
            return f.a().a(xLWxPayParam, "xl-js-wx-pay", new com.xunlei.common.pay.js.a(this));
        } catch (JSONException e) {
            XLLog.v(this.TAG, "js param error");
            return -1;
        }
    }

    private int userAliPay(String str) {
        JSONObject jsonParam = getJsonParam(str);
        if (jsonParam == null) {
            return -1;
        }
        XLAlipayParam xLAlipayParam = new XLAlipayParam();
        try {
            XLPayJSUserInfo c = this.mXLPayJSRegister.c();
            if (c == null) {
                return -1;
            }
            xLAlipayParam.mUserId = c.userId;
            xLAlipayParam.mAccessToken = c.accessToken;
            xLAlipayParam.mSessionId = c.sessionId;
            xLAlipayParam.mVasType = jsonParam.getInt("vasType");
            xLAlipayParam.mMonth = jsonParam.getInt("month");
            xLAlipayParam.mOrderType = jsonParam.getInt("orderType");
            xLAlipayParam.mSource = jsonParam.optString(SocialConstants.PARAM_SOURCE);
            xLAlipayParam.mReferFrom = jsonParam.optString("referfrom");
            xLAlipayParam.mParamExt1 = jsonParam.optString("ext1");
            xLAlipayParam.mOrderExtraParam = jsonParam.optString("orderExtraParam");
            xLAlipayParam.mOrderVoucher = jsonParam.optString("orderVoucher");
            xLAlipayParam.mActivity = this.mXLPayJSRegister.b();
            return f.a().a(xLAlipayParam, "xl-js-ali-pay", new com.xunlei.common.pay.js.a(this));
        } catch (JSONException e) {
            XLLog.v(this.TAG, "js param error");
            return -1;
        }
    }

    private int userNbPay(String str) {
        JSONObject jsonParam = getJsonParam(str);
        if (jsonParam == null) {
            return -1;
        }
        XLAlipayParam xLAlipayParam = new XLAlipayParam();
        try {
            XLPayJSUserInfo c = this.mXLPayJSRegister.c();
            if (c == null) {
                return -1;
            }
            xLAlipayParam.mUserId = c.userId;
            xLAlipayParam.mAccessToken = c.accessToken;
            xLAlipayParam.mSessionId = c.sessionId;
            xLAlipayParam.mVasType = jsonParam.getInt("vasType");
            xLAlipayParam.mMonth = jsonParam.getInt("month");
            xLAlipayParam.mOrderType = jsonParam.getInt("orderType");
            xLAlipayParam.mSource = jsonParam.optString(SocialConstants.PARAM_SOURCE);
            xLAlipayParam.mReferFrom = jsonParam.optString("referfrom");
            xLAlipayParam.mParamExt1 = jsonParam.optString("ext1");
            xLAlipayParam.mOrderExtraParam = jsonParam.optString("orderExtraParam");
            xLAlipayParam.mOrderVoucher = jsonParam.optString("orderVoucher");
            xLAlipayParam.mActivity = this.mXLPayJSRegister.b();
            return f.a().b(xLAlipayParam, "xl-js-nb-pay", new com.xunlei.common.pay.js.a(this));
        } catch (JSONException e) {
            XLLog.v(this.TAG, "js param error");
            return -1;
        }
    }

    private JSONObject getJsonParam(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            XLLog.v(this.TAG, "get json param error.");
            return null;
        }
    }
}
