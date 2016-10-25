package com.xunlei.common.pay.a;

import android.text.TextUtils;
import com.xunlei.analytics.b.c;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.encrypt.URLCoder;
import com.xunlei.common.pay.XLPayType;
import com.xunlei.common.pay.param.XLAliPayContractParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLWxContractParam;
import com.xunlei.common.pay.param.XLWxPayParam;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: XLPayRequest.java
public class b {
    private static final String a = "http://dypay.vip.xunlei.com/phonepay/order/?";
    private static final String b = "http://dypay.vip.xunlei.com/phonepay/upgrade/?";
    private static final String c = "http://dypay.vip.xunlei.com/channelpay/monthUnsign/?";
    private static final String d = "http://dypay.vip.xunlei.com/channelpay/monthQuerysign/?";
    private static final String e = "http://dypay.vip.xunlei.com/phonepay/getprice/?";
    private XLPayParam f;

    public String a() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.f.mOrderType == 0) {
            String encode;
            StringBuffer append = stringBuffer.append("http://dypay.vip.xunlei.com/phonepay/order/?userid=").append(this.f.mUserId).append("&sessionid=").append(this.f.mSessionId).append("&access_token=").append(this.f.mAccessToken).append("&payway=").append(e()).append("&vastype=").append(this.f.mVasType).append("&month=").append(this.f.mMonth).append("&source=").append(this.f.mSource).append("&referfrom=").append(this.f.mReferFrom).append("&other1=").append(f()).append("&other2=").append(g()).append("&other4=").append(h()).append("&ext1=").append(i()).append("&ext2=").append(j()).append("&bgurl=");
            String str = BuildConfig.VERSION_NAME;
            if (this.f.mPayType == 268435462) {
                XLAliPayContractParam xLAliPayContractParam = (XLAliPayContractParam) this.f;
                if (!TextUtils.isEmpty(xLAliPayContractParam.mContractResultScheme)) {
                    encode = URLCoder.encode(xLAliPayContractParam.mContractResultScheme, CharsetConvert.UTF_8);
                    append.append(encode).append("&callback=").append(System.currentTimeMillis());
                }
            }
            encode = str;
            append.append(encode).append("&callback=").append(System.currentTimeMillis());
        } else if (this.f.mOrderType == 1) {
            stringBuffer.append("http://dypay.vip.xunlei.com/phonepay/upgrade/?userid=").append(this.f.mUserId).append("&sessionid=").append(this.f.mSessionId).append("&access_token=").append(this.f.mAccessToken).append("&payway=").append(e()).append("&vastype=").append(this.f.mVasType).append("&tdays=").append(this.f.mMonth).append("&source=").append(this.f.mSource).append("&referfrom=").append(this.f.mReferFrom).append("&other1=").append(f()).append("&other2=").append(g()).append("&other4=").append(h()).append("&ext1=").append(i()).append("&ext2=").append(j()).append("&callback=").append(System.currentTimeMillis());
        }
        return stringBuffer.toString();
    }

    public String b() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://dypay.vip.xunlei.com/phonepay/getprice/?userid=").append(this.f.mUserId).append("&access_token=").append(this.f.mAccessToken).append("&type=").append(this.f.mOrderType).append("&vastype=").append(this.f.mVasType).append("&payway=").append(e()).append("&source=").append(this.f.mSource).append("&callback=").append(System.currentTimeMillis());
        return stringBuffer.toString();
    }

    public String c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://dypay.vip.xunlei.com/channelpay/monthUnsign/?userid=").append(this.f.mUserId).append("&sessionid=").append(this.f.mSessionId).append("&vastype=").append(this.f.mVasType).append("&source=").append(this.f.mSource).append("&payway=").append(e()).append("&callback=").append(System.currentTimeMillis());
        return stringBuffer.toString();
    }

    public String d() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://dypay.vip.xunlei.com/channelpay/monthQuerysign/?userid=").append(this.f.mUserId).append("&sessionid=").append(this.f.mSessionId).append("&vastype=").append(this.f.mVasType).append("&source=").append(this.f.mSource).append("&payway=").append(e()).append("&callback=").append(System.currentTimeMillis());
        return stringBuffer.toString();
    }

    public b(XLPayParam xLPayParam) {
        this();
        this.f = null;
        this.f = xLPayParam;
    }

    private String e() {
        String str = BuildConfig.VERSION_NAME;
        switch (this.f.mPayType) {
            case XLPayType.XL_WX_PAY:
                return "weixin_app";
            case XLPayType.XL_ALI_PAY:
                return "zfb_jijian";
            case XLPayType.XL_NB_PAY:
                return "zfb_jijian";
            case XLPayType.XL_BAIDU_PAY:
                return "baidu";
            case XLPayType.XL_APPLE_PAY:
                return "apple";
            case XLPayType.XL_ALIPAY_CONTRACT:
                XLAliPayContractParam xLAliPayContractParam = (XLAliPayContractParam) this.f;
                if (!(xLAliPayContractParam.mOperateType == 8193 && xLAliPayContractParam.mQueryAllContract)) {
                    return "zfb_monthly";
                }
                return str;
            case XLPayType.XL_WX_CONTRACT:
                XLWxContractParam xLWxContractParam = (XLWxContractParam) this.f;
                if (!(xLWxContractParam.mOperateType == 8193 && xLWxContractParam.mQueryAllContract)) {
                    return "weixin_monthly";
                }
                return str;
            default:
                return str;
        }
    }

    private String f() {
        return this.f.mPayType == 268435457 ? ((XLWxPayParam) this.f).mAppId : this.f.mParamOther1;
    }

    private String g() {
        if (this.f.mPayType == 268435458) {
            return "0";
        }
        if (this.f.mPayType == 268435459) {
            return c.f;
        }
        return this.f.mPayType == 268435462 ? "ALIPAYAPP" : this.f.mParamOther2;
    }

    private String h() {
        return this.f.mParamOther4;
    }

    private String i() {
        String str = BuildConfig.VERSION_NAME;
        if (this.f.mVasType == 206) {
            return !TextUtils.isEmpty(this.f.mOrderExtraParam) ? this.f.mOrderExtraParam : str;
        } else {
            return this.f.mParamExt1;
        }
    }

    private String j() {
        String str = BuildConfig.VERSION_NAME;
        if (this.f.mVasType == 206) {
            return !TextUtils.isEmpty(this.f.mOrderVoucher) ? this.f.mOrderVoucher : str;
        } else {
            return this.f.mParamExt2;
        }
    }

    private String k() {
        String str = BuildConfig.VERSION_NAME;
        if (this.f.mPayType == 268435462) {
            XLAliPayContractParam xLAliPayContractParam = (XLAliPayContractParam) this.f;
            if (!TextUtils.isEmpty(xLAliPayContractParam.mContractResultScheme)) {
                return URLCoder.encode(xLAliPayContractParam.mContractResultScheme, CharsetConvert.UTF_8);
            }
        }
        return str;
    }
}
