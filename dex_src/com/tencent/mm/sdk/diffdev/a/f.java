package com.tencent.mm.sdk.diffdev.a;

import android.os.AsyncTask;
import com.qq.e.comm.constants.ErrorCode.NetWorkError;
import com.tencent.mm.sdk.diffdev.OAuthErrCode;
import com.tencent.mm.sdk.diffdev.OAuthListener;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.download.Downloads.Impl;
import org.json.JSONObject;

final class f extends AsyncTask<Void, Void, a> {
    private OAuthListener an;
    private String aq;
    private int aw;
    private String url;

    static class a {
        public OAuthErrCode ap;
        public String ax;
        public int ay;

        a() {
        }

        public static a e(byte[] bArr) {
            a aVar = new a();
            if (bArr == null || bArr.length == 0) {
                aVar.ap = OAuthErrCode.WechatAuth_Err_NetworkErr;
            } else {
                try {
                    try {
                        JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                        aVar.ay = jSONObject.getInt("wx_errcode");
                        String.format("nooping uuidStatusCode = %d", new Object[]{Integer.valueOf(aVar.ay)});
                        switch (aVar.ay) {
                            case NetWorkError.RETRY_TIME_NATIVE_ERROR:
                                aVar.ap = OAuthErrCode.WechatAuth_Err_Timeout;
                                break;
                            case XLErrorCode.VERIFY_ERR_INCORRECT:
                                aVar.ap = OAuthErrCode.WechatAuth_Err_Cancel;
                                break;
                            case XLErrorCode.VERIFY_ERR_DUPLICATE:
                                aVar.ap = OAuthErrCode.WechatAuth_Err_OK;
                                break;
                            case XLErrorCode.VERIFY_ERR_EXPIRE:
                                aVar.ap = OAuthErrCode.WechatAuth_Err_OK;
                                aVar.ax = jSONObject.getString("wx_code");
                                break;
                            case XLErrorCode.VERIFY_ERR_TYPE_DISMATCH:
                                aVar.ap = OAuthErrCode.WechatAuth_Err_OK;
                                break;
                            case Impl.STATUS_PEER_NOT_FOUND_ERROR:
                                aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                                break;
                            default:
                                aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                                break;
                        }
                    } catch (Exception e) {
                        String.format("parse json fail, ex = %s", new Object[]{e.getMessage()});
                        aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                    }
                } catch (Exception e2) {
                    String.format("parse fail, build String fail, ex = %s", new Object[]{e2.getMessage()});
                    aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                }
            }
            return aVar;
        }
    }

    public f(String str, OAuthListener oAuthListener) {
        this.aq = str;
        this.an = oAuthListener;
        this.url = String.format("https://long.open.weixin.qq.com/connect/l/qrconnect?f=json&uuid=%s", new Object[]{str});
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        if (this.aq == null || this.aq.length() == 0) {
            a aVar = new a();
            aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
            return aVar;
        }
        while (!isCancelled()) {
            String str = this.url + (this.aw == 0 ? com.umeng.a.d : new StringBuilder("&last=").append(this.aw).toString());
            long currentTimeMillis = System.currentTimeMillis();
            byte[] b = e.b(str, 60000);
            long currentTimeMillis2 = System.currentTimeMillis();
            aVar = a.e(b);
            String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", new Object[]{str, aVar.ap.toString(), Integer.valueOf(aVar.ay), Long.valueOf(currentTimeMillis2 - currentTimeMillis)});
            if (aVar.ap == OAuthErrCode.WechatAuth_Err_OK) {
                this.aw = aVar.ay;
                if (aVar.ay == g.aB.getCode()) {
                    this.an.onQrcodeScanned();
                } else if (aVar.ay != g.aD.getCode() && aVar.ay == g.aC.getCode()) {
                    if (aVar.ax != null && aVar.ax.length() != 0) {
                        return aVar;
                    }
                    aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                    return aVar;
                }
            } else {
                String.format("nooping fail, errCode = %s, uuidStatusCode = %d", new Object[]{aVar.ap.toString(), Integer.valueOf(aVar.ay)});
                return aVar;
            }
        }
        aVar = new a();
        aVar.ap = OAuthErrCode.WechatAuth_Err_Auth_Stopped;
        return aVar;
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        a aVar = (a) obj;
        this.an.onAuthFinish(aVar.ap, aVar.ax);
    }
}
