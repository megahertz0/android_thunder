package com.xunlei.common.pay.a;

import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLContractor$XLContractOperate;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import org.android.spdy.SpdyProtocol;

// compiled from: XLStatPayListener.java
public final class g implements XLOnPayListener {
    private XLStatUtil a;

    public g(XLStatUtil xLStatUtil) {
        this.a = null;
        this.a = xLStatUtil;
    }

    public final void onWxPay(int i, String str, Object obj, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 300000;
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
    }

    public final void onAliPay(int i, String str, Object obj, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 300001;
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
    }

    public final void onNbPay(int i, String str, Object obj, int i2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 300002;
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
    }

    public final void onGetPrice(int i, String str, Object obj, int i2, String str2) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mCommandID = 300004;
        xLStatPack.mErrorCode = i;
        this.a.report(i2, xLStatPack);
    }

    public final void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mErrorCode = i;
        switch (xLContractResp.mOperateType) {
            case SpdyProtocol.SLIGHTSSL_1_RTT_MODE:
                if (xLContractResp.mContractType == 4096) {
                    xLStatPack.mCommandID = 301001;
                }
                break;
            case XLContractor$XLContractOperate.XL_OPERATE_QUERY:
                if (xLContractResp.mContractType == 4096) {
                    xLStatPack.mCommandID = 301002;
                }
                break;
            case XLContractor$XLContractOperate.XL_OPERATE_DISCONTRACT:
                if (xLContractResp.mContractType == 4096) {
                    xLStatPack.mCommandID = 301003;
                }
                break;
        }
        this.a.report(i2, xLStatPack);
    }

    private void a(int i, int i2, XLContractResp xLContractResp) {
        XLStatPack xLStatPack = new XLStatPack();
        xLStatPack.mErrorCode = i;
        switch (xLContractResp.mOperateType) {
            case SpdyProtocol.SLIGHTSSL_1_RTT_MODE:
                if (xLContractResp.mContractType == 4096) {
                    xLStatPack.mCommandID = 301001;
                }
                break;
            case XLContractor$XLContractOperate.XL_OPERATE_QUERY:
                if (xLContractResp.mContractType == 4096) {
                    xLStatPack.mCommandID = 301002;
                }
                break;
            case XLContractor$XLContractOperate.XL_OPERATE_DISCONTRACT:
                if (xLContractResp.mContractType == 4096) {
                    xLStatPack.mCommandID = 301003;
                }
                break;
        }
        this.a.report(i2, xLStatPack);
    }
}
